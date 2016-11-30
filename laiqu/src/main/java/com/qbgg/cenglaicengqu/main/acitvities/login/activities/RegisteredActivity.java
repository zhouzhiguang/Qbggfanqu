package com.qbgg.cenglaicengqu.main.acitvities.login.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;

public class RegisteredActivity extends BaseActivity {

    private TextView layout_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registered_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        layout_title = findView(R.id.layout_title);
        layout_title.setText(R.string.quick_register_account);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
