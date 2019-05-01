package com.zw.service;

import java.util.List;

import com.zw.po.User;

/**
 * 
 * 接口描述：用户业务逻辑层接口
 * 作者： LiuJinrong  
 * 创建日期：2018年12月9日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface UserService {

	/**
	 * 
	 * 方法描述:查询用户列表
	 * @return 填充用户列表实体类的List集合
	 */
	List<User> userList();

	/**
	 * 
	 * 方法描述:根据模糊用户昵称和性别查询用户列表
	 * @return
	 */
	List<User> userListByNameSex(User userNameSex);
	
}
