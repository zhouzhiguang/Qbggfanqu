package com.qbgg.cenglaicengqu.homepage.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;

/**
 * 快捷查找厨房
 */
public class QuickSearchKitchenActivity extends BaseActivity implements View.OnClickListener {
    private TextView quick_search_kitchen_style_dish;
    private LinearLayout quick_search_kitchen_style_dish_layout;
    private boolean style_dish_state = false;//默认的是关闭的菜式
    private TextView quick_search_kitchen_ten;
    private SHSwipeRefreshLayout quick_search_kitchen_swiperefreshLayout;
    private TextView quick_search_kitchen_hunan_cuisine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_quick_search_kitchen_layout);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
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
                ToastUtils.showCenterToast(QuickSearchKitchenActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        quick_search_kitchen_hunan_cuisine=findView(R.id.quick_search_kitchen_hunan_cuisine);
       quick_search_kitchen_hunan_cuisine.setSelected(true);
        quick_search_kitchen_swiperefreshLayout = findView(R.id.quick_search_kitchen_swiperefreshLayout);
        quick_search_kitchen_swiperefreshLayout.setLoadmoreEnable(false);
        quick_search_kitchen_swiperefreshLayout.setRefreshEnable(false);
        quick_search_kitchen_ten = findView(R.id.quick_search_kitchen_ten);
        quick_search_kitchen_style_dish = findView(R.id.quick_search_kitchen_style_dish);
        quick_search_kitchen_style_dish_layout = findView(R.id.quick_search_kitchen_style_dish_layout);
    }

    private void initDate() {
        //默认的关闭的
        quick_search_kitchen_style_dish_layout.setVisibility(View.GONE);
        quick_search_kitchen_style_dish.setCompoundDrawablesWithIntrinsicBounds(null, null,
                ContextCompat.getDrawable(this, R.mipmap.ic_up_arrows), null);
        style_dish_state = false;
    }

    private void initListener() {
        quick_search_kitchen_style_dish.setOnClickListener(this);
        quick_search_kitchen_ten.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.quick_search_kitchen_style_dish:
                SwithStyledishstate();
                break;
            case R.id.quick_search_kitchen_ten:
                ToastUtils.showCenterToast(this, Integer.parseInt((String) quick_search_kitchen_ten.getTag()) + "--");

                break;
            default:
                break;
        }
    }

    /**
     * 点击菜式打开关闭下面的布局
     */
    private void SwithStyledishstate() {
        if (style_dish_state) {
            //关闭
            quick_search_kitchen_style_dish_layout.setVisibility(View.GONE);
            quick_search_kitchen_style_dish_layout.setAnimation(AnimationUtils.loadAnimation(this, R.anim.style_dish_close_layout));
            quick_search_kitchen_style_dish.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    ContextCompat.getDrawable(this, R.mipmap.ic_up_arrows), null);
            style_dish_state = false;
        } else {
            //打开 箭头往下
            quick_search_kitchen_style_dish_layout.setVisibility(View.VISIBLE);
            quick_search_kitchen_style_dish_layout.setAnimation(AnimationUtils.loadAnimation(this, R.anim.style_dish_open_layout));

            //quick_search_kitchen_style_dish.setAnimation(AnimationUtils.loadAnimation(this, R.anim.style_dish_open));
            quick_search_kitchen_style_dish.setCompoundDrawablesWithIntrinsicBounds(null, null,
                    ContextCompat.getDrawable(this, R.mipmap.ic_down_arrows), null);
            style_dish_state = true;
        }
    }
}
