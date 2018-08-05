package com.dfdk.yunwei.common.util;

import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

public class MD5 {
	
	private static final String  ENCRYP_MD5 = "MD5";
	/**
	 * MD5加密的值
	 * @param pwd 原始密码
	 * @return 返回加密的值
	 */
	public static String getMD5String(String pwd) {
		ByteSource salt = ByteSource.Util.bytes(Const.SALT_VALUE);
		String MD5Pwd = new SimpleHash(ENCRYP_MD5,pwd,salt).toString();
		return MD5Pwd;
	}
	
	/**
	 * 设置原始密码的盐值
	 * @return 原始密码的加密值
	 */
	public static String getOriginPwdMD5String() {
		ByteSource salt = ByteSource.Util.bytes(Const.SALT_VALUE);
		String MD5Pwd = new SimpleHash(ENCRYP_MD5,Const.ORIGIN_PASSWORD,salt).toString();
		return MD5Pwd;
	}
	
	public static void main(String[] args) {
		System.out.println(getOriginPwdMD5String());
	}
}
