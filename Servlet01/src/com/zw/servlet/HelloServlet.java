package com.zw.servlet;

import java.io.IOException;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class HelloServlet implements Servlet {
	
	// 构造器:只执行一次,在第一次加载servlet执行,单利的
	public HelloServlet() {
		System.out.println("hello构造器");
	}
	
	// 注销:只被调用了一次,在当前servlet所在的web应用被卸载的时候,用于释放当前servlet所占用的资源
	@Override
	public void destroy() {
		System.out.println("hello销毁");
		
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
	public void init(ServletConfig arg0) throws ServletException {
		System.out.println("hello初始化");
		
	}
	
	// service方法:被调用多次,每次请求都会执行service方法,实际用于获取用户请求,处理业务
	@Override
	public void service(ServletRequest arg0, ServletResponse arg1) throws ServletException, IOException {
		System.out.println("helloservice");
		
	}

}
