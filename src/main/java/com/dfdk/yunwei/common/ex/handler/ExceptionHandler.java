package com.dfdk.yunwei.common.ex.handler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

public class ExceptionHandler implements HandlerExceptionResolver{
	
	private Logger logger = LogManager.getLogger(this.getClass());
	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		logger.info("==========================================异常开始======================================");
		ex.printStackTrace();
		logger.info("==========================================异常结束======================================");
		ModelAndView mv = new ModelAndView();
		mv.setViewName("error");
		mv.addObject("ex",ex.toString().replaceAll("\n", "<br/>"));
		return mv;
	}
	
	
}
