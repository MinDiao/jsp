package com.zw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hpe.util.JdbcUtils;
import com.zw.dao.IDeptDao;
import com.zw.po.Dept;
/**
 * 
 * 类描述：部门数据访问层实现类
 * 作者： Administrator  
 * 创建日期：2018年11月27日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class DeptDaoImpl implements IDeptDao {

	@Override
	public List<Dept> deptSelectAll() {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT deptId, deptName ");
		sql.append("FROM dept ");
		// 初始化包含部门对象的集合
		List<Dept> list = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql并返回影响行数
		try {
			list = queryRunner.query(sql.toString(), new BeanListHandler<>(Dept.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
