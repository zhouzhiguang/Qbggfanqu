package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;

import com.fanqu.R;
import com.fanqu.framework.CustomApplication;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.DensityUtil;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.personcentre.frgment.NoCertificationFragment;


/**
 * 实名制认证页面
 */
public class RealNameAuthenticationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_real_name_authentication_layout);
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
                ToastUtils.showCenterToast(RealNameAuthenticationActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }
    private void initView() {
        int h = CustomApplication.SCREEN_HEIGHT;
        int w = CustomApplication.SCREEN_WIDTH;
        DisplayMetrics dm;//屏幕分辨率容器
        dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int px = DensityUtil.sp2px(getApplicationContext(), 20);
        float fontScale = getApplicationContext().getResources().getDisplayMetrics().scaledDensity;

    }

    private void initDate() {
        //根据不同状态加载不同fragment
        //1 还在认证中
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
        //1.6875倍
        NoCertificationFragment fragment = new NoCertificationFragment();
        //AuthenticatingFragment fragment = new AuthenticatingFragment();
        //  AuthenticationSuccessFragment fragment=new AuthenticationSuccessFragment();.
        // AuthenticationFailureFragment fragment = new AuthenticationFailureFragment();
        fragment.setContainerId(R.id.real_name_authentication_fragment_content);
//        transaction.replace(R.id.real_name_authentication_fragment_content, fragment);
//        transaction.commit();
        switchContent(fragment);
    }

    private void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
