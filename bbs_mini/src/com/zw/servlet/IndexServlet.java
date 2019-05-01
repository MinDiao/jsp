package com.zw.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;
import com.zw.base.BaseServlet;
import com.zw.doto.MessageDto;
import com.zw.doto.MessageVo;
import com.zw.pojo.Message;
import com.zw.service.IndexService;
import com.zw.service.impl.IndexServiceImpl;
import com.zw.util.Page;
import com.zw.util.ToolUtil;

/**
 * 
 * 类描述：index首页Servlet
 * 作者： LiuJinrong  
 * 创建日期：2018年12月12日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
@WebServlet("/index")
public class IndexServlet extends BaseServlet {

	private static final long serialVersionUID = 1L;

	// 注入首页Service层
	IndexService indexService = new IndexServiceImpl();

	/**
	 * 首页列表
	 */
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 创建Json对象
		JSONObject json = new JSONObject();

		// 实例化分页对象,分页信息
		Page page = new Page();

		try {
			// 获取模糊查询的主题输入框内容
			String msgtopic = request.getParameter("msgtopic");

			// 页码
			String pageNum = request.getParameter("pageNum");
			// 页容量
			String pageSize = request.getParameter("pageSize");

			// 封装到messageDto对象

			MessageDto messageDto = new MessageDto();
			messageDto.setMsgtopic(msgtopic);

			// 初始化填充首页内容栏对象的List集合
			//List<Index> list = null;

			page.setPageNum(Integer.parseInt(pageNum));
			page.setPageSize(Integer.parseInt(pageSize));

			// 执行模糊查询方法并返回填充首页内容栏对象的List集合
			page = indexService.indexList(messageDto, page);

		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将填充首页内容栏对象的List集合的结果填充至json对象中
		json.put("page", page);

		// 将json响应至ajax
		response.getWriter().write(json.toString());

	}

	/**
	 * 
	 * 方法描述:发表帖子
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void add(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建json对象
		JSONObject json = new JSONObject();
		// 初始化结果参数
		int res = 0;

		try {
			// 1.获取参数
			String msgtopic = request.getParameter("msgtopic");
			String msgcontent = request.getParameter("msgcontent");
			String userid = request.getParameter("userid");

			// 2.封装对象
			Message msg = new Message();
			msg.setMsgtopic(msgtopic);
			msg.setMsgcontents(msgcontent);
			msg.setUserid(Integer.valueOf(userid));
			// 当前系统时间工具类
			msg.setMsgtime(ToolUtil.getCurrentTime());
			// 用户ip
			msg.setMsgip(request.getRemoteAddr());

			// 执行保存
			res = indexService.add(msg);

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 判断
		if (res > 0) {
			json.put("code", 1);
		} else {
			json.put("code", 0);
		}

		// 响应
		response.getWriter().write(json.toString());
	}

	/**
	 * 
	 * 方法描述:按访问量排序
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void visitOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建Json对象
		JSONObject json = new JSONObject();
		// 初始化填充首页列表实体类的List集合
		List<MessageVo> list = null;
		try {
			// 执行按访问量排序查询方法
			list = indexService.visitOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将结果填充至json对象中
		json.put("code", list);
		// 响应到ajax
		response.getWriter().write(json.toString());
	}

	/**
	 * 
	 * 方法描述:按访问量排序
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void replyOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建Json对象
		JSONObject json = new JSONObject();
		// 初始化填充首页列表实体类的List集合
		List<MessageVo> list = null;
		try {
			// 执行按访问量排序查询方法
			list = indexService.replyOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将结果填充至json对象中
		json.put("code", list);
		// 响应到ajax
		response.getWriter().write(json.toString());
	}

	/**
	 * 
	 * 方法描述:按访问量排序
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	public void idOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 创建Json对象
		JSONObject json = new JSONObject();
		// 初始化填充首页列表实体类的List集合
		List<MessageVo> list = null;
		try {
			// 执行按访问量排序查询方法
			list = indexService.idOrder();
		} catch (Exception e) {
			e.printStackTrace();
		}
		// 将结果填充至json对象中
		json.put("code", list);
		// 响应到ajax
		response.getWriter().write(json.toString());
	}

}
