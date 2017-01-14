package com.fanqu.personcentre.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.fanqu.R;
import com.fanqu.framework.activities.BaseActivity;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.LogUtil;
import com.fanqu.framework.main.util.ThemUtils;
import com.fanqu.framework.model.ToolBarOptions;
import com.fanqu.personcentre.adapter.CouponCentreListAdapter;
import com.fanqu.personcentre.model.CouponCentreDetailEntity;
import com.zhy.adapter.recyclerview.MultiItemTypeAdapter;

import java.util.ArrayList;
import java.util.List;


/**
 * 领券中心
 */
public class GetCouponCentreActivity extends BaseActivity {
    private RecyclerView recyclerView;
    private CouponCentreListAdapter adapter;
    private List<CouponCentreDetailEntity> datas;

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
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(manager);

    }

    private void initDate() {
        datas = new ArrayList<>();
        // datas.add(new CouponCentreDetailEntity());
        initDatas(datas);
        adapter = new CouponCentreListAdapter(this, R.layout.coupon_centre_list_item_layout, datas);
        recyclerView.setAdapter(adapter);
    }

    private void initDatas(List<CouponCentreDetailEntity> datas) {
        if (datas != null) {
            for (int i = 0; i < 20; i++) {
                int result = i % 3;
                //未领取状态
                if (result == 0) {
                    CouponCentreDetailEntity entity = new CouponCentreDetailEntity();
                    if (i % 2 == 0) {
                        ////优惠券抵扣条件 0代表无门槛消费 数字代表满多少能使用
                        entity.setCouponcondition(0);
                    } else {
                        //满一百元可用有条件的
                        entity.setCouponcondition(getRandomInt(150, 800));
                    }

                    entity.setCouponids(String.valueOf(541651465 + i));
                    entity.setCoupontitle("全品类（特价商品除外）");
                    //未使用
                    entity.setCouponstate(1);
                    entity.setGetcoupondate("2017.01-10");
                    entity.setOverduedate("2017.03-10");
                    entity.setPromotioncode(String.valueOf(getRandomInt(25554669, 99999999)));
                    entity.setCouponmoney(String.valueOf(getRandomInt(10, 100)));
                    entity.setAlreadygetcouponnumberpeople(String.valueOf(getRandomInt(20, 100)) + "%");
                    //三个状态 1 还未领取 0 已经领取 -1 标识 已经卖完了抢光了
                    entity.setCouponcentrestate(1);
                    datas.add(entity);
                } else if (result == 1) {
                    //已经领取还未使用的
                    CouponCentreDetailEntity entity = new CouponCentreDetailEntity();
                    if (i % 2 == 0) {
                        ////优惠券抵扣条件 0代表无门槛消费 数字代表满多少能使用
                        entity.setCouponcondition(0);
                    } else {
                        //满一百元可用有条件的
                        entity.setCouponcondition(getRandomInt(150, 800));
                    }

                    entity.setCouponids(String.valueOf(541651465 + i));
                    entity.setCoupontitle("全品类（特价商品除外）");
                    //未使用
                    entity.setCouponstate(1);
                    entity.setGetcoupondate("2017.01-10");
                    entity.setOverduedate("2017.03-10");
                    entity.setPromotioncode(String.valueOf(getRandomInt(25554669, 99999999)));
                    entity.setCouponmoney(String.valueOf(getRandomInt(10, 100)));
                    entity.setAlreadygetcouponnumberpeople("60%");
                    //三个状态 1 还未领取 0 已经领取 -1 标识 已经卖完了抢光了
                    entity.setCouponcentrestate(0);
                    datas.add(entity);
                } else if (result == 2) {
                    //已经抢光了
                    CouponCentreDetailEntity entity = new CouponCentreDetailEntity();
                    if (i % 2 == 0) {
                        ////优惠券抵扣条件 0代表无门槛消费 数字代表满多少能使用
                        entity.setCouponcondition(0);
                    } else {
                        //满一百元可用有条件的
                        entity.setCouponcondition(getRandomInt(150, 800));
                    }

                    entity.setCouponids(String.valueOf(541651465 + i));
                    entity.setCoupontitle("全品类（特价商品除外）");
                    //未使用
                    entity.setCouponstate(1);
                    entity.setGetcoupondate("2017.01-10");
                    entity.setOverduedate("2017.03-10");
                    entity.setPromotioncode(String.valueOf(getRandomInt(25554669, 99999999)));
                    entity.setCouponmoney(String.valueOf(getRandomInt(10, 100)));
                    entity.setAlreadygetcouponnumberpeople("60%");
                    //三个状态 1 还未领取 0 已经领取 -1 标识 已经卖完了抢光了
                    entity.setCouponcentrestate(-1);
                    datas.add(entity);
                }

            }

        }
    }


    private void initListener() {

        adapter.setOnItemClickListener(new MultiItemTypeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, RecyclerView.ViewHolder holder, int position) {
                CouponCentreDetailEntity entity = datas.get(position);
                int couponcondition = entity.getCouponcondition();
                //三个状态 1 还未领取 0 已经领取 -1 标识 已经卖完了抢光了
                int couponcentrestate = entity.getCouponcentrestate();
                if (couponcentrestate == 1) {
                    entity.setCouponcentrestate(0);
                    //adapter.notifyDataSetChanged();
                    adapter.notifyItemChanged(position, entity);
                    LogUtil.e("已经领取了");
                }
            }

            @Override
            public boolean onItemLongClick(View view, RecyclerView.ViewHolder holder, int position) {
                return false;
            }
        });

    }

    @Override
    protected int getLayoutId() {
        return 0;
    }

    // 返回a到b之間(包括a,b)的任意一個自然数,如果a > b || a < 0，返回-1
    public int getRandomInt(int a, int b) {
        if (a > b || a < 0)
            return -1;
        // 下面两种形式等价
        // return a + (int) (new Random().nextDouble() * (b - a + 1));
        return a + (int) (Math.random() * (b - a + 1));
    }
}
