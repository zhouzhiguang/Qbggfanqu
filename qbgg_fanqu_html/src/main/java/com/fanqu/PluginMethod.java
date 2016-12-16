package com.fanqu;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.text.TextUtils;
import android.webkit.CookieManager;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;

import com.fanqu.qbgg.fanqu.alipay.utill.ExternalPartner;
import com.fanqu.qbgg.fanqu.alipay.utill.PayResult;
import com.fanqu.qbgg.fanqu.framework.Constants;
import com.fanqu.qbgg.fanqu.framework.LogUtil;
import com.fanqu.qbgg.fanqu.framework.utills.ToastUtils;
import com.fanqu.qbgg.fanqu.weixingpayutill.Util;
import com.fanqu.qbgg.fanqu.weixingpayutill.WeiXinPay;
import com.fanqu.qbgg.fanqu.widget.NavigationDialog;
import com.fanqu.qbgg.fanqu.widget.NotetakeShareDialog;
import com.google.gson.Gson;
import com.tencent.mm.sdk.constants.Build;
import com.tencent.mm.sdk.modelpay.PayReq;
import com.tencent.mm.sdk.openapi.IWXAPI;
import com.tencent.mm.sdk.openapi.WXAPIFactory;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import cn.sharesdk.framework.Platform;
import cn.sharesdk.framework.PlatformActionListener;
import cn.sharesdk.framework.ShareSDK;
import cn.sharesdk.sina.weibo.SinaWeibo;
import cn.sharesdk.tencent.qq.QQ;
import cn.sharesdk.wechat.friends.Wechat;

@SuppressLint("NewApi")

public class PluginMethod implements PlatformActionListener, Callback {

    private WebView webView;
    private PhoneGap2Activity droidGap;
    private Handler handler;
    private static final int MSG_AUTH_CANCEL = 1;// cancel
    private static final int MSG_AUTH_ERROR = 2;
    private static final int MSG_AUTH_COMPLETE = 3;
    private static final int MSG_AUTH_TEST = 4;
    private static final int SDK_PAY_FLAG = 5;
    private static final int SDK_CHECK_FLAG = 6;
    private static final int MSG_PAY_ERROR = 7;
    private static final int PAY_RESULT_NOTIFY = 8;
    private IWXAPI msgApi;
    private PhoneGap2Activity activity;
    private StringBuffer sb;
    private String order_id;
    private WebView cordovaWebView;

    @SuppressLint("NewApi")
    public PluginMethod(Activity activity, WebView view) {
        cordovaWebView = view;
        webView = view;

        droidGap = (PhoneGap2Activity) activity;
        this.activity = droidGap;
        sb = new StringBuffer();
        msgApi = WXAPIFactory.createWXAPI(droidGap.getApplicationContext(),
                Constants.WXAPI_PAY_APPID, true);

        msgApi.registerApp(Constants.WXAPI_PAY_APPID);
        handler = new Handler(Looper.getMainLooper(), this);
    }

    public interface WxpayResultListener {
        void wxpaysuccess();

    }

    /**
     * JS调用 用于更新App
     *
     * @param path 更新门店的地址
     */
    public void UpdateApp(final String path) {
        System.out.println("---------------"); // 注意这里日志输出
    }

    /**
     * 注销登录了回到登录界面
     */
    @JavascriptInterface
    public void escUser() {
        cordovaWebView.post(new Runnable() {
            @Override
            public void run() {
                String result_url = "http://cengfan7.cn/wap.php?m=Wap&c=index&a=login&wx_auto_login=0";
                //String result_url = "http://test.cengfan7.cn/wap.php?m=Wap&c=index&a=login&wx_auto_login=0";
                cordovaWebView.clearHistory();
                // System.out.println("蹭饭趣注销登录---");
                Message msg = new Message();
                msg.what = PAY_RESULT_NOTIFY;
                msg.obj = result_url;
                handler.sendMessageDelayed(msg, 1000);
                CookieManager cookieManager = CookieManager.getInstance();
                cookieManager.removeAllCookie();
                webView.clearHistory();
                clearCacheFolder(activity.getCacheDir(), System.currentTimeMillis());
            }
        });


    }

    private int clearCacheFolder(File dir, long numDays) {
        int deletedFiles = 0;
        if (dir != null && dir.isDirectory()) {
            try {
                for (File child : dir.listFiles()) {
                    if (child.isDirectory()) {
                        deletedFiles += clearCacheFolder(child, numDays);
                    }
                    if (child.lastModified() < numDays) {
                        if (child.delete()) {
                            deletedFiles++;
                        }
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return deletedFiles;
    }

    /*
     * 分享 share(JSON.stringify(json))
     */
    @JavascriptInterface
    public void share(String json) {
        NotetakeShareDialog dialog = new NotetakeShareDialog(activity);
        if (!dialog.isShowing()) {
            dialog.setShareDate(json);
            dialog.show();
        }

    }

    /**
     * 支付宝
     */
    @JavascriptInterface
    public void AliPay(String json) {
        if (TextUtils.isEmpty(json)) {
            System.out.println("------为空了------");
        } else {
            alipay(json);
        }
    }

    /**
     * @param 传入json 支付宝支付
     *               <p>
     *               {"order_sn":"201609021230018490","order_id":"684","amount":
     *               "0.01","good_name":"鲜篮子西冷牛排等商品","desc":"鲜篮子商品"}
     */
    @JavascriptInterface
    private void alipay(String json) {

        try {

            JSONObject object = new JSONObject(json);
            String orderName = object.getString("good_name");
            order_id = object.getString("order_id");
            String orderNO = object.getString("order_sn");
            String orderFEE = object.getString("amount");
            ExternalPartner.getInstance(activity, orderName, orderNO, handler,
                    orderFEE).doOrder(order_id);

        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * 微信支付 WxPay
     */

    @JavascriptInterface
    public void WxPay(String json) {
        String order_id = null;
        String order_sn = null;
        String amount = null, good_name = null, desc = null;
        boolean appInstalled = msgApi.isWXAppInstalled();
        boolean isPaySupported = msgApi.getWXAppSupportAPI() >= Build.PAY_SUPPORTED_SDK_INT;
        if (!appInstalled) {
            ToastUtils.showToast(activity, R.string.weixin_app_notinstall);
        } else {
            if (!isPaySupported) {
                ToastUtils.showToast(activity, R.string.weixin_pay_nonsupport);
            } else {

                try {
                    JSONObject jsonObject = new JSONObject(json);
                    order_sn = jsonObject.getString("order_sn");
                    order_id = jsonObject.getString("order_id");
                    amount = jsonObject.getString("amount");
                    good_name = jsonObject.getString("good_name");
                    desc = jsonObject.getString("desc");
                    String nonceStr = WeiXinPay.genNonceStr();
                    GetPrepayIdTask getPrepayId = new GetPrepayIdTask();
                    int f = Integer
                            .valueOf((int) (Float.parseFloat(amount) * 100));
                    String money = String.valueOf(f);
                    String[] params = new String[]{order_sn, order_id, money,
                            good_name, desc, nonceStr};
                    getPrepayId.execute(params);
                    this.order_id = order_id;
                } catch (JSONException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 微信支付请求客户端
     *
     * @param result
     * @return
     */
    private String sendPayReq(Map<String, String> result, String desc,
                              String notify_url, String out_trade_no, String total_feer,
                              String good_name, String nonceStr, String order_id) {
        if (msgApi != null) {

            if (msgApi.isWXAppInstalled()) {
                System.out.println(result.toString() + "获取预付id");
                if (!result.isEmpty()) {
                    PayReq req = new PayReq();
                    req.appId = Constants.WXAPI_PAY_APPID;
                    // 商户号 partnerid
                    req.partnerId = Constants.PARTNERID;
                    String prepay_id = result.get("prepay_id");
                    req.prepayId = prepay_id;
                    req.packageValue = "Sign=WXPay";
                    req.nonceStr = WeiXinPay.genNonceStr().toUpperCase();
                    String timestamo = String.valueOf(System
                            .currentTimeMillis() / 1000);
                    req.timeStamp = timestamo;
                    SortedMap<Object, Object> parameters = new TreeMap<Object, Object>();
                    parameters.put("appid", Constants.WXAPI_PAY_APPID);
                    parameters.put("partnerid", Constants.PARTNERID);
                    parameters.put("prepayid", req.prepayId);
                    parameters.put("package", req.packageValue);
                    parameters.put("noncestr", req.nonceStr);
                    parameters.put("timestamp", req.timeStamp);
                    String characterEncoding = "UTF-8";
                    String mySign = WeiXinPay.createSign(characterEncoding,
                            parameters);
                    // List<NameValuePair> packageParams = new
                    // LinkedList<NameValuePair>();
                    // packageParams.add(new BasicNameValuePair("appid",
                    // Constants.WXAPI_PAY_APPID));
                    // packageParams.add(new BasicNameValuePair("mch_id",
                    // req.nonceStr));
                    // packageParams.add(new BasicNameValuePair("package",
                    // req.packageValue));
                    // // 商户号 partnerid
                    // packageParams.add(new BasicNameValuePair("partnerid",
                    // Constants.PARTNERID));
                    // packageParams.add(new BasicNameValuePair("prepayid",
                    // req.prepayId));
                    // packageParams.add(new BasicNameValuePair("timestamp",
                    // req.timeStamp));
                    req.sign = mySign;// WeiXinPay.genAppSign(packageParams);
                    // System.out.println(req.sign + "sign 参数是多少");
                    msgApi.registerApp(Constants.WXAPI_PAY_APPID);
                    boolean sendReq = msgApi.sendReq(req);
                    // true微信支付后返回结果
                    return "参数";
                }
            }
        }
        return null;

    }

    /**
     * qq登錄了
     */
    @JavascriptInterface
    public void qqLogin() {
        Platform qq = ShareSDK.getPlatform(QQ.NAME);
        if (qq.isAuthValid()) {
            qq.removeAccount(true);
            return;
        }
        qq.SSOSetting(false);
        // 使用SSO授权，通过客户单授权
        qq.setPlatformActionListener(this);
        qq.showUser(null);

    }

    /**
     * 微信登录
     */
    @JavascriptInterface
    public void wxLogin() {
        Platform wechat = ShareSDK.getPlatform(Wechat.NAME);
        if (wechat.isAuthValid()) {
            wechat.removeAccount(true);
            return;
        }
        // 使用SSO授权，通过客户单授权
        wechat.SSOSetting(false);
        wechat.setPlatformActionListener(this);
        wechat.showUser(null);
        // Toast.makeText(droidGap.getActivity(), "微信登录了", Toast.LENGTH_SHORT)
        // .show();
    }

    /**
     * sina微博
     */
    @JavascriptInterface
    public void wbLogin() {
        Platform sinaweibo = ShareSDK.getPlatform(SinaWeibo.NAME);
        if (sinaweibo.isAuthValid()) {
            sinaweibo.removeAccount(true);
            return;
        }
        // 使用SSO授权，通过客户单授权
        sinaweibo.SSOSetting(true);

        sinaweibo.setPlatformActionListener(this);
        sinaweibo.showUser(null);
    }

    // 把数据写入SD卡
    private void writeSDcard(String str) {
        try {
            // 判断是否存在SD卡
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                // 获取SD卡的目录
                File file = Environment.getExternalStorageDirectory();
                FileOutputStream fileW = new FileOutputStream(
                        file.getCanonicalPath() + "/test.txt");
                fileW.write(str.getBytes());
                fileW.close();
            } else {
                showMessage("SD卡不存在！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 从SD卡中读取数据
    private String readSDcard() {
        StringBuffer str = new StringBuffer();
        try {
            // 判断是否存在SD
            if (Environment.getExternalStorageState().equals(
                    Environment.MEDIA_MOUNTED)) {
                File file = new File(Environment.getExternalStorageDirectory()
                        .getCanonicalPath() + "/test.txt");
                // 判断是否存在该文件
                if (file.exists()) {
                    // 打开文件输入流
                    FileInputStream fileR = new FileInputStream(file);
                    BufferedReader reads = new BufferedReader(
                            new InputStreamReader(fileR));
                    String st = null;
                    while ((st = reads.readLine()) != null) {
                        str.append(st);
                    }
                    fileR.close();
                } else {
                    // txt1.setText("该目录下文件不存在");
                }
            } else {
                Toast.makeText(activity, "SD卡不存在！！", Toast.LENGTH_SHORT).show();
                // showMessage("SD卡不存在！！");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return str.toString();
    }

    // 显示信息
    public void showMessage(String msg) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * QQ登录
     */
    @JavascriptInterface
    public void qq_native_login(final String json) {

        activity.runOnUiThread(new Runnable() {
            public void run() {
                /*
                 * {"is_yellow_vip":"0","msg":"","vip":"0","nickname":"，旧的回忆",
				 * "figureurl_qq_1":
				 * "http://q.qlogo.cn/qqapp/1105570013/BE20832C7715B28CC6E5BD1D600341DA/40"
				 * , "city":"深圳","figureurl_1":
				 * "http://qzapp.qlogo.cn/qzapp/1105570013/BE20832C7715B28CC6E5BD1D600341DA/50"
				 * , "gender":"男","province":"广东","is_yellow_year_vip":"0",
				 * "yellow_vip_level":"0","
				 * figureurl":"http://qzapp.qlogo.cn/qzapp
				 * /1105570013/BE20832C7715B28CC6E5BD1D600341DA
				 * /30","figureurl_2":" http://qzapp.qlogo.cn/qzapp/1105570013/
				 * BE20832C7715B28CC6E5BD1D600341DA
				 * /100","is_lost":0,"figureurl_qq_2":
				 * "http://q.qlogo.cn/qqapp/1105570013/BE20832C7715B28CC6E5BD1D600341DA/100"
				 * ,"level":"0","ret":0}
				 */
                webView.loadUrl("javascript:qq_native_login('" + json + "')");

            }
        });
    }

    /*
     * 取消
     */
    public void onCancel(Platform platform, int action) {
        // TODO Auto-generated method stub
        Message msg = new Message();
        msg.what = MSG_AUTH_CANCEL;
        handler.sendMessage(msg);
    }

    /*
     * 授权成功
     */
    public void onComplete(Platform platform, int action,
                           HashMap<String, Object> res) {
        // TODO Auto-generated method stub
        Message msg = new Message();
        msg.what = MSG_AUTH_COMPLETE;
        msg.arg2 = action;
        // 获取用户OpenID
        String openid = platform.getDb().getUserId();
        msg.obj = new Object[]{platform.getName(), res};
        Bundle bundle = new Bundle();
        bundle.putString(Constants.LOGIN_OPENID, openid);
        msg.setData(bundle);
        handler.sendMessage(msg);
        // Toast.makeText(droidGap.getActivity(), res.toString(),
        // Toast.LENGTH_SHORT).show();
        // Toast.makeText(droidGap.getActivity(), "微信登录成功了", Toast.LENGTH_SHORT)
        // .show();

    }

    public void onError(Platform platform, int action, Throwable t) {
        // TODO Auto-generated method stub
        // Toast.makeText(droidGap.getActivity(), "微信登录失败了", Toast.LENGTH_SHORT)
        // .show();
        // Toast.makeText(droidGap.getActivity(), "出错",
        // Toast.LENGTH_SHORT).show();
        if (action == Platform.ACTION_USER_INFOR) {
            Message msg = new Message();
            msg.what = MSG_AUTH_ERROR;
            msg.arg2 = action;
            msg.obj = t;
            handler.sendMessage(msg);
        }
        t.printStackTrace();
    }

    public boolean handleMessage(Message msg) {
        // TODO Auto-generated method stub
        switch (msg.what) {
            case MSG_AUTH_CANCEL: {
                // 取消
                // Toast.makeText(droidGap.getActivity(),
                // R.string.share_from_html_cancel_text, Toast.LENGTH_SHORT)
                // .show();
            }
            break;
            case MSG_AUTH_ERROR: {
                // 失败
                Throwable t = (Throwable) msg.obj;
                String text = "caught error: " + t.getMessage();
                Toast.makeText(activity, text, Toast.LENGTH_SHORT).show();
                t.printStackTrace();
            }
            break;
            case MSG_AUTH_COMPLETE: {

                // 成功
                Object[] objs = (Object[]) msg.obj;
                String plat = (String) objs[0];
                @SuppressWarnings("unchecked")
                HashMap<String, Object> res = (HashMap<String, Object>) objs[1];
                String openid = msg.getData().getString(Constants.LOGIN_OPENID);
                if (!TextUtils.isEmpty(openid)) {
                    res.put("openid", openid);
                }

                Gson gson = new Gson();
                String json = gson.toJson(res);
                // 动作标志
                int arg2 = msg.arg2;
                if (plat.equals("Wechat")) {
                    weixin_native_login(json);
                } else if (plat.equals("QQ")) {
                    qq_native_login(json);
                } else {
                    weibo_native_login(json);
                }

            }
            break;
            case MSG_AUTH_TEST:
                Toast.makeText(activity, "授权事件", Toast.LENGTH_SHORT).show();
                break;

            case SDK_PAY_FLAG: {
                PayResult payResult = new PayResult((String) msg.obj);
                // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                String resultInfo = payResult.getResult();
                String resultStatus = payResult.getResultStatus();

                // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                if (TextUtils.equals(resultStatus, "9000")) {
                    // http://cengfan7.cn/wap.php?m=Wap&c=Order&a=pay_complete&id=
                    ToastUtils.showToast(activity, R.string.weixin_pay_succeed);
                    payresultnotify();
                } else {
                    // 判断resultStatus 为非“9000”则代表可能支付失败
                    // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                    if (TextUtils.equals(resultStatus, "8000")) {
                        ToastUtils.showToast(activity, R.string.alipay_result);

                    } else if (TextUtils.equals(resultStatus, "6001")) {
                        ToastUtils.showToast(activity, R.string.weixin_pay_cancel);
                    } else if (TextUtils.equals(resultStatus, "4000")) {
                        // 订单支付失败
                        ToastUtils.showToast(activity,
                                R.string.alipay_pay_order_failure);
                    } else {
                        // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                        Toast.makeText(activity, "支付失败", Toast.LENGTH_SHORT).show();

                    }
                }
                break;

            }
            case MSG_PAY_ERROR:
                String string = (String) msg.obj;
                if (!TextUtils.isEmpty(string)) {
                    ToastUtils.showToast(activity, string);
                }
                break;

            case PAY_RESULT_NOTIFY:
                String result_url = (String) msg.obj;
                System.out.println(result_url + "支付成功后刷新界面");
                // CookieManager cookieManager = CookieManager.getInstance();
                // String CookieStr = cookieManager.getCookie(result_url);
                cordovaWebView.loadUrl(result_url);

                // cordovaWebView.loadUrlIntoView(result_url);
                // droidGap.shouldOverrideUrlLoading
                // droidGap.loadUrlNo
                // droidGap.loadUrl(result_url, 3000);
                break;
        }
        return false;
    }

    /**
     * 微博登录
     */
    @JavascriptInterface
    private void weibo_native_login(final String json) {
        // TODO Auto-generated method stub
        activity.runOnUiThread(new Runnable() {
            public void run() {

                webView.loadUrl("javascript:weibo_native_login('" + json + "')");

            }
        });
    }

    /**
     * 微信登录
     */
    @JavascriptInterface
    private void weixin_native_login(final String json) {
        // TODO Auto-generated method stub
        activity.runOnUiThread(new Runnable() {
            public void run() {
                Toast.makeText(activity, "微信登录", Toast.LENGTH_SHORT).show();
                webView.loadUrl("javascript:weixin_native_login('" + json
                        + "')");

            }
        });
    }

    /**
     * 获取PrepayId 预付id
     */
    private class GetPrepayIdTask extends AsyncTask<String, Void, String> {

        private ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            dialog = ProgressDialog.show(activity, activity.getResources()
                    .getString(R.string.app_tip), activity.getResources()
                    .getString(R.string.getting_prepayid));

        }

        @Override
        protected void onPostExecute(String result) {
            if (dialog != null) {
                dialog.dismiss();
            }
        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
        }

        @Override
        protected String doInBackground(String... params) {
            String order_sn = params[0];
            String order_id = params[1];
            String amount = params[2];
            String good_name = params[3];
            String desc = params[4];
            String nonceStr = params[5];
            String res = null;

            String url = String
                    .format("https://api.mch.weixin.qq.com/pay/unifiedorder");
            String entity = WeiXinPay.genProductArgs(desc, "", order_sn,
                    amount, nonceStr, order_id);
            try {
                String parameter = new String(entity.toString().getBytes(),
                        "ISO-8859-1");
                byte[] buf = Util.httpPost(url, parameter);
                String content = new String(buf);
                //
                // CommonHttpClient commonHttpClient = CommonHttpClientFactory
                // .buildHttpClient(activity);
                // String prepayId = commonHttpClient.getPrepayId(parameter);
                // System.out.println("commonHttpClient prepayId请求:" +
                // prepayId);
                Map<String, String> xml = WeiXinPay.decodeXml(content);

                String result_code = xml.get("result_code");
                if (result_code.equals("FAIL")) {
                    String err_code_des = xml.get("err_code_des");

                    Message msg = new Message();
                    msg.what = MSG_PAY_ERROR;
                    msg.obj = err_code_des;
                    handler.sendMessage(msg);
                } else {
                    res = sendPayReq(xml, desc, "", order_sn, amount,
                            good_name, nonceStr, order_id);
                }

                return res;
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            return entity;

        }
    }

    /**
     * @param order_id 传递订单id
     * @return 支付成功后的结果回调地址
     */
    private String getpayresultulr(String order_id) {
        StringBuffer buffer = new StringBuffer();
        buffer.append(Constants.PAY_RUSULT_NOTIFY_URL);
        buffer.append(order_id);
        return buffer.toString();

    }

    /**
     * 微信支付成功后回調刷新界面
     */
    public void payresultnotify() {
        // TODO Auto-generated method stub
        if (!order_id.equals("-666")) {
            String result_url = getpayresultulr(order_id);
            Message msg = new Message();
            msg.what = PAY_RESULT_NOTIFY;
            msg.obj = result_url;
            handler.sendMessageDelayed(msg, 1000);
        }
    }

    /**
     * 拨打电话号码
     */
    @JavascriptInterface
    public void telephone(String phonenumber) {
        Intent intent = new Intent(Intent.ACTION_CALL);
        intent.setData(Uri.parse("tel:" + phonenumber));
        activity.startActivity(intent);

    }

    /**
     * 定位了
     */
    @JavascriptInterface
    public void navigation(String navlnfo) {
        Intent intent = new Intent(activity, NavigationActivity.class);
        intent.putExtra(Constants.MINE_MAP_LOCATION,activity.getMineMapLocation());
        Gson gson = new Gson();
        MapLocation  destination = gson.fromJson(navlnfo, MapLocation.class);
        intent.putExtra(Constants.DESTINATION_LOCATION,destination);
        activity.startActivity(intent);

//        NavigationDialog dialog = new NavigationDialog(activity);
//        if (!dialog.isShowing()) {
//            dialog.setShareDate(navlnfo);
//            dialog.setStartLocation(activity.getMineMapLocation());
//            dialog.show();
//        }

    }
}
