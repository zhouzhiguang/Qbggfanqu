package com.qbgg.cenglaicengqu.homepage.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.homepage.model.SelectShareBean;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 精选分享布局
 */

public class SelectionShareAdapter extends CommonAdapter<SelectShareBean> {

    private  Activity activity;
    private List<SelectShareBean> datas;
    private  int layoutId;
    public SelectionShareAdapter(Activity activity, int layoutId, List<SelectShareBean> datas) {
        super(activity, layoutId, datas);
        this.activity=activity;
        this.datas=datas;
        this.layoutId=layoutId;
    }

    @Override
    protected void convert(ViewHolder holder, SelectShareBean selectShareBean, int position) {
        ImageView shareimage=holder.getView(R.id.selectionshare_image);
        TextView share_title=holder.getView(R.id.selectionshare_title);
        TextView share_content=holder.getView(R.id.selectionshare_content);
        share_content.setText(selectShareBean.getSharecontent());
        share_title.setText(selectShareBean.getSharetiltl());
         String shareimageurl= selectShareBean.getShareimage();
        if (TextUtils.isEmpty(shareimageurl)){

        }
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);
    }


    public void setDates(List<SelectShareBean> dates) {
        this.datas = dates;
    }
}
