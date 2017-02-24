package com.fanqu.framework.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户详细信息实体类
 */

public class UserDetail extends User implements Parcelable {


    /**
     * username : 15976543860
     * phone : 15976543860
     * email :
     * uid : 34
     * reg_time : 2016-04-14 14:13:10
     * u_type : 0
     * pid : 34
     * real_name_cert : 0
     * vip : 64
     * avatar : http://o8uz2td92.bkt.clouddn.com/2017-02-14_58a28a338e32b.jpg
     * gender : 1
     * age : 27
     * signature :
     * address : 北京市 东城区
     * marriage : 0
     * nickname : 34号测试的啦~~!
     * birthday : null
     * bind_phone : 15976543860
     * is_bind : true
     * u_type_str : 手机号用户:34号测试的啦~~!
     * membership : 2
     * asset : {"balance":"6788.850","buy_balance":"0.000","commission_balance":"43.000","total_score":"90.34","expend_srcoe":"0.00"}
     * order_status_count : {"wait_pay":"0","wait_eat":"7","wait_comment":"72","refund_count":"0"}
     */

    private String username;
    private String phone;
    private String email;
    private String uid;
    private String reg_time;
    private String u_type;
    private String pid;
    private String real_name_cert;
    private String vip;
    private String avatar;
    private String gender;
    private String age;
    private String signature;
    private String address;
    private String marriage;
    private String nickname;
    private Object birthday;
    private String bind_phone;
    private String is_bind;
    private String u_type_str;
    private String membership;

    public UserDetail() {
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getReg_time() {
        return reg_time;
    }

    public void setReg_time(String reg_time) {
        this.reg_time = reg_time;
    }

    public String getU_type() {
        return u_type;
    }

    public void setU_type(String u_type) {
        this.u_type = u_type;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getReal_name_cert() {
        return real_name_cert;
    }

    public void setReal_name_cert(String real_name_cert) {
        this.real_name_cert = real_name_cert;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    @Override
    public String getAvatar() {
        return avatar;
    }

    @Override
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMarriage() {
        return marriage;
    }

    public void setMarriage(String marriage) {
        this.marriage = marriage;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Object getBirthday() {
        return birthday;
    }

    public void setBirthday(Object birthday) {
        this.birthday = birthday;
    }

    public String getBind_phone() {
        return bind_phone;
    }

    public void setBind_phone(String bind_phone) {
        this.bind_phone = bind_phone;
    }

    public String getIs_bind() {
        return is_bind;
    }

    public void setIs_bind(String is_bind) {
        this.is_bind = is_bind;
    }

    public String getU_type_str() {
        return u_type_str;
    }

    public void setU_type_str(String u_type_str) {
        this.u_type_str = u_type_str;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }
}
