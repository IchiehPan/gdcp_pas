/**
 * 
 */
package com.gdcp.pas.score.vo;

/**
 * @author ����:������
 * @version ����ʱ�䣺2015-9-30 ����12:39:01 ��˵������ѧ���Ŷ�������VO
 */
public class DeptScoreQuantitationVO {
	private int deptId; // ����id
	private String deptName; // ��������
	private int deptType; // ��������
	private String remark; // ��ע
	private float quantitationScore; // ���Ŷ�����

	public float getQuantitationScore() {
		return quantitationScore;
	}

	public void setQuantitationScore(float quantitationScore) {
		this.quantitationScore = quantitationScore;
	}

	public int getDeptId() {
		return deptId;
	}

	public void setDeptId(int deptId) {
		this.deptId = deptId;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public int getDeptType() {
		return deptType;
	}

	public void setDeptType(int deptType) {
		this.deptType = deptType;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
