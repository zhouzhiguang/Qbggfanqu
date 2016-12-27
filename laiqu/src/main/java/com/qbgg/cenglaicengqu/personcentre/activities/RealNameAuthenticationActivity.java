package com.qbgg.cenglaicengqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.View;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.LaiquApplication;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.DensityUtil;
import com.qbgg.cenglaicengqu.main.util.LogUtil;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;
import com.qbgg.cenglaicengqu.personcentre.frgment.NoCertificationFragment;

/**
 * 1.6875倍
 * 实名制认证页面
 */
public class RealNameAuthenticationActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoUtils.setSize(this, true, 1080, 1812);// 没有状态栏,设计尺寸的宽高
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
        int h = LaiquApplication.SCREEN_HEIGHT;
        int w = LaiquApplication.SCREEN_WIDTH;
        DisplayMetrics dm;//屏幕分辨率容器
        dm = new DisplayMetrics();
        this.getWindowManager().getDefaultDisplay().getMetrics(dm);
        int width = dm.widthPixels;
        int height = dm.heightPixels;
        int px = DensityUtil.sp2px(getApplicationContext(), 20);
        float fontScale = getApplicationContext().getResources().getDisplayMetrics().scaledDensity;
        LogUtil.e("测试", width + "像素");
        LogUtil.e("测试", height + "像素");
        LogUtil.e("转换后的输出", fontScale + "--倍数------------");
        LogUtil.e("测试", DensityUtil.px2dip(getApplicationContext(), width) + "360宽dpdp");

        LogUtil.e("测试高", DensityUtil.px2dip(getApplicationContext(), height) + "高");
    }

    private void initDate() {
        //根据不同状态加载不同fragment
        //1 还在认证中
//        FragmentManager manager = getSupportFragmentManager();
//        FragmentTransaction transaction = manager.beginTransaction();
        //1.6875倍
        NoCertificationFragment fragment = new NoCertificationFragment();
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
