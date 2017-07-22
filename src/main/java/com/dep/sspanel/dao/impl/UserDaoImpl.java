package com.dep.sspanel.dao.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.hibernate.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.dep.sspanel.dao.UserDao;
import com.dep.sspanel.entity.User;
import com.dep.sspanel.util.vo.Page;

@Repository
public class UserDaoImpl extends GenericDaoImpl<User> implements UserDao{
	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	
	@SuppressWarnings("unchecked")
	@Override
	public User findUserByName(String name) {
		String hql="from User where username=?";
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
		String hql="from User where username=? or email=?";
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
		String hql="update User set enable=:enable,updateDate=:expiresDate where expiresDate<=:expiresDate and enable=true";
		Query query = getSessionFactory().getCurrentSession().createQuery(hql);
		query.setParameter("enable", false);
		query.setParameter("expiresDate", Calendar.getInstance().getTime());
		return query.executeUpdate();
	}


	@Override
	public Page<User> findByPage(Page<User> page, User condition) {
		StringBuffer sb=new StringBuffer();
		sb.append(" where 1=1 ");
		List<Object> list=new ArrayList<Object>();
		
		if(!StringUtils.isEmpty(condition.getUsername())){
			sb.append(" and username=? ");
			list.add(condition.getUsername());
		}
		return super.findByPage(page,sb.toString(),list.toArray());
	}

}
