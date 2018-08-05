package com.dfdk.yunwei.dao.sys;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dfdk.yunwei.dao.base.BaseDao;
import com.dfdk.yunwei.model.sys.RoleModel;
@Repository("roleMapper")
public interface RoleMapper extends BaseDao<RoleModel>{
	
	public List<Map<String,Object>> queryObjects() throws Exception;
	
	public int updateRoleStatusById(@Param("roleid")String roleid,@Param("status")String status);
}
