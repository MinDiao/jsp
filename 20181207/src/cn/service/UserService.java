package cn.service;

import java.util.List;

import cn.pojo.City;
import cn.pojo.User;
import cn.utils.Page;

public interface UserService {

	Page selectUsers(User user, Page page);

	/**
	 * 
	 * 方法描述:城市列表
	 * @return 填充城市对象的List集合
	 */
	List<City> cityList();

	/**
	 * 
	 * 方法描述:根据用户id删除用户
	 * @param userId 用户id
	 * @return
	 */
	int userDeleteById(int userId);

	/**
	 * 
	 * 方法描述:添加用户
	 * @param user 用户实体类
	 * @return 影响行数
	 */
	int userAdd(User user);

	/**
	 * 
	 * 方法描述:根据多个Id批量删除用户
	 * @param userDeleteSId 多个用户Id
	 * @return 影响行数
	 */
	int userDeleteSById(String userDeleteSId);
}
