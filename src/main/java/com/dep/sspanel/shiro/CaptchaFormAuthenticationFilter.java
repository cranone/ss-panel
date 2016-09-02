package com.dep.sspanel.shiro;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dep.sspanel.service.SystemLogService;

/**
 * 自定义表单过滤器,附带验证码
 * @author Maclaine
 *
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
	private static final Logger logger = LoggerFactory.getLogger(CaptchaFormAuthenticationFilter.class);
	
	@Resource
	private SystemLogService systemLogService;
	
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
		return super.onLoginSuccess(token, subject, request, response);
	}
	
	@Override
	protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest request, ServletResponse response) {
		logger.debug("{}登录失败:{}",token.getPrincipal(),e.getMessage());
		return super.onLoginFailure(token, e, request, response);
	}
	
}
