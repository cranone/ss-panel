package com.dep.sspanel.util;

import org.apache.shiro.util.StringUtils;

public class GlobalConst {
	public static final String encoding;
	public static final String salt;
	public static final Integer retryTime;
	public static final String resourceURL;

	static {
		encoding = setValue("UTF-8", "encoding");
		salt = setValue("", "salt");
		retryTime = setValue(5, "retryTime");
		resourceURL = setValue("", "resourceURL");
	}

	private static String setValue(String param, String value) {
		String str = ServerUtil.loadProperty(value);
		if (StringUtils.hasText(str)) {
			return str;
		}
		return param;
	}

	private static Integer setValue(Integer param, String value) {
		String str = ServerUtil.loadProperty(value);
		if (StringUtils.hasText(str)) {
			return Integer.parseInt(str);
		}
		return param;
	}
}
