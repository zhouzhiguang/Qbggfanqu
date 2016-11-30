package com.qbgg.cenglaicengqu.addconference.activitiies;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.managestatus.SystemBarTintManager;

public class EditPublisAddActivity extends BaseActivity implements View.OnClickListener {
    private ImageView edit_publis_add_backtrack;

    private RelativeLayout activityEditPublisAddLayout;
    private RelativeLayout publishHeadLayout;
    private ImageView editPublisAddBacktrack;
    private LinearLayout dinnerPartyLiinerLayout;
    private EditText dinnerPartyDetailedAddress;
    private TextView dinnerPartyRelease;

    private void assignViews() {
        activityEditPublisAddLayout = (RelativeLayout) findViewById(R.id.activity_edit_publis_add_layout);
        publishHeadLayout = (RelativeLayout) findViewById(R.id.publish_head_layout);
        editPublisAddBacktrack = (ImageView) findViewById(R.id.edit_publis_add_backtrack);
        dinnerPartyLiinerLayout = (LinearLayout) findViewById(R.id.dinner_party_liiner_layout);
        dinnerPartyDetailedAddress = (EditText) findViewById(R.id.dinner_party_detailed_address);
        dinnerPartyRelease = (TextView) findViewById(R.id.dinner_party_release);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoUtils.auto(this);
        initthem(R.color.white);
        setContentView(R.layout.activity_edit_publis_add_layout);
        assignViews();
        initLintener();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected void Initwidget() {
        //super.Initwidget();


    }

    private void initLintener() {
        edit_publis_add_backtrack = findView(R.id.edit_publis_add_backtrack);
        dinnerPartyRelease.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dinner_party_release:
                Intent intent=new Intent(this,DinnerReleaseSuccessActivity.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;
        }
    }

    /**
     * 初始化沉浸式狀態欄
     */
    protected void initthem(int color) {
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
        //tintManager.setStatusBarTintResource(R.color.white);
        tintManager.setStatusBarTintResource(color);
        // tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        tintManager.setStatusBarDarkMode(false, this);
        // 设置状态栏的文字颜色
        tintManager.setStatusBarDarkMode(false, this);
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
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
}
