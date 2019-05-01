package com.hpe.util;

public class PageUtil {

	/**
	 * 上一页和下一页的分页方法
	 * @param totalNum 
	 * @param currentPage 
	 * @param pageSize 
	 * @param typeId 
	 * @return
	 */
	public static String getUpAndDownPagation(int totalNum,int currentPage,int pageSize,String typeId){
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
	
		StringBuffer pageCode=new StringBuffer();
		pageCode.append("<ul class='pager'>");
		if(currentPage==1){
			pageCode.append("<li class='disabled'><a>上一页</a></li>");
		}else{
			pageCode.append("<li><a href='news?action=list&typeId="+typeId+"&page="+(currentPage-1)+"'>上一页</a></li>");			
		}
		pageCode.append("&nbsp;&nbsp;");
		if(currentPage==totalPage){
			pageCode.append("<li class='disabled'><a>下一页</a></li>");
		}else{
			pageCode.append("<li><a href='news?action=list&typeId="+typeId+"&page="+(currentPage+1)+"'>下一页</a></li>");			
		}
		pageCode.append("</ul>");
		return pageCode.toString();
	}
	
	/**
	 * 基本分页方法
	 * @param targetUrl
	 * @param totalNum 一共有几条记录
	 * @param currentPage 当前页
	 * @param pageSize 每页显示几条数据
	 * @return
	 */
	public static String getPagation(String targetUrl,int totalNum,int currentPage,int pageSize){
		// 计算总页数
		int totalPage=totalNum%pageSize==0?totalNum/pageSize:totalNum/pageSize+1;
		StringBuffer pageCode=new StringBuffer();
		pageCode.append("<ul>");
		pageCode.append("<li><a href='"+targetUrl+"&page=1'>首页</a></li>");
		if(currentPage==1){
			pageCode.append("<li class='disabled'><a>上一页</a></li>");
		}else{
			// 调用servlet拼接html
			pageCode.append("<li><a href='"+targetUrl+"&page="+(currentPage-1)+"'>上一页</a></li>");
		}
		
		/*仅仅显示的是5个分页按钮的数量,意义并不是很大*/
		for(int i=currentPage-2;i<=currentPage+2;i++){
			if(i<1 || i>totalPage){
				continue;
			}
			if(i==currentPage){
				pageCode.append("<li class='active'><a>"+i+"</a></li>");
			}else{
				pageCode.append("<li><a href='"+targetUrl+"&page="+i+"'>"+i+"</a></li>");
			}
			
		}
		
		if(currentPage==totalPage){
			pageCode.append("<li class='disabled'><a>下一页</a></li>");
		}else{
			pageCode.append("<li><a href='"+targetUrl+"&page="+(currentPage+1)+"'>下一页</a></li>");
		}
		pageCode.append("<li><a href='"+targetUrl+"&page="+totalPage+"'>尾页</a></li>");
		pageCode.append("</ul>");
		return pageCode.toString();
	}
}
