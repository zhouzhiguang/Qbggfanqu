package com.fanqu.dinner.activity;

import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.dinner.adapter.CommentAdapter;
import com.fanqu.dinner.adapter.DishesListAdapter;
import com.fanqu.dinner.dialog.SelectSetMealDialog;
import com.fanqu.dinner.listener.AppBarStateChangeListener;
import com.fanqu.dinner.listener.State;
import com.fanqu.dinner.modle.CommentEntity;
import com.fanqu.dinner.modle.DishesEntity;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.main.widget.StarView;

import java.util.ArrayList;
import java.util.List;


/**
 * 饭局详情1.3版本
 */
public class DinnerPartyDetailActivity extends BaseActivity implements View.OnClickListener {

    private AppBarLayout kitchen_details_app_bar_layout;
    private ImageView kitchen_details_share_image, kitchen_details_like, kitchen_details_share;
    private ToolBarOptions options;
    private TextView kitchen_details_a_set_meal;
    private TextView kitchen_details_information;
    private CollapsingToolbarLayout kitchen_details_collapsing;
    private TextView set_meal_how_much_a, set_meal_how_much_b, set_meal_how_much_c;
    private StarView starView;
    private RelativeLayout a_set_meal_container;
    private RecyclerView a_set_meal_recyclerview, b_set_meal_recyclerview, c_set_meal_recyclerview;//abc 三个套餐的recyclerview
    private List<DishesEntity> menu_a, menu_b, menu_c;
    private LinearLayout a_set_meal_layout, b_set_meal_layout, c_set_meal_layout;
    private TextView examine_more_comment;//查看更多饭局
    private RecyclerView comment_recyclerview;//评论的recycler
    private TextView message;
    private LinearLayout kitchen_introduce_layout, immediately_reserve;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.transparent);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_dinner_party_detail_layout);
        //StatusBarUtil.setTranslucent(DinnerPartyDetailActivity.this);
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
        String how_much_1 = getResources().getString(R.string.set_meal_how_much);
        set_meal_how_much_a.setText(String.format(how_much_1, "20"));

        menu_a = new ArrayList<>();
        String[] a_set_meal_dishes = new String[]{
                "红烧猪蹄", "剁椒鱼头", "义乌包子"};

        for (int i = 0; i < a_set_meal_dishes.length; i++) {
            DishesEntity entity = new DishesEntity();
            entity.setDish_name(a_set_meal_dishes[i]);
            if (i % a_set_meal_dishes.length == 0) {
                entity.setIsspecialty(true);
            }
            menu_a.add(entity);

        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        DishesListAdapter adapter = new DishesListAdapter(this, R.layout.memu_a_list_item_layout, menu_a);
        a_set_meal_recyclerview.setLayoutManager(manager);
        a_set_meal_recyclerview.setAdapter(adapter);
        b_set_meal_layout.setVisibility(View.GONE);
        c_set_meal_layout.setVisibility(View.GONE);

        List<CommentEntity> datas = new ArrayList<>();
        datas.add(new CommentEntity());
        datas.add(new CommentEntity());
        datas.add(new CommentEntity());
        CommentAdapter commentAdapter = new CommentAdapter(this, R.layout.comment_list_item_layout, datas);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        comment_recyclerview.setLayoutManager(manager);
        comment_recyclerview.setAdapter(commentAdapter);
    }

    private void initView() {
        //立即预定
        immediately_reserve = findView(R.id.immediately_reserve);
        //厨神主页
        kitchen_introduce_layout = findView(R.id.kitchen_introduce_layout);
        comment_recyclerview = findView(R.id.comment_recyclerview);
        //查看跟多评论
        examine_more_comment = findView(R.id.examine_more_comment);
        c_set_meal_recyclerview = findView(R.id.c_set_meal_recyclerview);
        set_meal_how_much_c = findView(R.id.set_meal_how_much_c);
        b_set_meal_recyclerview = findView(R.id.a_set_meal_recyclerview);
        set_meal_how_much_b = findView(R.id.set_meal_how_much_b);
        a_set_meal_recyclerview = findView(R.id.a_set_meal_recyclerview);
        set_meal_how_much_a = findView(R.id.set_meal_how_much_a);
        a_set_meal_layout = findView(R.id.a_set_meal_layout);
        b_set_meal_layout = findView(R.id.b_set_meal_layout);
        c_set_meal_layout = findView(R.id.c_set_meal_layout);
        starView = findView(R.id.starview);
        starView.setMark(4.5f);
        starView.setEnabled(false);
        kitchen_details_collapsing = findView(R.id.kitchen_details_collapsing);
        kitchen_details_app_bar_layout = findView(R.id.kitchen_details_app_bar_layout);
        kitchen_details_like = findView(R.id.kitchen_details_like);
        kitchen_details_share = findView(R.id.kitchen_details_share);
        kitchen_details_a_set_meal = findView(R.id.kitchen_details_a_set_meal);
        message = findView(R.id.message);
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
                    ThemUtils.initthem(DinnerPartyDetailActivity.this, R.color.transparent);
                    // StatusBarUtil.setTranslucent(DinnerPartyDetailActivity.this, StatusBarUtil.DEFAULT_STATUS_BAR_ALPHA);
                    //StatusBarUtil.setColor(DinnerPartyDetailActivity.this, ContextCompat.getColor(DinnerPartyDetailActivity.this, R.color.black));
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
                    //StatusBarUtil.setColor(DinnerPartyDetailActivity.this, ContextCompat.getColor(DinnerPartyDetailActivity.this, R.color.black));
                    if (getSupportActionBar() != null) {
                        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
                    }
                    ThemUtils.initthem(DinnerPartyDetailActivity.this, R.color.black);
                } else {

                    //中间状态
                    // ToastUtils.showCenterToast(DinnerPartyDetailActivity.this, "中间状态");
                    //StatusBarUtil.setColor(DinnerPartyDetailActivity.this, Color.BLACK);
                }
            }
        });

        examine_more_comment.setOnClickListener(this);
        kitchen_introduce_layout.setOnClickListener(this);
        immediately_reserve.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {

        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.examine_more_comment:
                //查看跟多评论
                jumpActivity(MoreCommentActivity.class);
                break;
            case R.id.kitchen_introduce_layout:
                jumpActivity(KitchenhomepageActivity.class);
                break;
            case R.id.immediately_reserve:
                //跳转到支付dialog
//                jumpActivity(KitchenhomepageActivity.class);
                SelectSetMealDialog dialog = new SelectSetMealDialog(DinnerPartyDetailActivity.this, R.style.select_setmeal_dialog);
                if (!dialog.isShowing()) {
                    dialog.show();
                }
                break;
            default:
                break;
        }
    }

    private class NavigationOnClickListener implements View.OnClickListener {

        @Override
        public void onClick(View view) {
            ToastUtils.showCenterToast(DinnerPartyDetailActivity.this, "点击返回了哦");
        }
    }
}
