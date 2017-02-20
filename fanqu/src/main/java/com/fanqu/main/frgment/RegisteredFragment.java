package com.fanqu.main.frgment;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.fragment.BaseFragment;
import com.fanqu.framework.main.util.CommonUtil;
import com.fanqu.framework.main.util.CountDownTimerUtils;
import com.fanqu.framework.main.util.LogUtil;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.main.location.LoginRegisteredFactory;
import com.qbgg.network.request.nohttp.NohttpConfig;
import com.qbgg.network.request.nohttp.protocol.BaseStringProtocol;
import com.yolanda.nohttp.RequestMethod;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;

/**
 * 注册fragment
 */

public class RegisteredFragment extends BaseFragment implements Handler.Callback, View.OnClickListener {
    private AppCompatEditText phone_edit_text;
    private ImageView show_pass;
    private TextView send_code;
    private Handler handler;
    private Activity activity;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.activity_registered_layout);
        handler = new Handler();
        activity = getActivity();
        initview();
    }

    @Override
    protected void setListener() {
        send_code.setOnClickListener(this);
        phone_edit_text.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (editable != null && editable.length() > 0) {


                }
            }
        });
    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onUserVisible() {

    }

    private void initview() {
        phone_edit_text = findView(R.id.phone_edit_text);
        send_code = findView(R.id.send_code);
    }


    @Override
    public boolean handleMessage(Message message) {

        return false;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_code:
                String phonenumber = phone_edit_text.getText().toString();
                if (!CommonUtil.isMobile(phonenumber)) {
                    ToastUtils.showToast(activity, R.string.invalid_phone_number);
                    return;
                } else {
                    getauthcode();
                    getmobilephoneauthcode(phonenumber);

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
            default:
                break;
        }
    }

    private void getauthcode() {
        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(send_code, 30000, 1000);
        countDownTimerUtils.start();
    }

    public void getmobilephoneauthcode(String phonenumber) {
        String URL = LoginRegisteredFactory.getRegisteredUrl();
        BaseStringProtocol baseStringProtocol = new BaseStringProtocol();
        Map<String, String> params =new HashMap<>();
        params.put("phone",phonenumber);
        Observable<String> observable = baseStringProtocol.createObservable(URL, params, RequestMethod.POST, NohttpConfig.NOHTTP_CACHEMODE_NETWORK_FAILED_READ_CACHE);
        observable.compose(this.<String>bindToLifecycle())    //  (2)
                .observeOn(AndroidSchedulers.mainThread())  //  (3)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        LogUtil.e("测试数据",s);
                    }
                });
    }
}
