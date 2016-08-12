package com.dep.sspanel.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.DynamicUpdate;

/**
 * 权限
 * @author Maclaine
 *
 */
@Entity(name="permission")
@DynamicUpdate
public class Permission implements Serializable {
	private Integer id;
	private String name;
	private List<Role> roleList;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
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
	
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}
	
	
}
