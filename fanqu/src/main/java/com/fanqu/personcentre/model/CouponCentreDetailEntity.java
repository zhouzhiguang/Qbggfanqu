package com.fanqu.personcentre.model;

import android.os.Parcel;

/**
 * 领券中心实体类
 */
public class CouponCentreDetailEntity extends CouponlDetailEntity {
    private int couponcentrestate;//三个状态 1 还未领取 0 已经领取 -1 标识 已经卖完了抢光了
    private String alreadygetcouponnumberpeople;//目前领取的状态

    public CouponCentreDetailEntity() {
    }


    public CouponCentreDetailEntity(int couponcentrestate, String alreadygetcouponnumberpeople) {
        this.couponcentrestate = couponcentrestate;
        this.alreadygetcouponnumberpeople = alreadygetcouponnumberpeople;
    }

    public CouponCentreDetailEntity(String couponids, String promotioncode, String quickcodeurl, String couponusablerange, int couponcondition, String couponmoney, String couponurl, String overduedate, String getcoupondate, String coupontitle, int couponstate, int couponcentrestate, String alreadygetcouponnumberpeople) {
        super(couponids, promotioncode, quickcodeurl, couponusablerange, couponcondition, couponmoney, couponurl, overduedate, getcoupondate, coupontitle, couponstate);
        this.couponcentrestate = couponcentrestate;
        this.alreadygetcouponnumberpeople = alreadygetcouponnumberpeople;
    }

    public CouponCentreDetailEntity(Parcel in, int couponcentrestate, String alreadygetcouponnumberpeople) {
        super(in);
        this.couponcentrestate = couponcentrestate;
        this.alreadygetcouponnumberpeople = alreadygetcouponnumberpeople;
    }

    public int getCouponcentrestate() {
        return couponcentrestate;
    }

    public void setCouponcentrestate(int couponcentrestate) {
        this.couponcentrestate = couponcentrestate;
    }

    public String getAlreadygetcouponnumberpeople() {
        return alreadygetcouponnumberpeople;
    }

    public void setAlreadygetcouponnumberpeople(String alreadygetcouponnumberpeople) {
        this.alreadygetcouponnumberpeople = alreadygetcouponnumberpeople;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        super.writeToParcel(dest, flags);
        dest.writeInt(this.couponcentrestate);
        dest.writeString(this.alreadygetcouponnumberpeople);
    }

    protected CouponCentreDetailEntity(Parcel in) {
        super(in);
        this.couponcentrestate = in.readInt();
        this.alreadygetcouponnumberpeople = in.readString();
    }

    public static final Creator<CouponCentreDetailEntity> CREATOR = new Creator<CouponCentreDetailEntity>() {
        public CouponCentreDetailEntity createFromParcel(Parcel source) {
            return new CouponCentreDetailEntity(source);
        }

        public CouponCentreDetailEntity[] newArray(int size) {
            return new CouponCentreDetailEntity[size];
        }
    };
}
