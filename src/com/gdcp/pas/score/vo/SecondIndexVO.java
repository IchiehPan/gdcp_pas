package com.gdcp.pas.score.vo;

public class SecondIndexVO {

	private int ruleDetailId;// 二级规则Id
	private String secondIndex;// 二级规则名
	private int score;// 二级规则分数
	private int detailSV;

	public int getRuleDetailId() {
		return ruleDetailId;
	}

	public void setRuleDetailId(int ruleDetailId) {
		this.ruleDetailId = ruleDetailId;
	}

	public String getSecondIndex() {
		return secondIndex;
	}

	public void setSecondIndex(String secondIndex) {
		this.secondIndex = secondIndex;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getDetailSV() {
		return detailSV;
	}

	public void setDetailSV(int detailSV) {
		this.detailSV = detailSV;
	}
}
