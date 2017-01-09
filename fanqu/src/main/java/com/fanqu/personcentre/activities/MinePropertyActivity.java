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
 * 我的钱包
 */
public class MinePropertyActivity extends BaseActivity implements View.OnClickListener {


    private CircleImageView mine_property_head_image;
    private TextView mine_property_nice_name, mine_property_identity, nice_name;
    private ImageView mine_property_balance_image;
    private TextView mine_property_balance_text, mine_property_balance_amount;
    private ImageView mine_property_bank_card_image;
    private TextView mine_property_bank_card_text, mine_property_bank_card_amount;
    private ImageView mine_property_integral_image;
    private TextView mine_property_integral_text, mine_property_integral_amount;
    private ImageView mine_property_coupon_image;
    private TextView mine_property_coupon_text, mine_property_coupon_amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        // AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_mine_property_layout);
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
                ToastUtils.showCenterToast(MinePropertyActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        //头像
        mine_property_head_image = findView(R.id.mine_property_head_image);
        mine_property_nice_name = findView(R.id.mine_property_nice_name);
        mine_property_identity = findView(R.id.mine_property_identity);
        nice_name = findView(R.id.nice_name);
        mine_property_balance_image = findView(R.id.mine_property_balance_image);
        mine_property_balance_text = findView(R.id.mine_property_balance_text);
        mine_property_balance_amount = findView(R.id.mine_property_balance_amount);
        mine_property_bank_card_image = findView(R.id.mine_property_bank_card_image);
        mine_property_bank_card_text = findView(R.id.mine_property_bank_card_text);
        mine_property_bank_card_amount = findView(R.id.mine_property_bank_card_amount);
        mine_property_integral_image = findView(R.id.mine_property_integral_image);
        mine_property_integral_text = findView(R.id.mine_property_integral_text);
        mine_property_integral_amount = findView(R.id.mine_property_integral_amount);
        mine_property_coupon_image = findView(R.id.mine_property_coupon_image);
        mine_property_coupon_text = findView(R.id.mine_property_coupon_text);
        mine_property_coupon_amount = findView(R.id.mine_property_coupon_amount);

    }

    private void initDate() {

//        Drawable drawable = ContextCompat.getDrawable(this, R.mipmap.ic_real_name_no_authentication);
//        //还未实名认证设置
//        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
//        mine_property_identity.setCompoundDrawables(drawable, null, null, null);

    }

    private void initListener() {
        mine_property_head_image.setOnClickListener(this);
        mine_property_nice_name.setOnClickListener(this);
        mine_property_identity.setOnClickListener(this);
        nice_name.setOnClickListener(this);
        mine_property_balance_image.setOnClickListener(this);
        mine_property_balance_text.setOnClickListener(this);
        mine_property_balance_amount.setOnClickListener(this);
        mine_property_bank_card_amount.setOnClickListener(this);
        mine_property_bank_card_text.setOnClickListener(this);
        mine_property_integral_image.setOnClickListener(this);
        mine_property_integral_text.setOnClickListener(this);
        mine_property_integral_amount.setOnClickListener(this);
        mine_property_coupon_image.setOnClickListener(this);
        mine_property_coupon_text.setOnClickListener(this);
        mine_property_coupon_amount.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.mine_property_head_image:
            case R.id.mine_property_nice_name:
            case R.id.mine_property_identity:
            case R.id.nice_name:
                jumpActivity(PersonalDataActivity.class);
                break;
            case R.id.mine_property_balance_image:
            case R.id.mine_property_balance_text:
            case R.id.mine_property_integral_amount:
                //余额
                jumpActivity(MineBalanceActivity.class);
//                Intent it = new Intent(this, MineBalanceActivity.class);
//                startActivity(it);
//                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            case R.id.mine_property_integral_image:
            case R.id.mine_property_integral_text:
            case R.id.mine_property_balance_amount:
                //积分
                jumpActivity(MineIntegralActivity.class);
//                Intent it = new Intent(this, MineBalanceActivity.class);
//                startActivity(it);
//                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;

            default:
                break;
        }
    }
}
