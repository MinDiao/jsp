package com.zw.dao;

import java.sql.SQLException;
import java.util.List;

import com.zw.doto.ReplyVo;
import com.zw.pojo.Reply;

/**
 * 
 * 类描述：回帖
 * 作者： LiuJinrong  
 * 创建日期：2018年12月14日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public interface ReplyDao {

	/**
	 * 
	 * 方法描述:回帖显示
	 * @param msgid
	 * @return
	 */
	List<ReplyVo> select(String msgid);

	/**
	 * 
	 * 方法描述:回帖
	 * @param reply 回帖实体类
	 * @throws SQLException 事务
	 * @return 影响行数
	 */
	int addReplyContents(Reply reply) throws SQLException;

	/**
	 * 
	 * 方法描述:回帖数量+1
	 * @param i 
	 * @return 影响行数
	 * @throws SQLException 事务
	 */
	int addReplyCount(Reply reply) throws SQLException;

}
