package com.fanqu.personcentre.adapter;

import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fanqu.R;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.main.util.DensityUtil;
import com.fanqu.personcentre.frgment.CouponlDetailListFragmentFragment;
import com.fanqu.personcentre.model.CouponlDetailEntity;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的优惠卷适配器
 */

public class CouponViewPageIndicatorAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
    private LayoutInflater inflater;
    private String[] titles;
    private Activity activity;
    private List<CouponlDetailEntity> dates;
    private List<CouponlDetailEntity> unuseddates, alreadyuseddates, outdatelist;//1是未使用 0已经使用-1已经过期
    private FragmentManager manager;

    public CouponViewPageIndicatorAdapter(Activity activity, FragmentManager manager, List<CouponlDetailEntity> dates) {
        super(manager);
        this.dates = dates;
        this.manager = manager;
        initcontentDate(dates);
        this.activity = activity;
        inflater = activity.getLayoutInflater();
        titles = new String[]{
                activity.getString(R.string.unused),
                activity.getString(R.string.already_used),
                activity.getString(R.string.out_date)
        };
    }

    private void initcontentDate(List<CouponlDetailEntity> dates) {
        if (dates != null) {
            outdatelist = new ArrayList<>();
            unuseddates = new ArrayList<>();
            alreadyuseddates = new ArrayList<>();
            int i = 0;
            //  优惠券状态state  1未使用0已经使用-1已经过期
            for (CouponlDetailEntity entity : dates) {
                i = i + 1;
                int state = entity.getCouponstate();
                switch (state) {
                    case -1:
                        outdatelist.add(entity);
                        break;
                    case 0:
                        alreadyuseddates.add(entity);
                        break;
                    case 1:
                        if (i % 2 == 0) {
                            entity.setCouponcondition(0);
                        }
                        unuseddates.add(entity);
                        break;
                }
            }
        }
    }

    @Override
    public int getCount() {

        return titles.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        TextView textView;

        //这里还要加数量
        if (convertView == null) {
            textView = new TextView(activity);
            textView.setTextSize(DensityUtil.px2dip(activity, activity.getResources().getDimension(R.dimen.dimen_50px)));
            textView.setGravity(Gravity.CENTER);
            convertView = textView;
        } else {
            textView = (TextView) convertView;
        }
        String title = titles[position];
        StringBuilder builder = new StringBuilder();
        builder.append(title);
        if (position == 0) {
            if (unuseddates.size() > 0) {

                builder.append("(");
                builder.append(unuseddates.size());
                builder.append(")");
            }

        } else if (position == 1) {
            if (alreadyuseddates.size() > 0) {

                builder.append("(");
                builder.append(alreadyuseddates.size());
                builder.append(")");
            }
        } else {

            builder.append("(");
            builder.append(outdatelist.size());
            builder.append(")");
        }
        textView.setText(builder.toString());
//        textView.setText(titles[position]);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        AutoUtils.auto(textView);
        return convertView;

    }

    @Override
    public Fragment getFragmentForPage(int position) {
        //优惠列表
        CouponlDetailListFragmentFragment fragment = null;
        if (position == 0) {
            fragment = CouponlDetailListFragmentFragment.newInstance(unuseddates);
//            fragment = new CouponlDetailListFragmentFragment();
//            Bundle bundle = new Bundle();
//            bundle.putParcelableArrayList(CouponlDetailListFragmentFragment.ARG_COLUMN_COUNT, (ArrayList<? extends Parcelable>) unuseddates);
//            fragment.setArguments(bundle);
//            if (fragment.isAdded()){
//                manager.beginTransaction().show(fragment);
//            }
        } else if (position == 1) {
            fragment = CouponlDetailListFragmentFragment.newInstance(alreadyuseddates);
//            fragment = new CouponlDetailListFragmentFragment();
//            if (fragment.isAdded()){
//                manager.beginTransaction().show(fragment);
//            }
//            Bundle bundle = new Bundle();
//            bundle.putParcelableArrayList(CouponlDetailListFragmentFragment.ARG_COLUMN_COUNT, (ArrayList<? extends Parcelable>) alreadyuseddates);
//            fragment.setArguments(bundle);
        } else {
            fragment = CouponlDetailListFragmentFragment.newInstance(outdatelist);
//            fragment = new CouponlDetailListFragmentFragment();
//            Bundle bundle = new Bundle();
//            bundle.putParcelableArrayList(CouponlDetailListFragmentFragment.ARG_COLUMN_COUNT, (ArrayList<? extends Parcelable>) outdatelist);
//            fragment.setArguments(bundle);
        }

        return fragment;
    }
}
