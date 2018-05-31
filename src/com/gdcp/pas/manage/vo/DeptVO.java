/**
 * 
 */
package com.gdcp.pas.manage.vo;

/**
 * @author 作者:潘文杰
 * @version 创建时间：2015-3-22 下午12:39:01 类说明 部门VO
 */
public class DeptVO {
	private int deptId; // 部门id
	private String deptName; // 部门名字
	private int deptType; // 部门类型
	private String remark; // 备注

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
