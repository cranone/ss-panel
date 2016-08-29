package com.dep.sspanel.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;

/**
 * 自定义表单过滤器,附带验证码
 * @author Maclaine
 *
 */
public class CaptchaFormAuthenticationFilter extends FormAuthenticationFilter {
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
}
