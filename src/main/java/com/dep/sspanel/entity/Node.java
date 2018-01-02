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
public class Node implements Serializable{
	private static final long serialVersionUID = -5627245449524084003L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;//*
    @Column
	private String name;//节点名*
    @Column(name="address")
	private String addr;//节点地址
    @Column(name="info")
	private String info;//描述
    @Column(name="traffic_rate")
	private Double trafficRate;//倍率*
	
	public Integer getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddr() {
		return addr;
	}
	public String getInfo() {
		return info;
	}
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
