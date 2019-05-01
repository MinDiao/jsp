package com.zw.service.impl;

import java.util.List;

import com.zw.dao.UserDao;
import com.zw.dao.impl.UserDaoImpl;
import com.zw.po.User;
import com.zw.service.UserService;

/**
 * 
 * 类描述：用户业务逻辑层实现类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月9日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class UserServiceImpl implements UserService {

	// 注入用户Dao层
	UserDao userDao = new UserDaoImpl();
	
	/**
	 * 查询用户列表
	 */
	@Override
	public List<User> userList() {
		
		return userDao.userList();
	}

	/**
	 * 根据模糊用户昵称和性别查询用户列表
	 */
	@Override
	public List<User> userListByNameSex(User userNameSex) {
		
		return userDao.userListByNameSex(userNameSex);
	}

}
