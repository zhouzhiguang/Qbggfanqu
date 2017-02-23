package com.fanqu.main.frgment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.fragment.BaseFragment;
import com.fanqu.framework.main.util.CommonUtil;
import com.fanqu.framework.main.util.CountDownTimerUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.Login;
import com.fanqu.main.location.LoginRegisteredFactory;
import com.fanqu.main.widget.ImitateIosAlertDialog;
import com.qbgg.network.request.nohttp.NohttpConfig;
import com.qbgg.network.request.nohttp.protocol.BaseStringProtocol;
import com.yolanda.nohttp.RequestMethod;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Observer;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 注册fragment
 */

public class RegisteredFragment extends BaseFragment implements Handler.Callback, View.OnClickListener {
    private AppCompatEditText phone_edit_text, send_code_user_pass, user_pass;
    private ImageView clear_pass;
    private TextView send_code, complete_btn;
    private Handler handler;
    private Activity activity;
    private String registeredsucceed = "0";
    private ImitateIosAlertDialog dialog;
    boolean isregisteredsucceed = false, isregister = false;
    private String phone_verification_code, password, phonenumber;
    private Observer mReuseSubscriber;

    public RegisteredFragment() {
    }

    public RegisteredFragment(Observer mReuseSubscriber) {
        this.mReuseSubscriber = mReuseSubscriber;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_registered_layout);
        handler = new Handler();
        activity = getActivity();
        dialog = new ImitateIosAlertDialog(activity);
        dialog.builder();
        initview();
    }

    @Override
    protected void setListener() {
        send_code.setOnClickListener(this);
        clear_pass.setOnClickListener(this);
        complete_btn.setOnClickListener(this);
        send_code_user_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {
                    examinecondition();
                } else {
                    complete_btn.setEnabled(false);
                }
            }
        });
        user_pass.addTextChangedListener(new TextWatcher() {
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
                    examinecondition();
                } else {
                    if (clear_pass.isShown()) {
                        clear_pass.setVisibility(View.GONE);
                    }
                }
            }
        });
    }

    /**
     * 检查 最后输入条件时候
     */
    private void examinecondition() {
        phonenumber = phone_edit_text.getText().toString();
        if (!CommonUtil.isMobile(phonenumber)) {
            ToastUtils.showToast(activity, R.string.invalid_phone_number);
            return;
        } else {
            phone_verification_code = send_code_user_pass.getText().toString();
            password = user_pass.getText().toString();
            if (!TextUtils.isEmpty(phone_verification_code) && !TextUtils.isEmpty(password)) {
                complete_btn.setEnabled(true);
            }
        }
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onUserVisible() {

    }

    private void initview() {
        clear_pass = findView(R.id.clear_pass);
        complete_btn = findView(R.id.complete_btn);
        phone_edit_text = findView(R.id.phone_edit_text);
        send_code = findView(R.id.send_code);
        user_pass = findView(R.id.user_pass);
        //请输入验证码
        send_code_user_pass = findView(R.id.send_code_user_pass);
        complete_btn.setEnabled(false);

    }


    @Override
    public boolean handleMessage(Message message) {

        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_code:
                phonenumber = phone_edit_text.getText().toString();
                if (!CommonUtil.isMobile(phonenumber)) {
                    ToastUtils.showToast(activity, R.string.invalid_phone_number);
                    return;
                } else {
                    getauthcode();
                    isregister = false;
                    getmobilephoneauthcodeorregister(isregister);

//                .subscribe(new Action1<String>() {          //  (4)
//
//                    @Override
//                    public void call(String data) {         //  (5)
//
//                        textView.setText(data+"-============================");
//                    }
//                }, new Action1<Throwable>() {
//                    @Override
//                    public void call(Throwable throwable) { //  (6)
//                        LogUtil.e("Get Result:\r\n" , throwable.getMessage());
//                    }
//                });
                }

                break;
            case R.id.clear_pass:
                user_pass.setText("");
                complete_btn.setEnabled(false);
                break;

            case R.id.complete_btn:
                phonenumber = phone_edit_text.getText().toString();
                if (!CommonUtil.isMobile(phonenumber)) {
                    ToastUtils.showToast(activity, R.string.invalid_phone_number);
                    return;
                } else {
                    phone_verification_code = send_code_user_pass.getText().toString();
                    password = user_pass.getText().toString();
                    if (!TextUtils.isEmpty(phone_verification_code) && !TextUtils.isEmpty(password)) {
                        complete_btn.setEnabled(true);
                        registerphonenumber();
                    }
                }

                break;
            default:
                break;
        }
    }

    /**
     * 注册手机号码了
     */
    private void registerphonenumber() {
        isregister = true;
        getmobilephoneauthcodeorregister(isregister);

    }

    private void getauthcode() {
        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(send_code, 60000, 1000);
        countDownTimerUtils.start();
    }

    public void getmobilephoneauthcodeorregister(final boolean isregister) {

        String URL = LoginRegisteredFactory.getRegisteredphoneverificationcodeUrl();
        BaseStringProtocol baseStringProtocol = new BaseStringProtocol();
        Map<String, String> params = new HashMap<>();
        params.put("phone", phonenumber);
        if (isregister) {
            params.put("pwd", password);
            params.put("code", phone_verification_code);
            URL = LoginRegisteredFactory.getRegisteredUrl();
        }
        Observable<String> observable = baseStringProtocol.createObservable(URL, params, RequestMethod.POST, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE);
        observable.compose(this.<String>bindToLifecycle())    //  (2)
                .observeOn(AndroidSchedulers.mainThread())  //  (3)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        String error = getString(R.string.error);
                        show_Dialog(error);
                    }

                    @Override
                    public void onNext(String s) {
                        if (!TextUtils.isEmpty(s)) {
                            try {
                                JSONObject object = new JSONObject(s);
                                String ErrorId = object.getString("ErrorId");
                                String ErrorDes = object.getString("ErrorDes");
                                if (object.has("Data")) {
                                    String Data = object.getString("Data");

                                    if (isregister) {
                                        object = new JSONObject(Data);
                                        if (object.has("uid")) {
                                            //用户注册的usid
                                            String uid = object.getString("uid");
                                            if (!TextUtils.isEmpty(uid)) {
                                                String register_succeed = getString(R.string.register_succeed);
                                                isregisteredsucceed=true;
                                                show_Dialog(register_succeed);
                                            }else {
                                                isregisteredsucceed=false;
                                            }
                                        }
                                    }
                                }
                                if (TextUtils.equals(ErrorId, registeredsucceed)) {
                                    if (isregister) {
                                        //注册成功了
                                    }
                                } else {
                                    show_Dialog(ErrorDes);
                                }
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                });
    }


    private void show_Dialog(String errorDes) {
        if (dialog != null) {
            dialog.setMsg(errorDes)
                    .setNegativeButton(getString(R.string.ok), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if (isregister && isregisteredsucceed) {
                                Login login=new Login();
                                login.username=phonenumber;
                                login.password=password;
                                Observable.just(login)
                                        .compose(RegisteredFragment.this.<Login>bindToLifecycle())
                                        .observeOn(AndroidSchedulers.mainThread())
                                        .subscribe(mReuseSubscriber);
                            }
                        }
                    }).show();
        }
    }
}