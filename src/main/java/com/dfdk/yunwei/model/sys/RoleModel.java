package com.dfdk.yunwei.model.sys;

import java.io.Serializable;

public class RoleModel implements Serializable{
	
	private static final long serialVersionUID = 7896154648922923299L;
	private String roleid;
	private String rolename;
	private String rolenumber;
	private String status;
	private String remark;
	private String createtime;
	private String createby;
	private String updatetime;
	private String updateby;
	public String getRoleid() {
		return roleid;
	}
	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}
	public String getRolename() {
		return rolename;
	}
	public void setRolename(String rolename) {
		this.rolename = rolename;
	}
	public String getRolenumber() {
		return rolenumber;
	}
	public void setRolenumber(String rolenumber) {
		this.rolenumber = rolenumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getCreatetime() {
		return createtime;
	}
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}
	public String getCreateby() {
		return createby;
	}
	public void setCreateby(String createby) {
		this.createby = createby;
	}
	public String getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(String updatetime) {
		this.updatetime = updatetime;
	}
	public String getUpdateby() {
		return updateby;
	}
	public void setUpdateby(String updateby) {
		this.updateby = updateby;
	}
	@Override
	public String toString() {
		return "RoleModel [roleid=" + roleid + ", rolename=" + rolename + ", rolenumber=" + rolenumber + ", status="
				+ status + ", remark=" + remark + ", createtime=" + createtime + ", createby=" + createby
				+ ", updatetime=" + updatetime + ", updateby=" + updateby + "]";
	}
	
	
}
