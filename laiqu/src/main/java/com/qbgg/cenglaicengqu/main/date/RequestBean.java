package com.qbgg.cenglaicengqu.main.date;

import android.os.Parcel;
import android.os.Parcelable;

import com.yolanda.nohttp.RequestMethod;
import com.yolanda.nohttp.rest.CacheMode;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求参数类
 */

public class  RequestBean implements Parcelable {
    private  String url;
    private Map<String, String> params;
    private RequestMethod requestMethod;
    private CacheMode cacheMode;
    private Class clazz;

    public RequestMethod getRequestMethod() {
        return requestMethod;
    }

    public void setRequestMethod(RequestMethod requestMethod) {
        this.requestMethod = requestMethod;
    }

    public CacheMode getCacheMode() {
        return cacheMode;
    }

    public void setCacheMode(CacheMode cacheMode) {
        this.cacheMode = cacheMode;
    }

    public Class getClazz() {
        return clazz;
    }

    public void setClazz(Class clazz) {
        this.clazz = clazz;
    }

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public RequestBean() {
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.url);
        dest.writeInt(this.params.size());
        for (Map.Entry<String, String> entry : this.params.entrySet()) {
            dest.writeString(entry.getKey());
            dest.writeString(entry.getValue());
        }
        dest.writeInt(this.requestMethod == null ? -1 : this.requestMethod.ordinal());
        dest.writeInt(this.cacheMode == null ? -1 : this.cacheMode.ordinal());
        dest.writeSerializable(this.clazz);
    }

    protected RequestBean(Parcel in) {
        this.url = in.readString();
        int paramsSize = in.readInt();
        this.params = new HashMap<String, String>(paramsSize);
        for (int i = 0; i < paramsSize; i++) {
            String key = in.readString();
            String value = in.readString();
            this.params.put(key, value);
        }
        int tmpRequestMethod = in.readInt();
        this.requestMethod = tmpRequestMethod == -1 ? null : RequestMethod.values()[tmpRequestMethod];
        int tmpCacheMode = in.readInt();
        this.cacheMode = tmpCacheMode == -1 ? null : CacheMode.values()[tmpCacheMode];
        this.clazz = (Class) in.readSerializable();
    }

    public static final Parcelable.Creator<RequestBean> CREATOR = new Parcelable.Creator<RequestBean>() {
        @Override
        public RequestBean createFromParcel(Parcel source) {
            return new RequestBean(source);
        }

        @Override
        public RequestBean[] newArray(int size) {
            return new RequestBean[size];
        }
    };
}
