package com.fanqu.qbgg.fanqu.framework.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import com.fanqu.qbgg.fanqu.framework.activities.BaseActivity;
import com.fanqu.qbgg.fanqu.framework.annotation.Injector;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.UUID;


public abstract class BaseFragment extends Fragment {
	protected View mMainView;
	protected HashMap<String, SoftReference<BaseFragment>> mCallbakFragments = new HashMap<String, SoftReference<BaseFragment>>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup viewgroup,
			Bundle bundle) {
		if (getLayoutId() != 0 && mMainView == null) {
			mMainView = inflater.inflate(getLayoutId(), viewgroup, false);
			Injector.inject(this, mMainView);
			
		}

		return mMainView;
	}

	protected abstract int getLayoutId();

	protected void handleResult(int requestCode, int resultCode, Intent data) {

	}

	protected void replaceFragment(BaseFragment fragment, int fragmentContainer) {

		if (this.isAdded() && fragment != null && !fragment.isAdded()) {
			FragmentManager fragmentManager = getChildFragmentManager();
			FragmentTransaction fragmentTransaction = fragmentManager
					.beginTransaction();
			fragmentTransaction.replace(fragmentContainer, fragment);
			fragmentTransaction.commitAllowingStateLoss();
		}
	}

	@Override
	public void onDestroyView() {
		// TODO Auto-generated method stub
		super.onDestroyView();

		ViewParent parent = mMainView.getParent();

		if (parent != null && parent instanceof ViewGroup) {
			((ViewGroup) parent).removeView(mMainView);
		}

		mMainView = null;
	}

	@Override
	public final void startActivityForResult(Intent intent, int requestCode) {
		// TODO Auto-generated method stub
		Fragment root = getRootFragment();

		if (root == null || !(root instanceof BaseFragment)) {
			super.startActivityForResult(intent, requestCode);
		} else {
			String key = UUID.randomUUID().toString();

			BaseFragment fragment = (BaseFragment) root;
			fragment.addCallbackFragment(key, this);
			intent.putExtra(BaseActivity.KEY_UUID, key);

			fragment.startActivityForResult(intent, requestCode);
		}
	}

	@Override
	public final void onActivityResult(int requestCode, int resultCode,
			Intent data) {
		// TODO Auto-generated method stub
		if (data == null) {
			handleResult(requestCode, resultCode, data);
			return;
		}

		String key = data.getStringExtra(BaseActivity.KEY_UUID);

		if (TextUtils.isEmpty(key)) {
			handleResult(requestCode, resultCode, data);
			return;
		}

		SoftReference<BaseFragment> fragmentRef = mCallbakFragments.get(key);

		if (fragmentRef == null) {
			return;
		}

		BaseFragment fragment = fragmentRef.get();
		mCallbakFragments.remove(key);

		if (fragment != null && fragment.isAdded()) {
			fragment.handleResult(requestCode, resultCode, data);
		}
	}

	protected void addCallbackFragment(String key, BaseFragment fragment) {
		mCallbakFragments.put(key, new SoftReference<BaseFragment>(fragment));
	}

	private Fragment getRootFragment() {
		Fragment parent = getParentFragment();

		if (parent == null) {
			return null;
		}

		while (parent.getParentFragment() != null) {
			parent = parent.getParentFragment();
		}

		return parent;
	}

	
}
