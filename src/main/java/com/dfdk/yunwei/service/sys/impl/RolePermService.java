package com.dfdk.yunwei.service.sys.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dfdk.yunwei.dao.sys.RolePermMapper;
import com.dfdk.yunwei.service.sys.RolePermManager;

@Service
@Transactional(readOnly=false)
public class RolePermService implements RolePermManager{
	
	@Autowired
	private RolePermMapper rolePermMapper;
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public int insert(String roleId, List<String> idList) throws Exception {
		return rolePermMapper.insert(roleId, idList);
	}
	
	@Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public Set<Map<String, Object>> queryMenuByRoleId(String id, String type) {
		return rolePermMapper.queryMenuListByRoleId(id, type);
	}
	
	@Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public List<Map<String, Object>> queryRolePermsById(String id) {
		return rolePermMapper.queryRolePermById(id);
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public int deleteRolePermsByRoleid(String roleid) {
		return rolePermMapper.deleteRolePermByRoleid(roleid);
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public int updateRolePermsByRoleid(String roleid,List<String> idList) throws Exception {
		if ("".equals(roleid) || roleid.length() == 0) {
			throw new NullPointerException("角色ID不能为空!");
		}
		rolePermMapper.deleteRolePermByRoleid(roleid);
		return rolePermMapper.insert(roleid, idList);
	}

}
