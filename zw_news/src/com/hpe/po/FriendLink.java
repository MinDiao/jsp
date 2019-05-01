package com.hpe.po;
/**
 * 
 * 类描述：友情链接实体类
 * 作者： Administrator  
 * 创建日期：2018年11月21日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class FriendLink {
	
	// 链接编号
	private int linkId;
	// 链接名称
	private String linkName;
	// 链接地址
	private String linkUrl;
	// 联系人邮箱
	private String linkEmail;
	// 热度排名
	private int orderNum;
	/**
	 * 
	 */
	public FriendLink() {
		
	}
	/**
	 * @param linkId
	 * @param linkName
	 * @param linkUrl
	 * @param linkEmail
	 */
	public FriendLink(int linkId, String linkName, String linkUrl, String linkEmail) {
		this.linkId = linkId;
		this.linkName = linkName;
		this.linkUrl = linkUrl;
		this.linkEmail = linkEmail;
	}
	/**
	 * @return the linkId
	 */
	public int getLinkId() {
		return linkId;
	}
	/**
	 * @param linkId the linkId to set
	 */
	public void setLinkId(int linkId) {
		this.linkId = linkId;
	}
	/**
	 * @return the linkName
	 */
	public String getLinkName() {
		return linkName;
	}
	/**
	 * @param linkName the linkName to set
	 */
	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}
	/**
	 * @return the linkUrl
	 */
	public String getLinkUrl() {
		return linkUrl;
	}
	/**
	 * @param linkUrl the linkUrl to set
	 */
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	/**
	 * @return the linkEmail
	 */
	public String getLinkEmail() {
		return linkEmail;
	}
	/**
	 * @param linkEmail the linkEmail to set
	 */
	public void setLinkEmail(String linkEmail) {
		this.linkEmail = linkEmail;
	}
	/**
	 * @return the orderNum
	 */
	public int getOrderNum() {
		return orderNum;
	}
	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(int orderNum) {
		this.orderNum = orderNum;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "FriendLink [linkId=" + linkId + ", linkName=" + linkName + ", linkUrl=" + linkUrl + ", linkEmail="
				+ linkEmail + ", orderNum=" + orderNum + "]";
	}
	
}
