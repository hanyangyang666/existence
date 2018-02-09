package com.llbt.app.bean;


public class ReceiveData {

	private String app_version;
	private String app_channel;
	private String device_info;
	private String fund_code;
	private String timestamp;
	private String sign;
	private String data_entity;
	private String transportKey;
	
	String key;
	
	public ReceiveData(String appVersion, String appChannel, String deviceInfo,
			String fundCode, String timestamp, String sign, String dataEntity,
			String key,String transportKey) {
		super();
		this.app_version = appVersion;
		this.app_channel = appChannel;
		this.device_info = deviceInfo;
		this.fund_code = fundCode;
		this.timestamp = timestamp;
		this.sign = sign;
		this.data_entity = dataEntity;
		this.key = key;
		this.transportKey = transportKey;
	}
	
	public ReceiveData(String appVersion, String appChannel, String deviceInfo,
			String fundCode, String timestamp, String sign, String dataEntity,String transportKey) {
		super();
		this.app_version = appVersion;
		this.app_channel = appChannel;
		this.device_info = deviceInfo;
		this.fund_code = fundCode;
		this.timestamp = timestamp;
		this.sign = sign;
		this.data_entity = dataEntity;
		this.transportKey = transportKey;
	}
	public String getApp_version() {
		return app_version;
	}
	public void setApp_version(String appVersion) {
		app_version = appVersion;
	}
	public String getApp_channel() {
		return app_channel;
	}
	public void setApp_channel(String appChannel) {
		app_channel = appChannel;
	}
	public String getDevice_info() {
		return device_info;
	}
	public void setDevice_info(String deviceInfo) {
		device_info = deviceInfo;
	}
	public String getFund_code() {
		return fund_code;
	}
	public void setFund_code(String fundCode) {
		fund_code = fundCode;
	}
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getData_entity() {
		return data_entity;
	}
	public void setData_entity(String dataEntity) {
		data_entity = dataEntity;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}

	public String getTransportKey() {
		return transportKey;
	}

	public void setTransportKey(String transportKey) {
		this.transportKey = transportKey;
	}
	
	
	@Override
	public String toString() {
		return "CommonData [app_channel=" + app_channel + ", app_version="
				+ app_version + ", data_entity=" + data_entity
				+ ", device_info=" + device_info + ", fund_code=" + fund_code
				+ ", key=" + key + ", sign=" + sign + ", timestamp="
				+ timestamp + "]";
	}

	
	
}
