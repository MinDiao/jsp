package com.hpe.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.hpe.dao.INewsDao;
import com.hpe.dao.impl.NewsDaoImpl;
import com.hpe.po.News;
import com.hpe.po.NewsSearch;
import com.hpe.po.PageBean;
import com.hpe.service.INewsService;
/**
 * 
 * 类描述：新闻业务逻辑层实现类
 * 作者： Administrator  
 * 创建日期：2018年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class NewsServiceImpl implements INewsService {
	
	// 注入Dao
	private INewsDao newsDao = new NewsDaoImpl();
	

	@Override
	public List<News> newsList(NewsSearch search, PageBean pageBean) {
		
		return newsDao.newsList(search, pageBean);
	}

	@Override
	public long newsCount(NewsSearch search) {
		
		return newsDao.newsCount(search);
	}

	@Override
	public News getNewsById(int newsId) {
		
		return newsDao.getNewsById(newsId);
	}

	@Override
	public int newsClick(int newsId) {
		
		return newsDao.newsClick(newsId);
	}

	@Override
	public int newsAdd(News news) {

		return newsDao.newsAdd(news);
	}

	@Override
	public int newsUpdate(News news) {

		return newsDao.newsUpdate(news);
	}

	@Override
	public int newsDelete(int newsId) {
		int result = 0;
		try {
			result = newsDao.newsDelete(newsId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public List<News> newestNewList() {
		
		return newsDao.newestNewList();
	}

	@Override
	public List<News> hotNewsList() {
		
		return newsDao.hotNewsList();
	}

	@Override
	public List<News> allIndexNewsList(int typeId) {
		
		return newsDao.allIndexNewsList(typeId);
	}

}
