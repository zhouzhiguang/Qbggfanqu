package com.fanqu.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 我的资产类
 */

public class AssetBean implements Parcelable {
    /**
     * balance : 6788.850
     * buy_balance : 0.000
     * commission_balance : 43.000
     * total_score : 90.34
     * expend_srcoe : 0.00
     */

    private String balance;
    private String buy_balance;
    private String commission_balance;
    private String total_score;
    private String expend_srcoe;

    public AssetBean() {
    }

    public String getBalance() {
        return balance;
    }

    public void setBalance(String balance) {
        this.balance = balance;
    }

    public String getBuy_balance() {
        return buy_balance;
    }

    public void setBuy_balance(String buy_balance) {
        this.buy_balance = buy_balance;
    }

    public String getTotal_score() {
        return total_score;
    }

    public void setTotal_score(String total_score) {
        this.total_score = total_score;
    }

    public String getExpend_srcoe() {
        return expend_srcoe;
    }

    public void setExpend_srcoe(String expend_srcoe) {
        this.expend_srcoe = expend_srcoe;
    }

    public String getCommission_balance() {
        return commission_balance;
    }

    public void setCommission_balance(String commission_balance) {
        this.commission_balance = commission_balance;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.balance);
        dest.writeString(this.buy_balance);
        dest.writeString(this.commission_balance);
        dest.writeString(this.total_score);
        dest.writeString(this.expend_srcoe);
    }

    protected AssetBean(Parcel in) {
        this.balance = in.readString();
        this.buy_balance = in.readString();
        this.commission_balance = in.readString();
        this.total_score = in.readString();
        this.expend_srcoe = in.readString();
    }

    public static final Parcelable.Creator<AssetBean> CREATOR = new Parcelable.Creator<AssetBean>() {
        public AssetBean createFromParcel(Parcel source) {
            return new AssetBean(source);
        }

        public AssetBean[] newArray(int size) {
            return new AssetBean[size];
        }
    };
}
