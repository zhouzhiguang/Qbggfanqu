package com.fanqu.framework.data;

/**
 * Created by Administrator on 2017/1/4.
 */

import android.content.Context;
import android.text.TextUtils;

import com.fanqu.framework.main.util.PreferenceUitl;
import com.fanqu.framework.model.User;

/**
 * 管理用户的登录信息和个人信息
 */
public class UserManager {

    private static final String USER_ID = "user_id";
    private static final String USER_TOKEN = "user_token";
    private static final String PROFILE = "user_profile";
    private Context context;
    private User user;
    private static UserManager sInstance;

    public UserManager(Context context) {
        this.context = context;
        int userId = PreferenceUitl.getInstance(context).getInt(USER_ID, -1);
        String userToken = PreferenceUitl.getInstance(context).getString(USER_TOKEN, "");
        if (userId > 0 && !TextUtils.isEmpty(userToken)) {
            user = new User();
            user.setUser_id(String.valueOf(userId));
            user.setUser_token(userToken);
        }

    }


    /**
     * 判断用户是否已经登录
     */
    public boolean isLogined() {
        return user != null;
    }

    public static UserManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (UserManager.class) {
                sInstance = new UserManager(context);
            }
        }
        return sInstance;
    }
}