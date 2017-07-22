package com.dep.sspanel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 节点在线人数日志
 * @author Maclaine
 *
 */
@Entity(name="ss_node_online_log")
@DynamicUpdate
public class NodeOnlineLog  implements Serializable{
	private static final long serialVersionUID = 2195673864802795212L;
	
	private Integer id;//*
	private String onlineUser;//在线人数*
	private Long logTime;//记录时间*
	private Node node;//节点*
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(name="log_time")
	public Long getLogTime() {
		return logTime;
	}
	@ManyToOne
	@JoinColumn(name="node_id")
	public Node getNode() {
		return node;
	}
	@Column(name="online_user")
	public String getOnlineUser() {
		return onlineUser;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLogTime(Long logTime) {
		this.logTime = logTime;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public void setOnlineUser(String onlineUser) {
		this.onlineUser = onlineUser;
	}
	
	
	
}
