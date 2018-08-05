package com.dfdk.yunwei.model.base;

public class BaseModel<T> extends BaseSerialization{
	
	private static final long serialVersionUID = 806904528918574248L;
	private String createtime;
	private String createby;
	private String updatetime;
	private String updateby;
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
	
	
}
