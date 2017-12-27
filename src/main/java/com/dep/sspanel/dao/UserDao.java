package com.dep.sspanel.dao;


import com.dep.sspanel.entity.User;

public interface UserDao extends GenericDao<User>{
	
	/**
	 * 根据用户名或邮箱获取用户
	 * @param condition
	 * @return
	 */
	public User findUserByNameOrEmail(String value);
	
	/**
	 * 禁用所有过期用户
	 * @return
	 */
	public Integer updateAllOutDate();
	
	/**
	 * 根据用户名或邮箱获取用户
	 * @param name
	 * @param email
	 * @return
	 */
	public User findUserByNameOrEmail(String name,String email);
	
	/**
	 * 查找最后一个User
	 * @return
	 */
	public User findLastUser();
}
