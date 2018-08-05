package com.dfdk.yunwei.shiro.cache;

import java.util.Set;

public interface IRedisManager {
	/**
	 * 根据key获取值
	 * @param key
	 * @return
	 * @author Lanna
	 * @date 2018年7月31日
	 */
	byte[] get(byte[] key);
	/**
	 * 保存值
	 * @param key
	 * @param value
	 * @param expire
	 * @return
	 * @author Lanna
	 * @date 2018年7月31日
	 */
	byte[] set(byte[] key, byte[] value, int expire);
	/**
	 * 删除
	 * @param key
	 * @author Lanna
	 * @date 2018年7月31日
	 */
	void del(byte[] key);
	
	Long dbSize(byte[] pattern);
	
	Set<byte[]> keys(byte[] pattern);
	
}
