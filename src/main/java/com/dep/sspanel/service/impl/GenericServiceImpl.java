package com.dep.sspanel.service.impl;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dep.sspanel.dao.GenericDao;
import com.dep.sspanel.service.GenericService;
import com.dep.sspanel.util.vo.Page;

@Transactional
@Service
public abstract class GenericServiceImpl<T> implements GenericService<T> {
	private static final Logger logger = LoggerFactory.getLogger(GenericServiceImpl.class);
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
	
	@Override
	public Page<T> findByPage(Page<T> page) {
		return genericDao.findByPage(page);
	}
	
	@Override
	public Page<T> findByPage(Page<T> page, Map<String, Object> map) {
		return genericDao.findByPage(page, map);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<T> findAll(){
		return genericDao.find(Collections.EMPTY_MAP);
	}
	
	@Override
	public T findOne(Map<String, Object> map){
		List<T> list = genericDao.find(map);
		if(list.size()==0){
			return null;
		}
		if(list.size()>1){
			logger.warn("expect get one,but ?.map:?",list.size(),map.values().toString());
		}
		return list.get(0);
	}
	
	
	@Override
	public List<T> find(Map<String, Object> map){
		return genericDao.find(map);
	}
}
