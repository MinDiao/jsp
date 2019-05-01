package cn.service.impl;

import java.util.List;

import cn.dao.UserDao;
import cn.dao.impl.UserDaoImpl;
import cn.pojo.City;
import cn.pojo.User;
import cn.service.UserService;
import cn.utils.Page;

public class UserServiceImpl implements UserService{

	private UserDao userDao = new UserDaoImpl();

	@Override
	public Page selectUsers(User user, Page page) {
		
		// 分页查询出的用户
		List<User> users = userDao.selectUsers(user, page);
		
		// 查询一共多少页
		// 一共多少条记录
		long rows = userDao.selectCount(user);
		
		// 多少页的计算
		long totalPage = rows % page.getPageSize()==0?rows/page.getPageSize():rows/page.getPageSize()+1;
		
		// 返回数据的Page封装
		page.setData(users);
		page.setRows(rows);
		page.setTotalPage(totalPage);
		
		return page;
	}

	@Override
	public List<City> cityList() {
		
		return userDao.cityList();
	}

	/**
	 * 根据用户id删除用户
	 */
	@Override
	public int userDeleteById(int userId) {
		
		return userDao.userDeleteById(userId);
	}

	/**
	 * 添加用户
	 */
	@Override
	public int userAdd(User user) {
		
		return userDao.userAdd(user);
	}

	/**
	 * 根据多个Id批量删除用户
	 */
	@Override
	public int userDeleteSById(String userDeleteSId) {
		
		return userDao.userDeleteSById(userDeleteSId);
	}

}
