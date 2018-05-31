package com.gdcp.pas.manage.vo;

/**
 * @author 黄岸鹏 2015-03-23
 */
public class FunctionVO {
	private String functionId;
	private String functionName;
	private String isValid; // 0表示无效，1表示有效
	private String remark;

	public String getFunctionId() {
		return functionId;
	}

	public void setFunctionId(String functionId) {
		this.functionId = functionId;
	}

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
