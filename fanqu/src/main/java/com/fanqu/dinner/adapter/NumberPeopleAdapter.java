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
 * 在订单确定时候人数可以滑动的viewpage
 */

public class NumberPeopleAdapter extends CommonAdapter<String> {

    private Context context;
    private List<String> datas;
    private int onItemSelect = -1;

    public NumberPeopleAdapter(Context context, int layoutId, List<String> datas) {
        super(context, layoutId, datas);
        this.context = context;
        this.datas = datas;
    }

    @Override
    protected void convert(ViewHolder holder, String s, int position) {
        final TextView number_people = holder.getView(R.id.number_people);
        LinearLayout number_people_layout = holder.getView(R.id.number_people_layout);
        if (!TextUtils.isEmpty(s)) {
            number_people.setText(s);
        }
        if (position == 0) {
            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) number_people_layout.getLayoutParams();
            int top = (int) context.getResources().getDimension(R.dimen.dimen_40px);
            int right = (int) context.getResources().getDimension(R.dimen.dimen_80px);
            params.setMargins(0, top, right, 0);
            number_people_layout.setLayoutParams(params);
        }
        if (position == onItemSelect) {
            number_people.setSelected(true);
        } else {
            number_people.setSelected(false);
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
