package com.dfdk.yunwei.dao.base;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.dfdk.yunwei.common.util.Pagination;

public interface BaseDao<T> {
	
	//插入数据
	public int insert(T model) throws Exception;
	//更新数据
	public void update(T model) throws Exception;
	//根据ID删除数据
	public void delete(String id) throws Exception;
	//根据ID查找
	public T query(String id) throws Exception;
	//分页查询
	public List<Map<String,Object>> queryPageObject(@Param("model")T model,@Param("pagination")Pagination pagination);
	//查询所有记录
	public int queryRowCount(@Param("model")T model);
}
