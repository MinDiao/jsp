package com.hpe.service.impl;

import java.util.List;

import com.hpe.dao.IFriendLinkDao;
import com.hpe.dao.impl.FriendLinkDaoImpl;
import com.hpe.po.FriendLink;
import com.hpe.service.IFriendLinkService;
/**
 * 
 * 类描述：友情链接业务逻辑层实现类
 * 作者： Administrator  
 * 创建日期：2018年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class FriendLinkServiceImpl implements IFriendLinkService {
	
	// 注入Dao
	IFriendLinkDao friendLinkDao = new FriendLinkDaoImpl();

	@Override
	public List<FriendLink> selectAll() {
		
		// 查询所有
		return friendLinkDao.selectAll();
	}

	@Override
	public int add(FriendLink friendLink) {
		
		// 添加
		return friendLinkDao.add(friendLink);
	}

	@Override
	public int update(FriendLink friendLink) {
		
		// 更新
		return friendLinkDao.update(friendLink);
	}

	@Override
	public int delete(int friendLinkId) {
		
		// 首先判断是否存在此条友情链接
		long existFriendLinkBy = friendLinkDao.existFriendLinkBy(friendLinkId);
		if (existFriendLinkBy > 0) {
			// 存在则执行删除
			return friendLinkDao.delete(friendLinkId);
		} else {
			// 不存在
			return 0;
		}
	}

	@Override
	public FriendLink selectById(int friendLinkId) {
		
		return friendLinkDao.selectById(friendLinkId);
	}

}
