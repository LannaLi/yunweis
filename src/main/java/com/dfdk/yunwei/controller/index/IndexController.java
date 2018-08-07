package com.dfdk.yunwei.controller.index;

import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dfdk.yunwei.annotion.SysLogControllerLog;
import com.dfdk.yunwei.common.util.Const;
import com.dfdk.yunwei.common.web.JsonResult;
import com.dfdk.yunwei.controller.base.BaseController;
import com.dfdk.yunwei.model.sys.UserModel;
import com.dfdk.yunwei.service.sys.UserManager;
import com.dfdk.yunwei.shiro.seesion.ShiroSessionManager;
@Controller
public class IndexController extends BaseController{
	@Autowired
	private UserManager userService;
	
	@SysLogControllerLog(description="加载首页")
	@RequestMapping("index")
	public ModelAndView index() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("sys/index/index");
		mv.addObject("username",ShiroSessionManager.getUserName());
		return mv;
	}
	
	@SysLogControllerLog(description="加载用户角色对应的菜单")
	@RequestMapping("permMenuLists")
	@ResponseBody
	public JsonResult getPermMenus() {
		UserModel user = ShiroSessionManager.getUser();
		try {
			Set<Map<String,Object>> menuMap = userService.queryMenuLists(user.getUserid(),Const.TYPE_MENU);
			return new JsonResult(menuMap);
		} catch (NullPointerException e) {
			return new JsonResult(e);
		}
	}
	
	@SysLogControllerLog(description="加载修改个人页面")
	@RequestMapping("editUser")
	public ModelAndView userEdit() {
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("sys/user/user_edit");
		mv.addObject("user", ShiroSessionManager.getUser());
		return mv;
	}
}
