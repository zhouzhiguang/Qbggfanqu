package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.model.ToolBarOptions;

/**
 * 我的余额说明
 */
public class MineBalanceExplainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_mine_balance_explain_layout);
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
                finish();
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
