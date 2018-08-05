package com.dfdk.yunwei.model.sys;

import java.io.Serializable;

public class UserRoleModel implements Serializable{
	
	private static final long serialVersionUID = 3103451876616392922L;
	private String userroleid;
	private String userid;
	private String roleid;
	public String getUserroleid() {
		return userroleid;
	}
	public void setUserroleid(String userroleid) {
		this.userroleid = userroleid;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	@Override
	public String toString() {
		return "UserRoleModel [userroleid=" + userroleid + ", userid=" + userid + ", roleid=" + roleid + "]";
	}
	
}
