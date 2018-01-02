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
 * 
 * @author Maclaine
 *
 */
@Entity(name = "permission")
@DynamicUpdate
public class Permission implements Serializable {
    private static final long serialVersionUID = 3838923776523548913L;
    @Id
    @GenericGenerator(name = "system-uuid", strategy = "uuid2")
    @GeneratedValue(generator = "system-uuid")
    @Column(length = 36, nullable = false)
    private String id;
    @Column
    private String name;
    @ManyToMany(mappedBy = "permissionList")
    private List<Role> roleList;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

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
