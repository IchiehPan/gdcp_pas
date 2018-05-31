package com.gdcp.pas.score.vo;

public class ScoreResultVO {

	// 数据库字段：
	private int id;
	private String objectId;
	private int objectType;
	private String scorerId;
	private int scorerType;
	private int scoreRuleId;
	private int status;
	private double scoreResult;
	private String commitDate;
	private String remark;

	// 列表需要
	private String scorerName;
	private String scorerDeptName;
	private String objectName;
	private String objectDeptName;
	private String scoreRuleTable;

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

	public int getObjectType() {
		return objectType;
	}

	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}

	public String getScorerId() {
		return scorerId;
	}

	public void setScorerId(String scorerId) {
		this.scorerId = scorerId;
	}

	public int getScorerType() {
		return scorerType;
	}

	public void setScorerType(int scorerType) {
		this.scorerType = scorerType;
	}

	public int getScoreRuleId() {
		return scoreRuleId;
	}

	public void setScoreRuleId(int scoreRuleId) {
		this.scoreRuleId = scoreRuleId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public double getScoreResult() {
		return scoreResult;
	}

	public void setScoreResult(double scoreResult) {
		this.scoreResult = scoreResult;
	}

	public String getCommitDate() {
		return commitDate;
	}

	public void setCommitDate(String commitDate) {
		this.commitDate = commitDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectDeptName() {
		return objectDeptName;
	}

	public void setObjectDeptName(String objectDeptName) {
		this.objectDeptName = objectDeptName;
	}

	public String getScoreRuleTable() {
		return scoreRuleTable;
	}

	public void setScoreRuleTable(String scoreRuleTable) {
		this.scoreRuleTable = scoreRuleTable;
	}

	public String getScorerName() {
		return scorerName;
	}

	public void setScorerName(String scorerName) {
		this.scorerName = scorerName;
	}

	public String getScorerDeptName() {
		return scorerDeptName;
	}

	public void setScorerDeptName(String scorerDeptName) {
		this.scorerDeptName = scorerDeptName;
	}

}
