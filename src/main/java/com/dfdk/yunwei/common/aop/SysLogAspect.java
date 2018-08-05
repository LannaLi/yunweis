package com.dfdk.yunwei.common.aop;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.NamedThreadLocal;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.stereotype.Component;

import com.dfdk.yunwei.annotion.SysLogControllerLog;
import com.dfdk.yunwei.common.util.DateUtil;
import com.dfdk.yunwei.common.util.UuidUtil;
import com.dfdk.yunwei.model.log.SysLog;
import com.dfdk.yunwei.model.sys.UserModel;
import com.dfdk.yunwei.shiro.seesion.ShiroSessionManager;

/**
 * 系统日志切面
 * @Company:www.dfdk.com.cn 
 * @author Lanna 
 * @date 2018年8月3日  
 * @version 1.0
 */
@Aspect
@Component
public class SysLogAspect {
	
	//时间线程
	private final ThreadLocal<Date> startTimeThread = new NamedThreadLocal<Date>("ThreadLocal beginTime");
	//日志操作线程
	private final ThreadLocal<SysLog> sysLogThread = new NamedThreadLocal<SysLog>("ThreadLocal syslog");
	//用户线程
	private final ThreadLocal<UserModel> userThread = new NamedThreadLocal<UserModel>("ThreadLocal user");
	
	@Autowired
	private ThreadPoolTaskExecutor threadPool;
	
	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	private SysLogManager sysLogService;
	
	@Pointcut("execution(* com.dfdk.yunwei.controller.*.*.*(..))")
	public void controllerPoitcut() {}
	
	@Before(value="controllerPoitcut()")
	public void doBefore(JoinPoint joinPoint) {
		Date date = new Date();
		startTimeThread.set(date);
		UserModel model = ShiroSessionManager.getUser();
		userThread.set(model);
	}
	
	@SuppressWarnings("unchecked")
	@After("controllerPoitcut()")
	public void doAfter(JoinPoint joinPoint) {
		//获取用户
		UserModel user = userThread.get();
		if (user == null) {
			user = ShiroSessionManager.getUser();
			if (user == null) {
				return;
			}
		}
		
		//获取相关的日志信息
		SysLog sysLog = new SysLog();
		sysLog.setReqmethod(request.getMethod());
		sysLog.setRequesturl(request.getRequestURI());
		sysLog.setRemoteaddr(request.getRemoteAddr());
		sysLog.setLogid(UuidUtil.get32UUID());
		String title = "";
		try {
			title = getControllerMethodDes(joinPoint);
			sysLog.setTitle(title);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//开始时间
		String sartTime = DateUtil.date2String(startTimeThread.get());
		sysLog.setOperatetime(sartTime);
		//结束时间
		Date date = new Date();
		sysLog.setTimeout(DateUtil.date2String(date));
		//获取参数
		StringBuffer sb = new StringBuffer();
		Map<String,String[]> params = request.getParameterMap();
		for (Map.Entry<String,String[]> entry:params.entrySet()) {
			String key = entry.getKey();
			sb.append(key+"=");
			String[] values = entry.getValue();
			for (int i=0;i<values.length;i++) {
				sb.append(values[i]);
			}
			sb.append("&");
		}
		
		sysLog.setParams(sb.toString());
		sysLog.setType("info");
		sysLog.setUserid(user.getUserid());
		
		//交给异步线程来执行
		threadPool.execute(new addSysLogThread(sysLog,sysLogService));
		sysLogThread.set(sysLog);
	}
	
	@AfterThrowing(pointcut="controllerPoitcut()",throwing = "e")
	public void doAfterThrowable(JoinPoint joinPoint, Throwable e) {
		SysLog sysLog = sysLogThread.get();
		if (sysLog != null) {
			sysLog.setType("error");
			sysLog.setExcept(e.getMessage());
			//更新
			new modifySysLogThread(sysLog,sysLogService).start();
		}
	}
	
	/**
	 * 获取方法上注解的描述内容
	 * @param joinPoint
	 * @return
	 * @author Lanna
	 * @date 2018年8月3日
	 */
	public static String getControllerMethodDes(JoinPoint joinPoint) {
		//获取签署名
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		//获取织入的方法
		Method method = signature.getMethod();
		//获取方法上的注解
		SysLogControllerLog sysLogControllerLog = method.getAnnotation(SysLogControllerLog.class);
		return sysLogControllerLog.description();
	}
	
	/**
	 * 内部类,用来添加日志
	 * @Company:www.dfdk.com.cn 
	 * @author Lanna 
	 * @date 2018年8月4日  
	 * @version 1.0
	 */
	private class addSysLogThread implements Runnable{
		private SysLog sysLog;
		private SysLogManager sysLogService;
		
		public addSysLogThread(SysLog sysLog,SysLogManager sysLogService) {
			this.sysLog = sysLog;
			this.sysLogService = sysLogService;
		}
		
		@Override
		public void run() {
			sysLogService.insert(sysLog);
		}
	}
	
	/**
	 * 出现异常的时候就更新日志中的type和添加异常信息
	 * @Company:www.dfdk.com.cn 
	 * @author Lanna 
	 * @date 2018年8月4日  
	 * @version 1.0
	 */
	private class modifySysLogThread extends Thread{
		
		private SysLog sysLog;
		private SysLogManager sysLogService;
		
		public modifySysLogThread(SysLog sysLog,SysLogManager sysLogService) {
			super(modifySysLogThread.class.getSimpleName());
			this.sysLog = sysLog;
			this.sysLogService = sysLogService;
		}

		@Override
		public void run() {
			sysLogService.update(sysLog);
		}
	}
}
