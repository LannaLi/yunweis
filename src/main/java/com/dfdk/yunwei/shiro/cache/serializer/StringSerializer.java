package com.dfdk.yunwei.shiro.cache.serializer;

import java.io.UnsupportedEncodingException;

import com.dfdk.yunwei.shiro.cache.ex.SerializationException;
/**
 * 字符串序列化与反序列化
 * @Company:www.dfdk.com.cn 
 * @author Lanna 
 * @date 2018年7月31日  
 * @version 1.0
 */
public class StringSerializer implements RedisSerializer<String> {
	
	private static final String DEFAULT_CHARSET = "UTF-8";
	
	private String charset = DEFAULT_CHARSET;
	
	public String getCharset() {
		return charset;
	}
	
	public void setCharset(String charset) {
		this.charset = charset;
	}
	@Override
	public byte[] serialize(String t) throws SerializationException {
		try {
            return (t == null ? null : t.getBytes(charset));
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("serialize error, string=" + t, e);
        }
	}

	@Override
	public String deserialize(byte[] bytes) throws SerializationException {
		try {
            return (bytes == null ? null : new String(bytes, charset));
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException("deserialize error", e);
        }
	}

}
