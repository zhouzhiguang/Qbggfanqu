package com.fanqu.dinner.adapter;

import android.app.Activity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.dinner.modle.DinnerBean;
import com.fanqu.framework.autolayout.AutoUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 饭局adapter
 */

public class DinnerAdapter extends CommonAdapter<DinnerBean> {
    private Activity activity;
    private List<DinnerBean> datas;
    private LinearLayout dinner_recyclerview_one_image_layout, dinner_recyclerview_three_image_layout, dinner_recyclerview_two_image_layout;
    private ImageView dinner_recyclerview_one_image_introduce, dinner_recyclerview_two_image_introduce, dinner_recyclerview_three_image_introduce;

    public DinnerAdapter(Activity activity, int layoutId, List<DinnerBean> datas) {
        super(activity, layoutId, datas);
        this.activity = activity;
        this.datas = datas;
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);

    }

    @Override
    protected void convert(ViewHolder holder, DinnerBean dinnerBean, int position) {
        //头像
        CircleImageView dinner_recyclerview_politeness_image = holder.getView(R.id.dinner_recyclerview_politeness_image);
        //昵称
        TextView dinner_accounts_nickname = holder.getView(R.id.dinner_accounts_nickname);
        //当前饭局的状态
        ImageView dinner_state = holder.getView(R.id.dinner_state);
        //饭局介绍
        TextView dinner_introduc = holder.getView(R.id.dinner_introduce);
        dinner_recyclerview_one_image_introduce = holder.getView(R.id.dinner_recyclerview_one_image_introduce);
        dinner_recyclerview_two_image_introduce = holder.getView(R.id.dinner_recyclerview_two_image_introduce);
        dinner_recyclerview_three_image_introduce = holder.getView(R.id.dinner_recyclerview_three_image_introduce);
       //关注
        TextView dinner_attention=holder.getView(R.id.dinner_attention);
        //评论
        TextView dinner_comment=holder.getView(R.id.dinner_comment);
        //喜欢
        TextView dinner_like=holder.getView(R.id.dinner_like);

    }
}
