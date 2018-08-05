package com.dfdk.yunwei.service.sys;

import java.util.List;
import java.util.Map;

import com.dfdk.yunwei.model.sys.RoleModel;
import com.dfdk.yunwei.service.base.BaseService;

public interface RoleManager extends BaseService<RoleModel>{
	
	public List<Map<String,Object>> queryRoles() throws Exception;
	
	public int updateRoleStatus(String roleid,String status);
}
