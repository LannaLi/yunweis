package com.dfdk.yunwei.dao.tb;

import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface TbMapper {
	
	/**
	 * 查找所有的表名
	 * @return
	 * @author Lanna
	 * @date 2018年8月2日
	 */
	List<String> queryTbName(@Param("database")String database);
	
	int queryCountByTbName(@Param("database")String database,@Param("tbName")String tbName);
	
}
