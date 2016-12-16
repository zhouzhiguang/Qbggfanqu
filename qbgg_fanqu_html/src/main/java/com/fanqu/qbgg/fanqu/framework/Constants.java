package com.fanqu.qbgg.fanqu.framework;

/**
 * 常量类
 */

/**
 * @author Administrator
 */
public class Constants {
    //正式地址
    public static final String LOADING_CURRENT_URL = "http://cengfan7.cn/wap.php";

    public static final String FRIST_START_APP = "frist_start_app";
    public static final String NAME_APP_PARAMS = "appParams";// 存储app的参数
    // UpdateKey.API_TOKEN = "";
    // UpdateKey.APP_ID = "573ecefa748aac36f4000007";
    // 配置自动更新
    public static final String UPDATEKEY_API_TOKEN = "8e1bb6d08a1dda6bb1f4f196fe5e8e35";
    public static final String UPDATEKEY_API_ID = "57d3a808ca87a87e01000834";
    // 主頁加載的html頁面
    // "http://xlanzi.com/index.php/Mobile/Session/login.html";
    public static final String WXPAY_SUCCESS_RESULT = "wxpay_success_action";
    public static final String WXPAY_SUCCESS_RESULT_CODE = "wxpay_code";
    // 微信支付商户号
    public static final String PARTNERID = "1332606201";
    public static final String LOGIN_OPENID = "login_openid";
    public static String WXAPI_PAY_APPID = "wx9ec2ec0486313de5";
    public static String WXAPI_PAY_API_KEY = "cengfan701234567cengfan701234567";
    public static String WXAPI_PAY_SECRET = "2ec8423c0c8c896bb696978b37bb6d2f";
    // notify_url 微信支付回调地址
    public static String WXAPI_PAY_NOTIFY_URL = "http://cengfan7.cn/wap.php/WxPay/pay_notify";
    //public static String WXAPI_PAY_NOTIFY_URL="http://test.cengfan7.cn/wap.php/WxPay/pay_notify";
    //微信充值回调地址 Recharge
    public static String WXAPI_RECHARGE_NOTIFY_URL = "http://cengfan7.cn/wap.php/WxPay/pay_notify2";
    //public static String WXAPI_RECHARGE_NOTIFY_URL="http://test.cengfan7.cn/wap.php/WxPay/pay_notify2";
    // prepay_id支付交易会话标识
    public static String WXAPI_PAY_GET_PREPAY_ID = "https://api.mch.weixin.qq.com/pay/unifiedorder";
    // 支付宝常量
    // 商户ID
    public static final String PARTNER = "2088221421061801";
    // 受款账户
    public static final String SELLER = "cengfanqu@qq.com";
    //MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM/in6hJi3V8OCwCnDWaGc0tPsK8sRi5Ld6ch5IFLfegNvrs2lX81BCkbuCJYswydVS6HkXqLAJO3ux7oeCL4C4q6u5YKQAjG6OCKa8OIyljkuOwjbwjsLX7BMSs02l26WZBPMkb6yFP5vmITr8A5/9lXBiFfPaAnhtIqoAkyd2zAgMBAAECgYA7a+7xHpyTZ39y7QgTtkm7wTwYU/FXPM9ku19xGLPr/UrZ7Mv4Gj3PyAC9g+fOsHmjT1QJ7gbIXh0QgcJiufjJFZPGinC7oLqel6yBR9/pnHLSSk0G/ziDvwobAj3+gPpCMONsQwDZDzJqw4+saMj6xEyUgu0f7OPwNsasNg85kQJBAPA1p+zcHcfZoLxSF7/exQFJIBqCgBGYOmjyScV0BeWe52J6HockXzCzUGtz+V3vxUfxlD2huISE32ZJGgw/sYkCQQDdjQAlpHQG2t8IbxOCID1ZhcKWOxoDJuoF56arnMPiSn5H0Gl4yZmTN5/LASxQiF2S2sUCIA0TLo0QBokccTJbAkEAlAC7YtmND+Irg10hfPsJb21a9nqJ5k1hT+BZS+vc5kouTyLnRGVIsX0Kg+qFLzfY2icjK4ZFZoFoFGHeRsXH4QJBALjWi1ncY2TYtB0gNsFozmZi/15BPsmEbNiyVM52gsLH7aVSjrxUTcmSLcG7X9Jsgs0jshu8y8vGGbBWE4xkMGMCQEoFcFbw6OoveiEiXQOSUrJ1MVqWt2B+meyFh8qkdLmoyVkXIHPOiTU10F/b/ipPuxqtYiAFuIU2ccYW8DB1swc=
    public static final String RSA_PRIVATE = "MIICdwIBADANBgkqhkiG9w0BAQEFAASCAmEwggJdAgEAAoGBAM/in6hJi3V8OCwCnDWaGc0tPsK8sRi5Ld6ch5IFLfegNvrs2lX81BCkbuCJYswydVS6HkXqLAJO3ux7oeCL4C4q6u5YKQAjG6OCKa8OIyljkuOwjbwjsLX7BMSs02l26WZBPMkb6yFP5vmITr8A5/9lXBiFfPaAnhtIqoAkyd2zAgMBAAECgYA7a+7xHpyTZ39y7QgTtkm7wTwYU/FXPM9ku19xGLPr/UrZ7Mv4Gj3PyAC9g+fOsHmjT1QJ7gbIXh0QgcJiufjJFZPGinC7oLqel6yBR9/pnHLSSk0G/ziDvwobAj3+gPpCMONsQwDZDzJqw4+saMj6xEyUgu0f7OPwNsasNg85kQJBAPA1p+zcHcfZoLxSF7/exQFJIBqCgBGYOmjyScV0BeWe52J6HockXzCzUGtz+V3vxUfxlD2huISE32ZJGgw/sYkCQQDdjQAlpHQG2t8IbxOCID1ZhcKWOxoDJuoF56arnMPiSn5H0Gl4yZmTN5/LASxQiF2S2sUCIA0TLo0QBokccTJbAkEAlAC7YtmND+Irg10hfPsJb21a9nqJ5k1hT+BZS+vc5kouTyLnRGVIsX0Kg+qFLzfY2icjK4ZFZoFoFGHeRsXH4QJBALjWi1ncY2TYtB0gNsFozmZi/15BPsmEbNiyVM52gsLH7aVSjrxUTcmSLcG7X9Jsgs0jshu8y8vGGbBWE4xkMGMCQEoFcFbw6OoveiEiXQOSUrJ1MVqWt2B+meyFh8qkdLmoyVkXIHPOiTU10F/b/ipPuxqtYiAFuIU2ccYW8DB1swc=";
    public static final String ALIPAY_NOTIFY_URL = "http://cengfan7.cn/wap.php/Alipay/notifyApp";
    //public static final String ALIPAY_NOTIFY_URL="http://test.cengfan7.cn/wap.php/Alipay/notifyApp";
    //支付宝充值回调地址 RECHARGE
    public static final String ALIPAY_RECHARGE_NOTIFY_URL = "cengfan7.cn/wap.php/Alipay/recharge_notify_app";
    //测试版本
    //public static final String ALIPAY_RECHARGE_NOTIFY_URL="http://test.cengfan7.cn/wap.php/Alipay/recharge_notify_app";
    //支付成功后回调地址通知页面更新
    public static final String PAY_RUSULT_NOTIFY_URL = "http://cengfan7.cn/wap.php?m=Wap&c=Order&a=pay_complete&id=";
    //测试地址
    //public static final String  PAY_RUSULT_NOTIFY_URL="http://test.cengfan7.cn/wap.php?m=Wap&c=Order&a=pay_complete&id=";
    //当前位置 String targetmine MapLocation
    public static final String MINE_MAP_LOCATION = "mine_MapLocation";
    public static final String DESTINATION_LOCATION = "destination_Location";
}

