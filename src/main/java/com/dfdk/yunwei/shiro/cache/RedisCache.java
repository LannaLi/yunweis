package com.dfdk.yunwei.shiro.cache;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dfdk.yunwei.shiro.cache.ex.PrincipalIdNullException;
import com.dfdk.yunwei.shiro.cache.ex.PrincipalInstanceException;
import com.dfdk.yunwei.shiro.cache.ex.SerializationException;
import com.dfdk.yunwei.shiro.cache.serializer.ObjectSerializer;
import com.dfdk.yunwei.shiro.cache.serializer.RedisSerializer;
import com.dfdk.yunwei.shiro.cache.serializer.StringSerializer;

public class RedisCache<K,V> implements Cache<K,V> {
	
	private static final Logger logger = LoggerFactory.getLogger(RedisCache.class);
	
	@SuppressWarnings("rawtypes")
	private RedisSerializer keySerializer = new StringSerializer();
	@SuppressWarnings("rawtypes")
	private RedisSerializer valSerializer = new ObjectSerializer();
	private IRedisManager iRedisManager;
	private String keyPrefix = "";
	private int expire = 0;
	private String principalIdFieldName = RedisCacheManager.DEFAULT_PRINCIPAL_ID_FIELD_NAME;
	
	/**
	 * 实例化
	 * @param iRedisManager
	 * @param keySerializer
	 * @param valSerializer
	 * @param keyPrefix
	 * @param expire
	 * @param principalIdFieldName
	 */
	public RedisCache(IRedisManager iRedisManager,RedisSerializer<String> keySerializer
			,RedisSerializer<Object> valSerializer,String keyPrefix
			,int expire,String principalIdFieldName) {
		if (iRedisManager == null) {
	         throw new IllegalArgumentException("redisManager cannot be null.");
	     }
	     this.iRedisManager = iRedisManager;
		 if (keySerializer == null) {
			 throw new IllegalArgumentException("keySerializer cannot be null.");
		 }
		 this.keySerializer = keySerializer;
		if (valSerializer == null) {
			throw new IllegalArgumentException("valueSerializer cannot be null.");
		}
		 this.valSerializer = valSerializer;
		 if (keyPrefix != null && !"".equals(keyPrefix)) {
			 this.keyPrefix = keyPrefix;
		 }
		 if (expire != -1) {
		 	this.expire = expire;
		 }
		 if (principalIdFieldName != null && !"".equals(principalIdFieldName)) {
			 this.principalIdFieldName = principalIdFieldName;
		 }
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public V get(K key) throws CacheException {
		logger.debug("获取key ["+key+"]");
		if (key == null) {
			return null;
		}
		try {
			Object redisCacheKey = getRedisCacheKey(key);
			byte[] val = iRedisManager.get(keySerializer.serialize(redisCacheKey));
			if (val == null) {
				return null;
			}
			V value = (V)valSerializer.deserialize(val);
			return value;
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public V put(K key, V value) throws CacheException {
		logger.debug("设置redis缓存 ["+key+"]");
		if (key == null) {
			logger.warn("key不能为空");
			return value;
		}
		try {
			Object redisCacheKey = getRedisCacheKey(key);
			iRedisManager.set(keySerializer.serialize(redisCacheKey)
					,value != null? valSerializer.serialize(value) : null, expire);
			return value;
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public V remove(K key) throws CacheException {
		logger.debug("remove key ["+key+"]");
		if (key == null) {
			return null;
		}
		
		try {
			Object redisCacheKey = getRedisCacheKey(key);
			byte[] val = iRedisManager.get(keySerializer.serialize(redisCacheKey));
			V previous = (V)valSerializer.deserialize(val);
			iRedisManager.del(keySerializer.serialize(redisCacheKey));
			return previous;
		} catch (Exception e) {
			throw new CacheException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void clear() throws CacheException {
		logger.debug("清除缓存");
		Set<byte[]> keys = null;
		try {
			keys = iRedisManager.keys(keySerializer.serialize(this.keyPrefix + "*"));
		} catch (SerializationException e) {
			logger.error("获取key时发生了异常:"+e.getMessage().toString());
		}
		
		if (keys == null || keys.size() == 0) {
			return;
		}
		for (byte[] key:keys) {
			iRedisManager.del(key);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public int size() {
		Long size = 0L;
		try {
			size = new Long(iRedisManager.dbSize(keySerializer.serialize(this.keyPrefix + "*")));
		} catch (SerializationException e) {
			logger.error("获取key时发生了异常:"+e.getMessage().toString());
		}
		return size.intValue();
	}

	@SuppressWarnings("unchecked")
	@Override
	public Set<K> keys() {
		Set<byte[]> keys = null;
		try {
			keys = iRedisManager.keys(keySerializer.serialize(this.keyPrefix + "*"));
		} catch (SerializationException e) {
			logger.error("获取key时发生了异常:"+e.getMessage().toString());
			return Collections.emptySet();
		}
		
		if (CollectionUtils.isEmpty(keys)) {
			return Collections.emptySet();
		}
		Set<K> convertedKeys = new HashSet<K>();
		for (byte[] key:keys) {
			try {
				convertedKeys.add((K)keySerializer.deserialize(key));
			} catch (SerializationException e) {
				logger.error("反序列化出现异常");
			}
		}
		return convertedKeys;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Collection<V> values() {
		Set<byte[]> keys = null;
		try {
			keys = iRedisManager.keys(keySerializer.serialize(this.keyPrefix + "*"));
		} catch (SerializationException e) {
			logger.error("获取key时发生了异常:"+e.getMessage().toString());
			return Collections.emptySet();
		}
		if (CollectionUtils.isEmpty(keys)) {
			return Collections.emptySet();
		}
		List<V> values = new ArrayList<V>(keys.size());
		for (byte[] key:keys) {
			V value = null;
			try {
				value = (V)valSerializer.deserialize(iRedisManager.get(key));
			} catch (Exception e) {
				logger.error("反序列化出现异常");
			}
			if (value != null) {
				values.add(value);
			}
		}
		return Collections.unmodifiableList(values);
	}
	
	private Object getRedisCacheKey(K key) {
		if (key == null) {
			return null;
		}
		if (keySerializer instanceof StringSerializer) {
			return this.keyPrefix + getStringRedisKey(key);
		}
		return key;
	}
	
	private String getStringRedisKey(K key) {
		String redisKey;
		if (key instanceof PrincipalCollection) {
			redisKey = getRedisKeyFromPrincipalIdField((PrincipalCollection)key);
		} else {
			redisKey = key.toString();
		}
		return redisKey;
	}

	private String getRedisKeyFromPrincipalIdField(PrincipalCollection key) {
		String redisKey;
		Object principalObject = key.getPrimaryPrincipal();
		Method principalIdGetter = null;
		Method[] methods = principalObject.getClass().getDeclaredMethods();
		
		for (Method m:methods) {
			if (RedisCacheManager.DEFAULT_PRINCIPAL_ID_FIELD_NAME.equals(this.principalIdFieldName)
					&& ("getAuthCacheKey".equals(m.getName())
							|| "getId".equals(m.getName()))) {
				principalIdGetter = m;
				break;
			}
			if (m.getName().equals("get" + this.principalIdFieldName.substring(0,1).toUpperCase()+
					this.principalIdFieldName.substring(1))) {
				principalIdGetter = m;
				break;
			}
		}
		if (principalIdGetter == null) {
			throw new PrincipalInstanceException(principalObject.getClass(), this.principalIdFieldName);
		}
		
		try {
			Object idObj = principalIdGetter.invoke(principalObject);
			if (idObj == null) {
				throw new PrincipalIdNullException(principalObject.getClass(), this.principalIdFieldName);
			}
			redisKey = idObj.toString();
		} catch (IllegalAccessException e) {
			throw new PrincipalInstanceException(principalObject.getClass(), this.principalIdFieldName, e);
		} catch (InvocationTargetException e) {
			throw new PrincipalInstanceException(principalObject.getClass(), this.principalIdFieldName, e);
		}
		return redisKey;
	}

	public String getKeyPrefix() {
		return keyPrefix;
	}
	public void setKeyPrefix(String keyPrefix) {
		this.keyPrefix = keyPrefix;
	}
	public String getPrincipalIdFieldName() {
		return principalIdFieldName;
	}
	public void setPrincipalIdFieldName(String principalIdFieldName) {
		this.principalIdFieldName = principalIdFieldName;
	}
}
