package com.dep.sspanel.dao.impl;

import java.io.Serializable;
import java.util.List;

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

	@Override
	public Page<T> findByPage(Page<T> page, String condition, Object[] values) {
		StringBuffer sb = new StringBuffer();
		sb.append(" from ").append(this.entityClass.getName()).append(" ");
		if (!StringUtils.isEmpty(condition)) {
			sb.append(condition);
		}
		String hql = sb.toString();
		// 获取分页数据
		List<T> list = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<List<T>>() {
			@Override
			public List<T> doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(hql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				queryObject.setFirstResult(page.getSizePage() * (page.getCurrentPage() - 1));
				queryObject.setMaxResults(page.getSizePage());
				return queryObject.list();
			}
		});
		page.setList(list);

		// 计算总页数
		String countHql = " select count(*) " + hql;
		Integer count = getHibernateTemplate().executeWithNativeSession(new HibernateCallback<Integer>() {
			@Override
			public Integer doInHibernate(Session session) throws HibernateException {
				Query queryObject = session.createQuery(countHql);
				if (values != null) {
					for (int i = 0; i < values.length; i++) {
						queryObject.setParameter(i, values[i]);
					}
				}
				return Integer.valueOf(queryObject.uniqueResult().toString());
			}
		});
		page.setTotalPage((count - 1) / page.getSizePage() + 1);

		return page;
	}
}
