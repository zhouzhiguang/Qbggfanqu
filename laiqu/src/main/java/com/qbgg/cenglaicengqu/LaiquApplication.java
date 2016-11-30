package com.qbgg.cenglaicengqu;

import android.app.Application;
import android.content.SharedPreferences;

import com.qbgg.cenglaicengqu.main.util.LogUtil;
import com.qbgg.cenglaicengqu.main.util.PreferenceUitl;
import com.qbgg.network.request.nohttp.NohttpClient;
import com.qbgg.network.request.update.UpdateConfig;

/**
 * Created by Administrator on 2016/11/7.
 */

public class LaiquApplication extends Application {



    private static LaiquApplication mApplication;
    private SharedPreferences mSharedPreferences;


    public static LaiquApplication getInstance() {

        return mApplication;
    }


    public void onCreate() {

        super.onCreate();
        mApplication = this;
        PreferenceUitl.getInstance(getApplicationContext()).init(getApplicationContext());
        initSDK();
        initupdate();
        //开启日志调试功能
        LogUtil.isDebug=true;
    }

    /**
     * 初始化自动更新
     */
    private void initupdate() {
        UpdateConfig.init(this);
    }

    /**
     * 初始化用到的分享登录即时通通讯sdk
     */
    private void initSDK() {
        NohttpClient.init(this);


    }
}
