package com.dep.sspanel.control;

public class URIConstants {
	/** 默认页面 */
	public static final String DEFAULT="/";
	/** 默认页面 */
	public static final String INDEX="/index";
	/** 登录 */
	public static final String GET_LOGIN=INDEX+"/login";
	/** 注册 */
	public static final String GET_REGISTER=INDEX+"/register";
	/** 执行登录 */
	public static final String POST_LOGIN=INDEX+"/dologin";
	/** 执行登出 */
	public static final String LOGOUT=INDEX+"/logout";
	/** 执行注册 */
	public static final String POST_REGISTER=INDEX+"/doregister";
	/** 转换语言 */
	public static final String LANGUAGE=INDEX+"/language";
	
	/** 验证码 */
	public static final String CAPTCHA="images/captcha.jpg";
	
	
	/** 管理员首页 */
	public static final String ADMIN_INDEX="/admin";
	/** 管理员首页 */
	public static final String ADMIN_DEFAULT=ADMIN_INDEX+"/index";
	
	/** 用户首页 */
	public static final String USER_INDEX="/user";
	/** 用户首页 */
	public static final String USER_DEFAULT=USER_INDEX+"/index";
	/** 用户节点 */
	public static final String USER_NODE=USER_INDEX+"/node";
	/** 用户消息 */
	public static final String USER_MESSAGE=USER_INDEX+"/message";
	/** 用户消息 */
	public static final String USER_SECURITY=USER_INDEX+"/security";
	
	
}
