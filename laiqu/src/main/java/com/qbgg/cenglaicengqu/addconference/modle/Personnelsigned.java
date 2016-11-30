package com.qbgg.cenglaicengqu.addconference.modle;

/**
 * 已经报名的实体类
 */

public class Personnelsigned {
    private  String accounts;//帐号
    private  String nickname;//昵称
    private  String headimageurl;//头像
    private  String singnedtime;//报名时间

    public  Personnelsigned(){}



    public String getAccounts() {
        return accounts;
    }

    public void setAccounts(String accounts) {
        this.accounts = accounts;
    }

    public String getSingnedtime() {
        return singnedtime;
    }

    public void setSingnedtime(String singnedtime) {
        this.singnedtime = singnedtime;
    }

    public String getHeadimageurl() {
        return headimageurl;
    }

    public void setHeadimageurl(String headimageurl) {
        this.headimageurl = headimageurl;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
