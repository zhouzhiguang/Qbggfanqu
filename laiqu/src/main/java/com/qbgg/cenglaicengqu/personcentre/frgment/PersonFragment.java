package com.qbgg.cenglaicengqu.personcentre.frgment;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.fragment.BaseFragment;
import com.qbgg.cenglaicengqu.personcentre.activities.SetingActivity;

import de.hdodenhof.circleimageview.CircleImageView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PersonFragment extends BaseFragment implements View.OnClickListener {
    private Activity activity;
    private View view;
    private FrameLayout fragmentPersonLoggedHeadLayout;
    private FrameLayout fragmentPersonNotloggedHeadLayout;
    private TextView fragmentPersonWaitPay;//待支付
    private TextView fragmentPersonWaitOpenSeats;//待开席
    private TextView fragmentPersonWaitEvaluate;//待评价
    private TextView fragmentPersonAfterSale;//退款售后
    private LinearLayout fragmentPersonMineProperty;//我的资产
    private TextView accountsBalance;//账户余额
    private TextView accountsIntegral;//积分
    private TextView accountsCoupon;//优惠劵
    private TextView mineFocus;//我的关注
    private TextView log_out;//退出登录
    private ImageView fragment_person_seting;//设置
    private CircleImageView head_image;//头像
    private TextView fragment_person_name;//帐号名字
    private TextView fragment_person_vip_targ;//身份标识
    private TextView fragment_person_account_management;//帐号管理
    private TextView fragment_person_login_register;//还没有登录登录转登录注册
    private TextView real_name_authentication;//实名认证

    /**
     * 初始化view
     */
    private void assignViews() {

        fragmentPersonLoggedHeadLayout = findView(R.id.fragment_person_logged_head_layout);
        fragmentPersonNotloggedHeadLayout = findView(R.id.fragment_person_notlogged_head_layout);
        fragmentPersonWaitPay = findView(R.id.fragment_person_wait_pay);
        fragmentPersonWaitOpenSeats = (TextView) findView(R.id.fragment_person_wait_open_seats);
        fragmentPersonWaitEvaluate = (TextView) findView(R.id.fragment_person_wait_evaluate);
        fragmentPersonAfterSale = (TextView) findView(R.id.fragment_person_after_sale);
        fragmentPersonMineProperty = findView(R.id.fragment_person_mine_property);
        accountsBalance = (TextView) findView(R.id.accounts_balance);
        accountsIntegral = (TextView) findView(R.id.accounts_integral);
        accountsCoupon = (TextView) findView(R.id.accounts_coupon);
        mineFocus = (TextView) findView(R.id.mine_focus);
        real_name_authentication = findView(R.id.real_name_authentication);
        log_out = (TextView) findView(R.id.log_out);
        fragment_person_seting = findView(R.id.fragment_person_seting);
        head_image = findView(R.id.profile_image);
        fragment_person_name = findView(R.id.fragment_person_name);
        //setCompoundDrawables  设置drawableRight  drawableLeft等属性
        fragment_person_vip_targ = findView(R.id.fragment_person_vip_targ);
        fragment_person_account_management = findView(R.id.fragment_person_account_management);
        fragment_person_login_register = findView(R.id.fragment_person_login_register);
    }


    public PersonFragment() {
        // Required empty public constructor

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        //AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.fragment_person_layout);
        activity = getActivity();
        assignViews();

    }

    @Override
    protected void setListener() {
        initListener();
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onUserVisible() {

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    /**
     * 初始化点击事件
     */
    private void initListener() {
        fragment_person_seting.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fragment_person_seting:
                jumpactivity(SetingActivity.class);

                break;
            default:
                break;
        }

    }

    private void jumpactivity(Class clazz) {
        Intent intent = new Intent(activity, clazz);
        PersonFragment.this.startActivity(intent);
        activity.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }
}
