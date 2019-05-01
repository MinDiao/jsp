package com.hpe.dao;

import java.util.List;

import com.hpe.po.FriendLink;

/**
 * 
 * 接口描述：友情链接数据访问层接口
 * 作者： Administrator  
 * 创建日期：2018年11月21日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface IFriendLinkDao {
	
	/**
	 * 
	 * 方法描述:查询所有友情链接
	 * @return 包含友情链接实体类的List集合
	 */
	List<FriendLink> selectAll();
	
	/**
	 * 
	 * 方法描述:添加友情链接
	 * @param friendLink 友情链接实体类
	 * @return 执行行数
	 */
	int add(FriendLink friendLink);
	
	/**
	 * 
	 * 方法描述:修改友情链接
	 * @param friendLink 友情链接实体类
	 * @return 执行行数
	 */
	int update(FriendLink friendLink);
	

	/**
	 * 
	 * 方法描述:根据友情链接id查询友情链接实体类对象
	 * @param friendLinkID
	 * @return
	 */
	FriendLink selectById(int friendLinkId);
	
	/**
	 * 
	 * 方法描述:删除友情链接
	 * @param FriendLinkID 友情链接编号
	 * @return 执行行数
	 */
	int delete(int friendLinkId);
	
	/**
	 * 
	 * 方法描述:根据友情链接编号查询是否存在此友情链接
	 * @param FriendLinkID  友情链接编号
	 * @return 是否存在
	 */
	long existFriendLinkBy(int friendLinkId);
	
}
