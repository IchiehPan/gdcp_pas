package com.gdcp.pas.manage.vo;

/**
 * @author 陈永鑫 2015-03-13
 * @see
 */
public class UserLevelVO {
	private int userLevelId;// 用户身份ID
	private String userLevelName;// 用户身份名
	private String remark;// 备注

	public int getUserLevelId() {
		return userLevelId;
	}

	public void setUserLevelId(int userLevelId) {
		this.userLevelId = userLevelId;
	}

	public String getUserLevelName() {
		return userLevelName;
	}

	public void setUserLevelName(String userLevelName) {
		this.userLevelName = userLevelName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
