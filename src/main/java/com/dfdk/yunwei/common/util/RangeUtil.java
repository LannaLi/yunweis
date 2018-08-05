package com.dfdk.yunwei.common.util;

import java.util.Arrays;

/**
 * 随机工具类
 * @author Lanna
 *
 */
public class RangeUtil {
	
	private static final char[] CHAR_CODE = { '1', '2', '3', '4', '5', '6',
			 '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j',
			 'k', 'm', 'n', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y',
			 'z', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L',
			 'M', 'N', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
	
	/**
	 * @param Simple  只包含数字
	 * @param Medium  Medium包含数字和小写英文
	 * @param Hard    Hard包含数字和大小写英文
	 * @author Lanna
	 *
	 */
	public enum Level{Simple,Medium,Hard};
	
	
	public static char[] getSecurityCode(int length,Level level,boolean isRepeat) {
		//随机生成的字符长度
		int len = length;
		char[] code;
		switch (level) {
		case Simple:
			code = Arrays.copyOfRange(CHAR_CODE,0,9);
			break;
		case Medium:	
			code = Arrays.copyOfRange(CHAR_CODE,0,33);
			break;
		case Hard:
			code = Arrays.copyOfRange(CHAR_CODE,0,CHAR_CODE.length);
			break;
		default:
			code = Arrays.copyOfRange(CHAR_CODE,0,CHAR_CODE.length);
			break;
		}
		// 字符集合长度
		 int n = code.length;
		 // 抛出运行时异常
		 if (len > n && isRepeat == false) {
			 throw new RuntimeException(
				String.format(
						"调用getSecurityCode(%1$s,%2$s,%3$s)出现异常,"
						+ "当isRepeat为%3$s时,传入参数%1$s不能大于%4$s", len,level, isRepeat, n
						)
				);
		 }
		 // 存放抽取出来的字符
		 char[] result = new char[len];
		 // 判断能否出现重复的字符
		 if (isRepeat) {
			 for (int i = 0; i < result.length; i++) {
				 // 索引 0 and n-1
				 int r = (int) (Math.random() * n);
				 // 将result中的第i个元素设置为codes[r]存放的数值
				 result[i] = code[r];
			 }
		 } else {
			 for (int i = 0; i < result.length; i++) {
				 // 索引 0 and n-1
				 int r = (int) (Math.random() * n);
				 // 将result中的第i个元素设置为codes[r]存放的数值
				 result[i] = code[r];
				 // 必须确保不会再次抽取到那个字符，因为所有抽取的字符必须不相同。
				 // 因此，这里用数组中的最后一个字符改写codes[r]，并将n减1
				 code[r] = code[n - 1];
				 n--;
			 }
		 }
		 return result;
	}
	
	
	public static String rangeIdent() {
		StringBuilder sb = new StringBuilder();
		sb.append("R68706875");
		char[] code = getSecurityCode(6,Level.Medium,false);
		sb.append(String.valueOf(code));
		return sb.toString();
	}
	
	public static void main(String[] args) {
		
		System.out.println(rangeIdent());
	}
}
