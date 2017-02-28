package com.fanqu.main.frgment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.fragment.BaseFragment;
import com.fanqu.framework.main.util.CommonUtil;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.Login;
import com.fanqu.main.acitvities.RecoveredPasswordAcitivty;
import com.fanqu.main.login.LoginRegisteredActivity;
import com.fanqu.main.widget.WaitProgressDialog;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;

import static com.fanqu.R.id.phone_edit_text;
import static com.fanqu.R.id.user_pass;


/**
 * 这里是登录的fragment
 */
public class LoginFragment extends BaseFragment implements View.OnClickListener, Handler.Callback {

    private LinearLayout loginLayout;
    private AppCompatEditText phoneEditText;
    private AppCompatEditText userPass;
    private TextView forginPass;
    private ImageView clear_pass;
    private TextView loginBtn;
    private TextView wechatLogin;
    private TextView qqLogin;
    private TextView weiboLogin;
    private Toolbar toolbar;
    private LoginRegisteredActivity activity;
    private Observer mReuseSubscriber;
    private String phonenumber, password;
    private Handler handler;
    private final int SHOWN_WAIT_PROGRESS_DIALOG = 1;
    private WaitProgressDialog dialog;

    public LoginFragment() {
        // Required empty public constructor
    }

    public LoginFragment(Observer mReuseSubscriber) {
        this.mReuseSubscriber = mReuseSubscriber;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_login_layout);
        handler = new Handler();
        assignViews();
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
        dialog = WaitProgressDialog.getInstance(activity);


        initListener();

    }

    /**
     * 初始化事件
     */
    private void initListener() {
        forginPass.setOnClickListener(this);
        loginBtn.setOnClickListener(this);
        clear_pass.setOnClickListener(this);
        forginPass.setOnClickListener(this);
        phoneEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {
                    if (!clear_pass.isShown()) {
                        clear_pass.setVisibility(View.VISIBLE);
                    }
                    if (editable.length() == 11) {
                        if (!CommonUtil.isMobile(editable.toString())) {
                            ToastUtils.showToast(activity, R.string.invalid_phone_number);
                            return;
                        } else {
                            if (!loginBtn.isEnabled()) {
                                loginBtn.setEnabled(true);

                            }
                        }
                    }
                } else {
                    clear_pass.setVisibility(View.GONE);
                }

            }
        });
        userPass.addTextChangedListener(new TextWatcher() {


            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {
                    phonenumber = phoneEditText.getText().toString();
                    if (!CommonUtil.isMobile(phonenumber)) {
                        ToastUtils.showToast(activity, R.string.invalid_phone_number);
                        return;
                    } else {
                        if (!loginBtn.isEnabled()) {
                            loginBtn.setEnabled(true);

                        }
                    }
                }
            }
        });
    }

    /**
     * 初始化view
     */
    private void assignViews() {
        loginLayout = (LinearLayout) findView(R.id.login_layout);
        phoneEditText = (AppCompatEditText) findView(phone_edit_text);
        userPass = (AppCompatEditText) findView(user_pass);
        forginPass = (TextView) findView(R.id.forgin_pass);
        clear_pass = (ImageView) findView(R.id.clear_pass);
        loginBtn = (TextView) findView(R.id.login_btn);
     //   wechatLogin =  findView(R.id.wechat_login);
//        qqLogin = (TextView) findView(R.id.qq_login);
//        weiboLogin = (TextView) findView(R.id.weibo_login);
        loginBtn.setEnabled(false);
        clear_pass.setVisibility(View.GONE);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.forgin_pass:
                // restitem(ForginpasswordActivity.class);
                if (CommonUtil.isFastDoubleClick()) {
                    return;
                } else {
                    Intent intent=new Intent(activity, RecoveredPasswordAcitivty.class);
                    LoginFragment.this.startActivity(intent);
                }

                break;
            case R.id.login_btn:
                //跳转到主页上面
                if (CommonUtil.isFastDoubleClick()) {
                    return;
                } else {
                    Dologining();
                }


                break;
            case R.id.clear_pass:
                phoneEditText.setText("");
                loginBtn.setEnabled(false);
                clear_pass.setVisibility(View.GONE);
                break;
            default:
                break;
        }
    }

    /**
     * 登录了
     */
    private void Dologining() {
        phonenumber = phoneEditText.getText().toString();
        if (!CommonUtil.isMobile(phonenumber)) {
            ToastUtils.showToast(activity, R.string.invalid_phone_number);
            return;
        } else {
            password = userPass.getEditableText().toString();
            if (!TextUtils.isEmpty(password)) {
                Login login = new Login();
                login.username = phonenumber;
                login.password = password;

                Observable.just(login)
                        .compose(LoginFragment.this.<Login>bindToLifecycle())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(mReuseSubscriber);
            }
        }
    }

    private void restitem(Class clazz) {
        Intent intent = new Intent(LoginFragment.this.getActivity(), clazz);
        LoginFragment.this.startActivity(intent);
        activity.overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    @Override
    public boolean handleMessage(Message message) {
        switch (message.what) {
            case SHOWN_WAIT_PROGRESS_DIALOG:


                break;
        }
        return false;
    }



}
