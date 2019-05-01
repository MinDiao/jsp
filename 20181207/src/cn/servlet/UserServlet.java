package cn.servlet;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import cn.pojo.City;
import cn.pojo.User;
import cn.service.UserService;
import cn.service.impl.UserServiceImpl;
import cn.utils.Page;

/**
 * 查询用户列表 ，支持异步
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService = new UserServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 响应json格式
		response.setContentType("application/json;charset=utf-8");
		
		JSONObject json = new JSONObject();//申明json对象
		//JSONArray array = new JSONArray();// 声明json数组
		
		//List<User> users = new ArrayList<>();//返回的user列表
		
		// 注意:调用service方法要放到try{}里,从request中获取数据也放到try{}里
		// 实例化分页对象,分页信息
		Page page = new Page();
		try {
			String username = request.getParameter("username");
			String sex = request.getParameter("sex");
			
			// 页码
			String pageNum = request.getParameter("pageNum");
			// 页容量
			String pageSize = request.getParameter("pageSize");
			
			User user = new User();
			user.setUsername(username);
			user.setSex(sex);
			
			page.setPageNum(Integer.valueOf(pageNum));
			page.setPageSize(Integer.valueOf(pageSize));
			
			// 调用service 进行分页 并 进行分页,条件筛选查询
			page = userService.selectUsers(user, page);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		json.put("page", page);//往json里放值
		//返回json字符串  应该返回的是从数据库中查询出来的用户列表信息   你们的作业
		response.getWriter().write(json.toString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 响应json格式
		response.setContentType("application/json;charset=utf-8");
		
		JSONObject json = new JSONObject();//申明json对象
		
		// 初始化请求信息
		String action = request.getParameter("action");
		
		try {
			// 城市下拉框
			if (action.equals("citySelectAll")) {

				// 返回城市对象List集合
				List<City> list = userService.cityList();

				// 填充到json中
				json.put("citys", list);
			}
			
			// 删除用户
			if (action.equals("userDelete")) {

				// 获取用户id
				String userId = request.getParameter("userDeleteById");

				// 返回删除用户的影响行数
				int result = userService.userDeleteById(Integer.parseInt(userId));

				// 将结果填充至json对象中
				json.put("result", result);
			} 
			
			// 添加用户
			if (action.equals("userAdd")) {
				
				// 获取请求数据
				String username = request.getParameter("username");
				String birthday = request.getParameter("userBirthday");
				String sex = request.getParameter("sex");
				String address = request.getParameter("address");
				
				// 创建日期格式化对象
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				// 将String类型日期转换为Date类型
				Date parse = simpleDateFormat.parse(birthday);
				
				// 实例化用户对象
				User user = new User(username, parse, sex, address);
				
				// 返回添加用户的影响行数
				int result = userService.userAdd(user);
				
				// 将结果填充至json对象中
				json.put("result", result);
			}
			
			// 批量删除
			if (action.equals("userDeleteS")) {
				
				// 获取请求数据
				String userDeleteSId = request.getParameter("userDeleteSId");
				
				// 返回影响行数并执行Service的批量删除
				int result = userService.userDeleteSById(userDeleteSId);
				
				// 将结果填充至json对象
				json.put("result", result);
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 响应到添加页面
		response.getWriter().write(json.toString());
	}

}
