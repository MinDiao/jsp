package com.zw.service;

import java.util.List;

import com.zw.doto.MessageDto;
import com.zw.doto.MessageVo;
import com.zw.pojo.Message;
import com.zw.util.Page;

/**
 * 
 * 类接口：首页业务逻辑层接口
 * 作者： LiuJinrong  
 * 创建日期：2018年12月12日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface IndexService {

	/**
	 * 
	 * 方法描述:首页内容列表
	 * @return 填充首页内容对象的List集合
	 */
	Page indexList(MessageDto messageDto, Page page) throws RuntimeException;

	/**
	 * 
	 * 方法描述:添加帖子
	 * @param msg 帖子实体类
	 * @return 影响行数
	 */
	int add(Message msg) throws RuntimeException;

	/**
	 * 
	 * 方法描述:查询帖子详情
	 * @param msgid
	 * @return
	 */
	MessageVo selectInfo(String msgid) throws RuntimeException;

	/**
	 * 
	 * 方法描述:按访问量排序
	 * @return 填充首页列表实体类的List集合
	 */
	List<MessageVo> visitOrder();

	/**
	 * 
	 * 方法描述:按回复量排序
	 * @return 填充首页列表实体类的List集合
	 */
	List<MessageVo> replyOrder();

	/**
	 * 
	 * 方法描述:按帖子id排序
	 * @return
	 */
	List<MessageVo> idOrder();
}
