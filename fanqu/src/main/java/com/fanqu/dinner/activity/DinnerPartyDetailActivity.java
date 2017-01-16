package com.fanqu.dinner.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.dinner.listener.AppBarStateChangeListener;
import com.fanqu.dinner.listener.State;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.main.widget.StarView;


/**
 * 饭局详情1.3版本
 */
public class DinnerPartyDetailActivity extends BaseActivity {

    private AppBarLayout kitchen_details_app_bar_layout;
    private ImageView kitchen_details_share_image, kitchen_details_like, kitchen_details_share;
    private ToolBarOptions options;
    private TextView kitchen_details_a_set_meal;
    private TextView kitchen_details_information;
    private CollapsingToolbarLayout kitchen_details_collapsing;

    private StarView starView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.transparent);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_dinner_party_detail_layout);
        // StatusBarUtil.setColor(DinnerPartyDetailActivity.this, Color.BLACK);
        AutoUtils.auto(this);
        options = new ToolBarOptions();
        options.titleString = "";
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows_white;
        setToolBar(R.id.toolbar, options);
        Toolbar toolbar = getToolBar();

        toolbar.setNavigationOnClickListener(new NavigationOnClickListener());
        initView();
        initDate();
        initListener();
    }

    private void initDate() {
    }

    private void initView() {
        starView = findView(R.id.starview);
        starView.setMark(4.5f);
        starView.setEnabled(false);
        kitchen_details_collapsing = findView(R.id.kitchen_details_collapsing);
        kitchen_details_app_bar_layout = findView(R.id.kitchen_details_app_bar_layout);
        kitchen_details_like = findView(R.id.kitchen_details_like);
        kitchen_details_share = findView(R.id.kitchen_details_share);
        kitchen_details_a_set_meal = findView(R.id.kitchen_details_a_set_meal);
        kitchen_details_information = findView(R.id.kitchen_details_information);
    }


    private void initListener() {
        kitchen_details_app_bar_layout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {

                if (state == State.EXPANDED) {

                    options.navigateId = R.mipmap.ic_left_arrows_white;
                    setToolBar(R.id.toolbar, options);
                    getToolBar().setNavigationOnClickListener(new NavigationOnClickListener());
                    kitchen_details_like.setSelected(false);

                    //展开状态
                    kitchen_details_like.setImageResource(R.mipmap.ic_like_white);
                    kitchen_details_share.setImageResource(R.mipmap.ic_share_white);
                    //StatusBarUtil.setColor(DinnerPartyDetailActivity.this, Color.BLACK);
//                    ThemUtils.initthem(this, R.color.white);
                    // StatusBarUtil.setTranslucent(DinnerPartyDetailActivity.this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);

                    ToastUtils.showCenterToast(DinnerPartyDetailActivity.this, "展开状态");
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    }
                } else if (state == State.COLLAPSED) {
                    options.navigateId = R.mipmap.ic_left_arrows;
                    setToolBar(R.id.toolbar, options);
                    getToolBar().setNavigationOnClickListener(new NavigationOnClickListener());

                    // StatusBarUtil.setTranslucent(DinnerPartyDetailActivity.this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
                    //StatusBarUtil.setTranslucentForImageView(DinnerPartyDetailActivity.this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA, kitchen_details_collapsing);
                    ToastUtils.showCenterToast(DinnerPartyDetailActivity.this, "折叠状态状态");
                    kitchen_details_like.setImageResource(R.mipmap.ic_like);
                    kitchen_details_share.setImageResource(R.mipmap.ic_share_black);
                    //折叠状态
                    //StatusBarUtil.setTranslucent(DinnerPartyDetailActivity.this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    }
                } else {

                    //中间状态
                    // ToastUtils.showCenterToast(DinnerPartyDetailActivity.this, "中间状态");
                    //StatusBarUtil.setColor(DinnerPartyDetailActivity.this, Color.BLACK);
                }
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    private class NavigationOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            ToastUtils.showCenterToast(DinnerPartyDetailActivity.this, "点击返回了哦");
        }
    }
}
