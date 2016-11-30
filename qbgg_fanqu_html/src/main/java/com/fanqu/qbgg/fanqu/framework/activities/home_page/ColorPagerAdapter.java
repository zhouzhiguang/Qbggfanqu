package com.fanqu.qbgg.fanqu.framework.activities.home_page;

import java.util.ArrayList;
import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class ColorPagerAdapter extends PagerAdapter {

	private GuideActivity guideActivity;
	private List<View> mViewList;

	public ColorPagerAdapter(GuideActivity activity, List<View> mViewList) {
		// TODO Auto-generated constructor stub
		this.guideActivity = activity;
		this.mViewList = mViewList;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mViewList.size();
	}

	@Override
	public boolean isViewFromObject(View view, Object object) {
		// TODO Auto-generated method stub
		return view == object;
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		container.addView(mViewList.get(position));
		return mViewList.get(position);
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		if (object instanceof View) {
			container.removeView((View) object);
		}
	}

	@Override
	public int getItemPosition(Object object) {
		// TODO Auto-generated method stub
		return POSITION_NONE;
	}

}
