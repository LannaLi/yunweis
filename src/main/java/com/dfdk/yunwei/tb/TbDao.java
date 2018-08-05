package com.dfdk.yunwei.tb;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dfdk.yunwei.model.tb.TbModel;

@Repository
public class TbDao implements TbManager{
	
	private static Logger logger = LogManager.getLogger(TbDao.class);
	
	private static final String TN1_PREFIX = "sys";
	private static final String TN2_PREFIX = "yw";
	private JdbcTemplate jdbcTemplate;
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
		this.jdbcTemplate = jdbcTemplate;
	}
	
	@Override
	public int createTb(String tableName,TbModel model,List<TbModel> tbList) {
		logger.info("==================create table begin====================");
		String tb_prefix = tableName.split("_")[0];
		if ((!tb_prefix.equals(TN1_PREFIX)) && (!tb_prefix.equals(TN2_PREFIX))) {
			logger.error("表名前缀不正确");
			throw new RuntimeException("表名前缀不正确");
		}
		StringBuffer sb = new StringBuffer();
		sb.append("create table " + tableName + "(");
		sb.append(model.getName() +" " + model.getColums() +",");
		for (int i=0;i<tbList.size();i++) {
			sb.append(tbList.get(i).getName() 
					+" " 
					+ tbList.get(i).getColums() 
					+",");
		}
		sb.append("PRIMARY KEY(" + model.getName() + ")");
		sb.append(")charset=utf8");
		logger.info("==================create table end====================");
		return jdbcTemplate.update(sb.toString());
	}
	
}
