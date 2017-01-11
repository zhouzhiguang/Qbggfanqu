package com.fanqu.personcentre.frgment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.CustomApplication;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.fragment.LazyFragment;
import com.fanqu.framework.main.util.ViewHolder;
import com.fanqu.personcentre.activities.GetCouponCentreActivity;
import com.fanqu.personcentre.adapter.CouponlListAdapter;
import com.fanqu.personcentre.model.CouponlDetailEntity;
import com.zhy.adapter.recyclerview.wrapper.HeaderAndFooterWrapper;
import com.zhy.adapter.recyclerview.wrapper.LoadMoreWrapper;

import java.util.ArrayList;
import java.util.List;

/**
 * 优惠卷列表集合recyview
 */
public class CouponlDetailListFragmentFragment extends LazyFragment implements View.OnClickListener {

    // TODO: Customize parameter argument names
    public static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private List<CouponlDetailEntity> dates;
    private RecyclerView recylcerview;
    private LinearLayoutManager manager;
    private LayoutInflater inflater;

    public CouponlDetailListFragmentFragment() {
    }


    public static CouponlDetailListFragmentFragment newInstance(List<CouponlDetailEntity> dates) {
        CouponlDetailListFragmentFragment fragment = new CouponlDetailListFragmentFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_COLUMN_COUNT, (ArrayList<? extends Parcelable>) dates);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected void onCreateViewLazy(Bundle savedInstanceState) {
        super.onCreateViewLazy(savedInstanceState);
        inflater = getLayoutInflater(savedInstanceState);

        setContentView(R.layout.fragment_couponl_detail_list_layout);

        recylcerview = findView(R.id.couponl_list_recyclerview);
        dates = getArguments().getParcelableArrayList(ARG_COLUMN_COUNT);
        manager = new LinearLayoutManager(getActivity());
        CouponlListAdapter adapter = new CouponlListAdapter(getActivity(), R.layout.couponl_recyclerview_item_layout, dates);
        recylcerview.setLayoutManager(manager);
        HeaderAndFooterWrapper mHeaderAndFooterWrapper = new HeaderAndFooterWrapper(adapter);
        View layouy = inflater.inflate(R.layout.couponl_recyclerview_footview_go_getcouponl_centre_layout, null);
        AutoUtils.auto(layouy);
        mHeaderAndFooterWrapper.addFootView(layouy);
        LoadMoreWrapper mLoadMoreWrapper = new LoadMoreWrapper(mHeaderAndFooterWrapper);
        recylcerview.setAdapter(mLoadMoreWrapper);
        LinearLayout goto_coupon_centre_layout = ViewHolder.get(layouy, R.id.goto_coupon_centre_layout);
        int width = CustomApplication.SCREEN_WIDTH;
        int height = (int) getResources().getDimension(R.dimen.dimen_400px);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(width, height);
        goto_coupon_centre_layout.setLayoutParams(params);
        TextView goto_coupon_centre = ViewHolder.get(layouy, R.id.goto_coupon_centre);
        goto_coupon_centre.setOnClickListener(this);
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.goto_coupon_centre:
                //哥们要去领券中心了
                Intent intent = new Intent(getContext(), GetCouponCentreActivity.class);
                CouponlDetailListFragmentFragment.this.startActivity(intent);

                break;
            default:
                break;
        }
    }
}
