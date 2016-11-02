package com.dep.sspanel.shiro;

import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.Resource;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.springframework.stereotype.Component;

import com.dep.sspanel.util.GlobalConst;

/**
 * 加密安全类
 * @author Maclaine
 *
 */
@Component
public class SecurityUtil {
	private static String algorithmName="md5";
	private static String salt1=GlobalConst.salt;
	private static int hashIterations = 2;
	private static CacheManager cacheManager;
	private static Cache<String, AtomicInteger> passwordRetryCache;
	
	@SuppressWarnings("static-access")
	@Resource
	public void setCacheManager(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}
	
	public static Cache<String, AtomicInteger> getPasswordRetryCache() {
		if(passwordRetryCache==null){
			passwordRetryCache=cacheManager.getCache("passwordRetryCache");
		}
		return passwordRetryCache;
	}

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
	
	/**
	 * 判断是否超过登录限制,如果未超过则自增
	 * @param username
	 * @return
	 */
	public static boolean retryLimit(String username){
		AtomicInteger retryCount = getPasswordRetryCache().get(username);
        if(retryCount == null) {
            retryCount = new AtomicInteger(0);
            passwordRetryCache.put(username, retryCount);
        }
        if(retryCount.get() >= GlobalConst.retryTime) {
        	return true;
        }
        retryCount.incrementAndGet();
        return false;
	}
	
	/**
	 * 获取登录次数
	 * @param username
	 * @return 如果用户名无缓存则返回0
	 */
	public static Integer getRetyLimit(String username){
		AtomicInteger retryCount = getPasswordRetryCache().get(username);
		if(retryCount == null) {
			return 0;	
		}
		return retryCount.get();
	}
	
	/**
	 * 获取剩余登录次数
	 * @param username
	 * @return 如果用户名无缓存则返回最大次数
	 */
	public static Integer getRetyLimitRest(String username){
		AtomicInteger retryCount = getPasswordRetryCache().get(username);
		if(retryCount == null) {
			return GlobalConst.retryTime;
		}
		return GlobalConst.retryTime-retryCount.get();
	}
}
