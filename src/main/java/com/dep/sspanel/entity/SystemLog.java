package com.dep.sspanel.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.servlet.http.HttpServletRequest;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.dep.sspanel.util.ServerUtil;

@Entity
@DynamicUpdate
public class SystemLog {
	private String id;
	private String operator;
	private String description;
	private String operate;
	private String ip;
	private String mark;
	
	public SystemLog() {
	}
	
	public SystemLog(HttpServletRequest request) {
		this.ip=ServerUtil.getIpAddr(request);
	}
	
	/**
	 * 
	 * @param request
	 * @param description 描述
	 */
	public SystemLog(HttpServletRequest request,String description) {
		this.description=description;
		this.ip=ServerUtil.getIpAddr(request);
	}
	
	/**
	 * 
	 * @param request
	 * @param description 描述
	 * @param operator 操作人
	 * @param operate 操作内容
	 */
	public SystemLog(HttpServletRequest request,String description,String operator,String operate) {
		this.operator=operator;
		this.description=description;
		this.operate=operate;
		this.ip=ServerUtil.getIpAddr(request);
	}
	
	/**
	 * ID
	 * @return
	 */
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 36,nullable=false)
	public String getId() {
		return id;
	}
	/**
	 * 操作人
	 * @return
	 */
	@Column(length=32)
	public String getOperator() {
		return operator;
	}
	/**
	 * 描述
	 * @return
	 */
	@Column(nullable=true)
	public String getDescription() {
		return description;
	}
	
	/**
	 * 操作内容
	 * @return
	 */
	@Column
	public String getOperate() {
		return operate;
	}
	
	/**
	 * IP
	 * @return
	 */
	@Column
	public String getIp() {
		return ip;
	}
	
	/**
	 * 备注
	 * @return
	 */
	@Column
	public String getMark() {
		return mark;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setOperator(String operator) {
		this.operator = operator;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setOperate(String operate) {
		this.operate = operate;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	
	
	
}
