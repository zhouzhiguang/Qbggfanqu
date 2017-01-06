package com.fanqu.personcentre.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;

/**
 * 我的余额
 */
public class MineBalanceActivity extends BaseActivity implements View.OnClickListener {

    private TextView mineMembershipCardBalance;//会员卡余额
    private TextView mineBalance;//我的余额
    private TextView rechargeBalance;//充值
    private TextView mineBalanceTouchBalance;//余额明细
    private TextView mineBalanceSetTradersPassword;//设置交易密码
    private TextView mineBalanceExplain;//余额说明


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        //AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_mine_balance_layout);
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
                ToastUtils.showCenterToast(MineBalanceActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {

        mineMembershipCardBalance = findView(R.id.mine_membership_card_balance);
        mineBalance = findView(R.id.mine_balance);
        rechargeBalance = findView(R.id.recharge_balance);
        mineBalanceTouchBalance = findView(R.id.mine_balance_TouchBalance);
        mineBalanceSetTradersPassword = findView(R.id.mine_balance_set_Traders_Password);
        mineBalanceExplain = findView(R.id.mine_balance_explain);
    }

    private void initDate() {

    }

    private void initListener() {
        rechargeBalance.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recharge_balance:
                Intent intent = new Intent(this, RechargeBalanceActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
                break;
            default:
                break;
        }
    }
}