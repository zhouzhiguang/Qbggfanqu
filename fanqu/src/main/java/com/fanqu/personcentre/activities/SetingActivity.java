package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.main.widget.SwitchButton;


public class SetingActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout activitySetingLayout;
    private TextView setingPersonData;
    private TextView setingPersonAccountSafe;
    private TextView seting_person_real_name_authentication;
    private SwitchButton notifications_swithch;
    private TextView setingPersonCleanCache;
    private TextView setingPersonAboutApp;
    private TextView setingExitLogin;
    private TextView seting_person_clean_cache_text;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
      //  AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
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
        activitySetingLayout = findView(R.id.activity_seting_layout);
        setingPersonData = findView(R.id.seting_person_data);
        setingPersonAccountSafe = findView(R.id.seting_person_account_safe);
        seting_person_real_name_authentication = findView(R.id.seting_person_real_name_authentication);
        seting_person_clean_cache_text=findView(R.id.seting_person_clean_cache_text);
        notifications_swithch = findView(R.id.seting_person_new_notifications_swithch);
        setingPersonCleanCache = findView(R.id.seting_person_clean_cache);
        setingPersonAboutApp = findView(R.id.seting_person_about_app);
        setingExitLogin = findView(R.id.seting_exit_login);
    }

    private void initDate() {

    }

    private void initListener() {
        //个人资料
        setingPersonData.setOnClickListener(this);
        setingPersonAccountSafe.setOnClickListener(this);
        //实名制认证
        seting_person_real_name_authentication.setOnClickListener(this);
        //关于蹭范趣
        setingPersonAboutApp.setOnClickListener(this);
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
            case R.id.seting_person_about_app:
                jumpActivity(AboutFanquActivity.class);
            break;
            default:
                break;
        }
    }

//    /**
//     * @param clazz 跳转
//     */
//    private void jumpActivity(Class clazz) {
//        Intent intent = new Intent(this, clazz);
//        startActivity(intent);
//        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
//    }
}
