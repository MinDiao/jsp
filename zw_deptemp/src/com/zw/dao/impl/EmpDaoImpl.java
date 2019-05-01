package com.zw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.hpe.util.JdbcUtils;
import com.zw.dao.IEmpDao;
import com.zw.po.Dept;
import com.zw.po.Emp;
import com.zw.po.EmpDept;

/**
 * 
 * 类描述：员工数据访问层实现类
 * 作者： Administrator  
 * 创建日期：2018年11月26日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class EmpDaoImpl implements IEmpDao {

	@Override
	public List<EmpDept> empSelectAll() {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT e.EmpID, e.EmpName, e.deptId, e.EmpBirthday, e.Empphone, d.deptName ");
		sql.append("FROM emp e ");
		sql.append("INNER JOIN dept d ");
		sql.append("ON e.deptId = d.deptId ");
		// 初始化集合
		List<EmpDept> list = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql并返回集合
		try {
			list = queryRunner.query(sql.toString(), new BeanListHandler<>(EmpDept.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public EmpDept empSelectById(int empID) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT e.EmpID, e.EmpName, e.deptId, e.EmpBirthday, e.Empphone, d.deptName ");
		sql.append("FROM emp e ");
		sql.append("INNER JOIN dept d ");
		sql.append("ON e.deptId = d.deptId ");
		sql.append("HAVING e.EmpID = ? ");
		// 初始化实体类
		EmpDept empDept = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql并返回对象
		try {
			empDept = queryRunner.query(sql.toString(), new BeanHandler<>(EmpDept.class), empID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return empDept;
	}
	
	@Override
	public Dept deptSelectByName(String deptId) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT deptId, deptName ");
		sql.append("FROM dept ");
		sql.append("WHERE deptName = ? ");
		// 初始化实体类
		Dept dept = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql并返回对象
		try {
			dept = queryRunner.query(sql.toString(), new BeanHandler<>(Dept.class), deptId);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return dept;
	}

	@Override
	public int empAdd(Emp emp) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO emp ");
		sql.append("(EmpName, deptId, EmpBirthday, Empphone) ");
		sql.append("VALUES ");
		sql.append("(?, ?, ?, ?) ");
		// 初始化影响行数
		int result = 0;
		// 定义数组用来填充占位符
		Object[] params = {
				emp.getEmpName(), 
				emp.getDeptId(), 
				emp.getEmpBirthday(), 
				emp.getEmpphone()
		};
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql并返回对象
		try {
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int empUpdate(Emp emp) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE emp ");
		sql.append("SET EmpName = ?, deptId = ?, EmpBirthday = ?, Empphone = ? ");
		sql.append("WHERE EmpID = ? ");
		// 初始化执行结果
		int result = 0;
		// 定义数组用来填充占位符
		Object[] params = {
				emp.getEmpName(),
				emp.getDeptId(),
				emp.getEmpBirthday(),
				emp.getEmpphone(),
				emp.getEmpID()
		};
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql并返回影响行数
		try {
			result = queryRunner.update(sql.toString(), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}

	@Override
	public int empDelete(int empID) throws SQLException {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM emp ");
		sql.append("WHERE EmpID = ? ");
		// 初始化执行结果
		int result = 0;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner();
		// 执行删除sql并返回影响行数
		result = queryRunner.update(JdbcUtils.getConnection(), sql.toString(), empID);
		return result;
	}

}
