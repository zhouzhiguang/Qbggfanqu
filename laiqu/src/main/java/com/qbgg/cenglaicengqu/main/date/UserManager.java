package com.qbgg.cenglaicengqu.main.date;

import android.content.Context;
import android.text.TextUtils;

import com.alibaba.fastjson.JSON;
import com.qbgg.cenglaicengqu.LaiquApplication;
import com.qbgg.cenglaicengqu.main.model.UserToken;
import com.qbgg.cenglaicengqu.main.util.CommonUtil;
import com.qbgg.cenglaicengqu.main.util.PreferenceUitl;

import java.io.File;

/**
 * 管理用户的登录信息和个人信息

 */
public class UserManager {

    private static final String USER_ID = "user_id";
    private static final String USER_TOKEN = "user_token";
    private static final String PROFILE = "user_profile";

    private UserProfile mUserProfile;
    private UserToken mUserToken;
    private static UserManager sInstance;
    private static Context context;
    private PreferenceUitl preferenceUitl;

    private UserManager() {
        preferenceUitl = PreferenceUitl.getInstance(context);
        int userId = preferenceUitl.getInt(USER_ID, -1);
        String userToken = preferenceUitl.getString(USER_TOKEN, "");
        if (userId > 0 && !TextUtils.isEmpty(userToken)) {
            mUserToken = new UserToken(userId, userToken);
        }
        mUserProfile = readUserProfile();
    }

    public static UserManager getInstance(Context context) {
        if (sInstance == null) {
            synchronized (UserManager.class) {
                sInstance = new UserManager();
                UserManager.context = context;
            }
        }
        return sInstance;
    }
    /*
    * public static final Object parse(String text); // 把JSON文本parse为JSONObject或者JSONArray
public static final JSONObject parseObject(String text)； // 把JSON文本parse成JSONObject
public static final  T parseObject(String text, Class clazz); // 把JSON文本parse为JavaBean
public static final JSONArray parseArray(String text); // 把JSON文本parse成JSONArray
public static final  List parseArray(String text, Class clazz); //把JSON文本parse成JavaBean集合
public static final String toJSONString(Object object); // 将JavaBean序列化为JSON文本
public static final String toJSONString(Object object, boolean prettyFormat); // 将JavaBean序列化为带格式的JSON文本
public static final Object toJSON(Object javaObject); 将JavaBean转换为JSONObject或者JSONArray。
    * */

    private UserProfile readUserProfile() {
        File file = LaiquApplication.getInstance().getFileStreamPath(PROFILE);
        if (file.exists()) {
            String json = CommonUtil.readFileToString(file, "UTF-8");

            return JSON.parseObject(json, UserProfile.class);
        }
        return null;
    }

    /**
     * @param 把自己的实体类保存到文件
     */
    private void saveUserProfile(UserProfile profileInfo) {
        File file = LaiquApplication.getInstance().getFileStreamPath(PROFILE);
        CommonUtil.saveStringToFile(JSON.toJSONString(profileInfo, true), file, "UTF-8");
    }

    /**
     * 判断用户是否已经登录
     */
    public boolean isLogined() {
        return mUserToken != null;
    }

    /**
     * 保存用户登录信息
     */
    public void setUserToken(UserToken userToken) {
        mUserToken = userToken;
        preferenceUitl.saveInt(USER_ID, userToken.UserId);
        preferenceUitl.saveString(USER_TOKEN, userToken.Token);
    }

    /**
     * 保存用户信息
     */
    public void setUserProfile(UserProfile userProfile) {
        mUserProfile = userProfile;
        saveUserProfile(userProfile);
    }

    public UserToken getUserToken() {
        return mUserToken;
    }

    public UserProfile getUserProfile() {
        return mUserProfile;
    }

    public void removeAll() {
        mUserToken = null;
        preferenceUitl.remove(USER_ID);
        preferenceUitl.remove(USER_TOKEN);

        mUserProfile = null;
        File file = LaiquApplication.getInstance().getFileStreamPath(PROFILE);
        if (file.exists()) {
            file.delete();
        }
    }

}
