package com.dfdk.yunwei.common.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.sf.json.JSONObject;
/**
 * 序列化
 * @Company:www.dfdk.com.cn 
 * @author Lanna 
 * @date 2018年7月27日  
 * @version 1.0
 */
public class SerializeUtil {
	
	private final static Logger logger = LogManager.getLogger(SerializeUtil.class);
	
	/**
	 * 序列化
	 * @param value
	 * @return
	 */
	public static byte[] serialize(Object value) {
		if (value == null) {
			throw new NullPointerException("序列化的对象不能为空值!");
		}
		byte[] val = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream oos = null;
		try {
			bos = new ByteArrayOutputStream();
			oos = new ObjectOutputStream(bos);
			oos.writeObject(value);
			oos.close();
			bos.close();
			val = bos.toByteArray();
		} catch (Exception e) {
			logger.error(e.toString(),"serialize error %s",JSONObject.fromObject(value));
		} finally {
			close(bos);
			close(oos);
		}
		return val;
	}
	
	/**
	 * 根据值,类进行反序列化
	 * @param value
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static <T>T deserialize(byte[] value,Class<T>...clazz){
		Object obj = null;
		ByteArrayInputStream bis = null;
		ObjectInputStream ois = null;
		try {
			if (value != null) {
				bis = new ByteArrayInputStream(value);
				ois = new ObjectInputStream(bis);
				obj = ois.readObject();
			}
		} catch (Exception e) {
			logger.error(e.getMessage(),"Serialize Error %s",value);
		} finally {
			close(bis);
			close(ois);
		}
		return (T)obj;
	}
	
	@SuppressWarnings("unchecked")
	public static Object deserialize(byte[] val) {
		return deserialize(val,Object.class);
	}
	
	/**
	 * 关闭流
	 * @param closeable
	 */
	public static void close(Closeable closeable) {
		if (closeable != null) {
			try {
				closeable.close();
			} catch (Exception e) {
				logger.error(e.getMessage());
			}
		}
	}
	
}
