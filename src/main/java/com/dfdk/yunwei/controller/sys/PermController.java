package com.dfdk.yunwei.controller.sys;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dfdk.yunwei.annotion.SysLogControllerLog;
import com.dfdk.yunwei.common.util.StringUtil;
import com.dfdk.yunwei.common.web.JsonResult;
import com.dfdk.yunwei.controller.base.BaseController;
import com.dfdk.yunwei.service.sys.RoleManager;
import com.dfdk.yunwei.service.sys.UserRoleManager;
@Controller
@Scope(value="prototype")
@RequestMapping("perm/")
public class PermController extends BaseController{
	
	@Autowired
	private RoleManager roleService;
	@Autowired
	private UserRoleManager userRoleService;
	
	@SysLogControllerLog(description="权限管理")
	@RequestMapping("permlist")
	@RequiresPermissions("sys:perm:view")
	public ModelAndView permList() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("sys/permission/perm_list");
		try {
			List<Map<String,Object>>  roleList = roleService.queryRoles();
			mv.addObject("roleList", roleList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mv;
	}
	
	@SysLogControllerLog(description="添加用户")
	@RequestMapping("addUserRole")
	@ResponseBody
	public JsonResult addUserRole(@RequestParam("userid")String userid
			,@RequestParam(value="roleIdList[]")String[] roleIdList) {
		List<String> idList = StringUtil.string2List(roleIdList);
		try {
			int num = userRoleService.insertObject(userid, idList);
			return new JsonResult(num);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	
	@SysLogControllerLog(description="查找用户对应的角色")
	@RequestMapping("queryUR")
	@ResponseBody
	public JsonResult queryURById(@RequestParam("userid")String userid) {
		List<Map<String,Object>> urList = userRoleService.queryURoleById(userid);
		return new JsonResult(urList);
	}
	
	@SysLogControllerLog(description="更新用户角色")
	@RequestMapping("updateUserRole")
	@ResponseBody
	public JsonResult updateUserRole(@RequestParam("userid")String userid
			,@RequestParam(value="roleIdList[]")String[] roleIdList) {
		try {
			List<String> idList = StringUtil.string2List(roleIdList);
			int num = userRoleService.updateUR(userid, idList);
			return new JsonResult(num);
		} catch (NullPointerException ne) {
			ne.printStackTrace();
			return new JsonResult(ne);
		}catch (Exception e) {
			e.printStackTrace();
			return new JsonResult();
		}
	}
	
	/**
	 * 删除用户所有的角色
	 * @param userid
	 * @return
	 */
	@SysLogControllerLog(description="删除用户所有角色")
	@RequestMapping("delUserRole")
	@ResponseBody
	public JsonResult delRole(@RequestParam("userid")String userid) {
		int num = userRoleService.deleteRoleIdByUserId(userid);
		return new JsonResult(num);
	}
	
	
	
	
}
