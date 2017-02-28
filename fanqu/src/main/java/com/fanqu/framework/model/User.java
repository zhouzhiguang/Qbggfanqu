package com.fanqu.framework.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 用户的实体类
 */

public class User implements Parcelable {
    private String uid;//用户id
    private String user_token;//回话
    private String avatar;

    public User() {
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.uid);
        dest.writeString(this.user_token);
        dest.writeString(this.avatar);
    }

    protected User(Parcel in) {
        this.uid = in.readString();
        this.user_token = in.readString();
        this.avatar = in.readString();
    }

}
