package com.dep.sspanel.exception;

public class GenericException extends Exception {
	private static final long serialVersionUID = 1L;
	
	private String errorCode="Unknown_Exception";
	private String i18nCode="errorcode.unknown.error";
	
	public GenericException(String message){
		super(message);
	}
	
	public GenericException(String message,String errorCode){
		super(message);
		this.errorCode=errorCode;
	}
	
	public GenericException(String message,String errorCode,String i18nCode){
		super(message);
		this.errorCode=errorCode;
		this.i18nCode=i18nCode;
	}
	
	public String getErrorCode(){
		return this.errorCode;
	}
	
	public String getI18nCode() {
		return i18nCode;
	}
}
