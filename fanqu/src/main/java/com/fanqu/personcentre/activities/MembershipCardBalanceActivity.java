package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.model.ToolBarOptions;

/**
 * 会员卡余额
 */
public class MembershipCardBalanceActivity extends BaseActivity {
    private TextView membership_card_balance_consume_deadline;
    private TextView membership_card_balance_has_no_consume;//未消费会员卡余额
    private TextView membership_card_balance_has_consume;//已经消费会员卡金额

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
       // AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_membership_card_balance_layout);
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
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        membership_card_balance_has_consume = findView(R.id.membership_card_balance_has_consume);
        membership_card_balance_has_no_consume = findView(R.id.membership_card_balance_has_no_consume);
        membership_card_balance_consume_deadline = findView(R.id.membership_card_balance_consume_deadline);
    }

    private void initDate() {
        String consume_deadline = "2017.01.10-2017.03.10";
        membership_card_balance_consume_deadline.setText(consume_deadline);
    }

    private void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
