package com.zw.util;

import java.sql.Connection;
import java.sql.SQLException;

import javax.sql.DataSource;

import com.mchange.v2.c3p0.ComboPooledDataSource;

/**
 * 数据库操作工具类
 * 
 * @author CHENXIANGYANG
 *
 */
public class JdbcUtils {

	// 设置数据源(使用C3P0数据库连接池)
	private static ComboPooledDataSource dataSource = new ComboPooledDataSource("mysql-config");
	// 创建一个与事务相关联的局部线程变量
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	/**
	 * 获取数据源
	 * 
	 * @return
	 */
	public static DataSource getDataSource() {
		return dataSource;
	}

	/**
	 * 获取连接
	 * 
	 * @return
	 * @throws SQLException
	 */
	public static Connection getConnection() throws SQLException {
		// 获取线程局部变量的值connection
		Connection conn = tl.get();
		if (conn == null) {
			// 如果连接为空,则从数据库连接池中获取连接
			conn = dataSource.getConnection();
		}
		return conn;
	}

	/**
	 * 开启事务
	 * 
	 * @throws SQLException
	 */
	public static void beginTransaction() throws SQLException {
		Connection conn = tl.get();
		if (conn != null) {
			throw new SQLException("不能重复开启事务");
		}
		conn = getConnection();
		// 关闭自动提交事务
		conn.setAutoCommit(false);
		// 将线程变量的值设置为connection
		tl.set(conn);
	}

	/**
	 * 提交事务
	 * 
	 * @throws SQLException
	 */
	public static void commitTransaction() throws SQLException {
		Connection conn = tl.get();
		if (conn == null) {
			throw new SQLException("没有开启事务，不能提交");
		}
		// 提交事务
		conn.commit();
		// 关闭连接
		conn.close();
		// 移除此线程局部变量的值
		tl.remove();
	}

	/**
	 * 回滚事务
	 * @throws SQLException
	 */
	public static void rollbackTransaction() throws SQLException {
		Connection conn = tl.get();
		if (conn == null) {
			throw new SQLException("没有开启事务，不能回滚");
		}
		//回滚事务
		conn.rollback();
		//关闭连接
		conn.close();
		// 移除此线程局部变量的值
		tl.remove();
	}
}
