package com.gdcp.pas.manage.vo;

public class AddAndSubVO {
	private int id;
	private String objectId;
	private int status;
	private int score;
	private String describe;
	private String userName;
	private int oldstatus;

	public int getOldstatus() {
		return oldstatus;
	}

	public void setOldstatus(int oldstatus) {
		this.oldstatus = oldstatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDescribe() {
		return describe;
	}

	public void setDescribe(String describe) {
		this.describe = describe;
	}
}
