package cn.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import cn.dao.UserDao;
import cn.pojo.City;
import cn.pojo.User;
import cn.utils.JdbcUtils;
import cn.utils.Page;
import cn.utils.StringUtil;

public class UserDaoImpl implements UserDao{

	@Override
	public List<User> selectUsers(User user, Page page) {
		StringBuffer sql=new StringBuffer();
		sql.append("select * from user where 1=1 ");
		List<Object> paramList = new ArrayList<>();
		
		if(StringUtil.isNotEmpty(user.getUsername())){
			sql.append(" and username like ? ");
			paramList.add("%"+user.getUsername()+"%");
		}
		if(StringUtil.isNotEmpty(user.getSex())){
			sql.append(" and sex = ? ");
			paramList.add(user.getSex());
		}

		// 分页
		if (page.getPageNum() != 0) {
			// 分页的sql
			sql.append(" limit ?,? ");
			// 设置起始页(用到小算法)
			int start = (page.getPageNum() - 1) * page.getPageSize();
			paramList.add(start);
			// 设置每页显示的记录数
			paramList.add(page.getPageSize());
		}


		//将List转化为数组
		Object[] params = paramList.toArray();
		List<User> users=null;
		QueryRunner queryRunner=new QueryRunner(JdbcUtils.getDataSource());
		try {
			users=queryRunner.query(sql.toString(), new BeanListHandler<>(User.class), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return users;
	}

	/**
	 * 分页页数的统计
	 */
	@Override
	public long selectCount(User user) {

		// sql
		StringBuffer sql=new StringBuffer();
		// 为了防止and的情况
		sql.append(" select count(*) from user where 1 = 1 ");
		List<Object> paramList = new ArrayList<>();
		if(StringUtil.isNotEmpty(user.getUsername())){
			sql.append(" and username like ? ");
			paramList.add("%"+user.getUsername()+"%");
		}
		if(StringUtil.isNotEmpty(user.getSex())){
			sql.append(" and sex = ? ");
			paramList.add(user.getSex());
		}

		//将List转化为数组
		Object[] params = paramList.toArray();
		Long rows = null;
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			rows = queryRunner.query(sql.toString(), new ScalarHandler<Long>(), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	/**
	 * 城市列表
	 */
	@Override
	public List<City> cityList() {
		// sql
		String sql = "SELECT cityId, cityName FROM city";
		// 初始化填充城市对象的List集合
		List<City> list = null;
		// 创建数据源
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql并返回填充城市对象的List集合
		try {
			list = queryRunner.query(sql, new BeanListHandler<>(City.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 根据用户id删除用户
	 */
	@Override
	public int userDeleteById(int userId) {
		// sql
		String sql = "DELETE FROM `user` WHERE id = ?";
		// 初始化结果
		int result = 0;
		// 创建数据源
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql语句并返回删除的影响行数
		try {
			result = queryRunner.update(sql, userId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 添加用户
	 */
	@Override
	public int userAdd(User user) {
		// sql
		String sql = "INSERT INTO `user` (username, birthday, sex, address) VALUE (?, ?, ?, ?)";
		// 初始化结果
		int result = 0;
		// 创建数组来填充占位符
		Object[] params = {user.getUsername(), user.getBirthday(), user.getSex(), user.getAddress()};
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql并返回影响行数
		try {
			result = queryRunner.update(sql, params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据id批量删除用户
	 */
	@Override
	public int userDeleteSById(String userDeleteSId) {
		// sql
		String sql = "DELETE FROM `user` WHERE id IN(?)";
		// 初始化影响行数
		int result = 0;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql并返回影响行数
		try {
			result = queryRunner.update(sql, userDeleteSId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
