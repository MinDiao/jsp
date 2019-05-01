package com.zw.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 *	创建过滤器:实现Filter接口, 实现方法:init, doFilter, destory
 *	过滤器也是单实例, 也就是只创建一次
 *	创建过滤器时机:当服务器加载web应用时,就创建实例,调用init方法
 */
public class OneFilter implements Filter {
	public OneFilter() {
		System.out.println("Constructor");
	}
	
	/**
	 * 	执行注销的条件:
	 *	1.当web服务器卸载web应用时
	 *	2.停止服务器时
	 *	3.当重新加载web应用时
	 */
	@Override
	public void destroy() {
		System.out.println("destroy");
	}

	/**
	 * 每次请求都会调用doFilter方法
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("1.oneFilter开始过滤requestis"+ request);
		// 放行
		chain.doFilter(request, response);
		response.getWriter().write("hello");
		System.out.println("4.oneFilter过滤结束");
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		System.out.println("init");
	}
}
