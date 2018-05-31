package com.gdcp.pas.score.vo;

import java.util.ArrayList;

/**
 * @author 陈伟镇
 * @see 对应评分规则表的VO
 * @version 0327-20:56
 */
public class ScoreRuleVO {

	private int scoreRuleId;
	private int ruleYear;
	private int ruleType;
	private int score;
	private int detailLevel;
	private String ruleName;
	private String remark;

	private ArrayList<Integer> srdLV1Ids = new ArrayList<Integer>();

	public int getScoreRuleId() {
		return scoreRuleId;
	}

	public void setScoreRuleId(int scoreRuleId) {
		this.scoreRuleId = scoreRuleId;
	}

	public int getRuleYear() {
		return ruleYear;
	}

	public void setRuleYear(int ruleYear) {
		this.ruleYear = ruleYear;
	}

	public int getRuleType() {
		return ruleType;
	}

	public void setRuleType(int ruleType) {
		this.ruleType = ruleType;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getDetailLevel() {
		return detailLevel;
	}

	public void setDetailLevel(int detailLevel) {
		this.detailLevel = detailLevel;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public ArrayList<Integer> getSrdLV1s() {
		return srdLV1Ids;
	}

	public void setSrdLV1s(ArrayList<Integer> srdLV1Ids) {
		this.srdLV1Ids = srdLV1Ids;
	}

}
