package com.llbt.channel.enumm;

/**
 * 结果消息返回类(枚举)
 * @author it
 *
 */
public enum ReturnType {

	
	OK("000000","请求成功"),
	ERROR("-10000","请求异常"),
	ERROR_PARAM("-10001","参数异常"),
	ERROR_PARAMLOST("-10002","参数缺失"),
	ERROR_EMPTY("-10003","返回值为空"),
	ERROR_FILE("-10004","文件上传失败"),
	ERROR_MODIFY("-10006","必须修改且前后值不可以全部一致");
	
	
	private String ret_code;
	private String ret_msg;
	ReturnType(String ret_code,String ret_msg){
		this.ret_code=ret_code;
		this.ret_msg=ret_msg;
	}
	public String getRet_code() {
		return ret_code;
	}
	public void setRet_code(String ret_code) {
		this.ret_code = ret_code;
	}
	public String getRet_msg() {
		return ret_msg;
	}
	public void setRet_msg(String ret_msg) {
		this.ret_msg = ret_msg;
	}
	
	
	
}
