package com.qbgg.cenglaicengqu.personcentre.activities;

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
 * 修改密码界面
 */
public class ChangePasswordActivity extends BaseActivity implements View.OnClickListener {
    private EditText change_password_edit_current_password, change_password_edit_new_password, change_password_edit_confirm_new_password;
    private TextView change_password_confirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_change_password_layout);
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
                ToastUtils.showCenterToast(ChangePasswordActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        //当前密码
        change_password_edit_current_password = findView(R.id.change_password_edit_current_password);
        //新密码
        change_password_edit_new_password = findView(R.id.change_password_edit_new_password);
        //确认新密码
        change_password_edit_confirm_new_password = findView(R.id.change_password_edit_confirm_new_password);
        //确定
        change_password_confirm = findView(R.id.change_password_confirm);
    }

    private void initDate() {

    }

    private void initListener() {
        change_password_confirm.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.change_password_confirm:
                //确认
            break;
            default:

                break;
        }

    }
}
