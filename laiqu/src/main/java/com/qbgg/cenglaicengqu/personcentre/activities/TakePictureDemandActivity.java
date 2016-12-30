package com.qbgg.cenglaicengqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;

/**
 * 1.6875倍
 * 实名制认证拍照要求
 */
public class TakePictureDemandActivity extends BaseActivity implements View.OnClickListener {
    private ImageView take_picture_demand_image;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //   AutoUtils.setSize(this, true, 1080, 1812);// 没有状态栏,设计尺寸的宽高
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_take_picture_demand_layout);
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
                overridePendingTransition(R.anim.activity_out, R.anim.activity_in);

            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initDate() {

    }

    private void initListener() {
        take_picture_demand_image.setOnClickListener(this);
    }

    private void initView() {
        take_picture_demand_image = findView(R.id.take_picture_demand_image);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.take_picture_demand_image:
                finish();
                overridePendingTransition(R.anim.activity_out, R.anim.activity_in);
                break;
            default:
                break;
        }
    }
}
