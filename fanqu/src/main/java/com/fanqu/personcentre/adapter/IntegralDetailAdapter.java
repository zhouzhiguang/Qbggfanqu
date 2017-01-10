package com.fanqu.personcentre.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.personcentre.model.IntegralDetailEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 我的积分详情页面
 */


public class IntegralDetailAdapter extends CommonAdapter<IntegralDetailEntity> {

    public IntegralDetailAdapter(Activity activity, int layoutId, List<IntegralDetailEntity> datas) {
        super(activity, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, IntegralDetailEntity entity, int position) {
        TextView title = holder.getView(R.id.integral_detail_title);
        TextView date = holder.getView(R.id.integral_detail_date);
        TextView count = holder.getView(R.id.integral_count);
        title.setText(entity.getIntegraldetailtitle());
        date.setText(entity.getIntegraldate());
        count.setText(entity.getIntegralcount());
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);

    }
}
