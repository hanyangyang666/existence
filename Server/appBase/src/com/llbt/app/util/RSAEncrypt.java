package com.llbt.app.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.Key;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;

import org.apache.commons.codec.binary.Base64;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import com.llbt.app.common.AppConfig;

public class RSAEncrypt {
	 public static final String  SIGN_ALGORITHMS = "MD5WithRSA";
	    private static final String ALGORITHM       = "RSA";
	    private static final int MAX_ENCRYPT_BLOCK = 117;  
	    private static final int MAX_DECRYPT_BLOCK = 128;  
//	    public static final String priKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAPKZrs4MTh0QjSWYQ8bkpIo8TsPLWtbA+PLdScMmz6GMMiWr4KYREAM6eTdzJG+NK/aK+ImihxOrJV/VJNvoSscdQxP7brvjbEQbFs+PKovMWEO7xJmBm3U27+9HOH9NFprSWCog9cfbtQXAXwTeGFyXybOgUtVsL17kcIJabYd3AgMBAAECgYA8kzsen9vxTeywcnCZ/QVIrv5LzT8FWHHQ0ohUfiBiCguLdHtHfAMviy4xNkLmx60uhkzAsSBhPN68KxBlCH+C88j3OiLtibCHTEiyTtjuT6C+hmh0UNY6HI5kcKbGqalGp1wvtf6FQ9GT6c5t8FdsT5vDua75VdSqkOg7ETu+gQJBAPxrjhQ6Lq5wb7aiCFnDkr6rD4quQRrYwEanjRv/p/3J8Udntmfq67Q2z1gWvHsj8tvdq9Ufn9FjPb8D7m1B58cCQQD2CnnHAd6r+L96q74EWrCUyzT0t4/i71cX0rlqT7Exo6N2frPkX3mPU0oQ45nxW3dUC0Q4SoDPFHYpsrnzMkLRAkAMpQqqArwh6un9uSI39O8d9A3EQbRrNt5y66PK+kSdFweqLwzZKdCC55f9bq4kcQmScAlSlNH5uEH4lqbT/FAzAkAwBTVlYPkLyX3dvF1Wzjh+ofaQ+K6tlRcDgE5N8IXd8Vk7DFmh7fx0u8XN7A4krDxq+9PxxKDFSfISC179l0axAkABIMCJqK+fE2MASJgliVkrwc/JvJM9BhL1RwcfIDvqvL00exyBlPMbwxEoJe//2F53/h1Kh/rN0bCGU21/njrv";
//	    public static final String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDyma7ODE4dEI0lmEPG5KSKPE7Dy1rWwPjy3UnDJs+hjDIlq+CmERADOnk3cyRvjSv2iviJoocTqyVf1STb6ErHHUMT+26742xEGxbPjyqLzFhDu8SZgZt1Nu/vRzh/TRaa0lgqIPXH27UFwF8E3hhcl8mzoFLVbC9e5HCCWm2HdwIDAQAB";
//      
	    public static final String priKey = AppConfig.getValue("priKey");
	    public static final String pubKey = AppConfig.getValue("pubKey");
	    /**
	     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串  进行md5加密
	     * 
	     * @param params
	     *            需要排序并参与字符拼接的参数组
	     * @return 拼接后字符串
	     */
	    public static String createLinkString(Map<String, Object> params)
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
	    
	    public static String encrypt(String content,String charset, String aliPubKey) {
	        try {
	            
	            byte[] keyBytes = Base64.decodeBase64(aliPubKey);  
	            X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);  
	            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);  
	            Key pubKey = keyFactory.generatePublic(x509KeySpec);  
	            
	            // 对数据加密  
//	            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm()); 
	            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
	            cipher.init(Cipher.ENCRYPT_MODE, pubKey);  
	            
	            byte[] data = content.getBytes(charset);
	            byte[] splitData = splitData(data, cipher,MAX_ENCRYPT_BLOCK);  
	            return Base64.encodeBase64String(splitData); 

	        } catch (Exception e) {
	            
	            throw new RuntimeException(e);
	        }
	        
	    }


	    public static String decrypt(String content,String charset, String myPriKey){
	        
	        try {
	            
	            byte[] keyBytes = Base64.decodeBase64(myPriKey);  
	            PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
	            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);  
	            Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
//	            Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());  
//	            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding",new BouncyCastleProvider());
	            Cipher cipher = Cipher.getInstance("RSA"); 
	            cipher.init(Cipher.DECRYPT_MODE, privateK);  
	            byte[] data = Base64.decodeBase64(content);
	            byte[] splitData = splitData(data, cipher,MAX_DECRYPT_BLOCK);  
	            return new String(splitData,charset);   

	        } catch (Exception e) {
	            
	            throw new RuntimeException(e);
	        }
	    }
	    

	    private static byte[] splitData(byte[] data, Cipher cipher,int maxLen) throws IllegalBlockSizeException,
	                                                                  BadPaddingException, IOException {
	        int totalLen = data.length;  
	        int offSet = 0 ;
	        int index = 0;  
	        byte[] cache;  
	        
	        ByteArrayOutputStream out = new ByteArrayOutputStream();  
	        // 对数据分段解密  
	        while (totalLen - offSet > 0) {  
	            if (totalLen - offSet > maxLen) {  
	                cache = cipher.doFinal(data, offSet, maxLen);  
	            } else {  
	                cache = cipher.doFinal(data, offSet, totalLen - offSet);  
	            }  
	            out.write(cache, 0, cache.length);  
	            index++;  
	            offSet = index * maxLen;  
	        }  
	        byte[] decryptedData = out.toByteArray();  
	        out.close();
	        return decryptedData;
	        
	    }
	    

	    public static String sign(String content, String charset, String myPriKey) {

	        try {
	            
	            byte[] decodePubKey = Base64.decodeBase64(myPriKey);
	            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(decodePubKey);
	            KeyFactory factory = KeyFactory.getInstance(ALGORITHM);
	            PrivateKey privateKey = factory.generatePrivate(keySpec);

	            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

	            signature.initSign(privateKey);
	            signature.update(content.getBytes(charset));

	            byte[] signed = signature.sign();

	            return Base64.encodeBase64String(signed);

	        } catch (Exception e) {

	            throw new RuntimeException(e);
	        }

	    }


	    public static boolean verify(String content, String charset, String aliPubKey,String aliSign) {
	        try {
	            
	            KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);
	            byte[] decodePubKey = Base64.decodeBase64(aliPubKey);
	            
	            PublicKey pubKey = keyFactory.generatePublic(new X509EncodedKeySpec(decodePubKey));

	            Signature signature = Signature.getInstance(SIGN_ALGORITHMS);

	            signature.initVerify(pubKey);
	            signature.update(content.getBytes(charset));
	            
	            return signature.verify(Base64.decodeBase64(aliSign));

	        } catch (Exception e) {
	            
	            throw new RuntimeException(e);
	        }
	    }
	    
	    /** *//** 
	     * <p> 
	     * 用私钥对信息生成数字签名 
	     * </p> 
	     *  
	     * @param data 已加密数据 
	     * @param privateKey 私钥(BASE64编码) 
	     *  
	     * @return 
	     * @throws Exception 
	     */  
	    public static String sign(byte[] data, String privateKey) throws Exception {  
	        byte[] keyBytes = Base64.decodeBase64(privateKey);  
	        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);  
	        KeyFactory keyFactory = KeyFactory.getInstance(ALGORITHM);  
	        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);  
	        Signature signature = Signature.getInstance(SIGN_ALGORITHMS);  
	        signature.initSign(privateK);  
	        signature.update(data);  
	        return Base64.encodeBase64String(signature.sign());  
	    }  
	    
	    
	    public static void main(String[] args){
	        
//	         String priKey = "MIICdQIBADANBgkqhkiG9w0BAQEFAASCAl8wggJbAgEAAoGBAPKZrs4MTh0QjSWYQ8bkpIo8TsPLWtbA+PLdScMmz6GMMiWr4KYREAM6eTdzJG+NK/aK+ImihxOrJV/VJNvoSscdQxP7brvjbEQbFs+PKovMWEO7xJmBm3U27+9HOH9NFprSWCog9cfbtQXAXwTeGFyXybOgUtVsL17kcIJabYd3AgMBAAECgYA8kzsen9vxTeywcnCZ/QVIrv5LzT8FWHHQ0ohUfiBiCguLdHtHfAMviy4xNkLmx60uhkzAsSBhPN68KxBlCH+C88j3OiLtibCHTEiyTtjuT6C+hmh0UNY6HI5kcKbGqalGp1wvtf6FQ9GT6c5t8FdsT5vDua75VdSqkOg7ETu+gQJBAPxrjhQ6Lq5wb7aiCFnDkr6rD4quQRrYwEanjRv/p/3J8Udntmfq67Q2z1gWvHsj8tvdq9Ufn9FjPb8D7m1B58cCQQD2CnnHAd6r+L96q74EWrCUyzT0t4/i71cX0rlqT7Exo6N2frPkX3mPU0oQ45nxW3dUC0Q4SoDPFHYpsrnzMkLRAkAMpQqqArwh6un9uSI39O8d9A3EQbRrNt5y66PK+kSdFweqLwzZKdCC55f9bq4kcQmScAlSlNH5uEH4lqbT/FAzAkAwBTVlYPkLyX3dvF1Wzjh+ofaQ+K6tlRcDgE5N8IXd8Vk7DFmh7fx0u8XN7A4krDxq+9PxxKDFSfISC179l0axAkABIMCJqK+fE2MASJgliVkrwc/JvJM9BhL1RwcfIDvqvL00exyBlPMbwxEoJe//2F53/h1Kh/rN0bCGU21/njrv";
//	         String pubKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDyma7ODE4dEI0lmEPG5KSKPE7Dy1rWwPjy3UnDJs+hjDIlq+CmERADOnk3cyRvjSv2iviJoocTqyVf1STb6ErHHUMT+26742xEGxbPjyqLzFhDu8SZgZt1Nu/vRzh/TRaa0lgqIPXH27UFwF8E3hhcl8mzoFLVbC9e5HCCWm2HdwIDAQAB";
//		         
	        String encrptyContent = "NDVEqxEvN4MDOH4FxXZzHXtEZktFv1V/KvRrMY6hwJ8tcrKlOhIi732KWa36vEO+9Ns4a0qwKuSF5QgeBY2LcKeRGVAuP9BEvpeE0yvK32vZl+Eo7jRAwfhnoOS5E53g8KAAOVpw9ZMmIDP8tmzSnsBQxK7riukaWnP5hPx8WqA=";
	    	String charset = "utf-8";
//	         Map<String,Object> ha = new HashMap<String,Object>();
//	         ha.put("name", "haha");
//	         ha.put("phone", "12345678909");
//	         String content = createLinkString(ha);
//	        String sign = null;
//			try {
//				sign = sign(content.getBytes(),priKey);
//			} catch (Exception e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//	        System.out.println("签名值sign:"+sign);
//	        boolean result = verify(content,charset,pubKey,sign);
//	        System.out.println("验证结果："+result);
	        
	        String encrptyContent1 = encrypt("123456",charset,pubKey);
//	        System.out.println("encrptyContent:"+encrptyContent1);
//	    	String encrptyContent1 = null ;
//			try {
//				encrptyContent1 = new String(Base64.decodeBase64(encrptyContent),"utf-8");
//			} catch (UnsupportedEncodingException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
	    	System.out.println("加密后1："+encrptyContent1);
	        String realContent = decrypt(encrptyContent1,charset,priKey);
	        System.out.println("解密结果："+realContent);
//	        System.out.println(String.format("sign=%s result=%s realContent=%s",sign,result,realContent));
	    }
}
