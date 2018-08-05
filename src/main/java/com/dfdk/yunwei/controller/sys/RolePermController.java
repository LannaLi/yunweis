package com.dfdk.yunwei.controller.sys;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfdk.yunwei.annotion.SysLogControllerLog;
import com.dfdk.yunwei.common.web.JsonResult;
import com.dfdk.yunwei.controller.base.BaseController;
import com.dfdk.yunwei.service.sys.RolePermManager;
@Controller
@Scope(value="prototype")
public class RolePermController extends BaseController{
	
	@Autowired
	private RolePermManager rolePermService;
	
	/**
	 * 为角色添加对应的资源
	 * @param roleid
	 * @param menuIdList
	 * @return
	 */
	@SysLogControllerLog(description="添加用户权限")
	@RequestMapping("addRolePerm")
	@ResponseBody
	public JsonResult addRolePerm(@RequestParam(value="roleid")String roleid
			,@RequestParam(value="menuIdList[]")String[] menuIdList) {
		List<String> idList = new ArrayList<String>();
		for (int i=0;i<menuIdList.length;i++) {
			idList.add(menuIdList[i]);
		}
		
		try {
			int num = rolePermService.insert(roleid, idList);
			return new JsonResult(num);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	
	/**
	 * 根据角色ID查找对应的资源
	 * @param roleid
	 * @return
	 */
	@SysLogControllerLog(description="查找用户对应的权限")
	@RequestMapping("queryPermByRoleId")
	@ResponseBody
	public JsonResult queryPermByRoleId(@RequestParam("roleid")String roleid) {
		List<Map<String,Object>> permList = rolePermService.queryRolePermsById(roleid);
		return new JsonResult(permList);
	}
	
	/**
	 * 修改角色对应的资源
	 * @param roleid
	 * @param menuIdList
	 * @return
	 */
	@SysLogControllerLog(description="修改角色权限")
	@RequestMapping("updateRolePerm")
	@ResponseBody
	public JsonResult updateRolePerm(@RequestParam(value="roleid")String roleid
			,@RequestParam(value="menuIdList[]")String[] menuIdList) {
		List<String> idList = new ArrayList<String>();
		for (int i=0;i<menuIdList.length;i++) {
			idList.add(menuIdList[i]);
		}
		try {
			int num = rolePermService.updateRolePermsByRoleid(roleid, idList);
			return new JsonResult(num);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	
	@SysLogControllerLog(description="删除所有用户权限")
	@RequestMapping("delRolePermsByRoleid")
	@ResponseBody
	public JsonResult delRolePermsByRoleid(@RequestParam(value="roleid")String roleid) {
		int num = rolePermService.deleteRolePermsByRoleid(roleid);
		return new JsonResult(num);
	}
	
}
