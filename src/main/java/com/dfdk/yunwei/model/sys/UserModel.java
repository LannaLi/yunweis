package com.dfdk.yunwei.model.sys;

import java.io.Serializable;

public class UserModel implements Serializable{
	
	
	private static final long serialVersionUID = -686825645756270886L;
	private String userid;
	private String username;
	private String password;
	private String realname;
	private String phone;
	private String email;
	private String weichat;
	private String qqcode;
	private String sex;
	private String birthdate;
	private String deptid;
	private String onlines;
	private String onduty;
	private String belarea;
	private String status;
	private String createtime;
	private String createby;
	private String updatetime;
	private String updateby;
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getWeichat() {
		return weichat;
	}
	public void setWeichat(String weichat) {
		this.weichat = weichat;
	}
	public String getQqcode() {
		return qqcode;
	}
	public void setQqcode(String qqcode) {
		this.qqcode = qqcode;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthdate() {
		return birthdate;
	}
	public void setBirthdate(String birthdate) {
		this.birthdate = birthdate;
	}
	public String getDeptid() {
		return deptid;
	}
	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	public String getOnlines() {
		return onlines;
	}
	public void setOnlines(String onlines) {
		this.onlines = onlines;
	}
	public String getOnduty() {
		return onduty;
	}
	public void setOnduty(String onduty) {
		this.onduty = onduty;
	}
	public String getBelarea() {
		return belarea;
	}
	public void setBelarea(String belarea) {
		this.belarea = belarea;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
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
		return "UserModel [userid=" + userid + ", username=" + username + ", password=" + password + ", realname="
				+ realname + ", phone=" + phone + ", email=" + email + ", weichat=" + weichat + ", qqcode=" + qqcode
				+ ", sex=" + sex + ", birthdate=" + birthdate + ", deptid=" + deptid + ", onlines=" + onlines
				+ ", onduty=" + onduty + ", belarea=" + belarea + ", status=" + status + ", createtime=" + createtime
				+ ", createby=" + createby + ", updatetime=" + updatetime + ", updateby=" + updateby + "]";
	}
}
