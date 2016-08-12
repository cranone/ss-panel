package com.dep.sspanel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dep.sspanel.dao.UserDao;
import com.dep.sspanel.entity.User;
import com.dep.sspanel.service.UserService;

@Service
@Transactional
public class UserServiceImpl extends GenericServiceImpl<User> implements UserService{
	private UserDao userDao;
	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
		this.genericDao=userDao;
	}
}
