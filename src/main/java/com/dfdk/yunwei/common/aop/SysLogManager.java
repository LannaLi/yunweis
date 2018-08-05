package com.dfdk.yunwei.common.aop;

import com.dfdk.yunwei.model.log.SysLog;

public interface SysLogManager {
	
	void insert(SysLog model);
	
	void update(SysLog model);
	
}
