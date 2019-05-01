package com.hpe.po;
/**
 * 
 * 类描述：新闻类别实体类
 * 作者： Administrator  
 * 创建日期：2018年11月21日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class NewsType {
	
	// 新闻类别ID 因为通过的是反射机制,所以需要属性名与字段名相同
	private int newsTypeId;
	// 新闻类别名称
	private String typeName;
	/**
	 * 
	 */
	public NewsType() {
		
	}
	/**
	 * @param typeName
	 */
	public NewsType(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * @return the newsTypeId
	 */
	public int getNewsTypeId() {
		return newsTypeId;
	}
	/**
	 * @param newsTypeId the newsTypeId to set
	 */
	public void setNewsTypeId(int newsTypeId) {
		this.newsTypeId = newsTypeId;
	}
	/**
	 * @return the typeName
	 */
	public String getTypeName() {
		return typeName;
	}
	/**
	 * @param typeName the typeName to set
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "NewsType [newsTypeId=" + newsTypeId + ", typeName=" + typeName + "]";
	}

}
