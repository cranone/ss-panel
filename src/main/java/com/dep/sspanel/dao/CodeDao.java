package com.dep.sspanel.dao;

import com.dep.sspanel.entity.Code;

public interface CodeDao extends GenericDao<Code>{
	public Code findByCode(String code);
	public Code findByCodeNoConsumer(String code);
}
