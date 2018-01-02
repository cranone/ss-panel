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
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;//*
    @Column(name="online_user")
	private String onlineUser;//在线人数*
    @Column(name="log_time")
	private Long logTime;//记录时间*
    @ManyToOne
    @JoinColumn(name="node_id")
	private Node node;//节点*
	
	public Integer getId() {
		return id;
	}
	public Long getLogTime() {
		return logTime;
	}
	public Node getNode() {
		return node;
	}
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
