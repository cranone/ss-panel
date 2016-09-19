package com.dep.sspanel.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
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
	private String operateContent;
	private String ip;
	private String mark;
	private Date date;
	private String stackTrace;
	
	public SystemLog() {
		this.date=new Date();
	}
	
	public SystemLog(HttpServletRequest request) {
		this.ip=ServerUtil.getIpAddr(request);
		this.date=new Date();
	}
	
	/**
	 * 
	 * @param request
	 * @param description 描述
	 */
	public SystemLog(HttpServletRequest request,String description) {
		this.description=description;
		this.ip=ServerUtil.getIpAddr(request);
		this.date=new Date();
	}
	
	/**
	 * 
	 * @param request
	 * @param description 描述
	 * @param operateContent 操作人
	 * @param operate 操作内容
	 */
	public SystemLog(HttpServletRequest request,String description,String operator,String operateContent) {
		this.operator=operator;
		this.description=description;
		this.operateContent=operateContent;
		this.ip=ServerUtil.getIpAddr(request);
		this.date=new Date();
	}
	
	/**
	 * 
	 * @param request
	 * @param description 描述
	 * @param operator 操作人
	 * @param operateContent 操作内容
	 * @param stackTrace 触发层级
	 */
	public SystemLog(HttpServletRequest request,String description,String operator,String operateContent,String stackTrace) {
		this.operator=operator;
		this.description=description;
		this.operateContent=operateContent;
		this.ip=ServerUtil.getIpAddr(request);
		this.stackTrace=stackTrace;
		this.date=new Date();
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
	@Column(length=5000)
	public String getOperateContent() {
		return operateContent;
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
	
	/**
	 * 创建日期
	 * @return
	 */
	@Column(name="create_date")
	public Date getDate() {
		return date;
	}
	
	/**
	 * 触发层级
	 * @return
	 */
	@Lob
	@Basic(fetch = FetchType.LAZY)
	@Column
	public String getStackTrace() {
		return stackTrace;
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
	public void setOperateContent(String operateContent) {
		this.operateContent = operateContent;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public void setMark(String mark) {
		this.mark = mark;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	
	
	
}
