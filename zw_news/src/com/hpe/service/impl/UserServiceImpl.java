package com.hpe.service.impl;

import com.hpe.dao.IUserDao;
import com.hpe.dao.impl.UserDaoImpl;
import com.hpe.po.User;
import com.hpe.service.IUserService;

/**
 * 
 * 类描述：用户业务逻辑实现类
 * 作者： Administrator  
 * 创建日期：2018年11月20日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class UserServiceImpl implements IUserService {
	
	// 注入Dao 父类的引用指向子类的实例
	IUserDao userDao = new UserDaoImpl();
	
	@Override
	public User login(User user) {
		
		return userDao.login(user);
	}

}
