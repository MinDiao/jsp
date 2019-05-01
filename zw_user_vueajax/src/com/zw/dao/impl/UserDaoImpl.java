package com.zw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hpe.util.JdbcUtils;
import com.zw.dao.UserDao;
import com.zw.po.User;

public class UserDaoImpl implements UserDao {

	/**
	 * 查询用户列表
	 */
	@Override
	public List<User> userList() {
		
		// 创建查询所有用户列表的sql语句
		String sql = "select id, username, birthday, sex, address FROM `user`";
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 初始化填充用户对象的List集合
		List<User> list = null;
		// 执行sql语句并返回填充用户对象的List集合
		try {
			list = queryRunner.query(sql, new BeanListHandler<>(User.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		// 返回填充用户列表实体类的List集合
		return list;
	}

	/**
	 * 根据模糊用户昵称和性别查询用户列表
	 */
	@Override
	public List<User> userListByNameSex(User userNameSex) {
		
		// 创建根据模糊用户昵称查询用户列表sql语句
		String sql = "SELECT id, username, birthday, sex, address FROM `user` HAVING username LIKE ? AND sex = ?";
		
		// 创建数组来填充占位符
		Object[] params = {"%"+userNameSex.getUsername()+"%", userNameSex.getSex()};
		
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		// 初始化填充用户对象的List集合
		List<User> list = null;
		
		// 执行sql语句并返回填充用户对象的List集合
		try {
			list = queryRunner.query(sql, new BeanListHandler<>(User.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		// 返回填充用户列表实体类的List集合
		return list;
	}

}
