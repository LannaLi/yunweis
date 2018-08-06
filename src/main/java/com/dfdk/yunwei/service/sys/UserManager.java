package com.dfdk.yunwei.service.sys;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.dfdk.yunwei.model.sys.UserModel;
import com.dfdk.yunwei.service.base.BaseService;

public interface UserManager extends BaseService<UserModel>{
	
	public List<Map<String,Object>> queryUsers() throws Exception;
	
	public boolean hasUser(String username) throws Exception;
	
	public UserModel getUserByName(String username)throws Exception;
	
	public int updateUserPwd(UserModel model)throws Exception;
	
	public int updateUserState(UserModel model)throws Exception;
	
	public String getStateByName(String username);
	
	public List<String> queryPermsById(String id);
	
	public Set<Map<String,Object>> queryMenuLists(String id,String type);
	
	public List<Map<String,Object>> queryRolesStatus(UserModel model);
	
	public int update(UserModel model);
	
	public void updateOn(UserModel model);
	
	public String isOnLine(UserModel model);
	
}
