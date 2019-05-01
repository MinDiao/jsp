package com.zw.dao.impl;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;

import com.zw.dao.CountMsgDao;
import com.zw.pojo.CountMsg;
import com.zw.util.JdbcUtils;
/**
 * 
 * 接口描述：帖子访问点击数据访问层实现类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月13日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class CountMsgDaoImpl implements CountMsgDao {

	/**
	 * 添加统计数据
	 */
	@Override
	public int add(CountMsg countMsg) throws Exception {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append(" insert into t_countmsg(msgid,accessCount,replyCount) ");
		sql.append(" values (?,?,?) ");
		// 创建数据填充占位符
		Object[] params = {countMsg.getMsgid(),countMsg.getAccessCount(), countMsg.getReplyCount()};
		// 创建数据源
		QueryRunner qr = new QueryRunner();
		// 初始化结果
		int res = 0;
		// 执行sql并上抛异常
		res = qr.update(JdbcUtils.getConnection(), sql.toString(), params);
		return res;
	}

	/**
	 * 修改访问量
	 */
	@Override
	public int update(String msgid) {
		// sql 
		String sql = "UPDATE t_countmsg SET accessCount = accessCount + 1 WHERE msgid = ?";
		// 创建数据源
		QueryRunner qr = new QueryRunner(JdbcUtils.getDataSource());
		// 初始化执行结果
		int res = 0;
		// 执行sql并返回执行结果
		try {
			res = qr.update(sql, msgid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return res;
	}

}
