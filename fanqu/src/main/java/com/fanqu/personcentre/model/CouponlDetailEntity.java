package com.fanqu.personcentre.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 我的优惠实体类
 */

public class CouponlDetailEntity implements Parcelable {
    private String couponids;//优惠券id
    private int couponstate;//优惠券状态  1未使用0已经使用-1已经过期
    private String coupontitle;//优惠卷使用说明
    private String getcoupondate;//领券日期；
    private String overduedate;//过期日期
    private String couponurl;//券地址
    private String couponmoney;//优惠卷金额也是抵扣金额
    private int couponcondition;//优惠券抵扣条件 0代表无门槛消费 数字代表满多少能使用
    private String couponusablerange;//预留一个优惠券使用范围


    public CouponlDetailEntity() {
    }

    public CouponlDetailEntity(String couponids, String couponusablerange, int couponcondition, String couponmoney, String couponurl, String overduedate, String getcoupondate, String coupontitle, int couponstate) {
        this.couponids = couponids;
        this.couponusablerange = couponusablerange;
        this.couponcondition = couponcondition;
        this.couponmoney = couponmoney;
        this.couponurl = couponurl;
        this.overduedate = overduedate;
        this.getcoupondate = getcoupondate;
        this.coupontitle = coupontitle;
        this.couponstate = couponstate;
    }

    public String getCouponusablerange() {
        return couponusablerange;
    }

    public void setCouponusablerange(String couponusablerange) {
        this.couponusablerange = couponusablerange;
    }

    public int getCouponcondition() {
        return couponcondition;
    }

    public void setCouponcondition(int couponcondition) {
        this.couponcondition = couponcondition;
    }

    public String getCouponmoney() {
        return couponmoney;
    }

    public void setCouponmoney(String couponmoney) {
        this.couponmoney = couponmoney;
    }

    public String getCouponids() {
        return couponids;
    }

    public void setCouponids(String couponids) {
        this.couponids = couponids;
    }

    public String getOverduedate() {
        return overduedate;
    }

    public void setOverduedate(String overduedate) {
        this.overduedate = overduedate;
    }

    public String getCouponurl() {
        return couponurl;
    }

    public void setCouponurl(String couponurl) {
        this.couponurl = couponurl;
    }

    public String getGetcoupondate() {
        return getcoupondate;
    }

    public void setGetcoupondate(String getcoupondate) {
        this.getcoupondate = getcoupondate;
    }

    public String getCoupontitle() {
        return coupontitle;
    }

    public void setCoupontitle(String coupontitle) {
        this.coupontitle = coupontitle;
    }

    public int getCouponstate() {
        return couponstate;
    }

    public void setCouponstate(int couponstate) {
        this.couponstate = couponstate;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.couponids);
        dest.writeInt(this.couponstate);
        dest.writeString(this.coupontitle);
        dest.writeString(this.getcoupondate);
        dest.writeString(this.overduedate);
        dest.writeString(this.couponurl);
        dest.writeString(this.couponmoney);
        dest.writeInt(this.couponcondition);
        dest.writeString(this.couponusablerange);
    }

    protected CouponlDetailEntity(Parcel in) {
        this.couponids = in.readString();
        this.couponstate = in.readInt();
        this.coupontitle = in.readString();
        this.getcoupondate = in.readString();
        this.overduedate = in.readString();
        this.couponurl = in.readString();
        this.couponmoney = in.readString();
        this.couponcondition = in.readInt();
        this.couponusablerange = in.readString();
    }

    public static final Creator<CouponlDetailEntity> CREATOR = new Creator<CouponlDetailEntity>() {
        public CouponlDetailEntity createFromParcel(Parcel source) {
            return new CouponlDetailEntity(source);
        }

        public CouponlDetailEntity[] newArray(int size) {
            return new CouponlDetailEntity[size];
        }
    };
}
