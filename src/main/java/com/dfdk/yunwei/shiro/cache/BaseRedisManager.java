package com.dfdk.yunwei.shiro.cache;

import java.util.HashSet;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.slf4j.Logger;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.ScanParams;
import redis.clients.jedis.ScanResult;
/**
 * redis的删除改查抽象类
 * @Company:www.dfdk.com.cn 
 * @author Lanna 
 * @date 2018年7月31日  
 * @version 1.0
 */
public abstract class BaseRedisManager implements IRedisManager{
	
	private static Logger logger = (Logger) LogManager.getLogger(BaseRedisManager.class);
	/**
	 * 获取Jedis对象
	 * @return
	 * @author Lanna
	 * @date 2018年7月31日
	 */
	protected abstract Jedis getJedis();
	//默认保存时间
	protected static final int DEFAULT_EXPIRE = -1;
	//默认保存数
	protected static final int DEFAULT_COUNT = 100;
	
	private int count = DEFAULT_COUNT;
	
	private JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
	@Override
	public byte[] get(byte[] key) {
		if (key == null) {
			return null;
		}
		byte[] val = null;
		Jedis jedis = getJedis();
		try {
			val = jedis.get(key);
		} catch (Exception e) {
			logger.info("获取值的时候发生了异常:"+e.getMessage());
		} finally {
			jedis.close();
		}
		return val;
	}
	
	@Override
	public byte[] set(byte[] key, byte[] value, int expire) {
		if (key == null) {
            return null;
        }
        Jedis jedis = getJedis();
        try {
            jedis.set(key, value);
            if (expire >= 0) {
                jedis.expire(key, expire);
            }
         } finally {
            jedis.close();
        }
        return value;
	}
	
	@Override
	public void del(byte[] key) {
		if (key == null) {
            return;
        }
        Jedis jedis = getJedis();
        try {
            jedis.del(key);
        } finally {
            jedis.close();
        }
	}
	
	@Override
	public Long dbSize(byte[] pattern) {
		long dbSize = 0L;
        Jedis jedis = getJedis();
        try {
            ScanParams params = new ScanParams();
            params.count(count);
            params.match(pattern);
            byte[] cursor = ScanParams.SCAN_POINTER_START_BINARY;
            ScanResult<byte[]> scanResult;
            do {
                scanResult = jedis.scan(cursor, params);
                dbSize++;
                cursor = scanResult.getCursorAsBytes();
            } while (scanResult.getStringCursor().compareTo(ScanParams.SCAN_POINTER_START) > 0);
        } finally {
            jedis.close();
        }
        return dbSize;
	}
	
	@Override
	public Set<byte[]> keys(byte[] pattern) {
		Set<byte[]> keys = new HashSet<byte[]>();
        Jedis jedis = getJedis();

        try {
            ScanParams params = new ScanParams();
            params.count(count);
            params.match(pattern);
            byte[] cursor = ScanParams.SCAN_POINTER_START_BINARY;
            ScanResult<byte[]> scanResult;
            do {
                scanResult = jedis.scan(cursor, params);
                keys.addAll(scanResult.getResult());
                cursor = scanResult.getCursorAsBytes();
            } while (scanResult.getStringCursor().compareTo(ScanParams.SCAN_POINTER_START) > 0);
        } finally {
            jedis.close();
        }
        return keys;
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public JedisPoolConfig getJedisPoolConfig() {
		return jedisPoolConfig;
	}
	public void setJedisPoolConfig(JedisPoolConfig jedisPoolConfig) {
		this.jedisPoolConfig = jedisPoolConfig;
	}
}
