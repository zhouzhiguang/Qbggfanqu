package com.fanqu.personcentre.adapter;

import android.app.Activity;
import android.os.Bundle;
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
import com.fanqu.personcentre.frgment.IntegralDetailFragment;
import com.shizhefei.view.indicator.IndicatorViewPager;

/**
 * view page 指示器
 */

public class ViewPageIndicatorAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter {
    private LayoutInflater inflater;
    private String[] titles;
    private Activity activity;

    public ViewPageIndicatorAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    public ViewPageIndicatorAdapter(Activity activity, FragmentManager supportFragmentManager) {
        super(supportFragmentManager);
        this.activity = activity;
        inflater = activity.getLayoutInflater();
        titles = new String[]{activity.getString(R.string.all), activity.getString(R.string.income), activity.getString(R.string.expend)};
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup viewGroup) {
        TextView textView;
//        if (container == null) {
//            textView = new TextView(activity);
//
//            textView.setText(titles[position]);
//        } else {
//            textView = (TextView) convertView;
//        }

        if (convertView == null) {
            textView = new TextView(activity);
            textView.setTextSize(DensityUtil.px2dip(activity, activity.getResources().getDimension(R.dimen.dimen_50px)));
            textView.setGravity(Gravity.CENTER);
            convertView = textView;
        } else {
            textView = (TextView) convertView;
        }
        textView.setText(titles[position]);
        ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        textView.setLayoutParams(params);
        AutoUtils.auto(textView);
        return convertView;
    }

    @Override
    public Fragment getFragmentForPage(int position) {
        IntegralDetailFragment fragment = new IntegralDetailFragment();
        Bundle bundle = new Bundle();
        bundle.putInt("position", position);
        fragment.setArguments(bundle);

        return fragment;
    }
}
