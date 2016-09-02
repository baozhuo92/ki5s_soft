package com.ki5s.base.util;
import java.io.UnsupportedEncodingException;  
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

public class UtilBase64  {
	
	public static String encryption(String password) {
		byte[] b = null;  
        String s = null;  
        try {  
            b = password.getBytes("utf-8");  
        } catch (UnsupportedEncodingException e) {  
            e.printStackTrace();  
        }  
        if (b != null) {  
            s = new BASE64Encoder().encode(b);  
        }  
        return s;  
	}
	/** 解密 */ 
    public static String decrypt(String s) {  
        byte[] b = null;  
        String result = null;  
        if (s != null) {  
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
