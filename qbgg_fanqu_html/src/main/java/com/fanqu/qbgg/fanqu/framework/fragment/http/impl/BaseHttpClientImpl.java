package com.fanqu.qbgg.fanqu.framework.fragment.http.impl;

import com.hnzteict.httpClient.PersistentCookieStore;
import com.hnzteict.httpClient.SyncHttpClient;

import android.content.Context;

public abstract class BaseHttpClientImpl {
	protected SyncHttpClient mSynClient;
	protected Context mContext;

	public BaseHttpClientImpl(Context context) {
		mContext = context;
		mSynClient = new SyncHttpClient();
		mSynClient.setCookieStore(new PersistentCookieStore(context));//开启Cookie
		mSynClient.setTimeout(15000);//设置超时时间为15s
	}
}
