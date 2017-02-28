package com.fanqu.main.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 订单状态实体类
 */

public class OrderStatusCountBean implements Parcelable {
    /**
     * wait_pay : 0
     * wait_eat : 7
     * wait_comment : 72
     * refund_count : 0
     */

    private String wait_pay;
    private String wait_eat;
    private String wait_comment;
    private String refund_count;

    public OrderStatusCountBean() {
    }

    public void setWait_pay(String wait_pay) {
        this.wait_pay = wait_pay;
    }

    public void setWait_eat(String wait_eat) {
        this.wait_eat = wait_eat;
    }

    public void setWait_comment(String wait_comment) {
        this.wait_comment = wait_comment;
    }

    public void setRefund_count(String refund_count) {
        this.refund_count = refund_count;
    }

    public String getWait_pay() {
        return wait_pay;
    }

    public String getWait_eat() {
        return wait_eat;
    }

    public String getWait_comment() {
        return wait_comment;
    }

    public String getRefund_count() {
        return refund_count;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.wait_pay);
        dest.writeString(this.wait_eat);
        dest.writeString(this.wait_comment);
        dest.writeString(this.refund_count);
    }

    protected OrderStatusCountBean(Parcel in) {
        this.wait_pay = in.readString();
        this.wait_eat = in.readString();
        this.wait_comment = in.readString();
        this.refund_count = in.readString();
    }

    public static final Parcelable.Creator<OrderStatusCountBean> CREATOR = new Parcelable.Creator<OrderStatusCountBean>() {
        public OrderStatusCountBean createFromParcel(Parcel source) {
            return new OrderStatusCountBean(source);
        }

        public OrderStatusCountBean[] newArray(int size) {
            return new OrderStatusCountBean[size];
        }
    };
}
