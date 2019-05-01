package com.zw.dao;

import com.zw.pojo.CountMsg;

/**
 * 
 * 接口描述：帖子访问点击数据访问层接口
 * 作者： LiuJinrong  
 * 创建日期：2018年12月13日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface CountMsgDao {

	/**
	 * 
	 * 方法描述:添加统计数据
	 * @param countMsg
	 */
	int add(CountMsg countMsg) throws Exception;

	/**
	 * 
	 * 方法描述:修改访问量
	 * @param msgid
	 * @return
	 */
	int update(String msgid);

}
