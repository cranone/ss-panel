package com.dep.sspanel.interceptor;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.dep.sspanel.util.ServerUtil;

public class GlobalInterceptor implements HandlerInterceptor {
	private static final Logger logger = LoggerFactory.getLogger(GlobalInterceptor.class);
	private static String encoding = null;
	/**
	 * 排除过滤资源文件,以顶级路径区分
	 */
	private static List<String> excludeResource;
	/**
	 * 排除过滤无需登录URL,以顶级路径区分
	 */
	private static List<String> excludeURL;

	static {
		if (ServerUtil.loadProperty("encoding") == null) {
			logger.error(encoding);
			encoding = "UTF-8";
		}
		encoding = ServerUtil.loadProperty("encoding");
		logger.info("encoding:{}", encoding);
		// 初始化排除过滤资源文件
		excludeResource = new ArrayList<String>();
		excludeResource.add("^/resource(.*)");// 如果有Context-root,则为^/Context-root/resource(.*),下同
		logger.info("excludeResource初始化完毕");

		// 初始化排除过滤无需登录URL
		excludeURL = new ArrayList<String>();
		excludeURL.add("^/index(.*)");
		excludeURL.add("^/$");
		excludeURL.add("^/error(.*)");
		logger.info("excludeURL初始化完毕");
	}

	/**
	 * preHandle方法是进行处理器拦截用的，顾名思义，该方法将在Controller处理之前进行调用，
	 * SpringMVC中的Interceptor拦截器是链式的，可以同时存在
	 * 多个Interceptor，然后SpringMVC会根据声明的前后顺序一个接一个的执行
	 * ，而且所有的Interceptor中的preHandle方法都会在
	 * Controller方法调用之前调用。SpringMVC的这种Interceptor链式结构也是可以进行中断的
	 * ，这种中断方式是令preHandle的返 回值为false，当preHandle的返回值为false的时候整个请求就结束了。
	 */
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		request.setCharacterEncoding(encoding);
		response.setCharacterEncoding(encoding);
		// logger.debug(ServerUtil.getServerPath(request));
		request.setAttribute("globalURL", ServerUtil.getServerPath(request));
		
		String URI=request.getServletPath();
		String globalURL = ServerUtil.getServerPath(request);
		request.setAttribute("globalURL", globalURL);
		logger.debug("path:{}{}",globalURL,URI);
		// 是否排除过滤登录
		if (ServerUtil.isInclude(URI,excludeURL)||ServerUtil.isInclude(URI,excludeResource)) {
			logger.debug("{}:无需登录",URI);
			return true;
		}
		//TODO:登录验证
		logger.debug("登录验证");
		return true;
	}

	/**
	 * 这个方法只会在当前这个Interceptor的preHandle方法返回值为true的时候才会执行。postHandle是进行处理器拦截用的，
	 * 它的执行时间是在处理器进行处理之
	 * 后，也就是在Controller的方法调用之后执行，但是它会在DispatcherServlet进行视图的渲染之前执行
	 * ，也就是说在这个方法中你可以对ModelAndView进行操
	 * 作。这个方法的链式结构跟正常访问的方向是相反的，也就是说先声明的Interceptor拦截器该方法反而会后调用
	 * ，这跟Struts2里面的拦截器的执行过程有点像，
	 * 只是Struts2里面的intercept方法中要手动的调用ActionInvocation的invoke方法
	 * ，Struts2中调用ActionInvocation的invoke方法就是调用下一个Interceptor
	 * 或者是调用action，然后要在Interceptor之前调用的内容都写在调用invoke之前
	 * ，要在Interceptor之后调用的内容都写在调用invoke方法之后。
	 */
	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * 该方法也是需要当前对应的Interceptor的preHandle方法的返回值为true时才会执行。该方法将在整个请求完成之后，
	 * 也就是DispatcherServlet渲染了视图执行，
	 * 这个方法的主要作用是用于清理资源的，当然这个方法也只能在当前这个Interceptor的preHandle方法的返回值为true时才会执行。
	 */
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub

	}
}
