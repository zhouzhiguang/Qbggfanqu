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
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.personcentre.adapter.CouponViewPageIndicatorAdapter;
import com.fanqu.personcentre.model.CouponlDetailEntity;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券界面
 */
public class MineCouponActivity extends BaseActivity {
    private FixedIndicatorView indicator;
    private ViewPager viewPager;
    private IndicatorViewPager indicatorViewPager;
    private List<CouponlDetailEntity> dates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        if (savedInstanceState != null) {
            String FRAGMENTS_TAG = "android:support:fragments";
            // remove saved fragment, will new fragment in mPagerAdapter
            savedInstanceState.remove(FRAGMENTS_TAG);
        }
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_mine_coupon_layout);
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
                ToastUtils.showCenterToast(MineCouponActivity.this, "点击返回了哦");
                // finish();
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        indicator = findView(R.id.mine_coupon_viewpage_indicator);
        viewPager = findView(R.id.mine_coupon_viewPager);

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
        viewPager.setOffscreenPageLimit(3);
        indicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        dates = getTestDate();
        //我的优惠卷适配器
        CouponViewPageIndicatorAdapter adapter = new CouponViewPageIndicatorAdapter(this, getSupportFragmentManager(), dates);

        indicatorViewPager.setAdapter(adapter);

    }

    private void initListener() {
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    public List<CouponlDetailEntity> getTestDate() {
        List<CouponlDetailEntity> list = new ArrayList<CouponlDetailEntity>();

        for (int i = 0; i < 15; i++) {
            CouponlDetailEntity entity = new CouponlDetailEntity();
            if (i % 3 == 0) {
                entity.setCouponids(String.valueOf(541651465 + i));
                entity.setCoupontitle("全品类（特价商品除外）");
                entity.setCouponstate(0);
                entity.setGetcoupondate("2017.01-10");
                entity.setOverduedate("2017.03-10");
                //优惠券状态  1未使用0已经使用-1已经过期
                entity.setCouponcondition(0);
                entity.setCouponmoney("10");
            } else if (i % 3 == 1) {
                entity.setCouponids(String.valueOf(541651465 + i));
                entity.setCoupontitle("全品类（特价商品除外）");
                entity.setCouponstate(1);
                entity.setGetcoupondate("2017.01-10");
                entity.setOverduedate("2017.03-10");
                //满一百元可用
                entity.setCouponcondition(getRandomInt(100, 800));
                entity.setCouponmoney("10");
            } else {
                entity.setCouponids(String.valueOf(541651465 + i));
                entity.setCoupontitle("全品类（特价商品除外）");
                entity.setCouponstate(-1);
                entity.setGetcoupondate("2017.01-10");
                entity.setOverduedate("2017.03-10");
                //满一百元可用
                entity.setCouponcondition(getRandomInt(100, 800));
                entity.setCouponmoney("10");
            }
            list.add(entity);
        }
        return list;
    }

    // 返回a到b之間(包括a,b)的任意一個自然数,如果a > b || a < 0，返回-1
    public int getRandomInt(int a, int b) {
        if (a > b || a < 0)
            return -1;
        // 下面两种形式等价
        // return a + (int) (new Random().nextDouble() * (b - a + 1));
        return a + (int) (Math.random() * (b - a + 1));
    }
}
