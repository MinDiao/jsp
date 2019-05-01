package com.hpe.test;

import static org.junit.Assert.fail;

import java.util.List;

import org.junit.Test;

import com.hpe.dao.INewsDao;
import com.hpe.dao.impl.NewsDaoImpl;
import com.hpe.po.News;
import com.hpe.po.NewsSearch;
import com.hpe.po.PageBean;

public class TestNews {

	INewsDao newsDao = new NewsDaoImpl();
	@Test
	public void testNewsList() {
		// 指定搜索条件
		NewsSearch search = new NewsSearch();
		search.setTitle("中国");
		search.setStartDate("2017-12-05");
		search.setEndDate("2017-12-22");
		// 指定分页信息
		PageBean pageBean = new PageBean(1, 2);
		List<News> newsList = newsDao.newsList(search, pageBean);
		for (News news : newsList) {
			System.out.println(news);
		}
	}

	@Test
	public void testNewsCount() {
		NewsSearch search = new NewsSearch();
		/*search.setTitle("中国");
		search.setStartDate("2017-12-05");
		search.setEndDate("2017-12-22");*/
		long count = newsDao.newsCount(search);
		System.out.println(count);
	}

}
