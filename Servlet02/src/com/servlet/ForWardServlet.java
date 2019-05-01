package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ForWardServlet
 */
@WebServlet("/forWardServlet")
public class ForWardServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ForWardServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("ForWardServlet...");
		// setAttribute方法
		request.setAttribute("name", "zhangsan");
		System.out.println(request.getAttribute("name"));
		// 请求转发:把请求转发给另一个servlet帮助我们处理
		String path = "/testServlet";
		request.getRequestDispatcher(path).forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
}
