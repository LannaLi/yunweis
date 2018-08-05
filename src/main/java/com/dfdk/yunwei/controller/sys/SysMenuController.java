package com.dfdk.yunwei.controller.sys;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dfdk.yunwei.annotion.SysLogControllerLog;
import com.dfdk.yunwei.common.util.Const;
import com.dfdk.yunwei.common.util.Map2Bean;
import com.dfdk.yunwei.common.web.JsonResult;
import com.dfdk.yunwei.controller.base.BaseController;
import com.dfdk.yunwei.model.sys.SysMenuModel;
import com.dfdk.yunwei.model.sys.UserModel;
import com.dfdk.yunwei.service.sys.MenuManager;
import com.dfdk.yunwei.shiro.seesion.ShiroSessionManager;
@Controller
@Scope(value="prototype")
@RequestMapping("menu/")
public class SysMenuController extends BaseController{
	@Autowired
	private MenuManager menuService;
	
	@SysLogControllerLog(description="菜单管理")
	@RequestMapping("menulist")
	public ModelAndView menuList() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("sys/menu/menu_list");
		return mv;
	}
	
	/**
	 * 用于形成树形
	 * @return
	 */
	@SysLogControllerLog(description="加载当前用户对应的菜单")
	@RequestMapping("treeData")
	@ResponseBody
	public List<Map<String,Object>> treeData(){
		return menuService.queryTreeMenu();
	}
	
	/**
	 * 添加菜单
	 * @param request
	 * @return
	 */
	@SysLogControllerLog(description="添加菜单")
	@RequestMapping("addMenu")
	@ResponseBody
	public int addMenu() {
		Map<String, Object> map = this.getRequestParam();
		SysMenuModel model = (SysMenuModel) Map2Bean.map2JavaBean(map, SysMenuModel.class);
		UserModel user = ShiroSessionManager.getUser();
		model.setPermid(this.get32UUID());
		model.setCreateby(user.getUsername());
		model.setUpdateby(user.getUsername());
		model.setStatus(Const.STATUS_USE);
		try {
			return menuService.insertObject(model);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	/**
	 * 查看所有的菜单及子菜单
	 * @return
	 */
	@SysLogControllerLog(description="查看所有的菜单及子菜单")
	@RequestMapping("getMenus")
	@RequiresPermissions("sys:menu:view")
	@ResponseBody
	public JsonResult getMenus() {
		try {
			List<Map<String,Object>> list = menuService.queryTreeTableMenu();
			return new JsonResult(list);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	
	
	
}
