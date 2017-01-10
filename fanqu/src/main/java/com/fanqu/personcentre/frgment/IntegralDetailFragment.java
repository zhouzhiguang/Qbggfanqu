package com.fanqu.personcentre.frgment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fanqu.R;
import com.fanqu.framework.fragment.LazyFragment;
import com.fanqu.framework.main.util.LogUtil;
import com.fanqu.personcentre.adapter.IntegralDetailAdapter;
import com.fanqu.personcentre.model.IntegralDetailEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


/**
 * 积分明细集合
 */
public class IntegralDetailFragment extends LazyFragment {

    private static IntegralDetailFragment fragment;
    private int position;
    private RecyclerView integral_detail_recyclerview;
    private IntegralDetailAdapter adapter;
    private List<IntegralDetailEntity> dates;
    private List<IntegralDetailEntity> ncomedates;//收入
    private List<IntegralDetailEntity> expenddates;//支出
    private LinearLayoutManager manager;


    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        setContentView(R.layout.fragment_integral_detail_layout);
        integral_detail_recyclerview = findView(R.id.integral_detail_recyclerview);
        Bundle bundle = getArguments();
        if (bundle != null) {
            position = bundle.getInt("position");
        }
//        datas = new ArrayList<IntegralDetailEntity>();
//        for (int i = 0; i < 10; i++) {
//            IntegralDetailEntity entity = new IntegralDetailEntity();
//            datas.add(entity);
//        }
//        adapter = new IntegralDetailAdapter(getActivity(), R.layout.integral_detail_item_layout, datas);
//        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
//        manager.setOrientation(LinearLayoutManager.VERTICAL);
//        integral_detail_recyclerview.setLayoutManager(manager);
//        integral_detail_recyclerview.setAdapter(adapter);
        initTestDate();
        manager = new LinearLayoutManager(getActivity());
        manager.setOrientation(LinearLayoutManager.VERTICAL);
        ncomedates = new ArrayList<>();
        expenddates = new ArrayList<>();
    }

    private void initTestDate() {
        dates = new ArrayList<IntegralDetailEntity>();
        Random random = new Random(31);

        for (int i = 0; i < 10; i++) {
            IntegralDetailEntity bean = new IntegralDetailEntity();
            int date = random.nextInt();
            int result = i % 3;
            if (result == 0) {

                bean.setIntegraldetailtitle("成功支付订单");
                bean.setIntegraldate(String.valueOf(result) + date + " 12:00");
                bean.setIntegralcount(String.valueOf(i * 5));
                bean.setRevenueexpenditure_mark(1);
            } else if (result == 1) {

                bean.setIntegraldetailtitle("积分兑换");
                bean.setIntegraldate(String.valueOf(result) + date + " 15:00");
                bean.setIntegralcount("-" + String.valueOf(i * 5));
                bean.setRevenueexpenditure_mark(-1);
            } else {
                bean.setRevenueexpenditure_mark(1);
                bean.setIntegraldetailtitle("签到");
                bean.setIntegraldate(String.valueOf(result) + date + " 08:00");
                bean.setIntegralcount("5");
            }
            dates.add(bean);
        }
    }

    @Override
    protected void onDestroyViewLazy() {
        super.onDestroyViewLazy();


    }

    protected void onFragmentStartLazy() {
        LogUtil.e("测试生命周期", "onFragmentStartLazy++++++" + position);
        //初始化各个集合
        getIntegralDetailList();
    }

    private void getIntegralDetailList() {
        if (dates != null && dates.size() > 0) {
            for (IntegralDetailEntity entity : dates) {
                int mark = entity.getRevenueexpenditure_mark();
                if (mark == 1) {
                    ncomedates.add(entity);
                } else {
                    expenddates.add(entity);
                }
            }
        }

    }

    protected void onFragmentStopLazy() {
        LogUtil.e("测试生命周期", "onFragmentStopLazy");
    }


    protected void onResumeLazy() {

        switch (position) {
            case 1:

                adapter = new IntegralDetailAdapter(getActivity(), R.layout.integral_detail_item_layout, ncomedates);
                integral_detail_recyclerview.setLayoutManager(manager);
                integral_detail_recyclerview.setAdapter(adapter);
                break;
            case 2:
                adapter = new IntegralDetailAdapter(getActivity(), R.layout.integral_detail_item_layout, expenddates);
                integral_detail_recyclerview.setLayoutManager(manager);
                integral_detail_recyclerview.setAdapter(adapter);
                break;
            case 0:
                adapter = new IntegralDetailAdapter(getActivity(), R.layout.integral_detail_item_layout, dates);
                integral_detail_recyclerview.setLayoutManager(manager);
                integral_detail_recyclerview.setAdapter(adapter);

                break;
            default:
                break;
        }
    }

    protected void onPauseLazy() {
        LogUtil.e("测试生命周期", "onPauseLazy");
    }


}
