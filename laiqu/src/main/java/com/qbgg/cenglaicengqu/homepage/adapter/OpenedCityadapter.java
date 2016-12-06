package com.qbgg.cenglaicengqu.homepage.adapter;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 已经开通城市列表
 */

public class OpenedCityadapter extends CommonAdapter<String> {
    private Activity activity;
    private  String locationcity;


    public OpenedCityadapter(Activity activity, int layoutId, List<String> datas) {
        super(activity, layoutId, datas);
        this.activity = activity;
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        TextView cityname = holder.getView(R.id.opened_city_name);
        LinearLayout opened_city_layout = holder.getView(R.id.opened_city_layout);
        ImageView opened_city_location = holder.getView(R.id.opened_city_location);
        holder.setText(R.id.opened_city_name, s);
        if (locationcity.equals(s)){
            opened_city_location.setVisibility(View.VISIBLE);
        }
        if (position == 0) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) opened_city_layout.getLayoutParams();
            params.setMargins(0, 0, 0, 0);
            opened_city_layout.setLayoutParams(params);
        }
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        super.onViewHolderCreated(holder, itemView);
        AutoUtils.auto(itemView);
    }

    public void setLocationcity(String locationcity) {
        this.locationcity = locationcity;
    }
}
