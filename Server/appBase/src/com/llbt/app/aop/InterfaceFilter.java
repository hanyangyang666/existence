package com.llbt.app.aop;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.llbt.app.common.ChannelResponse;
import com.llbt.app.common.UrlChannel;
import com.llbt.app.util.CommonUtil;
import com.llbt.app.util.HttpUtil;
import com.llbt.app.util.MD5Util;
import com.llbt.app.util.RSAEncrypt;

/**
 * 方法请求拦截器
 * @author xdy
 *
 */
public class InterfaceFilter {
	
	
	private Logger logger = LoggerFactory.getLogger(InterfaceFilter.class); 
	/** 
     * 校验入参 
     * @param point 
     * @throws Throwable  
     */  
    @SuppressWarnings("unchecked")
	public Object validate(ProceedingJoinPoint point) throws Throwable{  
		logger.info("签名过滤拦截!");
		Object[] objs = point.getArgs();
		ChannelResponse res = null;
		HttpServletRequest request = HttpUtil.getRequest();
		String sign = request.getHeader("sign");
		if(null == sign){
			return CommonUtil.result(res, "-10000", "签名为空！");
		}
		Map parms = new HashMap();
		parms.putAll(getParameter(request));
		//从参数中移除签名信息，使拼接的签名串和前端一致
		String key = request.getHeader("key");
		//解密key值
//		 String va = new String(Base64.decodeBase64(key));
		parms.put("key", RSAEncrypt.decrypt( key, "utf-8", RSAEncrypt.priKey));
		parms.put("timeStamp", request.getHeader("timeStamp"));
		parms.put("deviceModel", request.getHeader("deviceModel"));
		parms.put("deviceVersion", request.getHeader("deviceVersion"));
		parms.put("appVersion", request.getHeader("appVersion"));
		parms.put("imei", request.getHeader("imei"));
		parms.put("deviceOS", request.getHeader("deviceOS"));
		String token = request.getHeader("token");
		if(null != token){
			parms.put("token", token);
		}
		String signContent = CommonUtil.createLinkString(parms);
		String ss = MD5Util.MD5(signContent);
		// 验证签名是否正确
		if (!MD5Util.MD5(signContent).equals(sign)) {
			return CommonUtil.result(res, "-10000", "签名失败");
		}
		// 没有错误就继续前进!
		return point.proceed(objs); 
    }

    public Map getParameter(HttpServletRequest request){
    	Map map = new HashMap();  
        Enumeration paramNames = request.getParameterNames();  
        while (paramNames.hasMoreElements()) {  
            String paramName = (String) paramNames.nextElement();  
            String[] paramValues = request.getParameterValues(paramName);  
            if (paramValues.length == 1) {  
                String paramValue = paramValues[0];  
                if (paramValue.length() != 0) {  
                    map.put(paramName, paramValue);  
                }  
            }  
        }  
        return map;
    }
    

	/** 
     * 具体的校验逻辑,返回警告信息 
     * @param obj 
     * @return 
     * @throws IllegalAccessException  
     * @throws IllegalArgumentException  
     */  
    private String validateDetail(Param param) throws IllegalArgumentException, IllegalAccessException{  
        Validate val = (Validate)param.getAnno();  
        boolean isVali = val.isValidate();  
        StringBuilder sb = new StringBuilder();  
        if(isVali){  
            if(val.isForm() == true){  
                String res = validateForm(param);  
                append(sb,res);  
            }else{  
                String res = validateCommon(param);  
                append(sb,res);  
            }  
        }  
        return sb.toString();  
    }  
      
    private void append(StringBuilder sb,String res){  
        if(res!=null){  
            sb.append("_");  
            sb.append(res);  
        }  
    }  
      
    /** 
     * 验证是否有某个注解 
     * @param annos 
     * @param validate 
     * @return 
     */  
    private boolean validateParameterAnnotation(Annotation[][] annos){  
        boolean flag = false;  
        for(Annotation[] at : annos){  
            for(Annotation a : at){  
                if(a.annotationType() == Validate.class){  
                    flag = true;  
                }  
            }  
        }  
        return flag;  
    }  
      
    private String  validateCommon(Param param){  
        String res = null;  
        if(param.getValue()==null){  
            res = param.getName()+"的参数值为空!";  
        }  
        return res;  
    }  
      
    private String validateForm(Param param) throws IllegalArgumentException, IllegalAccessException{  
        Class<?> clazz = param.getValue().getClass();  
        Field[] fields = clazz.getDeclaredFields();  
        StringBuilder sb = new StringBuilder();  
        for(Field f : fields){  
            Annotation[] annos = f.getAnnotations();  
            if(annos!=null){  
                String paramName = param.getName()+"."+f.getName();  
                Must must = (Must)annos[0];  
                if(must.isMust()){  
                    f.setAccessible(true);  
                    Object obj = f.get(param.getValue());  
                    Class<?> type = f.getType();  
                    if(type.isArray()){  
                        Object[] arr = (Object[])obj;  
                        if(arr==null){  
                            append(sb, paramName+"不能为空!");  
                        }  
                    }else if(type.isPrimitive()){  
                        if(type == int.class){  
                            int intObj = (int)obj;  
                            if(intObj <= 0){  
                                append(sb, paramName+"不能小于等于0!");  
                            }  
                        }else if(type == long.class){  
                            long longObj = (long)obj;  
                            if(longObj <= 0){  
                                append(sb, paramName+"不能小于等于0!");  
                            }  
                        }  
                    }else if(type == String.class){  
                        if(obj==null){  
                            append(sb, paramName+"不能为空!");  
                        }  
                    }  
                }  
            }  
        }  
        return sb.toString();  
    }  
}
