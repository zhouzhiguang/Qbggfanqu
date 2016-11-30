package com.qbgg.cenglaicengqu.main.model;

import android.text.TextUtils;

/**
 用户id token保存的实体类
 */
public class UserToken {
    public int UserId;
    public String Token;

    public UserToken(int userId, String token) {
        UserId = userId;
        Token = token;
    }

    public boolean isValid() {

        return UserId > 0 && !TextUtils.isEmpty(Token);
    }
}
