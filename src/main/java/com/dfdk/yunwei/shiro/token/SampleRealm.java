package com.dfdk.yunwei.shiro.token;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

import com.dfdk.yunwei.common.util.Const;
import com.dfdk.yunwei.model.sys.UserModel;
import com.dfdk.yunwei.service.sys.UserManager;
import com.dfdk.yunwei.shiro.seesion.ShiroSessionManager;

public class SampleRealm extends AuthorizingRealm{
	
	
	@Autowired
	private UserManager userService;
	
	
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
		try {
			UsernamePasswordToken tokens = (UsernamePasswordToken) token;
			String username = tokens.getUsername();
			UserModel user = userService.getUserByName(username);
			ByteSource salt = ByteSource.Util.bytes(Const.SALT_VALUE);
			SimpleAuthenticationInfo info = new SimpleAuthenticationInfo(
					user,user.getPassword(),salt,getName());
			return info;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
		//当前用户
		UserModel user = ShiroSessionManager.getUser();
		//该用户具有的资源标识号
		List<String> permList = new ArrayList<String>();
		permList = userService.queryPermsById(user.getUserid());
		Set<String> permSet = new HashSet<String>();
		for (String perm:permList) {
			if (perm != null && !"".equals(perm)) {
				permSet.add(perm);
			}
		}
		SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
		info.setStringPermissions(permSet);
		return info;
	}
}
