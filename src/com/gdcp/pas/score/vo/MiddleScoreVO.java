package com.gdcp.pas.score.vo;

/**
 * @author 陈伟镇
 *
 */
public class MiddleScoreVO {

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
	private String lv1_1score;
	private String lv1_2score;
	private String lv1_3score;
	private String lv1_4score;
	private String scorerTypeScore1;
	private String scorerTypeScore2;
	private String scorerTypeScore3;
	private String scorerTypeScore4;

	public MiddleScoreVO(AverageScoreVO asVo) {
		averageId = asVo.getAverageId();
		objectId = asVo.getObjectId();
		objectType = asVo.getObjectType();
		socrerType = asVo.getSocrerType();
		scoreRuleId = asVo.getScoreRuleId();
		averageScore = asVo.getAverageScore();
		actualCommit = asVo.getActualCommit();
		shouldCommit = asVo.getShouldCommit();
		remark = asVo.getRemark();

		objectDeptName = asVo.getObjectDeptName();
		objectName = asVo.getObjectName();
	}

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

	public String getLv1_1score() {
		return lv1_1score;
	}

	public void setLv1_1score(String lv1_1score) {
		this.lv1_1score = lv1_1score;
	}

	public String getLv1_2score() {
		return lv1_2score;
	}

	public void setLv1_2score(String lv1_2score) {
		this.lv1_2score = lv1_2score;
	}

	public String getLv1_3score() {
		return lv1_3score;
	}

	public void setLv1_3score(String lv1_3score) {
		this.lv1_3score = lv1_3score;
	}

	public String getLv1_4score() {
		return lv1_4score;
	}

	public void setLv1_4score(String lv1_4score) {
		this.lv1_4score = lv1_4score;
	}

	public String getScorerTypeScore1() {
		return scorerTypeScore1;
	}

	public void setScorerTypeScore1(String scorerTypeScore1) {
		this.scorerTypeScore1 = scorerTypeScore1;
	}

	public String getScorerTypeScore2() {
		return scorerTypeScore2;
	}

	public void setScorerTypeScore2(String scorerTypeScore2) {
		this.scorerTypeScore2 = scorerTypeScore2;
	}

	public String getScorerTypeScore3() {
		return scorerTypeScore3;
	}

	public void setScorerTypeScore3(String scorerTypeScore3) {
		this.scorerTypeScore3 = scorerTypeScore3;
	}

	public String getScorerTypeScore4() {
		return scorerTypeScore4;
	}

	public void setScorerTypeScore4(String scorerTypeScore4) {
		this.scorerTypeScore4 = scorerTypeScore4;
	}

}
