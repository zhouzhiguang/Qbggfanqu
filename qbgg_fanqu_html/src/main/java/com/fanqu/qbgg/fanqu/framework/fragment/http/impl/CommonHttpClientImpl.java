package com.fanqu.qbgg.fanqu.framework.fragment.http.impl;

import android.content.Context;

import com.fanqu.qbgg.fanqu.framework.fragment.http.CommonHttpClient;



public class CommonHttpClientImpl extends BaseHttpClientImpl implements CommonHttpClient {

	public CommonHttpClientImpl(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getPrepayId(String params) {
		String url = BaseUrlFactory.getgetPrepayIdUrl();
		String response = mSynClient.post(params);
		
		return response;
	}

}
