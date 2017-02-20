package com.fanqu.main.location;

/**
 * 注册登录 budil url 工具类
 */

public class LoginRegisteredFactory extends  BaseUrlFactory{
    public static String getRegisteredUrl(){
        StringBuilder builder = new StringBuilder();
        builder.append(getInterfaceBaseUrl());
        builder.append("/Sms/reg");
        return builder.toString();
    }
}
