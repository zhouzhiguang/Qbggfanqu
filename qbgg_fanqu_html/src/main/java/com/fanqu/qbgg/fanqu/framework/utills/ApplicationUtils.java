package com.fanqu.qbgg.fanqu.framework.utills;

import java.io.File;
import java.util.List;

import android.app.ActivityManager;
import android.app.ActivityManager.RunningTaskInfo;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ResolveInfo;
import android.net.Uri;



public class ApplicationUtils {
	public static final int DEFAULT__VERSION_CODE = -1;

	public static String getVersionName(Context context, String packageName) {
		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo packInfo = packageManager
					.getPackageInfo(packageName, 0);
			return packInfo.versionName;
		} catch (NameNotFoundException ex) {
			// ignore
		}

		return null;
	}

	public static int getVersionCode(Context context, String packageName) {
		PackageManager packageManager = context.getPackageManager();
		try {
			PackageInfo packInfo = packageManager
					.getPackageInfo(packageName, 0);
			return packInfo.versionCode;

		} catch (NameNotFoundException ex) {
			ex.printStackTrace();
		}

		return DEFAULT__VERSION_CODE;
	}

	public static boolean isInastalled(Context context, String packageName) {
		boolean result = false;
		PackageManager packageManager = context.getPackageManager();

		try {
			packageManager.getPackageInfo(packageName, 0);
			result = true;

		} catch (NameNotFoundException ex) {
			// ignore
		}

		return result;
	}

	public static void installApp(Context context, String uriString) {
		Uri uri = Uri.parse(uriString);

		File file = new File(uri.getPath());
		if (!file.exists()) {
			return;
		}

		Intent intent = new Intent(Intent.ACTION_VIEW);
		intent.setDataAndType(uri, "application/vnd.android.package-archive");
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(intent);
	}

	public static String getLauncherActivityName(Context context, String pkgName) {
		PackageManager packageManager = context.getPackageManager();
		Intent intent = new Intent();

		intent.setAction(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_LAUNCHER);

		intent.setPackage(pkgName);

		List<ResolveInfo> ResolveInfoList = packageManager
				.queryIntentActivities(intent, 0);

		if (ResolveInfoList != null && ResolveInfoList.size() > 0) {
			return ResolveInfoList.get(0).activityInfo.name;
		}

		return null;
	}

	public static boolean isBackgroudOrForground(Context context) {
		boolean result = false;

		String pkgName = context.getPackageName();
		ActivityManager am = (ActivityManager) context
				.getSystemService(Context.ACTIVITY_SERVICE);
		List<RunningTaskInfo> taskList = am.getRunningTasks(Integer.MAX_VALUE);

		if (taskList != null && taskList.size() > 0) {
			for (RunningTaskInfo taskInfo : taskList) {
				ComponentName topActivity = taskInfo.topActivity;
				ComponentName baseActivity = taskInfo.baseActivity;
				if (topActivity.getPackageName().equals(pkgName)
						|| baseActivity.getPackageName().equals(pkgName)) {
					result = true;
					break;
				}
			}
		}

		return result;
	}

	public static void startApplication(Context context, String packageName) {
		try {
			Intent intent = context.getPackageManager()
					.getLaunchIntentForPackage(packageName);
			context.startActivity(intent);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}