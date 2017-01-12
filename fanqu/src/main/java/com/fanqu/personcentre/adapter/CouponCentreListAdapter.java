package com.fanqu.personcentre.adapter;

import android.app.Activity;
import android.view.View;

import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.personcentre.model.CouponCentreDetailEntity;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 领券中心recyviewadapter
 */

public class CouponCentreListAdapter extends CommonAdapter<CouponCentreDetailEntity> {
    public CouponCentreListAdapter(Activity activity, int layoutId, List<CouponCentreDetailEntity> datas) {
        super(activity, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, CouponCentreDetailEntity couponCentreDetailEntity, int position) {

    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);

    }
}
