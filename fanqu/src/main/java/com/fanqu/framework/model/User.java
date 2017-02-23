package com.fanqu.framework.model;

/**
 * 用户的实体类
 */

public class User {
    private String uid;//用户id
    private String user_token;//回话
    private String avatar;

    public User() {
    }

    public String getUser_id() {
        return uid;
    }

    public void setUser_id(String uid) {
        this.uid = uid;
    }

    public String getUser_token() {
        return user_token;
    }

    public void setUser_token(String user_token) {
        this.user_token = user_token;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}
