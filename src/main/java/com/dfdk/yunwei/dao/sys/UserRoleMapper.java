package com.dfdk.yunwei.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;
@Repository("userRoleMapper")
public interface UserRoleMapper {
	
	public int insert(@Param("userid")String userid,@Param("idList")List<String>idList)throws Exception;
	
	public List<Map<String,Object>> queryUserRoleById(@Param("userid")String userid);
	
	public int delRoleIdByUserId(@Param("userid")String userid);
	
}
