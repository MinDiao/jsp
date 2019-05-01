package com.hpe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.hpe.po.News;
import com.hpe.po.NewsSearch;
import com.hpe.po.NewsType;
import com.hpe.po.PageBean;
import com.hpe.service.INewsService;
import com.hpe.service.INewsTypeService;
import com.hpe.service.impl.NewsServiceImpl;
import com.hpe.service.impl.NewsTypeServiceImpl;
import com.hpe.util.PageUtil;
import com.hpe.util.StringUtil;

@WebServlet("/news")
public class NewsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	// 注入Service
	private INewsService newsService = new NewsServiceImpl();
	
	private INewsTypeService newsTypeService = new NewsTypeServiceImpl();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		switch (action) {
			case "list":
				share(request, response);
				newslist(request, response);
				break;
	
			case "detail":
				share(request, response);
				newsDetail(request, response);
				break;
				
			case "backList":
				newsBackList(request, response);
				break;
				
			case "newsDelete":
				newsDelete(request, response);
				break;
				
			case "newsShow":
				newsShow(request, response);
				break;
				
			case "newsSave":
				newsSave(request, response);
				break;
				
			default:
				break;
		}
	}
	
	// 前台新闻详细信息
	private void newsDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收新闻Id
		String newsId = request.getParameter("newsId");
		// 增加点击量
		newsService.newsClick(Integer.parseInt(newsId));
		// 获取新闻详细信息
		News news = newsService.getNewsById(Integer.parseInt(newsId));
		request.setAttribute("news", news);
		// 页面跳转
		request.setAttribute("mainPage", "news/newsShow.jsp");
		request.getRequestDispatcher("/foreground/newsTemp.jsp").forward(request, response);
		
	}
	
	// 获取新闻类型列表,最新新闻,热点新闻
	private void share(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取新闻类型
		List<NewsType> newsTypeList = newsTypeService.newsTypeList();
		request.setAttribute("newsTypeList", newsTypeList);
		// 获取最新新闻
		List<News> newestNewsList = newsService.newestNewList();
		request.setAttribute("newestNewsList", newestNewsList);
		// 获取热点新闻
		List<News> hotNewsList = newsService.hotNewsList();
		request.setAttribute("hotNewsList", hotNewsList);
	}
	
	// 显示新闻列表
	private void newslist(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取新闻类别Id
		String typeId = request.getParameter("typeId");
		// 获取当前第几页
		String page = request.getParameter("page");
		// 判断page是否为空
		if (StringUtil.isEmpty(page)) {
			// 空则为1
			page = "1";
		}
		// 封装查询条件
		NewsSearch search = new NewsSearch();
		if (StringUtil.isNotEmpty(typeId)) {
			search.setTypeId(Integer.parseInt(typeId));
		}
		
		// 设置分页信息
		// 总记录数
		int totalNum = (int) newsService.newsCount(search);
		// 当前页
		int currentPage = Integer.parseInt(page);
		// 每页显示的记录数
		int pageSize = 5;
		// 创建PageBean
		PageBean pageBean = new PageBean(currentPage, pageSize);
		// 获取某个类别下的所有新闻信息
		List<News> newsListWithType = newsService.newsList(search, pageBean);
		// 填充到请求域中
		request.setAttribute("newsListWithType", newsListWithType);
		// 获取分页代码
		String pageCode = PageUtil.getUpAndDownPagation(totalNum, currentPage, pageSize, typeId);
		// 将代码填充道请求域中
		request.setAttribute("pageCode", pageCode);
		
		request.setAttribute("mainPage", "news/newsList.jsp");
		// 转发到
		request.getRequestDispatcher("/foreground/newsTemp.jsp").forward(request, response);
	}
	
	// 后台新闻列表
	private void newsBackList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*顶部搜索条件*/
		// 当前页
		String page = request.getParameter("page");
		
		/*封装查询条件*/
		// 1.新闻标题
		String title = request.getParameter("title");
		// 2.开始日期
		String startDate = request.getParameter("startDate");
		// 3.结束日期
		String endDate = request.getParameter("endDate");
		// 4.封装到对象
		NewsSearch search = new NewsSearch(-1, title, startDate, endDate);
		
		// 保存查询条件
		HttpSession session = request.getSession();
		if (StringUtil.isEmpty(page)) {
			page = "1";
			session.setAttribute("search", search);
		} else {
			// 因为切换页码的时候顶头的搜索设置需要被绑定过去,所以需要保存到session域中,并从session域中获取搜索的值
			search = (NewsSearch) session.getAttribute("search");
		}
		
		
		/*底部目录*/
		// 总记录数
		int totalNum = (int) newsService.newsCount(search);
		// 请求url
		String targetUrl = request.getContextPath()+"/news?action=backList";
		// 当前页
		int currentPage = Integer.parseInt(page);
		// 每页显示的记录数
		int pageSize = 5;
		// 获取分页的HTML代码
		String pageCode = PageUtil.getPagation(targetUrl, totalNum, currentPage, pageSize);
		// 设置分页信息--当前页,显示页数
		PageBean pageBean = new PageBean(currentPage, pageSize);
		// 获取新闻列表
		List<News> newsBackList = newsService.newsList(search, pageBean);
		
		// 将分页代码及新闻列表信息设置到请求域中
		request.setAttribute("pageCode", pageCode);
		request.setAttribute("newsBackList", newsBackList);
		
		// 转发到newsList.jsp页面
		request.getRequestDispatcher("/background/news/newsList.jsp").forward(request, response);
	}
	
	
	// 删除新闻
	private void newsDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 获取新闻ID
		String newsId = request.getParameter("newsId");
		int count = newsService.newsDelete(Integer.parseInt(newsId));
		boolean result = count > 0 ? true : false;
		response.getWriter().println(result);
	}
	
	
	// 显示新闻
	private void newsShow(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取新闻id
		String newsId = request.getParameter("newsId");
		if (StringUtil.isNotEmpty(newsId)) {
			// 修改
			// 根据新闻Id查询新闻信息
			News news = newsService.getNewsById(Integer.parseInt(newsId));
			request.setAttribute("news", news);
		}
		// 还需要绑定新闻类别信息
		List<NewsType> newsTypeList = newsTypeService.newsTypeList();
		request.setAttribute("newsTypeList", newsTypeList);
		request.getRequestDispatcher("/background/news/newsSave.jsp").forward(request, response);
	}
	
	// 保存新闻
	private void newsSave(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 接收参数:新闻标题,作者,新闻类别id,新闻内容,新闻id,头条,热点
		// 创建好一个对象
		News news = new News();
		
		// 获取参数
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String typeId = request.getParameter("typeId");
		String content = request.getParameter("content");
		String newsId = request.getParameter("newsId");
		String isHead = request.getParameter("isHead");
		String isHot = request.getParameter("isHot");
		
		// 分别给对象设置属性(在前台已经判断非空)
		news.setTitle(title);
		news.setAuthor(author);
		news.setTypeId(Integer.parseInt(typeId));
		news.setContent(content);
		
		// 非常重要的newsId:判断newsid来进行选择:添加\修改 (因为没有在前台进行判断非空,所以在此判断)
		if (StringUtil.isNotEmpty(newsId)) {
			news.setNewsId(Integer.parseInt(newsId));
		}
		if (StringUtil.isNotEmpty(isHead)) {
			news.setIsHead(Integer.parseInt(isHead));
		}
		if (StringUtil.isNotEmpty(isHot)) {
			news.setIsHot(Integer.parseInt(isHot));
		}
		
		// 初始化结果变量
		int result = 0;
		String type;
		// 执行判断
		if (news.getNewsId() != 0) {
			// 修改新闻
			result = newsService.newsUpdate(news);
			type = "update";
		} else {
			// 添加新闻
			result = newsService.newsAdd(news);
			type = "add";
		}
		// {"result":1或0,"type":"update"或"add"}
		
		// 服务器响应给浏览器的json格式,json必须是字符串格式,所以需要两个转义字符
		response.getWriter().print("{\"result\":"+result+",\"type\":\""+type+"\"}");
	}
	
}
