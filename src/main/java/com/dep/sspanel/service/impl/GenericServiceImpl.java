package com.dep.sspanel.service.impl;

import java.io.Serializable;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dep.sspanel.dao.GenericDao;
import com.dep.sspanel.service.GenericService;

@Transactional
@Service
public abstract class GenericServiceImpl<T> implements GenericService<T> {
	
	protected GenericDao<T> genericDao;
	
	@Override
	public T get(Serializable id) {
		return genericDao.get(id);
	}
	
	@Override
	public T load(Serializable id) {
		return genericDao.load(id);
	}
	
	@Override
	public Serializable save(T t) {
		return genericDao.save(t);
	}
	
	@Override
	public void update(T t) {
		genericDao.update(t);
	}
	
	@Override
	public void saveOrUpdate(T t) {
		genericDao.saveOrUpdate(t);
	}
	
	@Override
	public void delete(Serializable... ids) {
		genericDao.delete(ids);
	}
}
