package com.fanqu.personcentre.adapter;


import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.personcentre.model.CouponlDetailEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 优惠券列表 recyview 适配器
 */

public class CouponlListAdapter extends CommonAdapter<CouponlDetailEntity> {
    private Activity activity;
    private List<CouponlDetailEntity> datas;
    private String blank_13 = "             ";

    public CouponlListAdapter(Activity activity, int layoutId, List<CouponlDetailEntity> datas) {
        super(activity, layoutId, datas);
        this.datas = datas;
        this.activity = activity;
    }

    @Override
    protected void convert(ViewHolder holder, CouponlDetailEntity entity, int position) {
        RelativeLayout coupon_layout = holder.getView(R.id.coupon_layout);
        TextView coupon_money = holder.getView(R.id.coupon_money);
        TextView coupon_condition = holder.getView(R.id.coupon_condition);
        TextView coupon_limit = holder.getView(R.id.coupon_limit);
        TextView coupon_title = holder.getView(R.id.coupon_title);
        TextView coupon_detailed_information = holder.getView(R.id.coupon_detailed_information);
        TextView coupon_us_date = holder.getView(R.id.coupon_us_date);
        CircleImageView coupon_us_state = holder.getView(R.id.coupon_us_state);
        coupon_money.setText(entity.getCouponmoney());
        coupon_title.setText(blank_13 + entity.getCoupontitle());
        int couponcondition = entity.getCouponcondition();
        if (couponcondition == 0) {
            //无门槛
            coupon_condition.setText(activity.getString(R.string.coupon_unlimited));
            coupon_limit.setText(activity.getString(R.string.coupon_consumption_unlimited));

        } else {
            String coupon_unlimited = activity.getString(R.string.coupon_exceed_how_much);
            coupon_condition.setText(String.format(coupon_unlimited, entity.getCouponcondition()));
            coupon_limit.setText(activity.getString(R.string.coupon_exceed_minus));
        }
        String date = entity.getGetcoupondate() + "-" + entity.getOverduedate();
        coupon_us_date.setText(date);
        int couponstate = entity.getCouponstate();
        //优惠券状态  1未使用0已经使用-1已经过期
        if (couponstate == -1) {
            //已经过期
            coupon_us_state.setVisibility(View.GONE);
            coupon_layout.setBackgroundColor(ContextCompat.getColor(activity, R.color.color_C2C2C2));
        } else if (couponstate == 0) {
            //已经使用
            coupon_us_state.setVisibility(View.VISIBLE);
            coupon_layout.setBackgroundColor(ContextCompat.getColor(activity, R.color.color_C2C2C2));

        } else {
            coupon_us_state.setVisibility(View.GONE);
            if (couponcondition == 0) {
                coupon_layout.setBackgroundColor(ContextCompat.getColor(activity, R.color.color_FF7676));
            } else {
                coupon_layout.setBackgroundColor(ContextCompat.getColor(activity, R.color.color_76B6FF));
                coupon_limit.setSelected(true);
            }

        }
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);

    }
}
