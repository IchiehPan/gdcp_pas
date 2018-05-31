package com.gdcp.pas.manage.vo;

/**    
 * @author 作者:张俊杰
 * @version 创建时间：2015-3-26 下午3:28:17  
 * 类说明   创建评分结果VO
 */
/**
 * @author Administrator
 *
 */
public class ScoreResultVO {
	private int id;
	private String objectId; // 评分对象id
	private String scorerId; // 评价主体id
	private int scoreruleId; // 评分规则id
	private String objectTypeId; // 考核对象类型ID
	private String scorerTypeId; // 评价主体类型ID
	private String obecjtType; // 考核对象类型
	private String scorerType; // 评价主体类型
	private int status; // 评分状态
	private String remark; // 备注
	private String odeptName; // 评分对象所属部门名字
	private String ouserName; // 评分对象名字
	private String sdeptName; // 评价主体所属部门名字
	private String suserName; // 评价主体名字
	private String ruleName; // 评价规则名字
	private String deptName; // 用户所选部门
	private String userName; // 用户输入的名字
	private String userType; // 如果USERTYPE=0则userName表示OBJECTNAME,USERTYPE=1则userName表示SCORERNAME

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOdeptName() {
		return odeptName;
	}

	public void setOdeptName(String odeptName) {
		this.odeptName = odeptName;
	}

	public String getOuserName() {
		return ouserName;
	}

	public void setOuserName(String ouserName) {
		this.ouserName = ouserName;
	}

	public String getSdeptName() {
		return sdeptName;
	}

	public void setSdeptName(String sdeptName) {
		this.sdeptName = sdeptName;
	}

	public String getSuserName() {
		return suserName;
	}

	public void setSuserName(String suserName) {
		this.suserName = suserName;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

	public String getScorerId() {
		return scorerId;
	}

	public void setScorerId(String scorerId) {
		this.scorerId = scorerId;
	}

	public int getScoreruleId() {
		return scoreruleId;
	}

	public void setScoreruleId(int scoreruleId) {
		this.scoreruleId = scoreruleId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getObjectTypeId() {
		return objectTypeId;
	}

	public void setObjectTypeId(String objectTypeId) {
		this.objectTypeId = objectTypeId;
	}

	public String getScorerTypeId() {
		return scorerTypeId;
	}

	public void setScorerTypeId(String scorerTypeId) {
		this.scorerTypeId = scorerTypeId;
	}

	public String getObecjtType() {
		return obecjtType;
	}

	public void setObecjtType(String obecjtType) {
		this.obecjtType = obecjtType;
	}

	public String getScorerType() {
		return scorerType;
	}

	public void setScorerType(String scorerType) {
		this.scorerType = scorerType;
	}

}
