package com.llbt.app.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class IDCardUtil {

	private final static Date MINIMAL_BIRTH_DATE = new Date(-2209017600000L);  // 身份证的最小出生日期,1900年1月1日
	private final static int NEW_CARD_NUMBER_LENGTH = 18;
	private final static int OLD_CARD_NUMBER_LENGTH = 15;
	private final static int[] VERIFY_CODE_WEIGHT = { 7, 9, 10, 5, 8, 4, 2, 1,
		6, 3, 7, 9, 10, 5, 8, 4, 2 };//18位身份证中，各个数字的生成校验码时的权值
	private final static char[] VERIFY_CODE = { '1', '0', 'X', '9', '8', '7',
		'6', '5', '4', '3', '2' };   //18位身份证中最后一位校验码
	
	public static String contertToNewCardNumber(String oldCardNumber) {
    	if(OLD_CARD_NUMBER_LENGTH!=oldCardNumber.length())
    		return null;
    	for (int i = 0;i<OLD_CARD_NUMBER_LENGTH; i++) {
    			char ch = oldCardNumber.charAt(i);
    			if(!(ch >= '0' && ch <= '9'))
    				return null;
    	}
		StringBuilder buf = new StringBuilder(NEW_CARD_NUMBER_LENGTH);
		buf.append(oldCardNumber.substring(0, 6));
		buf.append("19");
		buf.append(oldCardNumber.substring(6));
		buf.append(calculateVerifyCode(buf));
		return buf.toString();
	}
	
	private static char calculateVerifyCode(CharSequence cardNumber) {
		int sum = 0;
		for (int i = 0; i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
			char ch = cardNumber.charAt(i);
			sum += ((int) (ch - '0')) * VERIFY_CODE_WEIGHT[i];
		}
		return VERIFY_CODE[sum % 11];
	}
	
	/**
	 * 身份证加密
	 * @param cardNumber
	 * @return
	 */
	public static String cId(String cid) {
		String jg = cid;
		if(cid!=null && !cid.equals("")){
			cid = cid.trim();
			jg = cid;
			if(cid.length()==18){
				String str="********";
				jg = cid.substring(0,6)+str+cid.substring(14,18);
			}else if(cid.length()==15){
				String str="******";
				jg = cid.substring(0,6)+str+cid.substring(12,15);
			}
		}
		return jg;
	}

	/**
	 * 银行卡加密
	 * @param cardNumber
	 * @return
	 */
	public static String cardId(String cardId) {
		String jg = cardId;
		if(cardId!=null && !cardId.equals("")){
			cardId = cardId.trim();
			jg = cardId;
			if(cardId.length()>8){
				String[] crdk = cardId.split("");
				int zj = crdk.length - 8;
				String str="";
				for(int i=1;i<zj;i++){
					str +="*";
				}
				jg = cardId.substring(0, 4) + str + cardId.substring(cardId.length()-4, cardId.length());
			}
		}
		return jg;
	}
	
	/**
	 * 手机号码加密
	 * @param cardNumber
	 * @return
	 */
	public static String phoneId(String phoneId) {
		String jg = phoneId;
		if(phoneId!=null && !phoneId.equals("")){
			phoneId = phoneId.trim();
			jg = phoneId;
			if(phoneId.length()==11){
				jg = phoneId.substring(0, 4)+"****"+phoneId.substring(8, 11);
			}
		}
		return jg;
	}
	
	
	public static void main(String[] args) {
		String ci = IDCardUtil.cardId("370802222940221002");
		System.out.println(ci);
	}
	
	
	/**
	 * 校验身份证是否合法
	 * @param cardNumber
	 * @return
	 */
	public static boolean validate(String cardNumber) {
		if(cardNumber==null||cardNumber.trim()==null)
			return false;
		cardNumber = cardNumber.trim();
		if (OLD_CARD_NUMBER_LENGTH == cardNumber.length()) {
		    cardNumber = contertToNewCardNumber(cardNumber);//将15位身份证号转为18位
		}
		if(cardNumber==null) return false;
		if(cardNumber.length()!=NEW_CARD_NUMBER_LENGTH)
			return false;		
		boolean result = true;
		// 身份证号的前17位必须是阿拉伯数字
		for (int i = 0; result && i < NEW_CARD_NUMBER_LENGTH - 1; i++) {
			char ch = cardNumber.charAt(i);
			if(ch < '0' || ch > '9')
				return false;
		}
		// 身份证号的第18位校验正确
		String charObj = String.valueOf(calculateVerifyCode(cardNumber));
		String charObj2 = String.valueOf(cardNumber.charAt(NEW_CARD_NUMBER_LENGTH - 1));
		if (!charObj.toUpperCase().equals(charObj2.toUpperCase())){
			return false;
		}
			
		// 出生日期不能晚于当前时间，并且不能早于1900年
		try {
			String birthDatePart=cardNumber.substring(6, 14);
			SimpleDateFormat sf=new SimpleDateFormat("yyyymmdd");
			Date birthDate = sf.parse(birthDatePart);
			if(null == birthDate)
				return false;
			if(birthDate.after(new Date()))
				return false;
			if(birthDate.before(MINIMAL_BIRTH_DATE))
				return false;
			// 出生日期中的年、月、日符合现实
			if(!checkDate(birthDate))
				return false;
		} catch (Exception e) {
			result = false;
		}						

		return true;
	}
	
	public static boolean checkDate(Date date){
		SimpleDateFormat sf=new SimpleDateFormat("yyyymmdd");
		String sdate = sf.format(date);
		boolean result=false;
		int year=Integer.parseInt(sdate.substring(0,4));
		int month=Integer.parseInt(sdate.substring(4,6));
		int day=Integer.parseInt(sdate.substring(6));   
		int Month[]={0,31,28,31,30,31,30,31,31,30,31,30,31};
		if(year%4==0&&year%100!=0||year%400==0)
			 Month[2]++;
		if(day<=Month[month]&&day>0&&month<13&&month>0&&year>0){
			result=true;
		}
		
		return result;	
	}
	
	  /** 
     * 获得一个UUID 
     * @return String UUID 
     */ 
    public static String getUUID(){ 
        String s = UUID.randomUUID().toString(); 
        //去掉“-”符号 
        return s.substring(0,8)+s.substring(9,13)+s.substring(14,18)+s.substring(19,23)+s.substring(24); 
    } 
}
