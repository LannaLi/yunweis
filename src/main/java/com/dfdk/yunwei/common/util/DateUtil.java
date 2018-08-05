package com.dfdk.yunwei.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 将日期转为字符
	 * @param date
	 * @return
	 * @author Lanna
	 * @date 2018年8月3日
	 */
	public static String date2String(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 将日期转为字符
	 * @param date
	 * @param pattern
	 * @return
	 * @author Lanna
	 * @date 2018年8月3日
	 */
	public static String date2String(Date date,String pattern) {
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}
	
	
	public static void main(String[] args) {
		Date date = new Date();
		System.out.println(date2String(date,"yyyy-MM-dd"));
	}
}
