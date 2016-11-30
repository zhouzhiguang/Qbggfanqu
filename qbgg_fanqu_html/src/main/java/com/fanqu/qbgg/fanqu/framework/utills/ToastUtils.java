package com.fanqu.qbgg.fanqu.framework.utills;

import android.content.Context;
import android.view.Gravity;
import android.widget.Toast;

/**
 * 管理所有Toast 防止连续点击产生的消息滞留
 * 
 *
 *
 */
public class ToastUtils {

	private static Toast mToast;

	public static void showToast(Context context, int strResId) {
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context, strResId, Toast.LENGTH_LONG);
		mToast.show();
	}

	public static void showToast(Context context, String str) {
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context, str, Toast.LENGTH_LONG);
		mToast.show();
	}

	public static void cancelToast() {
		if (mToast != null) {
			mToast.cancel();
		}
	}

	public static void showCenterToast(Context context, String str) {
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context, str, Toast.LENGTH_LONG);
		mToast.setGravity(Gravity.CENTER, 0, 0);
		mToast.show();
	}

	public static void showCenterToast(Context context, int strResId) {
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context, strResId, Toast.LENGTH_LONG);
		mToast.setGravity(Gravity.CENTER, 0, 0);
		mToast.show();
	}

	/**
	 * 显示通用的Toast提醒
	 * 
	 * @param context
	 */
	public static void showCommToast(Context context) {
		if (mToast != null) {
			mToast.cancel();
		}
		mToast = Toast.makeText(context, "服务不可用", Toast.LENGTH_LONG);
		mToast.show();
	}

}
