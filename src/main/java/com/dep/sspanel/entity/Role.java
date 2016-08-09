package com.dep.sspanel.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

/**
 * 角色
 * @author Maclaine
 *
 */
public class Role {
	private Integer id;
	private String name;
	private List<Permission> permissionList;
	private List<User> userList;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	public Integer getId() {
		return id;
	}
	@Column(name="permissionName")
	public String getName() {
		return name;
	}
    @OneToMany(mappedBy="role")  
	public List<Permission> getPermissionList() {
		return permissionList;
	}
	@ManyToMany  
	public List<User> getUserList() {
		return userList;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setPermissionList(List<Permission> permissionList) {
		this.permissionList = permissionList;
	}
	public void setUserList(List<User> userList) {
		this.userList = userList;
	}
	
	@Transient
	public List<String> getPermissionName(){
		List<String> list=new ArrayList<String>();
		List<Permission> perlist=getPermissionList();
		if(perlist==null){
			return list;
		}
		for (Permission permission : perlist) {
			list.add(permission.getName());
		}
		return list;
	}
	
}
