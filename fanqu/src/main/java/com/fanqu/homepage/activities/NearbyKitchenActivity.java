package com.fanqu.homepage.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.main.util.ToastUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.homepage.adapter.NearbyKitchenAdapter;
import com.fanqu.homepage.model.KitchenBean;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;

public class NearbyKitchenActivity extends BaseActivity implements View.OnClickListener {
    private RecyclerView nearby_kitchen_recyclerview;
    private NearbyKitchenAdapter adapter;
    private ImageView nearby_kitchen_location;
    private LinearLayout nearby_kitchen_condition_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        setContentView(R.layout.activity_nearby_kitchen_layout);
      //  AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
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
        nearby_kitchen_condition_layout=findView(R.id.nearby_kitchen_condition_layout);
        nearby_kitchen_location=findView(R.id.nearby_kitchen_location);
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
        adapter = new NearbyKitchenAdapter(this, R.layout.kitchen_recyclerview_item, beas);
        nearby_kitchen_recyclerview.setAdapter(adapter);
    }

    private void initListener() {
        //item 点击事件
        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {

                ToastUtils.showCenterToast(getApplicationContext(),"位置"+position);
                //点击进入饭局详情页面
                 Intent intent =new Intent(NearbyKitchenActivity.this,KitchenDetailsActivity.class);
                 startActivity(intent);
                 //finish();
                 overridePendingTransition(R.anim.activity_in,R.anim.activity_out);

            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });
        for(int i=0;i<nearby_kitchen_condition_layout.getChildCount();i++){
            nearby_kitchen_condition_layout.getChildAt(i).setOnClickListener(this);
        }
        nearby_kitchen_location.setOnClickListener(this);
    }


    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.nearby_kitchen_area:
            ToastUtils.showCenterToast(this,"nearby_kitchen_area");
            break;
            case R.id.nearby_kitchen_price:
                ToastUtils.showCenterToast(this,"nearby_kitchen_price");
                break;
            case R.id.nearby_kitchen_house_type:
                ToastUtils.showCenterToast(this,"nearby_kitchen_house_type");
                break;
            case R.id.nearby_kitchen_style_dish:
                ToastUtils.showCenterToast(this,"nearby_kitchen_style_dish");
                break;
            case R.id.nearby_kitchen_location:
                ToastUtils.showCenterToast(this,"nearby_kitchen_location");
                break;
            default:
                break;
        }
    }
}
