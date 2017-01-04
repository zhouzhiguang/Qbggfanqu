package com.fanqu.framework;

import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.util.DisplayMetrics;

import com.fanqu.framework.data.UserManager;
import com.fanqu.framework.main.util.PreferenceUitl;
import com.qbgg.network.request.nohttp.NohttpClient;
import com.qbgg.network.request.update.UpdateConfig;

public class CustomApplication extends Application {
    /**
     * 屏幕分辨率：屏幕宽度
     */
    public static int SCREEN_WIDTH = 0;

    /**
     * _屏幕分辨率：屏幕高度
     */
    public static int SCREEN_HEIGHT = 0;


    /**
     * 状态栏的高
     */
    public static int STATUSBAR_HEIGHT = 25;

    /**
     * 屏幕密度
     */
    public static float DENSITY;

    public static float SCALE_X;

    /**
     * 只和像素相关的拉伸
     **/
    public static float SCALE_X_ALL;

    public static float SCALE_Y;

    /**
     * 用" 点/英-per-inch"屏幕密度
     */
    public static int DENSITY_DPI;

    private static float xdpi;

    private static float ydpi;

    public static int DEFAULT_PAGE_SIZE = 10;

    public static boolean isPad = false;
    private static CustomApplication mApplication;

    public static CustomApplication getInstance() {

        return mApplication;
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        // TODO Auto-generated method stub
        super.onCreate();
        mApplication = this;
        UserManager userManager = new UserManager(getApplicationContext());
        PreferenceUitl.getInstance(getApplicationContext()).init(getApplicationContext());
        initSDK();
        initupdate();
        //开启日志调试功能
        LogUtil.isDebug = true;
        init();
    }

    /**
     * 初始化自动升级
     */
    private void initupdate() {
        UpdateConfig.init(this);
    }

    private void initSDK() {
        //初始化网络请求
        NohttpClient.init(this);

    }

    private void init() {
        // TODO Auto-generated method stub
        Context context = CustomApplication.this.getApplicationContext();
        DisplayMetrics display = context
                .getResources().getDisplayMetrics();
        SCREEN_WIDTH = display.widthPixels;
        SCREEN_HEIGHT = display.heightPixels;
        // 屏幕的宽大于高宽高到转过来
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int flag = SCREEN_WIDTH;
            SCREEN_WIDTH = SCREEN_HEIGHT;
            SCREEN_HEIGHT = flag;
        }
        // 屏幕密度 density
        DENSITY = context.getResources().getDisplayMetrics().density;
        DENSITY_DPI = 0;//context.getWindowManager().getDefaultDisplay().getMetrics(displaysMetrics);  ((Resources) context).getDisplayMetrics().densityDpi;
        xdpi = context.getResources().getDisplayMetrics().xdpi;
        ydpi = context.getResources().getDisplayMetrics().ydpi;
        double length = Math.sqrt(CustomApplication.SCREEN_WIDTH
                * CustomApplication.SCREEN_WIDTH
                / (CustomApplication.xdpi * CustomApplication.xdpi)
                + CustomApplication.SCREEN_HEIGHT
                * CustomApplication.SCREEN_HEIGHT
                / (CustomApplication.ydpi * CustomApplication.ydpi));
        // 判断是否是平板
        if (length > 6.0f) {
            isPad = true;
        } else {
            isPad = false;
        }

        if (isPad) {
            SCALE_X = 1;
        } else {
            SCALE_X = SCREEN_WIDTH / 480f;
        }
        // scale x all
        SCALE_X_ALL = SCREEN_WIDTH / 480f;
        SCALE_Y = SCREEN_HEIGHT / 800f;

    }

}
