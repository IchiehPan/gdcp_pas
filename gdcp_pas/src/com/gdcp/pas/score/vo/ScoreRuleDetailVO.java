package com.gdcp.pas.score.vo;

import java.util.ArrayList;

/**
 * @author 陈伟镇
 * @see 对应评分规则子表的VO
 * @version 0327-20:56
 */
public class ScoreRuleDetailVO {

	// 对应数据库的字段
	private int scoreRuleId;// 规则Id（表id）
	private int scoreDetailId;// 细则Id
	private int detailScore;// 细则分数
	private int detailLevel;// 细则等级
	private int parentId;// 父细则id
	private String descRiption;// 细则说明
	private String remark;// 备注

	private ArrayList<ScoreRuleDetailVO> childs = new ArrayList<ScoreRuleDetailVO>();

	// 表格形式需要显示：
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
