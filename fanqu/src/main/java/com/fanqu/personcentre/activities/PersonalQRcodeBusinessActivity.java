package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;

import de.hdodenhof.circleimageview.CircleImageView;


/**
 * 二维码名片页面
 */
public class PersonalQRcodeBusinessActivity extends BaseActivity {
    private CircleImageView personal_qrcode_business_headimage;//头像
    private TextView personal_qrcode_business_nicename;//昵称
    private TextView personal_qrcode_business_identity;//身份标识
    private ImageView personal_qrcode_business_image;//正真 的二维码

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_personal_qrcode_business_layout);
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
                ToastUtils.showCenterToast(PersonalQRcodeBusinessActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        personal_qrcode_business_headimage = findView(R.id.personal_qrcode_business_headimage);
        personal_qrcode_business_nicename = findView(R.id.personal_qrcode_business_nicename);
        personal_qrcode_business_identity = findView(R.id.personal_qrcode_business_identity);
        personal_qrcode_business_image = findView(R.id.personal_qrcode_business_image);
    }

    private void initDate() {

    }

    private void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
