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
import com.dfdk.yunwei.common.util.MD5;
import com.dfdk.yunwei.dao.sys.UserMapper;
import com.dfdk.yunwei.model.sys.UserModel;
import com.dfdk.yunwei.service.sys.UserManager;
@Transactional(readOnly=false,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
@Service
public class UserService implements UserManager{
	
	@Autowired
	private UserMapper userMapper;
	
	@Transactional(readOnly=true)
	@Override
	public UserModel queryObject(String id) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int insertObject(UserModel model) throws Exception {
		return userMapper.insert(model);
	}
	
	@Override
	public void updateObject(UserModel model) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteObject(String id) throws Exception {
		// TODO Auto-generated method stub
		
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Map<String, Object>> queryUsers() throws Exception {
		return userMapper.queryObjects();
	}
	
	@Transactional(readOnly=true)
	@Override
	public boolean hasUser(String username) throws Exception {
		int count = userMapper.isExit(username);
		if (count > 0) {
			return true;
		}
		return false;
	}
	
	@Transactional(readOnly=true)
	@Override
	public UserModel getUserByName(String username) throws Exception {
		return userMapper.queryUserByName(username);
	}
	
	@Override
	public int updateUserPwd(UserModel model) throws Exception {
		int num = userMapper.updatePwd(model);
		if (num < 0) {
			throw new ModifiedException("服务器繁忙,请稍后再试!");
		}
		return num;
	}
	
	@Override
	public int updateUserState(UserModel model) throws Exception {
		int num = userMapper.updateState(model);
		if (num < 0) {
			throw new ModifiedException("服务器繁忙,请稍后再试!");
		}
		return num;
	}
	
	@Transactional(readOnly=true)
	@Override
	public String getStateByName(String username){
		return userMapper.queryStateByName(username);
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<String> queryPermsById(String id) {
		return userMapper.queryPermById(id);
	}
	
	@Transactional(readOnly=true)
	@Override
	public Set<Map<String, Object>> queryMenuLists(String id, String type) {
		Set<Map<String, Object>> setList = new HashSet<Map<String, Object>>();
		setList = userMapper.queryMenuList(id, type);
		if (null == setList || setList.size() == 0) {
			throw new NullPointerException("该用户还没具有任何的角色,请联系管理员!");
		}
		return setList;
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Map<String, Object>> queryRolesStatus(UserModel model) {
		return userMapper.queryRoleStatus(model);
	}
	
	@Override
	public int update(UserModel model) {
		//先判断传过来的密码是否跟原来的一样
		String pwd = userMapper.queryPwdById(model.getUserid());
		if (model.getPassword().equals(pwd)) {
			model.setPassword(pwd);
		}
		//如果不一样就将明文进行加密
		String md5Pwd = MD5.getMD5String(model.getPassword());
		model.setPassword(md5Pwd);
		int num = userMapper.updateUser(model);
		if (num != 1) {
			throw new ModifiedException("修改用户信息失败!");
		}
		return num;
	}
	
	@Override
	public void updateOn(UserModel model) {
		int num = userMapper.updateOnLine(model);
		if (num != 1) {
			throw new ModifiedException("更改在线状态失败");
		}
	}
	
	@Transactional(readOnly=true)
	@Override
	public String isOnLine(UserModel model) {
		return userMapper.isOnLine(model);
	}
	
}
