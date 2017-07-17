package com.dep.sspanel.util.quartz;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.dep.sspanel.entity.SystemLog;
import com.dep.sspanel.service.SystemLogService;
import com.dep.sspanel.service.UserService;

@Component
public class QuartzJob {

	private static final Logger logger=LoggerFactory.getLogger(QuartzJob.class);
	
	@Resource
	private UserService userService;
	@Resource
	private SystemLogService systemLogService;
	
	public QuartzJob(){
		
	}
	
	public void work(){
		Integer result = userService.checkExpires();
		logger.info("检测到{}个用户已过期",result);
		if(result>0){
			systemLogService.save(new SystemLog("127.0.0.1","info","admin","检测到"+result+"个用户已过期"));
		}
	}
}
