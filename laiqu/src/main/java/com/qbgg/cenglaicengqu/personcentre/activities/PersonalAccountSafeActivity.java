package com.qbgg.cenglaicengqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;

public class PersonalAccountSafeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
         AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_personal_account_safe_layout);
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
                ToastUtils.showCenterToast(PersonalAccountSafeActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {

    }

    private void initDate() {

    }

    private void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
