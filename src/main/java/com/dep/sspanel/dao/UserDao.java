package com.dep.sspanel.dao;

import com.dep.sspanel.entity.User;

public interface UserDao extends GenericDao<User>{
	/**
	 * 根据邮箱获取用户
	 * @param email
	 * @return User/null
	 */
	public User findUserByEmail(String email);
}
