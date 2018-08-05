package com.dfdk.yunwei.dao.sys;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

import com.dfdk.yunwei.dao.base.BaseDao;
import com.dfdk.yunwei.model.sys.SysMenuModel;
@Repository(value="menuMapper")
public interface MenuMapper extends BaseDao<SysMenuModel>{
	
	public List<SysMenuModel> queryObjects();
	
	public List<Map<String,Object>> queryTreeData();
	
	public List<Map<String,Object>> queryMenu() throws Exception;
	
}
