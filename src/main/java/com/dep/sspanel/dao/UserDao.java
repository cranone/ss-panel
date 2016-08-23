package com.dep.sspanel.dao;

import com.dep.sspanel.entity.User;

public interface UserDao extends GenericDao<User>{
	/**
	 * 根据邮箱获取用户
	 * @param email
	 * @return User/null
	 */
	public User findUserByEmail(String email);
	
	/**
	 * 根据用户名或邮箱获取用户
	 * @param condition
	 * @return
	 */
	public User findUserByNameOrEmail(String condition);
}
