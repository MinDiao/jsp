package com.zw.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import com.zw.dao.ReplyDao;
import com.zw.doto.ReplyVo;
import com.zw.pojo.Reply;
import com.zw.util.JdbcUtils;
/**
 * 
 * 类描述：
 * 作者： LiuJinrong  
 * 创建日期：2018年12月14日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class ReplyDaoImpl implements ReplyDao {

	/**
	 * 回帖显示
	 */
	@Override
	public List<ReplyVo> select(String msgid) {
		// sql 
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT r.*, u.`username`, u.`city` ");
		sql.append("FROM t_reply r LEFT JOIN t_user u ");
		sql.append("ON r.userid = u.`userid` ");
		sql.append("WHERE r.msgid = ? ");

		// 初始化实体类集合
		List<ReplyVo> replyVos = null;

		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());

		// 执行sql并返回集合
		try {
			replyVos = queryRunner.query(sql.toString(), new BeanListHandler<>(ReplyVo.class), msgid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return replyVos;
	}

	/**
	 * 回帖
	 * @throws SQLException 
	 */
	@Override
	public int addReplyContents(Reply reply) throws SQLException {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("INSERT INTO t_reply(msgid,userid,replycontents,replytime,replyip) ");
		sql.append("VALUES(?,?,?,?,?) ");
		// 初始化影响行数
		int result = 0;
		// 创建数组填充占位符
		Object[] params = {reply.getMsgid(),reply.getUserid(),reply.getReplycontents(),reply.getReplytime(),reply.getReplyip()};
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner();
		// 执行sql并返回影响行数
		result = queryRunner.update(JdbcUtils.getConnection(), sql.toString(), params);
		// 返回影响行数
		return result;
	}

	/**
	 * 回帖数量+1
	 * @throws SQLException 
	 */
	@Override
	public int addReplyCount(Reply reply) throws SQLException {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("UPDATE t_countmsg ");
		sql.append("SET replyCount = replyCount + 1 ");
		sql.append("WHERE msgid = ? ");
		// 初始化影响行数
		int result = 0;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner();
		// 执行sql并返回影响行数
		result = queryRunner.update(JdbcUtils.getConnection(), sql.toString(), reply.getMsgid());
		// 返回影响行数
		return result;
	}

}
