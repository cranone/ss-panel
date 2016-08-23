package com.dep.sspanel.control;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dep.sspanel.entity.User;
import com.dep.sspanel.service.UserService;
import com.dep.sspanel.util.ServerUtil;

@Controller
public class UserControl {
	private static final Logger logger = LoggerFactory.getLogger(UserControl.class);

	@Resource
	private UserService userService;
	
	@RequestMapping(value = URIConstants.GET_LOGIN)
	public String login(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = ServerUtil.i18n(req, "user.login.error");
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = ServerUtil.i18n(req, "user.login.error");
		}else if(ExcessiveAttemptsException.class.getName().equals(exceptionClassName)){
			error = ServerUtil.i18n(req, "user.login.frequently");
		} else if ("captcha.error".equals(exceptionClassName)) {
			error = ServerUtil.i18n(req, "captcha.error");
		} else if (exceptionClassName != null) {
			error = ServerUtil.i18n(req, "errorcode.unknown.error");
			logger.error(exceptionClassName);
		}
		model.addAttribute("error", error);
		model.addAttribute("username", req.getParameter("username"));
		return "login";
	}

	@RequestMapping(value = { URIConstants.USER_INDEX, URIConstants.USER_DEFAULT })
	public String index(Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		String userName = currentUser.getPrincipal().toString();
		User user=userService.findUserByEmail(userName);
		model.addAttribute("user", user);
		return "user/index";
	}

	@RequestMapping(value = URIConstants.USER_NODE)
	public String node(Model model) {
		Subject currentUser = SecurityUtils.getSubject();
		String userName = currentUser.getPrincipal().toString();
		User user=userService.findUserByEmail(userName);
		model.addAttribute("user", user);
		return "user/node";
	}

	@RequestMapping(value = URIConstants.USER_MESSAGE)
	public String message() {
		return "user/message";
	}

	@RequestMapping(value = URIConstants.USER_SECURITY)
	public String security() {
		return "user/security";
	}
}
