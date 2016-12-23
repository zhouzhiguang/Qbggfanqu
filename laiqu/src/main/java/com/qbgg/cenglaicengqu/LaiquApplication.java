package com.qbgg.cenglaicengqu;

import android.app.Application;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Environment;
import android.text.TextUtils;
import android.util.DisplayMetrics;

import com.netease.nim.uikit.ImageLoaderKit;
import com.netease.nim.uikit.NimUIKit;
import com.netease.nim.uikit.cache.FriendDataCache;
import com.netease.nim.uikit.cache.NimUserInfoCache;
import com.netease.nim.uikit.cache.TeamDataCache;
import com.netease.nim.uikit.contact.ContactProvider;
import com.netease.nimlib.sdk.NIMClient;
import com.netease.nimlib.sdk.SDKOptions;
import com.netease.nimlib.sdk.StatusBarNotificationConfig;
import com.netease.nimlib.sdk.auth.LoginInfo;
import com.netease.nimlib.sdk.msg.MessageNotifierCustomization;
import com.netease.nimlib.sdk.msg.constant.SessionTypeEnum;
import com.netease.nimlib.sdk.msg.model.IMMessage;
import com.netease.nimlib.sdk.team.model.Team;
import com.netease.nimlib.sdk.uinfo.UserInfoProvider;
import com.netease.nimlib.sdk.uinfo.model.NimUserInfo;
import com.qbgg.cenglaicengqu.main.acitvities.login.LoginActivity;
import com.qbgg.cenglaicengqu.main.util.Constants;
import com.qbgg.cenglaicengqu.main.util.LogUtil;
import com.qbgg.cenglaicengqu.main.util.PreferenceUitl;
import com.qbgg.cenglaicengqu.netease.DemoCache;
import com.qbgg.cenglaicengqu.netease.session.NimDemoLocationProvider;
import com.qbgg.cenglaicengqu.netease.util.crash.AppCrashHandler;
import com.qbgg.cenglaicengqu.netease.util.sys.SystemUtil;
import com.qbgg.network.request.nohttp.NohttpClient;
import com.qbgg.network.request.update.UpdateConfig;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/11/7.
 */

public class LaiquApplication extends Application {
    /**
     * 屏幕分辨率：屏幕宽度
     */
    public static int SCREEN_WIDTH = 0;

    /**
     * _屏幕分辨率：屏幕高度
     */
    public static int SCREEN_HEIGHT = 0;


    private static LaiquApplication mApplication;
    private SharedPreferences mSharedPreferences;


    public static LaiquApplication getInstance() {

        return mApplication;
    }


    public void onCreate() {

        super.onCreate();
        mApplication = this;
        initview();
        PreferenceUitl.getInstance(getApplicationContext()).init(getApplicationContext());
        initSDK();
        initupdate();
        //开启日志调试功能
        LogUtil.isDebug = true;

    }

    private void initview() {
        DisplayMetrics display = getApplicationContext().getResources().getDisplayMetrics();
        SCREEN_WIDTH = display.widthPixels;
        SCREEN_HEIGHT = display.heightPixels;
        // 屏幕的宽大于高宽高到转过来
        if (SCREEN_WIDTH > SCREEN_HEIGHT) {
            int flag = SCREEN_WIDTH;
            SCREEN_WIDTH = SCREEN_HEIGHT;
            SCREEN_HEIGHT = flag;
        }
    }

    /**
     * 初始化自动更新
     */
    private void initupdate() {

        UpdateConfig.init(this);
    }

    /**
     * 初始化用到的分享登录即时通通讯sdk
     */
    private void initSDK() {
        NohttpClient.init(this);
        DemoCache.setContext(this);
        // crash handler
        AppCrashHandler.getInstance(this);

        NIMClient.init(this, loginInfo(), options());

        if (inMainProcess()) {

            // 初始化UIKit模块
            initUIKit();
            // 初始化消息提醒
            //NIMClient.toggleNotification(UserPreferences.getNotificationToggle());
            // 注册通知消息过滤器
            //registerIMMessageFilter();
            // 注册语言变化监听
//            // registerLocaleReceiver(true);
        }


    }

    private UserInfoProvider infoProvider = new UserInfoProvider() {
        @Override
        public UserInfo getUserInfo(String account) {
            UserInfo user = NimUserInfoCache.getInstance().getUserInfo(account);
            if (user == null) {
                NimUserInfoCache.getInstance().getUserInfoFromRemote(account, null);
            }

            return user;
        }

        @Override
        public int getDefaultIconResId() {
            return R.mipmap.ic_default_avatar;
        }

        @Override
        public Bitmap getTeamIcon(String teamId) {
            /**
             * 注意：这里最好从缓存里拿，如果读取本地头像可能导致UI进程阻塞，导致通知栏提醒延时弹出。
             */
            // 从内存缓存中查找群头像
            Team team = TeamDataCache.getInstance().getTeamById(teamId);
            if (team != null) {
                Bitmap bm = ImageLoaderKit.getNotificationBitmapFromCache(team.getIcon());
                if (bm != null) {
                    return bm;
                }
            }

            // 默认图
            Drawable drawable = getResources().getDrawable(R.drawable.nim_avatar_group);
            if (drawable instanceof BitmapDrawable) {
                return ((BitmapDrawable) drawable).getBitmap();
            }

            return null;
        }

        @Override
        public Bitmap getAvatarForMessageNotifier(String account) {
            /**
             * 注意：这里最好从缓存里拿，如果读取本地头像可能导致UI进程阻塞，导致通知栏提醒延时弹出。
             */
            UserInfo user = getUserInfo(account);
            return (user != null) ? ImageLoaderKit.getNotificationBitmapFromCache(user.getAvatar()) : null;
        }

        @Override
        public String getDisplayNameForMessageNotifier(String account, String sessionId, SessionTypeEnum sessionType) {
            String nick = null;
            if (sessionType == SessionTypeEnum.P2P) {
                nick = NimUserInfoCache.getInstance().getAlias(account);
            } else if (sessionType == SessionTypeEnum.Team) {
                nick = TeamDataCache.getInstance().getTeamNick(sessionId, account);
                if (TextUtils.isEmpty(nick)) {
                    nick = NimUserInfoCache.getInstance().getAlias(account);
                }
            }
            // 返回null，交给sdk处理。如果对方有设置nick，sdk会显示nick
            if (TextUtils.isEmpty(nick)) {
                return null;
            }

            return nick;
        }
    };
    private ContactProvider contactProvider = new ContactProvider() {
        @Override
        public List<UserInfoProvider.UserInfo> getUserInfoOfMyFriends() {
            List<NimUserInfo> nimUsers = NimUserInfoCache.getInstance().getAllUsersOfMyFriend();
            List<UserInfoProvider.UserInfo> users = new ArrayList<>(nimUsers.size());
            if (!nimUsers.isEmpty()) {
                users.addAll(nimUsers);
            }

            return users;
        }

        @Override
        public int getMyFriendsCount() {
            return FriendDataCache.getInstance().getMyFriendCounts();
        }

        @Override
        public String getUserDisplayName(String account) {
            return NimUserInfoCache.getInstance().getUserDisplayName(account);
        }
    };

    private MessageNotifierCustomization messageNotifierCustomization = new MessageNotifierCustomization() {
        @Override
        public String makeNotifyContent(String nick, IMMessage message) {
            return null; // 采用SDK默认文案
        }

        @Override
        public String makeTicker(String nick, IMMessage message) {
            return null; // 采用SDK默认文案
        }
    };



    /**
     * 初始化
     */
    private void initUIKit() {
        // 初始化，需要传入用户信息提供者
        NimUIKit.init(this, infoProvider, contactProvider);

        // 设置地理位置提供者。如果需要发送地理位置消息，该参数必须提供。如果不需要，可以忽略。
        NimUIKit.setLocationProvider(new NimDemoLocationProvider());

        // 会话窗口的定制初始化。
//        SessionHelper.init();
//
//        // 通讯录列表定制初始化
//        ContactHelper.init();

    }

    public boolean inMainProcess() {
        String packageName = getPackageName();
        String processName = SystemUtil.getProcessName(this);
        return packageName.equals(processName);
    }

    // 如果已经存在用户登录信息，返回LoginInfo，否则返回null即可
    public LoginInfo loginInfo() {
        String accout = PreferenceUitl.getInstance(getApplicationContext()).getString(Constants.KEY_USER_ACCOUNT, null);
        String token = PreferenceUitl.getInstance(getApplicationContext()).getString(Constants.KEY_USER_TOKEN, null);
        if (!TextUtils.isEmpty(accout) && !TextUtils.isEmpty(token)) {
            DemoCache.setAccount(accout.toLowerCase());
            return new LoginInfo(accout, token);
        } else {
            return null;
        }
    }


    // 如果返回值为 null，则全部使用默认参数。
    private SDKOptions options() {
        SDKOptions options = new SDKOptions();

        // 如果将新消息通知提醒托管给 SDK 完成，需要添加以下配置。否则无需设置。
        StatusBarNotificationConfig config = new StatusBarNotificationConfig();
        config.notificationEntrance = LoginActivity.class; // 点击通知栏跳转到该Activity
        config.notificationSmallIconId = R.mipmap.ic_launcher;
        // 呼吸灯配置
        config.ledARGB = Color.GREEN;
        config.ledOnMs = 1000;
        config.ledOffMs = 1500;
        // 通知铃声的uri字符串
        config.notificationSound = "android.resource://com.netease.nim.demo/raw/msg";
        options.statusBarNotificationConfig = config;

        // 配置保存图片，文件，log 等数据的目录
        // 如果 options 中没有设置这个值，SDK 会使用下面代码示例中的位置作为 SDK 的数据目录。
        // 该目录目前包含 log, file, image, audio, video, thumb 这6个目录。
        // 如果第三方 APP 需要缓存清理功能， 清理这个目录下面个子目录的内容即可。
        String sdkPath = Environment.getExternalStorageDirectory() + "/" + getPackageName() + "/cache";
        options.sdkStorageRootPath = sdkPath;

        // 配置是否需要预下载附件缩略图，默认为 true
        options.preloadAttach = true;

        // 配置附件缩略图的尺寸大小。表示向服务器请求缩略图文件的大小
        // 该值一般应根据屏幕尺寸来确定， 默认值为 Screen.width / 2
        options.thumbnailSize = SCREEN_WIDTH/ 2;

        // 用户资料提供者, 目前主要用于提供用户资料，用于新消息通知栏中显示消息来源的头像和昵称
        options.userInfoProvider = new UserInfoProvider() {
            @Override
            public UserInfo getUserInfo(String account) {
                return null;
            }

            @Override
            public int getDefaultIconResId() {

                return R.mipmap.ic_default_avatar;
            }

            @Override
            public Bitmap getTeamIcon(String tid) {
                return null;
            }

            @Override
            public Bitmap getAvatarForMessageNotifier(String account) {
                return null;
            }

            @Override
            public String getDisplayNameForMessageNotifier(String account, String sessionId,
                                                           SessionTypeEnum sessionType) {
                return null;
            }
        };
        return options;
    }

}
