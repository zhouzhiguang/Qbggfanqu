package com.qbgg.cenglaicengqu.homepage.fragment;

/**
 * Created by Administrator on 2016/11/12.
 */

public class TestBean {

    /**
     * method : GET
     * data : {"website":"www.yanzhenjie.com","blog":"blog.yanzhenjie.com"}
     * error : 0
     * url : http://api.nohttp.net/jsonObject
     */

    private String method;
    private DataBean data;
    private int error;
    private String url;

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getError() {
        return error;
    }

    public void setError(int error) {
        this.error = error;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class DataBean {
        /**
         * website : www.yanzhenjie.com
         * blog : blog.yanzhenjie.com
         */

        private String website;
        private String blog;

        public String getWebsite() {
            return website;
        }

        public void setWebsite(String website) {
            this.website = website;
        }

        public String getBlog() {
            return blog;
        }

        public void setBlog(String blog) {
            this.blog = blog;
        }
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "method='" + method + '\'' +
                ", data=" + data +
                ", error=" + error +
                ", url='" + url + '\'' +
                '}';
    }
}
