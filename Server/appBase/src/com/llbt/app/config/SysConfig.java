package com.llbt.app.config;

import java.util.Timer;

public class SysConfig {

	public final static boolean PrintLog=true;
	/**
	 * 普通日志
	 */
	public final static boolean NormalLog=true;
	/**
	 * 数据调试日志
	 */
	public final static boolean DebugDataLog=true;
	/**
	 * 是否使用假数据
	 */
	public final static boolean FalseData=false;
	/**
	 * 公共交易信息组件
	 */
	public final static String TransChannel="49";
	public final static String LoginType="2";
	
	/**
	 * 最大请求预设值
	 */
	public static int RequestUpCount=7500;
	/**
	 * 接口请求统计间隔时间
	 */
	public static long RequestCountInterval=5*60*1000;
	/**
	 * 接口请求流量状态
	 * 0  请求数量未达到预设值
	 * 1  请求数量未达到预设值
	 */
	public static int RequestFlowState=0;
	
	/**
	 * 接口请求统计计时器间隔
	 */
	public static long RequestTimerInterval=10*1000;
	/**
	 * 接口请求统计计时器
	 */
	public static Timer RequestCountTimer;
	
	public static String trancode = "010301";
	
	public static String Check = "0";
	
	
}
