package com.fanqu.qbgg.fanqu.framework.fragment.http.impl;


import android.content.Context;

import com.fanqu.qbgg.fanqu.framework.fragment.http.CommonHttpClient;

public class CommonHttpClientFactory {

		public static CommonHttpClient buildHttpClient(Context context){
			return  new CommonHttpClientImpl(context);
		}
	}