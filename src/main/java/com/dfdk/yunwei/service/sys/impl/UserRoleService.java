package com.dfdk.yunwei.service.sys.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dfdk.yunwei.dao.sys.UserRoleMapper;
import com.dfdk.yunwei.service.sys.UserRoleManager;
@Service
@Transactional(readOnly=false,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
public class UserRoleService implements UserRoleManager{
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	
	@Override
	public int insertObject(String userid, List<String> idList) throws Exception {
		return userRoleMapper.insert(userid, idList);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Map<String, Object>> queryURoleById(String id) {
		List<Map<String, Object>> list = userRoleMapper.queryUserRoleById(id);
		return list;
	}
	
	@Override
	public int updateUR(String userid, List<String> idList) throws Exception {
		if (null == userid || "".equals(userid)) {
			throw new NullPointerException("用户ID不能为空");
		}
		userRoleMapper.delRoleIdByUserId(userid);
		return userRoleMapper.insert(userid, idList);
	}
	
	@Override
	public int deleteRoleIdByUserId(String userid) {
		return userRoleMapper.delRoleIdByUserId(userid);
	}
}
