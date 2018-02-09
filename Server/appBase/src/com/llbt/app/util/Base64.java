package com.llbt.app.util;

import java.io.UnsupportedEncodingException;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class Base64 {

	//加密  
    public static String getBase64(String str) {  
        byte[] b = null;  
        String s = "";  
        if(str != null && !"".equals(str)){
        	try {  
                b = str.getBytes("utf-8");  
            } catch (UnsupportedEncodingException e) {  
                e.printStackTrace();  
            }  
            if (b != null) {  
                s = new BASE64Encoder().encode(b);  
                if(s.indexOf("+")>-1){
                	s = s.replaceAll("[+]", "!");
                }
                if(s.indexOf("/")>-1){
                	s = s.replaceAll("[/]", "-");
                }
            }  
        }
        return s;  
    } 
    
    //解密  
    public static String getFromBase64(String s) {  
        byte[] b = null;  
        String result = "";  
        if (s != null && !"".equals(s)) {
        	if(s.indexOf("!")>-1){
        		s = s.replaceAll("!", "+");
        	}
        	if(s.indexOf("!")>-1){
        		s = s.replaceAll("-", "/");
        	}
            BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8"); 
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    } 
    
    //解密(Double)  
    public static String getDoubleBase64(String s) {  
        byte[] b = null;  
        String result = "0.00";  
        if (s != null && !"".equals(s)) {  
        	if(s.indexOf("!")>-1){
        		s = s.replaceAll("!", "+");
        	}
        	if(s.indexOf("!")>-1){
        		s = s.replaceAll("-", "/");
        	}
			BASE64Decoder decoder = new BASE64Decoder();  
            try {  
                b = decoder.decodeBuffer(s);  
                result = new String(b, "utf-8");  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    } 
    
}