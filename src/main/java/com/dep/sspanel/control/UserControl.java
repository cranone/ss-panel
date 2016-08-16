package com.dep.sspanel.control;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserControl {

	private static final Logger logger = LoggerFactory.getLogger(UserControl.class);
	
	@RequestMapping(value=URIConstants.GET_LOGIN)
	public String login(HttpServletRequest req, Model model){
		String exceptionClassName = (String)req.getAttribute("shiroLoginFailure");
        String error = null;
        if(UnknownAccountException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
            error = "用户名/密码错误";
        } else if(exceptionClassName != null) {
            error = "其他错误：" + exceptionClassName;
        }
        logger.info(error);
        model.addAttribute("error", error);
        return "login";
	}
	
	
	@RequestMapping(value={URIConstants.USER_INDEX,URIConstants.USER_DEFAULT})
	public String index(){
		return "user/index";
	}
}
