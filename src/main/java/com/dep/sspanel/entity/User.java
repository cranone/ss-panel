package com.dep.sspanel.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.dep.sspanel.util.type.UserType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用户
 * @author Maclaine
 *
 */
@Entity
@Table(name="user")
@DynamicUpdate
@JsonIgnoreProperties(value={"hibernateLazyInitializer", "handler","pass","roleList","rolePermissionSet","roleNameSet"})   
public class User implements Serializable{
	private static final long serialVersionUID = 1228256212143751728L;
	
	private String id;//*
	private String username;//用户名
	private String email;//用户邮箱
	private String pass;//用户密码
	private String passwd;//ss密码*
	private Integer time=0;//最后使用的时间*
	private Long upload=0L;//已上传流量*
	private Long download=0L;//已下载流量*
	private Long transferEnable=0L;//可用流量（总量）*
	private Integer port=50000;//ss端口*
	private Boolean enable=true;//启用或禁用ss帐号（1启用，0禁用）*
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8") 
	private Date updateDate=Calendar.getInstance().getTime();//修改日期
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern="yyyy-MM-dd",timezone="GMT+8")
	private Date expiresDate;//到期时间
	private String method;//="aes-256-cfb"加密*
	private String obfs;//="tls1.2_ticket_auth_compatible"混淆*
	private String protocol;//="auth_sha1_v4_compatible"协议*
	private Integer status=UserType.unactive.getId();//用户状态
	private List<Role> roleList;
	
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 36,nullable=false)
	public String getId() {
		return id;
	}
	/**
	 * 用户名
	 * @return
	 */
	@Column(name="user_name",length=32,nullable=false,unique=true)
	public String getUsername() {
		return username;
	}
	/**
	 * 用户邮箱
	 * @return
	 */
	@Column(name="email",length=32,nullable=false,unique=true)
	public String getEmail() {
		return email;
	}
	/**
	 * 用户密码
	 * @return
	 */
	@Column(name="pass",length=32,nullable=false)
	public String getPass() {
		return pass;
	}
	/**
	 * ss密码
	 * @return
	 */
	@Column(name="passwd",length=16,nullable=false)
	public String getPasswd() {
		return passwd;
	}
	@Column(name="t",nullable=false)
	public Integer getTime() {
		return time;
	}
	@Column(name="u",nullable=false)
	public Long getUpload() {
		return upload;
	}
	@Column(name="d",nullable=false)
	public Long getDownload() {
		return download;
	}
	@Column(name="transfer_enable",nullable=false)
	public Long getTransferEnable() {
		return transferEnable;
	}
	@Column(name="port",nullable=false,unique=true)
	public Integer getPort() {
		return port;
	}
	@Column(name="enable",nullable=false)
	public Boolean getEnable() {
		return enable;
	}
	@Column
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 用户状态
	 * @return
	 */
	@Column(nullable=false)
	public Integer getStatus() {
		return status;
	}
	
	@ManyToMany
	@Cascade(value=CascadeType.SAVE_UPDATE)
	@JoinTable(name="auth_user_role",joinColumns={@JoinColumn(name="uid")},inverseJoinColumns={@JoinColumn(name="rid")})
	public List<Role> getRoleList() {
		return roleList;
	}
	
	/**
	 * 过期时间
	 * @return
	 */
	@Column
	public Date getExpiresDate() {
		return expiresDate;
	}
	/**
	 * 加密
	 * @return
	 */
	@Column(length=64)
	public String getMethod() {
		return method;
	}
	/**
	 * 混淆
	 * @return
	 */
	@Column(length=64)
	public String getObfs() {
		return obfs;
	}
	/**
	 * 协议
	 * @return
	 */
	@Column(length=64)
	public String getProtocol() {
		return protocol;
	}
	
	/**
	 * 是否过期
	 * @return true:过期;false:未过期
	 */
	@Transient
	public boolean isTimeout(){
		if(expiresDate==null){
			return true;
		}
		Calendar cal=Calendar.getInstance();
		cal.setTime(expiresDate);
		if(cal.getTimeInMillis()<Calendar.getInstance().getTimeInMillis()){
			return true;
		}
		return false;
	}
	
	/**
	 * 获取角色名集合
	 * @return Set<String>;不存在则返回Collections.emptySet()
	 */
	@Transient
	public Set<String> getRoleNameSet(){
		if(CollectionUtils.isEmpty(roleList)){
			return Collections.emptySet();
		}
		Set<String> set=new HashSet<String>();
		for (Role role : roleList) {
			set.add(role.getName());
		}
		return set;
	}
	
	/**
	 * 获取权限名集合
	 * @return Set<String>;不存在则返回Collections.emptySet()
	 */
	@Transient
	public Set<String> getRolePermissionSet(){
		if(CollectionUtils.isEmpty(roleList)){
			return Collections.emptySet();
		}
		Set<String> set=Collections.emptySet();
		for (Role role : roleList) {
			List<Permission> list=role.getPermissionList();
			if(CollectionUtils.isEmpty(list)){
				continue;
			}
			for (Permission permission : list) {
				if(CollectionUtils.isEmpty(set)){
					set=new HashSet<String>();
				}
				set.add(permission.getName());
			}
		}
		return set;
	}
	
	
	
	public void setId(String id) {
		this.id = id;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}
	public void setTime(Integer time) {
		this.time = time;
	}
	public void setUpload(Long upload) {
		this.upload = upload;
	}
	public void setDownload(Long download) {
		this.download = download;
	}
	public void setTransferEnable(Long transferEnable) {
		this.transferEnable = transferEnable;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public void setExpiresDate(Date expiresDate) {
		this.expiresDate = expiresDate;
	}
	public void setMethod(String method) {
		this.method = method;
	}
	public void setObfs(String obfs) {
		this.obfs = obfs;
	}
	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}
}
