package com.gdcp.pas.score.vo;

/**
 * @author 陈伟镇
 * @version 0329-00:21
 * @see 对应评分结果明细表的VO
 */
public class ScoreResultDetailVO {

	private int scoreDetailId; // 自己的id
	private int scoreId; // 结果主表对应记录id
	private int detailId; // 规则细则id
	private int score; // 分数
	private String remark;

	public int getScoreDetailId() {
		return scoreDetailId;
	}

	public void setScoreDetailId(int scoreDetailId) {
		this.scoreDetailId = scoreDetailId;
	}

	public int getScoreId() {
		return scoreId;
	}

	public void setScoreId(int scoreId) {
		this.scoreId = scoreId;
	}

	public int getDetailId() {
		return detailId;
	}

	public void setDetailId(int detailId) {
		this.detailId = detailId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
