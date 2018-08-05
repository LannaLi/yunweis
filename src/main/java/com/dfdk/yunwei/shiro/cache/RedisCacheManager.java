package com.dfdk.yunwei.shiro.cache;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.apache.logging.log4j.LogManager;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;

import com.dfdk.yunwei.shiro.cache.serializer.ObjectSerializer;
import com.dfdk.yunwei.shiro.cache.serializer.RedisSerializer;
import com.dfdk.yunwei.shiro.cache.serializer.StringSerializer;

public class RedisCacheManager implements CacheManager{
	
	private static Logger logger = (Logger) LogManager.getLogger(RedisCacheManager.class);
	
	@SuppressWarnings("rawtypes")
	private final ConcurrentMap<String,Cache> caches = new ConcurrentHashMap<String,Cache>();
	private RedisSerializer<String> keySerializer = new StringSerializer();
	private RedisSerializer<Object> valSerializer = new ObjectSerializer();
	private IRedisManager redisManager;
	
	private static final int DEFAULT_EXPIRE = 1800;
	private int expire = DEFAULT_EXPIRE;
	
	public static final String DEFAULT_CACHE_KEY_PREFIX = "shiro:cache:";
	private String keyPrefix = DEFAULT_CACHE_KEY_PREFIX;

	public static final String DEFAULT_PRINCIPAL_ID_FIELD_NAME = "authCacheKey or id";
	private String principalIdFieldName = DEFAULT_PRINCIPAL_ID_FIELD_NAME;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public <K, V> Cache<K, V> getCache(String name) throws CacheException {
		logger.debug("get cache" + name);
		
		Cache cache = getCache(name);
		if (cache == null) {
			cache = new RedisCache<K,V>(redisManager,keySerializer,valSerializer,keyPrefix+name + ":",expire,principalIdFieldName);
			caches.put(name,cache);
		}
		return cache;
	}
	public IRedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(IRedisManager redisManager) {
		this.redisManager = redisManager;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}

	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}

	@SuppressWarnings("rawtypes")
	public RedisSerializer getKeySerializer() {
		return keySerializer;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setKeySerializer(RedisSerializer keySerializer) {
		this.keySerializer = keySerializer;
	}

	@SuppressWarnings("rawtypes")
	public RedisSerializer getValueSerializer() {
		return valSerializer;
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public void setValueSerializer(RedisSerializer valueSerializer) {
		this.valSerializer = valueSerializer;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}

	public String getPrincipalIdFieldName() {
		return principalIdFieldName;
	}

	public void setPrincipalIdFieldName(String principalIdFieldName) {
		this.principalIdFieldName = principalIdFieldName;
	}
	
}
