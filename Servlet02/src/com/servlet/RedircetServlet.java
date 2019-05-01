package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RedircetServlet
 */
@WebServlet("/redircetServlet")
public class RedircetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public RedircetServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("RedircetServlet...");
		
		// setAttribute方法
		request.setAttribute("name", "zhangsan");
		System.out.println(request.getAttribute("name"));
		response.sendRedirect("testServlet");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
}
