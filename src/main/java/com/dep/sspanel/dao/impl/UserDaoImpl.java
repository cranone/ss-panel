package com.dep.sspanel.dao.impl;

import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dep.sspanel.dao.UserDao;
import com.dep.sspanel.entity.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	
	@SuppressWarnings("unchecked")
	@Override
	public User findUserByName(String name) {
		String hql="from user where username=?";
		List<User> list=(List<User>) getHibernateTemplate().find(hql, name);
		if(list.size()==0){
			return null;
		}else{
			if(list.size()>1){
				logger.warn("expect get one,but ?,email:?",list.size(),name);
			}
			return list.get(0);
		}
		
	}


	@SuppressWarnings("unchecked")
	@Override
	public User findUserByNameOrEmail(String value) {
		String hql="from user where username=? or email=?";
		List<User> list=(List<User>) getHibernateTemplate().find(hql, value,value);
		if(list.size()==0){
			return null;
		}else{
			if(list.size()>1){
				logger.warn("expect get one,but ?,condition:?",list.size(),value);
			}
			return list.get(0);
		}
	}


	@Override
	public Integer updateAllOutDate() {
		String hql="update user set enable=:enable,updateDate=:expiresDate where expiresDate<=:expiresDate and enable=true";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("enable", false);
		query.setParameter("expiresDate", Calendar.getInstance().getTime());
		return query.executeUpdate();
	}

}
