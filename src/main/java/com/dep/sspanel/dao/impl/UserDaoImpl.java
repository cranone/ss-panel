package com.dep.sspanel.dao.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dep.sspanel.dao.UserDao;
import com.dep.sspanel.entity.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	
	@Override
	public User findUserByEmail(String email) {
		String hql="from user where email=?";
		List<User> list=(List<User>) getHibernateTemplate().find(hql, email);
		if(list.size()==0){
			return null;
		}else{
			if(list.size()>1){
				logger.warn("expect get one,but ?,email:?",list.size(),email);
			}
			return list.get(0);
		}
		
	}


	@Override
	public User findUserByNameOrEmail(String condition) {
		String hql="from user where username=? or email=?";
		List<User> list=(List<User>) getHibernateTemplate().find(hql, condition,condition);
		if(list.size()==0){
			return null;
		}else{
			if(list.size()>1){
				logger.warn("expect get one,but ?,condition:?",list.size(),condition);
			}
			return list.get(0);
		}
	}

}
