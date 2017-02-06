package com.dep.sspanel.service;

import java.util.Set;

import com.dep.sspanel.entity.User;
import com.dep.sspanel.util.vo.Page;

public interface UserService extends GenericService<User>{
	/**
	 * 根据用户名获取用户
	 * @param condition
	 * @return
	 */
	public User findUserByName(String name);
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
	
	/**
	 * 修改密码
	 * @param username
	 * @return
	 */
	public boolean changePassword(String username,String oldpassword,String password);
	
	/**
	 * 根据条件分页查询用户信息
	 * @param page
	 * @param condition
	 * @return
	 */
	public Page<User> findByPage(Page<User> page,User condition);
}
