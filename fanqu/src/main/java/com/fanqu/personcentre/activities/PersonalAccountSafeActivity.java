package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;

import de.hdodenhof.circleimageview.CircleImageView;


public class PersonalAccountSafeActivity extends BaseActivity implements View.OnClickListener {
    private TextView personal_account_safe_password, personal_account_safe_bound_mobile_phone;
    private TextView personal_account_safe_bound_mobile_phone_number;
    private CircleImageView personal_account_safe_WeChat;
    private TextView personal_account_safe_WeChat_nicename, personal_account_safe_WeChat_bound_state;
    private CircleImageView personal_account_safe_QQ;
    private TextView personal_account_safe_QQ_nicename, personal_account_safe_QQ_bound_state;
    private boolean wechatbound = false;//默认微信没有绑定帐号
    private boolean qqbound = false;//QQ默认qq没有绑定帐号

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_personal_account_safe_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        Toolbar toolbar = getToolBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showCenterToast(PersonalAccountSafeActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        personal_account_safe_password = findView(R.id.personal_account_safe_password);
        personal_account_safe_bound_mobile_phone = findView(R.id.personal_account_safe_bound_mobile_phone);
        //绑定的手机号码
        personal_account_safe_bound_mobile_phone_number = findView(R.id.personal_account_safe_bound_mobile_phone_number);
        //微信图标
        personal_account_safe_WeChat = findView(R.id.personal_account_safe_WeChat);
        //微信昵称
        personal_account_safe_WeChat_nicename = findView(R.id.personal_account_safe_WeChat_nicename);
        //微信绑定状态
        personal_account_safe_WeChat_bound_state = findView(R.id.personal_account_safe_WeChat_bound_state);
        //qq
        personal_account_safe_QQ = findView(R.id.personal_account_safe_QQ);
        //qq 昵称
        personal_account_safe_QQ_nicename = findView(R.id.personal_account_safe_QQ_nicename);
        //QQ绑定状态
        personal_account_safe_QQ_bound_state = findView(R.id.personal_account_safe_QQ_bound_state);
    }

    private void initDate() {
        wechatbound = true;
        setwechataccountstate(wechatbound);
        qqbound = false;
        setQQaccountstate(qqbound);
        //personal_account_safe_bound_mobile_phone_number

    }

    private void initListener() {
        personal_account_safe_password.setOnClickListener(this);
        personal_account_safe_bound_mobile_phone.setOnClickListener(this);


    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.personal_account_safe_password:
                //修改密码
                jumpActivity(ChangePasswordActivity.class);
                break;
            case R.id.personal_account_safe_bound_mobile_phone:
                //绑定手机号码
                jumpActivity(BoundMobilePoneActivity.class);
                break;
            default:
                break;
        }
    }

//    private void jumpActivity(Class clazz) {
//        Intent intent = new Intent(this, clazz);
//        startActivity(intent);
//        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
//    }

    /**
     * 设置微信绑定状态
     */
    private void setwechataccountstate(boolean wechatbound) {
        if (wechatbound) {
            //已经绑定
            personal_account_safe_WeChat.setImageResource(R.mipmap.ic_wechat_bound);
            personal_account_safe_WeChat_nicename.setVisibility(View.VISIBLE);
            //获取到的昵称
            personal_account_safe_WeChat_nicename.setText("王大锤");
            personal_account_safe_WeChat_bound_state.setTextColor(ContextCompat.getColor(PersonalAccountSafeActivity.this, R.color.textGrayLight));
            personal_account_safe_WeChat_bound_state.setText(R.string.is_binding);

            personal_account_safe_WeChat_bound_state.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(PersonalAccountSafeActivity.this, R.mipmap.ic_bound), null, null, null);
            personal_account_safe_WeChat_bound_state.setSelected(true);
        } else {
            //还未绑定帐号状态
            personal_account_safe_WeChat.setImageResource(R.mipmap.ic_wechat_no_bound);
            personal_account_safe_WeChat_nicename.setVisibility(View.GONE);
            personal_account_safe_WeChat_bound_state.setTextColor(ContextCompat.getColor(PersonalAccountSafeActivity.this, R.color.red_bg));
            personal_account_safe_WeChat_bound_state.setText(R.string.binding);
            //设置默认状态

            personal_account_safe_WeChat_bound_state.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(PersonalAccountSafeActivity.this, R.mipmap.ic_plus), null, null, null);
            personal_account_safe_WeChat_bound_state.setSelected(false);
        }
    }

    /**
     * @param qqbound aa帐号绑定状态
     */
    private void setQQaccountstate(boolean qqbound) {
        if (qqbound) {
            //已经绑定
            personal_account_safe_QQ.setImageResource(R.mipmap.ic_qq_bound);
            personal_account_safe_QQ_nicename.setVisibility(View.VISIBLE);
            //获取到的昵称
            personal_account_safe_QQ_nicename.setText("王大锤");
            personal_account_safe_QQ_bound_state.setTextColor(ContextCompat.getColor(PersonalAccountSafeActivity.this, R.color.textGrayLight));
            personal_account_safe_QQ_bound_state.setText(R.string.is_binding);

            personal_account_safe_QQ_bound_state.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(PersonalAccountSafeActivity.this, R.mipmap.ic_bound), null, null, null);
            personal_account_safe_QQ_bound_state.setSelected(true);
        } else {
            //还未绑定帐号状态
            personal_account_safe_QQ.setImageResource(R.mipmap.ic_qq_no_bound);
            personal_account_safe_QQ_nicename.setVisibility(View.GONE);
            personal_account_safe_QQ_bound_state.setTextColor(ContextCompat.getColor(PersonalAccountSafeActivity.this, R.color.red_bg));
            personal_account_safe_QQ_bound_state.setText(R.string.binding);
            //设置默认状态

            personal_account_safe_QQ_bound_state.setCompoundDrawablesWithIntrinsicBounds(ContextCompat.getDrawable(PersonalAccountSafeActivity.this, R.mipmap.ic_plus), null, null, null);
            personal_account_safe_QQ_bound_state.setSelected(false);
        }
    }

}
