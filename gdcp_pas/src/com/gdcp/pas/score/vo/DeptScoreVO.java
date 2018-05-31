package com.gdcp.pas.score.vo;

public class DeptScoreVO {
	private String detpId;
	private int deptLeaderANum;
	private double deptLeaderAScore;
	private int deptLeaderBNum;
	private double deptLeaderBScore;
	private int deptMemberNum;
	private double deptMemberScore;

	public String getDetpId() {
		return detpId;
	}

	public void setDetpId(String detpId) {
		this.detpId = detpId;
	}

	public int getDeptLeaderANum() {
		return deptLeaderANum;
	}

	public void setDeptLeaderANum(int deptLeaderANum) {
		this.deptLeaderANum = deptLeaderANum;
	}

	public double getDeptLeaderAScore() {
		return deptLeaderAScore;
	}

	public void setDeptLeaderAScore(double deptLeaderAScore) {
		this.deptLeaderAScore = deptLeaderAScore;
	}

	public int getDeptLeaderBNum() {
		return deptLeaderBNum;
	}

	public void setDeptLeaderBNum(int deptLeaderBNum) {
		this.deptLeaderBNum = deptLeaderBNum;
	}

	public double getDeptLeaderBScore() {
		return deptLeaderBScore;
	}

	public void setDeptLeaderBScore(double deptLeaderBScore) {
		this.deptLeaderBScore = deptLeaderBScore;
	}

	public int getDeptMemberNum() {
		return deptMemberNum;
	}

	public void setDeptMemberNum(int deptMemberNum) {
		this.deptMemberNum = deptMemberNum;
	}

	public double getDeptMemberScore() {
		return deptMemberScore;
	}

	public void setDeptMemberScore(double deptMemberScore) {
		this.deptMemberScore = deptMemberScore;
	}

	public double getAverageScore() {
		double score = 0;
		if (deptLeaderANum == 0) {
			score = deptLeaderBScore / deptLeaderBNum * 0.6 + deptMemberScore / deptMemberNum * 0.4;
		} else if (deptLeaderBNum == 0) {
			if (deptMemberNum == 0) {
				score = deptLeaderAScore / deptLeaderANum;
			} else {
				score = deptLeaderAScore / deptLeaderANum * 0.75 + deptMemberScore / deptMemberNum * 0.25;
			}
		} else if (deptMemberNum == 0) {
			score = deptLeaderAScore / deptLeaderANum * 0.6 + deptLeaderBScore / deptLeaderBNum * 0.4;
		} else {
			score = deptLeaderAScore / deptLeaderANum * 0.5 + deptLeaderBScore / deptLeaderBNum * 0.3
					+ deptMemberScore / deptMemberNum * 0.2;
		}
		return score;
	}
}
