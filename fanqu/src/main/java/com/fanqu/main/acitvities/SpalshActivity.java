package com.fanqu.main.acitvities;

import android.content.Intent;
import android.os.Bundle;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.data.UserManager;
import com.fanqu.framework.main.util.ThemUtils;

import cn.sharesdk.framework.ShareSDK;

public class SpalshActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        ShareSDK.initSDK(this);
        ThemUtils.initthem(this,R.color.transparent);
        // setContentView(R.layout.activity_spalsh_lyout);
//        Glide.with(this).load(imageUrl).fitCenter().into(imageView);
        boolean islogined = UserManager.getInstance(this).isLogined();
        //判断当前登录状态
        if (islogined) {

        } else {
            Intent intent = new Intent(SpalshActivity.this, MainActivity.class);
            startActivity(intent);
            overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
            finish();
        }
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
