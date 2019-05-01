package com.hpe.po;
/**
 * 
 * 类描述：新闻管理实体类->用于查询的功能
 * 作者： Administrator  
 * 创建日期：2018年11月22日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class NewsSearch {
	private int typeId=-1;//类型Id
	private String title;//新闻标题
	private String startDate;//开始日期
	private String endDate;//结束日期
	
	public NewsSearch() {
		
	}	
	public NewsSearch(int typeId, String title, String startDate, String endDate) {
		super();
		this.typeId = typeId;
		this.title = title;
		this.startDate = startDate;
		this.endDate = endDate;
	}

	public int getTypeId() {
		return typeId;
	}
	public void setTypeId(int typeId) {
		this.typeId = typeId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	
}
