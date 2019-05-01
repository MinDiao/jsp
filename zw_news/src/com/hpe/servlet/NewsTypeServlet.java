package com.hpe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.po.NewsType;
import com.hpe.service.INewsTypeService;
import com.hpe.service.impl.NewsTypeServiceImpl;
import com.hpe.util.StringUtil;

/**
 * 
 * 类描述：新闻Controller
 * 作者： Administrator  
 * 创建日期：2018年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
@WebServlet("/newsType")
public class NewsTypeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 注入service
	private INewsTypeService newsTypeService = new NewsTypeServiceImpl();
       
    public NewsTypeServlet() {
        
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 默认都使用post方式
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		switch (action) {
			case "backList":
				newsTypeBackList(request, response);
				break;
			
			case "newsTypeShow":
				newsTypeShow(request, response);
				break;
			
			case "save":
				newsTypeSave(request, response);
				break;
				
			case "delete":
				newsTypeDelete(request, response);
				break;
				
			default:
				break;
		}
	}
	
	/**
	 * 
	 * 方法描述:新闻类型列表
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void newsTypeBackList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 调用service查询所有新闻类别信息
		List<NewsType> newsTypeBackList = newsTypeService.newsTypeList();
		// 将查询的数据填充到请求域属性中
		request.setAttribute("newsTypeBackList", newsTypeBackList);
		// 转发到newsTypeList.jsp页面
		request.getRequestDispatcher("/background/newsType/newsTypeList.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * 方法描述:显示新闻类别信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void newsTypeShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsTypeId = request.getParameter("newsTypeId");
		if (StringUtil.isNotEmpty(newsTypeId)) {
			// 如果不为空
			// 根据新闻类别Id查询新闻列表信息,因为返回值是long需要转换为int类型
			NewsType newsType = newsTypeService.getNewsTypeById(Integer.parseInt(newsTypeId));
			// 将新闻类别信息放入到请求域中
			request.setAttribute("newsType", newsType);
		} 
		request.getRequestDispatcher("/background/newsType/newsTypeSave.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * 方法描述:新闻类别修改和新增
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void newsTypeSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 新闻类别ID
		String newsTypeId = request.getParameter("newsTypeId");
		// 新闻类别名称
		String typeName = request.getParameter("typeName");
		// 封装对象
		NewsType newsType = new NewsType(typeName);
		// 声明变量来接受执行sql结果
		int result = 0;
		
		if (StringUtil.isNotEmpty(newsTypeId)) {
			// 获取到的Id不为空,则为修改
			// 因为类型不同需要拆箱
			newsType.setNewsTypeId(Integer.parseInt(newsTypeId));
			result = newsTypeService.newsTypeUpdate(newsType);
		} else {
			// 新增类别信息
			result = newsTypeService.newsTypeAdd(newsType);
		}
		// 查询所有新闻类别信息,(因为是静态页面无法绑定数据,则需要请求一个servlet)
		request.getRequestDispatcher("/newsType?action=backList").forward(request, response);
	}
	
	/**
	 * 
	 * 方法描述:删除新闻类别信息
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void newsTypeDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newsTypeId = request.getParameter("newsTypeId");
		// 由于新闻类别下存在很多新闻信息,所以需要先删除新闻信息,然后再删除新闻类别信息,(需要使用到事务)
		boolean result = newsTypeService.newsTypeDelete(Integer.parseInt(newsTypeId));
		String msg = "";
		if (result) {
			msg = "<script>alert('删除成功')</script>";
		} else {
			msg = "<script>alert('删除失败')</script>";
		}
		request.setAttribute("msg", msg);
		// 请求转发
		request.getRequestDispatcher("/newsType?action=backList").forward(request, response);
	}

}
