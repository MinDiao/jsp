package com.zw.Listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * ServletContextListener接口用于监听ServletContext对象的创建和销毁事件
 * @author Administrator
 *
 */
public class MyServletContextListener implements ServletContextListener {
	/**
	 * ServletContext对象被创建
	 */
	@Override
	public void contextInitialized(ServletContextEvent arg0) {
		System.out.println("ServletContext创建...");
	}
	/**
	 * ServletContext对象被销毁
	 */
	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		System.out.println("ServletContext销毁...");
	}
}
