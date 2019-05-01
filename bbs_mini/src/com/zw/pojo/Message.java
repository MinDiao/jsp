package com.zw.pojo;
/**
 * 
 * 类描述：帖子列表实体类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月13日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class Message {
	
	// 主题id
	private int msgid;
	
	// 用户id
	private int userid;
	
	// 主题
	private String msgtopic;
	
	// 帖子内容
	private String msgcontents;
	
	// 发表日期
	private String msgtime;
	
	// 发帖ip
	private String msgip;

	public int getMsgid() {
		return msgid;
	}

	public void setMsgid(int msgid) {
		this.msgid = msgid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getMsgtopic() {
		return msgtopic;
	}

	public void setMsgtopic(String msgtopic) {
		this.msgtopic = msgtopic;
	}

	public String getMsgcontents() {
		return msgcontents;
	}

	public void setMsgcontents(String msgcontents) {
		this.msgcontents = msgcontents;
	}

	public String getMsgtime() {
		return msgtime;
	}

	public void setMsgtime(String msgtime) {
		this.msgtime = msgtime;
	}

	public String getMsgip() {
		return msgip;
	}

	public void setMsgip(String msgip) {
		this.msgip = msgip;
	}

	public Message() {
		
	}
}
