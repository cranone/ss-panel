package com.dep.sspanel.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 流量日志
 * @author Maclaine
 *
 */
@Entity(name="user_traffic_log")
@DynamicUpdate
public class UserTrafficLog implements Serializable{
	private static final long serialVersionUID = 6489380738553107509L;
	
	private Integer id;//*
	private Long upload=0L;//上传*
	private Long download=0L;//下载*
	private User user;//使用人*
	private Node node;//节点*
	private Double rate;//倍率*
	private String traffic;//流量*
	private Long logTime;//记录时间*

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name="u")
	public Long getUpload() {
		return upload;
	}
	@Column(name="d")
	public Long getDownload() {
		return download;
	}
	@ManyToOne
	@JoinColumn(name="user_id")
	public User getUser() {
		return user;
	}
	@ManyToOne
	@JoinColumn(name="node_id")
	public Node getNode() {
		return node;
	}
	@Column
	public Double getRate() {
		return rate;
	}
	@Column
	public String getTraffic() {
		return traffic;
	}
	@Column(name="log_time")
	public Long getLogTime() {
		return logTime;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setUpload(Long upload) {
		this.upload = upload;
	}
	public void setDownload(Long download) {
		this.download = download;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public void setRate(Double rate) {
		this.rate = rate;
	}
	public void setTraffic(String traffic) {
		this.traffic = traffic;
	}
	public void setLogTime(Long logTime) {
		this.logTime = logTime;
	}
	
	
}
