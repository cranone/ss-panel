package com.dep.sspanel.shiro;

import org.apache.shiro.SecurityUtils;
import org.springframework.util.StringUtils;

public class ShiroUtil {
	
	/**
	 * 获取当前用户名
	 * @return 未登录返回anonymous
	 */
	public static String getUserName(){
		Object user =SecurityUtils.getSubject().getPrincipal();
		if(user==null||StringUtils.isEmpty(user.toString())){
			return "anonymous";
		}
		return user.toString();
	}
}
