package com.gdcp.pas.score.vo;

public class AssessQueryVO {
	private String teacherID; // 教职工ID
	private String userName; // 用户名
	private double score; // 得分
	private String objectType; // 考核对象类型
	private String scoreType; // 评价主体类型
	private int scoreRuleId; // 评分规则ID
	private String ruleName; // 评分规则名称
	private int ruleYear; // 评分规则年度
	private String deptName; // 部门名
	private String deptId; // 部门ID
	private String objectTypeId; // 对象类型ID

	private int submittdNum; // 未提交人数
	private int allResultNum; // 总应该提交数

	public String getObjectTypeId() {
		return objectTypeId;
	}

	public void setObjectTypeId(String objectTypeId) {
		this.objectTypeId = objectTypeId;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getScoreRuleId() {
		return scoreRuleId;
	}

	public void setScoreRuleId(int scoreRuleId) {
		this.scoreRuleId = scoreRuleId;
	}

	public String getScoreType() {
		return scoreType;
	}

	public void setScoreType(String scoreType) {
		this.scoreType = scoreType;
	}

	public String getObjectType() {
		return objectType;
	}

	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	public String getTeacherID() {
		return teacherID;
	}

	public void setTeacherID(String teacherID) {
		this.teacherID = teacherID;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public int getRuleYear() {
		return ruleYear;
	}

	public void setRuleYear(int ruleYear) {
		this.ruleYear = ruleYear;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public int getSubmittdNum() {
		return submittdNum;
	}

	public void setSubmittdNum(int submittdNum) {
		this.submittdNum = submittdNum;
	}

	public int getAllResultNum() {
		return allResultNum;
	}

	public void setAllResultNum(int allResultNum) {
		this.allResultNum = allResultNum;
	}

}
