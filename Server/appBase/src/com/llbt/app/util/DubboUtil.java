package com.llbt.app.util;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class DubboUtil {

	public static ClassPathXmlApplicationContext context;
	
	private DubboUtil(){}
	
	private static ClassPathXmlApplicationContext getContext(){
		if(context==null){
			context = new ClassPathXmlApplicationContext("/demo-consumer.xml");
			context.setValidating(false);
		}
		return context;
	}
	
	public static Object getBean(String name){
		return getContext().getBean(name);
	}
	
}
