package com.zw.pojo;
/**
 * 
 * 类描述：回帖数据表实体类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月14日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class Reply {
	
	// 回帖id
	private int replyid;
	
	// 帖子id
	private int msgid;
	
	// 用户id
	private int userid;
	
	// 回帖内容
	private String replycontents;
	
	// 回帖时间
	private String replytime;
	
	// 帖子ip
	private String replyip;

	public Reply() {
		
	}

	public Reply(int msgid, int userid, String replycontents, String replytime, String replyip) {
		this.msgid = msgid;
		this.userid = userid;
		this.replycontents = replycontents;
		this.replytime = replytime;
		this.replyip = replyip;
	}

	public int getReplyid() {
		return replyid;
	}

	public void setReplyid(int replyid) {
		this.replyid = replyid;
	}

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

	public String getReplycontents() {
		return replycontents;
	}

	public void setReplycontents(String replycontents) {
		this.replycontents = replycontents;
	}

	public String getReplytime() {
		return replytime;
	}

	public void setReplytime(String replytime) {
		this.replytime = replytime;
	}

	public String getReplyip() {
		return replyip;
	}

	public void setReplyip(String replyip) {
		this.replyip = replyip;
	}

}
