package com.gdcp.pas.score.vo;

/**
 * @author ������
 * @see ��Ӧ���ֹ�����VO
 * @version 1006-20:56
 */
public class EvaluateScoreRuleVO {

	private int scoreRuleId;// ���ֹ���id
	private String ruleName;// ���ֹ������
	private int status; // ����״̬

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
