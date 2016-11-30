package com.qbgg.cenglaicengqu.addconference.activitiies;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.netease.nim.uikit.common.ui.imageview.CircleImageView;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.addconference.adapter.DinnerDetailsAlreadysignedAdapter;
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
public class DinnerPartyDetails extends BaseActivity {
    private PileLayout dinner_party_details_pile_layout;
    private SHSwipeRefreshLayout dinner_party_details_swiperefreshLayout;
    private RecyclerView dinner_party_details_has_signed_up_recylerview, dinner_party_details_eave_words_recylerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_dinner_party_details_layout);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        AutoUtils.auto(this);
        initView();
        innitDate();
    }


    /**
     * 初始化view
     */
    private void initView() {
        dinner_party_details_pile_layout = findView(R.id.dinner_party_details_pile_layout);
        dinner_party_details_swiperefreshLayout = findView(R.id.dinner_party_details_swiperefreshLayout);
        dinner_party_details_has_signed_up_recylerview = findView(R.id.dinner_party_details_has_signed_up_recylerview);
        dinner_party_details_eave_words_recylerview = findView(R.id.dinner_party_details_eave_words_recylerview);
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


    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
