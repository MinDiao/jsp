package com.hpe.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.hpe.dao.INewsTypeDao;
import com.hpe.dao.impl.NewsTypeDaoImpl;
import com.hpe.po.NewsType;
import com.hpe.service.INewsTypeService;
import com.hpe.util.JdbcUtils;
/**
 * 
 * 类描述：新闻类别业务逻辑层实现类
 * 作者： Administrator  
 * 创建日期：2018年11月21日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class NewsTypeServiceImpl implements INewsTypeService {
	
	// 注入Dao
	INewsTypeDao newsTypeDao = new NewsTypeDaoImpl();

	@Override
	public List<NewsType> newsTypeList() {
		return newsTypeDao.newsTypeList();
	}

	@Override
	public NewsType getNewsTypeById(int newsTypeID) {
		return newsTypeDao.getNewsTypeById(newsTypeID);
	}

	@Override
	public int newsTypeAdd(NewsType newsType) {
		return newsTypeDao.newsTypeAdd(newsType);
	}

	@Override
	public int newsTypeUpdate(NewsType newsType) {
		return newsTypeDao.newsTypeUpdate(newsType);
	}

	@Override
	public boolean newsTypeDelete(int newsTypeID) {
		// 定义一个状态位
		boolean flag = false;
		try {
			// 开启事务
			JdbcUtils.beginTransaction();
			// 执行sql语句
			// 1.删除类别对应的新闻信息
			// TODO
			
			// 2.删除响应的类别信息
			newsTypeDao.newsTypeDelete(newsTypeID);
			// 3.提交事务
			JdbcUtils.commitTransaction();
			// 4.将状态位改为true
			flag = true;
		} catch (Exception e) {
			// 回滚事务
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		}
		return flag;
	}

}
