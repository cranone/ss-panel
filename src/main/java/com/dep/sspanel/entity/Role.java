package com.dep.sspanel.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.apache.commons.collections.CollectionUtils;
import org.hibernate.annotations.DynamicUpdate;
import org.hibernate.annotations.GenericGenerator;

/**
 * 角色
 * @author Maclaine
 *
 */
@Entity(name="role")
@DynamicUpdate
public class Role implements Serializable { 
    private static final long serialVersionUID = 1812381120879135163L;
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 36,nullable=false)
	private String id;
    @Column
	private String name;
    @ManyToMany
    @JoinTable(name="auth_permission_role",joinColumns={@JoinColumn(name="rid")},inverseJoinColumns={@JoinColumn(name="pid")})
	private List<Permission> permissionList;
    @ManyToMany(mappedBy="roleList") 
	private List<User> userList;

	public String getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public List<Permission> getPermissionList() {
		return permissionList;
	}
	public List<User> getUserList() {
		return userList;
	}
	public void setId(String id) {
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
	
	@Transient
	public String getPermissionNameStr(){
		List<Permission> perlist=getPermissionList();
		if(CollectionUtils.isEmpty(perlist)){
			return "";
		}
		StringBuffer sb=new StringBuffer();
		for (Permission permission : perlist) {
			sb.append(permission.getName());
			sb.append(",");
		}
		return sb.toString();
	}
	
}
