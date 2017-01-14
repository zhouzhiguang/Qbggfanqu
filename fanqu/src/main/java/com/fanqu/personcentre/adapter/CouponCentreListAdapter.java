package com.fanqu.personcentre.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.main.widget.CircleProgressBar;
import com.fanqu.personcentre.model.CouponCentreDetailEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 领券中心recyviewadapter
 */

public class CouponCentreListAdapter extends CommonAdapter<CouponCentreDetailEntity> {
    private Activity activity;
    private int unlimitedcolor, havelimitedcolor;//没有限制颜色
    private int unlimitedprogressBarBackgroundColor, havelimitedprogressBarBackgroundColor;
    private int coupon_over_color;
    private List<CouponCentreDetailEntity> datas;
    private int end;
    //记录上次的位置和容器
    private LinearLayout oldContainer;
    private int oldPosition;

    private final int bottom;
    private final int top;
    private int marginRigh, marginbottom;
    private int imagewidth;

    public CouponCentreListAdapter(Activity activity, int layoutId, List<CouponCentreDetailEntity> datas) {
        super(activity, layoutId, datas);
        this.activity = activity;
        this.datas = datas;
        unlimitedcolor = ContextCompat.getColor(activity, R.color.color_76B6FF);
        unlimitedprogressBarBackgroundColor = ContextCompat.getColor(activity, R.color.color_4499FF);
        havelimitedprogressBarBackgroundColor = ContextCompat.getColor(activity, R.color.red_bg);
        havelimitedcolor = ContextCompat.getColor(activity, R.color.color_F35757);
        coupon_over_color = ContextCompat.getColor(activity, R.color.color_C2C2C2);
        end = datas.size() - 1;
        bottom = AutoUtils.getDisplayHeightValue((int) activity.getResources().getDimension(R.dimen.dimen_80px));
        top = AutoUtils.getDisplayHeightValue((int) activity.getResources().getDimension(R.dimen.dimen_40px));
    }

    @Override
    protected void convert(ViewHolder holder, CouponCentreDetailEntity entity, int position) {
        RelativeLayout coupon_centre_root_content_layout = holder.getView(R.id.coupon_centre_root_content_layout);
        ImageView coupon_centre_top_image = holder.getView(R.id.coupon_centre_top_image);
        ImageView coupon_centre_bottom_image = holder.getView(R.id.coupon_centre_bottom_image);
        LinearLayout coupon_centre_root_layout = holder.getView(R.id.coupon_centre_root_layout);
        //领取进度
        CircleProgressBar circleProgressBar = holder.getView(R.id.coupon_get_amount);
        TextView coupon_title = holder.getView(R.id.coupon_title);
        TextView coupontmoney_discount = holder.getView(R.id.coupon_price);
        TextView coupon_service_conditions = holder.getView(R.id.coupon_service_conditions);
        TextView coupon_money_unit = holder.getView(R.id.coupon_money_unit);
        LinearLayout coupon_centre_layout = holder.getView(R.id.coupon_centre_layout);
        RelativeLayout coupon_layout = holder.getView(R.id.coupon_layout);
        CircleImageView coupon_centre_has_get = holder.getView(R.id.coupon_centre_has_get);
        TextView coupon_centre_immediately_get = holder.getView(R.id.coupon_centre_immediately_get);
        //已经抢光了
        CircleImageView coupon_centre_over = holder.getView(R.id.coupon_centre_over);
//        if (marginRigh == 0) {
//            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//            coupon_centre_top_image.measure(w, h);
//            int height = coupon_centre_top_image.getMeasuredHeight();
//            int width = coupon_centre_top_image.getMeasuredWidth();
//            coupon_centre_top_image.measure(width, height);
//            int referencemargrigh = (int) activity.getResources().getDimension(R.dimen.dimen_400px);
//            imagewidth = width;
//            marginRigh = referencemargrigh - width / 2;
//        }
//        if (marginbottom == 0) {
//            int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//            int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
//            coupon_centre_root_content_layout.measure(w, h);
//            int height = coupon_centre_root_content_layout.getMeasuredHeight();
//            int width = coupon_centre_root_content_layout.getMeasuredWidth();
//            coupon_centre_root_content_layout.measure(width, height);
//            marginbottom = height;
//        }
     //   LogUtil.e("jjj", imagewidth + "----56----------");
//        RelativeLayout.LayoutParams imageparams = (RelativeLayout.LayoutParams) coupon_centre_top_image.getLayoutParams();
//        imageparams.width = imagewidth;
//        imageparams.height = imagewidth;
//        imageparams.addRule(RelativeLayout.CENTER_IN_PARENT);
//        imageparams.alignWithParent=true;
//        imageparams.setMargins(0, 0, marginRigh, marginbottom - imagewidth);
//        coupon_centre_top_image.setScaleType(ImageView.ScaleType.FIT_XY);
//        coupon_centre_top_image.setLayoutParams(imageparams);
//        imageparams.setMargins(0, marginbottom - imagewidth, marginRigh, 0);
//        coupon_centre_bottom_image.setLayoutParams(imageparams);
        ////优惠券抵扣条件 0代表无门槛消费 数字代表满多少能使用
        int couponcondition = entity.getCouponcondition();
        int couponcentrestate = entity.getCouponcentrestate();
        String coupontitle = entity.getCoupontitle();
        String coupontmoney = entity.getCouponmoney();
        String Alreadygetcouponnumberpeople = entity.getAlreadygetcouponnumberpeople();
        //关联circleProgressBar
        if (!TextUtils.isEmpty(Alreadygetcouponnumberpeople)) {
            String progress = Alreadygetcouponnumberpeople.substring(0, Alreadygetcouponnumberpeople.length() - 1);
            circleProgressBar.setProgress(Float.valueOf(progress));
            StringBuffer buffer = new StringBuffer();
            buffer.append(activity.getString(R.string.has_get));
            buffer.append("\n");
            buffer.append(Alreadygetcouponnumberpeople);
            circleProgressBar.setText(buffer.toString());
        }
        ////优惠券状态  1未使用0已经使用-1已经过期
        int couponstate = entity.getCouponstate();

        if (!TextUtils.isEmpty(coupontitle)) {
            coupon_title.setText(coupontitle);
        }
        if (!TextUtils.isEmpty(coupontmoney)) {
            coupontmoney_discount.setText(coupontmoney);
        }
        if (couponcondition == 0) {
            //无门槛
            coupon_service_conditions.setText(activity.getString(R.string.coupon_unlimited));
            coupon_money_unit.setTextColor(unlimitedcolor);
            coupontmoney_discount.setTextColor(unlimitedcolor);
            coupon_service_conditions.setTextColor(unlimitedcolor);
            coupon_centre_layout.setBackgroundColor(unlimitedcolor);
            circleProgressBar.setBackgroundColor(unlimitedprogressBarBackgroundColor);
        } else {
            String coupon_unlimited = activity.getString(R.string.coupon_exceed_how_much);
            coupon_service_conditions.setText(String.format(coupon_unlimited, entity.getCouponcondition()));
            coupon_money_unit.setTextColor(havelimitedcolor);
            coupontmoney_discount.setTextColor(havelimitedcolor);
            coupon_service_conditions.setTextColor(havelimitedcolor);
            coupon_centre_layout.setBackgroundColor(havelimitedcolor);
            circleProgressBar.setBackgroundColor(havelimitedprogressBarBackgroundColor);

        }
        ////三个状态 1  还未领取  ;0 已经领取 -1 标识 已经卖完了抢光了
        if (couponcentrestate == 0) {
            //已经领取
            circleProgressBar.setVisibility(View.GONE);
            //设置去使用
            coupon_centre_immediately_get.setText(activity.getString(R.string.ensure_use));
            coupon_centre_has_get.setVisibility(View.VISIBLE);
            coupon_centre_over.setVisibility(View.GONE);
            //、、app:text="已领\n72%"
            //、、app:text="已领\n72%"


        } else if (couponcentrestate == 1) {
            //未领取
            circleProgressBar.setVisibility(View.VISIBLE);
            coupon_centre_has_get.setVisibility(View.GONE);
            coupon_centre_immediately_get.setText(activity.getString(R.string.immediately_get_coupont));
            coupon_centre_over.setVisibility(View.GONE);
        } else {
            //已经领取完了
            coupon_centre_layout.setVisibility(View.GONE);
            coupon_centre_over.setVisibility(View.VISIBLE);
            coupon_money_unit.setTextColor(coupon_over_color);
            coupontmoney_discount.setTextColor(coupon_over_color);
            coupon_service_conditions.setTextColor(coupon_over_color);
            coupon_layout.setBackgroundColor(coupon_over_color);
        }

        if (position == end) {

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) coupon_centre_root_layout.getLayoutParams();

            params.setMargins(0, top, 0, bottom);
            coupon_centre_root_layout.setLayoutParams(params);
            //oldPosition = position;
        } else {

            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) coupon_centre_root_layout.getLayoutParams();

            params.setMargins(0, top, 0, 0);
            coupon_centre_root_layout.setLayoutParams(params);
            // oldPosition = position;
        }


    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);

    }
}
