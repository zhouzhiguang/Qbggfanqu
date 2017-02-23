package com.fanqu.main.location;

/**
 * 注册登录 budil url 工具类
 */

public class LoginRegisteredFactory extends BaseUrlFactory {
    /**
     * @return 获取手机验证码短信请求
     */
    public static String getRegisteredphoneverificationcodeUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append(getInterfaceBaseUrl());
        builder.append("/Sms/reg");
        return builder.toString();
    }

    public static String getWeChatLoginUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append(getInterfaceBaseUrl());
        builder.append("/Session/wx_login");
        return builder.toString();
    }

    public static String getLoginUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append(getInterfaceBaseUrl());
        builder.append("/Session/login");
        return builder.toString();
    }

    public static String getBindPhoneNumberUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append(getInterfaceBaseUrl());
        builder.append("/Session/bind_phone");
        return builder.toString();
    }

    public static String getBindphoneMessagecodeUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append(getInterfaceBaseUrl());
        builder.append("/Sms/bindphone");
        return builder.toString();
    }

    public static String getRegisteredUrl() {
        StringBuilder builder = new StringBuilder();
        builder.append(getInterfaceBaseUrl());
        builder.append("/Session/reg");
        return builder.toString();
    }


}
