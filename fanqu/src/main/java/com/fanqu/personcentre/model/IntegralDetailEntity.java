package com.fanqu.personcentre.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 我的积分积分详情实体类
 */

public class IntegralDetailEntity implements Parcelable {
    private String integraldetailtitle;//积分产生的标题
    private String integraldate;//日期
    private String integralcount;//多少积分
    private int revenueexpenditure_mark;//收入支出标志 1标识收入 -1标志支出

    public IntegralDetailEntity() {
    }

    public int getRevenueexpenditure_mark() {
        return revenueexpenditure_mark;
    }

    public void setRevenueexpenditure_mark(int revenueexpenditure_mark) {
        this.revenueexpenditure_mark = revenueexpenditure_mark;
    }

    public IntegralDetailEntity(String integraldate, String integralcount, String integraldetailtitle) {
        this.integraldate = integraldate;
        this.integralcount = integralcount;
        this.integraldetailtitle = integraldetailtitle;
    }

    public String getIntegraldetailtitle() {
        return integraldetailtitle;
    }

    public void setIntegraldetailtitle(String integraldetailtitle) {
        this.integraldetailtitle = integraldetailtitle;
    }

    public String getIntegraldate() {
        return integraldate;
    }

    public void setIntegraldate(String integraldate) {
        this.integraldate = integraldate;
    }

    public String getIntegralcount() {
        return integralcount;
    }

    public void setIntegralcount(String integralcount) {
        this.integralcount = integralcount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.integraldetailtitle);
        dest.writeString(this.integraldate);
        dest.writeString(this.integralcount);
        dest.writeInt(this.revenueexpenditure_mark);
    }

    protected IntegralDetailEntity(Parcel in) {
        this.integraldetailtitle = in.readString();
        this.integraldate = in.readString();
        this.integralcount = in.readString();
        this.revenueexpenditure_mark = in.readInt();
    }

    public static final Creator<IntegralDetailEntity> CREATOR = new Creator<IntegralDetailEntity>() {
        public IntegralDetailEntity createFromParcel(Parcel source) {
            return new IntegralDetailEntity(source);
        }

        public IntegralDetailEntity[] newArray(int size) {
            return new IntegralDetailEntity[size];
        }
    };
}
