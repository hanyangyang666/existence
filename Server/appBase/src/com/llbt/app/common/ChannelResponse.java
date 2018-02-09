package com.llbt.app.common;

import com.llbt.channel.enumm.ReturnType;


public class ChannelResponse {
	
	private String retCode;
	
	private String retMsg;
	
	private Object dataEntity;

	

	public String getRetCode() {
		return retCode;
	}

	public void setRetCode(String retCode) {
		this.retCode = retCode;
	}

	public String getRetMsg() {
		return retMsg;
	}

	public void setRetMsg(String retMsg) {
		this.retMsg = retMsg;
	}

	public Object getDataEntity() {
		return dataEntity;
	}

	public void setDataEntity(Object dataEntity) {
		this.dataEntity = dataEntity;
	}
    
	
	public boolean OK(){
		if(this.getRetCode() .equals("0")){
			return false;
		}else if(this.getRetCode().equals("000000")){
			return true;
		}else{
			return false;
		}
	}
	
	public static ChannelResponse getReturnData(ReturnType type){
		ChannelResponse res = new ChannelResponse();
		res.setRetCode(type.getRet_code());
		res.setRetMsg(type.getRet_msg());
		return res;
	}
	
	public static ChannelResponse getReturnData(String code) {
		ChannelResponse res = new ChannelResponse();
		res.setRetCode(code);
		return res;
	}
	
	public static ChannelResponse getReturnData(String code, String msg) {
		ChannelResponse res = new ChannelResponse();
		res.setRetCode(code);
		res.setRetMsg(msg);
		return res;
	}
	
	public static ChannelResponse getReturnData(ReturnType type,Object data){
		ChannelResponse res=new ChannelResponse();
		res.setRetCode(type.getRet_code());
		res.setRetMsg(type.getRet_msg());
		res.setDataEntity(data);
		return res;
	}
	
	public static ChannelResponse getReturnData(String code, Object data) {
		ChannelResponse res = new ChannelResponse();
		res.setRetCode(code);
		res.setDataEntity(data);
		return res;
	}
	
}
