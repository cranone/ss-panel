package com.dep.sspanel.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 权限
 * @author Maclaine
 *
 */
@Entity(name="permission")
@DynamicUpdate
public class Permission implements Serializable {
	private String id;
	private String name;
	private List<Role> roleList;
	
	@Id
	@GenericGenerator(name = "system-uuid", strategy = "uuid2")
	@GeneratedValue(generator = "system-uuid")
	@Column(length = 36,nullable=false)
	public String getId() {
		return id;
	}
	
	@Column
	public String getName() {
		return name;
	}
	
	@ManyToMany(mappedBy="permissionList")
	public List<Role> getRoleList() {
		return roleList;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	
}
