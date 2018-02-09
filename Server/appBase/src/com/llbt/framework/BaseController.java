package com.llbt.framework;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

public class BaseController {

	 /**
    * 日志对象
    */
   protected Logger logger = LoggerFactory.getLogger(getClass());

   /**
    * 页面地址前缀
    */
   protected String pathPrefix = "";

   /**
    * 视图名称MAP
    */
   protected Map<String, String> viewNameMap = null;

   public BaseController() {
       viewNameMap = new HashMap<String, String>();
   }
   /**
	 * 取得request对象
	 * 
	 * @return
	 */
	protected HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}

	/**
	 * 取得session对象
	 * 
	 * @return
	 */
	protected HttpSession getSession() {
		return getRequest().getSession();
	}

}
