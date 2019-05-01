package com.hpe.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.po.User;
import com.hpe.service.IUserService;
import com.hpe.service.impl.UserServiceImpl;

/**
 * 
 * 类描述：用户Controller
 * 作者： Administrator  
 * 创建日期：2018年11月20日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	// 注入service
	IUserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if (action.equals("login")) {
			// 登录
			login(request, response);
		} else if (action.equals("logout")) {
			// 退出
			logout(request, response);
		}
	}

	/**
	 * 
	 * 方法描述:用户登录
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收用户名和密码
		String userName = request.getParameter("userName");
		String password = request.getParameter("password");
		User user = new User(userName, password);
		// 创建session
		HttpSession session = request.getSession();
		// 调用service中的登录方法
		User currentUser = userService.login(user);
		if (currentUser == null) {
			String error = "<script>alert('用户名或密码错误')</script>";
			request.setAttribute("error", error);
			request.getRequestDispatcher("/background/login.jsp").forward(request, response);
		} else {
			// 将用户信息保存到session中
			session.setAttribute("currentUser", currentUser);
			// 转发的 / 表示当前web应用根目录
			request.getRequestDispatcher("/background/main.jsp").forward(request, response);
		}
	}
	
	/**
	 * 
	 * 方法描述:用户退出
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void logout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 注销用户,session失效
		//request.getSession().invalidate();
		HttpSession session = request.getSession();
		session.invalidate();
		// 重定向的 / 表示当前web站点,所以前面必须加获取到的web应用根目录
		response.sendRedirect(request.getContextPath()+"/background/login.jsp");
	}
	
	

}
