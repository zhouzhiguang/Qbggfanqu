package com.fanqu.main.location;

/**
 * 网络请求基本url
 *
 * http://test-skapi-v1-3.cengfan7.com/Session/login
 */


public abstract class BaseUrlFactory {
    public static final String SCHEME = "http";
    private static final String HOST = "test-skapi-v1-3.cengfan7.com";// 服务器地址

    public static final String COMMON_PATH = "/Session";

    public static String getInterfaceBaseUrl() {
        StringBuilder builder = new StringBuilder();

        builder.append(SCHEME + "://");
        builder.append(HOST);
        builder.append(COMMON_PATH);
        return builder.toString();
    }


}
