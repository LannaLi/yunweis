package com.dfdk.yunwei.controller.base;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import com.dfdk.yunwei.common.util.RequestParam;
import com.dfdk.yunwei.common.util.UuidUtil;

public class BaseController {
	
	public Logger logger = LogManager.getLogger(this.getClass());
	
	/**
	 * 获得ModelAndView的实例
	 * @return
	 */
	public ModelAndView getModelAndView() {
		return new ModelAndView();
	}
	
	public Map<String,Object> getRequestParam() {
		Map<String,Object> map = RequestParam.getParamterMap(this.getRequest());
		return map;
	}
	
	/**
	 * 获取请求对象
	 * @return
	 */
	public HttpServletRequest getRequest() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder
				.getRequestAttributes()).getRequest();
		return request;
	}
	
	/**
	 * 获取32位的随机数用于ID
	 * @return
	 */
	public String get32UUID() {
		return UuidUtil.get32UUID();
	}
	
	/**
	 * 方法执行前
	 * @param logger
	 * @param interfaceName
	 */
	public void before(Logger logger,String interfaceName) {
		logger.info("===========================================");
		logger.info("START");
		logger.info(interfaceName);
	}
	
	/**
	 * 方法执行后
	 * @param logger
	 */
	public void end(Logger logger) {
		logger.info("===========================================");
		logger.info("END");
	}
}
