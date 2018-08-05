package test;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.dfdk.yunwei.dao.sys.RolePermMapper;
import com.dfdk.yunwei.dao.sys.UserMapper;
import com.dfdk.yunwei.dao.sys.UserRoleMapper;
import com.dfdk.yunwei.dao.tb.TbMapper;
import com.dfdk.yunwei.model.sys.UserModel;

public class TestSQL {
	
	private ClassPathXmlApplicationContext cxt;
	
	@Before
	public void init() {
		cxt = new ClassPathXmlApplicationContext("ApplicationContext-main.xml","ApplicationContext-dataSource.xml");
	}
	
	@Test
	public void testQuery() {
		UserRoleMapper userRoleMapper = (UserRoleMapper) cxt.getBean("userRoleMapper");
		String id = "26fa5520d48342af983a7fde86aff524";
		List<Map<String,Object>> list=userRoleMapper.queryUserRoleById(id);
		System.out.println(list);
	}
	
	@Test
	public void testSql() throws SQLException {
		DataSource dataSource = (DataSource) cxt.getBean("dataSource");
		Connection conn = dataSource.getConnection();
		System.out.println(conn);
	}
	
	@Test
	public void testQueryRole() {
		UserMapper userMapper = (UserMapper) cxt.getBean("userMapper");
		UserModel model = new UserModel();
		model.setUserid("ce74b3b8a28045f286c31ec74706d160");
		List<Map<String,Object>> list = userMapper.queryRoleStatus(model);
		System.out.println(list);
	}
	@Test
	public void testQueryRolePerm() {
		RolePermMapper RolePermMapper = (RolePermMapper) cxt.getBean("rolePermMapper");
		List<Map<String, Object>> set = 
				RolePermMapper.queryRolePermById("b5dc816b98b64f2993aa4d27d6204c63");
		System.out.println(set);
	}
	@Test
	public void testQueryTbName() {
		TbMapper tbMapper = (TbMapper) cxt.getBean("tbMapper");
		List<String> list = tbMapper.queryTbName("filemanager");
		System.out.println(list);
		int n = tbMapper.queryCountByTbName("filemanager", "yw_pact");
		System.out.println(n);
	}
}
