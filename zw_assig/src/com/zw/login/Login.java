package com.zw.login;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 更改编码
		response.setContentType("text/html;charset=utf-8");
		
		// 获取用户名和密码
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		// 定义密码和账号
		String username1 = "ljr";
		String password1 = "123";
		
		// 获取用户提交的令牌token
		String token = request.getParameter("token");
		
		// 获取session中的令牌
		HttpSession session = request.getSession();
		String sToken = (String) session.getAttribute("token");
		
		// 验证重复提交
		if (token.equals(sToken)) {
			// 删除令牌
			session.removeAttribute("token");
			// 判断
			if (username.equals(username1) && password.equals(password1)) {		
				// 登录成功
				response.sendRedirect("wel.html");
				System.out.println("登录成功");
			} else {
				// 登录失败
				response.sendRedirect("err.html");
			}
			
			// 重复提交
		} else {
			System.out.println("表单重复提交");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
