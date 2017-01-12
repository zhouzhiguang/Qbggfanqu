package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanqu.R;
import com.fanqu.framework.Constants;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.personcentre.model.CouponlDetailEntity;
import com.scu.miomin.shswiperefresh.core.SHSwipeRefreshLayout;

/**
 * 优惠券 详情页面
 */
public class CouponlDetailActivity extends BaseActivity {

    private SHSwipeRefreshLayout shswipeRefreshLayout;
    private CouponlDetailEntity entity;
    private LinearLayout activitySetingLayout;
    private Toolbar toolbar;

    private TextView couponl_money;
    private TextView couponlTitle;
    private TextView couponlEffectiveDate;
    private TextView couponlPromotionCode;
    private ImageView couponlQrcode;
    private TextView couponl_detail_use;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.color_D6DBE3);
        AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_couponl_detail_layout);
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
                ToastUtils.showCenterToast(CouponlDetailActivity.this, "点击返回了哦");
                // finish();
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        shswipeRefreshLayout = findView(R.id.shswipeRefreshLayout);
        couponl_money = findView(R.id.couponl_money);
        couponlTitle = findView(R.id.couponl_title);
        couponlEffectiveDate = findView(R.id.couponl_effective_date);
        couponlPromotionCode = findView(R.id.couponl_promotion_code);
        couponlQrcode = findView(R.id.couponl_qrcode);
        couponl_detail_use = findView(R.id.couponl_detail_use);
        shswipeRefreshLayout.setLoadmoreEnable(false);
        shswipeRefreshLayout.setRefreshEnable(false);
    }

    private void initDate() {
        entity = getIntent().getParcelableExtra(Constants.COUPONL_DETAIL_ENTITY);
        if (entity != null) {


            String couponmoney = entity.getCouponmoney();
            String coupontitle = entity.getCoupontitle();
            String startdate = entity.getGetcoupondate();
            String enddate = entity.getOverduedate();
            String promotioncode = entity.getPromotioncode();
            String quickcodeurl = entity.getQuickcodeurl();
            int couponstate = entity.getCouponstate();
            //有效日期：2017-01-10至2017-03-10
            if (!TextUtils.isEmpty(couponmoney)) {
                StringBuffer buffer=new StringBuffer();
                buffer.append("￥");
                buffer.append(couponmoney);
                couponl_money.setText(buffer.toString());
            }
            if (!TextUtils.isEmpty(coupontitle)) {
                couponlTitle.setText(coupontitle);
            }
            if (!TextUtils.isEmpty(startdate) && !TextUtils.isEmpty(enddate)) {
                StringBuilder builder = new StringBuilder();
                builder.append(getString(R.string.effective_date));
                builder.append(startdate);
                builder.append(getString(R.string.until));
                builder.append(enddate);
            }
            if (!TextUtils.isEmpty(promotioncode)) {
                couponlPromotionCode.setText(promotioncode);
            }
            if (!TextUtils.isEmpty(quickcodeurl)) {
                // couponlQrcode.set(couponmoney);
                Glide.with(this).load(quickcodeurl).placeholder(R.mipmap.ic_qrcode_business).into(couponlQrcode);
            }
            if (couponstate != 1) {
                couponl_detail_use.setVisibility(View.GONE);
            }
        }
    }

    private void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
