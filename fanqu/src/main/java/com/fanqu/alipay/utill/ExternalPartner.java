package com.fanqu.alipay.utill;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Message;

import com.alipay.sdk.app.PayTask;
import com.fanqu.framework.Constants;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


/**
 * 支付宝调用类 该类使用方法 获取单例执行 doOrder方法即可
 * 
 * 
 */

public class ExternalPartner {
	private static final int SDK_PAY_FLAG = 5;

	private static final int SDK_CHECK_FLAG = 6;

	public static final String TAG = "alipay-sdk";

	private static ExternalPartner externalPartner;

	private Context context = null;
	private String orderNO = null;
	private String orderName = null;
	private Handler mHandler = null;
	private String orderFEE = "";

	/**
	 * 支付宝快捷支付
	 * 
	 * @param context
	 *            上下文
	 * @param orderNO
	 *            订单号
	 * @param mHandler
	 *            回调响应句柄 "9000", "操作成功"; "4000", "系统异常" "4001","数据格式不正确"
	 *            "4003","该用户绑定的支付宝账户被冻结或不允许支付" "4004", "该用户已解除绑定" "4005",
	 *            "绑定失败或没有绑定" "4006", "订单支付失败" "4010","重新绑定账户" "6000",
	 *            "支付服务正在进行升级操作" "6001", "用户中途取消支付操作" "7001", "网页支付失败"
	 * @param orderFEE
	 *            价格
	 * @param orderName
	 *            商品名
	 */
	private ExternalPartner(Context context, String orderName, String orderNO,
			Handler mHandler, String orderFEE) {
		this.context = context;
		this.orderNO = orderNO;
		this.mHandler = mHandler;
		this.orderFEE = orderFEE;
		this.orderName = orderName;
	}

	/**
	 * 支付宝快捷支付调用类
	 * 
	 * @param context
	 *            上下文
	 * @param orderNO
	 *            订单号
	 * @param mHandler
	 *            回调响应句柄
	 * @param orderFEE
	 *            价格
	 */
	public static ExternalPartner getInstance(Context context,
			String orderName, String orderNO, Handler mHandler, String orderFEE) {
		externalPartner = new ExternalPartner(context, orderName, orderNO,
				mHandler, orderFEE);
		return externalPartner;
	}

	/**
	 * 执行支付方法
	 */
	public void doOrder(String order_id) {
		// 订单
		String info = getNewOrderInfo(orderNO, orderFEE,order_id);
		// 对订单做RSA 签名
		String sign = sign(info);
		try {
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		info += "&sign=\"" + sign + "\"&" + getSignType();

		try {
			// 仅需对sign 做URL编码
			sign = URLEncoder.encode(sign, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}

		// 完整的符合支付宝参数规范的订单信息
		final String payInfo = info;
		Runnable payRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask alipay = new PayTask((Activity) context);
				// 调用支付接口，获取支付结果
				String result = alipay.pay(payInfo, true);

				Message msg = new Message();
				msg.what = SDK_PAY_FLAG;
				msg.obj = result;
				mHandler.sendMessage(msg);
			}
		};

		// 必须异步调用
		Thread payThread = new Thread(payRunnable);
		payThread.start();
	}

	private String getNewOrderInfo(String ordeNO, String orderFEE,String order_id) {
		StringBuilder sb = new StringBuilder();
		sb.append("partner=\"");
		// 合作身份者id
		sb.append(Constants.PARTNER);
		sb.append("\"&out_trade_no=\"");
		sb.append(ordeNO);
		sb.append("\"&subject=\"");
		sb.append(orderName);
		sb.append("\"&body=\"");
		sb.append("body");
		sb.append("\"&total_fee=\"");
		sb.append(orderFEE);
		sb.append("\"&notify_url=\"");
		// 网址需要做URL编码
		if (order_id.equals("-666")) {
			sb.append( Constants.ALIPAY_RECHARGE_NOTIFY_URL);
		}else {
			sb.append( Constants.ALIPAY_NOTIFY_URL);
		}
		sb.append("\"&service=\"mobile.securitypay.pay");
		sb.append("\"&_input_charset=\"UTF-8");
		sb.append("\"&return_url=\"");
		try {
			sb.append(URLEncoder.encode("http://m.alipay.com","GBK"));
		}catch (UnsupportedEncodingException  e){
			e.printStackTrace();
		}

		sb.append("\"&payment_type=\"1");
		sb.append("\"&seller_id=\"");
		// // 收款支付宝账号
		sb.append(Constants.SELLER);

		// 如果show_url值为空，可不传
		// sb.append("\"&show_url=\"");
		sb.append("\"&it_b_pay=\"1m");
		sb.append("\"");

		return new String(sb);
	}

	private String getSignType() {

		return "sign_type=\"RSA\"";
	}

	/**
	 * sign the order info. 对订单信息进行签名
	 * 
	 * @param content
	 *            待签名订单信息
	 */
	public String sign(String content) {
		// 商户私钥
		return SignUtils.sign(content, Constants.RSA_PRIVATE);
	}

	public static class Product {
		public String subject;
		public String body;
		public String price;
	}

	public static Product[] sProducts;

	/**
	 * check whether the device has authentication alipay account.
	 * 查询终端设备是否存在支付宝认证账户
	 * 
	 */
	public void check() {
		Runnable checkRunnable = new Runnable() {

			@Override
			public void run() {
				// 构造PayTask 对象
				PayTask payTask = new PayTask((Activity) context);
				// 调用查询接口，获取查询结果
				boolean isExist = true;// payTask.checkAccountIfExist();

				Message msg = new Message();
				msg.what = SDK_CHECK_FLAG;
				msg.obj = isExist;
				mHandler.sendMessage(msg);
			}
		};

		Thread checkThread = new Thread(checkRunnable);
		checkThread.start();

	}
}