package com.dep.sspanel.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dep.sspanel.dao.CodeDao;
import com.dep.sspanel.entity.Code;
import com.dep.sspanel.entity.User;
import com.dep.sspanel.service.CodeService;
import com.dep.sspanel.util.type.CodeType;

@Service
@Transactional
public class CodeServiceImpl extends GenericServiceImpl<Code> implements CodeService{
	private CodeDao codeDao;
	@Resource
	public void setRoleDao(CodeDao codeDao) {
		this.codeDao = codeDao;
		this.genericDao=codeDao;
	}
	@Override
	public Code findByCode(String code) {
		return codeDao.findByCode(code);
	}
	@Override
	public List<Code> createCode(CodeType codeType, int num, int amount,User user) {
		List<Code> list=new ArrayList<>();
		for(int i=0;i<num;i++){
			Code code=new Code();
			code.setCode(UUID.randomUUID().toString());
			code.setCodeType(codeType);
			code.setAmount(amount);
			code.setCreater(user);
			code.setCreateDate(new Date());
			codeDao.save(code);
			list.add(code);
		}
		return list;
	}
	@Override
	public Code findByCodeActive(String code) {
		return codeDao.findByCodeNoConsumer(code);
	}
}
