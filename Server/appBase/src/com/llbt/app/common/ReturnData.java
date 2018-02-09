package com.llbt.app.common;

import com.google.gson.Gson;


public class ReturnData {

	private String ret_code;
	private String ret_msg;
	private Object data_entity;
	
	public ReturnData(){
		
	}
	
	public ReturnData(String retCode, String retMsg, String dataEntity) {
		super();
		this.ret_code = retCode;
		this.ret_msg = retMsg;
		this.data_entity = dataEntity;
	}
	public String getRet_code() {
		return ret_code;
	}
	public void setRet_code(String retCode) {
		ret_code = retCode;
	}
	public String getRet_msg() {
		return ret_msg;
	}
	public void setRet_msg(String retMsg) {
		ret_msg = retMsg;
	}
	public Object getData_entity() {
		return data_entity;
	}

	public void setData_entity(Object dataEntity) {
		data_entity = dataEntity;
	}

	
	/*
	 * 如调用isp平台未收到成功状态码，渠道返回的原错误信息，这一层继续上送原错误信息，code统一为"-11000"
	 */
	public static ReturnData getReturnData(String code, String msg) {
		ReturnData returnData = new ReturnData();
		returnData.setRet_code(code);
		returnData.setRet_msg(msg);
		return returnData;
	}

	@Override
	public String toString() {
		Gson gson = new Gson();
		return gson.toJson(this);
	}
	
	
	
}
