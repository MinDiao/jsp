package com.zw.service.impl;

import java.util.List;

import com.zw.dao.IDeptDao;
import com.zw.dao.impl.DeptDaoImpl;
import com.zw.po.Dept;
import com.zw.service.IDeptService;
/**
 * 
 * 类描述：部门业务逻辑层实现类
 * 作者： Administrator  
 * 创建日期：2018年11月27日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class DeptServiceImpl implements IDeptService {
	
	// 注入Service
	IDeptDao deptDao = new DeptDaoImpl();

	@Override
	public List<Dept> deptSelectAll() {
		
		return deptDao.deptSelectAll();
	}

}
