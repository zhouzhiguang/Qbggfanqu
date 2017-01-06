package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SimpleItemAnimator;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.personcentre.adapter.MoneyAdapter;
import com.fanqu.personcentre.interfacely.MoneyInputListener;
import com.fanqu.personcentre.model.MoneyEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * 充值的主要界面
 */
public class RechargeBalanceActivity extends BaseActivity {
    private RecyclerView money_rv;
    private MoneyAdapter adapter;
    private TextView selling_price, mine_balance;
    private String recharge;//充值金额

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
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
    }

    private void initListener() {
        adapter.setMoneyInputListener(new MoneyInputListener() {
            @Override
            public void onGetMoneyInput(String money) {
                selling_price.setText(money);
                recharge = money;
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
