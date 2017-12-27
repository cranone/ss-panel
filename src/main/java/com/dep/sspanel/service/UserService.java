package com.dep.sspanel.service;

import java.util.Set;

import com.dep.sspanel.entity.User;

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
	 * 检查到期时间
	 * @return
	 */
	public Integer checkExpires();
	
	/**
	 * 充值
	 * @param code 激活码
	 * @param user 用户
	 * @return
	 */
	public boolean recharge(String code,String userName);
	
	/**
	 * 用户是否存在
	 * @param user
	 * @return
	 */
	public boolean isExist(User user);
	
	/**
	 * 添加用户
	 * @param user
	 * @return
	 */
	public String addUser(User user); 
}
