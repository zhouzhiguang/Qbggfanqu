package com.fanqu.dinner.fragment;


import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fanqu.R;
import com.fanqu.dinner.adapter.DinnerAdapter;
import com.fanqu.dinner.modle.DinnerBean;
import com.fanqu.framework.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 饭局主fragment  还需要该动
 */
public class DinnerFragment extends BaseFragment {

    private Activity activity;
    private RecyclerView dinner_recyclerview;

    public DinnerFragment() {

    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_dinner_layout);
        dinner_recyclerview = findView(R.id.dinner_recyclerview);
        activity = getActivity();
        initEventAndData();
    }

    @Override
    protected void setListener() {

    }

    @Override
    protected void processLogic(Bundle savedInstanceState) {

    }

    @Override
    protected void onUserVisible() {

    }


    protected void initEventAndData() {

        LinearLayoutManager manager = new LinearLayoutManager(activity);
        manager.setOrientation(LinearLayoutManager.VERTICAL);

        List<DinnerBean> dates = new ArrayList<DinnerBean>();
        for (int i = 0; i < 10; i++) {
            DinnerBean bean = new DinnerBean();
            dates.add(bean);
        }
        DinnerAdapter adapter = new DinnerAdapter(activity, R.layout.dinner_recyclerview_layout, dates);
//        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
////        TextView textView=new TextView(activity);
////        textView.setText("测试");
////        textView.setTextSize(80f);
//        //   mHeaderAndFooterWrapper.addFootView(textView);
//        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(adapter);
//        mLoadMoreWrapper.setLoadMoreView(R.layout.nim_emoji_layout);
        dinner_recyclerview.setLayoutManager(manager);
        dinner_recyclerview.setAdapter(adapter);
    }


}
