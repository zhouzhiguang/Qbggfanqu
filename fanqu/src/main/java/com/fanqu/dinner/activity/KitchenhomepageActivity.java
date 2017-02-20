package com.fanqu.dinner.activity;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.dinner.adapter.CommentAdapter;
import com.fanqu.dinner.modle.CommentEntity;
import com.fanqu.framework.CustomApplication;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ViewHolder;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.main.widget.StarView;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

import static com.fanqu.R.id.comment_recyclerview;

/**
 * 厨神端主页
 */
public class KitchenhomepageActivity extends BaseActivity {
    private TextView real_name_authentication_text_sign;
    private SHSwipeRefreshLayout shswiperefreshLayout;
    private RecyclerView recyclerview;
    private List<CommentEntity> datas;
    private LayoutInflater inflater;
    private LinearLayout footview_main_layout;
    private ImageView share;
    private ImageView informationBackground;//顶部背景图片
    private CircleImageView headImage;//厨神头像
    private ImageView identitySign;//会员标识身份
    private TextView niceName;//厨神昵称
    private StarView starview;//厨神综合得分
    private TextView compositeScore;//得多少分
    private TextView eatenTime;//吃过次数
    private TextView fansNumberPeople;//粉丝数量
    private TextView realNameAuthenticationTextSign;//实名认证状态标识
    private TextView authenticatedExplainText;//实名认证状态标识文字说明
    private TextView alipayAuthenticationTextSign;//阿里支付认证
    private TextView alipayAuthenticatedExplainText;//h支付宝支付认证文字
    private TextView superFoodieAuthenticationTextSign;//超级吃货认证标识
    private TextView superFoodieAuthenticatedExplainText;//超级吃货认证文字购买了吗
    private TextView evaluate;//他的评价
    private TextView evaluateScoreText;//厨神评分
    private StarView evaluateScore;//评分星星
    private TextView reasonableCollocation;//合理搭配
    private TextView serviceEnthusiasm;//服务热情
    private TextView foodMaterialFresh;//食材新鲜
    private TextView reasonableCollocation1;//合理搭配
    private TextView servingSlow;//上菜慢
    private RecyclerView commentRecyclerview;
    private TextView like;
    private TextView message;
    private LinearLayout immediatelyReserve;

    private void assignViews() {


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_kitchen_homepage_layout);
        //StatusBarUtil.setTranslucent(DinnerPartyDetailActivity.this);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.titleString = "";
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        setToolBar(R.id.toolbar, options);
        inflater = getLayoutInflater();
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        recyclerview = findView(comment_recyclerview);
        shswiperefreshLayout = findView(R.id.swiperefreshLayout);
        shswiperefreshLayout.setLoadmoreEnable(false);
        shswiperefreshLayout.setRefreshEnable(false);
        real_name_authentication_text_sign = findView(R.id.real_name_authentication_text_sign);
        share = findView(R.id.share);

        informationBackground = findView(R.id.information_background);
        headImage = findView(R.id.head_image);
        identitySign = findView(R.id.identity_sign);
        niceName = findView(R.id.nice_name);
        starview = findView(R.id.starview);
        compositeScore = findView(R.id.composite_score);
        eatenTime = findView(R.id.eaten_time);
        fansNumberPeople = findView(R.id.fans_number_people);
        realNameAuthenticationTextSign = findView(R.id.real_name_authentication_text_sign);
        authenticatedExplainText = findView(R.id.authenticated_explain_text);
        alipayAuthenticationTextSign = findView(R.id.alipay_authentication_text_sign);
        alipayAuthenticatedExplainText = findView(R.id.alipay_authenticated_explain_text);
        superFoodieAuthenticationTextSign = findView(R.id.super_foodie_authentication_text_sign);
        superFoodieAuthenticatedExplainText = findView(R.id.super_foodie_authenticated_explain_text);
        evaluate = findView(R.id.evaluate);
        evaluateScoreText = findView(R.id.evaluate_score_text);
        evaluateScore = findView(R.id.evaluate_score);
        reasonableCollocation = findView(R.id.reasonable_collocation);
        serviceEnthusiasm = findView(R.id.service_enthusiasm);
        foodMaterialFresh = findView(R.id.food_material_fresh);
        reasonableCollocation1 = findView(R.id.reasonable_collocation1);
        servingSlow = findView(R.id.serving_slow);
        like = findView(R.id.like);
        message = findView(R.id.message);


    }

    private void initDate() {
        starview.setMark(4.5f);
        String evaluates = getString(R.string.he_evaluate);
        String format = String.format(evaluates, "50");
        evaluate.setText(format);
        String s = getString(R.string.reasonable_collocation);
        reasonableCollocation.setText(String.format(s, "10"));
        //认证 状态
        //Drawable drawable= ContextCompat.getDrawable(KitchenhomepageActivity.this,R.drawable.unautherized_state_shape);
        // real_name_authentication_text_sign.setBackgroundResource(R.drawable.authenticated_state_shape);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerview.setLayoutManager(manager);
        datas = new ArrayList<>();
        datas.add(new CommentEntity());
        datas.add(new CommentEntity());
        CommentAdapter adapter = new CommentAdapter(this, R.layout.comment_list_item_layout, datas);
        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        View layout = inflater.inflate(R.layout.more_comment_recylerview_footview_layout, null);
        footview_main_layout = ViewHolder.get(layout, R.id.footview_main_layout);
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) footview_main_layout.getLayoutParams();
        params.width = CustomApplication.SCREEN_WIDTH;
        params.height = (int) getResources().getDimension(R.dimen.dimen_130px);
        footview_main_layout.setLayoutParams(params);
        AutoUtils.auto(layout);
        mHeaderAndFooterWrapper.addFootView(layout);
        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
        recyclerview.setAdapter(mLoadMoreWrapper);
    }

    private void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
