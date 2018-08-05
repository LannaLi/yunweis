package com.dfdk.yunwei.dao.base;

public interface BaseDao<T> {
	
	//插入数据
	public int insert(T model) throws Exception;
	//更新数据
	public void update(T model) throws Exception;
	//根据ID删除数据
	public void delete(String id) throws Exception;
	//根据ID查找
	public T query(String id) throws Exception;
	
	
}
