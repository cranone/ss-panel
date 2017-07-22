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
 * 节点日志
 * @author Maclaine
 *
 */
@Entity(name="ss_node_info_log")
@DynamicUpdate
public class NodeInfoLog  implements Serializable{
	private static final long serialVersionUID = 2195673864802795212L;
	
	private Integer id;//*
	private String load;//负载*
	private Double upTime;//运行时间*
	private Long logTime;//记录时间*
	private Node node;//节点*
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	//load为关键字,加上引号写入
	@Column(name="`load`")
	public String getLoad() {
		return load;
	}
	@Column(name="uptime")
	public Double getUpTime() {
		return upTime;
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
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLoad(String load) {
		this.load = load;
	}
	public void setUpTime(Double upTime) {
		this.upTime = upTime;
	}
	public void setLogTime(Long logTime) {
		this.logTime = logTime;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	
	
	
}
