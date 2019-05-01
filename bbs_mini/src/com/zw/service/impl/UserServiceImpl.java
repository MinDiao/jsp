package com.zw.service.impl;

import com.zw.dao.UserDao;
import com.zw.dao.impl.UserDaoImpl;
import com.zw.pojo.User;
import com.zw.service.UserService;

public class UserServiceImpl implements UserService {
	
	// 注入Dao
	UserDao userDao = new UserDaoImpl();

	/**
	 * 注册用户
	 */
	@Override
	public int addUser(User user) throws RuntimeException {
		int res = 0;
		// 注册前再次确认用户名是否重复
		int flag = checkUser(user.getUsername());
		// 判断
		if (flag == 0) {
			res = userDao.addUser(user);
		}
		return res;
	}

	/**
	 * 检查用户名是否重复
	 */
	@Override
	public int checkUser(String username) throws RuntimeException {
		
		return userDao.checkUser(username);
	}

	@Override
	public User login(User user) throws RuntimeException {
		
		return userDao.login(user);
	}

}
