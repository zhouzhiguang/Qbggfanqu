package com.fanqu.weixingpayutill;

import android.util.Xml;

import com.fanqu.framework.Constants;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.xmlpull.v1.XmlPullParser;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedMap;


/**
 * 

 */
public class WeiXinPay {
	/** APP_ID 应用从官方网站申请到的合法appId */
	public static final String WX_APP_ID = Constants.WXAPI_PAY_APPID;
	/** 商户号 */
	public static final String WX_PARTNER_ID = Constants.PARTNERID;
	/** 统一下单接口链接 */
	public static final String url = "https://api.mch.weixin.qq.com/pay/unifiedorder";
	/** 商户平台和开发平台约定的API密钥，在商户平台设置 */
	public static final String key =Constants.WXAPI_PAY_API_KEY;
	// 商户号
	private static final String MCH_ID =Constants.PARTNERID;

	// API密钥，在商户平台设置
	public static final String API_KEY = Constants.WXAPI_PAY_API_KEY;//

	StringBuffer mStringBuffer;

	// @SuppressWarnings({ "unchecked", "rawtypes" })
	// public void submitOrder(double realPayPrice, int tradeType, String ip)
	// throws UnsupportedEncodingException {
	// int realpayPrice = (int) (realPayPrice * 100);
	// List <BasicNameValuePair>nvps = new ArrayList();
	// // 调用统一下单接口必需传的参数,可以查看微信支付统一下单接口api查看每个参数的意思
	// ContentValues pair = new ContentValues();
	// Map<String, String> map = new HashMap<String, String>();
	// nvps.add(new BasicNameValuePair("appid", WX_APP_ID));
	// nvps.add(new BasicNameValuePair("body", "body"));
	// nvps.add(new BasicNameValuePair("mch_id", WX_PARTNER_ID));
	// nvps.add(new BasicNameValuePair("nonce_str", UUID.randomUUID()
	// .toString().replace("-", "")));
	// nvps.add(new BasicNameValuePair("notify_url",
	// "http://www.weixin.qq.com/wxpay/pay.php")); // 回调地址需要根据实际项目做修改
	// nvps.add(new BasicNameValuePair("out_trade_no", "100"));
	// nvps.add(new BasicNameValuePair("spbill_create_ip", ip));//
	// ip地址需要根据实际项目做修改
	// nvps.add(new BasicNameValuePair("total_fee", realpayPrice + ""));
	//
	// StringBuffer sb = new StringBuffer();
	//
	// for (BasicNameValuePair nvp : nvps) {
	// sb.append(nvp.getName() + "=" + nvp.getValue() + "&");
	// }
	// // String signA = sb.toString(); // 根据签名格式组装数据，详见微信支付api
	// // String stringSignTemp = signA + "key=" + key; // 根据签名格式组装数据，详见微信支付api
	// // System.out.println("signA=" + signA);
	// // System.out.println("stringSignTemp=" + stringSignTemp);
	// // String sign = DigestUtils.md5Hex(
	// // getContentBytes(stringSignTemp, "UTF-8")).toUpperCase(); //
	// 把组装好的签名数据md5之后字母都转换为大写，详见微信支付api
	// // System.out.println("sign=" + sign);
	// String sign = Payutill.genPackageSign(nvps);
	// nvps.add(new NameValuePair("sign", sign)); // 把签名后的数据组装成参数
	// TenpayHttpClient httpClient = new TenpayHttpClient();
	// httpClient.setReqContent(url);
	// String resContent = "";
	// if (httpClient.callHttpPost(url, toXml(nvps))) {
	// resContent = httpClient.getResContent();
	// String result = new String(resContent.getBytes("GBK"), "UTF-8");
	// System.out.println("请求返回的结果=" + result);
	// }
	// }
	//
	// // 转换成xml格式
	// private String toXml(List<BasicNameValuePair> params) {
	// StringBuilder sb = new StringBuilder();
	// sb.append("<xml>");
	// for (int i = 0; i < params.size(); i++) {
	// sb.append("<" + params.get(i).getName() + ">");
	// sb.append((params.get(i)).getValue());
	// sb.append("</" + params.get(i).getName() + ">");
	// }
	// sb.append("</xml>");
	// System.out.println("xml数据=" + sb.toString());
	// return sb.toString();
	// }
	//
	// // 编码转换
	// public byte[] getContentBytes(String content, String charset) {
	// if (charset == null || "".equals(charset)) {
	// return content.getBytes();
	// }
	// try {
	// return content.getBytes(charset);
	// } catch (UnsupportedEncodingException e) {
	// throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:"
	// + charset);
	// }
	// }
	//
	// public static void main(String[] args) throws
	// UnsupportedEncodingException {
	// WeiXinPay wx = new WeiXinPay();
	// wx.submitOrder(10.00, 1, "127.0.0.1");
	// }
	public void WeiXinPay() {
		mStringBuffer = new StringBuffer();
	}

	/**
	 * @param body body 商品描述
	 * @param notify_url 通知地址 notify_url
	 * @param out_trade_no 商户订单号 out_trade_no
	 * @param total_feer  总金额 这里分为单位
	 * @return
	 */
	public static String genProductArgs(String body, 
			String notify_url, String out_trade_no, String total_feer,String nonceStr,String order_id) {
	
		StringBuffer xml = new StringBuffer();
		WeiXinPay pay = new WeiXinPay();

		try {
			
			xml.append("</xml>");
			List<NameValuePair> packageParams = new LinkedList<NameValuePair>();
			// 应用ID
			packageParams.add(new BasicNameValuePair("appid",
					Constants.WXAPI_PAY_APPID));
			// body 商品描述
			packageParams.add(new BasicNameValuePair("body", body));
			// 商户号 mch_id
			packageParams.add(new BasicNameValuePair("mch_id", MCH_ID));
			// 随机字符串 nonce_str
			packageParams.add(new BasicNameValuePair("nonce_str", nonceStr));
			// 通知地址 notify_url
			if (order_id.equals("-666")) {
				packageParams.add(new BasicNameValuePair("notify_url",
						Constants.WXAPI_RECHARGE_NOTIFY_URL));
			}else {
				packageParams.add(new BasicNameValuePair("notify_url",
						Constants.WXAPI_PAY_NOTIFY_URL));
			}
			// 商户订单号 out_trade_no
			packageParams.add(new BasicNameValuePair("out_trade_no", out_trade_no));
			// 终端IP
			packageParams.add(new BasicNameValuePair("spbill_create_ip",
					"123.12.12.123"));
			// 总金额 这里分为单位
			packageParams.add(new BasicNameValuePair("total_fee", total_feer));
			// 交易类型 trade_type 是 String(16) APP
			packageParams.add(new BasicNameValuePair("trade_type", "APP"));
			String sign = pay.genPackageSign(packageParams);
			packageParams.add(new BasicNameValuePair("sign", sign));
			String xmlstring = pay.toXml(packageParams);
			return xmlstring;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 生成签名
	 */

	private String genPackageSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(key);

		String packageSign = MD5.getMessageDigest(sb.toString().getBytes())
				.toUpperCase();
		
		return packageSign;
	}

	public static String genAppSign(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append(key);
		String appSign = MD5.getMessageDigest(sb.toString().getBytes())
				.toUpperCase();
		return appSign;
	}

	private String toXml(List<NameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		for (int i = 0; i < params.size(); i++) {
			sb.append("<" + params.get(i).getName() + ">");

			sb.append(params.get(i).getValue());
			sb.append("</" + params.get(i).getName() + ">");
		}
		sb.append("</xml>");

		return sb.toString();
	}

	public static String genNonceStr() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000))
				.getBytes());
	}

	public static String genOutTradNo() {
		Random random = new Random();
		return MD5.getMessageDigest(String.valueOf(random.nextInt(10000))
				.getBytes());

	}

	public static Map<String, String> decodeXml(String content) {

		try {
			Map<String, String> xml = new HashMap<String, String>();
			XmlPullParser parser = Xml.newPullParser();
			parser.setInput(new StringReader(content));
			int event = parser.getEventType();
			while (event != XmlPullParser.END_DOCUMENT) {

				String nodeName = parser.getName();
				switch (event) {
				case XmlPullParser.START_DOCUMENT:

					break;
				case XmlPullParser.START_TAG:

					if ("xml".equals(nodeName) == false) {
						// 实例化student对象
						xml.put(nodeName, parser.nextText());
					}
					break;
				case XmlPullParser.END_TAG:
					break;
				}
				event = parser.next();
			}

			return xml;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
	  public static String createSign(String characterEncoding,SortedMap<Object,Object> parameters){  
	        StringBuffer sb = new StringBuffer();  
	        Set es = parameters.entrySet();//所有参与传参的参数按照accsii排序（升序）  
	        Iterator it = es.iterator();  
	        while(it.hasNext()) {  
	            Map.Entry entry = (Map.Entry)it.next();  
	            String k = (String)entry.getKey();  
	            Object v = entry.getValue();  
	            if(null != v && !"".equals(v)   
	                    && !"sign".equals(k) && !"key".equals(k)) {  
	                sb.append(k + "=" + v + "&");  
	            }  
	        }  
	        sb.append("key=" + key);  
	        String packageSign = MD5.getMessageDigest(sb.toString().getBytes())
					.toUpperCase();
	        
	        return packageSign;  
	    } 
	 
}