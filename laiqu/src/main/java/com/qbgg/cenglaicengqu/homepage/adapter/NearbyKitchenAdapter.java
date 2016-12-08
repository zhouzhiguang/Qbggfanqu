package com.qbgg.cenglaicengqu.homepage.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.LinearLayout;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.homepage.TypeItem.GeneralNearbyKitchen;
import com.qbgg.cenglaicengqu.homepage.TypeItem.LikeNearbyKitchen;
import com.qbgg.cenglaicengqu.homepage.model.KitchenBean;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 附近的厨房adapter
 */

public class NearbyKitchenAdapter extends CommonAdapter<KitchenBean> {
    private List<KitchenBean> datas;

    public NearbyKitchenAdapter(Activity activity, int layoutId, List<KitchenBean> datas) {
        super(activity, layoutId, datas);
        this.datas = datas;
        addItemViewDelegate(new GeneralNearbyKitchen());
        //喜欢的附近的饭局
        addItemViewDelegate(new LikeNearbyKitchen());
    }

    @Override
    protected void convert(ViewHolder holder, KitchenBean kitchenBean, int position) {
        LinearLayout kitchen_recyclerview_layout = holder.getView(R.id.kitchen_recyclerview_layout);
//        if (position == 0) {
//            RecyclerView.LayoutParams params = (RecyclerView.LayoutParams) kitchen_recyclerview_layout.getLayoutParams();
//            params.setMargins(0, 0, 0, 0);
//            kitchen_recyclerview_layout.setLayoutParams(params);
//        }

    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        super.onViewHolderCreated(holder, itemView);
        AutoUtils.auto(itemView);
    }

}
