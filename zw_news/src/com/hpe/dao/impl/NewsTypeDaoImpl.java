package com.hpe.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hpe.dao.INewsTypeDao;
import com.hpe.po.NewsType;
import com.hpe.util.JdbcUtils;
/**
 * 
 * 类描述：新闻类别数据访问层实现类
 * 作者： Administrator  
 * 创建日期：2018年11月21日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class NewsTypeDaoImpl implements INewsTypeDao {

	@Override
	public List<NewsType> newsTypeList() {
		String sql = "SELECT newsTypeId, typeName FROM t_newstype";
		List<NewsType> list = null;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			list = queryRunner.query(sql, new BeanListHandler<>(NewsType.class));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public NewsType getNewsTypeById(int newsTypeID) {
		String sql = "SELECT newsTypeId, typeName FROM t_newstype WHERE newsTypeId=?";
		NewsType newsType = null;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			newsType = queryRunner.query(sql, new BeanHandler<>(NewsType.class), newsTypeID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return newsType;
	}

	@Override
	public int newsTypeAdd(NewsType newsType) {
		String sql = "INSERT INTO t_newstype(typeName) VALUES(?)";
		int result = 0;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			result = queryRunner.update(sql, newsType.getTypeName());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int newsTypeUpdate(NewsType newsType) {
		String sql = "UPDATE t_newstype SET typeName=? WHERE newsTypeId=?";
		int result = 0;
		//Object[] params = {newsType.getNewsTypeId(), newsType.getTypeName()};
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			result = queryRunner.update(sql, newsType.getTypeName(), newsType.getNewsTypeId());
			//result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int newsTypeDelete(int newsTypeID) throws SQLException {
		String sql = "DELETE FROM t_newstype WHERE newsTypeId=?";
		int result = 0;
		QueryRunner queryRunner = new QueryRunner();
		result = queryRunner.update(JdbcUtils.getConnection(), sql, newsTypeID);
		return result;
	}

	@Override
	public int existNewsTypeNameByName(String typeName) {
		String sql = "SELECT COUNT(1) AS COUNT FROM t_newstype WHERE typeName=?";
		int result = 0;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			// 返回单列值
			result = (int)queryRunner.query(sql, new ScalarHandler<>(), typeName);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
