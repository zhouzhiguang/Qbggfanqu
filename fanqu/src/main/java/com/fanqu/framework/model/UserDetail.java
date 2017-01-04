package com.fanqu.framework.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户详细信息实体类
 */

public class UserDetail extends User implements Parcelable {


    public String description;//个性签名
    public String email;//邮箱
    public String logoPath;
    public String name;
    public String nickName;
    public String phone;//绑定手机号码
    public String sex;// 性别 1：男 2：女
    public String birthday;
    public String lastLoginTime;// 最后登录时间
    public String qq;
    public String weixin;
    public String sinaWeibo;//用于第三方登录
    public List<TastePreference> TastePreferences;//口味偏好集合
    public String images;//头像地址
    public String neteaseId;// 云信账号
    public String neteaseStatus; // 环信注册状态码 1.成功 0.失败
    public String token;
    public String neteasePassword;// 环信密码
    public String requireVerifyCode; // 系统登录是否需要验证码 1：是 0：否
    public String remark;
    public String logoBack;// 背景图片

    public UserDetail() {

    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getLogoBack() {
        return logoBack;
    }

    public void setLogoBack(String logoBack) {
        this.logoBack = logoBack;
    }

    public String getRequireVerifyCode() {
        return requireVerifyCode;
    }

    public void setRequireVerifyCode(String requireVerifyCode) {
        this.requireVerifyCode = requireVerifyCode;
    }

    public String getNeteasePassword() {
        return neteasePassword;
    }

    public void setNeteasePassword(String neteasePassword) {
        this.neteasePassword = neteasePassword;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getNeteaseStatus() {
        return neteaseStatus;
    }

    public void setNeteaseStatus(String neteaseStatus) {
        this.neteaseStatus = neteaseStatus;
    }

    public String getNeteaseId() {
        return neteaseId;
    }

    public void setNeteaseId(String neteaseId) {
        this.neteaseId = neteaseId;
    }

    public String getImages() {
        return images;
    }

    public void setImages(String images) {
        this.images = images;
    }

    public List<TastePreference> getTastePreferences() {
        return TastePreferences;
    }

    public void setTastePreferences(List<TastePreference> tastePreferences) {
        TastePreferences = tastePreferences;
    }

    public String getSinaWeibo() {
        return sinaWeibo;
    }

    public void setSinaWeibo(String sinaWeibo) {
        this.sinaWeibo = sinaWeibo;
    }

    public String getWeixin() {
        return weixin;
    }

    public void setWeixin(String weixin) {
        this.weixin = weixin;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(String lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        this.birthday = birthday;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLogoPath() {
        return logoPath;
    }

    public void setLogoPath(String logoPath) {
        this.logoPath = logoPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.description);
        dest.writeString(this.email);
        dest.writeString(this.logoPath);
        dest.writeString(this.name);
        dest.writeString(this.nickName);
        dest.writeString(this.phone);
        dest.writeString(this.sex);
        dest.writeString(this.birthday);
        dest.writeString(this.lastLoginTime);
        dest.writeString(this.qq);
        dest.writeString(this.weixin);
        dest.writeString(this.sinaWeibo);
        dest.writeList(this.TastePreferences);
        dest.writeString(this.images);
        dest.writeString(this.neteaseId);
        dest.writeString(this.neteaseStatus);
        dest.writeString(this.token);
        dest.writeString(this.neteasePassword);
        dest.writeString(this.requireVerifyCode);
        dest.writeString(this.remark);
        dest.writeString(this.logoBack);
    }

    protected UserDetail(Parcel in) {
        this.description = in.readString();
        this.email = in.readString();
        this.logoPath = in.readString();
        this.name = in.readString();
        this.nickName = in.readString();
        this.phone = in.readString();
        this.sex = in.readString();
        this.birthday = in.readString();
        this.lastLoginTime = in.readString();
        this.qq = in.readString();
        this.weixin = in.readString();
        this.sinaWeibo = in.readString();
        this.TastePreferences = new ArrayList<TastePreference>();
        in.readList(this.TastePreferences, List.class.getClassLoader());
        this.images = in.readString();
        this.neteaseId = in.readString();
        this.neteaseStatus = in.readString();
        this.token = in.readString();
        this.neteasePassword = in.readString();
        this.requireVerifyCode = in.readString();
        this.remark = in.readString();
        this.logoBack = in.readString();
    }

    public static final Parcelable.Creator<UserDetail> CREATOR = new Parcelable.Creator<UserDetail>() {
        public UserDetail createFromParcel(Parcel source) {
            return new UserDetail(source);
        }

        public UserDetail[] newArray(int size) {
            return new UserDetail[size];
        }
    };
}
