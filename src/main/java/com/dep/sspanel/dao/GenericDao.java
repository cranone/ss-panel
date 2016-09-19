package com.dep.sspanel.dao;

import java.io.Serializable;

import com.dep.sspanel.util.vo.Page;

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
	
	/**
	 * 分页查找
	 * @param page
	 * @return
	 */
	public Page<T> findByPage(Page<T> page);
	
	/**
	 * 按条件分页查找
	 * @param page
	 * @param condition
	 * @return
	 */
	public Page<T> findByPage(Page<T> page,String condition);
	
	/**
	 * 按条件分页查找
	 * @param page
	 * @param condition
	 * @param values
	 * @return
	 */
	public Page<T> findByPage(Page<T> page,String condition,Object values[]);
}
