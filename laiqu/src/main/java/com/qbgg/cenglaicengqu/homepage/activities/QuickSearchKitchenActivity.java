package com.qbgg.cenglaicengqu.homepage.activities;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private LinearLayout quick_search_kitchen_style_dish_layout, quick_search_kitchen_style_dish_layout_child1, quick_search_kitchen_style_dish_layout_child2;
    private boolean style_dish_state = false;//默认的是关闭的菜式
    private SHSwipeRefreshLayout quick_search_kitchen_swiperefreshLayout;
    private TextView quick_search_kitchen_hunan_cuisine;
    private RelativeLayout search_dinner_party_hot_lable_layout, search_dinner_party_pattern_layout;
    private TextView hotlable, paternlable, styledish, numberpeople;//热门标签默认是没有的点击的
    private Drawable backdrawable;
    //饭局人数
    private LinearLayout quick_search_kitchen_dinner_party_number_people_layout;

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
        search_dinner_party_pattern_layout = findView(R.id.search_dinner_party_pattern_layout);
        search_dinner_party_hot_lable_layout = findView(R.id.search_dinner_party_hot_lable_layout);
        quick_search_kitchen_swiperefreshLayout = findView(R.id.quick_search_kitchen_swiperefreshLayout);
        quick_search_kitchen_swiperefreshLayout.setLoadmoreEnable(false);
        quick_search_kitchen_swiperefreshLayout.setRefreshEnable(false);
        quick_search_kitchen_style_dish = findView(R.id.quick_search_kitchen_style_dish);
        //菜式
        quick_search_kitchen_style_dish_layout = findView(R.id.quick_search_kitchen_style_dish_layout);
        quick_search_kitchen_style_dish_layout_child1 = findView(R.id.quick_search_kitchen_style_dish_layout_child1);
        quick_search_kitchen_style_dish_layout_child2 = findView(R.id.quick_search_kitchen_style_dish_layout_child2);
        quick_search_kitchen_dinner_party_number_people_layout = findView(R.id.quick_search_kitchen_dinner_party_number_people_layout);
    }

    private void initDate() {
        //默认的关闭的
        quick_search_kitchen_style_dish_layout.setVisibility(View.GONE);
        quick_search_kitchen_style_dish.setCompoundDrawablesWithIntrinsicBounds(null, null,
                ContextCompat.getDrawable(this, R.mipmap.ic_up_arrows), null);
        style_dish_state = false;
        backdrawable = ContextCompat.getDrawable(this, R.drawable.search_dinner_party_label_select);
    }

    private void initListener() {
        quick_search_kitchen_style_dish.setOnClickListener(this);
        for (int i = 0; i < search_dinner_party_hot_lable_layout.getChildCount(); i++) {
            View view = search_dinner_party_hot_lable_layout.getChildAt(i);
            if (view instanceof TextView) {
                TextView hotlabeltextView = (TextView) view;
                hotlabeltextView.setOnClickListener(this);

            }
        }
        for (int i = 0; i < search_dinner_party_pattern_layout.getChildCount(); i++) {
            View view = search_dinner_party_pattern_layout.getChildAt(i);
            view.setOnClickListener(this);

        }

        for (int i = 0; i < quick_search_kitchen_style_dish_layout_child1.getChildCount(); i++) {
            View view = quick_search_kitchen_style_dish_layout_child1.getChildAt(i);
            View view2 = quick_search_kitchen_style_dish_layout_child2.getChildAt(i);
            if (view instanceof TextView) {
                view.setOnClickListener(this);
            }
            if (view2 instanceof TextView) {
                view2.setOnClickListener(this);
            }
        }
        for (int i = 0; i < quick_search_kitchen_dinner_party_number_people_layout.getChildCount(); i++) {
            quick_search_kitchen_dinner_party_number_people_layout.getChildAt(i).setOnClickListener(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public void onClick(View view) {
        ViewGroup footview = (ViewGroup) view.getParent();
        int layoutids = footview.getId();
        switch (layoutids) {
            case R.id.search_dinner_party_hot_lable_layout:
                if (hotlable == null) {
                    if (view instanceof TextView) {
                        TextView hotlabeltextView = (TextView) view;
                        hotlable = hotlabeltextView;
                        hotlable.setSelected(true);

                    }
                } else {
                    if (hotlable.isSelected()) {
                        hotlable.setSelected(false);
                    } else {
                        hotlable.setSelected(true);
                    }
//                    hotlable.setBackground(backdrawable);
                    TextView hotlabeltextView = (TextView) view;
                    hotlable = hotlabeltextView;
                    hotlable.setSelected(true);
                }

                break;
            case R.id.search_dinner_party_pattern_layout:
                if (paternlable == null) {
                    if (view instanceof TextView) {
                        TextView paternlabletextView = (TextView) view;
                        paternlable = paternlabletextView;
                        paternlable.setSelected(true);

                    }
                } else {
                    if (paternlable.isSelected()) {
                        paternlable.setSelected(false);
                    } else {
                        paternlable.setSelected(true);
                    }
//                    hotlable.setBackground(backdrawable);
                    TextView paternlabletextView = (TextView) view;
                    paternlable = paternlabletextView;
                    paternlable.setSelected(true);
                }

                break;
            case R.id.quick_search_kitchen_style_dish_layout_child1:
            case R.id.quick_search_kitchen_style_dish_layout_child2:
                if (styledish == null) {
                    if (view instanceof TextView) {
                        TextView styledishtextView = (TextView) view;
                        styledish = styledishtextView;
                        styledish.setSelected(true);

                    }
                } else {
                    if (styledish.isSelected()) {
                        styledish.setSelected(false);
                    } else {
                        styledish.setSelected(true);
                    }
//                    hotlable.setBackground(backdrawable);
                    TextView styledishtextView = (TextView) view;
                    styledish = styledishtextView;
                    styledish.setSelected(true);
                }

                break;
            case R.id.quick_search_kitchen_dinner_party_number_people_layout:
                if (numberpeople == null) {
                    if (view instanceof TextView) {
                        TextView numberpeopletextView = (TextView) view;
                        numberpeople = numberpeopletextView;
                        numberpeople.setSelected(true);

                    }
                } else {
                    if (numberpeople.isSelected()) {
                        numberpeople.setSelected(false);
                    } else {
                        numberpeople.setSelected(true);
                    }
//                    hotlable.setBackground(backdrawable);
                    TextView numberpeopletextView = (TextView) view;
                    numberpeople = numberpeopletextView;
                    numberpeople.setSelected(true);
                }
                String tag = (String) numberpeople.getText();
                ToastUtils.showCenterToast(this, "点击的是的位置" + tag);
                break;
            default:
                break;

        }

        switch (view.getId()) {

            case R.id.quick_search_kitchen_style_dish:
                SwithStyledishstate();
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

    /**
     * 从当前页面中查找所有的Spinner控件
     *
     * @param group
     * @return
     */
    private TextView findTextView(ViewGroup group) {
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof TextView) {
                    return (TextView) child;
                } else if (child instanceof ViewGroup) {
                    TextView result = findTextView((ViewGroup) child);
                    if (result != null)
                        return result;
                }


            }
        }
        return null;
    }

}
