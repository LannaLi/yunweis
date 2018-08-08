package com.dfdk.yunwei.controller.login;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dfdk.yunwei.annotion.SysLogControllerLog;
import com.dfdk.yunwei.common.ex.ModifiedException;
import com.dfdk.yunwei.common.util.Const;
import com.dfdk.yunwei.common.util.Map2Bean;
import com.dfdk.yunwei.common.web.JsonResult;
import com.dfdk.yunwei.controller.base.BaseController;
import com.dfdk.yunwei.model.sys.UserModel;
import com.dfdk.yunwei.service.sys.UserManager;
import com.dfdk.yunwei.shiro.seesion.ShiroSessionManager;
import com.dfdk.yunwei.shiro.token.manager.ShiroTokenManager;

@Controller
public class LoginController extends BaseController{
	
	@Autowired
	private UserManager userService;
	
	@RequestMapping("login")
	public ModelAndView login() {
		this.before(logger, "login()");
		ModelAndView mv = this.getModelAndView();
		mv.setViewName("login/login");
		this.end(logger);
		return mv;
	}
	
	@SysLogControllerLog(description="登录系统")
	@RequestMapping("signIn")
	@ResponseBody
	public JsonResult signIn() {
		
		Map<String,Object> map = this.getRequestParam();
		UserModel model = (UserModel) Map2Bean.map2JavaBean(map,UserModel.class);
		String state = userService.getStateByName(model.getUsername());
		if (state.equals(Const.STATUS_USE)) {  //判断该用户的状态,非可用状态,则给出通知
			Subject currentUser = ShiroSessionManager.getCurrentSubject();
			if (!currentUser.isAuthenticated()) {
				try {
					ShiroTokenManager.login(currentUser,model);
					return new JsonResult("success");
				} catch (IncorrectCredentialsException e) {
					e = new IncorrectCredentialsException("PWDERROR");
					return new JsonResult(e);
				} catch (AuthenticationException e) {
					e = new AuthenticationException("SERVERERROR");
					return new JsonResult(e);
				} catch (ModifiedException e) {
					return new JsonResult(e);
				}
			}
		} else {
			return new JsonResult(state);
		}
		return null;
	}
	
	@RequestMapping("logout")
	public void signOut(HttpServletResponse response) {
		UserModel user = ShiroSessionManager.getUser();
		user.setOnlines(Const.OFFLINE_STATUS);
		userService.updateOn(user);
		try {
			response.sendRedirect("login.do");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
