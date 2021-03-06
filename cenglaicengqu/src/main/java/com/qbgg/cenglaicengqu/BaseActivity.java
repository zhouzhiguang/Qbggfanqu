package com.qbgg.cenglaicengqu;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTabHost;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netease.nim.uikit.common.activity.UI;
import com.qbgg.cenglaicengqu.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.autolayout.SystemBarTintManager;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Build.VERSION_CODES;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

import java.lang.reflect.Field;


/**
 * Created by Administrator on 2016/10/29.
 */

public class BaseActivity extends UI {

    //状态栏沉浸模式使用
    /*statusbar view*/
    private ViewGroup view;
    /*沉浸颜色*/
    private TextView textView;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高

    }



    /**
     * 沉浸模式状态栏初始化
     * @param context 上下文
     * @param statusbar沉浸颜色
     * @return
     */
    @SuppressLint("NewApi")
    public void initStatusbar(Context context, int statusbar_bg) {
        //4.4版本及以上可用
        if (android.os.Build.VERSION.SDK_INT >= VERSION_CODES.KITKAT) {
            // 状态栏沉浸效果
            Window window = ((Activity) context).getWindow();
            window.setFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            //decorview实际上就是activity的外层容器，是一层framlayout
            view = (ViewGroup) ((Activity) context).getWindow().getDecorView();
            LinearLayout.LayoutParams lParams = new LinearLayout.LayoutParams(
                    LayoutParams.MATCH_PARENT, getStatusBarHeight());
            //textview是实际添加的状态栏view，颜色可以设置成纯色，也可以加上shape，添加gradient属性设置渐变色
            textView = new TextView(this);
            textView.setBackgroundResource(statusbar_bg);
            textView.setLayoutParams(lParams);
            view.addView(textView);
        }
    }

    /**
     * 获取状态栏高度
     * @return
     */
    public int getStatusBarHeight() {
        Class<?> c = null;
        Object obj = null;
        Field field = null;
        int x = 0, statusBarHeight = 0;
        try {
            c = Class.forName("com.android.internal.R$dimen");
            obj = c.newInstance();
            field = c.getField("status_bar_height");
            x = Integer.parseInt(field.get(obj).toString());
            statusBarHeight = getResources().getDimensionPixelSize(x);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return statusBarHeight;
    }

    /**
     * 如果项目中用到了slidingmenu,根据slidingmenu滑动百分比设置statusbar颜色：渐变色效果
     * @param alpha
     */
    @SuppressLint("NewApi")
    public void changeStatusBarColor(float alpha) {
        //textview是slidingmenu关闭时显示的颜色
        //textview2是slidingmenu打开时显示的颜色
        textView.setAlpha(1 - alpha);

    }


    /**
     * 初始化沉浸式狀態欄
     */
    protected void initthem() {
        // TODO Auto-generated method stub
        /**
         * 沉浸式状态栏
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏透明 需要在创建SystemBarTintManager 之前调用。
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        // 使StatusBarTintView 和 actionbar的颜色保持一致，风格统一。 设置沉浸式状态
        tintManager.setStatusBarTintResource(R.color.white);
       // tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        tintManager.setStatusBarDarkMode(false, this);
        // 设置状态栏的文字颜色
        tintManager.setStatusBarDarkMode(false, this);
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
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
