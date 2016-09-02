package com.ki5s.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sun.jmx.snmp.Timestamp;

/**
 * 时间格式化工具类
 * @author 包卓
 *
 */
public class UtilDateFormat {

	/**
	 * 格式化时间返回字符串
	 * @param 时间
	 * @param 格式 如:yyyy-MM-dd
	 * @return
	 */
	public static String DateFormat(Date date,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		String result = sdf.format(date);
		return result;
	}
	/**
	 * 格式化时间返回时间类型
	 * @param date 
	 * @param format
	 * @return
	 */
	public static Date DateFormat(String date,String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format);
			Date result = sdf.parse(date);
			return result;
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * 时间戳转字符串
	 * @param date
	 * @param format
	 * @return
	 */
	public static String timeStampToString(Long date,String format) {
		SimpleDateFormat sdf=new SimpleDateFormat(format);
		String result = sdf.format(date);
		return result;
	}
	
	public static void main(String[] e){
		System.out.println(DateFormat("2016-03-03 21:32:51","yyyy-MM-dd HH:mm:ss")); 
		System.out.println(DateFormat(new Date(),"yyyy-MM-dd HH:mm:ss"));
		
		Long timeStamp = System.currentTimeMillis();  //获取当前时间戳
		System.out.println(timeStamp);
		System.out.println(timeStampToString(timeStamp,"yyyy-MM-dd HH:mm:ss"));
	}
	
}
