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

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;

/**
 * inner class <br>
 * 一个内部的代理接受者，与核心类分离，为对外开放做扩展
 * 
 * @author kymjs (http://my.oschina.net/kymjs)
 * 
 */
/* package */class ProxyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Intent proxyAction = new Intent();
        if (Intent.ACTION_SCREEN_OFF.equals(action)) { // 屏幕熄灭
            proxyAction.setAction(KJPushConfig.ACTION_SCREEN_OFF);
            proxyAction.setClass(context, KJPushManager.receiverClazz);
            context.sendBroadcast(proxyAction);
        } else if (Intent.ACTION_SCREEN_ON.equals(action)) { // 屏幕亮起
            proxyAction.setAction(KJPushConfig.ACTION_SCREEN_ON);
            proxyAction.setClass(context, KJPushManager.receiverClazz);
            context.sendBroadcast(proxyAction);
        } else if (ConnectivityManager.CONNECTIVITY_ACTION.equals(action)) { // 网络状态改变
            proxyAction.setAction(KJPushConfig.ACTION_NEWWORK_CHANGE);
            proxyAction.setClass(context, KJPushManager.receiverClazz);
            context.sendBroadcast(proxyAction);
        }
    }
}
