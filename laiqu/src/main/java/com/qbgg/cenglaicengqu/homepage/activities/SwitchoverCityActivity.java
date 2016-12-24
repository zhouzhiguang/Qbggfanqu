package com.qbgg.cenglaicengqu.homepage.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.netease.nim.uikit.model.ToolBarOptions;
import com.qbgg.cenglaicengqu.R;
import com.qbgg.cenglaicengqu.homepage.adapter.OpenedCityadapter;
import com.qbgg.cenglaicengqu.main.acitvities.BaseActivity;
import com.qbgg.cenglaicengqu.main.autolayout.AutoUtils;
import com.qbgg.cenglaicengqu.main.util.ThemUtils;
import com.qbgg.cenglaicengqu.main.util.ToastUtils;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class SwitchoverCityActivity extends BaseActivity {
    private RecyclerView switchover_city_opened_city_recyclerview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.red_bg);
        setContentView(R.layout.activity_switchover_city_layout);
        // AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
        AutoUtils.auto(this);
        ToolBarOptions options = new ToolBarOptions();
        options.isNeedNavigate = true;
        options.navigateId = R.mipmap.ic_left_arrows_white;
        options.titleString = "";
        setToolBar(R.id.toolbar, options);
        Toolbar toolbar = getToolBar();

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.showCenterToast(SwitchoverCityActivity.this, "点击返回了哦");
            }
        });
        initView();
        innitDate();
        initListener();
    }

    /**
     * 初始化view
     */
    private void initView() {
        switchover_city_opened_city_recyclerview = findView(R.id.switchover_city_opened_city_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        switchover_city_opened_city_recyclerview.setLayoutManager(manager);
        List<String> cities = new ArrayList<String>();

        cities.add("广州");
        cities.add("杭州");
        cities.add("上海");
        cities.add("北京");
        cities.add("东莞");
        for (int i = 0; i < 20; i++) {
            cities.add("东莞");
        }

        OpenedCityadapter adapter = new OpenedCityadapter(this, R.layout.opened_city_recyclerview_item, cities);
        adapter.setLocationcity("东莞");
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        switchover_city_opened_city_recyclerview.setAdapter(adapter);
    }

    private void initListener() {
    }

    private void innitDate() {
    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
