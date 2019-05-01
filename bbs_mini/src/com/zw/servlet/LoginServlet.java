package com.zw.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.zw.base.BaseServlet;
import com.zw.pojo.User;
import com.zw.service.UserService;
import com.zw.service.impl.UserServiceImpl;

/**
 * 
 * 类描述：登录Servlet
 * 作者： LiuJinrong  
 * 创建日期：2018年12月12日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
@WebServlet("/login")
public class LoginServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	// 注入Service
	private UserService userService = new UserServiceImpl();
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/**
		 * Servlet初始化是单例的,由Tomcat进行第一次访问进行初始化,会涉及到线程安全,所以前后访问会导致访问结果不同,所以需要将声明的对象放至方法中
		 */
		// 创建json对象
		JSONObject json = new JSONObject();
		// 初始化登录对象
		User loginUser = null;
		// 方便调试
		try {
			// 用户名
			String username = request.getParameter("username");
			// 密码
			String pwd = request.getParameter("pwd");
			// 封装到User对象
			User user = new User(username, pwd);
			// 调用服务登录方法
			loginUser = userService.login(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 判断是否登录成功
		if (loginUser != null) {
			json.put("code", 1);
			// 方法一:返回登录信息,保存到h5的sessionStorage
			json.put("loginUser", loginUser);
			
			// 方法二:将登录信息填充至Session
			//request.getSession().setAttribute("loginUser", loginUser);
			
		} else {
			json.put("code", 0);
		}
		// 将json结果返回页面
		response.getWriter().write(json.toString());
	}
	

	// tomcat无法验证jsessionid 是否为同一个请求,甚至tomcat集群则就需要自定义响应头包含自定义的jsessionid
	/*public void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("loginUser");
		request.getSession().removeAttribute("loginUser");
	}*/

}
