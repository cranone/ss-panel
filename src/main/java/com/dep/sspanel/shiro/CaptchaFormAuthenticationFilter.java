package com.dep.sspanel.shiro;

import java.text.MessageFormat;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dep.sspanel.entity.SystemLog;
import com.dep.sspanel.service.SystemLogService;
import com.dep.sspanel.util.GlobalConst;

/**
 * 自定义表单过滤器,附带验证码
 * @author Maclaine
 *
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
	private static final Logger logger = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class);
	
	@Resource
	private SystemLogService systemLogService;
	@Resource
	private CacheManager cacheManager;
	
	//表示当访问拒绝时是否已经处理了；如果返回true表示需要继续处理；如果返回false表示该拦截器实例已经处理了，将直接返回即可。
	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		if (request.getAttribute(getFailureKeyAttribute()) != null) {
			return true;
		}
		return super.onAccessDenied(request, response, mappedValue);
	}
	
	//表示是否允许访问；mappedValue就是[urls]配置中拦截器参数部分，如果允许访问返回true，否则false；
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
		//先验证是否为登录请求,否则重复登录相同账号导致无法跳转(已登录状态并且为登录请求,则拒绝访问)
		if(super.getSubject(request, response).isAuthenticated()&&super.isLoginRequest(request, response)){
			return false;
		}
		return super.isAccessAllowed(request, response, mappedValue);
	}
	
	@Override
	protected boolean onLoginSuccess(AuthenticationToken token, Subject subject, ServletRequest request, ServletResponse response) throws Exception {
		logger.debug("{}登录成功",token.getPrincipal());
		systemLogService.save(new SystemLog((HttpServletRequest)request,"login",token.getPrincipal().toString(),"登录成功"));
		return super.onLoginSuccess(token, subject, request, response);
	}
	
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
		Integer remaining=Integer.parseInt(cacheManager.getCache("passwordRetryCache").get(token.getPrincipal().toString()).toString());
		logger.debug("登录失败:{};次数:{}",token.getPrincipal(),e.getMessage(),remaining);
		systemLogService.save(new SystemLog((HttpServletRequest)request,"login",token.getPrincipal().toString(),MessageFormat.format("登录失败:{0};次数:{1}",e.getMessage(),remaining)));
		return super.onLoginFailure(token, e, request, response);
	}
	
}
