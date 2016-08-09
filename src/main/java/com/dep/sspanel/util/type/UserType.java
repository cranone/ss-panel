package com.dep.sspanel.util.type;

/**
 * 用户状态
 * @author Maclaine
 *
 */
public enum UserType {
	/**
	 * 未激活
	 */
	unactive(0,"未激活"),
	/**
	 * 普通会员
	 */
	normal(1,"普通会员"),
	/**
	 * 停用
	 */
	disable(2,"停用"),
	/**
	 * 管理员
	 */
	admin(10,"管理员")
	;
	
	private int id;
	private String name;
	
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	
	private UserType() {}
	private UserType(int id,String name){
		this.id=id;
		this.name=name;
	}
}
