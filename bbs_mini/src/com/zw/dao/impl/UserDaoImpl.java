package com.zw.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zw.dao.UserDao;
import com.zw.pojo.User;
import com.zw.util.JdbcUtils;
/**
 * 
 * 类描述：用户dao实现类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月11日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */

public class UserDaoImpl implements UserDao {
	
	/**
	 * 添加用户
	 */
	@Override
	public int addUser(User user) {
		
		//sql
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO t_user (username,password,sex,hobbys,birthday,city,email,qq,createtime) ");
		sql.append("VALUES (?,?,?,?,?,?,?,?,?) ");
		
		// 创建数据来填充占位符
		Object[] params = {
				user.getUsername(),
				user.getPassword(),
				user.getSex(),
				user.getHobbys(),
				user.getBirthday(),
				user.getCity(),
				user.getEmail(),
				user.getQq(),
				user.getCreatetime()
		};
		
		// 初始化影响行数
		int result= 0 ;
		
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		// 执行sql语句并返回添加用户的影响行数
		try {
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return result;
	}

	/**
	 * 检查用户名是否重复
	 */
	@Override
	public int checkUser(String username) {
		// sql
		StringBuffer sql = new StringBuffer();
		// 拼接sql 
		sql.append("SELECT COUNT(1) FROM t_user ");
		sql.append("WHERE username = ? ");
		// 初始化结果
		int result = 0;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql语句并返回查询是否存在此用户的结果
		try {
			result = queryRunner.query(sql.toString(), new ScalarHandler<Long>(), username).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public User login(User user) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT * FROM t_user ");
		sql.append("WHERE username = ? ");
		sql.append("AND `password` = ? ");
		
		// 初始化用户实体类
		User loginUser = null;
		
		// 创建数组用来填充占位符
		Object[] params = {user.getUsername(), user.getPassword()};
		
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		
		// 执行sql并返回用户对象
		try {
			loginUser = queryRunner.query(sql.toString(), new BeanHandler<>(User.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return loginUser;
	}
	
}
