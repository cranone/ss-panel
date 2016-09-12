package com.dep.sspanel.control;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dep.sspanel.service.SystemLogService;
import com.dep.sspanel.service.UserService;

@Controller
public class AdminControl {
	private static final Logger logger = LoggerFactory.getLogger(AdminControl.class);
	
	@Resource
	private UserService userService;
	@Resource
	private SystemLogService systemLogService;
	
	@RequestMapping(value = {URIConstants.ADMIN_INDEX,URIConstants.ADMIN_DEFAULT})
	public String index(){
		return "admin/index";
	}
	
	@RequestMapping(value = URIConstants.ADMIN_LOG)
	public String log(){
		return "admin/log";
	}
}
