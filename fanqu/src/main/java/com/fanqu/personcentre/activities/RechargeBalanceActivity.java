package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.LogUtil;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.main.widget.SmoothCheckBox;
import com.fanqu.personcentre.adapter.MoneyAdapter;
import com.fanqu.personcentre.interfacely.MoneyInputListener;
import com.fanqu.personcentre.model.MoneyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 充值的主要界面
 */
public class RechargeBalanceActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView money_rv;
    private MoneyAdapter adapter;
    private TextView selling_price, mine_balance;
    private String recharge;//充值金额
    private SmoothCheckBox pay_weixin_checkbox, pay_alipay_checkbox;
    private LinearLayout pay_weixin_layout, pay_alipay_layout, recharge_balance_layout;
    private TextView recharge_balance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
       // AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_recharge_balance_layout);

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
                ToastUtils.showCenterToast(RechargeBalanceActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        money_rv = findView(R.id.money_rv);
        selling_price = findView(R.id.selling_price);
        //我的余额
        mine_balance = findView(R.id.mine_balance);
        pay_weixin_checkbox = findView(R.id.pay_weixin_checkbox);
        pay_alipay_checkbox = findView(R.id.pay_alipay_checkbox);
        pay_weixin_layout = findView(R.id.pay_weixin_layout);
        pay_alipay_layout = findView(R.id.pay_alipay_layout);
        recharge_balance = findView(R.id.recharge_balance);
        recharge_balance_layout = findView(R.id.recharge_balance_layout);
    }

    private void initDate() {
        //防止recycleview闪烁
        ((SimpleItemAnimator) money_rv.getItemAnimator()).setSupportsChangeAnimations(false);
        GridLayoutManager manager = new GridLayoutManager(this, 4);
        List<MoneyEntity> data = new ArrayList<MoneyEntity>();
        data.add(new MoneyEntity("0.01元", true));
        data.add(new MoneyEntity("10元", false));
        data.add(new MoneyEntity("20元", false));
        data.add(new MoneyEntity("50元", false));
        data.add(new MoneyEntity("100元", false));
        data.add(new MoneyEntity("200元", false));
        data.add(new MoneyEntity("500元", false));
        adapter = new MoneyAdapter(this, data);
        money_rv.setLayoutManager(manager);
        money_rv.setAdapter(adapter);
        recharge = "0.01元";
        selling_price.setText(recharge);
        pay_weixin_checkbox.setChecked(true);
//        pay_weixin_checkbox.clearFocus();
//        pay_alipay_checkbox.clearFocus();
    }

    private void initListener() {
        adapter.setMoneyInputListener(new MoneyInputListener() {
            @Override
            public void onGetMoneyInput(String money) {
                selling_price.setText(money);
                recharge = money;
            }
        });
        pay_weixin_checkbox.setOnClickListener(this);
        pay_alipay_checkbox.setOnClickListener(this);

        pay_weixin_checkbox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {

                boolean alipay_checked = pay_alipay_checkbox.isChecked();
                if (!isChecked && !alipay_checked) {
                    recharge_balance.setEnabled(false);
                } else {
                    recharge_balance.setEnabled(true);
                }
            }
        });
        pay_alipay_checkbox.setOnCheckedChangeListener(new SmoothCheckBox.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(SmoothCheckBox checkBox, boolean isChecked) {
                boolean weixin = pay_weixin_checkbox.isChecked();
                if (!weixin && !isChecked) {
                    recharge_balance.setEnabled(false);

                } else {
                    recharge_balance.setEnabled(true);

                }
            }
        });
        pay_weixin_layout.setOnClickListener(this);
        pay_alipay_layout.setOnClickListener(this);
        //recharge_balance.setOnClickListener(this);
        recharge_balance_layout.setOnClickListener(this);
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.pay_weixin_layout:
            case R.id.pay_weixin_checkbox:
                switchweixincheckbox();
                break;
            case R.id.pay_alipay_layout:
            case R.id.pay_alipay_checkbox:

                switchalipaycheckbox();
                break;
            case R.id.recharge_balance:
            case R.id.recharge_balance_layout:
                //充值了
                rechargebalance();
                break;
            default:
                break;
        }
    }

    private void rechargebalance() {
        boolean enable = recharge_balance.isEnabled();
        if (enable) {
            boolean alipay_checked = pay_alipay_checkbox.isChecked();
            if (alipay_checked) {
                alipay();
            } else {
                weixinpay();
            }

        } else {
            ToastUtils.showCenterToast(this, R.string.recharge_balance_pay_way);
        }
    }

    /**
     * 微信支付
     */
    private void weixinpay() {
        String paymoney = recharge.substring(0, recharge.length() - 1);
        LogUtil.e("weixinpay支付多少钱", paymoney);
    }

    /**
     * 支付宝支付
     */
    private void alipay() {
        String paymoney = recharge.substring(0, recharge.length() - 1);
        LogUtil.e("alipay支付多少钱", paymoney);
    }

    private void switchalipaycheckbox() {
        boolean checked = pay_alipay_checkbox.isChecked();
        boolean weixin_checked = pay_weixin_checkbox.isChecked();
        if (checked) {
            pay_alipay_checkbox.setChecked(false, true);
        } else {
            if (weixin_checked) {
                pay_weixin_checkbox.setChecked(false, true);
            }
            pay_alipay_checkbox.setChecked(true, true);
        }
    }

    /**
     * 改变微信选择的状态
     */
    private void switchweixincheckbox() {
        boolean checked = pay_weixin_checkbox.isChecked();
        boolean alipay_checked = pay_alipay_checkbox.isChecked();
        if (checked) {
            pay_weixin_checkbox.setChecked(false, true);
        } else {
            if (alipay_checked) {
                pay_alipay_checkbox.setChecked(false);
            }
            pay_weixin_checkbox.setChecked(true, true);
        }
    }
}
