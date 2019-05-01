package com.zw.po;

import java.util.Date;

/**
 * 
 * 类描述：员工与部门总实体类
 * 作者： Administrator  
 * 创建日期：2018年11月26日
 * 修改人：
 * 修改日期：
 * 修改内容：
 * 版本号： 1.0.0
 */
public class EmpDept {

	// 员工ID
	private int empID;
	// 员工名称
	private String empName;
	// 部门ID
	private int deptId;
	// 员工生日
	private Date empBirthday;
	// 员工电话
	private String empphone;
	// 部门名称
	private String deptName;
	/**
	 * 
	 */
	public EmpDept() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @param empID
	 * @param empName
	 * @param deptId
	 * @param empBirthday
	 * @param empphone
	 * @param deptName
	 */
	public EmpDept(int empID, String empName, int deptId, Date empBirthday, String empphone, String deptName) {
		this.empID = empID;
		this.empName = empName;
		this.deptId = deptId;
		this.empBirthday = empBirthday;
		this.empphone = empphone;
		this.deptName = deptName;
	}

	/**
	 * @return the empID
	 */
	public int getEmpID() {
		return empID;
	}
	/**
	 * @param empID the empID to set
	 */
	public void setEmpID(int empID) {
		this.empID = empID;
	}
	/**
	 * @return the empName
	 */
	public String getEmpName() {
		return empName;
	}
	/**
	 * @param empName the empName to set
	 */
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	/**
	 * @return the deptId
	 */
	public int getDeptId() {
		return deptId;
	}
	/**
	 * @param deptId the deptId to set
	 */
	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}
	/**
	 * @return the empBirthday
	 */
	public Date getEmpBirthday() {
		return empBirthday;
	}
	/**
	 * @param empBirthday the empBirthday to set
	 */
	public void setEmpBirthday(Date empBirthday) {
		this.empBirthday = empBirthday;
	}
	/**
	 * @return the empphone
	 */
	public String getEmpphone() {
		return empphone;
	}
	/**
	 * @param empphone the empphone to set
	 */
	public void setEmpphone(String empphone) {
		this.empphone = empphone;
	}
	/**
	 * @return the deptName
	 */
	public String getDeptName() {
		return deptName;
	}
	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	
}
