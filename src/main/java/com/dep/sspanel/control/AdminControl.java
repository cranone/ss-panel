package com.dep.sspanel.control;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dep.sspanel.annotation.SystemControllerLog;
import com.dep.sspanel.entity.SystemLog;
import com.dep.sspanel.entity.User;
import com.dep.sspanel.service.SystemLogService;
import com.dep.sspanel.service.UserService;
import com.dep.sspanel.shiro.SecurityUtil;
import com.dep.sspanel.util.type.ErrorCodeType;
import com.dep.sspanel.util.vo.Page;

@Controller
public class AdminControl {
	private static final Logger logger = LoggerFactory.getLogger(AdminControl.class);
	
	@Resource
	private UserService userService;
	@Resource
	private SystemLogService systemLogService;
	
	@InitBinder
	public void initBinder(WebDataBinder binder) throws Exception {
		// 注册自定义的属性编辑器
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		CustomDateEditor dateEditor = new CustomDateEditor(df, true);
		// 表示如果命令对象有Date类型的属性，将使用该属性编辑器进行类型转换
		binder.registerCustomEditor(Date.class, dateEditor);
	}
	
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
	
	@ResponseBody
	@RequestMapping(value = "/admin/userListAjax")
	public Map<String,Object> userListAjax(User condition,Integer limit,Integer offset,Page<User> page){
		Map<String,Object> map=new HashMap<String, Object>();
		page =userService.findByPage(page,condition);
		map.put("rows", page.getList());
		map.put("total",page.getTotalPage());
		return map;
	}
	
	@SystemControllerLog(description="admin_useredit")
	@RequestMapping(value = URIConstants.ADMIN_USER_EDIT,method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> edit(User user,Double transfer){
		Map<String,Object> map=new HashMap<String, Object>();
		User oldUser=userService.get(user.getId());
		if(!StringUtils.isEmpty(user.getPasswd())){
			oldUser.setPasswd(SecurityUtil.encrypt(oldUser.getUsername(), user.getPasswd()));
		}
		oldUser.setTransferEnable(Math.round(transfer*1024*1024*1024));
		oldUser.setExpiresDate(user.getExpiresDate());
		userService.update(oldUser);
		map.put("status", ErrorCodeType.success.getCode());
		map.put("info", "修改成功");
		return map;
	}
}
