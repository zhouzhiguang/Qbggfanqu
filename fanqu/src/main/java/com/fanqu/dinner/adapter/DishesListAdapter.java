package com.fanqu.dinner.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.dinner.modle.DishesEntity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 菜单recyview adapter
 */

public class DishesListAdapter extends CommonAdapter<DishesEntity> {
    private Activity activity;

    public DishesListAdapter(Activity activity, int layoutId, List<DishesEntity> datas) {
        super(activity, layoutId, datas);
        this.activity = activity;
    }

    @Override
    protected void convert(ViewHolder holder, DishesEntity entity, int position) {
        TextView dish_name = holder.getView(R.id.dish_name);
        TextView bracket_left = holder.getView(R.id.bracket_left);
        TextView signature_dishes = holder.getView(R.id.signature_dishes);
        TextView bracket_reight = holder.getView(R.id.bracket_reight);
        String dishname = entity.getDish_name();
        boolean isspecialty = entity.isspecialty();
        if (!isspecialty) {
            bracket_left.setVisibility(View.GONE);
            signature_dishes.setVisibility(View.GONE);
            bracket_reight.setVisibility(View.GONE);
        } else {
            bracket_left.setVisibility(View.VISIBLE);
            signature_dishes.setVisibility(View.VISIBLE);
            bracket_reight.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(dishname)) {
            dish_name.setText(dishname);
        }

    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);

    }
}
