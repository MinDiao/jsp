package com.hpe.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hpe.po.FriendLink;
import com.hpe.service.IFriendLinkService;
import com.hpe.service.impl.FriendLinkServiceImpl;
import com.hpe.util.StringUtil;

/**
 * 
 * 类描述：友情链接Controller
 * 作者： Administrator  
 * 创建日期：2018年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
@WebServlet("/friendLink")
public class FriendLinkServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	// 注入Service
	IFriendLinkService friendLinkService = new FriendLinkServiceImpl();

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		switch (action) {
			// 链接添加
			/*case "friendLinkAdd":
				add(request, response);
				break;*/
		
			// 链接维护的显示全部
			case "friendLinkShow":
				selectAll(request, response);
				break;
				
			// 链接维护根据友情链接的实体类Id显示对象
			case "friendLinkShowById":
				friendLinkShowById(request, response);
				break;
				
			// 链接维护的添加\删除
			case "friendLinkAddUpdate":
				friendLinkAddUpdate(request, response);
				break;
				
				// 链接维护的显示全部
			case "friendLinkDelete":
				friendLinkDelete(request, response);
				break;
				
			default:
				break;
		}
	}
	
	/**
	 * 
	 * 方法描述:显示全部友情链接
	 */
	private void selectAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 调用Service查询所有友情链接
		List<FriendLink> friendLinkAll = friendLinkService.selectAll();
		// 将查询的所有友情链接实体类集合填充到请求域中
		request.setAttribute("friendLinkAll", friendLinkAll);
		// 转发到linkList.jsp页面
		request.getRequestDispatcher("/background/link/linkList.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * 方法描述:链接维护的显示全部-转发至save
	 */
	private void friendLinkShowById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 从请求域中获取属性值
		String linkId = request.getParameter("linkId");
		// 判断id不为空
		if (StringUtil.isNotEmpty(linkId)) {
			// 调用Service查询所有友情链接
			FriendLink friendLinkSave = friendLinkService.selectById(Integer.parseInt(linkId));
			// 将查询的所有友情链接实体类集合填充到请求域中
			request.setAttribute("friendLinkSave", friendLinkSave);
		}
		// 转发到linkUpdate.jsp页面,修改页面
		request.getRequestDispatcher("/background/link/linkUpdate.jsp").forward(request, response);
	}
	
	/**
	 * 
	 * 方法描述:添加\修改友情链接
	 */
	private void friendLinkAddUpdate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 初始化执行结果
		int result = 0;
		// 初始化script结果
		String successAddUpdate = null;
		
		// 从请求域中判断是添加/修改(因为添加和删除的四个对象的属性都存在所以无法使用id是否存在来判断添加/修改)
		String onoff = request.getParameter("onoff");
		
		// 从请求域中获取属性值
		String linkId = request.getParameter("linkId");
		String linkName = request.getParameter("linkName");
		String linkUrl = request.getParameter("linkUrl");
		String linkEmail = request.getParameter("linkEmail");
		
		// 存在则修改
		// 实例化实体类对象
		FriendLink friendLink = new FriendLink(Integer.parseInt(linkId), linkName, linkUrl, linkEmail);
		
		// 判断从request请求域中获取的linkId是否有值:有值修改/无值添加
		if (onoff.equals("update")) {
			// 修改
			// 调用服务层的修改方法
			result = friendLinkService.update(friendLink);
		} else if(onoff.equals("add")) {
			// 添加
			// 调用服务层的修改方法
			result = friendLinkService.add(friendLink);
		} 
		
		// 最终判断数据库是否执行成功
		if (result > 0) {
			successAddUpdate = "<script>alert('成功')</script>";
		} else {
			successAddUpdate = "<script>alert('失败')</script>";
		}
		// 将script结果添加到请求域
		request.setAttribute("successAddUpdate", successAddUpdate);
		// 执行查询所有的数据绑定并转发至background/link/linkList.jsp
		request.getRequestDispatcher("/friendLink?action=friendLinkShow").forward(request, response);
	}
	
	/**
	 * 
	 * 方法描述:删除友情链接
	 */
	private void friendLinkDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 从请求域中获取属性值
		String linkId = request.getParameter("linkId");
		// 初始化script执行结果
		String successdelete = null;
		// 判断id是否为空
		if (StringUtil.isNotEmpty(linkId)) {
			// 执行Service层
			int delete = friendLinkService.delete(Integer.parseInt(linkId));
			if (delete > 0) {
				successdelete = "<script>alert('删除成功')</script>";
			} else {
				successdelete = "<script>alert('删除失败')</script>";
			}
		}
		// 将script添加到请求域中
		request.setAttribute("successdelete", successdelete);
		// 执行查询所有的数据绑定并转发至background/link/linkList.jsp
		request.getRequestDispatcher("/friendLink?action=friendLinkShow").forward(request, response);
	}

}
