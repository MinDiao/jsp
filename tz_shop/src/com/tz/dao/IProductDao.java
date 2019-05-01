package com.tz.dao;

import java.util.List;

import com.tz.po.Product;

/**
 * 
 * 接口描述：商品管理数据访问层接口
 * 作者： Administrator  
 * 创建日期：2018年11月24日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface IProductDao {
	
	/**
	 * 
	 * 方法描述:查询所有商品详情内容信息
	 * @return 包含商品管理实体类的集合
	 */
	List<Product> selectProduct();
}
