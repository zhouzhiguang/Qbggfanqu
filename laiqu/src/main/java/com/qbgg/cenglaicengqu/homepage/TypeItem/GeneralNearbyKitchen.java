package com.qbgg.cenglaicengqu.homepage.TypeItem;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.homepage.model.KitchenBean;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * 一般的附近的饭局
 */

public class GeneralNearbyKitchen implements ItemViewDelegate<KitchenBean> {
    @Override
    public int getItemViewLayoutId() {
        return R.layout.kitchen_recyclerview_item;
    }

    @Override
    public boolean isForViewType(KitchenBean item, int position) {

        return !item.isKitchenBeanlike();
    }

    @Override
    public void convert(ViewHolder holder, KitchenBean kitchenBean, int position) {

    }

}
