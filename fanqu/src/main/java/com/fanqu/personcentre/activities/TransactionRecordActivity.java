package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.LogUtil;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.main.widget.ListViewPlus;
import com.fanqu.main.widget.PinnedSectionListView;
import com.fanqu.personcentre.adapter.BillListAdapter;
import com.fanqu.personcentre.model.Bill;

import java.util.ArrayList;

/**
 * 我的余额明细
 * 交易记录
 */
public class TransactionRecordActivity extends BaseActivity implements ListViewPlus.ListViewPlusListener {
    private PinnedSectionListView transaction_record_list;
    private BillListAdapter adapter;
    private ArrayList<Bill> bills;
    private int page = 1;//当前页

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
       // AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_transaction_record_layout);
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
        transaction_record_list = findView(R.id.transaction_record_list);
    }

    private void initDate() {
        bills = new ArrayList<Bill>();
        transaction_record_list.setLoadEnable(true);
        transaction_record_list.setListViewPlusListener(this);
        adapter = new BillListAdapter(this, bills);
        transaction_record_list.setAdapter(adapter);
        loadData(ListViewPlus.REFRESH);
    }

    private void initListener() {
        getHandler().postDelayed(new Runnable() {
            @Override
            public void run() {
                String TEST = bills.get(0).toString();
                LogUtil.e("测试", TEST);
            }
        },2000);

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onRefresh() {
        loadData(ListViewPlus.REFRESH);
    }

    @Override
    public void onLoadMore() {
        loadData(ListViewPlus.LOAD);
    }

    private void loadData(final int what) {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (ListViewPlus.REFRESH == what) {
                    bills.clear();
                    transaction_record_list.stopRefresh();
                    page = 1;
                } else {
                    transaction_record_list.stopLoadMore();
                    page++;
                }
                for (int i = (page - 1) * 8; i < page * 8; i++) {
                    Bill bill = new Bill(i, "title:" + i, i * 100 + "元", System.currentTimeMillis() - 1000L * 60 * 60 * 24 * 12 * i, "交易成功");
                    bills.add(bill);
                }
                adapter.notifyDataSetChanged();
            }
        }, 1000);
    }
}
