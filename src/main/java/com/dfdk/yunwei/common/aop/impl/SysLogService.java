package com.dfdk.yunwei.common.aop.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfdk.yunwei.common.aop.SysLogManager;
import com.dfdk.yunwei.dao.log.SysLogMapper;
import com.dfdk.yunwei.model.log.SysLog;
@Service
public class SysLogService implements SysLogManager{
	private static Logger logger = LogManager.getLogger(SysLogService.class);
	@Autowired
	private SysLogMapper sysLogMapper;
	
	@Override
	public void insert(SysLog model) {
		int num = sysLogMapper.insert(model);
		if (num != 1) {
			logger.error("添加系统日志出现异常");
			throw new RuntimeException("添加系统日志出现异常");
		}
	}

	@Override
	public void update(SysLog model) {
		int num = sysLogMapper.updateByPrimaryKey(model);
		if (num !=1) {
			logger.error("更新系统日志出现异常");
			throw new RuntimeException("更新系统日志出现异常");
		}
	}

}
