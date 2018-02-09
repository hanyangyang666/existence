package com.llbt.app.config;


public class ConstantConfig {

	/**
	 * 公共校验参数
	 */
	public final static boolean Constant_True = true;
	public final static boolean Constant_False = false;
	public final static String Constant_Null = null;
	public final static String Constant_Empty = "";
	
	/**
	 * CostomerLogin/MerchantLogin
	 */
	public final static String Customer_AccountStatus0 = "0";	//正常
	public final static String Customer_AccountStatus1 = "1";	//销户
	public final static String Customer_AccountStatus2 = "2";	//冻结
	
	public final static String Customer_AuthorityStatus0 = "0";	//未认证
	public final static String Customer_AuthorityStatus1 = "1";	//已认证
	
	public final static String Customer_NotRegister = "0";		//未注册
	public final static String Customer_AlreadyRegister = "1";	//已注册
	
	public final static String Customer_ActivateStatus0 = "0";	//未激活
	public final static String Customer_ActivateStatus1 = "1";	//已激活
	
	/**
	 * MobileBind
	 */
	public final static String Mobile_Status1 = "1";	//当前绑定
	public final static String Mobile_Status2 = "2";	//上次绑定
	public final static String Mobile_Status3 = "3";	//已解绑
	
	public final static String Mgate_Customer = "1";	//客户
	public final static String Mgate_Merchant = "2";	//商户
	
	public final static String Mgate_Success = "000000";	//成功
	public final static String Mgate_Defeat = "-000000";	//失败
	public final static String Mgate_TimeOut = "000109";	//sessionToken过期
	public final static String Request_TimeOut = "000110";	//sessionToken过期
	
	//上传文件路径
	public final static String File_Customer = "customer";	//客户
	public final static String File_Merchant = "merchant";	//商户
	public final static String File_Operator = "operator";	//操作员
	
	public final static String newHeadPortaitType1 = "1";	//客户
	public final static String newHeadPortaitType2 = "2";	//商户
	public final static String newHeadPortaitType3 = "3";	//操作员
	
	public final static int count1 = 1;	//sql语句执行结果
	public final static int count0 = 0;	//sql语句执行结果
	
	public final static String isRead_0 = "0"; //未读
	public final static String isRead_1 = "1"; //已读
	
	public final static String transAction = "MGATE_TRANSACTION";//交易流水表分表类型
	public final static String MONTH = "month"; //月
	public final static String WEEK = "week";   //周
	public final static String DAY = "day";     //日
	
	public final static String noticeQueryTimeInterval = "NOTICE_MAX_FIND_INTERVAL";//公告查询默认周期
	public final static String transactonTimeInterval = "TRABSACTION_FIND_INTERVAL";//交易流水查询默认周期
	
	public final static int isDelete_0 = 0; //未删除
	public final static int isDelete_1 = 1;//已删除
	
	public final static String userAll = "0"; //ALL
	public final static String userBlock = "1";//黑名单
	public final static String userWhite = "2";//白名单
	
	
	public final static String Cache_gjh = "gjh";//缓存国际化
	public final static String Cache_param = "param";//缓存国际化
	public final static String Cache_dict = "dict";//缓存字典
	public final static String Cache_interface_switch = "switch";//缓存接口开关
	public final static String Cache_interface_roll = "roll";//黑白名单及接口开关
	public final static String Cache_commanId = "commanId";//缓存对照commanId
	public final static String Cache_errorCode = "errorCode";//缓存错误对照表
	public final static String Cache_transation_limit = "transationLimit";//缓存交易限额
	public final static String File_path = "e://Cache";//读取资源文件路径
	public final static String MESSAGES_PROPERTIES = "messages";//US错误资源文件名
	public final static String EXCEPTION_PROPERTIES = "exception";//Exception错误信息资源文件名
	
	public final static String Error_File = "messages_en_US.properties";//US错误资源文件名
	
	public final static String certificatePath = "D:/aps_test_development.p12";//ios推送p12文件
	public final static String certificatePassword = "";//ios推送p12密码
	
	public final static String Service_State1 = "1";//开
	public final static String Service_State2 = "0";//关
	
	/**
	 * 系统错误信息
	 */
	public final static String Error_Code6000 = "6000";//token失效
	public final static String Error_Code6001 = "6001";//请求异常
	public final static String Error_Code6002 = "6002";//参数异常
	public final static String Error_Code6003 = "6003";//参数缺失
	public final static String Error_Code6004 = "6004";//返回值为空
	public final static String Error_Code6005 = "6005";//文件上传失败
	public final static String Error_Code6006 = "6006";//渠道信息返回错误
	public final static String Error_Code6007 = "6007";//该功能暂不可用
	public final static String Error_Code6010 = "6010";//用户信息不存在
	public final static String Error_Code6011 = "6011";//密码错误
	public final static String Error_Code6012 = "6012";//账户已冻结
	public final static String Error_Code6013 = "6013";//当前设备号与上次登录设备号不一致，请重新绑定
	public final static String Error_Code6014 = "6014";//用户未认证
	public final static String Error_Code6015 = "6015";//用户未激活
	public final static String Error_Code6016 = "6016";//设备未绑定
	public final static String Error_Code6017 = "6017";//未查询到绑定记录
	public final static String Error_Code6018 = "6018";//用户已注册
	public final static String Error_Code6019 = "6019";//operator不存在，请联系owner注册
	public final static String Error_Code6020 = "6020";//黑名单用户，不能使用
	public final static String Error_Code6021 = "6021";//非白名单用户，不能使用
	
	
	public final static String Error_Code1 = "6201";//请求异常
	public final static String Error_Code2 = "6202";//请求参数缺失
	public final static String Error_Code3 = "6203";//原密码输入错误
	public final static String Error_Code4 = "6204";//新密码与原密码相同
	public final static String Error_Code5 = "6205";//参数缺失
	public final static String Error_Code6 = "6206";//sql语句执行异常
	public final static String Error_Code7 = "6207";//密保答案错误
	public final static String Error_Code8 = "6208";//sessionToken为空
	public final static String Error_Code9 = "6209";//执行mgate_admin表出错
	public final static String Error_Code10 = "6210";//执行mms_feedback_info表出错
	public final static String Error_Code11 = "6211";//执行mgate_security_record表出错
	public final static String Error_Code12 = "6212";//此功能已关闭
	public final static String Error_Code13 = "6213";//执行mms_app_version表出错
	public final static String Error_Code14 = "6214";//CPS系统返回异常
	public final static String Error_Code15 = "6215";//执行mgate_customer表出错
	public final static String Error_Code16 = "6216";//初始PIN输入错误
	public final static String Error_Code17 = "6217";//新PIN与初始PIN相同
	public final static String Error_Code18 = "6218";//执行mms_notice表出错
	
	//功能开关 商户版
	public final static String m1 = "m1001";//Card pay
	public final static String m2 = "m1002";//Withdraw
	public final static String m3 = "m1003";//Activation
	public final static String m4 = "m1004";//News
	public final static String m5 = "m1005";//Change PIN
	public final static String m6 = "m1006";//Registered
	public final static String m7 = "m1007";//My account
	public final static String m8 = "m1008";//Settings
	public final static String m9 = "m1009";//loginIn
	public final static String m10 = "m1010";//singIn
	public final static String m11 = "m1011";//forgetPassword
	public final static String m12 = "m1012";//resetSecurity
	public final static String m13 = "m1013";//resetLoginPassword
	public final static String m14 = "m1014";//resetPIN
	public final static String m15 = "m1015";//修改商户图标
	public final static String m16 = "m1016";//queryMarchantInfo
	public final static String m17 = "m1017";//feedback
	public final static String m18 = "m1018";//password
	public final static String m19 = "m1019";//修改操作员头像
	public final static String m20 = "m1020";//随机密保问题
	public final static String m21 = "m1021";//查询密保问题
	public final static String m22 = "m1022";//添加反馈意见
	public final static String m23 = "m1023";//查询密保信息
	public final static String m36 = "m1036";//设置反馈意见已读
	public final static String m37 = "m1037";//设置公告已读
	public final static String m38 = "m1038";//查询公告信息
	public final static String m60 = "m1060";//查询交易流水信息
	public final static String m61 = "m1061";//设置交易流水为已读
	
	//功能开关 客户版
	public final static String m24 = "m1024";//查询个人信息
	public final static String m25 = "m1025";//修改个人头像
	public final static String m26 = "m1026";//Password
	public final static String m27 = "m1027";//Settings
	public final static String m28 = "m1028";//添加反馈意见
	public final static String m29 = "m1029";//查询反馈意见
	public final static String m30 = "m1030";//Reset PIN Password
	public final static String m31 = "m1031";//Reset Login Password
	public final static String m32 = "m1032";//查询密保信息
	public final static String m33 = "m1033";//Reset Security
	public final static String m34 = "m1034";//My QR Code
	public final static String m35 = "m1035";//用户注册

	
	public final static String m39 = "m1039";//操作员注册
	public final static String m40 = "m1040";//Owner创建操作员
	public final static String m41 = "m1041";//操作员激活
	public final static String m42 = "m1042";//商户登录
	public final static String m43 = "m1043";//商户绑定设备变更（登录）
	public final static String m44 = "m1044";//客户激活
	public final static String m45 = "m1045";//客户登录
	public final static String m46 = "m1046";//客户绑定设备变更（登录）
	public final static String m47 = "m1047";//设备绑定操作
	public final static String m48 = "m1048";//社保卡激活
	public final static String m49 = "m1049";//社保卡支付
	public final static String m50 = "m1050";//With Draw
	

	public final static String ERROR = "-10000"; //抛异常时的code
	
}
