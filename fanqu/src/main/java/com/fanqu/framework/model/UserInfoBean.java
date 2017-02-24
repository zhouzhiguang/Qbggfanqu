package com.fanqu.framework.model;

import com.fanqu.main.model.AssetBean;
import com.fanqu.main.model.OrderStatusCountBean;

/**
 * Created by Administrator on 2017/2/24.
 */

public class UserInfoBean extends BaseData {

    /**
     * Data : {"username":"15976543860","phone":"15976543860","email":"","uid":"34","reg_time":"2016-04-14 14:13:10","u_type":"0","pid":"34","real_name_cert":"0","vip":"64","avatar":"http://o8uz2td92.bkt.clouddn.com/2017-02-14_58a28a338e32b.jpg","gender":"1","age":"27","signature":"","address":"北京市 东城区","marriage":"0","nickname":"34号测试的啦~~!","birthday":null,"bind_phone":"15976543860","is_bind":"true","u_type_str":"手机号用户:34号测试的啦~~!","membership":"2","asset":{"balance":"6788.850","buy_balance":"0.000","commission_balance":"43.000","total_score":"90.34","expend_srcoe":"0.00"},"order_status_count":{"wait_pay":"0","wait_eat":"7","wait_comment":"72","refund_count":"0"}}
     */

    private DataBean Data;

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public DataBean getData() {
        return Data;
    }

    public static class DataBean {
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
        private AssetBean asset;
        private OrderStatusCountBean order_status_count;

        public void setUsername(String username) {
            this.username = username;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setReg_time(String reg_time) {
            this.reg_time = reg_time;
        }

        public void setU_type(String u_type) {
            this.u_type = u_type;
        }

        public void setPid(String pid) {
            this.pid = pid;
        }

        public void setReal_name_cert(String real_name_cert) {
            this.real_name_cert = real_name_cert;
        }

        public void setVip(String vip) {
            this.vip = vip;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public void setGender(String gender) {
            this.gender = gender;
        }

        public void setAge(String age) {
            this.age = age;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public void setMarriage(String marriage) {
            this.marriage = marriage;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public void setBirthday(Object birthday) {
            this.birthday = birthday;
        }

        public void setBind_phone(String bind_phone) {
            this.bind_phone = bind_phone;
        }

        public void setIs_bind(String is_bind) {
            this.is_bind = is_bind;
        }

        public void setU_type_str(String u_type_str) {
            this.u_type_str = u_type_str;
        }

        public void setMembership(String membership) {
            this.membership = membership;
        }

        public void setAsset(AssetBean asset) {
            this.asset = asset;
        }

        public void setOrder_status_count(OrderStatusCountBean order_status_count) {
            this.order_status_count = order_status_count;
        }

        public String getUsername() {
            return username;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getUid() {
            return uid;
        }

        public String getReg_time() {
            return reg_time;
        }

        public String getU_type() {
            return u_type;
        }

        public String getPid() {
            return pid;
        }

        public String getReal_name_cert() {
            return real_name_cert;
        }

        public String getVip() {
            return vip;
        }

        public String getAvatar() {
            return avatar;
        }

        public String getGender() {
            return gender;
        }

        public String getAge() {
            return age;
        }

        public String getSignature() {
            return signature;
        }

        public String getAddress() {
            return address;
        }

        public String getMarriage() {
            return marriage;
        }

        public String getNickname() {
            return nickname;
        }

        public Object getBirthday() {
            return birthday;
        }

        public String getBind_phone() {
            return bind_phone;
        }

        public String getIs_bind() {
            return is_bind;
        }

        public String getU_type_str() {
            return u_type_str;
        }

        public String getMembership() {
            return membership;
        }

        public AssetBean getAsset() {
            return asset;
        }

        public OrderStatusCountBean getOrder_status_count() {
            return order_status_count;
        }


    }

}