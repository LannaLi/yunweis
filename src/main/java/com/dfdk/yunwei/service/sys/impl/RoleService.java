package com.dfdk.yunwei.service.sys.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dfdk.yunwei.common.ex.QueryException;
import com.dfdk.yunwei.common.util.Pagination;
import com.dfdk.yunwei.dao.sys.RoleMapper;
import com.dfdk.yunwei.model.sys.RoleModel;
import com.dfdk.yunwei.service.sys.RoleManager;
@Service
@Transactional(readOnly=false,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
public class RoleService implements RoleManager{
	
	@Autowired
	private RoleMapper roleMapper;
	
	@Transactional(readOnly=true)
	@Override
	public RoleModel queryObject(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int insertObject(RoleModel model) throws Exception {
		return roleMapper.insert(model);
	}
	
	@Override
	public void updateObject(RoleModel model) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteObject(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Map<String, Object>> queryRoles() throws Exception {
		return roleMapper.queryObjects();
	}
	
	@Override
	public int updateRoleStatus(String roleid, String status) {
		if (null == roleid || roleid.length() == 0 ||"".equals(roleid)) {
			throw new NullPointerException("操作有误,请联系管理员!");
		}
		return roleMapper.updateRoleStatusById(roleid, status);
	}
	
	/**
	 * 分页查询
	 */
	@Transactional(readOnly=true)
	@Override
	public Map<String, Object> queryRO(RoleModel model, Pagination pagination) {
		Map<String, Object> map = new HashMap<String, Object>();
		int n = roleMapper.queryRowCount(model);
		if (n == 0) {
			throw new QueryException("没有找到相应的记录!");
		}
		pagination.setRowCount(n);
		List<Map<String, Object>> roleList = roleMapper.queryPageObject(model, pagination);
		map.put("roleList", roleList);
		map.put("pagination", pagination);
		return map;
	}

}
