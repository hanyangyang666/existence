package com.llbt.app.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.llbt.app.common.ChannelResponse;

/**
 * 公共方法类
 * @author xdy
 *
 */
public class CommonUtil {

	//UUID 
    public static String getUuid() {
    	 UUID uu = UUID.randomUUID();
    	 String uuid = null;
    	 if(uu != null)
		     uuid = uu.toString().replaceAll("-", "");
		 return uuid;
    } 
    
    
    //接收请求获取报文数据
    /**
     * 接收请求获取报文数据,转换对应的实体对象
     * @param request
     * @return
     */
    public static JSONObject init(HttpServletRequest request) {
    	StringBuilder sb = new StringBuilder();  
        try(BufferedReader reader = request.getReader();) {  
                 char[] buff = new char[1024];  
                 int len;  
                 while((len = reader.read(buff)) != -1) {  
                          sb.append(buff,0, len);  
                 }  
        }catch (IOException e) {  
                 e.printStackTrace();  
        }
        JSONObject json = JSONObject.parseObject(sb.toString());
        //GateVo gateVo = JSON.parseObject(json.toJSONString(), GateVo.class);
        return json;

    } 
    
    /**
     * 生成sessionToken
     * @return
     */
    public static String getOnLyCode(){
		String token=UUID.randomUUID().toString();//System.currentTimeMillis()+AccName;//System.currentTimeMillis()+AccName
		return string2MD5(token);
	}
	
	/*** 
     * MD5加码 生成32位md5码 
     */  
    public static String string2MD5(String inStr){  
        MessageDigest md5 = null;  
        try{  
            md5 = MessageDigest.getInstance("MD5");  
        }catch (Exception e){  
            System.out.println(e.toString());  
            e.printStackTrace();  
            return "";  
        }  
        char[] charArray = inStr.toCharArray();  
        byte[] byteArray = new byte[charArray.length];  
  
        for (int i = 0; i < charArray.length; i++)  
            byteArray[i] = (byte) charArray[i];  
        byte[] md5Bytes = md5.digest(byteArray);  
        StringBuffer hexValue = new StringBuffer();  
        for (int i = 0; i < md5Bytes.length; i++){  
            int val = ((int) md5Bytes[i]) & 0xff;  
            if (val < 16)  
                hexValue.append("0");  
            hexValue.append(Integer.toHexString(val));  
        }  
        return hexValue.toString();  
  
    } 
    
    
    
    /*** 
     * 二维码   生成18位的交易流水号 
     */  
    public static String transactionCode(){  
    	String strCode = "";

		Random random = new Random();
		strCode+=random.nextInt(9)+1; 
		for (int i = 0; i < 18-1; i++) {
			strCode+=random.nextInt(10); 
		}
    	return strCode;
    }
    
    /**
     * 返回结果
     * @param res
     * @param code
     * @param msg
     * @return
     */
	public static ChannelResponse result(ChannelResponse res,String code,String msg) {
		if (res == null)
		{
			res = new ChannelResponse();
		}
		
	     res.setRetCode(code);
		 res.setRetMsg(msg);
		 return res;
	} 
	
	
	/**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串  进行md5加密
     * 
     * @param params
     *            需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map params)
    {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);
        String prestr = "";
        for (int i = 0; i < keys.size(); i++ )
        {
            String key = keys.get(i);
            Object value = params.get(key);

            if (i == keys.size() - 1)
            {// 拼接时，不包括最后一个&字符
                prestr = prestr + key + "=" + value;
            }
            else
            {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }
    
}