package com.dep.sspanel.util.type;

/**
 * 异常信息
 * @author Maclaine
 *
 */
public enum ErrorCodeType {
	success("0","SUCCESS","成功"),
	/** GenericDao ID为NULL */
	id_null("4001","GDI_ID_NULL","ID为null"),
	/** 参数为NULL */
	param_null("4002","PARAM_NULL","参数为null"),
	/** 数据异常 */
	data_error("5001","DATA_ERROR","数据异常"),
	/** 未知异常 */
	unknown_error("9999","UNKNOWN_ERROR","发生错误");
	
	private String code;
	private String error;
	private String msg;
	
	public String getCode() {
		return code;
	}

	public String getError() {
		return error;
	}

	public String getMsg() {
		return msg;
	}

	private ErrorCodeType(){};
	
	private ErrorCodeType(String code,String error,String msg){
		this.code=code;
		this.error=error;
		this.msg=msg;
	};
	
	@Override
	public String toString() {
		return "ErrorCode:"+this.code+",error:"+this.error+",message:"+this.msg;
	}
}
