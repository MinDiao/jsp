package com.zw.dao;

import java.util.List;

import com.zw.doto.MessageDto;
import com.zw.doto.MessageVo;
import com.zw.pojo.Message;
import com.zw.util.Page;

/**
 * 
 * 接口描述：首页数据访问层接口
 * 作者： LiuJinrong  
 * 创建日期：2018年12月12日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface IndexDao {

	/**
	 * 
	 * 方法描述:首页内容列表
	 * @return 填充首页内容对象的List集合
	 */
	List<MessageVo> indexList(MessageDto messageDto, Page page);

	/**
	 * 
	 * 方法描述:分页
	 * @param index
	 * @return
	 */
	long selectCount(MessageDto messageDto);

	/**
	 * 
	 * 方法描述:新增帖子
	 * @param msg
	 * @return
	 */
	int add(Message msg);

	/**
	 * 
	 * 方法描述:查询帖子详情
	 * @param msgid
	 * @return
	 */
	MessageVo selectInfo(String msgid);

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
