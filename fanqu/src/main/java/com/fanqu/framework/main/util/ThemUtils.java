package com.fanqu.framework.main.util;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.view.Window;
import android.view.WindowManager;

import com.fanqu.framework.SystemBarTintManager;


/**
 * Created by Administrator on 2016/11/28.
 */

public   class ThemUtils {
    /**
     * 初始化沉浸式狀態欄
     */
    public static void initthem(Activity activity, int color) {
        // TODO Auto-generated method stub
        /**
         * 沉浸式状态栏
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏透明 需要在创建SystemBarTintManager 之前调用。
            setTranslucentStatus(true,activity);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(activity);
        tintManager.setStatusBarTintEnabled(true);
        // 使StatusBarTintView 和 actionbar的颜色保持一致，风格统一。 设置沉浸式状态
        //tintManager.setStatusBarTintResource(R.color.white);
        tintManager.setStatusBarTintResource(color);
        // tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        tintManager.setStatusBarDarkMode(false, activity);
        // 设置状态栏的文字颜色
        tintManager.setStatusBarDarkMode(false, activity);
    }

    @TargetApi(19)
    protected static void setTranslucentStatus(boolean on,Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win =activity.getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }
}
