package com.dfdk.yunwei.shiro.seesion;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

import com.dfdk.yunwei.model.sys.UserModel;

public class ShiroSessionManager {
	
	/**
	 * 获取当前主体
	 * @return
	 */
	public static Subject getCurrentSubject() {
		return SecurityUtils.getSubject();
	}
	
	/**
	 * 获取当前会话
	 * @return
	 */
	public static Session getSession() {
		return getCurrentSubject().getSession();
	}
	
	/**
	 * 设置当前会话
	 * @param key
	 * @param value
	 */
	public static void setSession(Object key,Object value) {
		getSession().setAttribute(key, value);
	}
	
	/**
	 * 根据key获取对应的值
	 * @param key
	 * @return
	 */
	public static Object getVal2Session(Object key) {
		return getSession().getAttribute(key);
	}
	
	/**
	 * 获取当前用户
	 * @return
	 */
	public static UserModel getUser() {
		return (UserModel) getCurrentSubject().getPrincipal();
	}
	
	/**
	 * 获取当前用户名
	 * @return
	 * @author Lanna
	 * @date 2018年7月30日
	 */
	public static String getUserName() {
		return getUser().getUsername();
	}
	
	/**
	 * 获取当前用户ID
	 * @return
	 * @author Lanna
	 * @date 2018年7月30日
	 */
	public static String getUerId() {
		return getUser().getUserid();
	}
}







