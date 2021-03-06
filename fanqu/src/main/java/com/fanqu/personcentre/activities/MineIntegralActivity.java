package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.personcentre.adapter.ViewPageIndicatorAdapter;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

/**
 * 我的积分
 */
public class MineIntegralActivity extends BaseActivity {
    private FixedIndicatorView indicator;
    private ViewPager ViewPager;
    private IndicatorViewPager indicatorViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            String FRAGMENTS_TAG = "android:support:fragments";
            // remove saved fragment, will new fragment in mPagerAdapter
            savedInstanceState.remove(FRAGMENTS_TAG);
        }
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        // AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_mine_integral_layout);
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
        indicator = findView(R.id.mine_integral_viewpage_indicator);
        ViewPager = findView(R.id.mine_integral_viewPager);
    }

    private void initDate() {
        ColorBar colorBar = new ColorBar(getApplicationContext(), ContextCompat.getColor(this, R.color.red_bg), 5);
//        colorBar.setWidth();
        indicator.setScrollBar(colorBar);

        //设置指示器颜色
        int selectColor = ContextCompat.getColor(this, R.color.red_bg);
        int unSelectColor = ContextCompat.getColor(this, R.color.textGrayMiddle);
        OnTransitionTextListener listener = new OnTransitionTextListener();
        listener.setColor(selectColor, unSelectColor);
        indicator.setOnTransitionListener(listener);
        ViewPager.setOffscreenPageLimit(1);
        indicatorViewPager = new IndicatorViewPager(indicator, ViewPager);
        ViewPageIndicatorAdapter adapter = new ViewPageIndicatorAdapter(this, getSupportFragmentManager());
        indicatorViewPager.setAdapter(adapter);
    }

    private void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
