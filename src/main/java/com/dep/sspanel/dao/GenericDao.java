package com.dep.sspanel.dao;

import java.io.Serializable;

public interface GenericDao<T> {
	/**
	 * 按照ID查询
	 * @param id 序列化ID
	 * @return T 实体对象
	 */
	public T get(Serializable id);
	
	/**
	 * 按照ID查询Lazy
	 * @param id 序列化ID
	 * @return T 实体对象
	 */
	public T load(Serializable id);
	
	/**
	 * 保存
	 * @param t 实体对象
	 * @return ID 序列化ID
	 */
	public Serializable save(T t);
	
	/**
	 * 保存或更新
	 * @param t 实体对象
	 * @return ID 序列化ID
	 */
	public void saveOrUpdate(T t);
	
	/**
	 * 更新
	 * @param t 实体对象
	 */
	public void update(T t);
	
	/**
	 * 删除
	 * @param ids 需要删除的ID
	 */
	public void delete(Serializable...ids);
}
