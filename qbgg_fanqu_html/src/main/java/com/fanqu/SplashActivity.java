package com.fanqu;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;

import com.fanqu.R;
import com.fanqu.qbgg.fanqu.framework.utills.NetworkUtils;
import com.fanqu.qbgg.fanqu.framework.utills.ToastUtils;

public class SplashActivity extends Activity {
	private NotReceiver myNetReceiver;

//	@Override
//	protected int getLayoutId() {
//		// TODO Auto-generated method stub
//		return 0;// R.layout.activity_splash_layout;
//	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		LayoutInflater inflater = getLayoutInflater();
		View view = inflater.inflate(R.layout.activity_splash_layout, null);
		setContentView(view);
		if (NetworkUtils.isConnectivityActive(this)) {
			Handler handler = new Handler();
			handler.postDelayed(new Runnable() {

				public void run() {
					// TODO Auto-generated method stub
					Intent intent = new Intent(SplashActivity.this,
							PhoneGap2Activity.class);
					startActivity(intent);
					finish();
					overridePendingTransition(R.anim.activity_in,
							R.anim.activity_out);
				}
			}, 1000);
		} else {
			setContentView(view);
			IntentFilter mFilter = new IntentFilter();
			mFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
			myNetReceiver = new NotReceiver();
			registerReceiver(myNetReceiver, mFilter);
			ToastUtils.showToast(this, R.string.has_no_connection);
		}
	}

	private class NotReceiver extends BroadcastReceiver {

		private ConnectivityManager mConnectivityManager;

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			String action = intent.getAction();
			if (action.equals(ConnectivityManager.CONNECTIVITY_ACTION)) {

				mConnectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo netInfo = mConnectivityManager
						.getActiveNetworkInfo();
				if (netInfo != null && netInfo.isAvailable()) {

					Intent inten = new Intent(SplashActivity.this,
							PhoneGap2Activity.class);
					startActivity(inten);
					finish();
					overridePendingTransition(R.anim.activity_in,
							R.anim.activity_out);
					// /////////////网络连接
					// String name = netInfo.getTypeName();
					//
					// if(netInfo.getType()==ConnectivityManager.TYPE_WIFI){
					// /////WiFi网络
					//
					// }else
					// if(netInfo.getType()==ConnectivityManager.TYPE_ETHERNET){
					// /////有线网络
					//
					// }else
					// if(netInfo.getType()==ConnectivityManager.TYPE_MOBILE){
					// /////////3g网络
					//
					// }
				} else {
					// //////网络断开
					ToastUtils.showToast(context, R.string.has_no_connection);
				}
			}

		}

	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		if (myNetReceiver != null) {
			unregisterReceiver(myNetReceiver);
		}
	}

//	@Override
//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// TODO Auto-generated method stub
//		if (keyCode == KeyEvent.KEYCODE_BACK
//				&& event.getAction() == KeyEvent.ACTION_DOWN) {
//			moveTaskToBack(false);
//			return true;
//		}
//		return super.onKeyDown(keyCode, event);
//	}
}
