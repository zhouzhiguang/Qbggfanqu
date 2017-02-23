package com.fanqu.main.acitvities;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.Constants;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.CommonUtil;
import com.fanqu.framework.main.util.CountDownTimerUtils;
import com.fanqu.framework.main.util.LogUtil;
import com.fanqu.framework.main.util.PreferenceUitl;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.framework.rxbus.RxBus;
import com.fanqu.main.location.LoginRegisteredFactory;
import com.fanqu.main.login.LoginRegisteredActivity;
import com.fanqu.main.model.BindphoneEntity;
import com.fanqu.main.widget.ImitateIosAlertDialog;
import com.jakewharton.rxbinding.view.RxView;
import com.qbgg.network.request.nohttp.NohttpConfig;
import com.qbgg.network.request.nohttp.protocol.BaseStringProtocol;
import com.qbgg.network.request.nohttp.protocol.BeanRequestProtocol;
import com.yolanda.nohttp.RequestMethod;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;

/**
 * 绑定手机号码
 */
public class BindingMobilePhoneNumberActivity extends BaseActivity implements View.OnClickListener {

    private AppCompatEditText phone_edit_text, send_code_user_pass;
    private TextView send_code, complete_btn;
    private String phonenumber;

    private String messagecode,the3rd_uid;
    private ImitateIosAlertDialog dialog;
    private RxBus _rxBus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //  AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_binding_mobile_phone_number_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        Toolbar toolbar = getToolBar();
        _rxBus=getRxBusSingleton();
        dialog = new ImitateIosAlertDialog(this);
        dialog.builder();
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        //手机号码
        phone_edit_text = findView(R.id.phone_edit_text);
        //发送手机验证码
        send_code = findView(R.id.send_code);
        send_code_user_pass = findView(R.id.send_code_user_pass);
        complete_btn = findView(R.id.complete_btn);
        complete_btn.setEnabled(false);
    }

    private void initDate() {
         the3rd_uid = getIntent().getStringExtra("the3rd_uid");


    }

    private void initListener() {
        phone_edit_text.addTextChangedListener(new TextWatcher() {


                                                   @Override
                                                   public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                                   }

                                                   @Override
                                                   public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                                                   }

                                                   @Override
                                                   public void afterTextChanged(Editable editable) {
                                                       if (editable.length() == 11) {
                                                           phonenumber = editable.toString();
                                                           if (!CommonUtil.isMobile(phonenumber)) {

                                                               ToastUtils.showToast(BindingMobilePhoneNumberActivity.this, R.string.invalid_phone_number);
                                                               return;
                                                           } else {
                                                               messagecode = send_code_user_pass.getText().toString();
                                                               if (TextUtils.isEmpty(messagecode)) {
                                                                   complete_btn.setEnabled(false);
                                                               } else {
                                                                   complete_btn.setEnabled(true);
                                                               }

                                                           }
                                                       } else {
                                                           complete_btn.setEnabled(false);
                                                       }
                                                   }

                                               }

        );
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
                    phonenumber = phone_edit_text.getText().toString();
                    if (!CommonUtil.isMobile(phonenumber)) {
                        complete_btn.setEnabled(false);
                        ToastUtils.showToast(BindingMobilePhoneNumberActivity.this, R.string.invalid_phone_number);
                        return;
                    } else {
                        messagecode = editable.toString();
                        if (TextUtils.isEmpty(messagecode)) {
                            complete_btn.setEnabled(false);
                        } else {
                            complete_btn.setEnabled(true);
                        }
                    }
                } else {
                    complete_btn.setEnabled(false);
                }

            }
        });
        send_code.setOnClickListener(this);
        complete_btn.setOnClickListener(this);
//        notMoreClick(send_code);
        //notMoreClick(complete_btn);
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
                if (!TextUtils.isEmpty(phonenumber)) {
                    if (!CommonUtil.isMobile(phonenumber)) {
                        ToastUtils.showToast(BindingMobilePhoneNumberActivity.this, R.string.invalid_phone_number);
                        return;
                    } else {

                        getBindphonecode();
                        getauthcode();
                    }
                } else {
                    ToastUtils.showToast(BindingMobilePhoneNumberActivity.this, R.string.mport_phone_number_error);
                    return;
                }

                break;
            case R.id.complete_btn:
                if (CommonUtil.isFastDoubleClick()) {
                    return;
                } else {
                    phonenumber = phone_edit_text.getText().toString();
                    messagecode= send_code_user_pass.getText().toString();
                    //弹出Toast或者Dialog
                    ExecuteBindPhoneNumber();

                }

                break;
            default:
                break;
        }
    }

    private void ExecuteBindPhoneNumber() {
        BeanRequestProtocol beanrequesprotocol = new BeanRequestProtocol();
        String URL = LoginRegisteredFactory.getBindPhoneNumberUrl();
        Map<String, String> params = new HashMap<>();
        params.put("phone", phonenumber);
        params.put("code", messagecode);
        params.put("the3rd_uid", the3rd_uid);
        Observable<BindphoneEntity> observable = beanrequesprotocol.createObservable(URL, params, RequestMethod.POST, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE, BindphoneEntity.class);
        observable.compose(BindingMobilePhoneNumberActivity.this.<BindphoneEntity>bindToLifecycle())    //  (2)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<BindphoneEntity>() {
                    @Override
                    public void onCompleted() {
                        LogUtil.e("测试返回","onCompleted");

                    }

                    @Override
                    public void onError(Throwable e) {
                        // textView.setText(e.getMessage().toString());
                        LogUtil.e("测试返回","onError");
                        if (!TextUtils.isEmpty(e.getMessage().toString())){
                            show_Dialog(e.getMessage().toString());
                        }
                    }

                    @Override
                    public void onNext(BindphoneEntity entity) {

                        LogUtil.e("测试返回",entity.toString());
                        if (entity != null) {
                            int ErrorId = entity.getErrorId();
                            String ErrorDes = entity.getErrorDes();
                            BindphoneEntity.DataBean data = entity.getData();
                            if (ErrorId == 0) {
                                //登录成功
                                if (data != null) {
                                    if (_rxBus.hasObservers()) {    //是否有观察者，有，则发送一个事件
                                        _rxBus.send(new LoginRegisteredActivity.CloseEvent());
                                    }
                                    String uid = data.getUid();
                                    String token = data.getToken();
                                    //保存当前登录的信息
                                    PreferenceUitl.getInstance(BindingMobilePhoneNumberActivity.this).saveString(Constants.USER_ID, uid);
                                    PreferenceUitl.getInstance(BindingMobilePhoneNumberActivity.this).saveString(Constants.USER_TOKEN, token);
                                    jumpActivity(MainActivity.class);
                                    finish();
                                }

                            } else {
                                if (!TextUtils.isEmpty(ErrorDes)) {
                                    show_Dialog(ErrorDes);
                                }
                            }
                        }

                    }
                });

    }

    /**
     * 获取短信二维码
     */
    private void getBindphonecode() {
        String URL = LoginRegisteredFactory.getBindphoneMessagecodeUrl();
        BaseStringProtocol baseStringProtocol = new BaseStringProtocol();
        Map<String, String> params = new HashMap<>();
        params.put("phone", phonenumber);
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
                                //{"ErrorId":0,"ErrorDes":"","Data":""}
                                LogUtil.e("测试", s);
                                LogUtil.e("测试", ErrorId + "********+" + ErrorDes);
                                if (TextUtils.equals(ErrorId, "0")) {
                                    ToastUtils.showToast(BindingMobilePhoneNumberActivity.this, "获取成功了");
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

    private void getauthcode() {
        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(send_code, 60000, 1000);
        countDownTimerUtils.start();
    }

    private void show_Dialog(String errorDes) {
        if (dialog != null) {
            dialog.setMsg(errorDes)
                    .setNegativeButton(getString(R.string.ok), new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                        }
                    }).show();
        }
    }

    /**
     * 3秒内不允许按钮多次点击
     */
    private void notMoreClick(View view) {
        RxView.clicks(view)
                .throttleFirst(3, TimeUnit.SECONDS)
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {

                    }
                });
    }
}