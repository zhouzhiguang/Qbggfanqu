package com.fanqu.main.model;

import com.fanqu.framework.model.BaseData;

/**
 * Created by Administrator on 2017/2/22.
 */

public class BindphoneEntity extends BaseData{

    /**
     * ErrorId : 0
     * ErrorDes : null
     * Data : {"token":"83adb400eb7e427cfe3eed94d1b864ef","uid":"34"}
     */
    private DataBean Data;

    public void setErrorId(int ErrorId) {
        this.ErrorId = ErrorId;
    }



    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getErrorId() {
        return ErrorId;
    }



    public DataBean getData() {
        return Data;
    }

    public static class DataBean {
        /**
         * token : 83adb400eb7e427cfe3eed94d1b864ef
         * uid : 34
         */

        private String token;
        private String uid;

        public void setToken(String token) {
            this.token = token;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public String getToken() {
            return token;
        }

        public String getUid() {
            return uid;
        }
    }
}
