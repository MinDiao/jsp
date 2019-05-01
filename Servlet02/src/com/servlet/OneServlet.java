package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/oneServlet")
public class OneServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
    public OneServlet() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// request:封装了http信息,说白了就是通过这个对象去获取请求信息,它是ServletRequest子接口
		// response:封装了响应信息
		// 这两个接口在每一次访问时,都会被创建,由servlet容器创建,并且在服务器调用service方法时传入
		
		// getWiter()方法,把结果响应给浏览器
		PrintWriter out = response.getWriter();
		
		
		// 获取姓名的值
		// 姓名：<input type="text" name="username" id="username"> getParameter方法参数的值就是input中name的值
		String name = request.getParameter("username");
		//System.out.println(name);
		
		out.write("<h1>"+ name +"<h1>");
		
		// getParameterValues (String)方法
		// 参数:就是name的属性的值
		String[] hobbys = request.getParameterValues("hobby");
		for (String h : hobbys) {
			System.out.println("爱好:"+ h);
		}
		
		// 性别:是单选,也就代表一个值
		String sex = request.getParameter("sex");
		System.out.println(sex);
		
		// getParameterNames()方法获取所有参数的名字,返回类型为
		Enumeration<String> names = request.getParameterNames();
		while (names.hasMoreElements()) {
			String string = names.nextElement();
			System.out.println(string);
		}
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}