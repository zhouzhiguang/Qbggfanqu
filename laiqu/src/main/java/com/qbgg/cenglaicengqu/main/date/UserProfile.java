package com.qbgg.cenglaicengqu.main.date;

/**
 * Created by Administrator on 2016/11/14.
 */

public class UserProfile {

    public Info user;
    public boolean isFollowing; //[boolean|该用户是否已经被查看者关注]
    public static class Info {
        public int UserId; //[int|用户Id]

        public String NickName; //[string|用户昵称]

        public int AccountBalance; //[decimal|用户账户余额]

        public int Age; //[byte|用户年龄]

        public String Avatar; //[string|用户头像（七牛云存储的图片Key）]
        public boolean IsBindPhone;//是否已经绑定手机号码

    }
}
