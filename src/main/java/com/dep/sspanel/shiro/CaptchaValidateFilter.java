package com.dep.sspanel.shiro;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.web.filter.AccessControlFilter;
import org.apache.shiro.web.util.WebUtils;

import com.dep.sspanel.util.KaptchaUtil;

public class CaptchaValidateFilter extends AccessControlFilter {
	private boolean captchaEbabled = true;// 是否开启验证码支持
	private String captchaParam = "captchaCode";// 前台提交的验证码参数名
	private String failureKeyAttribute = "shiroLoginFailure"; // 验证失败后存储到的属性名

	public void setCaptchaEbabled(boolean captchaEbabled) {
		this.captchaEbabled = captchaEbabled;
	}
	public void setCaptchaParam(String captchaParam) {
		this.captchaParam = captchaParam;
	}
	public void setFailureKeyAttribute(String failureKeyAttribute) {
		this.failureKeyAttribute = failureKeyAttribute;
	}
	
	@Override
	protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) throws Exception {
		request.setAttribute("captchaEbabled", captchaEbabled);  
		HttpServletRequest httpServletRequest = WebUtils.toHttp(request);  
		if (captchaEbabled == false || !"post".equalsIgnoreCase(httpServletRequest.getMethod())) {  
            return true;  
        }  
		return KaptchaUtil.validate(httpServletRequest, httpServletRequest.getParameter(captchaParam));
	}

	@Override
	protected boolean onAccessDenied(ServletRequest request, ServletResponse response) throws Exception {
		//如果验证码失败了，存储失败key属性  
        request.setAttribute(failureKeyAttribute, "captcha.error");  
        return true;
	}

}
