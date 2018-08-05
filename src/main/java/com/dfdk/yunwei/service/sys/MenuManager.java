package com.dfdk.yunwei.service.sys;

import java.util.List;
import java.util.Map;

import com.dfdk.yunwei.model.sys.SysMenuModel;
import com.dfdk.yunwei.service.base.BaseService;

public interface MenuManager extends BaseService<SysMenuModel>{
	
	public List<SysMenuModel> queryMenu();
	
	public List<Map<String,Object>> queryTreeMenu();
	
	public List<Map<String,Object>> queryTreeTableMenu() throws Exception;
	
}
