package com.dfdk.yunwei.shiro.cache;

import java.util.Date;

import org.apache.shiro.session.Session;

public class SessionInMemory {
	
	private Session session;
	private Date createTime;
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	
}
