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
package org.kymjs.push;

/**
 * 推送功能的常量与配置器
 * 
 * @author kymjs (http://my.oschina.net/kymjs)
 */
public class KJPushConfig {
    public static final String ACTION_PULL_SERVICE = "org.kymjs.push.service.pull_service";

    public static final String ACTION_PULL_ALARM = "org.kymjs.push.receiver.pull";

    public static final String ACTION_SCREEN_ON = "org.kymjs.push.receiver.screen_on";
    public static final String ACTION_SCREEN_OFF = "org.kymjs.push.receiver.screen_off";
    public static final String ACTION_NEWWORK_GPRS = "org.kymjs.push.receiver.newwork_gprs";
    public static final String ACTION_NEWWORK_WIFI = "org.kymjs.push.receiver.newwork_wifi";
    public static final String ACTION_NEWWORK_CHANGE = "org.kymjs.push.receiver.newwork_change";

    public static int PALPITATE_TIME = 180000; // 心跳间隔，默认流量时3分钟一次，WiFi时1分钟一次
}
