package com.dfdk.yunwei.dao.sys;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import com.dfdk.yunwei.dao.base.BaseDao;
import com.dfdk.yunwei.model.sys.UserModel;
@Repository("userMapper")
public interface UserMapper extends BaseDao<UserModel>{
	
	public List<Map<String,Object>> queryObjects() throws Exception;
	
	public int isExit(@Param("username")String username) throws Exception;
	
	public UserModel queryUserByName(@Param("username")String username)throws Exception;
	
	/**
	 * 重置密码
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updatePwd(@Param("model")UserModel model)throws Exception;
	/**
	 * 修改状态
	 * @param model
	 * @return
	 * @throws Exception
	 */
	public int updateState(@Param("model")UserModel model)throws Exception;
	/**
	 * 根据用户名查找用户状态
	 * @param username
	 * @return
	 * @throws Exception
	 */
	public String queryStateByName(@Param("username")String username);
	/**
	 * 根据用户ID查找相对应的权限
	 * @param id
	 * @return
	 */
	public List<String> queryPermById(@Param("userid")String id);
	/**
	 * 根据ID和类型查找当前用户可以看到的菜单
	 * @param id
	 * @param type
	 * @return
	 */
	public Set<Map<String,Object>> queryMenuList(@Param("userid")String id,@Param("types")String type);
	
	/**
	 * 根据用户查找对应的角色
	 * @param model
	 * @return
	 */
	public List<Map<String,Object>> queryRoleStatus(@Param("model")UserModel model);
	
	/**
	 * 修改用户信息
	 * @param model
	 * @return
	 * @author Lanna
	 * @date 2018年8月6日
	 */
	public int updateUser(@Param("model")UserModel model);
	
	/**
	 * 修改在线状态
	 * @param model
	 * @return
	 * @author Lanna
	 * @date 2018年8月6日
	 */
	public int updateOnLine(@Param("model")UserModel model);
	/**
	 * 是否已经在线(单点登录)
	 * @param model
	 * @return
	 * @author Lanna
	 * @date 2018年8月6日
	 */
	public String isOnLine(@Param("model")UserModel model);
	
}
