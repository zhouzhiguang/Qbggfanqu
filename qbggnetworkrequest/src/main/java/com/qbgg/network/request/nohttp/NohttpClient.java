package com.qbgg.network.request.nohttp;

import android.content.Context;

import com.yolanda.nohttp.Logger;
import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.cache.DBCacheStore;
import com.yolanda.nohttp.cache.DiskCacheStore;
import com.yolanda.nohttp.cookie.DBCookieStore;

/**
 * 网络请求主框架
 */

public class NohttpClient {
    /**
     * 初始化
     */
    public static void init(Context context) {
        //NoHttp.initialize(context);
        NoHttp.initialize(context, new NoHttp.Config()
                // 设置全局连接超时时间，单位毫秒
                .setConnectTimeout(NohttpConfig.CONNECT_TIME_OUT)
                // 设置全局服务器响应超时时间，单位毫秒
                .setReadTimeout(NohttpConfig.READ_TIME_OUT)
                // 保存到数据库
                .setCacheStore(
                        new DBCacheStore(context).setEnable(true) // 如果不使用缓存，设置false禁用。
                )
                // 或者保存到SD卡
             .setCacheStore(
                     new DiskCacheStore(context)
              )
                .setCookieStore(
                        new DBCookieStore(context).setEnable(false) // 如果不维护cookie，设置false禁用)

                )

        );
        Logger.setDebug(NohttpConfig.IS_SHOWN_NOHTTP_LOG);// 开启NoHttp的调试模式, 配置后可看到请求过程、日志和错误信息。
        Logger.setTag(NohttpConfig.SHOWN_NOHTTP_LOG_TAG);// 设置NoHttp打印Log的tag。
    }
}
