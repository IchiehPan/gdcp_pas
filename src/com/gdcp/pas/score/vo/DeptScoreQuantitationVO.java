/**
 * 
 */
package com.gdcp.pas.score.vo;

/**
 * @author 作者:王世鹏
 * @version 创建时间：2015-9-30 下午12:39:01 类说明：教学部门定量评分VO
 */
public class DeptScoreQuantitationVO {
	private int deptId; // 部门id
	private String deptName; // 部门名字
	private int deptType; // 部门类型
	private String remark; // 备注
	private float quantitationScore; // 部门定量分

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
