package com.tz.service.impl;

import java.util.List;

import com.tz.dao.ICategoryDao;
import com.tz.dao.impl.CategoryDaoImpl;
import com.tz.po.Category;
import com.tz.service.ICategoryService;

public class CategoryServiceImpl implements ICategoryService {
	
	// 注入Dao
	private ICategoryDao categoryDao = new CategoryDaoImpl();

	/**
	 * 查询所有商品分类
	 */
	@Override
	public List<Category> selectCategoryAll() {
		
		return categoryDao.selectCategoryAll();
	}

	@Override
	public Category selectCategoryById(int categoryId) {
		
		return categoryDao.selectCategoryById(categoryId);
	}

	@Override
	public int addCategory(Category category) {
		
		return categoryDao.addCategory(category);
	}

	@Override
	public int updateCategory(Category category) {
		
		return categoryDao.updateCategory(category);
	}

	@Override
	public int deleteCategoryById(int categoryId) {
		
		return categoryDao.deleteCategoryById(categoryId);
	}

}
