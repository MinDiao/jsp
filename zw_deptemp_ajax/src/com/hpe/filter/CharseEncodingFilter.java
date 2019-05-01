package com.hpe.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 拦截所有资源
/*@WebFilter("/*")*/

// 新方法,
@WebFilter(filterName = "CharsetEncodingFilter", urlPatterns={"/*"})
public class CharseEncodingFilter implements Filter {

   
	public void destroy() {
		
	}

	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// 先进行强转
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		// 设置编码格式
		req.setCharacterEncoding("utf-8");
		//res.setCharacterEncoding("utf-8");
		res.setContentType("application/json;charset=utf-8");
		chain.doFilter(req, res);
	}

	
	public void init(FilterConfig fConfig) throws ServletException {
		
	}

}
