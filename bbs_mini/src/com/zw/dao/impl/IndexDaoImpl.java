package com.zw.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import com.zw.dao.IndexDao;
import com.zw.doto.MessageDto;
import com.zw.doto.MessageVo;
import com.zw.pojo.Message;
import com.zw.util.JdbcUtils;
import com.zw.util.Page;
import com.zw.util.StringUtil;
/**
 * 
 * 类描述：首页业务逻辑层实现类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月12日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class IndexDaoImpl implements IndexDao {

	/**
	 * 首页内容列表
	 */
	@Override
	public List<MessageVo> indexList(MessageDto messageDto, Page page) {
		// sql
		StringBuffer sql = new StringBuffer();
		/*sql.append("SELECT m.msgid, m.msgtopic, m.msgtime, c.accessCount, c.replyCount, u.username ");
		sql.append("FROM t_message m, t_countmsg c, t_user u ");
		sql.append("WHERE m.msgid = c.msgid ");
		sql.append("AND m.userid = u.userid ");
		sql.append("AND 1 = 1 ");*/

		// 老师升级版：使用左连接查询
		sql.append("SELECT m.msgid, m.msgtopic, u.username, c.accessCount, c.replyCount, m.msgtime ");
		sql.append("FROM t_message m LEFT JOIN t_user u ON m.userid = u.userid ");
		sql.append("LEFT JOIN t_countmsg c ON m.msgid = c.msgid ");
		sql.append("WHERE 1 = 1 ");

		// 创建查询条件List集合
		List<Object> paramList = new ArrayList<>();

		// 如果执行模糊查询
		if (StringUtil.isNotEmpty(messageDto.getMsgtopic())) {
			// 插入模糊查询sql语句
			sql.append("AND m.msgtopic LIKE ? ");
			// 创建模糊查询条件
			paramList.add("%"+messageDto.getMsgtopic()+"%");
		}

		// 分页
		if (page.getPageNum() != 0) {
			// 分页的sql
			sql.append("ORDER BY m.msgid DESC limit ?,? ");

			// 设置起始页(用到小算法)
			int start = (page.getPageNum() - 1) * page.getPageSize();
			paramList.add(start);

			// 设置每页显示的记录数
			paramList.add(page.getPageSize());
		}

		// 将查询条件List集合转换为数组
		Object[] params = paramList.toArray();

		// 初始化填充首页内容对象的List集合
		List<MessageVo> list = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行sql并返回填充首页内容对象的List集合
		try {
			list = queryRunner.query(sql.toString(), new BeanListHandler<>(MessageVo.class), params);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 分页
	 */
	@Override
	public long selectCount(MessageDto messageDto) {
		// sql
		StringBuffer sql = new StringBuffer();
		/*sql.append("SELECT COUNT(1) ");
		sql.append("FROM t_message m, t_countmsg c, t_user u ");
		sql.append("WHERE m.msgid = c.msgid ");
		sql.append("AND m.userid = u.userid ");
		sql.append("AND 1 = 1 ");*/

		// 老师升级版：使用左连接查询
		sql.append("SELECT COUNT(1) ");
		sql.append("FROM t_message m LEFT JOIN t_user u ON m.userid = u.userid ");
		sql.append("LEFT JOIN t_countmsg c ON m.msgid = c.msgid ");
		sql.append("WHERE 1 = 1 ");

		// 创建查询条件List集合
		List<Object> paramList = new ArrayList<>();

		// 如果执行模糊查询
		if (StringUtil.isNotEmpty(messageDto.getMsgtopic())) {
			// 插入模糊查询sql语句
			sql.append("AND m.msgtopic LIKE ? ");
			// 创建模糊查询条件
			paramList.add("%"+messageDto.getMsgtopic()+"%");
		}

		// 将查询条件List集合转换为数组
		Object[] params = paramList.toArray();

		// 初始化页数
		Long rows = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		try {
			// 执行sql并返回
			rows = queryRunner.query(sql.toString(), new ScalarHandler<Long>(), params);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rows;
	}

	/**
	 * 新增帖子并返回id
	 */
	@Override
	public int add(Message msg) {

		// sql 
		StringBuffer sql = new StringBuffer();
		sql.append("insert into t_message(userid,msgtopic,msgcontents,msgtime,msgip) ");
		sql.append("values(?,?,?,?,?)");
		// 创建结果新增帖子id
		int msgid = 0;
		// 创建数组填充占位符
		Object[] params = {msg.getUserid(),msg.getMsgtopic(),msg.getMsgcontents(),
				msg.getMsgtime(),msg.getMsgip()
		};
		// 创建数据源
		QueryRunner queryRunner = new QueryRunner();
		// 执行结果并返回新增帖子id
		try {
			msgid = queryRunner.insert(JdbcUtils.getConnection(), sql.toString(), new ScalarHandler<Long>(), params).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msgid;
	}

	/**
	 * 查询帖子详情
	 */
	@Override
	public MessageVo selectInfo(String msgid) {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT m.*, u.`username`, u.`city`, u.`createtime` ");
		sql.append("FROM t_message m LEFT JOIN t_user u ");
		sql.append("ON m.`userid` = u.`userid` ");
		sql.append("WHERE m.`msgid` = ? ");
		// 初始化提诶子实体类
		MessageVo msgVo = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行SQL语句并返回帖子详情实体类
		try {
			msgVo = queryRunner.query(sql.toString(), new BeanHandler<>(MessageVo.class), msgid);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return msgVo;
	}

	/**
	 * 
	 * 方法描述:按访问量排序
	 * @return 填充首页列表实体类的List集合
	 */
	@Override
	public List<MessageVo> visitOrder() {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT m.msgid, m.msgtopic, u.username, c.accessCount, c.replyCount, m.msgtime ");
		sql.append("FROM t_message m LEFT JOIN t_user u ON m.userid = u.userid ");
		sql.append("LEFT JOIN t_countmsg c ON m.msgid = c.msgid ");
		sql.append("WHERE 1 = 1 ");
		sql.append("ORDER BY c.accessCount DESC ");
		sql.append("LIMIT 0, 5");
		// 初始化提诶子实体类
		List<MessageVo> list = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行SQL语句并返回帖子详情实体类
		try {
			list = queryRunner.query(sql.toString(), new BeanListHandler<>(MessageVo.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * 方法描述:按回复量排序
	 * @return 填充首页列表实体类的List集合
	 */
	@Override
	public List<MessageVo> replyOrder() {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT m.msgid, m.msgtopic, u.username, c.accessCount, c.replyCount, m.msgtime ");
		sql.append("FROM t_message m LEFT JOIN t_user u ON m.userid = u.userid ");
		sql.append("LEFT JOIN t_countmsg c ON m.msgid = c.msgid ");
		sql.append("WHERE 1 = 1 ");
		sql.append("ORDER BY c.replyCount DESC ");
		sql.append("LIMIT 0, 5");
		// 初始化提诶子实体类
		List<MessageVo> list = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行SQL语句并返回帖子详情实体类
		try {
			list = queryRunner.query(sql.toString(), new BeanListHandler<>(MessageVo.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	/**
	 * 
	 * 方法描述:按帖子id排序
	 * @return 填充首页列表实体类的List集合
	 */
	@Override
	public List<MessageVo> idOrder() {
		// sql
		StringBuffer sql = new StringBuffer();
		sql.append("SELECT m.msgid, m.msgtopic, u.username, c.accessCount, c.replyCount, m.msgtime ");
		sql.append("FROM t_message m LEFT JOIN t_user u ON m.userid = u.userid ");
		sql.append("LEFT JOIN t_countmsg c ON m.msgid = c.msgid ");
		sql.append("WHERE 1 = 1 ");
		sql.append("ORDER BY m.msgid DESC ");
		sql.append("LIMIT 0, 5");
		// 初始化提诶子实体类
		List<MessageVo> list = null;
		// 创建数据源对象
		QueryRunner queryRunner = new QueryRunner(JdbcUtils.getDataSource());
		// 执行SQL语句并返回帖子详情实体类
		try {
			list = queryRunner.query(sql.toString(), new BeanListHandler<>(MessageVo.class));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
