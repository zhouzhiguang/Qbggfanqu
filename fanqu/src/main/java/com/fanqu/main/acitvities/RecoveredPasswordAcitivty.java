package com.fanqu.main.acitvities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.model.ToolBarOptions;

/**
 * 找回密码
 * activity_recovered_password_layout
 */
public class RecoveredPasswordAcitivty extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //  AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_recovered_password_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        Toolbar toolbar = getToolBar();

       
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
