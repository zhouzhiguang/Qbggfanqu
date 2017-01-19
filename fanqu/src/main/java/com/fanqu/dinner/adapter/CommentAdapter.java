package com.fanqu.dinner.adapter;

import android.app.Activity;
import android.support.v4.widget.Space;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.fanqu.R;
import com.fanqu.dinner.modle.CommentEntity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.main.widget.StarView;
import com.zhy.adapter.recyclerview.CommonAdapter;
import com.zhy.adapter.recyclerview.base.ViewHolder;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * 饭局评论adapter
 */

public class CommentAdapter extends CommonAdapter<CommentEntity> {

    private String[] commentimageurl;
    private Activity activity;
    private List<CommentEntity> datas;

    public CommentAdapter(Activity activity, int layoutId, List<CommentEntity> datas) {
        super(activity, layoutId, datas);
        this.activity = activity;
        this.datas = datas;
    }

    @Override
    protected void convert(ViewHolder holder, CommentEntity entity, int position) {
        CircleImageView head_image = holder.getView(R.id.head_image);
        ImageView identity_sign = holder.getView(R.id.identity_sign);
        TextView comment_person_nicename = holder.getView(R.id.comment_person_nicename);
        Space comment_space = holder.getView(R.id.comment_space);
        TextView comment_time = holder.getView(R.id.comment_time);
        StarView starview = holder.getView(R.id.starview);
        TextView comment_text = holder.getView(R.id.comment_text);
        RelativeLayout comment_image_layout = holder.getView(R.id.comment_image_layout);
        ImageView comment_image1 = holder.getView(R.id.comment_image1);
        ImageView comment_image2 = holder.getView(R.id.comment_image2);
        ImageView comment_image3 = holder.getView(R.id.comment_image3);
        TextView comment_set_meal = holder.getView(R.id.comment_set_meal);
        TextView comment_date = holder.getView(R.id.comment_date);
        View line = holder.getView(R.id.line);
        String nicemane = entity.getCommentpersonnicemane();
        String headimageurl = entity.getCommentpersonheadimageurl();
        String dinnerpartygrade = entity.getDinnerpartygrade();
        String commenttext = entity.getCommenttext();
        String commentdate = entity.getCommentdate();
        String commentsetmeal = entity.getCommentsetmeal();
        if (position == datas.size() - 1) {
            line.setVisibility(View.GONE);
        } else {
            line.setVisibility(View.VISIBLE);
        }
        if (!TextUtils.isEmpty(commentsetmeal)) {
            comment_set_meal.setText(commentsetmeal);
        }
        if (!TextUtils.isEmpty(commenttext)) {
            comment_text.setText(commenttext);
        }
        commentimageurl = entity.getCommentimageurl();
        if (commentimageurl != null && commentimageurl.length > 0) {
            int length = commentimageurl.length;
            comment_space.setVisibility(View.VISIBLE);
            comment_image_layout.setVisibility(View.VISIBLE);
            if (length == 1) {
                //一张评论的图片
                comment_image1.setVisibility(View.VISIBLE);
                comment_image2.setVisibility(View.GONE);
                comment_image3.setVisibility(View.GONE);
                Glide.with(activity).load(commentimageurl[0]).into(comment_image1);
            } else if (length == 2) {
                comment_image1.setVisibility(View.VISIBLE);
                comment_image2.setVisibility(View.VISIBLE);
                comment_image3.setVisibility(View.GONE);
                Glide.with(activity).load(commentimageurl[0]).into(comment_image1);
                Glide.with(activity).load(commentimageurl[1]).into(comment_image2);
            } else {
                comment_image1.setVisibility(View.VISIBLE);
                comment_image2.setVisibility(View.VISIBLE);
                comment_image3.setVisibility(View.VISIBLE);
                Glide.with(activity).load(commentimageurl[0]).into(comment_image1);
                Glide.with(activity).load(commentimageurl[1]).into(comment_image2);
                Glide.with(activity).load(commentimageurl[2]).into(comment_image3);
            }
        } else {
            comment_image_layout.setVisibility(View.GONE);
            if (position % 4 == 0) {
                comment_image_layout.setVisibility(View.VISIBLE);
            }
        }
        if (!TextUtils.isEmpty(commentdate)) {
            comment_time.setText(commentdate);
            comment_date.setText(commentdate);
        }
        starview.setMark(4.5f);
        if (!TextUtils.isEmpty(dinnerpartygrade)) {
            float grade = Float.valueOf(dinnerpartygrade);
            starview.setMark(grade);
        }
        if (!TextUtils.isEmpty(headimageurl)) {
            // head_image.setText(headimageurl);
        }
        if (!TextUtils.isEmpty(nicemane)) {
            comment_person_nicename.setText(nicemane);
        }
    }

    @Override
    public void onViewHolderCreated(ViewHolder holder, View itemView) {
        AutoUtils.auto(itemView);
        super.onViewHolderCreated(holder, itemView);

    }
}
