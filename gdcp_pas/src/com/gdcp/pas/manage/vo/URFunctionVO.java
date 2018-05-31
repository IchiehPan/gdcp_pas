package com.gdcp.pas.manage.vo;

/**
 * @author 黄岸鹏 2015-03-23
 */
public class URFunctionVO {
	private String id;
	private String objectId;
	private int objectType; // 0表示用户，1表示角色
	private String functionId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public int getObjectType() {
		return objectType;
	}

	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

}
