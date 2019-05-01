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
import com.zw.util.ToolUtil;

/**
 * 
 * 类描述：注册的Servlet
 * 作者： LiuJinrong  
 * 创建日期：2018年12月11日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
@WebServlet("/reg")
public class RegServlet extends BaseServlet {
	
	private static final long serialVersionUID = 1L;
	
	// 注入service
	UserService userService = new UserServiceImpl();

	/**
	 * 默认调用的方法,当action为null时调用,注册方法
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 初始化返回影响行数
		int result = 0;
		// 初始化json对象
		JSONObject json = new JSONObject();
		
		try {
			// 1.获取用户提交的数据
			String username = request.getParameter("username");
			String password = request.getParameter("password");
			String sex = request.getParameter("sex");
			String hobbys = request.getParameter("hobbys");
			String birthday = request.getParameter("birthday");
			String city = request.getParameter("city");
			String my_email = request.getParameter("my_email");
			String my_qq = request.getParameter("my_qq");
			
			// 2.创建user对象,封装数据
			User user = new User(username, password, sex, hobbys, birthday, city, my_email, my_qq, ToolUtil.getCurrentTime());
			
			// 3.调用service进行保存
			result = userService.addUser(user);
			
		} catch (Exception e) {
			e.printStackTrace();
			// 错误则赋值0
			result = 0 ;
		}
		
		// 将返回影响结果填充至json
		json.put("code", result);
		
		// 将json响应至ajax
		response.getWriter().write(json.toString());
	}
	
	/**
	 * 
	 * 方法描述:检查用户名是否重复
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void checkUser(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建json对象
		JSONObject json = new JSONObject();
		// 初始化结果
		int res = 0;
		// 方便打印错误
		try {
			// 获取用户输入的用户名
			String username = request.getParameter("username");
			// 调用service检查
			res = userService.checkUser(username);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 判断后台返回
		if (res == 0) {
			// 不重复
			json.put("code", 0);
		} else {
			// 重复
			json.put("code", 1);
		}
		// 将json响应到页面
		response.getWriter().write(json.toString());
	}

}
