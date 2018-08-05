package com.dfdk.yunwei.shiro.cache.serializer;

import com.dfdk.yunwei.shiro.cache.ex.SerializationException;
/**
 * redis序列化接口
 * @Company:www.dfdk.com.cn 
 * @author Lanna 
 * @date 2018年7月31日  
 * @version 1.0
 */
public interface RedisSerializer<T> {
	
	/**
	 * 序列化
	 * @param t
	 * @return
	 * @throws SerializationException
	 * @author Lanna
	 * @date 2018年7月31日
	 */
	byte[] serialize(T t) throws SerializationException;
	/**
	 * 反序列化
	 * @param bytes
	 * @return
	 * @throws SerializationException
	 * @author Lanna
	 * @date 2018年7月31日
	 */
	T deserialize(byte[] bytes) throws SerializationException;
}
