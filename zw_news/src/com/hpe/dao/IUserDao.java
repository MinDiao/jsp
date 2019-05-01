package com.hpe.dao;

import com.hpe.po.User;

/**
 * 
 * 接口描述：用户数据访问接口
 * 作者： Administrator  
 * 创建日期：2018年11月20日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface IUserDao {
	
	/**
	 * 
	 * 方法描述:用户登录
	 * @param user 用户信息(用户名和密码)
	 * @return 用户信息
	 */
	public User login(User user);
}
