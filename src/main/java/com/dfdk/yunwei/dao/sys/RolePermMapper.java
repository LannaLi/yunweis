package com.dfdk.yunwei.dao.sys;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository("rolePermMapper")
public interface RolePermMapper {
	
	public int insert(@Param("roleid")String roleId
			,@Param("idList")List<String> idList) throws Exception;
	
	/**
	 * 根据角色ID获取对应的标识符号
	 * @param id
	 * @param type
	 * @return
	 */
	public Set<Map<String,Object>> queryMenuListByRoleId(
			@Param("roleid")String id
			,@Param("types")String type);
	
	public List<Map<String,Object>> queryRolePermById(@Param("roleid")String id);
	/**
	 * 根据角色ID删除相对应的资源
	 * @param roleid
	 */
	public int deleteRolePermByRoleid(@Param("roleid")String roleid);
}
