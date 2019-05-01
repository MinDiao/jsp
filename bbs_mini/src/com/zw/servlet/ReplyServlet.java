package com.zw.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.zw.base.BaseServlet;
import com.zw.doto.MessageVo;
import com.zw.doto.ReplyVo;
import com.zw.pojo.Reply;
import com.zw.service.IndexService;
import com.zw.service.ReplyService;
import com.zw.service.impl.IndexServiceImpl;
import com.zw.service.impl.ReplyServiceImpl;
import com.zw.util.ToolUtil;

/**
 * 
 * 类描述：回帖的Servlet
 * 作者： LiuJinrong  
 * 创建日期：2018年12月14日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
@WebServlet("/reply")
public class ReplyServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;
	
	// 注入index的Service层
	private IndexService indexService = new IndexServiceImpl();
	
	// 注入回帖Service层
	private ReplyService replyService = new ReplyServiceImpl();
	

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 创建json对象
		JSONObject json = new JSONObject();
		
		// 初始化封装对象
		MessageVo msgVo = null;
		
		// 初始化回帖显示list
		List<ReplyVo> replyVos = null;
		
		try {
			// 获取帖子id
			String msgid = request.getParameter("msgid");
			
			// 调用Service
			// 1.根据msgid查询帖子详情,并且包括作者信息
			msgVo = indexService.selectInfo(msgid);
			
			// 2.根据msgid查询回帖列表
			replyVos = replyService.select(msgid);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		// 将结果填充至json对象中
		json.put("msgVo", msgVo);
		json.put("replyVos", replyVos);
		
		// 响应
		response.getWriter().write(json.toString());
		
	}
	
	public void addReply(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建json对象
		JSONObject json = new JSONObject();
		// 初始化执行结果
		int result = 0;
		try {
			// 获取用户id
			String userid = request.getParameter("userid");
			// 获取帖子id
			String msgid = request.getParameter("msgid");
			// 获取回复内容
			String replycontents = request.getParameter("replycontents");
			// 封装对象
			Reply reply = new Reply(Integer.parseInt(msgid), Integer.parseInt(userid), replycontents, ToolUtil.getCurrentTime(), request.getRemoteAddr());
			// 调用回帖方法并返回执行结果
			result = replyService.addReply(reply);
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 判断后台返回影响行数
		if (result == 2) {
			// 将结果填充至json对象中
			json.put("result", 1);
		} else {
			// 将结果填充至json对象中
			json.put("result", 0);
		}
		// 响应到ajax
		response.getWriter().write(json.toString());
	}
	

}
