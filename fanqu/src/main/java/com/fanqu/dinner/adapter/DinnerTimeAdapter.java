package com.fanqu.dinner.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.autolayout.AutoUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;


/**
 * 可顶餐日期 recyclerview adapter
 */

public class DinnerTimeAdapter extends CommonAdapter<String> {
    private Context context;
    private int onItemSelect = -1;

    public DinnerTimeAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        this.context = context;
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        TextView timetextview = holder.getView(R.id.time);
        LinearLayout time_layout = holder.getView(R.id.time_layout);
        if (!TextUtils.isEmpty(s)) {
            timetextview.setText(s);
        }
        if (position == 0) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) time_layout.getLayoutParams();
            int top = (int) context.getResources().getDimension(R.dimen.dimen_40px);
            int right=(int) context.getResources().getDimension(R.dimen.dimen_60px);
            params.setMargins(0, top, right, 0);
            time_layout.setLayoutParams(params);
        }
        if (position == onItemSelect) {
            timetextview.setSelected(true);
        } else {
            timetextview.setSelected(false);
        }
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);

    }
    public void setOnItemSelect(int onItemSelect) {
        this.onItemSelect = onItemSelect;
    }
    public int getOnItemSelect() {
        return onItemSelect;
    }
}
