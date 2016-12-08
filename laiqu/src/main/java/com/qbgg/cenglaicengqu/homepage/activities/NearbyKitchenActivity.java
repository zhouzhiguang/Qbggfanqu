package com.qbgg.cenglaicengqu.homepage.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.homepage.adapter.NearbyKitchenAdapter;
import com.qbgg.cenglaicengqu.homepage.model.KitchenBean;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class NearbyKitchenActivity extends BaseActivity {
    private RecyclerView nearby_kitchen_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_nearby_kitchen_layout);
        AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
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
                ToastUtils.showCenterToast(NearbyKitchenActivity.this, "点击返回了哦");
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        nearby_kitchen_recyclerview = findView(R.id.nearby_kitchen_recyclerview);
    }

    private void initDate() {
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        nearby_kitchen_recyclerview.setLayoutManager(manager);
        List<KitchenBean> beas = new ArrayList<KitchenBean>();
        for (int i = 0; i < 6; i++) {
            KitchenBean kitchenBean = new KitchenBean();
            if (i % 2 == 0) {
                kitchenBean.setKitchenBeanlike(true);
            }
            beas.add(kitchenBean);
        }
        NearbyKitchenAdapter adapter = new NearbyKitchenAdapter(this, R.layout.kitchen_recyclerview_item, beas);
        nearby_kitchen_recyclerview.setAdapter(adapter);
    }

    private void initListener() {
    }


    @Override
    protected int getLayoutId() {
        return 0;
    }
}
