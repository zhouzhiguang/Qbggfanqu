package com.qbgg.cenglaicengqu.main.acitvities.login.activities;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.acitvities.login.countdownUtils.CountDownTimerUtils;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;

public class ForginpasswordActivity extends BaseActivity implements View.OnClickListener {
    private TextView layout_title;
    private LinearLayout activityForginpasswordLayout;

    private LinearLayout loginLayout;
    private AppCompatEditText phoneEditText;
    private AppCompatEditText sendCodeUserPass;
    private TextView sendCode;
    private AppCompatEditText userPass;
    private ImageView showPass;
    private TextView completeBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forginpassword_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        layout_title = findView(R.id.layout_title);
        layout_title.setText(R.string.recovered_password);
        initView();
        initListener();
    }




    private void initView() {
        activityForginpasswordLayout = (LinearLayout) findViewById(R.id.activity_forginpassword_layout);

        loginLayout = (LinearLayout) findViewById(R.id.login_layout);
        phoneEditText = (AppCompatEditText) findViewById(R.id.phone_edit_text);
        sendCodeUserPass = (AppCompatEditText) findViewById(R.id.send_code_user_pass);
        sendCode = (TextView) findViewById(R.id.send_code);
        userPass = (AppCompatEditText) findViewById(R.id.user_pass);
        showPass = (ImageView) findViewById(R.id.show_pass);
        completeBtn = (TextView) findViewById(R.id.complete_btn);
    }

    /**
     * 初始化监听
     */
    private void initListener() {
        sendCode.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }



    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.send_code:
                getauthcode();
            break;
            default:
                break;
        }
    }

    private void getauthcode() {
        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(sendCode, 30000, 1000);
        countDownTimerUtils .start();
    }


}
