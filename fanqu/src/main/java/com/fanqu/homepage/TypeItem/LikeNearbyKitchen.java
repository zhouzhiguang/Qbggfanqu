package com.fanqu.homepage.TypeItem;

import com.fanqu.R;
import com.fanqu.homepage.model.KitchenBean;
import com.zhy.adapter.recyclerview.base.ItemViewDelegate;
import com.zhy.adapter.recyclerview.base.ViewHolder;

/**
 * 喜欢收藏的饭局在附近的厨房出现
 */
public class LikeNearbyKitchen implements ItemViewDelegate<KitchenBean> {
    @Override
    public int getItemViewLayoutId() {

        return R.layout.kitchen_recyclerview_like_item;
    }

    @Override
    public boolean isForViewType(KitchenBean item, int position) {
        return item.isKitchenBeanlike();
    }

    @Override
    public void convert(ViewHolder holder, KitchenBean kitchenBean, int position) {

    }
}
