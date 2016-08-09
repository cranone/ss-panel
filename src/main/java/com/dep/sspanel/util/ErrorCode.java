package com.dep.sspanel.util;

/**
 * 异常信息
 * @author Administrator
 *
 */
public class ErrorCode {
	/** GenericDao ID为NULL Code */
	public final static String CODE_GDI_ID_NULL="4001";
	/** GenericDao ID为NULL String */
	public final static String MSG_GDI_ID_NULL="ID为null";
	/** GenericDao ID为NULL 国际化数值 */
	public final static String KEY_GDI_ID_NULL="errorcode.id.empty";
	
	/** 参数为NULL Code */
	public final static String CODE_PARAM_NULL="4002";
	/** 参数为NULL String */
	public final static String MSG_PARAM_NULL="参数为null";
	/** 参数为NULL 国际化数值 */
	public final static String KEY_PARAM_NULL="errorcode.param.empty";
	
	/** 数据异常 Code */
	public final static String CODE_DATA_ERROR="5001";
	/** 数据异常 String */
	public final static String MSG_DATA_ERROR="数据异常";
	/** 数据异常 国际化数值 */
	public final static String KEY_DATA_ERROR="errorcode.data.error";
	
	/** 未知异常 Code */
	public final static String CODE_UNKNOWN_ERROR="9999";
	/** 未知异常 String */
	public final static String MSG_UNKNOWN_ERROR="发生错误";
	/** 未知异常 国际化数值 */
	public final static String KEY_UNKNOWN_ERROR="errorcode.unknown.error";
}
