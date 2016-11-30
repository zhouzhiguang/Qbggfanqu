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

/**
 * 推送功能的对外实现接口，开发者只需要继承本类，并实现相应方法即可得到需要的功能
 * 
 * @author kymjs (http://my.oschina.net/kymjs)
 * 
 */
public abstract class PushReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (KJPushConfig.ACTION_NEWWORK_CHANGE.equals(action)) { // 网络状态改变
            onNetworkStateChange(context, intent);
        } else if (KJPushConfig.ACTION_SCREEN_OFF.equals(action)) { // 屏幕熄灭
            onScreenOff(context, intent);
        } else if (KJPushConfig.ACTION_SCREEN_ON.equals(action)) { // 屏幕亮起
            onScreenOn(context, intent);
        } else if (KJPushConfig.ACTION_PULL_ALARM.equals(action)) { // 心跳请求
            onTryPullData(context, intent);
        }
    }

    /**
     * 网络状态改变时回调
     * 
     * @param context
     * @param intent
     */
    public abstract void onNetworkStateChange(Context context, Intent intent);

    /**
     * 屏幕关闭时回调
     * 
     * @param context
     * @param intent
     */
    public abstract void onScreenOff(Context context, Intent intent);

    /**
     * 屏幕亮起时回调
     * 
     * @param context
     * @param intent
     */
    public abstract void onScreenOn(Context context, Intent intent);

    /**
     * 拉取数据时回调
     * 
     * @param context
     * @param intent
     */
    public abstract void onPullData(Context context, Intent intent);

    /**
     * 高级接口：需要服务器端单独提供一个接口，访问服务器由服务器判断当前是否有新数据，如果有则调用
     * {@link #onPullData(Context, Intent)}方法去拉取数据
     * 
     * @param context
     * @param intent
     */
    public void onTryPullData(Context context, Intent intent) {
        onPullData(context, intent);
    }
}
