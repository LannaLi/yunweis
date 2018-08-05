package com.dfdk.yunwei.controller.sys;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dfdk.yunwei.annotion.SysLogControllerLog;
import com.dfdk.yunwei.common.util.Const;
import com.dfdk.yunwei.common.util.Map2Bean;
import com.dfdk.yunwei.common.util.RangeUtil;
import com.dfdk.yunwei.common.web.JsonResult;
import com.dfdk.yunwei.controller.base.BaseController;
import com.dfdk.yunwei.model.sys.RoleModel;
import com.dfdk.yunwei.model.sys.UserModel;
import com.dfdk.yunwei.service.sys.RoleManager;
import com.dfdk.yunwei.shiro.seesion.ShiroSessionManager;
@Controller
@Scope(value="prototype")
@RequestMapping("role/")
public class RoleController extends BaseController{
	
	@Autowired
	private RoleManager roleService;
	
	@SysLogControllerLog(description="添加用户")
	@RequestMapping("addRole")
	@ResponseBody
	public JsonResult addRole() {
		Map<String,Object> object = this.getRequestParam();
		RoleModel model = (RoleModel) Map2Bean.map2JavaBean(object, RoleModel.class);
		UserModel user = ShiroSessionManager.getUser();
		model.setRoleid(this.get32UUID());
		model.setStatus(Const.STATUS_USE);
		model.setRolenumber(RangeUtil.rangeIdent());
		model.setCreateby(user.getUsername());
		model.setUpdateby(user.getUsername());
		try {
			int num = roleService.insertObject(model);
			return new JsonResult(num);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	
	@SysLogControllerLog(description="查找所有用户")
	@RequestMapping("queryRole")
	@ResponseBody
	public JsonResult queryRole() {
		try {
			List<Map<String,Object>>  roleList = roleService.queryRoles();
			for (int i=0;i<roleList.size();i++) {
				Map<String,Object> map = roleList.get(i);
				map.put("parentId","0");
			}
			return new JsonResult(roleList);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	
	@SysLogControllerLog(description="修改角色状态")
	@RequestMapping("updateRoleStatus")
	@ResponseBody
	public JsonResult updateRoleStatus(@RequestParam("roleid")String roleid
			,@RequestParam("types")String types) {
		String status = "";
		if (types.equals(Const.TYPE_RE)) {
			status = Const.STATUS_USE;
		} else if (types.equals(Const.TYPE_F)) {
			status = Const.STATUS_FREEZEUP;
		}
		try {
			int num = roleService.updateRoleStatus(roleid, status);
			return new JsonResult(num);
		} catch (NullPointerException e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	
}
