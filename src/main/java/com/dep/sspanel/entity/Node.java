package com.dep.sspanel.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 节点
 * @author Maclaine
 *
 */
@Entity(name="ss_node")
@DynamicUpdate
public class Node  implements Serializable{
	private static final long serialVersionUID = -5627245449524084003L;
	
	private Integer id;//*
	private String name;//节点名*
	private String addr;//节点地址
	private String info;//描述
	private Double trafficRate=1.0;//倍率*
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column
	public String getName() {
		return name;
	}
	@Column(name="address")
	public String getAddr() {
		return addr;
	}
	@Column(name="info")
	public String getInfo() {
		return info;
	}
	@Column(name="traffic_rate")
	public Double getTrafficRate() {
		return trafficRate;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setAddr(String addr) {
		this.addr = addr;
	}
	public void setInfo(String info) {
		this.info = info;
	}
	public void setTrafficRate(Double trafficRate) {
		this.trafficRate = trafficRate;
	}
	
	
}
