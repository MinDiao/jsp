package com.hpe.dao;

import java.sql.SQLException;
import java.util.List;

import com.hpe.po.News;
import com.hpe.po.NewsSearch;
import com.hpe.po.PageBean;

/**
 * 
 * 类描述：新闻信息数据访问接口
 * 作者： Administrator  
 * 创建日期：2018年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface INewsDao {

	/**
	 * 
	 * 方法描述:查询新闻信息
	 * @param search 搜索条件
	 * @param pageBean 分页信息
	 * @return
	 */
	List<News> newsList(NewsSearch search, PageBean pageBean);
	
	/**
	 * 
	 * 方法描述:新闻总记录数查询
	 * @param search 查询条件
	 * @return 总记录数
	 */
	long newsCount(NewsSearch search);
	
	/**
	 * 
	 * 方法描述:根据新闻ID查询信息
	 * @param newsId 新闻ID
	 * @return 新闻信息
	 */
	News getNewsById(int newsId);
	
	/**
	 * 
	 * 方法描述:更新点击率
	 * @param newsId
	 * @return
	 */
	int newsClick(int newsId);
	
	/**
	 * 
	 * 方法描述:添加新闻
	 * @param news 新闻内容
	 * @return 受影响行数
	 */
	int newsAdd(News news);
	
	/**
	 * 
	 * 方法描述:修改新闻
	 * @param news 新闻内容
	 * @return
	 */
	int newsUpdate(News news);
	
	/**
	 * 
	 * 方法描述:删除新闻
	 * @param newsId 新闻Id
	 * @return
	 * @throws SQLException 
	 */
	int newsDelete(int newsId) throws SQLException;
	
	/**
	 * 
	 * 方法描述:获取最新新闻
	 * @return
	 */
	List<News> newestNewList();
	
	/**
	 * 
	 * 方法描述:热点新闻
	 * @return
	 */
	List<News> hotNewsList();
	
	/**
	 * 
	 * 方法描述:根据类别ID查询新闻信息
	 * @param typeId
	 * @return
	 */
	List<News> allIndexNewsList(int typeId);
}
