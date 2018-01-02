package com.dep.sspanel.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;
import org.springframework.format.annotation.DateTimeFormat;

import com.dep.sspanel.util.type.CodeType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * 
 * 激活码
 * @author Maclaine E-mail:deathencyclopedia@gmail.com
 * 
 */
@Entity
@Table(name="code")
@DynamicUpdate
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(Include.NON_NULL)
public class Code implements Serializable {
    private static final long serialVersionUID = -1752957614908088903L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
    @Column(nullable=false,unique=true)
	private String code;
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone="GMT+8")  
    @Column
	private Date createDate;//创建时间
	@DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
	@JsonFormat(pattern="yyyy-MM-dd hh:mm:ss",timezone="GMT+8")  
    @Column
	private Date consumeDate;//使用时间
    @Column
    @Enumerated(EnumType.STRING)
	private CodeType codeType;//激活码类型
    @Column
	private Integer amount;//量,流量为G,时间为月
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="consumer_id")
	private User consumer;//使用者
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="creater_id")
	private User creater;//创建者
	
	public Integer getId() {
		return id;
	}
	public String getCode() {
		return code;
	}
	public Date getCreateDate() {
		return createDate;
	}
	public Date getConsumeDate() {
		return consumeDate;
	}
	public CodeType getCodeType() {
		return codeType;
	}
	public Integer getAmount() {
		return amount;
	}
	public User getConsumer() {
		return consumer;
	}
	public User getCreater() {
		return creater;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	public void setConsumeDate(Date consumeDate) {
		this.consumeDate = consumeDate;
	}
	public void setCodeType(CodeType codeType) {
		this.codeType = codeType;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}
	public void setConsumer(User consumer) {
		this.consumer = consumer;
	}
	public void setCreater(User creater) {
		this.creater = creater;
	}
	
	
}
