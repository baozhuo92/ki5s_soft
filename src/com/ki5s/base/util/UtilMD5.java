package com.ki5s.base.util;

/*
 * 调用 getMD5ofStr 方法获取加密字符串
 * 
 * */
public class UtilMD5 {

	private static final int SIZE = 2;
    private static final int KeyConstant = 64;
    private static final int CORPSCHAR = 0xff;

    private UtilMD5() {
    }

    public static String toHex(byte[] bytesArray) {
        StringBuffer strBuf = new StringBuffer(KeyConstant);
        String str = "";
        for (byte b : bytesArray) {
            str = Integer.toHexString(b & CORPSCHAR);
            if (str.length() < SIZE) {
                strBuf.append('0');
            }
            strBuf.append(str);
        }
        return strBuf.toString();
    }
}