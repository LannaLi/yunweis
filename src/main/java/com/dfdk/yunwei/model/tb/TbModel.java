package com.dfdk.yunwei.model.tb;

/**
 * <p>用户来存放创建数据表的字段,类型,描述</p>
 * @Company:www.dfdk.com.cn 
 * @author Lanna 
 * @date 2018年8月2日  
 * @version 1.0
 */
public class TbModel {
	
	//字段名
	private String name;
	//字段类型{如:int varchar datetime.....}
	private String colums;
	//描述
	private String comment;
	private String key;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getColums() {
		return colums;
	}
	public void setColums(String colums) {
		this.colums = colums;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	@Override
	public String toString() {
		return "TbModel [name=" + name + ", colums=" + colums + ", comment=" + comment + ", key=" + key + "]";
	}
	
}
