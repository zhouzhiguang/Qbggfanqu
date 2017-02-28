package com.fanqu.main.acitvities;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.CommonUtil;
import com.fanqu.framework.main.util.CountDownTimerUtils;
import com.fanqu.framework.main.util.LogUtil;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.main.location.LoginRegisteredFactory;
import com.fanqu.main.widget.ImitateIosAlertDialog;
import com.qbgg.network.request.nohttp.NohttpConfig;
import com.qbgg.network.request.nohttp.protocol.BaseStringProtocol;
import com.yolanda.nohttp.RequestMethod;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 找回密码
 * activity_recovered_password_layout
 */
public class RecoveredPasswordAcitivty extends BaseActivity implements View.OnClickListener {
    private AppCompatEditText phone_edit_text, send_code_user_pass, user_pass, user_pass_affirm;
    private TextView send_code, complete_btn;
    private String phonenumber, massageauthcode, password, affirmpassword;
    private ImitateIosAlertDialog dialog;
    private String succeed = "0";
    private ImageView clear_pass;
    private boolean isreset_pass = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //  AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_recovered_password_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        Toolbar toolbar = getToolBar();
        dialog = new ImitateIosAlertDialog(this);
        dialog.builder();

        initView();
        initDate();
        initListener();
    }

    private void initView() {
        clear_pass = findView(R.id.clear_pass);
        user_pass_affirm = findView(R.id.user_pass_affirm);
        user_pass = findView(R.id.user_pass);
        phone_edit_text = findView(R.id.phone_edit_text);
        send_code_user_pass = findView(R.id.send_code_user_pass);
        send_code = findView(R.id.send_code);
        complete_btn = findView(R.id.complete_btn);
        complete_btn.setEnabled(false);
    }

    private void initDate() {

    }

    private void initListener() {
        complete_btn.setOnClickListener(this);
        send_code.setOnClickListener(this);
        clear_pass.setOnClickListener(this);
        user_pass_affirm.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                phonenumber = phone_edit_text.getText().toString();
                if (!TextUtils.isEmpty(phonenumber) && !CommonUtil.isMobile(phonenumber)) {
                    ToastUtils.showToast(RecoveredPasswordAcitivty.this, R.string.invalid_phone_number);
                    complete_btn.setEnabled(false);
                    return;
                } else {
                    if (editable.length() > 0)
                        verifyInput();
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
                phonenumber = phone_edit_text.getText().toString();
                if (!TextUtils.isEmpty(phonenumber) && !CommonUtil.isMobile(phonenumber)) {
                    ToastUtils.showToast(RecoveredPasswordAcitivty.this, R.string.invalid_phone_number);
                    complete_btn.setEnabled(false);
                    return;
                } else {
                    if (editable.length() > 0)
                        verifyInput();
                }
            }
        });
        send_code_user_pass.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null) {
                    phonenumber = phone_edit_text.getText().toString();
                    if (!TextUtils.isEmpty(phonenumber) && !CommonUtil.isMobile(phonenumber)) {
                        ToastUtils.showToast(RecoveredPasswordAcitivty.this, R.string.invalid_phone_number);
                        complete_btn.setEnabled(false);
                        return;
                    } else {
                        verifyInput();
                    }
                }

            }
        });
        phone_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable.length() > 0) {
                    clear_pass.setVisibility(View.VISIBLE);
                } else {
                    clear_pass.setVisibility(View.GONE);
                }
                phonenumber = editable.toString();
                if (phonenumber.length() == 11 && !CommonUtil.isMobile(phonenumber)) {
                    ToastUtils.showToast(RecoveredPasswordAcitivty.this, R.string.invalid_phone_number);
                    complete_btn.setEnabled(false);
                    return;
                } else {
                    verifyInput();
                }

            }


        });
    }

    private void verifyInput() {
        phonenumber = phone_edit_text.getText().toString();
        massageauthcode = send_code_user_pass.getText().toString();
        password = user_pass.getText().toString();
        affirmpassword = user_pass_affirm.getText().toString();

        if (massageauthcode != null && massageauthcode.length() > 0 && password != null && password.length() > 0 && affirmpassword != null && affirmpassword.length() > 0) {
            complete_btn.setEnabled(true);
            LogUtil.e("是手机号码", "是手机");
        } else {
            complete_btn.setEnabled(false);
        }
        ;
    }


    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_code:
                phonenumber = phone_edit_text.getText().toString();
                if (TextUtils.isEmpty(phonenumber)) {
                    ToastUtils.showToast(RecoveredPasswordAcitivty.this, R.string.phone_number_empty);
                } else {
                    if (!CommonUtil.isMobile(phonenumber)) {
                        ToastUtils.showToast(RecoveredPasswordAcitivty.this, R.string.invalid_phone_number);
                    } else {
                        getauthcode();
                        ResetPwdRequest(false);
                    }
                }
                break;
            case R.id.clear_pass:
                phone_edit_text.setText("");
                complete_btn.setEnabled(false);
                break;
            case R.id.complete_btn:
                verifyInput();
                if (TextUtils.equals(password, affirmpassword)) {
                    ResetPwdRequest(true);
                    return;
                } else {
                    ToastUtils.showToast(this, R.string.twice_import_password_unlike);
                }
                break;
            default:
                break;
        }
    }

    private class EditTextChangedListener implements TextWatcher {

        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

        }

        @Override
        public void afterTextChanged(Editable editable) {

        }

    }

    private void getauthcode() {
        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(send_code, 60000, 1000);
        countDownTimerUtils.start();
    }

    public void ResetPwdRequest(final boolean reset_pass) {

        String URL = LoginRegisteredFactory.getResetPwdMessageAuthenticationCodeUrl();
        BaseStringProtocol baseStringProtocol = new BaseStringProtocol();
        Map<String, String> params = new HashMap<>();
        params.put("phone", phonenumber);
        if (reset_pass) {
            params.put("pwd1", password);
            params.put("code", massageauthcode);
            params.put("pwd2", affirmpassword);
            URL = LoginRegisteredFactory.getResetPassUrl();
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
                        isreset_pass = false;
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

                                }
                                if (TextUtils.equals(ErrorId, succeed)) {
                                    if (reset_pass) {
                                        String reset_succeed = getString(R.string.reset_succeed);
                                        show_Dialog(reset_succeed);
                                        isreset_pass = true;
                                    }
                                } else {
                                    isreset_pass = false;
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
                            if (isreset_pass) {
                                finish();
                                overridePendingTransition(R.anim.activity_out, R.anim.activity_in);
                            }

                        }
                    }).show();
        }
    }
}
