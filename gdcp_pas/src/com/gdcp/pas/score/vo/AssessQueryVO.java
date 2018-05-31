package com.gdcp.pas.score.vo;

public class AssessQueryVO {
	private String teacherID; // ��ְ��ID
	private String userName; // �û���
	private double score; // �÷�
	private String objectType; // ���˶�������
	private String scoreType; // ������������
	private int scoreRuleId; // ���ֹ���ID
	private String ruleName; // ���ֹ�������
	private int ruleYear; // ���ֹ������
	private String deptName; // ������
	private String deptId; // ����ID
	private String objectTypeId; // ��������ID

	private int submittdNum; // δ�ύ����
	private int allResultNum; // ��Ӧ���ύ��

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
