package com.gdcp.pas.score.vo;

/**
 * @author ������
 * @see ��Ӧ���ֹ�����VO
 * @version 1006-20:56
 */
public class ScoreResultsVO {

	private int resultsId; // ���Id
	private int objectId; // �����ֶ����ID
	private String objectName; // �����ֶ��������
	private String objectDept; // �����ֶ������ڲ���
	private int ScoreRuleId; // ���ֹ���
	private int status; // ����״̬
	private float score; // �÷�
	private String sonScore; // �������

	public int getResultsId() {
		return resultsId;
	}

	public void setResultsId(int resultsId) {
		this.resultsId = resultsId;
	}

	public int getObjectId() {
		return objectId;
	}

	public void setObjectId(int objectId) {
		this.objectId = objectId;
	}

	public String getObjectName() {
		return objectName;
	}

	public void setObjectName(String objectName) {
		this.objectName = objectName;
	}

	public String getObjectDept() {
		return objectDept;
	}

	public void setObjectDept(String objectDept) {
		this.objectDept = objectDept;
	}

	public int getScoreRuleId() {
		return ScoreRuleId;
	}

	public void setScoreRuleId(int scoreRuleId) {
		ScoreRuleId = scoreRuleId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public float getScore() {
		return score;
	}

	public void setScore(float score) {
		this.score = score;
	}

	public String getSonScore() {
		return sonScore;
	}

	public void setSonScore(String sonScore) {
		this.sonScore = sonScore;
	}

}
