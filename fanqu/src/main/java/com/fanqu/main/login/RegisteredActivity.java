package com.fanqu.main.login;

/**
 * 注册主类
 */

import android.os.Bundle;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.model.ToolBarOptions;


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
