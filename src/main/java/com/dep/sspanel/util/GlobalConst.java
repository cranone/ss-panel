package com.dep.sspanel.util;

import org.apache.shiro.util.StringUtils;

public class GlobalConst {
	public static String encoding = "UTF-8";
	public static String salt="";
	public static Integer retryTime=5;
	public static String resourceURL=null;
	
	static{
		encoding=setValue(encoding, "encoding");
		salt=setValue(salt, "salt");
		retryTime=setValue(retryTime, "retryTime");
		resourceURL=setValue(resourceURL, "resourceURL");
	}
	
	private static String setValue(String param,String value){
		String str=ServerUtil.loadProperty(value);
		if(StringUtils.hasText(str)){
			return str;
		}
		return param;
	}
	
	private static Integer setValue(Integer param,String value){
		String str=ServerUtil.loadProperty(value);
		if(StringUtils.hasText(str)){
			return Integer.parseInt(str);
		}
		return param;
	}
}
