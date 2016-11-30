package com.fanqu.qbgg.fanqu.framework.activities.home_page;

import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.fanqu.PhoneGap2Activity;
import com.fanqu.R;
import com.fanqu.SplashActivity;
import com.fanqu.qbgg.fanqu.framework.Constants;
import com.fanqu.qbgg.fanqu.framework.activities.BaseActivity;
import com.fanqu.qbgg.fanqu.framework.annotation.ViewId;
import com.fanqu.qbgg.fanqu.framework.utills.ApplicationUtils;
import com.fanqu.qbgg.fanqu.framework.utills.PreferenceUitl;

import java.util.ArrayList;
import java.util.List;


public class GuideActivity extends BaseActivity implements View.OnClickListener {

	@ViewId(R.id.activity_guide_root_view)
	RelativeLayout mActivity_guide_root_view;
	@ViewId(R.id.activity_guide_iv)
	View mView;
	@ViewId(R.id.activity_guide_viewpager)
	ViewPager mViewPager;
	@ViewId(R.id.activity_guide__indicator_ll)
	LinearLayout mLinearlayout;
	/**
	 * 跳过
	 */
	@ViewId(R.id.activity_guide__skip_tv)
	TextView mTextView;
	/**
	 * 下一頁
	 */
	@ViewId(R.id.activity_guide_next_btn)
	ImageButton mImageButton;
	/**
	 * 完成
	 */
	@ViewId(R.id.activity_guide_done_tv)
	TextView mTextView_finish;
	private List<View> mViewList;
	private ColorPagerAdapter pagerAdapter;
	/* 下面黑色移动圆点 */
	private TextView animIndicator;
	private ValueAnimator gradientAnim;
	private int totalDistance;// 小黑点要移动的全部距离
	private int startX;// 小黑点开始位置
	private static final int DURATION = 4 * 1000;
	int[] colors = new int[] { Color.parseColor("#ffff4444"),//
			Color.parseColor("#ff0099cc"),//
			Color.parseColor("#ff99cc00"),//
			Color.parseColor("#ffffbb33"),//
			Color.parseColor("#ffDA498C") };
	private boolean isfrist_start_app = false;// 判断第一次启动应用程序
	private PreferenceUitl preference;

	@Override
	protected int getLayoutId() {
		// TODO Auto-generated method stub
		return 0;  // R.layout.activity_guide_layout;
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		isfrist_start_app = PreferenceUitl.getInstance(this).getBoolean(
				Constants.FRIST_START_APP, false);
		preference = PreferenceUitl.getInstance(this);
		int savedVersionCode = preference.getInt(Constants.NAME_APP_PARAMS, -1);
		int currentVersionCode = ApplicationUtils.getVersionCode(
				getApplicationContext(), getPackageName());
		// 判断当前保存的版本和现在运行版本升级后继续执行此方法
		if (savedVersionCode < currentVersionCode) {
			setContentView(R.layout.activity_guide_layout);
			preference.saveInt(Constants.NAME_APP_PARAMS, currentVersionCode);
			initdate();
			setupAnim();
			setupIndicator();
			initListener();
		} else {
			Intent intent = new Intent(this, SplashActivity.class);
			startActivity(intent);
			finish();

		}

	}

	@SuppressLint("NewApi")
	private void setupAnim() {

		gradientAnim = ObjectAnimator.ofInt(mView, "backgroundColor", colors);
		gradientAnim.setEvaluator(new ArgbEvaluator());
		gradientAnim.setDuration(DURATION);
	}

	/**
	 * 设置指示标志
	 */
	@SuppressLint("NewApi")
	private void setupIndicator() {
		final Drawable indicatorNormal = getResources().getDrawable(
				R.drawable.indicator_normal_background);
		final Drawable indicatorSelected = getResources().getDrawable(
				R.drawable.indicator_selected_background);

		int size = getResources().getDimensionPixelSize(R.dimen.dimen_8dp);

		mLinearlayout.removeAllViews();
		final TextView[] indicators = new TextView[mViewList.size()];
		for (int i = 0; i < indicators.length; i++) {

			indicators[i] = new TextView(GuideActivity.this);
			indicators[i].setGravity(Gravity.CENTER);
			LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
					size, size);

			if (i != indicators.length - 1) {
				params.setMargins(0, 0, size, 0);
			} else {
				params.setMargins(0, 0, 0, 0);
			}
			indicators[i].setLayoutParams(params);
			indicators[i].setBackground(indicatorNormal);
			mLinearlayout.addView(indicators[i]);
		}

		animIndicator = new TextView(GuideActivity.this);
		animIndicator
				.setLayoutParams(new LinearLayout.LayoutParams(size, size));
		animIndicator.setBackground(indicatorSelected);
		mActivity_guide_root_view.addView(animIndicator);

		mLinearlayout.getViewTreeObserver().addOnPreDrawListener(
				new ViewTreeObserver.OnPreDrawListener() {
					public boolean onPreDraw() {
						mLinearlayout.getViewTreeObserver()
								.removeOnPreDrawListener(this);

						Rect rootRect = new Rect();
						Point globalOffset = new Point();
						mActivity_guide_root_view.getGlobalVisibleRect(
								rootRect, globalOffset);

						Rect firstRect = new Rect();
						mLinearlayout.getChildAt(0).getGlobalVisibleRect(
								firstRect);
						firstRect.offset(-globalOffset.x, -globalOffset.y);

						Rect lastRect = new Rect();
						mLinearlayout.getChildAt(indicators.length - 1)
								.getGlobalVisibleRect(lastRect);

						GuideActivity.this.totalDistance = lastRect.left
								- mLinearlayout.getLeft();
						GuideActivity.this.startX = firstRect.left;

						ViewCompat.setTranslationX(animIndicator,
								firstRect.left);
						ViewCompat
								.setTranslationY(animIndicator, firstRect.top);
						return true;
					}
				});
	}

	/**
	 * 初始化显示数据
	 */
	private void initdate() {
		mViewList = new ArrayList<View>();

		View view1 = LayoutInflater.from(this).inflate(
				R.layout.activity_guide_head_01, mViewPager, false);

		View view2 = LayoutInflater.from(this).inflate(
				R.layout.activity_guide_head_02, mViewPager, false);

		View view3 = LayoutInflater.from(this).inflate(
				R.layout.activity_guide_head_03, mViewPager, false);
		mViewList.add(view1);
		mViewList.add(view2);
		mViewList.add(view3);
		pagerAdapter = new ColorPagerAdapter(GuideActivity.this, mViewList);
		mViewPager.setAdapter(pagerAdapter);
	}

	/**
	 * 初始化事件监听
	 */
	private void initListener() {
		// TODO Auto-generated method stub

		ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.SimpleOnPageChangeListener() {
			@SuppressLint("NewApi")
			@Override
			public void onPageScrolled(int position, float positionOffset,
					int positionOffsetPixels) {

				if (pagerAdapter.getCount() > 0) {

					float length = (position + positionOffset)
							/ (pagerAdapter.getCount() - 1);
					int progress = (int) (length * DURATION);

					float path = length * GuideActivity.this.totalDistance;
					ViewCompat.setTranslationX(animIndicator,
							GuideActivity.this.startX + path);

					/* 设置过度颜色 */
					gradientAnim.setCurrentPlayTime(progress);
				}
			}

			@SuppressLint("NewApi")
			@Override
			public void onPageScrollStateChanged(int state) {

				// /* 关闭硬件加速 */
				// if (state != ViewPager.SCROLL_STATE_IDLE) {
				// final int childCount = mViewList.size();
				// for (int i = 0; i < childCount; i++) {
				// mViewPager.getChildAt(i).setLayerType(
				// View.LAYER_TYPE_NONE, null);
				// }
				// }
			}

			@Override
			public void onPageSelected(int position) {

				if (position == pagerAdapter.getCount() - 1) {
					GuideActivity.this.mTextView.setVisibility(View.GONE);
					mImageButton.setVisibility(View.GONE);
					GuideActivity.this.mTextView_finish
							.setVisibility(View.VISIBLE);
				} else {
					GuideActivity.this.mTextView.setVisibility(View.VISIBLE);
					GuideActivity.this.mImageButton.setVisibility(View.VISIBLE);
					GuideActivity.this.mTextView_finish
							.setVisibility(View.GONE);
				}
			}
		};
		mViewPager.addOnPageChangeListener(pageChangeListener);

		mTextView.setOnClickListener(this);
		mImageButton.setOnClickListener(this);
		mTextView_finish.setOnClickListener(this);

	}

	

	/**
	 * 完成滑动
	 */
	private void finishpage() {
		// TODO Auto-generated method stub
		skip();
	}

	/**
	 * 下一頁
	 */
	private void nextpage() {
		// TODO Auto-generated method stub
		mViewPager.setCurrentItem(mViewPager.getCurrentItem() + 1, true);
	}

	/**
	 * 跳過
	 */
	private void skip() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(GuideActivity.this, PhoneGap2Activity.class);
		startActivity(intent);
		finish();
		overridePendingTransition(R.anim.activity_in, R.anim.activity_out);

	}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		
			// TODO Auto-generated method stub
			switch (v.getId()) {
			case R.id.activity_guide__skip_tv:
				skip();
				break;
			case R.id.activity_guide_next_btn:
				nextpage();
				break;
			case R.id.activity_guide_done_tv:
				finishpage();
				break;

			default:
				break;
			}

		}
	
}
