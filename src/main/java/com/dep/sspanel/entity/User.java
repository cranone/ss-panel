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
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

import com.dep.sspanel.util.type.UserType;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 用户
 * @author Maclaine
 *
 */
@Entity(name="user")
@DynamicUpdate
@JsonIgnoreProperties(value={"pass","roleList","rolePermissionSet","roleNameSet"})   
public class User implements Serializable{
	private static final long serialVersionUID = 1228256212143751728L;
	
	private String id;
	private String username;//用户名
	private String email;//用户邮箱
	private String pass;//用户密码
	private String passwd;//ss密码
	private Integer time;//最后使用的时间
	private Long upload;//已上传流量
	private Long download;//已下载流量
	private Long transferEnable;//可用流量（总量）
	private Integer port=50000;//ss端口
	//private byte switchs=0;//保留字段
	private Boolean enable=true;//启用或禁用ss帐号（1启用，0禁用）
	//private byte type=0;//保留字段
	//private int lastGetGiftTime=0;//保留字段
	//private int lastRestPassTime=0;//保留字段
	private Date updateDate=Calendar.getInstance().getTime();//修改日期
	//private Integer cycleType=2;//周期类型:6=日,2=月,1=年
	//private Integer expires=0;//有效时间
	private Date expiresDate;//到期时间
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
	/*@Column(name="switch",nullable=false)
	public byte getSwitchs() {
		return switchs;
	}*/
	@Column(name="enable",nullable=false)
	public Boolean getEnable() {
		return enable;
	}
/*	@Column(name="type",nullable=false)
	public byte getType() {
		return type;
	}
	@Column(name="last_get_gift_time",nullable=false)
	public int getLastGetGiftTime() {
		return lastGetGiftTime;
	}
	@Column(name="last_rest_pass_time",nullable=false)
	public int getLastRestPassTime() {
		return lastRestPassTime;
	}*/
	@Column
	public Date getUpdateDate() {
		return updateDate;
	}
	/**
	 * 周期类型:6=日,2=月,1=年
	 * @return
	 */
/*	@Column(nullable=false)
	public Integer getCycleType() {
		return cycleType;
	}*/
	/**
	 * 有效期
	 * @return
	 */
/*	@Column
	public Integer getExpires() {
		return expires;
	}*/
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
/*	public void setSwitchs(byte switchs) {
		this.switchs = switchs;
	}*/
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
/*	public void setType(byte type) {
		this.type = type;
	}
	public void setLastGetGiftTime(int lastGetGiftTime) {
		this.lastGetGiftTime = lastGetGiftTime;
	}
	public void setLastRestPassTime(int lastRestPassTime) {
		this.lastRestPassTime = lastRestPassTime;
	}*/
	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
/*	public void setCycleType(Integer cycleType) {
		this.cycleType = cycleType;
	}*/
/*	public void setExpires(Integer expires) {
		this.expires = expires;
	}*/
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
	
	
}
