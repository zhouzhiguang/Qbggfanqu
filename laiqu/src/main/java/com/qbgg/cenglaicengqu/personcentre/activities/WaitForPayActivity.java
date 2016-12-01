package com.qbgg.cenglaicengqu.personcentre.activities;

import android.os.Bundle;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;
import com.qbgg.cenglaicengqu.main.widget.TimeViewComm;
import com.qbgg.cenglaicengqu.main.widgethelper.OnTimeoutListener;

public class WaitForPayActivity extends BaseActivity {
    private TimeViewComm wait_for_pay_timeview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_wait_for_pay_layout);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId=R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        initView();
    }

    /**初始化view
     *
     */
    private void initView() {
        wait_for_pay_timeview=findView(R.id.wait_for_pay_timeview);
        wait_for_pay_timeview.startTime(0,10,00);
        wait_for_pay_timeview.addTimeoutPoint(0,0,0);
        wait_for_pay_timeview.setOnTimeoutListener(new OnTimeoutListener() {
            @Override
            public void onTimePoint(String hour, String minute, String second) {
               // ToastUtils.showCenterToast(WaitForPayActivity.this,"超时了 "+second);
            }

            @Override
            public void onTimeout() {
                ToastUtils.showCenterToast(WaitForPayActivity.this,"超时了没有了");
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
