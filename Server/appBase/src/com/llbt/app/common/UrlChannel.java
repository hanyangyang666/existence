package com.llbt.app.common;

/**
 * 渠道访问路径地址
 * @author hc
 *
 */
public final class UrlChannel {

	/**
	 * 渠道服务器地址
	 */
	//public static final String HOST = "https://127.0.0.1:8443/channel/";
	
	public static final String HOST =AppConfig.getValue("host.url");


	/**
	 * 从页面获取transportkey，新增到数据库中
	 */
	public static final String RequestInsertTransPortKey = HOST+"common/insertTransPortKey.json";
	
	/**
	 * 根据imei获取transportkey
	 */
	public static final String RequestGetTransPortKey = HOST+"common/queryTransPortKey.json";

	/**
	 * 注册
	 */
	public static final String userRegister = HOST+"user/register.json";
	
	/**
	 * 登录
	 */
	public static final String userLogin = HOST+"user/login.json";
	
	/**
	 * 校验手机号是否可注册
	 */
	public static final String checkPhone = HOST+"common/checkPhone.json";
	
	/**
	 * 获取短信验证码
	 */
	public static final String getSmsCode = HOST+"common/getSmsCode.json";
	
	/**
	 * 修改密码
	 */
	public static final String changePassword = HOST+"user/changePassword.json";
	
	/**
	 * 获取支持的证件类型
	 */
	public static final String getDataDict = HOST+"common/getDataDict.json";
	
	/**
	 * 验证短信验证码
	 */
	public static final String verifySmsCode = HOST+"common/verifySmsCode.json";
	
	/**
	 * 获取用户信息
	 */
	public static final String getUserInfo = HOST+"user/getUserInfo.json";
	
	
	/**
	 * 检查版本更新
	 */
	public static final String checkVersion = HOST+"common/checkVersion.json";

	/**
	 * 忘记密码
	 */
	public static final String forgetPassword = HOST+"user/forgetPassword.json";
	
	/**
	 * 退出登录
	 */
	public static final String logout = HOST+"user/logout.json";
	
	/**
	 * 获取房源分页信息
	 */
	public static final String houseInfoPage = HOST+"houseInfo/houseInfoPage.json";
	
	/**
	 * 根据id获取房源信息详情
	 */
	public static final String houseInfoDetail = HOST+"houseInfo/houseInfoDetail.json";
	
	/**
	 * 通过openid获取用户信息
	 */
	public static final String getUserInfoByOpenid= HOST+"common/getUserInfoByOpenid.json";
}
