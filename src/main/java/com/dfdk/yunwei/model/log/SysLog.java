package com.dfdk.yunwei.model.log;

import java.io.Serializable;

/**
 * 系统日志类
 * @Company:www.dfdk.com.cn 
 * @author Lanna 
 * @date 2018年8月3日  
 * @version 1.0
 */
public class SysLog implements Serializable{
   
	private static final long serialVersionUID = -1588278906568269552L;

	private String logid;

    private String type;

    private String title;

    private String remoteaddr;

    private String requesturl;

    private String reqmethod;

    private String params;

    private String except;

    private String operatetime;

    private String timeout;

    private String userid;

    public String getLogid() {
        return logid;
    }

    public void setLogid(String logid) {
        this.logid = logid == null ? null : logid.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public String getRemoteaddr() {
        return remoteaddr;
    }

    public void setRemoteaddr(String remoteaddr) {
        this.remoteaddr = remoteaddr == null ? null : remoteaddr.trim();
    }

    public String getRequesturl() {
        return requesturl;
    }

    public void setRequesturl(String requesturl) {
        this.requesturl = requesturl == null ? null : requesturl.trim();
    }

    public String getReqmethod() {
        return reqmethod;
    }

    public void setReqmethod(String reqmethod) {
        this.reqmethod = reqmethod == null ? null : reqmethod.trim();
    }

    public String getParams() {
        return params;
    }
    
    public void setParams(String params) {
    	this.params = params;
    }

    public String getExcept() {
        return except;
    }

    public void setExcept(String except) {
        this.except = except == null ? null : except.trim();
    }

    public String getOperatetime() {
        return operatetime;
    }

    public void setOperatetime(String operatetime) {
        this.operatetime = operatetime == null ? null : operatetime.trim();
    }

    public String getTimeout() {
        return timeout;
    }

    public void setTimeout(String timeout) {
        this.timeout = timeout == null ? null : timeout.trim();
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

	@Override
	public String toString() {
		return "SysLog [logid=" + logid + ", type=" + type + ", title=" + title + ", remoteaddr=" + remoteaddr
				+ ", requesturl=" + requesturl + ", reqmethod=" + reqmethod + ", params=" + params + ", except="
				+ except + ", operatetime=" + operatetime + ", timeout=" + timeout + ", userid=" + userid + "]";
	}
    
    
}