package com.qbgg.cenglaicengqu.addconference.adapter;

import android.app.Activity;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.netease.nim.uikit.common.ui.imageview.CircleImageView;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.addconference.modle.Personnelsigned;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 已经报名recylerviewadapter
 */

public class DinnerDetailsAlreadysignedAdapter extends CommonAdapter<Personnelsigned> {
    private Activity activity;
    private List<Personnelsigned> datas;
    private int color;

    public DinnerDetailsAlreadysignedAdapter(Activity activity, int layoutId, List<Personnelsigned> datas) {
        super(activity, layoutId, datas);
        this.activity = activity;
        this.datas = datas;
        color = ContextCompat.getColor(activity, R.color.textGrayDeep);
    }

    @Override
    protected void convert(ViewHolder holder, Personnelsigned personnelsigned, int position) {
        //头像
        CircleImageView heaimage = holder.getView(R.id.signed_up_person_headimage);
        //昵称
        TextView signed_up_person_nicename = holder.getView(R.id.signed_up_person_nicename);
        //报名时间
        TextView signed_up_time = holder.getView(R.id.signed_up_time);
        //后面提示
        TextView signed_up_time_tips = holder.getView(R.id.signed_up_time_tips);
        if (position == 0) {
            signed_up_person_nicename.setTextColor(color);
            signed_up_time.setTextColor(color);
            signed_up_time_tips.setTextColor(color);
        }


    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);

    }
}
