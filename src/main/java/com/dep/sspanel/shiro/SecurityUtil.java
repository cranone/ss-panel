package com.dep.sspanel.shiro;

import org.apache.shiro.crypto.hash.SimpleHash;

import com.dep.sspanel.util.GlobalConst;

/**
 * 加密安全类
 * @author Maclaine
 *
 */
public class SecurityUtil {
	private static String algorithmName="md5";
	private static String salt1=GlobalConst.salt;
	private static int hashIterations = 2;  

	/**
	 * 将明文密码加密
	 * @param username
	 * @param password
	 * @return
	 */
	public static String encrypt(String username,String password){
		SimpleHash hash = new SimpleHash(algorithmName, password,username+salt1 , hashIterations);
		return hash.toHex();
	}
}
