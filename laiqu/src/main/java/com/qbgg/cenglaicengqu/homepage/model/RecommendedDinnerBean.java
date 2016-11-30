package com.qbgg.cenglaicengqu.homepage.model;

/**
 * 推荐 饭局实体类
 */

public class RecommendedDinnerBean {

    private   String recommendedinnerbeantitle ;//推荐饭局标题
    private  String  recommendedinnerbeancontent;//推荐饭局内容
    private  String  recommendedinnerbeanicon;//推荐饭局图片地址；



    public RecommendedDinnerBean() {
    }

    public String getRecommendedinnerbeanicon() {
        return recommendedinnerbeanicon;
    }

    public void setRecommendedinnerbeanicon(String recommendedinnerbeanicon) {
        this.recommendedinnerbeanicon = recommendedinnerbeanicon;
    }

    public String getRecommendedinnerbeancontent() {
        return recommendedinnerbeancontent;
    }

    public void setRecommendedinnerbeancontent(String recommendedinnerbeancontent) {
        this.recommendedinnerbeancontent = recommendedinnerbeancontent;
    }

    public String getRecommendedinnerbeantitle() {
        return recommendedinnerbeantitle;
    }

    public void setRecommendedinnerbeantitle(String recommendedinnerbeantitle) {
        this.recommendedinnerbeantitle = recommendedinnerbeantitle;
    }
}
