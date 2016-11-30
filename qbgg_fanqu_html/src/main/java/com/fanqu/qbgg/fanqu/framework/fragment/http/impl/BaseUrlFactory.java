package com.fanqu.qbgg.fanqu.framework.fragment.http.impl;


import com.fanqu.qbgg.fanqu.framework.Constants;

public abstract class BaseUrlFactory {
	public static final String SCHEME = "http";
	private static final String HOST = "58.67.201.21";// 服务器地址
	public static final String PORT = "8080";// 端口
	
	public static final String COMMON_PATH = "/apptest";

	public static String getInterfaceBaseUrl() {
		StringBuilder builder = new StringBuilder();

		builder.append(SCHEME + "://");
		builder.append(HOST);
		builder.append(":" + PORT);
		builder.append(COMMON_PATH);

		return builder.toString();
	}
	
	public static String getAbsolutePath(String relativePath){
		if(relativePath == null){
			return null;
		}
		
		if(relativePath.startsWith(SCHEME + ":")){
			return relativePath;
		}else{
			StringBuilder builder = new StringBuilder();

			builder.append(SCHEME + "://");
			builder.append(HOST);
			builder.append(":" + PORT);
			builder.append(COMMON_PATH);
			
			String subDirectory = relativePath.startsWith("/files") ? "" : "/files";
			builder.append(subDirectory + relativePath);
			
			return builder.toString();
		}
	}
	
	public static String getAbsoluteHeadPath(String relativePath){
		StringBuilder builder = new StringBuilder();

		builder.append(SCHEME + "://");
		builder.append(HOST);
		builder.append(":" + PORT);
		builder.append(COMMON_PATH);
	
		builder.append("/files/user/" + relativePath);

		return builder.toString();
	}

	public static String getgetPrepayIdUrl() {
	 
		return Constants.WXAPI_PAY_GET_PREPAY_ID;
	}
}
