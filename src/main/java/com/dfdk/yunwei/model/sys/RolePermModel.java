package com.dfdk.yunwei.model.sys;

import java.io.Serializable;

public class RolePermModel implements Serializable{
	
	private static final long serialVersionUID = 1776132502600267456L;
	private String permid;
	private String roleid;
	public String getPermid() {
		return permid;
	}
	public void setPermid(String permid) {
		this.permid = permid;
	}
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	@Override
	public String toString() {
		return "RolePermModel [permid=" + permid + ", roleid=" + roleid + "]";
	}
}
