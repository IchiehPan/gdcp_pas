package com.gdcp.pas.score.vo;

/**
 * @author 王世鹏
 * @see 对应评分规则表的VO
 * @version 1006-20:56
 */
public class ScoreResultsVO {

	private int resultsId; // 结果Id
	private int objectId; // 被评分对象的ID
	private String objectName; // 被评分对象的姓名
	private String objectDept; // 被评分对象所在部门
	private int ScoreRuleId; // 评分规则
	private int status; // 评分状态
	private float score; // 得分
	private String sonScore; // 子项分数

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
