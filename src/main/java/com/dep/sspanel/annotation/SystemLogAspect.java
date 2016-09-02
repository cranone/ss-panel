package com.dep.sspanel.annotation;

import java.lang.reflect.Method;

import javax.annotation.Resource;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dep.sspanel.service.SystemLogService;

@Aspect
@Component
public class SystemLogAspect {
	@Resource
	private SystemLogService systemLogService;
	private static final Logger logger = LoggerFactory.getLogger(SystemLogAspect.class);
	
	// Service层切点
	@Pointcut("@annotation(com.dep.sspanel.annotation.SystemServiceLog)")
	public void serviceAspect() {}

	// Controller层切点
	@Pointcut("@annotation(com.dep.sspanel.annotation.SystemControllerLog)")
	public void controllerAspect() {}

	/**
	 * 前置通知 用于拦截Controller层记录用户的操作
	 * 
	 * @param joinPoint
	 */
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint) {
		try {
			String str = getControllerMethodDescription(joinPoint);
			Object[] args = joinPoint.getArgs();
			System.out.println(args.toString());
			logger.debug(str);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 异常通知 用于拦截service层记录异常日志
	 * 
	 * @param joinPoint
	 * @param e
	 */
	@AfterThrowing(pointcut = "serviceAspect()", throwing = "e")
	public void doAfterThrowing(JoinPoint joinPoint, Throwable e) {
		try {
			String str = getServiceMthodDescription(joinPoint);
			logger.debug(str);
		} catch (Exception ex) {
			// TODO Auto-generated catch block
			ex.printStackTrace();
		}
		logger.debug("============={}",e.getMessage());
	}

	/**
	 * 获取注解中对方法的描述信息 用于Controller层注解
	 * 
	 * @param joinPoint
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getControllerMethodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemControllerLog.class).description();
					break;
				}
			}
		}
		return description;
	}

	/**
	 * 获取注解中对方法的描述信息 用于service层注解
	 * 
	 * @param joinPoint
	 * @return 方法描述
	 * @throws Exception
	 */
	public static String getServiceMthodDescription(JoinPoint joinPoint) throws Exception {
		String targetName = joinPoint.getTarget().getClass().getName();
		String methodName = joinPoint.getSignature().getName();
		Object[] arguments = joinPoint.getArgs();
		Class targetClass = Class.forName(targetName);
		Method[] methods = targetClass.getMethods();
		String description = "";
		for (Method method : methods) {
			if (method.getName().equals(methodName)) {
				Class[] clazzs = method.getParameterTypes();
				if (clazzs.length == arguments.length) {
					description = method.getAnnotation(SystemServiceLog.class).description();
					break;
				}
			}
		}
		return description;
	}
}
