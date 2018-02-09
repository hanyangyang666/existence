package com.llbt.app.interceptor;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import com.alibaba.fastjson.JSONObject;
import com.llbt.app.config.ConstantConfig;

public class RequestInterceptor implements HandlerInterceptor{
	
	
	private static final Logger logger = LoggerFactory.getLogger(RequestInterceptor.class);

	@Override
	public void afterCompletion(HttpServletRequest arg0,
			HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
			Object arg2, ModelAndView arg3) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
			Object arg2) throws Exception {
		System.out.println("interface method");
		return true;
	}

	private void printWriter(HttpServletResponse response, String errorMsg) {
		JSONObject json = new JSONObject();
		json.put("ret_code", ConstantConfig.Error_Code12);
		json.put("ret_message", errorMsg);
		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		PrintWriter out;
		try {
			out = response.getWriter();
			out.print( "(" + json.toJSONString() + ")");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
