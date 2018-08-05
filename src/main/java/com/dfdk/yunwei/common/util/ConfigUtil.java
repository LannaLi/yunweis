package com.dfdk.yunwei.common.util;

import com.dfdk.yunwei.common.config.Propertiess;
import com.dfdk.yunwei.common.config.model.Configuration;

public class ConfigUtil {
	
	private static Configuration config = (Configuration) Map2Bean.map2JavaBean(
			Propertiess.getProp2Map()
			,Configuration.class);
	
	/**
	 * 获取系统名称
	 * @return
	 */
	public static String getSysName() {
		return config.getTitle();
	}
	/**
	 * 获取公司名称
	 * @return
	 */
	public static String getCompanyName() {
		return config.getCompany();
	}
}
