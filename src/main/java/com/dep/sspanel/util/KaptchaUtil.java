package com.dep.sspanel.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class KaptchaUtil {
	
	public static boolean validate(HttpServletRequest request,String param){
		HttpSession session = request.getSession();
		String captcha=(String) session.getAttribute("KAPTCHA_SESSION_KEY");
		if(param!=null&&captcha!=null&&param.equals(captcha)){
			session.removeAttribute("KAPTCHA_SESSION_KEY");
			return true;
		}
		session.removeAttribute("KAPTCHA_SESSION_KEY");
		return false;
	}
}
