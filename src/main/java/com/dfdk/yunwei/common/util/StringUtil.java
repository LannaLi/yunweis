package com.dfdk.yunwei.common.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;

@SuppressWarnings("deprecation")
public class StringUtil {
	
	/**
	 * 将字符串数组变成字符集合
	 * @param strs
	 * @return
	 */
	public static List<String> string2List(String[] strs){
		List<String> list = new ArrayList<String>();
		if(strs.length == 1) {
			list.add(strs[0]);
			return list;
		} else if (strs.length > 1) {
			for (int i=0;i<strs.length;i++) {
				list.add(strs[i]);
			}
			return list;
		}
		return null;
	}
	
	/**
	 * 截取倒数第二位后面的字符
	 * @param str
	 * @return
	 * @author Lanna
	 * @date 2018年8月2日
	 */
	public static String getSubString(String str) {
		if (str == null || str.length() == 0 || "".equals(str)) {
			return null;
		}
		return str.substring(str.length()-2,str.length());
	}
	
	/**
	 * 缩略字符串（不区分中英文字符）
	 * @param str 目标字符串
	 * @param length 截取长度
	 * @return
	 */
	public static String abbr(String str,int len) {
		if (str == null) {
			return "";
		}
		try {
			StringBuilder sb = new StringBuilder();
			int currentLength = 0;
			for (char c : replaceHtml(StringEscapeUtils.unescapeHtml4(str)).toCharArray()) {
				currentLength += String.valueOf(c).getBytes("GBK").length;
				if (currentLength <= len - 3) {
					sb.append(c);
				} else {
					sb.append("...");
					break;
				}
			}
			return sb.toString();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return "";
	}
	
	/**
	 * 替换掉HTML标签方法
	 */
	public static String replaceHtml(String html) {
		if (StringUtils.isBlank(html)){
			return "";
		}
		String regEx = "<.+?>";
		Pattern p = Pattern.compile(regEx);
		Matcher m = p.matcher(html);
		String s = m.replaceAll("");
		return s;
	}
	
	public static String getPre() {
		return "上一页";
	}
	
	public static String getFirst() {
		return "首页";
	}
	
	public static String getNext() {
		return "下一页";
	}
	
	public static String getLast() {
		return "尾页";
	}
}
