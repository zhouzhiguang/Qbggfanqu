package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.main.widget.ClearEditText;


public class DateNicknameActivity extends BaseActivity implements View.OnClickListener {
    private TextView save;
    private ClearEditText date_nickname_edittext;
    private LinearLayout activity_date_nickname_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_date_nickname_layout);
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
                DateNicknameActivity.this.overridePendingTransition(R.anim.activity_out, R.anim.activity_in);
                ToastUtils.showCenterToast(DateNicknameActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        save = findView(R.id.save);
        date_nickname_edittext = findView(R.id.date_nickname_edittext);
        activity_date_nickname_layout = findView(R.id.activity_date_nickname_layout);
    }

    private void initDate() {
        //save.requestFocus();
        //save.setFocusable(false);
    }

    private void initListener() {
//        save.setOnClickListener(this);
//        date_nickname_edittext.setFocusable(true);
//        date_nickname_edittext.requestFocus();
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.save:
                String nickname = date_nickname_edittext.getText().toString();
                if (!TextUtils.isEmpty(nickname)) {

                }
                break;
            default:
                break;
        }
    }
}
