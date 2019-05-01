package com.zw.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.zw.dao.ReplyDao;
import com.zw.dao.impl.ReplyDaoImpl;
import com.zw.doto.ReplyVo;
import com.zw.pojo.Reply;
import com.zw.service.ReplyService;
import com.zw.util.JdbcUtils;

public class ReplyServiceImpl implements ReplyService {
	
	ReplyDao replyDao = new ReplyDaoImpl();

	/**
	 * 回帖显示
	 */
	@Override
	public List<ReplyVo> select(String msgid) {
		
		return replyDao.select(msgid);
	}

	/**
	 * 回帖
	 */
	@Override
	public int addReply(Reply reply) throws RuntimeException {
		int result = 0;
		try {
			// 开启事务
			JdbcUtils.beginTransaction();
			
			// 回帖
			result += replyDao.addReplyContents(reply);
			
			// 回帖数量+1
			result += replyDao.addReplyCount(reply);
			
			// 提交事务
			JdbcUtils.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				// 回滚事务
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		return result;
	}


}
