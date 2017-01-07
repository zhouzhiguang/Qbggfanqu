package com.fanqu.main.acitvities;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.data.UserManager;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.main.login.LoginRegisteredActivity;

import cn.sharesdk.framework.ShareSDK;

public class SpalshActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setImageTransparent(this);
        AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        ShareSDK.initSDK(this);
        ThemUtils.initthem(this, R.color.transparent);
        // setContentView(R.layout.activity_spalsh_lyout);
//        Glide.with(this).load(imageUrl).fitCenter().into(imageView);
        boolean islogined = UserManager.getInstance(this).isLogined();
        //判断当前登录状态
        if (islogined) {

        } else {
            Intent intent = new Intent(SpalshActivity.this, LoginRegisteredActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    /**
     * 图片全屏透明状态栏（图片位于状态栏下面）
     *
     * @param activity
     */
    public static void setImageTransparent(Activity activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        }
    }
}