package com.qbgg.cenglaicengqu.addconference.adapter;

import android.app.Activity;
import android.view.View;

import com.qbgg.cenglaicengqu.addconference.modle.LeaveWordBean;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * Created by Administrator on 2016/11/30.
 */

public class DinnerDetailsEaveWordsAdapter extends CommonAdapter<LeaveWordBean> {

    private  Activity activity;
    private List<LeaveWordBean> datas;
    public DinnerDetailsEaveWordsAdapter(Activity activity, int layoutId, List<LeaveWordBean> datas) {
        super(activity, layoutId, datas);
        this.activity=activity;
        this.datas=datas;

    }
    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);

    }

    @Override
    protected void convert(ViewHolder holder, LeaveWordBean leaveWordBean, int position) {

    }
}
