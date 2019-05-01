package com.hpe.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import com.hpe.dao.IUserDao;
import com.hpe.po.User;
import com.hpe.util.JdbcUtils;

/**
 * 
 * 类描述：用户数据访问层实现类
 * 作者： Administrator  
 * 创建日期：2018年11月20日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class UserDaoImpl implements IUserDao {

	@Override
	public User login(User user) {
		// 编写sql语句
		String sql = "SELECT userId, userName, `password` FROM t_user WHERE userName = ? AND `password` = ?";
		// 填充占位符
		Object[] params = {user.getUserName(), user.getPassword()};
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		User resultUser = null;
		try {
			resultUser = queryRunner.query(sql, new BeanHandler<>(User.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return resultUser;
	}

}
