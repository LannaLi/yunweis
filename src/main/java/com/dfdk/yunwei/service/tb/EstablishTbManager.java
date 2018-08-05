package com.dfdk.yunwei.service.tb;

import java.util.List;

import com.dfdk.yunwei.model.tb.TbModel;

public interface EstablishTbManager {
	
	/**
	 * 创建表
	 * @param paramMap
	 * @return
	 * @author Lanna
	 * @date 2018年8月1日
	 */
	int createTb(String tableName,TbModel model,List<TbModel> tbList);
	
}
