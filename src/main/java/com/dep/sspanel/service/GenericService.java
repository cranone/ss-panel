package com.dep.sspanel.service;

import java.io.Serializable;

public interface GenericService<T> {

	/**
	 * 查找
	 * @param id
	 * @return T
	 */
	public T find(Serializable id);
	
	/**
	 * 查找(懒加载)
	 * @param id
	 * @return T
	 */
	public T load(Serializable id);
	
	/**
	 * 保存
	 * @param t
	 * @return Serializable
	 */
	public Serializable save(T t);
	
	/**
	 * 保存或更新
	 * @param t
	 */
	public void saveOrUpdate(T t);
	
	/**
	 * 更新
	 * @param t
	 */
	public void update(T t);
	
	/**
	 * 删除
	 * @param ids
	 */
	public void delete(Serializable... ids);
}
