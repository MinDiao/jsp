package com.zw.pojo;

/**
 * 
 * 类描述：首页内容栏实体类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月12日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class Over_Index {
	
	// 主题id
	private int msgid;
	
	// 主题
	private String msgtopic;
	
	// 作者
	private String username;
	
	// 访问量
	private int accessCount;
	
	// 回复量
	private int replyCount;
	
	// 发表日期
	private String msgtime;

	

	public int getMsgid() {
		return msgid;
	}

	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}

	public String getMsgtopic() {
		return msgtopic;
	}

	public void setMsgtopic(String msgtopic) {
		this.msgtopic = msgtopic;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getAccessCount() {
		return accessCount;
	}

	public void setAccessCount(int accessCount) {
		this.accessCount = accessCount;
	}

	public int getReplyCount() {
		return replyCount;
	}

	public void setReplyCount(int replyCount) {
		this.replyCount = replyCount;
	}

	public String getMsgtime() {
		return msgtime;
	}

	public void setMsgtime(String msgtime) {
		this.msgtime = msgtime;
	}

	public Over_Index() {
		
	}

	public Over_Index(String msgtopic, String username, int accessCount, int replyCount, String msgtime) {
		this.msgtopic = msgtopic;
		this.username = username;
		this.accessCount = accessCount;
		this.replyCount = replyCount;
		this.msgtime = msgtime;
	}
}
