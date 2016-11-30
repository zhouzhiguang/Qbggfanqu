/*
 * Copyright (c) 2014,KJPush Open Source Project,张涛.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.kymjs.push.core;

import org.kymjs.push.KJPushConfig;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.net.ConnectivityManager;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * 整个推送核心管理者，负责控制整个推送功能
 * 
 * @author kymjs (http://my.oschina.net/kymjs)
 * 
 */
public class KJPushManager {

    /* package */static Class<?> receiverClazz;
    private static boolean isRunning = false; // 考虑多APP重用Service唯一，不再做重复调用判断
    private IPushService iService;
    private Context cxt;
    private final ProxyReceiver proxyReceiver = new ProxyReceiver();

    private KJPushManager() {}

    public static class KJPushManagerHolder {
        private static KJPushManager instance = new KJPushManager();
    }

    public static KJPushManager create() {
        return KJPushManagerHolder.instance;
    }

    /**
     * 开启推送进程
     * 
     * @param cxt
     */
    public void startWork(Context cxt, Class<? extends PushReceiver> clazz) {
        this.cxt = cxt;
        isRunning = true;
        receiverClazz = clazz;
        startPullService();
        startReceiver();
    }

    /**
     * 停止推送进程
     */
    public void stopWork() {
        if (isRunning) {
            isRunning = false;
            destroyPullService();
            stopReceiver();
        }
    }

    /************************* private method *****************************/

    /**
     * 启动拉去数据的服务进程
     */
    private void startPullService() {
        try {
            Intent service = new Intent(KJPushConfig.ACTION_PULL_SERVICE);
            cxt.bindService(service, conn, Context.BIND_AUTO_CREATE);
        } catch (Exception e) {
        }
    }

    /**
     * 结束拉去数据的服务进程
     */
    private void destroyPullService() {
        try {
            cxt.unbindService(conn);
        } catch (Exception e) {
        }
    }

    /**
     * 监听系统的一些广播
     */
    private void startReceiver() {
        try {
            IntentFilter filter = new IntentFilter();
            filter.addAction(Intent.ACTION_SCREEN_OFF);
            filter.addAction(Intent.ACTION_SCREEN_ON);
            filter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
            cxt.registerReceiver(proxyReceiver, filter);
        } catch (Exception e) {
        }

    }

    /**
     * 取消监听系统的一些广播
     */
    private void stopReceiver() {
        try {
            cxt.unregisterReceiver(proxyReceiver);
        } catch (Exception e) {
        }
    }

    /****************************** inside object **************************************/

    private final ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceDisconnected(ComponentName name) {}

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            iService = IPushService.Stub.asInterface(service);// 调用外部进程中service的转换方法
            try {
                iService.startPull();
            } catch (RemoteException e) {
                throw new RuntimeException(
                        "aidl error, not found open interface");
            }
        }
    };
}
