package com.tz.po;
/**
 * 
 * 类描述：商品类别实体类
 * 作者： Administrator  
 * 创建日期：2018年11月24日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class Category {

	// 商品类别序号
	private String cid;
	// 商品类别名称
	private String cname;

	public Category() {

	}
	/**
	 * @param cname
	 */
	public Category(String cname) {
		this.cname = cname;
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}
	
}
