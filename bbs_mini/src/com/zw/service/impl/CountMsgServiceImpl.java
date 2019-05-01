package com.zw.service.impl;

import com.zw.dao.CountMsgDao;
import com.zw.dao.impl.CountMsgDaoImpl;
import com.zw.pojo.CountMsg;
import com.zw.service.CountMsgService;
/**
 * 
 * 类描述：帖子访问点击业务逻辑层实现类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月13日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class CountMsgServiceImpl implements CountMsgService {
	
	// 注入Dao
	CountMsgDao cmDao = new CountMsgDaoImpl();

	@Override
	public int add(CountMsg countMsg) throws Exception{
		
		return cmDao.add(countMsg);
	}

	/**
	 * 修改访问量
	 */
	@Override
	public int update(String msgid) {
		
		return cmDao.update(msgid);
	}

}
