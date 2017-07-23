package com.dep.sspanel.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate4.HibernateCallback;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;
import org.springframework.util.StringUtils;

import com.dep.sspanel.dao.GenericDao;
import com.dep.sspanel.exception.SystemException;
import com.dep.sspanel.util.GenericsUtil;
import com.dep.sspanel.util.type.ErrorCodeType;
import com.dep.sspanel.util.vo.Page;

public abstract class GenericDaoImpl<T> extends HibernateDaoSupport implements GenericDao<T> {
	protected Class<T> entityClass;

	@SuppressWarnings("unchecked")
	public GenericDaoImpl() {
		this.entityClass = GenericsUtil.getSuperClassGenericType(this.getClass());
	}

	@Resource
	public void setSessionFacotry(SessionFactory sessionFacotry) {
		super.setSessionFactory(sessionFacotry);
	}

	@Override
	public T get(Serializable id) {
		if (id == null) {
			throw new SystemException(ErrorCodeType.id_null);
		}
		return getHibernateTemplate().get(this.entityClass, id);
	}

	@Override
	public T load(Serializable id) {
		if (id == null) {
			throw new SystemException(ErrorCodeType.id_null);
		}
		return getHibernateTemplate().load(this.entityClass, id);
	}

	@Override
	public Serializable save(T t) {
		return getHibernateTemplate().save(t);
	}

	@Override
	public void saveOrUpdate(T t) {
		getHibernateTemplate().saveOrUpdate(t);
	}

	@Override
	public void update(T t) {
		getHibernateTemplate().update(t);
	}

	@Override
	public void delete(Serializable... ids) {
		for (Serializable id : ids) {
			if (id == null) {
				throw new SystemException(ErrorCodeType.id_null);
			}
			T t = get(id);
			getHibernateTemplate().delete(t);
		}
	}
	
	@Override
	public Page<T> findByPage(Page<T> page) {
		return findByPage(page, null, null);
	}

	@Override
	public Page<T> findByPage(Page<T> page, String condition) {
		return findByPage(page, condition, null);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<T> findByPage(Page<T> page, String condition, Object[] values) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ").append(this.entityClass.getName()).append(" ");
		if (!StringUtils.isEmpty(condition)) {
			sb.append(condition);
		}
		addSort(sb,condition);
		String hql = sb.toString();
		// 获取分页数据
		Session session = currentSession();
		Query queryObject =session.createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		queryObject.setFirstResult(page.getCurrentPage());
		queryObject.setMaxResults(page.getSizePage());
		List<T> list=queryObject.list();
		page.setList(list);

		// 计算总页数
		String countHql = " select count(*) " + hql;
		queryObject =session.createQuery(countHql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				queryObject.setParameter(i, values[i]);
			}
		}
		Integer count=Integer.valueOf(queryObject.uniqueResult().toString());
//		page.setTotalPage((count - 1) / page.getSizePage() + 1);
		page.setTotal(count);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Page<T> findByPage(Page<T> page, Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ").append(this.entityClass.getName()).append(" ");
		if(map!=null&&!map.isEmpty()){
			sb.append(" where 1=1 ");
			for(String key:map.keySet()){
				sb.append(" and ").append(key).append("=:").append(key.replace(".", ""));
			}
		}
		// 获取分页数据
		String hql = sb.toString();
		Session session = currentSession();
		Query query = session.createQuery(hql);
		if(map!=null&&!map.isEmpty()){
			for(String key:map.keySet()){
				query.setParameter(key.replace(".", ""), map.get(key));
			}
		}
		query.setFirstResult(page.getCurrentPage());
		query.setMaxResults(page.getSizePage());
		List<T> list=query.list();
		page.setList(list);
		
		// 计算总页数
		String countHql = " select count(*) " + hql;
		query =session.createQuery(countHql);
		if(map!=null&&map.size()>0){
			for(String key:map.keySet()){
				query.setParameter(key.replace(".", ""), map.get(key));
			}
		}
		Integer count=Integer.valueOf(query.uniqueResult().toString());
//		page.setTotalPage((count - 1) / page.getSizePage() + 1);
		page.setTotal(count);
		return page;
	}
	
	@SuppressWarnings("unchecked")
	public List<T> find(String queryString, Object... values){
		return (List<T>) getHibernateTemplate().find(queryString, values);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> find(Map<String, Object> map) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ").append(this.entityClass.getName()).append(" ");
		if(map!=null&&!map.isEmpty()){
			sb.append(" where 1=1 ");
			for(String key:map.keySet()){
				sb.append(" and ").append(key).append("=:").append(key.replace(".", ""));
			}
		}
		Session session = currentSession();
		Query query = session.createQuery(sb.toString());
		if(map!=null&&!map.isEmpty()){
			for(String key:map.keySet()){
				query.setParameter(key.replace(".", ""), map.get(key));
			}
		}
		return query.list();
	}
	
	private void addSort(StringBuffer sb,String condition){
		try {
			if(!StringUtils.isEmpty(condition)&&!condition.contains("order by")&&entityClass.getField("id")!=null){
				sb.append(" order by id ");
			}
		} catch (NoSuchFieldException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}
}
