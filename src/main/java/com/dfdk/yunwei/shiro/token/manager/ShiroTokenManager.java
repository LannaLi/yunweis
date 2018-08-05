package com.dfdk.yunwei.shiro.token.manager;

import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

import com.dfdk.yunwei.model.sys.UserModel;

public class ShiroTokenManager {
	/**
	 * 登陆
	 * @param model
	 * @return
	 */
	public static void login(Subject subject,UserModel model) {
		UsernamePasswordToken token = new UsernamePasswordToken(model.getUsername(),model.getPassword());
		subject.login(token);
	}
}
