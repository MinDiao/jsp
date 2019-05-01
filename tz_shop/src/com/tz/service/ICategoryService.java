package com.tz.service;

import java.util.List;

import com.tz.po.Category;

/**
 * 
 * 接口描述：商品类别业务逻辑层接口
 * 作者： Administrator  
 * 创建日期：2018年11月24日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface ICategoryService {

	/**
	 * 
	 * 方法描述:查询所有商品分类
	 * @return 包含商品分类信息实体类的集合
	 */
	List<Category> selectCategoryAll();
	
	/**
	 * 
	 * 方法描述:根据商品分类id指定查询商品信息
	 * @return 分类管理实体类
	 */
	Category selectCategoryById(int categoryId);
	
	/**
	 * 
	 * 方法描述:添加商品分类
	 * @param category 商品分类对象
	 * @return 影响行数
	 */
	int addCategory(Category category);
	
	/**
	 * 
	 * 方法描述:修改商品分类
	 * @param category 商品分类对象
	 * @return 影响行数
	 */
	int updateCategory(Category category);
	
	/**
	 * 
	 * 方法描述:根据商品商品分类id删除单条商品信息
	 * @param categoryId 商品分类id
	 * @return 影响行数
	 */
	int deleteCategoryById(int categoryId);
}
