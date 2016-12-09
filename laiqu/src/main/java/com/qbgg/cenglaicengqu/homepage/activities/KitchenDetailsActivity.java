package com.qbgg.cenglaicengqu.homepage.activities;

import android.os.Bundle;
import android.view.View;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;

//厨房详情页面
public class KitchenDetailsActivity extends BaseActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_kitchen_details_layout);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        AutoUtils.auto(this);
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

    @Override
    public void onClick(View view) {
        switch (view.getId()){

        }
    }
}
