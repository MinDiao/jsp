package com.zw.service;

import com.zw.pojo.User;

/**
 * 
 * 接口描述：用户模块的服务接口
 * 作者： LiuJinrong  
 * 创建日期：2018年12月11日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface UserService {

	/**
	 * 
	 * 方法描述:注册用户
	 * @param user 用户实体类
	 * @return 影响行数
	 */
	int addUser(User user) throws RuntimeException;

	/**
	 * 
	 * 方法描述:检查用户名是否重复
	 * @param username
	 * @return
	 */
	int checkUser(String username) throws RuntimeException;

	/**
	 * 
	 * 方法描述:用户登录
	 * @param user 填充用户名和密码的用户对象
	 * @return 用户实体类
	 */
	User login(User user) throws RuntimeException;
}
