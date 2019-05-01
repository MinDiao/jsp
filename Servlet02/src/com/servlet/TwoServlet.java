package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TwoServlet
 */
@WebServlet("/twoServlet")
public class TwoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TwoServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*// 乱码原因:,因为获取请求和响应结果都需要经过tomcat服务器,而tomcat服务器的编码格式为iso-8859-1
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		// 第一种方式, 可以处理post和get请求的
		String username = request.getParameter("username"); // 乱码,现在的格式为iso-8859-1
		byte[] byte1 = username.getBytes("iso-8859-1"); // 把获取的name值(iso-8859-1)转成字节码
		String username1 = new String(byte1, "utf-8");
		out.write(username1);*/
		
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		request.setCharacterEncoding("utf-8");
		String username = request.getParameter("username");
		out.write(username);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
