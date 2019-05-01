package com.zw.Listener;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;

public class MyServletRequestListener implements ServletRequestListener {
	@Override
	public void requestDestroyed(ServletRequestEvent arg0) {
		System.out.println("请求被销毁");
	}

	@Override
	public void requestInitialized(ServletRequestEvent arg0) {
		System.out.println("请求被创建");
	}
}
