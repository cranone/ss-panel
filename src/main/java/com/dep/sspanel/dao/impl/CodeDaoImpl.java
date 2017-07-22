package com.dep.sspanel.dao.impl;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.dep.sspanel.dao.CodeDao;
import com.dep.sspanel.entity.Code;

@Repository
public class CodeDaoImpl extends GenericDaoImpl<Code> implements CodeDao{

	@Override
	public Code findByCode(String code) {
		String hql="from Code where code=:code";
		Query query=currentSession().createQuery(hql);
		query.setParameter("code", code);
		return (Code) query.uniqueResult();
	}

	@Override
	public Code findByCodeNoConsumer(String code) {
		String hql=" from Code where code=:code and consumer is null";
		Query query=currentSession().createQuery(hql);
		query.setParameter("code", code);
		return (Code) query.uniqueResult();
	}

}
