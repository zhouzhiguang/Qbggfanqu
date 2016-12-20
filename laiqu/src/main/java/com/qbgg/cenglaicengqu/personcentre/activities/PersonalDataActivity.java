package com.qbgg.cenglaicengqu.personcentre.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.netease.nim.uikit.common.ui.imageview.CircleImageView;
import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;
import com.qbgg.cenglaicengqu.main.widget.ActionSheetDialog;
import com.qbgg.cenglaicengqu.personcentre.dialog.BirthDayPickerDialog;
import com.qbgg.cenglaicengqu.personcentre.dialog.CityPickerDialog;
import com.qbgg.cenglaicengqu.personcentre.dialog.UploadPictureDialog;

/**
 * 个人资料中心
 */
public class PersonalDataActivity extends BaseActivity implements View.OnClickListener {
    private CircleImageView personal_data_head_image;
    private TextView personal_data_head_portrait, personal_data_nickname, personal_data_area_text, personal_data_area, personal_data_sex_text;
    private TextView personal_data_birthday_text, personal_data_personalized_signature, personal_data_QRCode_text;
    private TextView personal_data_taste_preference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        setContentView(R.layout.activity_personal_data_layout);
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
                ToastUtils.showCenterToast(PersonalDataActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        personal_data_head_image = findView(R.id.personal_data_head_image);
        personal_data_head_portrait = findView(R.id.personal_data_head_portrait);
        personal_data_nickname = findView(R.id.personal_data_nickname);
        personal_data_area_text = findView(R.id.personal_data_area_text);
        personal_data_area = findView(R.id.personal_data_area);
        personal_data_sex_text = findView(R.id.personal_data_sex_text);
        personal_data_birthday_text = findView(R.id.personal_data_birthday_text);
        personal_data_personalized_signature = findView(R.id.personal_data_personalized_signature);
        personal_data_QRCode_text = findView(R.id.personal_data_QRCode_text);
        personal_data_taste_preference=findView(R.id.personal_data_taste_preference);

    }

    private void initDate() {

    }

    private void initListener() {
        personal_data_head_portrait.setOnClickListener(this);
        personal_data_nickname.setOnClickListener(this);
        personal_data_area_text.setOnClickListener(this);
        personal_data_sex_text.setOnClickListener(this);
        personal_data_birthday_text.setOnClickListener(this);
        personal_data_personalized_signature.setOnClickListener(this);
        personal_data_QRCode_text.setOnClickListener(this);
        personal_data_taste_preference.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch (view.getId()) {
            case R.id.personal_data_head_portrait:
                UploadPictureDialog dialog = new UploadPictureDialog(PersonalDataActivity.this, R.style.CustomDatePickerDialog);
                dialog.show();

                break;
            case R.id.personal_data_nickname:
                //设置昵称
                intent = new Intent(this, DateNicknameActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case R.id.personal_data_area_text:
                CityPickerDialog cityPickerDialog = new CityPickerDialog(this, R.style.CustomcityPickerDialog);
                cityPickerDialog.show();
                break;
            case R.id.personal_data_sex_text:
//                SexPickerDialog sexPickerDialog = new SexPickerDialog(this, R.style.CustomcityPickerDialog);
//                sexPickerDialog.show();
                new ActionSheetDialog(this)
                        .builder()
                        .setCancelable(true)
                        .setCanceledOnTouchOutside(true)
                        .addSheetItem(getString(R.string.male), ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //男点击
                                        ToastUtils.showCenterToast(PersonalDataActivity.this, which + "第几条");
                                    }
                                })
                        .addSheetItem(getString(R.string.female), ActionSheetDialog.SheetItemColor.Blue,
                                new ActionSheetDialog.OnSheetItemClickListener() {
                                    @Override
                                    public void onClick(int which) {
                                        //女的点击
                                        ToastUtils.showCenterToast(PersonalDataActivity.this, which + "女");
                                    }
                                })

                        .show();
                break;
            case R.id.personal_data_birthday_text:
                BirthDayPickerDialog birthDayPickerDialog = new BirthDayPickerDialog(this, R.style.CustomcityPickerDialog);
                birthDayPickerDialog.show();
                break;
            case R.id.personal_data_personalized_signature:
                //个性签名
                intent = new Intent(this, PersonalizedSignatureActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case R.id.personal_data_QRCode_text:
                //二维码名片
                intent=new Intent(this,PersonalQRcodeBusinessActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
                break;
            case R.id.personal_data_taste_preference:
                //口味偏好
                 intent=new Intent(this,TastePreferenceActivity.class);
                 startActivity(intent);
                overridePendingTransition(R.anim.activity_in,R.anim.activity_out);
            break;
            default:
                break;
        }
    }
}
