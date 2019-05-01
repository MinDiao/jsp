package com.zw.util;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * 类描述：分页对象
 * 作者： LiuJinrong  
 * 创建日期：2018年12月10日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class Page {

	// 页码
	private int pageNum;
	
	// 页容量
	private int pageSize;
	
	// 一共多少条记录
	private long rows;
	
	// 一共多少页
	private long totalPage;
	
	// 查询出来的数据
	private List data = new ArrayList<>();

	/**
	 * @return the pageNum
	 */
	public int getPageNum() {
		return pageNum;
	}

	/**
	 * @param pageNum the pageNum to set
	 */
	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	/**
	 * @return the pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * @param pageSize the pageSize to set
	 */
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	/**
	 * @return the rows
	 */
	public long getRows() {
		return rows;
	}

	/**
	 * @param rows the rows to set
	 */
	public void setRows(long rows) {
		this.rows = rows;
	}

	/**
	 * @return the totalPage
	 */
	public long getTotalPage() {
		return totalPage;
	}

	/**
	 * @param totalPage the totalPage to set
	 */
	public void setTotalPage(long totalPage) {
		this.totalPage = totalPage;
	}

	/**
	 * @return the data
	 */
	public List getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(List data) {
		this.data = data;
	}
}
