package com.dep.sspanel.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * 权限
 * @author Maclaine
 *
 */
public class Permission {
	private Integer id;
	private String name;
	private Role role;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	
	@Column
	public String getName() {
		return name;
	}
	
	@ManyToOne
	public Role getRole() {
		return role;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
