package com.qbgg.cenglaicengqu.addconference.activitiies;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netease.nim.uikit.common.ui.imageview.CircleImageView;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.managestatus.SystemBarTintManager;

public class DinnerReleaseSuccessActivity extends BaseActivity implements View.OnClickListener {

    private TextView releaseSuccessPassword;
    private CircleImageView dinnerRecyclerviewPolitenessImage;//帐号头像；
    private TextView dinnerAccountsNickname;//用户昵称
    private ImageView dinnerAccountsHeadPortrait;//用户的等级身份
    private ImageView dinnerState;//当前饭局的状态
    private TextView dinnerIntroduce;//简单的饭局介绍
    private LinearLayout dinnerRecyclerviewImagesLayout;//饭局介绍的主图片布局没有就不显示了
    private LinearLayout dinner_one_image_layout, dinner_two_image_layout, dinner_three_image_layout;
    private ImageView dinner_one_image_introduce, dinner_two_image_introduce, dinner_three_image_introduce;//最多三张图片
    private LinearLayout dinnerLocationTimeLayout;
    //饭局地点时间人数；
    private TextView dinner_release_location, dinner_release_number_of_people, dinner_release_time;
    private TextView dinnerAttention;//关注
    private TextView dinnerComment;//评论
    private TextView dinnerLike;//喜欢数
    private TextView releaseSuccessBackDinnerPartyDetails;//返回饭局详情
    private TextView releaseSuccessInviteFriendsCengRice;//邀请好友来蹭饭


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        initthem(R.color.white);
        setContentView(R.layout.activity_dinner_release_success_layout);
        AutoUtils.auto(this);
        assignViews();
        initListener();
    }


    private void assignViews() {

        releaseSuccessPassword = (TextView) findView(
                R.id.release_success_password);
        dinnerRecyclerviewPolitenessImage = (CircleImageView) findView(
                R.id.dinner_recyclerview_politeness_image);
        dinnerAccountsNickname = (TextView) findView(
                R.id.dinner_accounts_nickname);
        dinnerAccountsHeadPortrait = (ImageView) findView(
                R.id.dinner_accounts_head_identity);
        dinnerState = (ImageView) findView(
                R.id.dinner_state);
        dinnerIntroduce = (TextView) findView(
                R.id.dinner_introduce);
        dinnerRecyclerviewImagesLayout = (LinearLayout) findView(
                R.id.dinner_recyclerview_images_layout);
        dinner_one_image_layout = findView(R.id.dinner_recyclerview_one_image_layout);
        dinner_two_image_layout = findView(R.id.dinner_recyclerview_two_image_layout);
        dinner_three_image_layout = findView(R.id.dinner_recyclerview_three_image_layout);
        dinner_one_image_introduce = findView(R.id.dinner_recyclerview_one_image_introduce);
        dinner_two_image_introduce = findView(R.id.dinner_recyclerview_two_image_introduce);
        dinner_three_image_introduce = findView(R.id.dinner_recyclerview_three_image_introduce);
        dinnerLocationTimeLayout = (LinearLayout) findView(R.id.dinner_location_time_layout);
        dinner_release_number_of_people = findView(R.id.dinner_release_number_of_people);
        dinner_release_location = findView(R.id.dinner_release_location);
        dinner_release_time = findView(R.id.dinner_release_time);
        dinnerAttention = (TextView) findView(
                R.id.dinner_attention);
        dinnerComment = (TextView) findView(
                R.id.dinner_comment);
        dinnerLike = (TextView) findView(
                R.id.dinner_like);
        releaseSuccessBackDinnerPartyDetails = (TextView) findView(
                R.id.release_success_back_dinner_party_details);
        releaseSuccessInviteFriendsCengRice = (TextView) findView(
                R.id.release_success_invite_friends_ceng_rice);
    }


    private void initListener() {
        releaseSuccessBackDinnerPartyDetails.setOnClickListener(this);
    }

    /**
     * 初始化沉浸式狀態欄
     */
    protected void initthem(int color) {
        // TODO Auto-generated method stub
        /**
         * 沉浸式状态栏
         */
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            // 状态栏透明 需要在创建SystemBarTintManager 之前调用。
            setTranslucentStatus(true);
        }

        SystemBarTintManager tintManager = new SystemBarTintManager(this);
        tintManager.setStatusBarTintEnabled(true);
        // 使StatusBarTintView 和 actionbar的颜色保持一致，风格统一。 设置沉浸式状态
        //tintManager.setStatusBarTintResource(R.color.white);
        tintManager.setStatusBarTintResource(color);
        // tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
        tintManager.setStatusBarDarkMode(false, this);
        // 设置状态栏的文字颜色
        tintManager.setStatusBarDarkMode(false, this);
    }

    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window win = getWindow();
            WindowManager.LayoutParams winParams = win.getAttributes();
            final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
            if (on) {
                winParams.flags |= bits;
            } else {
                winParams.flags &= ~bits;
            }
            win.setAttributes(winParams);
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.release_success_back_dinner_party_details:
                Intent intent=new Intent(this,DinnerPartyDetails.class);
                startActivity(intent);
                finish();
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;
        }

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_dinner_release_success_layout;
    }
}
