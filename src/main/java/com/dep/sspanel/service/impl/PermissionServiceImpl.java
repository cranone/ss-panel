package com.dep.sspanel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dep.sspanel.dao.PermissionDao;
import com.dep.sspanel.entity.Permission;
import com.dep.sspanel.service.PermissionService;

@Service
@Transactional
public class PermissionServiceImpl extends GenericServiceImpl<Permission> implements PermissionService {
	private PermissionDao permissionDao;
	@Resource
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
		this.genericDao=permissionDao;
	}
	
	
}
