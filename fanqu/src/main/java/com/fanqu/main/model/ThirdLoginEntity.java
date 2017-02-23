package com.fanqu.main.model;

import com.fanqu.framework.model.BaseData;

/**
 * Created by Administrator on 2017/2/22.
 */

public class ThirdLoginEntity extends BaseData {

    private DataBean Data;

    public void setData(DataBean Data) {
        this.Data = Data;
    }

    public DataBean getData() {
        return Data;
   }

    @Override
    public String toString() {
        return "ThirdLoginEntity{" +
                "Data=" + Data +
                '}';
    }

    public  class DataBean {
        /**
         * token : f7a497a68523a43a9c6abc5203f75233
         * uid : 34
         * the3rd_uid : 6118
         */

        private String token;
        private String uid;
        private String the3rd_uid;

        public void setToken(String token) {
            this.token = token;
        }

        public void setUid(String uid) {
            this.uid = uid;
        }

        public void setThe3rd_uid(String the3rd_uid) {
            this.the3rd_uid = the3rd_uid;
        }

        public String getToken() {
            return token;
        }

        public String getUid() {
            return uid;
        }

        public String getThe3rd_uid() {
            return the3rd_uid;
        }

        @Override
        public String toString() {
            return "DataBean{" +
                    "token='" + token + '\'' +
                    ", uid='" + uid + '\'' +
                    ", the3rd_uid='" + the3rd_uid + '\'' +
                    '}';
        }
    }

}
