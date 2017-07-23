package com.dep.sspanel.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import com.dep.sspanel.util.type.CodeType;
import com.dep.sspanel.util.vo.Page;

public interface GenericService<T> {

	/**
	 * 查找
	 * <br>对于get方法，hibernate会确认一下该id对应的数据是否存在，首先在session缓存中查找，然后在二级缓存中查找，还没有就查数据库，数据库中没有就返回null
	 * <br>需要注意的是，get方法如果在 session缓存中找到了该id对应的对象，如果刚好该对象前面是被代理过的，如被load方法使用过，或者被其他关联对象延迟加载过，那么返回的还是 原先的代理对象，而不是实体类对象，如果该代理对象还没有加载实体数据（就是id以外的其他属性数据），那么它会查询二级缓存或者数据库来加载数据，但是 返回的还是代理对象，只不过已经加载了实体数据
	 * @param id
	 * @return T
	 */
	public T get(Serializable id);
	
	/**
	 * 查找(懒加载)
	 * <br>load方法的执行则比较复杂首先查找session的persistent Context中是否有缓存，如果有则直接返回 如果没有则判断是否是lazy，如果不是直接访问数据库检索，查到记录返回，查不到抛出异常 如果是lazy则需要建立代理对象，对象的initialized属性为false，target属性为null 在访问获得的代理对象的属性时,检索数据库，如果找到记录则把该记录的对象复制到代理对象的target上，并将initialized=true，如果找不到就抛出异常
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
	
	/**
	 * 分页查询
	 * @return
	 */
	public Page<T> findByPage(Page<T> page);
	
	/**
	 * 条件分页查询
	 * @param page
	 * @param map
	 * @return
	 */
	public Page<T> findByPage(Page<T> page,  Map<String, Object> map);
	
	/**
	 * 查找全部
	 * @return
	 */
	public List<T> findAll();
	
	/**
	 * 按条件查找唯一
	 * @param map
	 * @return
	 */
	public T findOne(Map<String, Object> map);
	
	/**
	 * 按条件查找
	 * @param map
	 * @return
	 */
	public List<T> find(Map<String, Object> map);
	
}
