package com.hpe.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.hpe.dao.IFriendLinkDao;
import com.hpe.po.FriendLink;
import com.hpe.util.JdbcUtils;
/**
 * 
 * 类描述:友情链接数据访问层实现类
 * 作者： Administrator  
 * 创建日期：2018年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class FriendLinkDaoImpl implements IFriendLinkDao {

	@Override
	public List<FriendLink> selectAll() {
		// sql
		String sql = "SELECT linkId,linkName,linkUrl,linkEmail FROM t_link";
		// 初始化List集合
		List<FriendLink> list = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql语句并返回包含实体类对象集合
		try {
			list = queryRunner.query(sql, new BeanListHandler<>(FriendLink.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public int add(FriendLink friendLink) {
		// sql
		String sql = "INSERT INTO t_link(linkId,linkName,linkUrl,linkEmail) VALUES(?,?,?,?)";
		// 初始化影响行数
		int result = 0;
		// 创建填充实体类属性数组,用来填充占位符
		Object[] params = {friendLink.getLinkId(),friendLink.getLinkName(),friendLink.getLinkUrl(),friendLink.getLinkEmail()};
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行并返回影响行数
		try {
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int update(FriendLink friendLink) {
		// sql
		String sql = "UPDATE t_link SET linkName=?,linkUrl=?,linkEmail=? WHERE linkId=?";
		// 初始化影响行数
		int result = 0;
		// 定义数组填充占位符
		Object[] params = {friendLink.getLinkName(),friendLink.getLinkUrl(),friendLink.getLinkEmail(),friendLink.getLinkId()};
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行并返回影响行数
		try {
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@Override
	public FriendLink selectById(int friendLinkId) {
		// sql
		String sql = "SELECT linkId,linkName,linkUrl,linkEmail FROM t_link WHERE linkId=?";
		// 初始化链接名称属性
		FriendLink friendLink = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行并返回名称属性
		try {
			friendLink = queryRunner.query(sql, new BeanHandler<>(FriendLink.class), friendLinkId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return friendLink;
	}

	@Override
	public int delete(int friendLinkId) {
		// sql
		String sql = "DELETE FROM t_link WHERE linkId=?";
		// 初始化影响行数
		int result = 0;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行并返回影响行数
		try {
			result = queryRunner.update(sql, friendLinkId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public long existFriendLinkBy(int friendLinkId) {
		// sql
		String sql = "SELECT COUNT(1) AS count from t_link WHERE linkId=?";
		// 初始化影响行数
		long result = 0;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行返回影响行数
		try {
			result = queryRunner.query(sql, new ScalarHandler<Long>(), friendLinkId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
