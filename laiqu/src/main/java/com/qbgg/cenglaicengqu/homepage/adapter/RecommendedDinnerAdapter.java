package com.qbgg.cenglaicengqu.homepage.adapter;

import android.app.Activity;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.homepage.model.RecommendedDinnerBean;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

/**
 * 主页推荐饭局适配器
 */

public class RecommendedDinnerAdapter extends CommonAdapter<RecommendedDinnerBean> {
    private  List<RecommendedDinnerBean>beans;
    private Activity activity;

    public RecommendedDinnerAdapter(Activity activity, int layoutId, List<RecommendedDinnerBean> datas) {
        super(activity, layoutId, datas);
        beans=datas;
        this.activity=activity;
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        super.onViewHolderCreated(holder, itemView);
        AutoUtils.auto(itemView);
    }

    @Override
    protected void convert(ViewHolder holder, RecommendedDinnerBean recommendedDinnerBean, int position) {
        /* Picasso.with(activity)
          .load(url)
          .fit()
          .tag(activity)
          .into(imageView);
        */
        //LogUtil.e("测试",position+"--------------- ");
        TextView content= holder.getView(R.id.recommended_dinner_content);
        content.setText(recommendedDinnerBean.getRecommendedinnerbeancontent());
        TextView title= holder.getView(R.id.recommended_dinner_title);
        title.setText(recommendedDinnerBean.getRecommendedinnerbeantitle());
        ImageView imageView= holder.getView(R.id.recommended_dinner_image);
        String resulr= recommendedDinnerBean.getRecommendedinnerbeanicon();
        if (TextUtils.isEmpty(resulr)){
            imageView.setImageResource(R.mipmap.ic_major_suit_low_price);
        }
        //holder.setImageBitmap()
//        holder.getView();
//        holder.setImageBitmap(1,)
    }
}
