package com.gdcp.pas.score.vo;

/**
 * @author 王世鹏
 * @see 对应评分规则表的VO
 * @version 1006-20:56
 */
public class EvaluateScoreRuleVO {

	private int scoreRuleId;// 评分规则id
	private String ruleName;// 评分规则表名
	private int status; // 评分状态

	public int getScoreRuleId() {
		return scoreRuleId;
	}

	public void setScoreRuleId(int scoreRuleId) {
		this.scoreRuleId = scoreRuleId;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

}
