package com.fanqu.like.fragment;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fanqu.R;
import com.fanqu.dinner.adapter.DinnerPartyListAdapter;
import com.fanqu.dinner.modle.DinnerPartyEntity;
import com.fanqu.framework.fragment.BaseFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 喜欢的饭局
 */
public class LikeFragment extends BaseFragment {
    private RecyclerView recyclerview;


    @Override
    protected void initView(Bundle savedInstanceState) {
        setContentView(R.layout.fragment_like_layout);
        recyclerview = findView(R.id.like_dinner_party_recyclerview);
        LinearLayoutManager manager = new LinearLayoutManager(getActivity());
        recyclerview.setLayoutManager(manager);

        List<DinnerPartyEntity> dates = new ArrayList<DinnerPartyEntity>();
        initTestList(dates);
        DinnerPartyListAdapter adapter = new DinnerPartyListAdapter(getActivity(), R.layout.dinner_party_list_item_layout, dates);
        recyclerview.setAdapter(adapter);
    }

    private void initTestList(List<DinnerPartyEntity> dates) {
        if (dates != null) {
            for (int i = 0; i < 8; i++) {
                int result = i % 3;

                DinnerPartyEntity entity = new DinnerPartyEntity();
                if (result == 2) {
                    entity.setAtrest(true);
                }
                //0是一般饭局 1 是喜欢的饭局
                entity.setDinnerparty(1);
                entity.setDinnerpartytheme("一道客家风味河鲜菜品，红烧螺蛳");
                entity.setDinnerpartydistance(getRandomInt(10, 100) + "km");
                entity.setDinnerpartylocation("义乌市赤岸乔");
                entity.setDinnerpartygalleryful("6-10人");
                String[] feature;
                if (result == 0) {
                    feature = new String[1];
                    feature[0] = "瀑布旅游";
                } else if (result == 1) {
                    feature = new String[2];
                    feature[0] = "特色小吃";
                    feature[1] = "家常菜";
                } else {
                    feature = new String[3];
                    feature[0] = "儿时记忆";
                    feature[1] = "农家菜";
                    feature[1] = "原生态";
                }
                entity.setDinnerpartyfeature(feature);
                entity.setDinnerpartymoney(String.valueOf(getRandomInt(10, 100)));
                dates.add(entity);
            }
        }
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

    // 返回a到b之間(包括a,b)的任意一個自然数,如果a > b || a < 0，返回-1
    public int getRandomInt(int a, int b) {
        if (a > b || a < 0)
            return -1;
        // 下面两种形式等价
        // return a + (int) (new Random().nextDouble() * (b - a + 1));
        return a + (int) (Math.random() * (b - a + 1));
    }
}
