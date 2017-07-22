package com.dep.sspanel.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dep.sspanel.dao.NodeDao;
import com.dep.sspanel.entity.Node;
import com.dep.sspanel.service.NodeService;

@Service
@Transactional
public class NodeServiceImpl extends GenericServiceImpl<Node> implements NodeService{
	private NodeDao nodeDao;
	@Resource
	public void setRoleDao(NodeDao nodeDao) {
		this.nodeDao = nodeDao;
		this.genericDao=nodeDao;
	}
}
