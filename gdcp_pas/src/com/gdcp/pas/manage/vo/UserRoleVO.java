package com.gdcp.pas.manage.vo;

/**
 * @author 张俊杰 2015-03-19
 * @see 提供对角色用户表的增删改查等操作
 */
public class UserRoleVO {
	private String userName; // 用户名字
	private String roleName; // 角色角色
	private String userId; // 用户ID
	private String roleId; // 角色ID
	private String oldRoleName; // 原角色名字
	private String oldUserName; // 原用户名字

	public String getOldUserName() {
		return oldUserName;
	}

	public void setOldUserName(String oldUserName) {
		this.oldUserName = oldUserName;
	}

	public String getOldRoleName() {
		return oldRoleName;
	}

	public void setOldRoleName(String oldRoleName) {
		this.oldRoleName = oldRoleName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
