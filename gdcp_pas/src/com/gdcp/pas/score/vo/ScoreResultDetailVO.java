package com.gdcp.pas.score.vo;

/**
 * @author ��ΰ��
 * @version 0329-00:21
 * @see ��Ӧ���ֽ����ϸ���VO
 */
public class ScoreResultDetailVO {

	private int scoreDetailId; // �Լ���id
	private int scoreId; // ��������Ӧ��¼id
	private int detailId; // ����ϸ��id
	private int score; // ����
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
