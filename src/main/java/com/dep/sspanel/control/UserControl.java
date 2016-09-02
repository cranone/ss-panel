package com.dep.sspanel.control;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.cache.CacheManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dep.sspanel.entity.User;
import com.dep.sspanel.service.UserService;
import com.dep.sspanel.util.GlobalConst;
import com.dep.sspanel.util.KaptchaUtil;
import com.dep.sspanel.util.ServerUtil;
import com.dep.sspanel.util.type.ErrorCodeType;

@Controller
public class UserControl {
	private static final Logger logger = LoggerFactory.getLogger(UserControl.class);

	@Resource
	private UserService userService;
	
	@Resource
	private CacheManager cacheManager;
	
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
		if(error!=null&"user.login.error".equals(error)&&req.getParameter("username")!=null){
			Integer remaining=GlobalConst.retryTime-Integer.parseInt(cacheManager.getCache("passwordRetryCache").get(req.getParameter("username").toString()).toString());
			error+= ","+ServerUtil.i18n(req, "user.login.remaining",new Object[]{remaining});
		}
		model.addAttribute("error", error);
		model.addAttribute("username", req.getParameter("username"));
		return "login";
	}

	@RequestMapping(value = { URIConstants.USER_INDEX, URIConstants.USER_DEFAULT })
	public String index(Model model) {
		String userName =SecurityUtils.getSubject().getPrincipal().toString();
		User user=userService.findUserByName(userName);
		model.addAttribute("user", user);
		return "user/index";
	}

	@RequestMapping(value = URIConstants.USER_NODE)
	public String node(Model model) {
		String userName =SecurityUtils.getSubject().getPrincipal().toString();
		User user=userService.findUserByName(userName);
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
	
	@RequestMapping(value = URIConstants.USER_CHANGEPASSWORD,method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> changePassword(HttpServletRequest request,String oldpassword,String password,String captchaCode){
		Map<String,Object> map=new HashMap<String, Object>();
		map.put("status", "1");
		if(!KaptchaUtil.validate(request, captchaCode)){
			map.put("info", "验证码错误");
			return map;
		}
		String username =SecurityUtils.getSubject().getPrincipal().toString();
		if(!userService.changePassword(username, oldpassword, password)){
			map.put("info", "原密码错误");
			return map;
		}
		map.put("status", ErrorCodeType.success.getCode());
		map.put("info", "修改成功");
		return map;
	}
}
