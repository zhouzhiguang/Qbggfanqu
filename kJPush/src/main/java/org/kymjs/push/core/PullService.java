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

import java.lang.ref.WeakReference;

import org.kymjs.push.KJPushConfig;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

/**
 * 负责从服务器端拉取消息的独立服务进程
 * 
 * @author kymjs (http://my.oschina.net/kymjs)
 * 
 */
public class PullService extends Service {

    private final IBinder holder = new Holder(this);
    private AlarmManager mAlarmMgr;

    @Override
    public IBinder onBind(Intent intent) {
        return holder;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        startForeground(0, new Notification());
        mAlarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        stopForeground(true);
    }

    /***************************** core method start *********************************/
    private void startRequestAlarm() {
        cancelRequestAlarm();
        // 从1秒后开始，每隔2分钟执行getOperationIntent()
        // 注意，这个2分钟只是正常情况下的2分钟，实际情况可能不同系统的处理策略而被延长，比如坑爹的粗粮系统上可能被延长至5分钟
        mAlarmMgr.setRepeating(AlarmManager.RTC_WAKEUP,
                System.currentTimeMillis() + 1000, KJPushConfig.PALPITATE_TIME,
                getOperationIntent());
    }

    /**
     * 即使启动PendingIntent的原进程结束了的话,PendingIntent本身仍然还存在，可在其他进程（
     * PendingIntent被递交到的其他程序）中继续使用.
     * 如果我在从系统中提取一个PendingIntent的，而系统中有一个和你描述的PendingIntent对等的PendingInent,
     * 那么系统会直接返回和该PendingIntent其实是同一token的PendingIntent，
     * 而不是一个新的token和PendingIntent。然而你在从提取PendingIntent时，通过FLAG_CANCEL_CURRENT参数，
     * 让这个老PendingIntent的先cancel()掉，这样得到的pendingInten和其token的就是新的了。
     */
    private void cancelRequestAlarm() {
        mAlarmMgr.cancel(getOperationIntent());
    }

    /**
     * 采用轮询方式实现消息推送<br>
     * 每次被调用都去执行一次{@link #PushReceiver}onReceive()方法
     * 
     * @return
     */
    private PendingIntent getOperationIntent() {
        Intent intent = new Intent(this, PushReceiver.class);
        intent.setAction(KJPushConfig.ACTION_PULL_ALARM);
        PendingIntent operation = PendingIntent.getBroadcast(this, 0, intent,
                PendingIntent.FLAG_UPDATE_CURRENT);
        return operation;
    }

    /***************************** core method end *********************************/

    /**
     * 对外开放两个接口负责控制计时器的开关
     * 
     * @author kymjs (http://my.oschina.net/kymjs)
     * 
     */
    private static class Holder extends IPushService.Stub {
        WeakReference<PullService> _service;

        public Holder(PullService service) {
            _service = new WeakReference<PullService>(service);
        }

        @Override
        public void startPull() throws RemoteException {
            _service.get().startRequestAlarm();
        }

        @Override
        public void stopPull() throws RemoteException {
            _service.get().cancelRequestAlarm();
        }
    }
}
