package com.dfdk.yunwei.common.config;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Propertiess{
	
	private static Logger logger = LogManager.getLogger(Propertiess.class);
	private static Properties prop;
	private static final String FILE_URL = "config.properties";
	
	
	static {
		loadProperties();
	}
	
	synchronized static private void loadProperties() {
		InputStream is = null;
		try {
			prop = new Properties();
			is = Propertiess.class.getClassLoader().getResourceAsStream(FILE_URL);
			prop.load(is);
		} catch (FileNotFoundException e) {
			logger.error(FILE_URL+"没有找到!");
		} catch (Exception e) {
			logger.error(e.getMessage().toString());
		} finally {
			if (is != null) {
				try {
					is.close();
				} catch (IOException e) {
					logger.error(e.getMessage().toString());
				}
			}
		}
	}
	
	/**
	 * 获取
	 * @return
	 */
	public static Properties getProperties() {
		return prop;
	}
	
	/**
	 * 将prop转换为map,便于将其转换为对象
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static Map<String,Object> getProp2Map(){
		Map<String,Object> map = new HashMap<String,Object>((Map)prop);
		return map;
	}
	
	public static void main(String[] args) {
		System.out.println(getProp2Map());
	}
	
	
}
