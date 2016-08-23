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
	 * 根据邮箱获取角色列表
	 * @param email
	 * @return Set<String>;不存在则返回Collections.emptySet()
	 */
	public Set<String> findRolesByEmail(String email);
	
	/**
	 * 根据邮箱获取权限列表
	 * @param email
	 * @return Set<String>;不存在则返回Collections.emptySet()
	 */
	public Set<String> findPermissionsByEmail(String email);
	
	/**
	 * 根据用户名或邮箱获取用户
	 * @param condition
	 * @return
	 */
	public User findUserByNameOrEmail(String condition);
	
	/**
	 * 根据用户名或邮箱获取角色列表
	 * @param condition
	 * @return Set<String>;不存在则返回Collections.emptySet()
	 */
	public Set<String> findRolesByNameOrEmail(String condition);
	
	/**
	 * 根据用户名或邮箱获取权限列表
	 * @param condition
	 * @return Set<String>;不存在则返回Collections.emptySet()
	 */
	public Set<String> findPermissionsByNameOrEmail(String condition);
}
