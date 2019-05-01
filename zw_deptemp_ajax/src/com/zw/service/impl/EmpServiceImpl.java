package com.zw.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.zw.dao.IEmpDao;
import com.zw.dao.impl.EmpDaoImpl;
import com.zw.po.Dept;
import com.zw.po.Emp;
import com.zw.po.EmpDept;
import com.zw.service.IEmpService;
/**
 * 
 * 类描述：员工业务逻辑层实现类
 * 作者： Administrator  
 * 创建日期：2018年11月26日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class EmpServiceImpl implements IEmpService {
	
	// 注入Dao
	IEmpDao empDao = new EmpDaoImpl();

	@Override
	public List<EmpDept> empSelectAll() {
		
		return empDao.empSelectAll();
	}
	
	@Override
	public EmpDept empSelectById(int empID) {
		
		return empDao.empSelectById(empID);
	}

	@Override
	public Dept deptSelectByName(String deptId) {
		
		return empDao.deptSelectByName(deptId);
	}

	@Override
	public int empAdd(Emp emp) {
		
		return empDao.empAdd(emp);
	}

	@Override
	public int empUpdate(Emp emp) {
		
		return empDao.empUpdate(emp);
	}

	@Override
	public int empDelete(int empID) {
		int result = 0;
		try {
			result = empDao.empDelete(empID);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
}
