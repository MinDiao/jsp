package com.zw.service;

import java.util.List;

import com.zw.po.Dept;
import com.zw.po.Emp;
import com.zw.po.EmpDept;

/**
 * 
 * 接口描述：员工业务逻辑层接口
 * 作者： Administrator  
 * 创建日期：2018年11月26日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface IEmpService {

	/**
	 * 
	 * 方法描述:查询员工数据的所有信息
	 * @return 包含员工实体类的集合
	 */
	List<EmpDept> empSelectAll();
	
	/**
	 * 
	 * 方法描述:根据员工ID询单个员工的数据
	 * @param empID 员工ID
	 * @return 员工实体类
	 */
	EmpDept empSelectById(int empID);
	
	/**
	 * 
	 * 方法描述:根据部门名称查询部门ID
	 * @param detpId
	 * @return
	 */
	Dept deptSelectByName(String deptId);
	
	/**
	 * 
	 * 方法描述:添加员工
	 * @param emp 员工实体类
	 * @return 影响行数
	 */
	int empAdd(Emp emp);
	
	/**
	 * 
	 * 方法描述:修改员工
	 * @param emp 员工实体类
	 * @return 影响行数
	 */
	int empUpdate(Emp emp);
	
	/**
	 * 
	 * 方法描述:删除员工
	 * @param empID 员工ID
	 * @return
	 */
	int empDelete(int empID);
}
