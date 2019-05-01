package com.zw.pojo;
/**
 * 
 * 类描述：用户实体类
 * 作者： LiuJinrong  
 * 创建日期：2018年12月11日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class User {

	// 用户id
	private int userid;

	// 用户名称
	private String username;

	// 用户密码
	private String password;

	// 用户性别
	private String sex;

	// 用户爱好
	private String hobbys;

	// 用户生日
	private String birthday;

	// 用户地址
	private String city;

	// 用户邮箱
	private String email;

	// 用户qq
	private String qq;

	// 用户创建时间
	private String createtime;

	/**
	 * @return the userid
	 */
	public int getUserid() {
		return userid;
	}

	/**
	 * @param userid the userid to set
	 */
	public void setUserid(int userid) {
		this.userid = userid;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the sex
	 */
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the hobbys
	 */
	public String getHobbys() {
		return hobbys;
	}

	/**
	 * @param hobbys the hobbys to set
	 */
	public void setHobbys(String hobbys) {
		this.hobbys = hobbys;
	}

	/**
	 * @return the birthday
	 */
	public String getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}

	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the qq
	 */
	public String getQq() {
		return qq;
	}

	/**
	 * @param qq the qq to set
	 */
	public void setQq(String qq) {
		this.qq = qq;
	}

	/**
	 * @return the createtime
	 */
	public String getCreatetime() {
		return createtime;
	}

	/**
	 * @param createtime the createtime to set
	 */
	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	/**
	 * 
	 */
	public User() {
		
	}

	/**
	 * @param username
	 * @param password
	 */
	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	/**
	 * @param username
	 * @param password
	 * @param sex
	 * @param hobbys
	 * @param birthday
	 * @param city
	 * @param email
	 * @param qq
	 * @param createtime
	 */
	public User(String username, String password, String sex, String hobbys, String birthday, String city, String email,
			String qq, String createtime) {
		this.username = username;
		this.password = password;
		this.sex = sex;
		this.hobbys = hobbys;
		this.birthday = birthday;
		this.city = city;
		this.email = email;
		this.qq = qq;
		this.createtime = createtime;
	}

}
