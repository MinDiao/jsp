package com.zw.service;

import java.sql.SQLException;
import java.util.List;

import com.zw.doto.ReplyVo;
import com.zw.pojo.Reply;

public interface ReplyService {

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
	 * @param reply 回帖对象
	 * @return 影响行数
	 * @throws SQLException 
	 */
	int addReply(Reply reply) throws RuntimeException;

}
