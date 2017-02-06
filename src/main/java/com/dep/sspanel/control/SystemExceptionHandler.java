package com.dep.sspanel.control;

import java.util.Arrays;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.dep.sspanel.entity.SystemLog;
import com.dep.sspanel.exception.SystemException;
import com.dep.sspanel.service.SystemLogService;
import com.dep.sspanel.shiro.ShiroUtil;
import com.dep.sspanel.util.ServerUtil;
import com.dep.sspanel.util.type.ErrorCodeType;



/**
 * 全局捕获
 * @author Maclaine
 *
 */
@ControllerAdvice
public class SystemExceptionHandler implements HandlerExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);
	
	@Resource
	private SystemLogService systemLogService;
	
	@ExceptionHandler
	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		logger.error("GlobalException has catched:{}",ex.getMessage());
		logger.debug("Catch Error:{}",request.getHeader("accept"));
		String status=null,msg=null;
		//TODO: 异常信息国际化,尚未测试
		if(ex instanceof DataAccessException){//GenericDao
			logger.error("DataAccessException:{}",ErrorCodeType.data_error.toString(),ex.getMessage());
			msg=ErrorCodeType.data_error.getMsg();
			status=ErrorCodeType.data_error.getCode();
		}else if(ex instanceof NoHandlerFoundException){
			logger.error("NoHandlerFoundException:{}",ErrorCodeType.no_handler_found.toString(),ex.getMessage());
			msg=ErrorCodeType.no_handler_found.getMsg();
			status=ErrorCodeType.no_handler_found.getCode();
		}else if(ex instanceof SystemException){
			logger.error("SystemException:{}",((SystemException) ex).getErrorCodeType().toString());
			msg=((SystemException) ex).getErrorCodeType().getMsg();
			status=((SystemException) ex).getErrorCodeType().getCode();
		}else{
			logger.error("Exception:{}",ErrorCodeType.unknown_error.toString(),ex.getMessage());
			ex.printStackTrace();
			msg=ErrorCodeType.unknown_error.getMsg();
			status=ErrorCodeType.unknown_error.getCode();
		}
		
		//日志记录
		try {
			//Object user =SecurityUtils.getSubject().getPrincipal();user==null?"anonymous":user.toString()
			systemLogService.save(new SystemLog((HttpServletRequest)request,"error",ShiroUtil.getUserName(),ex.getMessage(),Arrays.toString(ex.getStackTrace())));
		} catch (Exception e) {
			logger.error(e.toString());
		}
		
		
		//判断是否为Ajax请求
		msg=ServerUtil.i18n(request,msg);
		if(request.getHeader("accept").indexOf("application/json")>=0){
			logger.debug("ajax");
			return new ModelAndView("forward:"+URIConstants.ERROR_AJAX+"?status="+status+"&info="+msg);
		}
		return new ModelAndView("forward:"+URIConstants.ERROR_DEFAULT+"?code="+status+"&msg="+msg);
	}
	
	
}
