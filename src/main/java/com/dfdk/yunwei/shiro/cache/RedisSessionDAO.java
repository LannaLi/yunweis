package com.dfdk.yunwei.shiro.cache;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.apache.logging.log4j.LogManager;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;
import org.slf4j.Logger;

import com.dfdk.yunwei.shiro.cache.ex.SerializationException;
import com.dfdk.yunwei.shiro.cache.serializer.ObjectSerializer;
import com.dfdk.yunwei.shiro.cache.serializer.RedisSerializer;
import com.dfdk.yunwei.shiro.cache.serializer.StringSerializer;

public class RedisSessionDAO extends AbstractSessionDAO{
	
	private static Logger logger = (Logger) LogManager.getLogger(RedisSessionDAO.class);
	
	private static final String DEFAULT_SESSION_KEY_PREFIX = "shiro:session";
	private String keyPrefix = DEFAULT_SESSION_KEY_PREFIX;
	//过期时间
	private static final long DEFAULT_SESSION_IN_MEMORY_TIMEOUT = 1000L;
	private long sessionInMemoryTinmeout = DEFAULT_SESSION_IN_MEMORY_TIMEOUT;
	//失效时间
	private static final int DEFAULT_EXPIRE = -2;
	private int expire = DEFAULT_EXPIRE;
	
	private static final int NO_EXPIRE = -1;
	
	private static final int MILLISECONDS_IN_A_SECOND = 1000;
	
	private IRedisManager redisManager;
	private RedisSerializer<String> keySerializer = new StringSerializer();
	private RedisSerializer<Object> valSerializer = new ObjectSerializer();
	//定义一个线程
	@SuppressWarnings("rawtypes")
	private static ThreadLocal sessionInThread = new ThreadLocal();
	
	/**
	 * 更新
	 */
	@Override
	public void update(Session session) throws UnknownSessionException {
		this.saveSession(session);
	}
	
	/**
	 * 删除
	 */
	@Override
	public void delete(Session session) {
		if (session == null || session.getId() == null) {
			logger.error("session 或者sessionId 不能为空");
		}
		
		try {
			redisManager.del(keySerializer.serialize(getRedisSessionKey(session.getId())));
		} catch (Exception e) {
			logger.error("删除会话缓存时发生了异常:"+e.getMessage());
		}
	}
	
	/**
	 * 获取所有的session
	 */
	@Override
	public Collection<Session> getActiveSessions() {
		Set<Session> sessionSet = new HashSet<Session>();
		try {
			Set<byte[]> keys = redisManager.keys(this.keySerializer.serialize(this.keyPrefix + "*"));
			if (keys != null && keys.size() > 0) {
				for (byte[] key:keys) {
					Session session = (Session) valSerializer.deserialize(redisManager.get(key));
					sessionSet.add(session);
				}
			}
		} catch (SerializationException e) {
			logger.error("获取session出现异常");
		}
		return sessionSet;
	}
	
	/**
	 * 再一次创建
	 */
	@Override
	protected Serializable doCreate(Session session) {
		if (session == null) {
			logger.error("session不能为空");
			throw new UnknownSessionException("session不能为空");
		}
		Serializable sessionId = this.generateSessionId(session);
		this.assignSessionId(session, sessionId);
		this.saveSession(session);
		return sessionId;
	}
	
	/**
	 * 重读
	 */
	@Override
	protected Session doReadSession(Serializable sessionId) {
		if (sessionId == null) {
			logger.warn("sessionId不能为空");
			return null;
		}
		
		Session session = getSessionFromThreadLocal(sessionId);
		if (session != null) {
			return session;
		}
		
		logger.debug("从redis中读取session");
		try {
			session = (Session)valSerializer.deserialize(redisManager.get(keySerializer.serialize(getRedisSessionKey(sessionId))));
			setSessionToThreadLocal(sessionId,session);
		} catch (SerializationException e) {
			logger.error("从redis中读取session出现异常.sessionId:"+sessionId);
		}
		return session;
	}
	
	/**
	 * 设置线程会话
	 * @param sessionId
	 * @param session
	 * @author Lanna
	 * @date 2018年7月31日
	 */
	@SuppressWarnings("unchecked")
	private void setSessionToThreadLocal(Serializable sessionId, Session session) {
		Map<Serializable,SessionInMemory> sessionMap = (Map<Serializable,SessionInMemory>)sessionInThread.get();
		if (sessionMap == null) {
			sessionMap = new HashMap<Serializable,SessionInMemory>();
			sessionInThread.set(sessionMap);
		}
		SessionInMemory sessionInMemory = new SessionInMemory();
		sessionInMemory.setCreateTime(new Date());
		sessionInMemory.setSession(session);
		sessionMap.put(sessionId,sessionInMemory);
	}

	/**
	 * 从线程中获取sessionID
	 * @param sessionId
	 * @return
	 * @author Lanna
	 * @date 2018年7月31日
	 */
	@SuppressWarnings("unchecked")
	private Session getSessionFromThreadLocal(Serializable sessionId) {
		Session session = null;
		if (sessionId == null) {
			return null;
		}
		
		if (sessionInThread.get() == null) {
			return null;
		}
		
		Map<Serializable,SessionInMemory> sessionMap = (Map<Serializable, SessionInMemory>) sessionInThread.get();
		SessionInMemory sessionInMemory = sessionMap.get(sessionId);
		if (sessionInMemory == null) {
			return null;
		}
		Date now = new Date();
		long duration = now.getTime() - sessionInMemory.getCreateTime().getTime();
		if (duration < sessionInMemoryTinmeout) {
			session = sessionInMemory.getSession();
			logger.debug("从redis上读取session");
		} else {
			sessionMap.remove(sessionId);
		}
		return session;
	}

	/**
	 * 保存session
	 * @param session
	 * @author Lanna
	 * @date 2018年7月31日
	 */
	private void saveSession(Session session) {
		if (session == null || session.getId() == null) {
			logger.error("session 或者sessionId 不能为空");
			throw new UnknownSessionException("session 或者sessionId 不能为空");
		}
		
		byte[] key,val;
		try {
			key = keySerializer.serialize(getRedisSessionKey(session.getId()));
			val = valSerializer.serialize(session);
		} catch (SerializationException e) {
			logger.error("会话序列化出现异常  %$% sessionId:" + session.getId());
			throw new UnknownSessionException(e);
		}
		//保存
		if (expire == DEFAULT_EXPIRE) {
			this.redisManager.set(key, val, (int)(session.getTimeout() /MILLISECONDS_IN_A_SECOND));
			return;
		}
		if (expire != NO_EXPIRE && expire * MILLISECONDS_IN_A_SECOND < session.getTimeout()) {
			logger.warn("Redis session expire time: "
					+ (expire * MILLISECONDS_IN_A_SECOND)
					+ " is less than Session timeout: "
					+ session.getTimeout()
					+ " . It may cause some problems.");
		}
		this.redisManager.set(key, val, expire);
		
	}
	
	/**
	 * 根据会话ID获取key
	 * @param id
	 * @return
	 * @author Lanna
	 * @date 2018年7月31日
	 */
	private String getRedisSessionKey(Serializable id) {
		return this.keyPrefix + id;
	}
	
	public IRedisManager getRedisManager() {
		return redisManager;
	}

	public void setRedisManager(IRedisManager iRedisManager) {
		this.redisManager = iRedisManager;
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

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setKeySerializer(RedisSerializer keySerializer) {
		this.keySerializer = keySerializer;
	}

	@SuppressWarnings("rawtypes")
	public RedisSerializer getValueSerializer() {
		return valSerializer;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setValueSerializer(RedisSerializer valueSerializer) {
		this.valSerializer = valueSerializer;
	}

	public long getSessionInMemoryTimeout() {
		return sessionInMemoryTinmeout;
	}

	public void setSessionInMemoryTimeout(long sessionInMemoryTimeout) {
		this.sessionInMemoryTinmeout = sessionInMemoryTimeout;
	}

	public int getExpire() {
		return expire;
	}

	public void setExpire(int expire) {
		this.expire = expire;
	}
}
