package com.qbgg.cenglaicengqu.personcentre.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;

/**
 * 输入新的手机号
 */
public class BoundMobilePoneActivity extends BaseActivity implements View.OnClickListener {
    private TextView bound_mobile_phone_current_number;
    private EditText bound_mobile_phone_security_code;
    private TextView bound_mobile_phone_send_security_code, bound_mobile_phone_nextstep;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_bound_mobile_pone_layout);
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
                ToastUtils.showCenterToast(BoundMobilePoneActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        bound_mobile_phone_current_number = findView(R.id.bound_mobile_phone_current_number);
        //输入的验证码
        bound_mobile_phone_security_code = findView(R.id.bound_mobile_phone_security_code);
        //发送验证码
        bound_mobile_phone_send_security_code = findView(R.id.bound_mobile_phone_send_security_code);
        bound_mobile_phone_nextstep = findView(R.id.bound_mobile_phone_nextstep);

    }

    private void initDate() {
        String phonestring = getString(R.string.bound_mobile_pone_number);
        String number = "18000000000";
        String n = number.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
        StringBuilder builder = new StringBuilder();
        builder.append(phonestring);
        builder.append(n);
        bound_mobile_phone_current_number.setText(builder.toString());

    }

    private void initListener() {
        bound_mobile_phone_send_security_code.setOnClickListener(this);
        bound_mobile_phone_nextstep.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bound_mobile_phone_send_security_code:
                break;
            case R.id.bound_mobile_phone_nextstep:
                //下一步
                Intent intent=new Intent(this,BoundPoneNumberActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;
            default:
                break;
        }
    }
}
