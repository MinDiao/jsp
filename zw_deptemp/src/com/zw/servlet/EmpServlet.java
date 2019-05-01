package com.zw.servlet;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.util.StringUtil;
import com.zw.po.Dept;
import com.zw.po.Emp;
import com.zw.po.EmpDept;
import com.zw.service.IDeptService;
import com.zw.service.IEmpService;
import com.zw.service.impl.DeptServiceImpl;
import com.zw.service.impl.EmpServiceImpl;
/**
 * 
 * 类描述：员工web层
 * 作者： Administrator  
 * 创建日期：2018年11月26日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
@WebServlet("/emp")
public class EmpServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 注入Service
	IEmpService empService = new EmpServiceImpl();
	
	IDeptService deptService = new DeptServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 从请求与中获取action
		String action = request.getParameter("action");
		// 选择对应的功能
		switch (action) {
			case "empServletAll":
				empServletAll(request, response);
				break;
				
			case "empServletById":
				empServletById(request, response);
				break;
				
			case "empAddUpdate":
				empAddUpdate(request, response);
				break;
				
			case "empDelete":
				empDelete(request, response);
				break;
	
			default:
				break;
		}
	}
	
	/**
	 * 
	 * 方法描述:查询所有员工信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void empServletAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 定义集合接收员工信息
		List<EmpDept> empDeptList = empService.empSelectAll();
		// 将全部信息填充到请求域中
		request.setAttribute("empDeptList", empDeptList);
		// 转发到empList.jsp中
		request.getRequestDispatcher("/empList.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * 方法描述:根据员工ID查询单个员工信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void empServletById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 从请求域中获取属性值
		String empID = request.getParameter("empID");
		// 定义集合接收部门信息
		List<Dept> deptList = deptService.deptSelectAll();
		// 将全部信息填充到请求域
		request.setAttribute("deptList", deptList);
		// 判断员工ID是否为空
		if (StringUtil.isNotEmpty(empID)) {
			// 封装员工信息对象
			EmpDept empDept = empService.empSelectById(Integer.parseInt(empID));
			// 将信息填充到请求域中
			request.setAttribute("empDept", empDept);
		}
		// 转发至empAddUpdate.jsp中
		request.getRequestDispatcher("/empAddUpdate.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * 方法描述:添加或修改员工信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void empAddUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 从请求域中获取属性值
		String empID = request.getParameter("empID");
		String empName = request.getParameter("empName");
		String deptId = request.getParameter("deptId");
		String empBirthday = request.getParameter("empBirthday");
		String empphone = request.getParameter("empphone");
		//String deptName = request.getParameter("deptName");
		
		// 先根据部门名称查询部门实体类
		//Dept deptBean = empService.deptSelectByName(deptName);
		
		// 创建日期格式化对象
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		// 初始化日期对象
		Date parse = null;
		try {
			// 执行将String转为Date对象
			parse = simpleDateFormat.parse(empBirthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		// 初始化结果
		int result = 0;
		
		// 初始化实体类
		Emp emp = null;
		
		// 判断是否存在ID来进行添加或修改
		if (StringUtil.isEmpty(empID)) {
			// 封装添加员工的对象
			emp = new Emp(empName, Integer.parseInt(deptId), parse, empphone);
			// 添加
			result = empService.empAdd(emp);
		} else {
			// 封装修改员工的对象
			emp = new Emp(Integer.parseInt(empID), empName, Integer.parseInt(deptId), parse, empphone);
			// 修改
			result = empService.empUpdate(emp);
		}
		// 将执行结果输出到页面
		success(result, request, response);
	}
	
	/**
	 * 
	 * 方法描述:删除员工信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void empDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 从请求域中获取员工的ID
		String empID = request.getParameter("empID");
		// 初始化执行结果
		int result = 0;
		// 判断是否存在
		if (StringUtil.isNotEmpty(empID)) {
			// 执行删除方法
			result = empService.empDelete(Integer.parseInt(empID));
		}
		// 将执行结果输出到页面
		success(result, request, response);
	}
	
	/**
	 * 
	 * 方法描述:封装执行输出的结果
	 * @param result
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void success(int result, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		// 初始化请求域的script代码
		String succe = null;
		// 将执行结果输出到empAddUpdate.jsp页面
		if (result > 0) {
			succe = "<script>alert('成功')</script>";
		} else {
			succe = "<script>alert('失败')</script>";
		}
		// 填充到请求域中
		request.setAttribute("succe", succe);
		// 转发到empList.jsp页面
		request.getRequestDispatcher("/emp?action=empServletAll").forward(request, response);
	}

}
