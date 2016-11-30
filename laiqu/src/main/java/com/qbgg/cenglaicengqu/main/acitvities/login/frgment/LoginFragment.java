package com.qbgg.cenglaicengqu.main.acitvities.login.frgment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.MainActivity;
import com.qbgg.cenglaicengqu.main.acitvities.login.LoginActivity;
import com.qbgg.cenglaicengqu.main.acitvities.login.activities.ForginpasswordActivity;
import com.qbgg.cenglaicengqu.main.acitvities.login.activities.RegisteredActivity;
import com.qbgg.cenglaicengqu.main.fragment.BaseFragment;


/**
 * 这里是登录的fragment
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener{

    private TextView loginLayoutTitle;
    private LinearLayout loginLayout;
    private AppCompatEditText phoneEditText;
    private AppCompatEditText userPass;
    private TextView forginPass;
    private ImageView showPass;
    private TextView loginBtn;
    private TextView registerBtn;
    private TextView wechatLogin;
    private TextView qqLogin;
    private TextView weiboLogin;
    private Toolbar toolbar;
    private LoginActivity activity;

    public LoginFragment() {
        // Required empty public constructor
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_login_layout);
    }

    @Override
    protected void setListener() {

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
        activity= (LoginActivity) getActivity();

        assignViews();
        initListener();


    }

    /**
     * 初始化事件
     */
    private void initListener() {
        registerBtn.setOnClickListener(this);
        forginPass.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    /**
     * 初始化view
     */
    private void assignViews() {
        toolbar = (Toolbar) findView(R.id.toolbar);
        loginLayoutTitle = (TextView) findView(R.id.login_layout_title);
        loginLayout = (LinearLayout) findView(R.id.login_layout);
        phoneEditText = (AppCompatEditText) findView(R.id.phone_edit_text);
        userPass = (AppCompatEditText) findView(R.id.user_pass);
        forginPass = (TextView) findView(R.id.forgin_pass);
        showPass = (ImageView) findView(R.id.show_pass);
        loginBtn = (TextView) findView(R.id.login_btn);
        registerBtn = (TextView) findView(R.id.register_btn);
        wechatLogin = (TextView) findView(R.id.wechat_login);
        qqLogin = (TextView) findView(R.id.qq_login);
        weiboLogin = (TextView) findView(R.id.weibo_login);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.forgin_pass:
                restitem(ForginpasswordActivity.class);
            break;
            case R.id.register_btn:
               restitem(RegisteredActivity.class);
            break;
            case R.id.login_btn:
                //跳转到主页上面
                restitem(MainActivity.class);
                activity.finish();
            break;

            default:
                break;
        }
    }

    private void restitem(Class clazz) {
        Intent intent=new Intent(LoginFragment.this.getActivity(),clazz);
        LoginFragment.this.startActivity(intent);
        activity.overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
    }
}
