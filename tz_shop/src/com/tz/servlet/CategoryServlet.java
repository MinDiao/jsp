package com.tz.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.util.StringUtil;
import com.tz.po.Category;
import com.tz.service.ICategoryService;
import com.tz.service.impl.CategoryServiceImpl;


@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 注入Service
	ICategoryService categoryService = new CategoryServiceImpl();

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
			case "selectAll":
				selectAll(request, response);
				break;
				
			case "categoryShow":
				categoryShow(request, response);
				break;
				
			case "addUpdate":
				addUpdate(request, response);
				break;
	
			default:
				break;
		}
	}

	/**
	 * 
	 * 方法描述:查询所有商品类别
	 */
	private void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 调用Service层查询所有功能
		List<Category> categoryList = categoryService.selectCategoryAll();
		// 将查询到的信息填充到请求域中
		request.setAttribute("categoryList", categoryList);
		// 转发到admin/category/list.jsp
		request.getRequestDispatcher("/admin/category/list.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * 方法描述:根据id显示商品类别名称
	 */
	private void categoryShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 获取商品类别id
		String categoryId = request.getParameter("categoryId");
		// 为了修改前的判空
		if (StringUtil.isNotEmpty(categoryId)) {
			// 查询出的商品类别对象
			Category categoryShow = categoryService.selectCategoryById(Integer.parseInt(categoryId));
			// 将商品信息填充到请求域中
			request.setAttribute("categoryShow", categoryShow);
		}
		// 转发到修改页面中
		request.getRequestDispatcher("/admin/category/edit.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * 方法描述:添加或修改商品类别
	 */
	private void addUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 获取商品类别id
		String categoryId = request.getParameter("categoryId");
		// 获取商品类别名称
		String categoryName = request.getParameter("categoryName");
		// 将商品类别名称封装到对象
		Category category = new Category(categoryName);
		// 判断是否有值来进行选择添加/修改
		if (StringUtil.isNotEmpty(categoryId)) {
			// 修改
			// 将id封装到当前对象
			category.setCid(categoryId);
			// 执行修改
			categoryService.updateCategory(category);
		} else {
			// 添加
			categoryService.addCategory(category);
		}
		// 转发到展现列表的Servlet
		request.getRequestDispatcher("/category?action=selectAll").forward(request, response);
	}
}
