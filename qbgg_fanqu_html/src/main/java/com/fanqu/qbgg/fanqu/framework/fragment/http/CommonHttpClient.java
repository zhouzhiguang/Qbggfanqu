package com.fanqu.qbgg.fanqu.framework.fragment.http;

public interface CommonHttpClient {
//	/**
//	 * 登录
//	 * @param params
//	 * @return
//	 */
//	public LoginUserData login(LoginingRegistrationParams params);
//	
//	/**
//	 * 忘记密码（重置密码）
//	 * @param params
//	 * @return
//	 */
//	public RegistrationUserData register(LoginingRegistrationParams params);
//	
//	/**
//	 * 重置密码
//	 * @param params
//	 * @return
//	 */
//	public BaseData resetPassword(ResettingPasswordParams params);
//	
//	/**
//	 * 获取手机验证码
//	 * @param params
//	 * @return
//	 */
//	public VerificationCodeData getVerificationCode(VerificationCodeParams params);
	// 微信支付请求 获取生成预付单
	public String getPrepayId(String params);
}
