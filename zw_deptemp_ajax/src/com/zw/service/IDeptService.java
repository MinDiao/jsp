package com.zw.service;

import java.util.List;

import com.zw.po.Dept;

/**
 * 
 * 
 * 接口描述：部门业务逻辑层接口
 * 作者： Administrator  
 * 创建日期：2018年11月27日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface IDeptService {
	
	/**
	 * 
	 * 方法描述:查询所有部门
	 * @return
	 */
	List<Dept> deptSelectAll();
}
