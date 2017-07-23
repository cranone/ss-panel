package com.dep.sspanel.service;

import java.util.List;

import com.dep.sspanel.entity.Code;
import com.dep.sspanel.entity.User;
import com.dep.sspanel.util.type.CodeType;
import com.dep.sspanel.util.vo.Page;

public interface CodeService extends GenericService<Code>{
	/**
	 * 根据code查找
	 * @param code
	 * @return
	 */
	public Code findByCode(String code);
	/**
	 * 根据code查找可用
	 * @param code
	 * @return
	 */
	public Code findByCodeActive(String code);
	
	/**
	 * 创建激活码
	 * @param codeType 类型
	 * @param num 数量
	 * @param amount 激活码效果
	 * @return
	 */
	public List<Code> createCode(CodeType codeType,int num,int amount,User user);
	

	public Page<Code> findByPage(Page<Code> page,CodeType codeType,String codeUse);
	
}
