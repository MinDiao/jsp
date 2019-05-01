package com.hpe.dao;

import java.sql.SQLException;
import java.util.List;

import com.hpe.po.NewsType;

/**
 * 
 * 接口描述：新闻类别数据访问层接口
 * 作者： Administrator  
 * 创建日期：2018年11月21日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface INewsTypeDao {

	/**
	 * 
	 * 方法描述:查询所有新闻类别信息
	 * @return
	 */
	List<NewsType> newsTypeList();
	
	/**
	 * 
	 * 方法描述:根据新闻类别查询新闻ID
	 * @param newsTypeID
	 * @return
	 */
	NewsType getNewsTypeById(int newsTypeID);
	
	/**
	 * 
	 * 方法描述:添加新闻类别
	 * @param newsType 类别名称
	 * @return
	 */
	int newsTypeAdd(NewsType newsType);
	
	/**
	 * 
	 * 方法描述:修改新闻类别
	 * @param newsType 类别名称
	 * @return
	 */
	int newsTypeUpdate(NewsType newsType);
	
	/**
	 * 
	 * 方法描述:删除新闻类别
	 * @param newsType newsTypeID
	 * @return
	 * @throws SQLException 
	 */
	int newsTypeDelete(int newsTypeID) throws SQLException;
	
	/**
	 * 
	 * 方法描述:根据类别名称查询是否存在
	 * @param typeName 类别名称
	 * @return
	 */
	int existNewsTypeNameByName(String typeName);
}
