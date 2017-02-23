package com.fanqu.framework.data;

/**
 * Created by Administrator on 2017/1/4.
 */

import android.content.Context;
import android.text.TextUtils;

import com.fanqu.framework.Constants;
import com.fanqu.framework.main.util.PreferenceUitl;
import com.fanqu.framework.model.User;

/**
 * 管理用户的登录信息和个人信息
 */
public class UserManager {


    private Context context;
    private User user;
    private static UserManager sInstance;

    public UserManager(Context context) {
        this.context = context;
        String userId = PreferenceUitl.getInstance(context).getString(Constants.USER_ID, null);
        String userToken = PreferenceUitl.getInstance(context).getString(Constants.USER_TOKEN, null);
        if (!TextUtils.isEmpty(userId) && !TextUtils.isEmpty(userToken)) {
            user = new User();
            user.setUser_id(userId);
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

    public User getUser() {
        if (isLogined()) {
            return user;
        } else {
            return null;
        }
    }
}