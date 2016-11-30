package com.fanqu.qbgg.fanqu.sharebean;

/**
 * 分享的bean
 */
public class ShareBean {
	public String img;// 分享图片
	public String title;// 分享标题
	public String url;// 分享网页
	public String desc;// 分享文字
	@Override
	public String toString() {
		return "ShareBean [img=" + img + ", title=" + title + ", url=" + url
				+ ", desc=" + desc + "]";
	}

}
