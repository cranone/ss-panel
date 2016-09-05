package com.dep.sspanel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dep.sspanel.dao.SystemLogDao;
import com.dep.sspanel.entity.SystemLog;
import com.dep.sspanel.service.SystemLogService;

@Service
@Transactional
public class SystemLogServiceImpl extends GenericServiceImpl<SystemLog> implements SystemLogService{
	private SystemLogDao systemLogDao;
	@Resource
	public void setSystemLogDao(SystemLogDao systemLogDao) {
		this.systemLogDao = systemLogDao;
		this.genericDao=systemLogDao;
	}
	
	
	
}
