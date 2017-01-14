package com.fanqu.dinner.adapter;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.dinner.modle.DinnerPartyEntity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 饭局列表recyview adapter
 */

public class DinnerPartyListAdapter extends CommonAdapter<DinnerPartyEntity> {

    private List<DinnerPartyEntity> datas;
    private int endpostion;
    private int bottom;

    public DinnerPartyListAdapter(final Activity activity, int layoutId, List<DinnerPartyEntity> datas) {
        super(activity, layoutId, datas);
        this.datas = datas;
        bottom = (int) activity.getResources().getDimension(R.dimen.dimen_40px);
        endpostion = datas.size() - 1;
//        Glide.with(activity)
//                .load("http://dn-qpos-box.qbox.me/hqs.jpg").asBitmap()
//                .into(new SimpleTarget<Bitmap>() {
//                    @Override
//                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap> glideAnimation) {
//                        Drawable drawable = new BitmapDrawable(activity.getResources(), resource);
//                        LogUtil.e(String.valueOf(drawable == null) + "----------------------");
//                    }
//                });
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void convert(ViewHolder holder, DinnerPartyEntity entity, int position) {
        LinearLayout dinner_party_main_layout = holder.getView(R.id.dinner_party_main_layout);
        RelativeLayout dinner_party_rest_layout = holder.getView(R.id.dinner_party_rest_layout);
        RelativeLayout dinner_party_headimage_layout = holder.getView(R.id.dinner_party_headimage_layout);
        TextView dinner_party_theme = holder.getView(R.id.dinner_party_theme);
        TextView dinner_party_location = holder.getView(R.id.dinner_party_location);
        TextView dinner_party_galleryful = holder.getView(R.id.dinner_party_galleryful);
        TextView dinner_party_distance = holder.getView(R.id.dinner_party_distance);
        TextView dinner_party_feature1 = holder.getView(R.id.dinner_party_feature1);
        TextView dinner_party_feature2 = holder.getView(R.id.dinner_party_feature2);
        TextView dinner_party_feature3 = holder.getView(R.id.dinner_party_feature3);
        TextView dinner_party_money = holder.getView(R.id.dinner_party_money);
        ImageView dinner_party_like = holder.getView(R.id.dinner_party_like);
        int dinnerparty = entity.getDinnerparty();
        String theme = entity.getDinnerpartytheme();
        boolean isAtrest = entity.isAtrest();
        String partylocation = entity.getDinnerpartylocation();
        String dinnerpartygalleryful = entity.getDinnerpartygalleryful();
        String distance = entity.getDinnerpartydistance();
        String[] dinnerpartyfeature = entity.getDinnerpartyfeature();
        String dinnerpartymoney = entity.getDinnerpartymoney();
        if (!TextUtils.isEmpty(dinnerpartymoney)) {
            dinner_party_money.setText(dinnerpartymoney);
        }
        if (dinnerpartyfeature != null && dinnerpartyfeature.length > 0) {
            int length = dinnerpartyfeature.length;
            if (length == 1) {
                dinner_party_feature1.setVisibility(View.VISIBLE);
                dinner_party_feature1.setText(dinnerpartyfeature[0]);
            } else if (length == 2) {
                dinner_party_feature1.setVisibility(View.VISIBLE);
                dinner_party_feature1.setText(dinnerpartyfeature[0]);
                dinner_party_feature2.setVisibility(View.VISIBLE);
                dinner_party_feature2.setText(dinnerpartyfeature[1]);
            } else {
                dinner_party_feature1.setVisibility(View.VISIBLE);
                dinner_party_feature1.setText(dinnerpartyfeature[0]);
                dinner_party_feature2.setVisibility(View.VISIBLE);
                dinner_party_feature2.setText(dinnerpartyfeature[1]);
                dinner_party_feature3.setVisibility(View.VISIBLE);
                dinner_party_feature3.setText(dinnerpartyfeature[1]);
            }
        }
        if (!TextUtils.isEmpty(distance)) {
            dinner_party_distance.setText(distance);
        }
        if (!TextUtils.isEmpty(dinnerpartygalleryful)) {
            dinner_party_galleryful.setText(dinnerpartygalleryful);
        }
        if (!TextUtils.isEmpty(partylocation)) {
            dinner_party_location.setText(partylocation);
        }
        if (isAtrest) {
            dinner_party_rest_layout.setVisibility(View.VISIBLE);
        } else {
            dinner_party_rest_layout.setVisibility(View.GONE);

        }
        if (!TextUtils.isEmpty(theme)) {
            dinner_party_theme.setText(theme);
        }

        if (dinnerparty == 1) {
            dinner_party_like.setImageAlpha(255);
            dinner_party_like.setImageResource(R.mipmap.ic_love_solid_read);
        } else {
            dinner_party_like.setImageAlpha(4);
            dinner_party_like.setImageResource(R.mipmap.ic_love_solid);
        }
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) dinner_party_main_layout.getLayoutParams();
        if (position == endpostion) {
            params.setMargins(0, 0, 0, bottom);
            dinner_party_main_layout.setLayoutParams(params);
        } else {
            params.setMargins(0, 0, 0, 0);
            dinner_party_main_layout.setLayoutParams(params);
        }
        AutoUtils.autoMargin(dinner_party_main_layout);
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);

    }
}
