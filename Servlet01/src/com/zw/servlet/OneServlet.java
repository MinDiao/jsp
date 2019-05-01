package com.zw.servlet;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class OneServlet implements Servlet {
	
	// 构造器:只执行一次,在第一次加载servlet执行,单利的
	public OneServlet() {
		System.out.println("one构造器");
	}
	
	// 注销:只被调用了一次,在当前servlet所在的web应用被卸载的时候,用于释放当前servlet所占用的资源
	@Override
	public void destroy() {
		System.out.println("one销毁");
		
	}

	@Override
	public ServletConfig getServletConfig() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getServletInfo() {
		// TODO Auto-generated method stub
		return null;
	}
	
	// 初始化:在第一次请求servlet时,才执行,只执行一次,可以统计登录的人数,给个计数变量
	@Override
	public void init(ServletConfig config) throws ServletException {
		System.out.println("one初始化");
		// 1. getServletName()	获取当前Servlet在web.xml中配置的名字
		String servletName = config.getServletName();
		System.out.println(servletName);
		// 2.getServletContext()   获取代表当前web应用的ServletContext对象
		String username = config.getInitParameter("username");
		System.out.println(username);
		// 3.getInitParameterNames()  获取当前Servlet所有初始化参数的名字组成的枚举
		Enumeration<String> names = config.getInitParameterNames();
		while (names.hasMoreElements()) {
			// 遍历获取当前初始化参数 name名
			String name = names.nextElement();
			// 获取初始化参数名
			String value = config.getInitParameter(name);
			System.out.println("name:"+ name +",value:"+ value);
		}
		// 4.getServletContext()   获取代表当前web应用的ServletContext对象
		ServletContext cxt = config.getServletContext();//获取当前web对象
		System.out.println(cxt.getContextPath());// 获取的是当前web应用的根目录
		
		// 5.获取当前项目的真实路径 /:代表当前web应用,也就是//localhost
		System.out.println(cxt.getRealPath("/node.txt"));
		
	}
	
	// service方法:被调用多次,每次请求都会执行service方法,实际用于获取用户请求,处理业务
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("oneservice");
		
	}

}
