package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.personcentre.adapter.CouponCentreListAdapter;
import com.fanqu.personcentre.model.CouponCentreDetailEntity;

import java.util.ArrayList;
import java.util.List;


/**
 * 领券中心
 */
public class GetCouponCentreActivity extends BaseActivity {
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState != null) {
            String FRAGMENTS_TAG = "android:support:fragments";
            // remove saved fragment, will new fragment in mPagerAdapter
            savedInstanceState.remove(FRAGMENTS_TAG);
        }
        super.onCreate(savedInstanceState);
        ThemUtils.initthem(this, R.color.white);
        // AutoUtils.setSize(this, false, 1080, 1812);// 没有状态栏,设计尺寸的宽高1.6875倍
        setContentView(R.layout.activity_get_coupon_centre_layout);
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
                //ToastUtils.showCenterToast(GetCouponCentreActivity.this, "点击返回了哦");
                finish();
            }
        });
        initView();
        initDate();
        initListener();
    }

    private void initView() {
        recyclerView = findView(R.id.coupon_centre_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(manager);

    }

    private void initDate() {
        List<CouponCentreDetailEntity> datas = new ArrayList<>();
        datas.add(new CouponCentreDetailEntity());

        CouponCentreListAdapter adapter = new CouponCentreListAdapter(this, R.layout.coupon_centre_list_item_layout, datas);
        recyclerView.setAdapter(adapter);
    }

    private void initListener() {

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }
}
