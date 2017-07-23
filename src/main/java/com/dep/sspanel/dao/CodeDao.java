package com.dep.sspanel.dao;

import com.dep.sspanel.entity.Code;
import com.dep.sspanel.util.vo.Page;

public interface CodeDao extends GenericDao<Code>{
	public Code findByCode(String code);
	public Code findByCodeNoConsumer(String code);
	public Page<Code> findByPage(Page<Code> page,Code code);
	public Page<Code> findByPageNoConsumer(Page<Code> page,Code code);
}
