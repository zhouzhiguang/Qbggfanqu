package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.CountDownTimerUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.main.widget.PayEditPasswordText;

/**
 * 修改交易密码
 */
public class ModificationTradersPasswordActivity extends BaseActivity implements View.OnClickListener {
    private LinearLayout activitySetingLayout;
    private Toolbar toolbar;
    private AppCompatEditText modificationTradersPasswordPhoneNumber;
    private AppCompatEditText sendCodeUserPass;
    private TextView sendCode;
    private PayEditPasswordText modificationTradersPasswordPayEditTextPay;
    private PayEditPasswordText modificationTradersPasswordensurePayEditTextPay;
    private TextView confirm;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_modification_traders_password_layout);
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
                finish();
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        activitySetingLayout = findView(R.id.activity_seting_layout);

        modificationTradersPasswordPhoneNumber = findView(R.id.modification_traders_password_phone_number);
        sendCodeUserPass = findView(R.id.send_code_user_pass);
        sendCode = findView(R.id.send_code);
        modificationTradersPasswordPayEditTextPay = findView(R.id.modification_traders_password_PayEditText_pay);
        modificationTradersPasswordensurePayEditTextPay = findView(R.id.modification_traders_passwordensure_PayEditText_pay);
        confirm = findView(R.id.confirm);

    }

    private void initDate() {

    }

    private void initListener() {
        confirm.setOnClickListener(this);
        sendCode.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.confirm:
                //确认修改
                break;
            case R.id.send_code:
                getauthcode();
                break;
            default:
                break;
        }
    }

    private void getauthcode() {
        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(sendCode, 30000, 1000);
        countDownTimerUtils.start();

    }
}
