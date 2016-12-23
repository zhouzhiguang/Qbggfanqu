package com.qbgg.cenglaicengqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.acitvities.login.countdownUtils.CountDownTimerUtils;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;

/**
 * 绑定新的手机号码
 */
public class BoundPoneNewNumberActivity extends BaseActivity implements View.OnClickListener {
    private TextView send_code;
    private TextView bound_phone_confirm_new_number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_bound_phone_new_number_layout);
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
                ToastUtils.showCenterToast(BoundPoneNewNumberActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        send_code = findView(R.id.send_code);
        bound_phone_confirm_new_number = findView(R.id.bound_phone_confirm_new_number);

    }

    private void initDate() {

    }

    private void initListener() {
        send_code.setOnClickListener(this);
        bound_phone_confirm_new_number.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.send_code:
                getauthcode();
                break;
            case R.id.bound_phone_confirm_new_number:

                break;
            default:
                break;
        }
    }

    private void getauthcode() {
        CountDownTimerUtils countDownTimerUtils = new CountDownTimerUtils(send_code, 30000, 1000);
        countDownTimerUtils.start();

    }
}
