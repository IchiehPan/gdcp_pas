package com.gdcp.pas.score.vo;

import java.util.ArrayList;

/**
 * @author ��ΰ��
 * @see ��Ӧ���ֹ����ӱ��VO
 * @version 0327-20:56
 */
public class ScoreRuleDetailVO {

	// ��Ӧ���ݿ���ֶ�
	private int scoreRuleId;// ����Id����id��
	private int scoreDetailId;// ϸ��Id
	private int detailScore;// ϸ�����
	private int detailLevel;// ϸ��ȼ�
	private int parentId;// ��ϸ��id
	private String descRiption;// ϸ��˵��
	private String remark;// ��ע

	private ArrayList<ScoreRuleDetailVO> childs = new ArrayList<ScoreRuleDetailVO>();

	// �����ʽ��Ҫ��ʾ��
	private int score;

	public int getScoreRuleId() {
		return scoreRuleId;
	}

	public void setScoreRuleId(int scoreRuleId) {
		this.scoreRuleId = scoreRuleId;
	}

	public int getScoreDetailId() {
		return scoreDetailId;
	}

	public void setScoreDetailId(int scoreDetailId) {
		this.scoreDetailId = scoreDetailId;
	}

	public int getDetailScore() {
		return detailScore;
	}

	public void setDetailScore(int detailScore) {
		this.detailScore = detailScore;
	}

	public int getDetailLevel() {
		return detailLevel;
	}

	public void setDetailLevel(int detailLevel) {
		this.detailLevel = detailLevel;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	public String getDescRiption() {
		return descRiption;
	}

	public void setDescRiption(String descRiption) {
		this.descRiption = descRiption;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<ScoreRuleDetailVO> getChilds() {
		return childs;
	}

	public void setChilds(ArrayList<ScoreRuleDetailVO> childs) {
		this.childs = childs;
	}

}
