package com.hpe.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.po.News;
import com.hpe.po.NewsType;
import com.hpe.service.INewsService;
import com.hpe.service.INewsTypeService;
import com.hpe.service.impl.NewsServiceImpl;
import com.hpe.service.impl.NewsTypeServiceImpl;


@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 注入Service
	INewsTypeService newsTypeService = new NewsTypeServiceImpl();
	INewsService newsService = new NewsServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取新闻类型
		List<NewsType> newsTypeList = newsTypeService.newsTypeList();
		// 填充到请求域中
		request.setAttribute("newsTypeList", newsTypeList);
		
		// 获取所有新闻
		// 使用集合嵌套的方式
		List<List<News>> allIndexNewsList = new ArrayList<>();
		// 先判断是否有新闻信息
		if (newsTypeList != null && newsTypeList.size() != 0) {
			// 遍历newsTypeList得到其中的数据
			for (int i = 0; i <newsTypeList.size(); i++) {
				NewsType newsType = newsTypeList.get(i);
				// 根据新闻类别Id查询新闻信息(此处的List是整个大的集合)
				List<News> list = newsService.allIndexNewsList(newsType.getNewsTypeId());
				// 填充
				allIndexNewsList.add(list);
			}
		}
		// 放到请求域中
		request.setAttribute("allIndexNewsList", allIndexNewsList);
		// 友情链接
		
		// 转发到index.jsp页面
		request.getRequestDispatcher("/index.jsp").forward(request, response);
	}

}
