package com.dfdk.yunwei.service.sys;

import java.util.List;
import java.util.Map;

public interface UserRoleManager {
	
	
	public int insertObject(String userid,List<String> idList) throws Exception;
	
	public List<Map<String,Object>> queryURoleById(String id);
	
	public int updateUR(String userid,List<String> idList)throws Exception;
	
	public int deleteRoleIdByUserId(String userid);
}
