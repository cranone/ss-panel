package com.dep.sspanel.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicUpdate;

import com.dep.sspanel.util.type.CodeType;

/**
 * 
 * 激活码
 * @author Maclaine E-mail:deathencyclopedia@gmail.com
 * 
 */
@Entity
@Table(name="code")
@DynamicUpdate
public class Code {

	private Integer id;
	private String code;
	private Date createDate;//创建时间
	private Date consumeDate;//使用时间
	private CodeType codeType;//激活码类型
	private Integer amount;//量,流量为G,时间为月
	private User consumer;//使用者
	private User creater;//创建者
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Integer getId() {
		return id;
	}
	@Column(nullable=false,unique=true)
	public String getCode() {
		return code;
	}
	@Column
	public Date getCreateDate() {
		return createDate;
	}
	@Column
	public Date getConsumeDate() {
		return consumeDate;
	}
	@Column  
    @Enumerated(EnumType.STRING)
	public CodeType getCodeType() {
		return codeType;
	}
	@Column
	public Integer getAmount() {
		return amount;
	}
	@ManyToOne
	@JoinColumn(name="consumer_id")
	public User getConsumer() {
		return consumer;
	}
	@ManyToOne
	@JoinColumn(name="creater_id")
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
