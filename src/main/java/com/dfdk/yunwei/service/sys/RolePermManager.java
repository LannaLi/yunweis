package com.dfdk.yunwei.service.sys;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RolePermManager {
	
	public int insert(String roleId,List<String> idList) throws Exception;
	
	public Set<Map<String,Object>> queryMenuByRoleId(String id,String type);
	
	public List<Map<String,Object>> queryRolePermsById(String id);
	
	public int deleteRolePermsByRoleid(String roleid);
	
	public int updateRolePermsByRoleid(String roleid,List<String> idList)throws Exception;
}
