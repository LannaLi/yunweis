package com.dfdk.yunwei.service.tb.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dfdk.yunwei.dao.tb.TbMapper;
import com.dfdk.yunwei.model.tb.TbModel;
import com.dfdk.yunwei.service.tb.EstablishTbManager;
import com.dfdk.yunwei.tb.TbDao;
@Service
public class EstablishTbService implements EstablishTbManager{
	private static final String DATABASE_NAME = "filemanager";
	@Autowired
	private TbDao tbDao;
	@Autowired
	private TbMapper tbMapper;
	@Override
	public synchronized int createTb(String tableName,TbModel model,List<TbModel> tbList) {
		int n = tbMapper.queryCountByTbName(DATABASE_NAME, tableName);
		if (n==1) {
			throw new RuntimeException("表已经存在!");
		}
		return tbDao.createTb(tableName, model, tbList);
	}
	
}
