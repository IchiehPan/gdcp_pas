package com.gdcp.pas.manage.vo;

/**
 * @author �ſ��� 2015-03-19
 * @see �ṩ�Խ�ɫ�û������ɾ�Ĳ�Ȳ���
 */
public class UserRoleVO {
	private String userName; // �û�����
	private String roleName; // ��ɫ��ɫ
	private String userId; // �û�ID
	private String roleId; // ��ɫID
	private String oldRoleName; // ԭ��ɫ����
	private String oldUserName; // ԭ�û�����

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
