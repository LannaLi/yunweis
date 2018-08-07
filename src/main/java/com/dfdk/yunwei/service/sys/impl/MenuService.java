package com.dfdk.yunwei.service.sys.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.dfdk.yunwei.common.ex.SaveException;
import com.dfdk.yunwei.dao.sys.MenuMapper;
import com.dfdk.yunwei.model.sys.SysMenuModel;
import com.dfdk.yunwei.service.sys.MenuManager;
@Service
@Transactional(readOnly=false,isolation=Isolation.REPEATABLE_READ,propagation=Propagation.REQUIRED)
public class MenuService implements MenuManager{
	@Autowired
	private MenuMapper menuMapper;
	
	@Transactional(readOnly=true)
	@Override
	public SysMenuModel queryObject(String id) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public int insertObject(SysMenuModel model) throws Exception {
		if (model == null) {
			throw new SaveException("用户不能为空");
		}
		return menuMapper.insert(model);
	}
	
	@Override
	public void updateObject(SysMenuModel model) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void deleteObject(String id) {
		// TODO Auto-generated method stub
		
	}
	@Transactional(readOnly=true)
	@Override
	public List<SysMenuModel> queryMenu() {
		return menuMapper.queryObjects();
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Map<String,Object>> queryTreeMenu() {
		return menuMapper.queryTreeData();
	}
	
	@Transactional(readOnly=true)
	@Override
	public List<Map<String, Object>> queryTreeTableMenu() throws Exception {
		return menuMapper.queryMenu();
	}

}
