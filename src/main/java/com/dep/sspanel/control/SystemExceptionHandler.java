package com.dep.sspanel.control;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import com.dep.sspanel.exception.SystemException;
import com.dep.sspanel.util.ErrorCode;
import com.dep.sspanel.util.ServerUtil;
import com.dep.sspanel.util.type.ErrorCodeType;



/**
 * 全局捕获
 * @author Maclaine
 *
 */
@Controller
public class SystemExceptionHandler implements HandlerExceptionResolver {
	private static final Logger logger = LoggerFactory.getLogger(SystemExceptionHandler.class);
			
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
		//判断是否为Ajax请求
		msg=ServerUtil.i18n(request,msg);
		if(request.getHeader("accept").indexOf("application/json")>=0){
			logger.debug("ajax");
			return new ModelAndView("forward:/error/errorajax?status="+status+"&info="+msg);
		}
		return new ModelAndView("forward:/error/error?code="+status+"&msg="+msg);
	}
	
	@RequestMapping(value={URIConstants.ERROR_INDEX,URIConstants.ERROR_DEFAULT})
	public String error(){
		return "error/error";
	}

	@ResponseBody
	@RequestMapping(URIConstants.ERROR_AJAX)
	public Map<String,Object> errorAjax(@RequestParam(required = false,defaultValue=ErrorCode.MSG_UNKNOWN_ERROR)String info
			,@RequestParam(required = false,defaultValue=ErrorCode.CODE_UNKNOWN_ERROR) String status){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("status", status);
		map.put("info", info);
		return map;
	}
}
