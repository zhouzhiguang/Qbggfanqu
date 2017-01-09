package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
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

    private TextView mineBalance;//我的余额
    private TextView rechargeBalance;//充值
    private TextView mineBalanceTouchBalance;//余额明细
    private TextView mineBalanceSetTradersPassword;//设置交易密码
    private TextView mineBalanceExplain;//余额说明
    private LinearLayout mine_membership_card_balance_layout;//会员卡余额布局
    private TextView mine_membership_card_balance;//会员卡余额
    private TextView mine_membership_card_balance_money;//这里有设置交易密码还有修改交易密码两个状态
    private TextView mine_balance_TouchBalance;
    private TextView mine_balance_set_Traders_Password;//设置交易密码

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
        mine_balance_TouchBalance = findView(R.id.mine_balance_TouchBalance);
        mine_membership_card_balance_layout = findView(R.id.mine_membership_card_balance_layout);
        mine_membership_card_balance = findView(R.id.mine_membership_card_balance);
        mineBalance = findView(R.id.mine_balance);
        rechargeBalance = findView(R.id.recharge_balance);
        mineBalanceTouchBalance = findView(R.id.mine_balance_TouchBalance);
        mineBalanceSetTradersPassword = findView(R.id.mine_balance_set_Traders_Password);
        mineBalanceExplain = findView(R.id.mine_balance_explain);
        //会员钱
        mine_membership_card_balance_money = findView(R.id.mine_membership_card_balance_money);
        //设置交易密码
        mine_balance_set_Traders_Password = findView(R.id.mine_balance_set_Traders_Password);

    }

    private void initDate() {

    }

    private void initListener() {
        mine_balance_TouchBalance.setOnClickListener(this);
        rechargeBalance.setOnClickListener(this);
        mine_membership_card_balance_layout.setOnClickListener(this);
        mine_membership_card_balance.setOnClickListener(this);
        mine_balance_set_Traders_Password.setOnClickListener(this);
        mineBalanceExplain.setOnClickListener(this);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.recharge_balance:
                jumpActivity(RechargeBalanceActivity.class);
                break;
            case R.id.mine_membership_card_balance:
            case R.id.mine_membership_card_balance_layout:

                jumpActivity(MembershipCardBalanceActivity.class);
                break;
            case R.id.mine_balance_TouchBalance:
                //交易记录
                jumpActivity(TransactionRecordActivity.class);
                break;
            case R.id.mine_balance_set_Traders_Password:
                //设置交易密码
                jumpActivity(SetTradersPasswordActivity.class);
                //还有可能是更改交易密码
                // jumpActivity(ModificationTradersPasswordActivity.class);
                break;
            case R.id.mine_balance_explain:
                //余额说明
                jumpActivity(MineBalanceExplainActivity.class);
                break;
            default:
                break;
        }
    }

//    /**
//     * @param clazz 跳转
//     */
//    private void jumpActivity(Class clazz) {
//        Intent intent = new Intent(this, clazz);
//        startActivity(intent);
//        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
//    }
}
