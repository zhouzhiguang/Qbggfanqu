package com.qbgg.cenglaicengqu.personcentre.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.CommonUtil;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.widget.ActionSheetDialog;

/**
 * 关于蹭范趣
 */
public class AboutFanquActivity extends BaseActivity implements View.OnClickListener {
    private TextView fanqu_versions, about_fanqu_feedback;
    private TextView About_Us, service_hotline;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_about_fanqu_layout);
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
//        Toolbar toolbar = getToolBar();
//
//        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                ToastUtils.showCenterToast(AboutFanquActivity.this, "点击返回了哦");
//            }
//        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        service_hotline = findView(R.id.service_hotline);
        About_Us = findView(R.id.About_Us);
        about_fanqu_feedback = findView(R.id.about_fanqu_feedback);
        fanqu_versions = findView(R.id.fanqu_versions);

    }

    private void initDate() {
        fanqu_versions.setText(CommonUtil.getVersion(getApplicationContext()));
    }

    private void initListener() {
        About_Us.setOnClickListener(this);
        about_fanqu_feedback.setOnClickListener(this);
        service_hotline.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.about_fanqu_feedback:
                Intent intent = new Intent(this, FeedBackActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case R.id.About_Us:
                break;
            case R.id.service_hotline:
                //服务热线
                new ActionSheetDialog(AboutFanquActivity.this)
                        .builder()
                        .setTitle(getString(R.string.hotline_tips))
                        .setTitleColor(R.color.dinner_recyclerview_attention_color)
                        .setTitleLayoutParams(0, getResources().getDimension(R.dimen.dimen_150px))
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem(getString(R.string.hotline), ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //拨打热线了
                                    }
                                }).show();
                break;
            default:
                break;
        }
    }
}
