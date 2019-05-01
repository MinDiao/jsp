package com.zw.doto;

import com.zw.pojo.Message;

/**
 * 帖子列表实体类继承类
 * 类描述：
 * 作者： LiuJinrong  
 * 创建日期：2018年12月13日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class MessageVo extends Message {
	
	// 访问量
	private int accessCount;

	// 回复量
	private int replyCount;
	
	// 用户名
	private String username;
	
	// 帖子用户地址
	private String city;
	
	// 帖子用户注册时间:吧龄
	private String createtime;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
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

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public MessageVo() {
		
	}
}
