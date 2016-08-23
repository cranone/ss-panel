package com.dep.sspanel.service.impl;

import java.util.Collections;
import java.util.Set;

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
	
	@Override
	public User findUserByEmail(String email) {
		return userDao.findUserByEmail(email);
	}

	@Override
	public Set<String> findRolesByEmail(String email) {
		User user=findUserByEmail(email);
		if(user == null) {
            return Collections.emptySet();
        }
		return user.getRoleNameSet();
	}

	@Override
	public Set<String> findPermissionsByEmail(String email) {
		User user=findUserByEmail(email);
		if(user == null) {
            return Collections.emptySet();
        }
		return user.getRolePermissionSet();
	}

	@Override
	public User findUserByNameOrEmail(String condition) {
		return userDao.findUserByNameOrEmail(condition);
	}

	@Override
	public Set<String> findRolesByNameOrEmail(String condition) {
		User user=findUserByNameOrEmail(condition);
		if(user == null) {
            return Collections.emptySet();
        }
		return user.getRoleNameSet();
	}

	@Override
	public Set<String> findPermissionsByNameOrEmail(String condition) {
		User user=findUserByNameOrEmail(condition);
		if(user == null) {
            return Collections.emptySet();
        }
		return user.getRolePermissionSet();
	}
}
