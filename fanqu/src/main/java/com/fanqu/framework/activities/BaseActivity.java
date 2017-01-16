
package com.fanqu.framework.activities;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;

import com.fanqu.R;
import com.fanqu.framework.SystemBarTintManager;
import com.fanqu.framework.autolayout.AutoUtils;
import com.fanqu.framework.fragment.BaseFragment;
import com.fanqu.framework.model.ToolBarOptions;
import com.jaeger.library.StatusBarUtil;
import com.trello.rxlifecycle.components.support.RxAppCompatActivity;

import cn.sharesdk.framework.ShareSDK;

@SuppressLint("NewApi")
public abstract class BaseActivity extends RxAppCompatActivity {
	public static final String KEY_UUID = "uuid";

	private boolean mIsDestroyed = false;
	private static Handler handler;

	private Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setStatusBar();
		ShareSDK.initSDK(this);
		 AutoUtils.setSize(this, false, 1080, 1920);// 没有状态栏,设计尺寸的宽高
		initthem();
		if (getLayoutId() != 0) {
			setContentView(getLayoutId());
		}
	}
	protected void setStatusBar() {
		StatusBarUtil.setColor(this, getResources().getColor(R.color.colorPrimary));
	}

	protected <T extends View> T findView(int resId) {
		return (T) (findViewById(resId));
	}

	/**
	 * 初始化沉浸式狀態欄
	 */

	private void initthem() {
		// TODO Auto-generated method stub
		/**
		 * 沉浸式状态栏
		 */
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
			// 状态栏透明 需要在创建SystemBarTintManager 之前调用。
			setTranslucentStatus(true);
		}

		SystemBarTintManager tintManager = new SystemBarTintManager(this);
		tintManager.setStatusBarTintEnabled(true);
		// 使StatusBarTintView 和 actionbar的颜色保持一致，风格统一。 设置沉浸式状态
		tintManager.setStatusBarTintResource(0x000000000);
		// tintManager.setStatusBarTintDrawable(getResources().getDrawable(R.drawable.bg1));
		tintManager.setStatusBarDarkMode(false, this);
		// 设置状态栏的文字颜色
		tintManager.setStatusBarDarkMode(false, this);
	}

	@Override
	public final void setContentView(int i) {
		super.setContentView(i);
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
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	
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

	/**
	 * 延时弹出键盘
	 *
	 * @param focus 键盘的焦点项
	 */
	protected void showKeyboardDelayed(View focus) {
		final View viewToFocus = focus;
		if (focus != null) {
			focus.requestFocus();
		}

		getHandler().postDelayed(new Runnable() {
			@Override
			public void run() {
				if (viewToFocus == null || viewToFocus.isFocused()) {
					showKeyboard(true);
				}
			}
		}, 200);
	}
	protected final Handler getHandler() {
		if (handler == null) {
			handler = new Handler(getMainLooper());
		}
		return handler;
	}
	protected void showKeyboard(boolean isShow) {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		if (isShow) {
			if (getCurrentFocus() == null) {
				imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
			} else {
				imm.showSoftInput(getCurrentFocus(), 0);
			}
		} else {
			if (getCurrentFocus() != null) {
				imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
			}
		}
	}

	public void setToolBar(int toolBarId, ToolBarOptions options) {
		toolbar = (Toolbar) findViewById(toolBarId);
		if (options.titleId != 0) {
			toolbar.setTitle(options.titleId);
		}
		String title = options.titleString;
		if (!TextUtils.isEmpty(title)) {
			toolbar.setTitle(options.titleString);
		} else {
			toolbar.setTitle("");
		}
		//  if (options.logoId != 0) {
		//toolbar.setLogo(options.logoId);
		//}
		setSupportActionBar(toolbar);

		if (options.isNeedNavigate) {
			toolbar.setNavigationIcon(options.navigateId);
			toolbar.setNavigationOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					onNavigateUpClicked();
				}
			});
		}
	}

	public void setToolBar(int toolbarId, int titleId, int logoId) {
		toolbar = (Toolbar) findViewById(toolbarId);
		toolbar.setTitle(titleId);
		if (logoId != 0) {
			toolbar.setLogo(logoId);
		}
		setSupportActionBar(toolbar);
	}

	public Toolbar getToolBar() {
		return toolbar;
	}

	public int getToolBarHeight() {
		if (toolbar != null) {
			return toolbar.getHeight();
		}

		return 0;
	}

	public BaseFragment switchContent(BaseFragment fragment) {

		return switchContent(fragment, false);
	}

	protected BaseFragment switchContent(BaseFragment fragment, boolean needAddToBackStack) {
		FragmentManager fm = getSupportFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(fragment.getContainerId(), fragment);
		if (needAddToBackStack) {
			fragmentTransaction.addToBackStack(null);
		}
		try {
			fragmentTransaction.commitAllowingStateLoss();
		} catch (Exception e) {

		}

		return fragment;
	}

	public void onNavigateUpClicked() {
		onBackPressed();
	}
	/**
	 * @param clazz 跳转
	 */
	protected void jumpActivity(Class clazz) {
		Intent intent = new Intent(this, clazz);
		startActivity(intent);
		overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
	}
}
