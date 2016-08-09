package com.dep.sspanel.exception;

import com.dep.sspanel.util.type.ErrorCodeType;


/**
 * Dao层异常
 * 继承RuntimeException<br>
 * 建议:所有异常在最上层捕获,否则使用GenericException(继承Exception)
 * @author Administrator
 *
 */
public class SystemException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	private ErrorCodeType errorCodeType=ErrorCodeType.unknown_error;
	
	public SystemException(String message){
		super(message);
	}
	
	public SystemException(ErrorCodeType errorCodeType){
		super(errorCodeType.getMsg());
		this.setErrorCodeType(errorCodeType);
	}
	

	public ErrorCodeType getErrorCodeType() {
		return errorCodeType;
	}

	public void setErrorCodeType(ErrorCodeType errorCodeType) {
		this.errorCodeType = errorCodeType;
	}
}
