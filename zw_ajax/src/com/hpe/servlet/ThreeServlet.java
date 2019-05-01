package com.hpe.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.hpe.po.Person;

@WebServlet("/three")
public class ThreeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		/*response.setContentType("text/html;charset=utf-8");*/
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		// 模拟数据
		List<Person> persons = new ArrayList<>();
		persons.add(new Person(1, "张三", 20, "山东济南"));
		persons.add(new Person(2, "李四", 30, "山东德州"));
		persons.add(new Person(3, "王五", 40, "山东青岛"));
		// 将List集合转换成json类型数据(需要依赖jar包)
		Gson gson = new Gson();
		String json = gson.toJson(persons);
		
		// 输出
		out.print("{\"result\":"+json+"}");
		
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
