package com.llbt.app.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateFormatUtil {
	
	static Calendar calendar = Calendar.getInstance(); //得到日历
	
	public static String toDateString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(date);
	}
	
	public static String toTimeString(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	public static Date dateStringToDate(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.parse(str);
	}
	
	public static Date timeStringToDate(String str) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.parse(str);
	}
	
	public static Date now() throws ParseException {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date now = new Date();
		String nowStr = df.format(new Date());
		try {
			now = df.parse(nowStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return now;
	}

	/**
	 * 获取几个月前的日期
	 * @param startDate
	 * @param interval
	 * @return
	 */
	public static Date getBeforeMonthDate(Date startDate,int interval){
		calendar.setTime(startDate);//把当前时间赋给日历
		calendar.add(calendar.MONTH, -interval);  //设置为前interval月
		return calendar.getTime();   //得到前interval月的时间
	}
	
	/**
	 * 获取几个星期前的日期
	 * @param startDate
	 * @param interval
	 * @return
	 */
	public static Date getBeforeDayDate(Date startDate,int interval){
		calendar.setTime(startDate);//把当前时间赋给日历
		calendar.add(calendar.DATE, -interval);  //设置为前interval天
		return calendar.getTime();   //得到前interval天的时间
	}
	
	/**
	 * 获取几天前的日期
	 * @param startDate
	 * @param interval
	 * @return
	 */
	public static Date getBeforeWeekDate(Date startDate,int interval){
		
		calendar.setTime(startDate);//把当前时间赋给日历
		calendar.add(calendar.WEEK_OF_YEAR, -interval);  //设置为前interval周
		return calendar.getTime();   //得到前interval周的时间
	}
	
	/**
	 * 格式化时间
	 * @param date
	 * @param formatType
	 * @return
	 */
	public static String fomatDate(Date date ,String formatType){
		SimpleDateFormat df = new SimpleDateFormat(formatType);
		return df.format(date);
	}
	
	/**
	 * 将String转为Date
	 * @param stringDate
	 * @param formatType
	 * @return
	 */
	public static Date stringToDate(String stringDate ,String formatType){
		SimpleDateFormat df = new SimpleDateFormat(formatType);
		if(null != stringDate && "" != stringDate){
			try {
				return df.parse(stringDate);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
	
	/**
	 * 获取两个日期相差的天数
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static int getDayInterval(Date startDate, Date endDate) {
	       calendar.setTime(startDate);
	       int myStartDate= calendar.get(Calendar.DAY_OF_YEAR);
	       calendar.setTime(endDate);
	       int myEndDate = calendar.get(Calendar.DAY_OF_YEAR);
	       return myEndDate - myStartDate;
	 }
}
