package com.zw.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.zw.po.User;

/**
 * Servlet implementation class OneServlet
 */
@WebServlet("/two")
public class TwoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 数组
		String[] array = {"张三","李四","王五"};
		request.setAttribute("array", array);
		
		// 集合
		List<String> list = new ArrayList<String>();
		list.add("刘备");
		list.add("关羽");
		list.add("张飞");
		HttpSession session = request.getSession();
		session.setAttribute("list", list);
		
		// 对象实例
		User user = new User("宋江", 30);
		// 添加到session域中
		session.setAttribute("user", user);
		
		// 创建泛型list集合
		List<User> users = new ArrayList<User>();
		users.add(new User("数码特", 30));
		users.add(new User("王者", 28));
		users.add(new User("荣耀", 25));
		// 添加到request域中
		request.setAttribute("users", users);
		
		// 创建泛型map集合
		Map<String, User> map = new HashMap<String, User>();
		map.put("one", new User("C罗", 33));
		map.put("two", new User("梅西", 31));
		map.put("three", new User("内马尔", 26));
		// 添加到request与中
		request.setAttribute("map", map);
		
		
		
		// 为了保证同一个对象,则使用转发的方式el.jsp
		request.getRequestDispatcher("/el.jsp").forward(request, response);
	}

}
