package com.zw.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.zw.po.User;
import com.zw.service.UserService;
import com.zw.service.impl.UserServiceImpl;
/**
 * 
 * 类描述：用户Servlet
 * 作者： LiuJinrong  
 * 创建日期：2018年12月9日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 注入用户Service层
	UserService userService = new UserServiceImpl();

	// 初始化请求
	String action = null;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 响应json格式
		response.setContentType("application/json;charset=utf-8");

		// 获得请求
		action = request.getParameter("action");

		// 判断请求
		if (action.equals("userList")) {
			userlist(request, response);
		}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 响应json格式
		response.setContentType("application/json;charset=utf-8");

		// 获得请求
		action = request.getParameter("action");

		// 判断请求
		if (action.equals("userListByNameSex")) {
			userListByNameSex(request, response);
		}
	}

	/**
	 * 
	 * 方法描述:查询所有/用户列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void userlist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 创建json对象
		JSONObject json = new JSONObject();

		// 从userService层获取用户列表的List集合
		List<User> list = userService.userList();

		// 将包含实体类的集合填充到json对象中
		json.put("users", list);

		// 将json对象响应到html页面
		response.getWriter().write(json.toString());
	}

	/**
	 * 
	 * 方法描述:根据名字模糊和性别查询
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void userListByNameSex(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 获取模糊查询的用户姓名
		String userName = request.getParameter("userName");
		String userSex = request.getParameter("userSex");
		
		// 创建json对象
		JSONObject json = new JSONObject();
		
		// 创建User对象
		User user = new User(userName, userSex);
		
		// 从userService层获取用户列表的List集合
		List<User> list = userService.userListByNameSex(user);

		// 将包含实体类的集合填充到json对象中
		json.put("users", list);

		// 将json对象响应到html页面
		response.getWriter().write(json.toString());
	}
	
	/**
	 * 
	 * 方法描述:根据用户id删除用户
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void userDeleteById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
