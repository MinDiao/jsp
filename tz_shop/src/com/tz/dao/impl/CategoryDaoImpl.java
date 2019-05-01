package com.tz.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hpe.util.JdbcUtils;
import com.tz.dao.ICategoryDao;
import com.tz.po.Category;
/**
 * 
 * 类描述：分类管理数据访问层实现类
 * 作者： Administrator  
 * 创建日期：2018年11月24日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class CategoryDaoImpl implements ICategoryDao {

	/**
	 * 查询所有商品分类
	 */
	@Override
	public List<Category> selectCategoryAll() {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT cid, cname ");
		sql.append("FROM category ");
		// 初始化集合
		List<Category> list = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行并返回集合
		try {
			list = queryRunner.query(sql.toString(), new BeanListHandler<>(Category.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/**
	 * 根据商品分类id指定查询商品信息
	 */
	@Override
	public Category selectCategoryById(int categoryId) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT cid, cname ");
		sql.append("FROM category ");
		sql.append("WHERE cid = ? ");
		// 初始化实体类
		Category category = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行并返回对象
		try {
			category = queryRunner.query(sql.toString(), new BeanHandler<>(Category.class), categoryId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return category;
	}

	/**
	 * 添加商品分类
	 */
	@Override
	public int addCategory(Category category) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO category ");
		sql.append("(cname) ");
		sql.append("VALUES (?) ");
		// 定义返回变量
		int result = 0;
		// 定义数组填充占位符
		Object[] params = {category.getCname()};
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行添加并返回影响行数
		try {
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 修改商品分类
	 */
	@Override
	public int updateCategory(Category category) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE category ");
		sql.append("SET cname = ? ");
		sql.append("WHERE cid = ? ");
		// 定义返回变量
		int result = 0;
		// 定义数组来填充占位符
		Object[] params = {category.getCname(), category.getCid()};
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行修改并返回影响行数
		try {
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 根据商品商品分类id 删除单条商品信息
	 */
	@Override
	public int deleteCategoryById(int categoryId) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM category ");
		sql.append("WHERE cid = ? ");
		// 定义返回变量
		int result = 0;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			result = queryRunner.update(sql.toString(), categoryId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

}
