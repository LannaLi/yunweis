package com.dfdk.yunwei.service.base;

public interface BaseService<T> {
	
	public T queryObject(String id) throws Exception;
	public int insertObject(T model) throws Exception;
	public void updateObject(T model) throws Exception;
	public void deleteObject(String id) throws Exception;
}
