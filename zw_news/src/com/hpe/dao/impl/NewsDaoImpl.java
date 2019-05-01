package com.hpe.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hpe.dao.INewsDao;
import com.hpe.po.News;
import com.hpe.po.NewsSearch;
import com.hpe.po.PageBean;
import com.hpe.util.JdbcUtils;
import com.hpe.util.StringUtil;
/**
 * 
 * 类描述：新闻数据访问实现类
 * 作者： Administrator  
 * 创建日期：2018年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class NewsDaoImpl implements INewsDao {

	@Override
	public List<News> newsList(NewsSearch search, PageBean pageBean) {

		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM t_news t1 ");
		sql.append("INNER JOIN t_newstype t2 ");
		sql.append("ON t1.typeId = t2.newsTypeId ");
		sql.append("WHERE 1=1 ");

		List<Object> paramList = new ArrayList<Object>();

		// 根据搜索条件拼接sql语句
		if (search != null) {
			// 不等于null,说明内部有内容
			// 新闻类别
			int typeId = search.getTypeId();
			// 新闻标题
			String title = search.getTitle();
			// 开始日期
			String startDate = search.getStartDate();
			// 结束日期
			String endDate = search.getEndDate();

			// 判断
			if (typeId != -1) {
				sql.append("AND t1.typeId=? ");
				paramList.add(typeId);
			}
			if (StringUtil.isNotEmpty(title)) {
				sql.append("AND t1.title LIKE ? ");
				// 不需要单引号
				paramList.add("%"+title+"%"); 
			}
			if (StringUtil.isNotEmpty(startDate)) {
				sql.append("AND TO_DAYS(t1.publishDate) >= TO_DAYS(?) ");
				paramList.add(startDate);
			}
			if (StringUtil.isNotEmpty(endDate)) {
				sql.append("AND TO_DAYS(t1.publishDate) <= TO_DAYS(?) ");
				paramList.add(endDate);
			}
		}

		sql.append("ORDER BY t1.publishDate DESC ");
		// ?起始页,?每页显示几条数据
		sql.append("LIMIT ?,? ");
		paramList.add(pageBean.getStart());
		paramList.add(pageBean.getPageSize());

		// 将List集合转化为数组
		Object[] params = paramList.toArray();

		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 初始化集合
		List<News> list = null;
		try {
			list = queryRunner.query(sql.toString(), new BeanListHandler<>(News.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public long newsCount(NewsSearch search) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT COUNT(*) AS total FROM t_news ");
		sql.append("WHERE 1=1 ");
		List<Object> paramList = new ArrayList<Object>();

		// 根据搜索条件拼接sql语句
		if (search != null) {
			// 不等于null,说明内部有内容
			// 新闻类别
			int typeId = search.getTypeId();
			// 新闻标题
			String title = search.getTitle();
			// 开始日期
			String startDate = search.getStartDate();
			// 结束日期
			String endDate = search.getEndDate();

			// 判断
			if (typeId != -1) {
				sql.append("AND typeId=? ");
				paramList.add(typeId);
			}
			if (StringUtil.isNotEmpty(title)) {
				sql.append("AND title LIKE ? ");//TODO
				// 不需要单引号
				paramList.add("%"+title+"%"); 
			}
			if (StringUtil.isNotEmpty(startDate)) {
				sql.append("AND TO_DAYS(publishDate) >= TO_DAYS(?) ");
				paramList.add(startDate);
			}
			if (StringUtil.isNotEmpty(endDate)) {
				sql.append("AND TO_DAYS(publishDate) <= TO_DAYS(?) ");
				paramList.add(endDate);
			}
		}
		// 将List转化为数组
		Object[] params = paramList.toArray();
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		long count = 0;
		try {
			count = queryRunner.query(sql.toString(), new ScalarHandler<Long>(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return count;
	}

	@Override
	public News getNewsById(int newsId) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM t_news t1,t_newstype t2 ");
		sql.append("WHERE t1.typeId=t2.newsTypeId AND t1.newsId=? ");
		// 初始化对象
		News news = null;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行并返回对象
		try {
			news = queryRunner.query(sql.toString(), new BeanHandler<>(News.class), newsId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return news;
	}

	@Override
	public int newsClick(int newsId) {
		// sql
		String sql = "UPDATE t_news SET click = click + 1 WHERE newsId = ?";
		int result = 0;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			result = queryRunner.update(sql, newsId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int newsAdd(News news) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO t_news ");
		sql.append("(title,content,publishDate,author,typeId,click,isHead,isHot) ");
		sql.append("VALUES(?,?,NOW(),?,?,0,?,?) ");
		Object[] params = {
				news.getTitle(),
				news.getContent(),
				news.getAuthor(),
				news.getTypeId(),
				news.getIsHead(),
				news.getIsHot()
		};
		// 定义返回值变量
		int result = 0;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int newsUpdate(News news) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE t_news ");
		sql.append("SET title=?,content=?,author=?,typeId=?,isHead=?,isHot=? ");
		sql.append("WHERE newsId=? ");
		Object[] params = {
				news.getTitle(),
				news.getContent(),
				news.getAuthor(),
				news.getTypeId(),
				news.getIsHead(),
				news.getIsHot(),
				news.getNewsId()
		};
		// 定义返回值变量
		int result = 0;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int newsDelete(int newsId) throws SQLException {
		// sql
		String sql = "DELETE FROM t_news WHERE newsId = ?";
		int result = 0;
		QueryRunner queryRunner = new QueryRunner();
		// 为了保证在同一个连接上
		result = queryRunner.update(JdbcUtils.getConnection(), sql, newsId);
		return result;
	}

	@Override
	public List<News> newestNewList() {
		// 获取最新新闻:根据日期来获取
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM t_news ");
		sql.append("ORDER BY publishDate DESC ");
		sql.append("LIMIT 0,8 ");
		List<News> list = null;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			list = queryRunner.query(sql.toString(), new BeanListHandler<>(News.class));//params是一个可变值,可以写为null
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<News> hotNewsList() {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM t_news WHERE isHot = 1 ");
		sql.append("ORDER BY publishDate DESC ");
		sql.append("LIMIT 0, 8  ");
		List<News> list = null;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			list = queryRunner.query(sql.toString(), new BeanListHandler<>(News.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public List<News> allIndexNewsList(int typeId) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM t_news, t_newstype ");
		sql.append("WHERE t_news.typeId = t_newstype.newsTypeId ");
		sql.append("AND typeId = ? ");
		sql.append("ORDER BY publishDate DESC LIMIT 0, 8 ");
		List<News> list = null;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			list = queryRunner.query(sql.toString(), new BeanListHandler<>(News.class), typeId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
