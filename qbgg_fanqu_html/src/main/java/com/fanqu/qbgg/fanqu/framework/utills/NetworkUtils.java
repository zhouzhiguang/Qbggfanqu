package com.fanqu.qbgg.fanqu.framework.utills;

import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Enumeration;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

public class NetworkUtils {
	public static boolean isConnectivityActive(Context context) {
		NetworkInfo networkinfo = ((ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo();
		return networkinfo != null && networkinfo.isConnected();
	}

	public static boolean isMobileConnected(Context context) {
		NetworkInfo networkinfo = ((ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo();
		return networkinfo != null
				&& networkinfo.getType() == ConnectivityManager.TYPE_MOBILE
				&& networkinfo.isConnected();
	}

	public static boolean isWifiConnected(Context context) {
		NetworkInfo networkinfo = ((ConnectivityManager) context
				.getSystemService(Context.CONNECTIVITY_SERVICE))
				.getActiveNetworkInfo();
		return networkinfo != null
				&& networkinfo.getType() == ConnectivityManager.TYPE_WIFI
				&& networkinfo.isConnected();
	}

	/**
	 * 获取本机ip
	 */
	public static String getLocalIpAddress() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (SocketException ex) {
			Log.e("WifiPreference IpAddress", ex.toString());
		}
		return null;
	}

}
