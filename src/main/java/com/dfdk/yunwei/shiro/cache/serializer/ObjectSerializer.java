package com.dfdk.yunwei.shiro.cache.serializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.dfdk.yunwei.shiro.cache.ex.SerializationException;
/**
 * 对象序列化与反序列化
 * @Company:www.dfdk.com.cn 
 * @author Lanna 
 * @date 2018年7月31日  
 * @version 1.0
 */
public class ObjectSerializer implements RedisSerializer<Object>{
	
	public static final int BYTE_ARRAY_OUTPUT_STREAM_SIZE = 128;
	
	@Override
	public byte[] serialize(Object t) throws SerializationException {
		byte[] result = new byte[0];
		
		if (t == null) {
			return result;
		}
		
		ByteArrayOutputStream byteStream = new ByteArrayOutputStream(BYTE_ARRAY_OUTPUT_STREAM_SIZE);
		
		if (!(t instanceof Serializable)) {
			throw new SerializationException("requires a Serializable payload "
                    + "but received an object of type [" + t.getClass().getName() + "]");
		}
		
		try {
			ObjectOutputStream objectStream = new ObjectOutputStream(byteStream);
			objectStream.writeObject(t);
			objectStream.flush();
			result = byteStream.toByteArray();
		} catch (Exception e) {
			throw new SerializationException("serialize error, object=" + t, e);
		}
		return result;
	}

	@Override
	public Object deserialize(byte[] bytes) throws SerializationException {
		Object result = null;
		
		if (bytes == null || bytes.length == 0) {
			return result;
		}
		
		try {
			ByteArrayInputStream byteStream = new ByteArrayInputStream(bytes);
			ObjectInputStream objectStream = new ObjectInputStream(byteStream);
			result = objectStream.readObject();
		} catch (IOException e) {
            throw new SerializationException("deserialize error", e);
        } catch (ClassNotFoundException e) {
            throw new SerializationException("deserialize error", e);
        }
		return result;
	}

}
