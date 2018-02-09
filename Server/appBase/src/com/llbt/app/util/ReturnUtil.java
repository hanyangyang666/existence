package com.llbt.app.util;

import java.io.Serializable;

import com.google.gson.Gson;
import com.llbt.channel.enumm.ReturnType;

@SuppressWarnings("serial")
public class ReturnUtil implements Serializable{

	String code;
	String info;
	Object result;
	
	public ReturnUtil(String code, String info, Object result) {
		super();
		this.code = code;
		this.info = info;
		this.result = result;
	}
	
	public static ReturnUtil returnData(ReturnData returnType){
		return returnData(returnType,null);
	}
	
	public static ReturnUtil returnData(ReturnData returnType,Object result){
		
		ReturnUtil ru=new ReturnUtil(returnType.getCode(),returnType.getInfo(),result);
		System.out.println("=============结果输出===============");
		System.out.println(new Gson().toJson(ru));
		System.out.println("=============结果输出===============");
		return ru;
	}

	
	public static ReturnUtil returnData(ReturnType returnType){
		return returnData(returnType,null);
	}
	
	

	public static ReturnUtil returnData(ReturnType returnType,Object result){
		
		ReturnUtil ru=new ReturnUtil(returnType.getRet_code(),returnType.getRet_msg(),result);
		System.out.println("=============结果输出===============");
		System.out.println(new Gson().toJson(ru));
		System.out.println("=============结果输出===============");
		return ru;
	}
	
	/*
	 * 如调用isp平台未收到成功状态码，把返回的错误信息原样返回，code统一为"-11000"
	 */
	public static ReturnUtil returnData(String rtnInf) {
		ReturnUtil ru=new ReturnUtil("-11000", rtnInf, null);
		System.out.println("=============结果输出===============");
		System.out.println(new Gson().toJson(ru));
		System.out.println("=============结果输出===============");
		return ru;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}
	
}
