package com.dfdk.yunwei.service.sys.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dfdk.yunwei.common.ex.ModifiedException;
import com.dfdk.yunwei.dao.sys.UserMapper;
import com.dfdk.yunwei.model.sys.UserModel;
import com.dfdk.yunwei.service.sys.UserManager;
@Transactional(readOnly=false)
@Service
public class UserService implements UserManager{
	
	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public UserModel queryObject(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public int insertObject(UserModel model) throws Exception {
		return userMapper.insert(model);
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public void updateObject(UserModel model) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public void deleteObject(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public List<Map<String, Object>> queryUsers() throws Exception {
		return userMapper.queryObjects();
	}
	
	@Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public boolean hasUser(String username) throws Exception {
		int count = userMapper.isExit(username);
		if (count > 0) {
			return true;
		}
		return false;
	}
	
	@Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public UserModel getUserByName(String username) throws Exception {
		return userMapper.queryUserByName(username);
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public int updateUserPwd(UserModel model) throws Exception {
		int num = userMapper.updatePwd(model);
		if (num < 0) {
			throw new ModifiedException("服务器繁忙,请稍后再试!");
		}
		return num;
	}
	
	@Transactional(isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public int updateUserState(UserModel model) throws Exception {
		int num = userMapper.updateState(model);
		if (num < 0) {
			throw new ModifiedException("服务器繁忙,请稍后再试!");
		}
		return num;
	}
	
	@Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public String getStateByName(String username){
		return userMapper.queryStateByName(username);
	}
	
	@Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public List<String> queryPermsById(String id) {
		return userMapper.queryPermById(id);
	}
	
	@Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public Set<Map<String, Object>> queryMenuLists(String id, String type) {
		Set<Map<String, Object>> setList = new HashSet<Map<String, Object>>();
		setList = userMapper.queryMenuList(id, type);
		if (null == setList || setList.size() == 0) {
			throw new NullPointerException("该用户还没具有任何的角色,请联系管理员!");
		}
		return setList;
	}
	
	@Transactional(readOnly=true,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
	@Override
	public List<Map<String, Object>> queryRolesStatus(UserModel model) {
		return userMapper.queryRoleStatus(model);
	}
	
}
