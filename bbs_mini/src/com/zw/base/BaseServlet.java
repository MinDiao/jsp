package com.zw.base;

import java.io.IOException;
import java.lang.reflect.Method;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 公共baseServlet
 * @author Administrator
 *
 */
public abstract class BaseServlet extends HttpServlet {
	/**
	* 
	*/
	private static final long serialVersionUID = 5887904330512273719L;

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		String cmd = request.getParameter("action");
		if (cmd == null || "".equals(cmd.trim())) {
			// 如果不指定请求的方法，默认为execute
			cmd = "execute";
		}
		// 根据反射机制
		@SuppressWarnings("rawtypes")
		Class clazz = this.getClass();
		try {
			// 得到调用方法的指针
			@SuppressWarnings("unchecked")
			Method method = clazz.getDeclaredMethod(cmd, HttpServletRequest.class, HttpServletResponse.class);
			// 调用方法
			method.invoke(this, request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public abstract void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}

