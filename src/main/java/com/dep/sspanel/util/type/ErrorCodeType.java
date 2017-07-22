package com.dep.sspanel.util.type;

/**
 * 异常信息
 * @author Maclaine
 *
 */
public enum ErrorCodeType {
	success("200","SUCCESS","success"),
	/** 404 */
	no_handler_found("404","NO_HANDLER_FOUND","errorcode.not.found"),
	/** 4001:GenericDao ID为NULL */
	id_null("4001","GDI_ID_NULL","errorcode.id.empty"),
	/** 参数为NULL */
	param_null("4002","PARAM_NULL","errorcode.param.empty"),
	/** 5001:数据异常 */
	data_error("5001","DATA_ERROR","errorcode.data.error"),
	/** 9999:未知异常 */
	unknown_error("9999","UNKNOWN_ERROR","errorcode.unknown.error");
	
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
