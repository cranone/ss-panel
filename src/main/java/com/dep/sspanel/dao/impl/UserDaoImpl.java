package com.dep.sspanel.dao.impl;

import java.util.Calendar;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.dep.sspanel.dao.UserDao;
import com.dep.sspanel.entity.User;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	

	@Override
	public User findUserByNameOrEmail(String value) {
		String hql="from User where username=:username or email=:email";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("username", value);
		query.setParameter("email", value);
		return (User) query.uniqueResult();
	}


	@Override
	public Integer updateAllOutDate() {
		String hql="update User set enable=:enable,updateDate=:expiresDate where expiresDate<=:expiresDate and enable=true";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("enable", false);
		query.setParameter("expiresDate", Calendar.getInstance().getTime());
		return query.executeUpdate();
	}


}
