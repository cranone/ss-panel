package com.dep.sspanel.entity;

import java.util.Date;

/**
 * 流量日志
 * @author Maclaine
 *
 */
public class TrafficLog {
	private String id;
	private long upload=0L;//上传
	private long download=0L;//下载
	private Date createDate;//创建时间
	private User user;//使用人
	private Node node;//节点
}
