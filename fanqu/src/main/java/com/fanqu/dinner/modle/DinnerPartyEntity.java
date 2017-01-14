package com.fanqu.dinner.modle;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Arrays;

/**
 * 饭局实体类了
 */

public class DinnerPartyEntity implements Parcelable {
    private String dinnerpartyids;//饭局id
    private String dinnerpartykitchener;//饭局厨神的ids
    private boolean atrest;//是否是在休息
    private int dinnerparty;//0是一般饭局 1 是喜欢的饭局
    private String dinnerpartytheme;//饭局主题
    private String dinnerpartythemeimageurl;//饭局主题图片
    private String dinnerpartylocation;//饭局地址
    private String dinnerpartygalleryful;//饭局容纳人数
    private String dinnerpartydistance;//距离
    private String dinnerpartymoney;//价钱.
    private String[] dinnerpartyfeature;//特色 也是标签

    public DinnerPartyEntity(String dinnerpartyids, String dinnerpartykitchener, boolean atrest, int dinnerparty, String dinnerpartytheme, String dinnerpartythemeimageurl, String dinnerpartylocation, String dinnerpartygalleryful, String dinnerpartydistance, String dinnerpartymoney, String[] dinnerpartyfeature) {
        this.dinnerpartyids = dinnerpartyids;
        this.dinnerpartykitchener = dinnerpartykitchener;
        this.atrest = atrest;
        this.dinnerparty = dinnerparty;
        this.dinnerpartytheme = dinnerpartytheme;
        this.dinnerpartythemeimageurl = dinnerpartythemeimageurl;
        this.dinnerpartylocation = dinnerpartylocation;
        this.dinnerpartygalleryful = dinnerpartygalleryful;
        this.dinnerpartydistance = dinnerpartydistance;
        this.dinnerpartymoney = dinnerpartymoney;
        this.dinnerpartyfeature = dinnerpartyfeature;
    }


    @Override
    public String toString() {
        return "DinnerPartyEntity{" +
                "dinnerpartyids='" + dinnerpartyids + '\'' +
                ", dinnerpartykitchener='" + dinnerpartykitchener + '\'' +
                ", atrest=" + atrest +
                ", dinnerparty=" + dinnerparty +
                ", dinnerpartytheme='" + dinnerpartytheme + '\'' +
                ", dinnerpartythemeimageurl='" + dinnerpartythemeimageurl + '\'' +
                ", dinnerpartylocation='" + dinnerpartylocation + '\'' +
                ", dinnerpartygalleryful='" + dinnerpartygalleryful + '\'' +
                ", dinnerpartydistance='" + dinnerpartydistance + '\'' +
                ", dinnerpartymoney='" + dinnerpartymoney + '\'' +
                ", dinnerpartyfeature=" + Arrays.toString(dinnerpartyfeature) +
                '}';
    }

    public boolean isAtrest() {
        return atrest;
    }

    public void setAtrest(boolean atrest) {
        this.atrest = atrest;
    }

    public String[] getDinnerpartyfeature() {
        return dinnerpartyfeature;
    }

    public void setDinnerpartyfeature(String[] dinnerpartyfeature) {
        this.dinnerpartyfeature = dinnerpartyfeature;
    }


    public DinnerPartyEntity() {
    }


    public String getDinnerpartyids() {
        return dinnerpartyids;
    }

    public void setDinnerpartyids(String dinnerpartyids) {
        this.dinnerpartyids = dinnerpartyids;
    }

    public String getDinnerpartymoney() {
        return dinnerpartymoney;
    }

    public void setDinnerpartymoney(String dinnerpartymoney) {
        this.dinnerpartymoney = dinnerpartymoney;
    }

    public String getDinnerpartydistance() {
        return dinnerpartydistance;
    }

    public void setDinnerpartydistance(String dinnerpartydistance) {
        this.dinnerpartydistance = dinnerpartydistance;
    }

    public String getDinnerpartygalleryful() {
        return dinnerpartygalleryful;
    }

    public void setDinnerpartygalleryful(String dinnerpartygalleryful) {
        this.dinnerpartygalleryful = dinnerpartygalleryful;
    }

    public String getDinnerpartylocation() {
        return dinnerpartylocation;
    }

    public void setDinnerpartylocation(String dinnerpartylocation) {
        this.dinnerpartylocation = dinnerpartylocation;
    }

    public String getDinnerpartythemeimageurl() {
        return dinnerpartythemeimageurl;
    }

    public void setDinnerpartythemeimageurl(String dinnerpartythemeimageurl) {
        this.dinnerpartythemeimageurl = dinnerpartythemeimageurl;
    }

    public String getDinnerpartytheme() {
        return dinnerpartytheme;
    }

    public void setDinnerpartytheme(String dinnerpartytheme) {
        this.dinnerpartytheme = dinnerpartytheme;
    }

    public int getDinnerparty() {
        return dinnerparty;
    }

    public void setDinnerparty(int dinnerparty) {
        this.dinnerparty = dinnerparty;
    }

    public String getDinnerpartykitchener() {
        return dinnerpartykitchener;
    }

    public void setDinnerpartykitchener(String dinnerpartykitchener) {
        this.dinnerpartykitchener = dinnerpartykitchener;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.dinnerpartyids);
        dest.writeString(this.dinnerpartykitchener);
        dest.writeByte(atrest ? (byte) 1 : (byte) 0);
        dest.writeInt(this.dinnerparty);
        dest.writeString(this.dinnerpartytheme);
        dest.writeString(this.dinnerpartythemeimageurl);
        dest.writeString(this.dinnerpartylocation);
        dest.writeString(this.dinnerpartygalleryful);
        dest.writeString(this.dinnerpartydistance);
        dest.writeString(this.dinnerpartymoney);
        dest.writeStringArray(this.dinnerpartyfeature);
    }

    protected DinnerPartyEntity(Parcel in) {
        this.dinnerpartyids = in.readString();
        this.dinnerpartykitchener = in.readString();
        this.atrest = in.readByte() != 0;
        this.dinnerparty = in.readInt();
        this.dinnerpartytheme = in.readString();
        this.dinnerpartythemeimageurl = in.readString();
        this.dinnerpartylocation = in.readString();
        this.dinnerpartygalleryful = in.readString();
        this.dinnerpartydistance = in.readString();
        this.dinnerpartymoney = in.readString();
        this.dinnerpartyfeature = in.createStringArray();
    }

    public static final Creator<DinnerPartyEntity> CREATOR = new Creator<DinnerPartyEntity>() {
        public DinnerPartyEntity createFromParcel(Parcel source) {
            return new DinnerPartyEntity(source);
        }

        public DinnerPartyEntity[] newArray(int size) {
            return new DinnerPartyEntity[size];
        }
    };
}
