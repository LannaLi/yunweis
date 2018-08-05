package com.dfdk.yunwei.tb;

import java.util.List;

import com.dfdk.yunwei.model.tb.TbModel;

public interface TbManager {
	
	int createTb(String tableName,TbModel model,List<TbModel> tbList);
}
