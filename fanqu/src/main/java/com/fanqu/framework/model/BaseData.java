package com.fanqu.framework.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * 最基础的请求java实体类
 */

public class BaseData implements Parcelable {
    public int ErrorId;
    public String ErrorDes;

    public BaseData() {
    }

    public int getErrorId() {
        return ErrorId;
    }

    public void setErrorId(int errorId) {
        ErrorId = errorId;
    }

    public String getErrorDes() {
        return ErrorDes;
    }

    public void setErrorDes(String errorDes) {
        ErrorDes = errorDes;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(this.ErrorId);
        dest.writeString(this.ErrorDes);
    }

    protected BaseData(Parcel in) {
        this.ErrorId = in.readInt();
        this.ErrorDes = in.readString();
    }

    public static final Parcelable.Creator<BaseData> CREATOR = new Parcelable.Creator<BaseData>() {
        public BaseData createFromParcel(Parcel source) {
            return new BaseData(source);
        }

        public BaseData[] newArray(int size) {
            return new BaseData[size];
        }
    };
}
