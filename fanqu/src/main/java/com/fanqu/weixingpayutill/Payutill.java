package com.fanqu.weixingpayutill;

import android.util.Log;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;

public class Payutill {

	
	/**
	 生成签名
	 */

	public static String genPackageSign(List<BasicNameValuePair> params) {
		StringBuilder sb = new StringBuilder();
		
		for (int i = 0; i < params.size(); i++) {
			sb.append(params.get(i).getName());
			sb.append('=');
			sb.append(params.get(i).getValue());
			sb.append('&');
		}
		sb.append("key=");
		sb.append("xianlanzi0123456xianlanzi0123456");
		

		String packageSign = MD5.getMessageDigest(sb.toString().getBytes()).toUpperCase();
		Log.e("orion",packageSign);
		return packageSign;
	}
}
