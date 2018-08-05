package com.dfdk.yunwei.model.sys;

import java.io.Serializable;

public class SysMenuModel implements Serializable{
	
	private static final long serialVersionUID = -4765840353452179565L;
	private String permid;
	private String permname;
	private String permurl;
	private String parentid;
	private String percode;
	private String icon;
	private String types;
	private String status;
	private String sort;
	private String remark;
	private String createtime;
	private String createby;
	private String updatetime;
	private String updateby;
	public SysMenuModel() {
		super();
	}
	public SysMenuModel(String permid, String permname, String permurl, String parentid, String percode, String icon,
			String types, String status, String sort, String remark, String createtime, String createby, String updatetime,
			String updateby) {
		super();
		this.permid = permid;
		this.permname = permname;
		this.permurl = permurl;
		this.parentid = parentid;
		this.percode = percode;
		this.icon = icon;
		this.types = types;
		this.status = status;
		this.sort = sort;
		this.remark = remark;
		this.createtime = createtime;
		this.createby = createby;
		this.updatetime = updatetime;
		this.updateby = updateby;
	}
	public String getPermid() {
		return permid;
	}
	public void setPermid(String permid) {
		this.permid = permid;
	}
	public String getPermname() {
		return permname;
	}
	public void setPermname(String permname) {
		this.permname = permname;
	}
	public String getPermurl() {
		return permurl;
	}
	public void setPermurl(String permurl) {
		this.permurl = permurl;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getPercode() {
		return percode;
	}
	public void setPercode(String percode) {
		this.percode = percode;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getTypes() {
		return types;
	}
	public void setTypes(String types) {
		this.types = types;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSort() {
		return sort;
	}
	public void setSort(String sort) {
		this.sort = sort;
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
		return "SysMenuModel [permid=" + permid + ", permname=" + permname + ", permurl=" + permurl + ", parentid="
				+ parentid + ", percode=" + percode + ", icon=" + icon + ", types=" + types + ", status=" + status
				+ ", sort=" + sort + ", remark=" + remark + ", createtime=" + createtime + ", createby=" + createby
				+ ", updatetime=" + updatetime + ", updateby=" + updateby + "]";
	}
}
