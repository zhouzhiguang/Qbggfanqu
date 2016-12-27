package com.qbgg.cenglaicengqu.homepage.activities;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;

//厨房详情页面
public class KitchenDetailsActivity extends BaseActivity implements View.OnClickListener {
    private AppBarLayout kitchen_details_app_bar_layout;
    private ImageView kitchen_details_share_image, kitchen_details_like, kitchen_details_share;
    private ToolBarOptions options;
    private TextView kitchen_details_a_set_meal;
    private TextView kitchen_details_information;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_kitchen_details_layout);
        //AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        AutoUtils.auto(this);
        options = new ToolBarOptions();
        options.titleString = "";
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows_white;
        setToolBar(R.id.toolbar, options);
        Toolbar toolbar = getToolBar();

        toolbar.setNavigationOnClickListener(new NavigationOnClickListener());
//          getSupportActionBar().setDisplayHomeAsUpEnabled(true);
//          getSupportActionBar().setDisplayShowHomeEnabled(true);
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        // kitchen_details_swiperefreshLayout = findView(R.id.kitchen_details_swiperefreshLayout);
        kitchen_details_app_bar_layout = findView(R.id.kitchen_details_app_bar_layout);
        kitchen_details_like = findView(R.id.kitchen_details_like);
        kitchen_details_share = findView(R.id.kitchen_details_share);
        kitchen_details_a_set_meal = findView(R.id.kitchen_details_a_set_meal);
        kitchen_details_information = findView(R.id.kitchen_details_information);

    }

    private void initDate() {
        String meal_a = getResources().getString(R.string.kitchen_set_meal_a);
        kitchen_details_a_set_meal.setText(String.format(meal_a, "30"));

//        kitchen_details_swiperefreshLayout.setRefreshEnable(false);
//        kitchen_details_swiperefreshLayout.setLoadmoreEnable(false);
        kitchen_details_app_bar_layout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state) {
                if (state == State.EXPANDED) {
                    options.navigateId = R.mipmap.ic_left_arrows_white;
                    setToolBar(R.id.toolbar, options);
                    getToolBar().setNavigationOnClickListener(new NavigationOnClickListener());
                    kitchen_details_like.setSelected(false);
                    ToastUtils.showCenterToast(KitchenDetailsActivity.this, "展开状态");
                    //展开状态
                    kitchen_details_like.setImageResource(R.mipmap.ic_like_white);
                    kitchen_details_share.setImageResource(R.mipmap.ic_share_white);
                } else if (state == State.COLLAPSED) {
                    options.navigateId = R.mipmap.ic_left_arrows;
                    setToolBar(R.id.toolbar, options);
                    getToolBar().setNavigationOnClickListener(new NavigationOnClickListener());
                    //折叠状态
                    ToastUtils.showCenterToast(KitchenDetailsActivity.this, "折叠状态状态");
                    kitchen_details_like.setImageResource(R.mipmap.ic_like);
                    kitchen_details_share.setImageResource(R.mipmap.ic_share_black);
                } else {

                    //中间状态
                    ToastUtils.showCenterToast(KitchenDetailsActivity.this, "中间状态");
                }
            }
        });
    }

    private void initListener() {
        //
        kitchen_details_information.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.kitchen_details_information:
                //咨询聊天界面了

                break;
            default:
                break;

        }
    }


    private class NavigationOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            ToastUtils.showCenterToast(KitchenDetailsActivity.this, "点击返回了哦");
        }
    }

    public abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {


        private State mCurrentState = State.IDLE;

        @Override
        public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            if (i == 0) {
                if (mCurrentState != State.EXPANDED) {
                    onStateChanged(appBarLayout, State.EXPANDED);
                }
                mCurrentState = State.EXPANDED;
            } else if (Math.abs(i) >= appBarLayout.getTotalScrollRange()) {
                if (mCurrentState != State.COLLAPSED) {
                    onStateChanged(appBarLayout, State.COLLAPSED);
                }
                mCurrentState = State.COLLAPSED;
            } else {
                if (mCurrentState != State.IDLE) {
                    onStateChanged(appBarLayout, State.IDLE);
                }
                mCurrentState = State.IDLE;
            }
        }

        public abstract void onStateChanged(AppBarLayout appBarLayout, State state);
    }

    public enum State {
        EXPANDED,
        COLLAPSED,
        IDLE
    }
}
