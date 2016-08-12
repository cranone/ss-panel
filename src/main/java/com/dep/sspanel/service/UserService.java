package com.dep.sspanel.service;

import java.util.Set;

import com.dep.sspanel.entity.User;

public interface UserService extends GenericService<User>{
	/**
	 * 根据邮箱获取用户
	 * @param email
	 * @return User/null
	 */
	public User findUserByEmail(String email);
	
	/**
	 * 根据邮箱获取权限列表
	 * @param email
	 * @return Set<String>
	 */
	public Set<String> findRolesByEmail(String email);
}
