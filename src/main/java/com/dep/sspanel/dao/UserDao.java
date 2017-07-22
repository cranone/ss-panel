package com.dep.sspanel.dao;


import com.dep.sspanel.entity.User;
import com.dep.sspanel.util.vo.Page;

public interface UserDao extends GenericDao<User>{
	/**
	 * 根据用户名获取用户
	 * @param email
	 * @return User/null
	 */
	public User findUserByName(String name);
	
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
	 * 分页查找用户
	 * @param page
	 * @param condition
	 * @return
	 */
	public Page<User> findByPage(Page<User> page,User condition);
}
