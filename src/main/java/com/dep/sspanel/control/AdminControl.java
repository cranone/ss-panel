package com.dep.sspanel.control;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dep.sspanel.entity.SystemLog;
import com.dep.sspanel.entity.User;
import com.dep.sspanel.service.SystemLogService;
import com.dep.sspanel.service.UserService;
import com.dep.sspanel.util.vo.Page;

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
	public String log(Page<SystemLog> page,Model model){
		page = systemLogService.findByPage(page);
		model.addAttribute("page", page);
		return "admin/log";
	}
	
	@RequestMapping(value = URIConstants.ADMIN_USER_LIST)
	public String userList(Page<User> page,Model model,User condition){
		page =userService.findByPage(page,condition);
		model.addAttribute("page", page);
		return "admin/userlist";
	}
}
