package com.fanqu;

import org.kymjs.push.core.PushReceiver;

import android.content.Context;
import android.content.Intent;


public class MyReceiver extends PushReceiver {

    @Override
    public void onNetworkStateChange(Context context, Intent intent) {
       System.out.println("改变网络");
    }

    @Override
    public void onScreenOff(Context context, Intent intent) {
    	 System.out.println("屏幕关闭");
    	 
    }

    @Override
    public void onScreenOn(Context context, Intent intent) {
    	System.out.println("屏幕开启");
    }

    @Override
    public void onPullData(Context context, Intent intent) {
    	System.out.println("请求服务器");
    }

}
