package com.zw.service.impl;

import java.sql.SQLException;
import java.util.List;

import com.zw.dao.IndexDao;
import com.zw.dao.impl.IndexDaoImpl;
import com.zw.doto.MessageDto;
import com.zw.doto.MessageVo;
import com.zw.pojo.CountMsg;
import com.zw.pojo.Message;
import com.zw.service.CountMsgService;
import com.zw.service.IndexService;
import com.zw.util.JdbcUtils;
import com.zw.util.Page;

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
public class IndexServiceImpl implements IndexService {

	/**
	 * 规范:本Service只能调用本Dao层的内容
	 */
	// 注入首页数据访问层
	IndexDao indexDao = new IndexDaoImpl();
	
	// 注入统计服务
	CountMsgService cmService = new CountMsgServiceImpl();

	/**
	 * 首页内容列表
	 */
	@Override
	public Page indexList(MessageDto messageDto, Page page) throws RuntimeException {

		List<MessageVo> indexList = indexDao.indexList(messageDto, page);

		// 查询一共多少页
		// 一共多少条记录
		long rows = indexDao.selectCount(messageDto);

		// 多少页的计算
		long totalPage = rows % page.getPageSize()==0?rows/page.getPageSize():rows/page.getPageSize()+1;

		// 返回数据的Page封装
		page.setData(indexList);
		page.setRows(rows);
		page.setTotalPage(totalPage);
		
		return page;
	}

	/**
	 * 发表帖子,返回影响行数
	 */
	@Override
	public int add(Message msg) throws RuntimeException {
		
		int res = 0;
		
		try {
			// 开启事务
			JdbcUtils.beginTransaction();
			
			// 1.调用indexDao 新增一条记录,并获取新增记录的msgid
			int msgid = indexDao.add(msg);
			
			// 3.新增统计表的记录
			CountMsg countMsg = new CountMsg();
			countMsg.setMsgid(msgid);
			countMsg.setAccessCount(0);
			countMsg.setReplyCount(0);
			res = cmService.add(countMsg);
			
			// 提交事务
			JdbcUtils.commitTransaction();
		} catch (Exception e) {
			e.printStackTrace();
			// 回滚事务
			try {
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}
		
		return res;
	}

	/**
	 * 查询帖子详情
	 */
	@Override
	public MessageVo selectInfo(String msgid) throws RuntimeException {
		
		// 1.查询帖子详情
		MessageVo msgVo = indexDao.selectInfo(msgid);
		
		// 2.修改访问量
		cmService.update(msgid);
		
		
		return msgVo;
	}

	/**
	 * 
	 * 方法描述:按访问量排序
	 * @return 填充首页列表实体类的List集合
	 */
	@Override
	public List<MessageVo> visitOrder() {
		
		return indexDao.visitOrder();
	}

	/**
	 * 
	 * 方法描述:按回复量排序
	 * @return 填充首页列表实体类的List集合
	 */
	@Override
	public List<MessageVo> replyOrder() {
		
		return indexDao.replyOrder();
	}

	/**
	 * 
	 * 方法描述:按帖子id排序
	 * @return
	 */
	@Override
	public List<MessageVo> idOrder() {
		
		return indexDao.idOrder();
	}

}
