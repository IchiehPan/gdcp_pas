package com.gdcp.pas.manage.vo;

import java.util.List;

/**
 * @author 黄岸鹏 2015-03-19
 */
/**
 * @author Administrator
 *
 */
public class UserVO {
	private int userId;
	private String teacherId;
	private String userName;
	private String password;
	private int userCharacter;
	private int roleId;
	private int deptId;
	private String deptName;
	private int prodeptId1;
	private int prodeptId2;
	private int evalPosition;
	private String sex;
	private int isProfessional;
	private String birthday;
	private String technicaltitle;
	private String job;
	private String degree;
	private String presentPosition;
	private String positionKind;
	private String userLevel;
	private String remark;
	private String roleName;// 角色名字

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	private List<String> rightList;// 用户的权限列表

	public List<String> getRightList() {
		return rightList;
	}

	public void setRightList(List<String> rightList) {
		this.rightList = rightList;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getUserCharacter() {
		return userCharacter;
	}

	public void setUserCharacter(int userCharacter) {
		this.userCharacter = userCharacter;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public int getProdeptId1() {
		return prodeptId1;
	}

	public void setProdeptId1(int prodeptId1) {
		this.prodeptId1 = prodeptId1;
	}

	public int getProdeptId2() {
		return prodeptId2;
	}

	public void setProdeptId2(int prodeptId2) {
		this.prodeptId2 = prodeptId2;
	}

	public int getEvalPosition() {
		return evalPosition;
	}

	public void setEvalPosition(int evalPosition) {
		this.evalPosition = evalPosition;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getTechnicaltitle() {
		return technicaltitle;
	}

	public void setTechnicaltitle(String technicaltitle) {
		this.technicaltitle = technicaltitle;
	}

	public String getJob() {
		return job;
	}

	public void setJob(String job) {
		this.job = job;
	}

	public String getDegree() {
		return degree;
	}

	public void setDegree(String degree) {
		this.degree = degree;
	}

	public String getPresentPosition() {
		return presentPosition;
	}

	public void setPresentPosition(String presentPosition) {
		this.presentPosition = presentPosition;
	}

	public String getPositionKind() {
		return positionKind;
	}

	public void setPositionKind(String positionKind) {
		this.positionKind = positionKind;
	}

	public String getUserLevel() {
		return userLevel;
	}

	public void setUserLevel(String userLevel) {
		this.userLevel = userLevel;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getIsProfessional() {
		return isProfessional;
	}

	public void setIsProfessional(int isProfessional) {
		this.isProfessional = isProfessional;
	}

}
