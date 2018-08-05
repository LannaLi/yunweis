package com.dfdk.yunwei.service.sys.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dfdk.yunwei.dao.sys.RoleMapper;
import com.dfdk.yunwei.model.sys.RoleModel;
import com.dfdk.yunwei.service.sys.RoleManager;
@Service
@Transactional(readOnly=false)
public class RoleService implements RoleManager{
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public RoleModel queryObject(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public int insertObject(RoleModel model) throws Exception {
		return roleMapper.insert(model);
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public void updateObject(RoleModel model) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public void deleteObject(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public List<Map<String, Object>> queryRoles() throws Exception {
		return roleMapper.queryObjects();
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public int updateRoleStatus(String roleid, String status) {
		if (null == roleid || roleid.length() == 0 ||"".equals(roleid)) {
			throw new NullPointerException("操作有误,请联系管理员!");
		}
		return roleMapper.updateRoleStatusById(roleid, status);
	}

}
