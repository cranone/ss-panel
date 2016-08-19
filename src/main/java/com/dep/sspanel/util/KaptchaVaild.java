package com.dep.sspanel.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class KaptchaVaild {

	public static boolean validateResponse(HttpServletRequest request,String param){
		HttpSession session = request.getSession();
		String captcha=(String) session.getAttribute("KAPTCHA_SESSION_KEY");
		if(param!=null&&captcha!=null&&param.equals(captcha)){
			return true;
		}
		return false;
	}
}
