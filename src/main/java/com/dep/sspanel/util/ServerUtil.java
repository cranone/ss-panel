package com.dep.sspanel.util;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.support.RequestContext;

/**
 * 工具类
 * 
 * @author Administrator
 *
 */
public abstract class ServerUtil {
	private static final Logger logger = LoggerFactory.getLogger(ServerUtil.class);
	private static Properties property;

	/**
	 * 读取global.property文件
	 * 
	 * @param name
	 *            键名
	 * @return 键名对应的值
	 * @throws IOException
	 */
	public static String loadProperty(String name) {
		if (property == null) {
			property = new Properties();
			try {
				property.load(ServerUtil.class.getClassLoader().getResourceAsStream("global.properties"));
			} catch (IOException e) {
				logger.error(e.getMessage());
				return null;
			}
		}
		String str = property.getProperty(name);
		return str == null ? "" : str.trim();
	}

	/**
	 * 获取服务的url基本地址
	 * 
	 * @param request
	 * @return URL
	 */
	public static String getServerPath(HttpServletRequest request) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		return basePath;
	}

	/**
	 * 获取带目录的url地址
	 * 
	 * @param request
	 * @param directory
	 * @return URL
	 */
	public static String getServerPath(HttpServletRequest request, String directory) {
		String path = request.getContextPath();
		String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
		return basePath + "/" + directory;
	}

	/**
	 * 获取服务器的根路径
	 * 
	 * @param request
	 * @param directory
	 * @return path
	 */
	public static String getServerContextPath(HttpServletRequest request) {
		String path = request.getContextPath();
		return path;
	}

	/**
	 * 正则判断是否被包含
	 * 
	 * @param value
	 *            String 需要判断的数据
	 * @param values
	 *            List<String> 目标数据(规则列表)
	 * @return true/false
	 */
	public static boolean isInclude(String value, List<String> values) {
		for (String regex : values) {
			if (value.matches(regex)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 正则判断是否被包含
	 * 
	 * @param value
	 *            String 需要判断的数据
	 * @param values
	 *            String[] 目标数据(规则列表)
	 * @return true/false
	 */
	public static boolean isInclude(String value, String[] values) {
		for (String regex : values) {
			if (value.matches(regex)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 获取国际化信息
	 * 
	 * @param request
	 * @param key
	 * @return
	 */
	public static String i18n(HttpServletRequest request, String key) {
		RequestContext requestContext = new RequestContext(request);
		return requestContext.getMessage(key);
	}
	
	/**
	 * 获取国际化信息注入参数
	 * @param request
	 * @param key
	 * @param objects
	 * @return
	 */
	public static String i18n(HttpServletRequest request, String key,Object[] objects) {
		RequestContext requestContext = new RequestContext(request);
		return requestContext.getMessage(key,objects);
	}

	/**
	 * 获取IP
	 * 
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}
}
