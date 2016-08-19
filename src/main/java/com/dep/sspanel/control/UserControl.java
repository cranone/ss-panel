package com.dep.sspanel.control;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dep.sspanel.util.ServerUtil;

@Controller
public class UserControl {

	private static final Logger logger = LoggerFactory.getLogger(UserControl.class);

	@RequestMapping(value = URIConstants.GET_LOGIN)
	public String login(HttpServletRequest req, Model model) {
		String exceptionClassName = (String) req.getAttribute("shiroLoginFailure");
		String error = null;
		if (UnknownAccountException.class.getName().equals(exceptionClassName)) {
			error = ServerUtil.i18n(req, "user.login.error");
		} else if (IncorrectCredentialsException.class.getName().equals(exceptionClassName)) {
			error = ServerUtil.i18n(req, "user.login.error");
		} else if ("captcha.error".equals(exceptionClassName)) {
			error = ServerUtil.i18n(req, "captcha.error");
		} else if (exceptionClassName != null) {
			error = ServerUtil.i18n(req, "errorcode.unknown.error");
			logger.error(exceptionClassName);
		}
		model.addAttribute("error", error);
		return "login";
	}

	@RequestMapping(value = { URIConstants.USER_INDEX, URIConstants.USER_DEFAULT })
	public String index() {
		return "user/index";
	}

	@RequestMapping(value = URIConstants.USER_NODE)
	public String node() {
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
