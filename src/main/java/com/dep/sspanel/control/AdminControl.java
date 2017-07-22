package com.dep.sspanel.control;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dep.sspanel.annotation.SystemControllerLog;
import com.dep.sspanel.entity.Code;
import com.dep.sspanel.entity.SystemLog;
import com.dep.sspanel.entity.User;
import com.dep.sspanel.service.CodeService;
import com.dep.sspanel.service.SystemLogService;
import com.dep.sspanel.service.UserService;
import com.dep.sspanel.shiro.SecurityUtil;
import com.dep.sspanel.util.type.CodeType;
import com.dep.sspanel.util.type.ErrorCodeType;
import com.dep.sspanel.util.vo.Page;

@Controller
public class AdminControl {
	private static final Logger logger = LoggerFactory.getLogger(AdminControl.class);
	
	@Resource
	private UserService userService;
	@Resource
	private CodeService codeService;
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
	public String userList(Model model){
		return "admin/userlist";
	}
	
	@ResponseBody
	@RequestMapping(value = URIConstants.ADMIN_USER_LIST_AJAX)
	public Map<String,Object> userListAjax(User condition,Page<User> page){
		Map<String,Object> map=new HashMap<String, Object>();
		page =userService.findByPage(page);
		map.put("rows", page.getList());
		map.put("total",page.getTotalPage());
		return map;
	}
	
	@RequestMapping(value = URIConstants.ADMIN_CODE_LIST)
	public String codeList(Model model){
		return "admin/codelist";
	}
	
	@ResponseBody
	@RequestMapping(value = URIConstants.ADMIN_CODE_LIST_AJAX)
	public Map<String,Object> codeListAjax(Code condition,Page<Code> page){
		Map<String,Object> map=new HashMap<String, Object>();
		page =codeService.findByPage(page);
		map.put("rows", page.getList());
		map.put("total",page.getTotalPage());
		return map;
	}
	
	@ResponseBody
	@RequestMapping(value = "/admin/createCode")
	public Map<String,Object> createCode(Integer num,Integer type,Integer amount){
		Map<String,Object> map=new HashMap<String, Object>();
		String userName =SecurityUtils.getSubject().getPrincipal().toString();
		User user=userService.findUserByName(userName);
		CodeType codeType=CodeType.time;
		switch (type) {
		case 1:
			codeType=CodeType.bandwidth;
			break;
		case 2:
			codeType=CodeType.time;
			break;
		default:
			codeType=CodeType.time;
			break;
		}
		List<Code> list=codeService.createCode(codeType, num, amount, user);
		map.put("status", ErrorCodeType.success.getCode());
		map.put("info", list);
		return map;
	}
	
	@SystemControllerLog(description="admin_useredit")
	@RequestMapping(value = URIConstants.ADMIN_USER_EDIT,method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> edit(String field,User user,Double transfer){
		Map<String,Object> map=new HashMap<String, Object>();
		User oldUser=userService.get(user.getId());
		switch (field) {
		case "transferEnable":
			oldUser.setTransferEnable(user.getTransferEnable()*1024*1024*1024);
			break;
		case "upload":
			oldUser.setUpload(user.getUpload()*1024*1024*1024);
			break;
		case "download":
			oldUser.setDownload(user.getDownload()*1024*1024*1024);
			break;
		case "pass":
			oldUser.setPass(SecurityUtil.encrypt(oldUser.getUsername(), user.getPass()));
			break;
		case "expiresDate":
			oldUser.setExpiresDate(user.getExpiresDate());
			break;
		default:
			break;
		}
		userService.update(oldUser);
		map.put("status", ErrorCodeType.success.getCode());
		return map;
	}
}
