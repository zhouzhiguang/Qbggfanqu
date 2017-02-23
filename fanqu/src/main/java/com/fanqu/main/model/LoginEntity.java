package com.fanqu.main.model;

/**
 * 登录成功后返回解析出来的实体类
 */

public class LoginEntity {

    /**
     * ErrorId : 0
     * ErrorDes : null
     * Data : {"token":"83adb400eb7e427cfe3eed94d1b864ef","uid":"34"}
     */

    private int ErrorId;
    private String ErrorDes;
    private DataBean Data;

    public void setErrorId(int ErrorId) {
        this.ErrorId = ErrorId;
    }

    public void setErrorDes(String ErrorDes) {
        this.ErrorDes = ErrorDes;
    }

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public int getErrorId() {
        return ErrorId;
    }

    public String getErrorDes() {
        return ErrorDes;
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
