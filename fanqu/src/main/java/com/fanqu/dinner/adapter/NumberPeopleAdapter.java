package com.fanqu.dinner.adapter;

import android.content.Context;
import android.view.View;

import com.fanqu.framework.autolayout.AutoUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 在订单确定时候人数可以滑动的viewpage
 */

public class NumberPeopleAdapter extends CommonAdapter<String> {


    public NumberPeopleAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {

    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);

    }
}
