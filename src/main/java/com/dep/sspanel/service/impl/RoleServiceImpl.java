package com.dep.sspanel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dep.sspanel.dao.RoleDao;
import com.dep.sspanel.entity.Role;
import com.dep.sspanel.service.RoleService;

@Service
@Transactional
public class RoleServiceImpl extends GenericServiceImpl<Role> implements RoleService{
	private RoleDao roleDao;
	@Resource
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
		this.genericDao=roleDao;
	}
}
