package com.fanqu.dinner.activity;

import android.os.Bundle;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.model.ToolBarOptions;

public class AvailableDiscountCouponActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_available_discount_coupon_layout);
        //StatusBarUtil.setTranslucent(DinnerPartyDetailActivity.this);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.titleString = "";
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        setToolBar(R.id.toolbar, options);
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
