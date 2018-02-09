package com.llbt.app.util;

import java.io.UnsupportedEncodingException;

public class MD5Util {

	public static String MD5(String str) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] array = null;
			try {
				array = md.digest(str.getBytes("utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
            StringBuffer sb = new StringBuffer("");
            for (int i = 0; i < array.length; ++i) {
                sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
            }
            return sb.toString();
        } catch (java.security.NoSuchAlgorithmException e) {
        	e.printStackTrace();
        }
        return null;
    }
	
	public static void main(String[] args) {
		String str = "appVersion=1.0&codeType=forgetPassword&deviceModel=2014811&deviceOS=android&deviceVersion=4.4.4&imei=866048028983684&key=41997429&timeStamp=1515036394719&token=null&userPhone=13113687503";
		System.out.println(str.length());
		String ss = MD5(str);
	    
		System.out.println(ss);
	
	}

}
