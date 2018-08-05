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
import com.dfdk.yunwei.common.ex.ModifiedException;
import com.dfdk.yunwei.common.util.Const;
import com.dfdk.yunwei.common.util.MD5;
import com.dfdk.yunwei.common.util.Map2Bean;
import com.dfdk.yunwei.common.web.JsonResult;
import com.dfdk.yunwei.controller.base.BaseController;
import com.dfdk.yunwei.model.sys.UserModel;
import com.dfdk.yunwei.service.sys.UserManager;
import com.dfdk.yunwei.shiro.seesion.ShiroSessionManager;
@Controller
@Scope(value="prototype")
@RequestMapping("user/")
public class UserController extends BaseController{
	
	@Autowired
	private UserManager userService;
	
	@SysLogControllerLog(description="用户管理")
	@RequestMapping("userlist")
	@RequiresPermissions("sys:user:view")
	public ModelAndView userList() {
		ModelAndView mv = this.getModelAndView();
		try {
			List<Map<String,Object>> userList = userService.queryUsers();
			mv.addObject("userList", userList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		mv.setViewName("sys/user/user_list");
		return mv;
	}
	
	/**
	 * 添加用户
	 * @return
	 */
	@SysLogControllerLog(description="添加用户")
	@RequestMapping("addUser")
	@ResponseBody
	public JsonResult addUser() {
		Map<String,Object> object = this.getRequestParam();
		//需要添加的用户信息
		UserModel model = (UserModel) Map2Bean.map2JavaBean(object,UserModel.class);
		//当前用户
		UserModel user = ShiroSessionManager.getUser();
		model.setUserid(this.get32UUID());
		model.setPassword(MD5.getOriginPwdMD5String());
		model.setStatus(Const.STATUS_USE);
		model.setCreateby(user.getUsername());
		model.setUpdateby(user.getUsername());
		try {
			int num = userService.insertObject(model);
			return new JsonResult(num);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	
	/**
	 * 验证是否存在重户
	 * @param username
	 * @return
	 */
	@RequestMapping("isHasUser")
	@ResponseBody
	public JsonResult isHasUser(@RequestParam("username")String username) {
		try {
			String result = "true";
			boolean flag = userService.hasUser(username);
			if (!flag) {
				result = "false";
			}
			return new JsonResult(result);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	
	/**
	 * 重置密码
	 * @return
	 */
	@SysLogControllerLog(description="重置密码")
	@RequestMapping("updatePwd")
	@ResponseBody
	public JsonResult updatePwd() {
		Map<String,Object> map = this.getRequestParam();
		UserModel model = (UserModel) Map2Bean.map2JavaBean(map, UserModel.class);
		String resetPwd = MD5.getOriginPwdMD5String();
		model.setPassword(resetPwd);
		try {
			int num = userService.updateUserPwd(model);
			return new JsonResult(num);
		} catch (ModifiedException me) {
			return new JsonResult(me);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
	
	/**
	 * 修改状态
	 * @param userid  用户ID
	 * @param type  类型,根据来判断是恢复,冻结,还是停用
	 * @return
	 */
	@SysLogControllerLog(description="修改用户状态")
	@RequestMapping("updateState")
	@ResponseBody
	public JsonResult updateState(@RequestParam("userid")String userid
			,@RequestParam("type")String type) {
		UserModel model = new UserModel();
		model.setUserid(userid);
		if (type.equals(Const.TYPE_F)) {
			model.setStatus(Const.STATUS_FREEZEUP);
		} else if (type.equals(Const.TYPE_S)) {
			model.setStatus(Const.STATUS_STOP);
		}else if (type.equals(Const.TYPE_RE)) {
			model.setStatus(Const.STATUS_USE);
		}
		try {
			int num = userService.updateUserState(model);
			return new JsonResult(num);
		} catch (ModifiedException me) {
			return new JsonResult(me);
		} catch (Exception e) {
			e.printStackTrace();
			return new JsonResult(e);
		}
	}
}
