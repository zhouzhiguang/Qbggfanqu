package com.fanqu.framework.model;

/**
 * 分享实体类
 */

public class ShareBean {
    /**
     * title'=>'分享标题',
     * 'desc'=>'分享描述',
     * 'link'=>'分享被打开后的链接地址',
     * 'cover'=>'分享上的图标'
     * }
     */

    private String title;
    private String desc;
    private String link;
    private String cover;

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getTitle() {
        return title;
    }

    public String getDesc() {
        return desc;
    }

    public String getLink() {
        return link;
    }

    public String getCover() {
        return cover;
    }
}
