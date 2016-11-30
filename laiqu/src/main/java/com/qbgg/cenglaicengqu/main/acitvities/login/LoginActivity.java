package com.qbgg.cenglaicengqu.main.acitvities.login;

import android.os.Bundle;
import android.widget.FrameLayout;

import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.acitvities.login.frgment.LoginFragment;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;

public class LoginActivity extends BaseActivity {
    private FrameLayout login_container;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_login_layout;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_layout);
        AutoUtils.auto(this);
        initview();
    }

    private void initview() {
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_login, new LoginFragment()).commit();
    }


}
