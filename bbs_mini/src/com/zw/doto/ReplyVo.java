package com.zw.doto;

import com.zw.pojo.Reply;

/**
 * 
 * 类描述：回帖列表视图实体类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月14日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class ReplyVo extends Reply {

	// 用户姓名
	private String username;
	
	// 用户地址
	private String city;
	
	// 创建时间
	private String createtime;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

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
}
