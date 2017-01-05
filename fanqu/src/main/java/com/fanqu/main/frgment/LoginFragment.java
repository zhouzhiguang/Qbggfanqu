package com.fanqu.main.frgment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.fragment.BaseFragment;
import com.fanqu.main.acitvities.MainActivity;
import com.fanqu.main.login.LoginRegisteredActivity;


/**
 * 这里是登录的fragment
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener {

    private LinearLayout loginLayout;
    private AppCompatEditText phoneEditText;
    private AppCompatEditText userPass;
    private TextView forginPass;
    private ImageView showPass;
    private TextView loginBtn;
    private TextView wechatLogin;
    private TextView qqLogin;
    private TextView weiboLogin;
    private Toolbar toolbar;
    private LoginRegisteredActivity activity;

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
        activity = (LoginRegisteredActivity) getActivity();

        assignViews();
        initListener();


    }

    /**
     * 初始化事件
     */
    private void initListener() {
        forginPass.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
    }

    /**
     * 初始化view
     */
    private void assignViews() {
        toolbar = (Toolbar) findView(R.id.toolbar);

        loginLayout = (LinearLayout) findView(R.id.login_layout);
        phoneEditText = (AppCompatEditText) findView(R.id.phone_edit_text);
        userPass = (AppCompatEditText) findView(R.id.user_pass);
        forginPass = (TextView) findView(R.id.forgin_pass);
        showPass = (ImageView) findView(R.id.show_pass);
        loginBtn = (TextView) findView(R.id.login_btn);

        wechatLogin = (TextView) findView(R.id.wechat_login);
        qqLogin = (TextView) findView(R.id.qq_login);
        weiboLogin = (TextView) findView(R.id.weibo_login);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgin_pass:
               // restitem(ForginpasswordActivity.class);
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
        Intent intent = new Intent(LoginFragment.this.getActivity(), clazz);
        LoginFragment.this.startActivity(intent);
        activity.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }
}
