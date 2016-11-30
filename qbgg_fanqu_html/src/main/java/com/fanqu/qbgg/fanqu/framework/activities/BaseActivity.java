package com.fanqu.qbgg.fanqu.framework.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.fanqu.qbgg.fanqu.framework.annotation.Injector;
import com.fanqu.qbgg.fanqu.framework.autolayout.AutoUtils;

import cn.sharesdk.framework.ShareSDK;



@SuppressLint("NewApi")
public abstract class BaseActivity extends FragmentActivity {
	public static final String KEY_UUID = "uuid";

	private boolean mIsDestroyed = false;

	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		ShareSDK.initSDK(this);
		AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
	//	initthem();

		if (getLayoutId() != 0) {
			setContentView(getLayoutId());
		}
	}

	
	/**
	 * 初始化沉浸式狀態欄
	 */
	private void initthem() {
		// TODO Auto-generated method stub
//		/**
//		 * 沉浸式状态栏
//		 */
//		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//			// 状态栏透明 需要在创建SystemBarTintManager 之前调用。
//			setTranslucentStatus(true);
//		}
//
//		SystemBarTintManager tintManager = new SystemBarTintManager(this);
//		tintManager.setStatusBarTintEnabled(true);
//		// 使StatusBarTintView 和 actionbar的颜色保持一致，风格统一。 设置沉浸式状态
//		tintManager.setStatusBarTintResource(0x000000000);
//		// tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.drawable.bg1));
//		tintManager.setStatusBarDarkMode(false, this);
//		// 设置状态栏的文字颜色
//		tintManager.setStatusBarDarkMode(false, this);
	}

	@Override
	public final void setContentView(int i) {
		super.setContentView(i);
		Injector.inject(this, this);
	}

	@Override
	protected void onNewIntent(Intent intent) {
		// TODO Auto-generated method stub
		super.onNewIntent(intent);

		setIntent(intent);
	}

	@TargetApi(19)
	private void setTranslucentStatus(boolean on) {
		Window win = getWindow();
		WindowManager.LayoutParams winParams = win.getAttributes();
		final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
		 if (on) {
		 winParams.flags |= bits;
		 } else {
		 winParams.flags &= ~bits;
		 }
		 win.setAttributes(winParams);
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();

	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		mIsDestroyed = true;
		super.onDestroy();
	}

	@Override
	public final boolean isDestroyed() {
		// TODO Auto-generated method stub
		return mIsDestroyed;
	}

	protected final void callbackForResult(int resultCode) {
		Intent data = new Intent();

		callbackForResult(resultCode, data);
	}

	protected final void callbackForResult(int resultCode, Intent data) {
		data.putExtra(KEY_UUID, getIntent().getStringExtra(KEY_UUID));

		setResult(resultCode, data);
	}

	protected abstract int getLayoutId();

	protected void addFragment(int containerId, Fragment fragment) {
		if (fragment != null && !fragment.isAdded()) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			fragmentTransaction.add(containerId, fragment);
			fragmentTransaction.commitAllowingStateLoss();
		}
	}

	protected void replaceFragment(int containerId, Fragment fragment) {
		if (fragment != null && !fragment.isAdded()) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			fragmentTransaction.replace(containerId, fragment);
			fragmentTransaction.commitAllowingStateLoss();
		}
	}

}
