package com.llbt.app.controller;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.google.gson.Gson;
import com.llbt.app.aop.Validate;
import com.llbt.app.bean.UserInfoData;
import com.llbt.app.common.ChannelResponse;
import com.llbt.app.common.UrlChannel;
import com.llbt.app.config.ConstantConfig;
import com.llbt.app.util.CommonUtil;
import com.llbt.app.util.HttpUtil;

/**
 * 项目的公共请求方法
 */
@Controller
public class CommonController {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonController.class);
	
	
	/**
	 * 
	* @Description: TODO(透传接口) 
	* @author lzw
	* @create 2017年12月20日 下午2:13:25
	* @param  map
	* @return ChannelResponse    返回类型 
	* @throws
	 */
	//@Validate
	@ResponseBody
	@RequestMapping(value = "common/{method}", method = {RequestMethod.GET, RequestMethod.POST })
	public ChannelResponse transMethod(HttpServletRequest request,
		@PathVariable("method") String method ,@RequestParam Map<String,String> queryParas) {
		// 获取transportKey
		ChannelResponse res = null;
		String url = null;
		// 调用中间件获取数据
		if(null == method){
			return CommonUtil.result(res, ConstantConfig.Mgate_Defeat, "请求接口为空！");
		}
		try {
			Field field = UrlChannel.class.getDeclaredField(method);
			if(null != field){
				url = (String)field.get(UrlChannel.class); 
			}
		} catch (Exception e) {
			logger.error("反射获取接口失败！");
			e.printStackTrace();
		}
		if(null == url){
			logger.error("反射获取接口为空！");
			return CommonUtil.result(res, ConstantConfig.Mgate_Defeat, "请求接口获取失败");
		}
		try {
			Map<String, String> headers = new HashMap<String, String>();
			headers.put("token", request.getHeader("token"));
			if ("userLogin".equals(method)) {
				headers.put("timeStamp", request.getHeader("timeStamp"));
				headers.put("deviceModel", request.getHeader("deviceModel"));
				headers.put("deviceVersion", request.getHeader("deviceVersion"));
				headers.put("appVersion", request.getHeader("appVersion"));
				headers.put("imei", request.getHeader("imei"));
			}
			
			//处理第三方登录方式
        	//wx ---表示微信登录        接收参数中包含openid、accessToken、refreshToken
        	//qq ---表示QQ登录         接收参数中包含openid、accessToken、refreshToken
        	//为空 ---表示其它登录方式   接收参数中包含username、password
        	if("wx".equals(queryParas.get("loginType")) ||  "qq".equals(queryParas.get("loginType"))){
        		//首次登录使用openid、accessToken、refreshToken都不能为空
        		if(!StringUtils.isEmpty(queryParas.get("openid")) && !StringUtils.isEmpty(queryParas.get("accessToken")) && !StringUtils.isEmpty(queryParas.get("refreshToken"))){
        			//都不为空，说明是首次登录
        			//根据微信API或QQ API获取用户信息
        			String openid = queryParas.get("openid");
        			String accessToken = queryParas.get("accessToken");
        			boolean flag = weixinMethod(queryParas, openid, accessToken);
        			if(!flag){
        				return CommonUtil.result(res, ConstantConfig.Mgate_Defeat, "调用微信服务异常！");
        			}
        			
        		}else {
        			//免登录方式配置 1：用户名+密码登录     2：微信登录    3：qq登录
        			//判断openid不能为空
        			if(StringUtils.isEmpty(queryParas.get("openid"))){
        				return CommonUtil.result(res, ConstantConfig.Mgate_Defeat,"openid不能为空！");
        			}else{
        				//通过openid获取accessToken、refreshToken
            			res = HttpUtil.get(UrlChannel.getUserInfoByOpenid, queryParas, headers);
            			
            			if(res.OK()){
            				//通过openid获取信息成功
            				final Gson gson=new Gson();
            				UserInfoData userInfoData = gson.fromJson(gson.toJson(res.getDataEntity()), UserInfoData.class);
            				//验证accessToken是否有效
            				boolean flag = weixinMethod(queryParas, userInfoData.getOpenid(), userInfoData.getAccessToken());
            				
            				queryParas.put("refreshToken", userInfoData.getRefreshToken());
            				
            				if(!flag){
                				//调用出现错误，则accessToken已过期，需刷新AccessToken
            					String wxUrl = "https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+"wx327b09f41710b3c3"+"&grant_type="+"refresh_token"+"&refresh_token="+userInfoData.getRefreshToken();
            					//调用微信服务接口
            					String result = HttpUtil.sendGet(wxUrl);
            					if(!StringUtils.isEmpty(result)){
            						logger.info(result);
            						JSONObject object=JSONObject.parseObject(result);
            						//如果获取失败，则刷新accessToken失败
            						if(object.get("errcode") != null && !"".equals(object.get("errcode").toString())){
            							return CommonUtil.result(res, ConstantConfig.Mgate_Defeat, "调用微信服务异常！");
            						}
            						String accessToken = object.get("access_token").toString();
            						boolean flag2 = weixinMethod(queryParas, userInfoData.getOpenid(), accessToken);
            						if(!flag2){
            	        				return CommonUtil.result(res, ConstantConfig.Mgate_Defeat, "调用微信服务异常！");
            	        			}
            					}
                			}
            			}else{
            				return CommonUtil.result(res, res.getRetCode(), res.getRetMsg());
            			}
        			}
        		}
    			
        	}/*else{
        		
        		//免登录方式配置 1：用户名+密码登录     2：微信登录    3：qq登录
    			//判断用户名和密码不能为空
    			if(StringUtils.isEmpty(queryParas.get("userName")) || StringUtils.isEmpty(queryParas.get("userPsw"))){
    				return CommonUtil.result(res, ConstantConfig.Mgate_Defeat,"用户名和密码不能为空！");
    			}
        	}*/
			System.out.println(url);
			res = HttpUtil.get(url, queryParas, headers);
			if (res == null) {
				return CommonUtil.result(res, ConstantConfig.Request_TimeOut,"请求超时！");
			}
			if (res.OK()) {
				return CommonUtil.result(res, res.getRetCode(), res.getRetMsg());
			} else {
				return CommonUtil.result(res, res.getRetCode(), res.getRetMsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			return CommonUtil.result(res, ConstantConfig.Request_TimeOut,"请求超时！");
		}
					
	}
	
	
	@ResponseBody
	@RequestMapping(value = "/test", method = {RequestMethod.GET,RequestMethod.POST})
	public ChannelResponse test(HttpServletRequest request,Model model) {
		//获取transportKey
		ChannelResponse res = null;
		//调用中间件获取数据
		Map<String,String> queryParas = new HashMap<String, String>();
		queryParas.put("OrgId","11075511");
		queryParas.put("DrawReasonId","A18 ");
		queryParas.put("ReserveDate","2017-11-22");
		queryParas.put("ReserveTime","a");
		queryParas.put("AccNum", "20437222779");
		queryParas.put("AccName", "df");
		queryParas.put("CertiNum","421083262733336372");
		queryParas.put("CardNo", "345345345");
		queryParas.put("UnitAccNum", "3345435");
		queryParas.put("Phone", "");
		queryParas.put("ChannelMark", "0");
		
	//	res = HttpUtil.get(UrlChannel.RequestGetTransPortKey1, queryParas);
		
		if(res.OK())
		{
			
			return CommonUtil.result(res, ConstantConfig.Mgate_Success, "");
			
		}else{
			
			return CommonUtil.result(res, ConstantConfig.Mgate_Defeat, "请求失败");
			
		}
	}
	
	/**
	 * 获取transportkey
	 * @param request
	 * @param transportkey
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/getTransportkey", method = {RequestMethod.GET,RequestMethod.POST})
	public ChannelResponse requestGetKey(HttpServletRequest request,String transportkey,String imei,Model model) {
		logger.info("获取transportkey="+transportkey);
		//获取transportKey
		ChannelResponse res = null;
		//调用中间件获取数据
		Map<String,String> queryParas = new HashMap<String, String>();
		queryParas.put("transportkey", transportkey);
		queryParas.put("imei", imei);
		
//		res = HttpUtil.get(UrlChannel.RequestInsertTransPortKey, queryParas);
		
		if(res.OK())
		{
			
			return CommonUtil.result(res, ConstantConfig.Mgate_Success, "");
			
		}else{
			
			return CommonUtil.result(res, ConstantConfig.Mgate_Defeat, "请求失败");
			
		}
	}
	
	
	/**
	 * 获取短信验证码
	 * @param GateCustomerVerificationCodeSC
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/verificationCode", method = {RequestMethod.GET,RequestMethod.POST})
	public ChannelResponse verificationCode(HttpServletRequest request,Model model) {
		//logger.info("获取参数手机号="+verificationCodeSC.getPhone()+",获取参数验证码类型="+verificationCodeSC.getVerificationCodeType());
		//获取transportKey
		ChannelResponse res = null;
		//调用中间件获取数据
		Map<String,String> queryParas = new HashMap<String, String>();
		//queryParas.put("phone", verificationCodeSC.getPhone());
		//queryParas.put("verificationCodeType", verificationCodeSC.getVerificationCodeType());
		
	//	res = HttpUtil.get(UrlChannel.ObtainVerificationCode, queryParas);
		
		if(res.OK())
		{
			
			return CommonUtil.result(res, ConstantConfig.Mgate_Success, "");
			
		}else{
			
			return CommonUtil.result(res, ConstantConfig.Mgate_Defeat, "请求失败");
			
		}
	}
	
	
	/**
	 * 验证短信验证码
	 * @param GateCustomerVerificationCodeSC
	 * @param model
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value = "/checkVerificationCode", method = {RequestMethod.GET,RequestMethod.POST})
	public ChannelResponse checkVerificationCode(HttpServletRequest request,Model model) {
		//logger.info("获取参数手机号="+verificationCodeSC.getPhone()+",获取参数验证码类型="+verificationCodeSC.getVerificationCodeType()+",获取参数验证码="+verificationCodeSC.getVerificationCode());
		//获取transportKey
		ChannelResponse res = null;
		//调用中间件获取数据
		Map<String,String> queryParas = new HashMap<String, String>();
//		queryParas.put("phone", verificationCodeSC.getPhone());
//		queryParas.put("verificationCodeType", verificationCodeSC.getVerificationCodeType());
//		queryParas.put("verificationCode", verificationCodeSC.getVerificationCode());
		
	//	res = HttpUtil.get(UrlChannel.ConfirmVerificationCode, queryParas);
		
		if(res.OK())
		{
			
			return CommonUtil.result(res, ConstantConfig.Mgate_Success, "");
			
		}else{
			
			return CommonUtil.result(res, ConstantConfig.Mgate_Defeat, "请求失败");
			
		}
	}
	
	/**
 * 调用微信服务获取用户信息
 * @param queryParas
 * @param openid
 * @param accessToken
 * @return
 */
private boolean weixinMethod(Map<String, String> queryParas, String openid,
		String accessToken) {
	boolean flag = false;
	String wxUrl = "https://api.weixin.qq.com/sns/userinfo?access_token="+accessToken+"&openid="+openid;
	//调用微信服务接口
	String result = HttpUtil.sendGet(wxUrl);
	if(!StringUtils.isEmpty(result)){
		logger.info(result);
		JSONObject object=JSONObject.parseObject(result);
		
		if(object.get("errcode") != null && !"".equals(object.get("errcode").toString())){
			return flag;
		}
		//用户名
		String userName = object.get("nickname").toString();
		//性别 1:男  2：女  3：其他
		String sex = object.get("sex").toString();
		//语言
		String language = object.get("language").toString();
		//city城市
		String city = object.get("city").toString();
		//省份
		String province = object.get("province").toString();
		//country国家
		String country = object.get("country").toString();
		//用户图像
		String headimgurl = object.get("headimgurl").toString();
		
		//把获取到的用户信息存入数据库
		queryParas.put("userName", userName);
		queryParas.put("sex", sex);
		queryParas.put("headimgurl", headimgurl);
		queryParas.put("accessToken", accessToken);
		
		flag = true;
	}
	
	return flag;
}

	
}
