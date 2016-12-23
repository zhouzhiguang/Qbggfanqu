package com.qbgg.cenglaicengqu.personcentre.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;

public class SetingActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout activitySetingLayout;
    private TextView setingPersonData;
    private TextView setingPersonAccountSafe;
    private TextView seting_person_real_name_authentication;
    private TextView setingPersonNewNotifications;
    private TextView setingPersonCleanCache;
    private TextView setingPersonAboutApp;
    private TextView setingExitLogin;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        // AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_seting_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        Toolbar toolbar = getToolBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showCenterToast(SetingActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        activitySetingLayout = (LinearLayout) findViewById(R.id.activity_seting_layout);
        setingPersonData = (TextView) findViewById(R.id.seting_person_data);
        setingPersonAccountSafe = (TextView) findViewById(R.id.seting_person_account_safe);
        seting_person_real_name_authentication = (TextView) findViewById(R.id.seting_person_real_name_authentication);
        setingPersonNewNotifications = (TextView) findViewById(R.id.seting_person_new_notifications);
        setingPersonCleanCache = (TextView) findViewById(R.id.seting_person_clean_cache);
        setingPersonAboutApp = (TextView) findViewById(R.id.seting_person_about_app);
        setingExitLogin = (TextView) findViewById(R.id.seting_exit_login);
    }

    private void initDate() {

    }

    private void initListener() {
        //个人资料
        setingPersonData.setOnClickListener(this);
        setingPersonAccountSafe.setOnClickListener(this);
        //实名制认证
        seting_person_real_name_authentication.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.seting_person_data:
                //个人中心
                jumpActivity(PersonalDataActivity.class);
                break;
            case R.id.seting_person_account_safe:
                //帐户与安全
                jumpActivity(PersonalAccountSafeActivity.class);
                break;
            case R.id.seting_person_real_name_authentication:
                //实名制认证
               jumpActivity(RealNameAuthenticationActivity.class);
                break;
            default:
                break;
        }
    }

    /**
     * @param clazz 跳转
     */
    private void jumpActivity(Class clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }
}
