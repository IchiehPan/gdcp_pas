package com.gdcp.pas.manage.vo;

/**    
 * @author ����:�ſ���
 * @version ����ʱ�䣺2015-3-26 ����3:28:17  
 * ��˵��   �������ֽ��VO
 */
/**
 * @author Administrator
 *
 */
public class ScoreResultVO {
	private int id;
	private String objectId; // ���ֶ���id
	private String scorerId; // ��������id
	private int scoreruleId; // ���ֹ���id
	private String objectTypeId; // ���˶�������ID
	private String scorerTypeId; // ������������ID
	private String obecjtType; // ���˶�������
	private String scorerType; // ������������
	private int status; // ����״̬
	private String remark; // ��ע
	private String odeptName; // ���ֶ���������������
	private String ouserName; // ���ֶ�������
	private String sdeptName; // ��������������������
	private String suserName; // ������������
	private String ruleName; // ���۹�������
	private String deptName; // �û���ѡ����
	private String userName; // �û����������
	private String userType; // ���USERTYPE=0��userName��ʾOBJECTNAME,USERTYPE=1��userName��ʾSCORERNAME

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
