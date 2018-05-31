package com.gdcp.pas.score.vo;

/**
 * @author 陈伟镇
 *
 */
public class AverageScoreVO {

	// 数据库相关
	private int averageId;
	private String objectId;
	private int objectType;
	private int socrerType;
	private int scoreRuleId;
	private double averageScore;
	private int actualCommit;
	private int shouldCommit;
	private String remark;

	// 导出相关：
	private String objectDeptName;
	private String objectName;

	public int getAverageId() {
		return averageId;
	}

	public void setAverageId(int averageId) {
		this.averageId = averageId;
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

	public int getSocrerType() {
		return socrerType;
	}

	public void setSocrerType(int socrerType) {
		this.socrerType = socrerType;
	}

	public int getScoreRuleId() {
		return scoreRuleId;
	}

	public void setScoreRuleId(int scoreRuleId) {
		this.scoreRuleId = scoreRuleId;
	}

	public double getAverageScore() {
		return averageScore;
	}

	public void setAverageScore(double averageScore) {
		this.averageScore = averageScore;
	}

	public int getActualCommit() {
		return actualCommit;
	}

	public void setActualCommit(int actualCommit) {
		this.actualCommit = actualCommit;
	}

	public int getShouldCommit() {
		return shouldCommit;
	}

	public void setShouldCommit(int shouldCommit) {
		this.shouldCommit = shouldCommit;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getObjectDeptName() {
		return objectDeptName;
	}

	public void setObjectDeptName(String objectDeptName) {
		this.objectDeptName = objectDeptName;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

}
