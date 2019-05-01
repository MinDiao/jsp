package com.session;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class SessionServlet3
 */
@WebServlet("/sessionServlet3")
public class SessionServlet3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public SessionServlet3() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		//System.out.println("成功");
		//request.getRequestDispatcher("/index.html"); // 会发生重复提交
		
		
		// 获取用户提交的令牌token
		String token = request.getParameter("token");
		// 获取session中的令牌
		HttpSession session = request.getSession();
		String sToken = (String) session.getAttribute("token");
		// 验证重复提交
		if (token.equals(sToken)) {
			System.out.println("注册成功");
			session.removeAttribute("token");
		} else {
			System.out.println("表单重复提交");
		}
		//response.sendRedirect(request.getContextPath()+"/index.html"); // 不会发生重复提交
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
