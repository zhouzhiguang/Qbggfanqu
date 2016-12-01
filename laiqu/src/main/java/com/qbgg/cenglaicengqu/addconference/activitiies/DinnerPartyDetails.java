package com.qbgg.cenglaicengqu.addconference.activitiies;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.netease.nim.uikit.common.ui.imageview.CircleImageView;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.addconference.adapter.DinnerDetailsAlreadysignedAdapter;
import com.qbgg.cenglaicengqu.addconference.adapter.DinnerDetailsEaveWordsAdapter;
import com.qbgg.cenglaicengqu.addconference.modle.LeaveWordBean;
import com.qbgg.cenglaicengqu.addconference.modle.Personnelsigned;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.widget.PileLayout;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 饭局详情页面
 */
public class DinnerPartyDetails extends BaseActivity implements View.OnClickListener {
    private PileLayout dinner_party_details_pile_layout;
    private SHSwipeRefreshLayout dinner_party_details_swiperefreshLayout;
    private RecyclerView dinner_party_details_has_signed_up_recylerview, dinner_party_details_eave_words_recylerview;
    private TextView dinner_party_details_eave_words_add_more;
    private List<LeaveWordBean> beans;
    private DinnerDetailsEaveWordsAdapter eaveWordsAdapter;
    //顶部布局
    private RelativeLayout dinnerPartyDetailsReleaseSuccessHeadLayout;
    private TextView dinnerPartyDetailsReturn;//返回
    private ImageView dinnerPartyDetailsShare;//顶部分享按钮
    private CircleImageView dinnerPartyDetailsHeadimage;//头像
    private TextView dinnerPartyDetailsAccountsNickname;//昵称
    private TextView dinnerPartyDetailsIndividualitySignature;//个性签名
    private ImageView dinnerPartyDetailsMore;//后面更多
    private TextView dinnerPartyDetailsContent;//饭局详情页面
    private LinearLayout dinnerPartyDetailsImageLayout;//饭局图片介绍主布局也可难没有图片
    private ImageView dinnerPartyDetailsImage1;//饭局介绍图片1
    private ImageView dinnerPartyDetailsImage2;////饭局介绍图片2
    private ImageView dinnerPartyDetailsImage3;//饭局介绍图片3
    private TextView dinnerPartyDetailsNumberOfPeople;//饭局名额
    private TextView openSeatsTime;//饭局时间
    private TextView dinnerPartyDetailsLocation;//饭局地点
    private TextView dinnerPartyDetailsDislike, dinner_party_details_like;//不喜欢 和喜欢
    private TextView dinnerPartyDetailsLikeNumber;//饭局喜欢人数
    private TextView dinnerPartyDetailsHasSignedUp;//已经报名人数
    private TextView dinnerPartyDetailsEaveWords;//留意条数
    private TextView dinnerPartyDetailsEaveWordsAddMore;//点击加载跟多评论
    private EditText dinnerPartyDetailsEditEaveWords;//发表的话
    private TextView dinnerPartyDetailsReleaseEditEaveWords;//发表
    private TextView innerPartyDetailsSignUp;//我要报名


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_dinner_party_details_layout);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        AutoUtils.auto(this);
        initView();
        innitDate();
        initListener();
    }


    /**
     * 初始化view
     */
    private void initView() {
        dinnerPartyDetailsReleaseSuccessHeadLayout = findView(R.id.dinner_party_details_release_success_head_layout);
        dinnerPartyDetailsReturn = findView(R.id.dinner_party_details_return);
        dinnerPartyDetailsShare = findView(R.id.dinner_party_details_share);
        dinnerPartyDetailsHeadimage = findView(R.id.dinner_party_details_headimage);
        dinnerPartyDetailsAccountsNickname = findView(R.id.dinner_party_details_accounts_nickname);
        dinnerPartyDetailsIndividualitySignature = findView(R.id.dinner_party_details_individuality_signature);
        dinnerPartyDetailsMore = findView(R.id.dinner_party_details_more);
        dinnerPartyDetailsContent = findView(R.id.dinner_party_details_content);
        dinnerPartyDetailsImageLayout = findView(R.id.dinner_party_details_image_layout);
        dinnerPartyDetailsImage1 = findView(R.id.dinner_party_details_image_1);
        dinnerPartyDetailsImage2 = findView(R.id.dinner_party_details_image_2);
        dinnerPartyDetailsImage3 = findView(R.id.dinner_party_details_image_3);
        dinnerPartyDetailsNumberOfPeople = findView(R.id.dinner_party_details_number_of_people);
        openSeatsTime = findView(R.id.dinner_party_details_open_seats_time_);
        dinnerPartyDetailsLocation = findView(R.id.dinner_party_details_location);
        dinnerPartyDetailsDislike = findView(R.id.dinner_party_details_dislike);
        dinner_party_details_like = findView(R.id.dinner_party_details_like);
        dinnerPartyDetailsLikeNumber = findView(R.id.dinner_party_details_like_number);

        dinnerPartyDetailsHasSignedUp = findView(R.id.dinner_party_details_has_signed_up);
        dinnerPartyDetailsEaveWords = findView(R.id.dinner_party_details_eave_words);
        dinnerPartyDetailsEaveWordsAddMore = findView(R.id.dinner_party_details_eave_words_add_more);
        dinnerPartyDetailsEditEaveWords = findView(R.id.dinner_party_details_edit_eave_words);
        dinnerPartyDetailsReleaseEditEaveWords = findView(R.id.dinner_party_details_release_edit_eave_words);
        innerPartyDetailsSignUp = findView(R.id.dinner_party_details_sign_up);
        dinner_party_details_pile_layout = findView(R.id.dinner_party_details_pile_layout);
        dinner_party_details_swiperefreshLayout = findView(R.id.dinner_party_details_swiperefreshLayout);
        dinner_party_details_has_signed_up_recylerview = findView(R.id.dinner_party_details_has_signed_up_recylerview);
        dinner_party_details_eave_words_recylerview = findView(R.id.dinner_party_details_eave_words_recylerview);
        dinner_party_details_eave_words_add_more = findView(R.id.dinner_party_details_eave_words_add_more);
        dinner_party_details_swiperefreshLayout.setLoadmoreEnable(false);
        dinner_party_details_swiperefreshLayout.setRefreshEnable(false);

    }

    /**
     * 初始化数据
     */
    private void innitDate() {
        CircleImageView circleimageview;
        int size = (int) getResources().getDimension(R.dimen.dimen_100px);
        int padding = (int) getResources().getDimension(R.dimen.dimen_23px);
        ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(size, size);
        for (int i = 0; i < 9; i++) {
            circleimageview = new CircleImageView(this);
            params.setMargins(padding, 0, padding, 0);
            circleimageview.setLayoutParams(params);
            circleimageview.setImageResource(R.mipmap.ic_theme_activity);
            dinner_party_details_pile_layout.addView(circleimageview);
        }
        List<Personnelsigned> dates = new ArrayList<Personnelsigned>();
        for (int j = 0; j < 6; j++) {
            Personnelsigned personnelsined = new Personnelsigned();
            personnelsined.setNickname("你大爷的");
            dates.add(personnelsined);
        }
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        DinnerDetailsAlreadysignedAdapter adapter = new DinnerDetailsAlreadysignedAdapter(DinnerPartyDetails.this, R.layout.dinner_party_details_has_signed_up_recylerview_item_layout, dates);
        dinner_party_details_has_signed_up_recylerview.setLayoutManager(manager);
        dinner_party_details_has_signed_up_recylerview.setAdapter(adapter);

        beans = new ArrayList<LeaveWordBean>();
        for (int k = 0; k < 5; k++) {
            LeaveWordBean bean = new LeaveWordBean();
            beans.add(bean);
        }
        eaveWordsAdapter = new DinnerDetailsEaveWordsAdapter(this, R.layout.dinner_party_details_eave_words_recylerview_item, beans);
        manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        dinner_party_details_eave_words_recylerview.setLayoutManager(manager);
        dinner_party_details_eave_words_recylerview.setAdapter(eaveWordsAdapter);


    }

    /**
     * 点击事件
     */
    private void initListener() {
        dinner_party_details_eave_words_add_more.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.dinner_party_details_eave_words_add_more:
                beans.add(new LeaveWordBean());
                beans.add(new LeaveWordBean());
                beans.add(new LeaveWordBean());
                beans.add(new LeaveWordBean());
                beans.add(new LeaveWordBean());
                eaveWordsAdapter.notifyDataSetChanged();
            default:
                break;
        }
    }
}
