package com.gdcp.pas.score.action;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.sql.RowSet;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.common.StringUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.common.extend.ExtendCode;
import com.gdcp.common.extend.ExtendCodeUtil;
import com.gdcp.pas.manage.bo.DeptBO;
import com.gdcp.pas.manage.bo.UserBO;
import com.gdcp.pas.manage.vo.DeptVO;
import com.gdcp.pas.manage.vo.UserVO;
import com.gdcp.pas.score.bo.AverageScoreBO;
import com.gdcp.pas.score.bo.ScoreResultBO;
import com.gdcp.pas.score.bo.ScoreResultDetailBO;
import com.gdcp.pas.score.bo.ScoreRuleDetailBO;
import com.gdcp.pas.score.config.ExtendCodeConfig;
import com.gdcp.pas.score.vo.AverageScoreVO;
import com.gdcp.pas.score.vo.DeptScoreVO;
import com.gdcp.pas.score.vo.MiddleScoreVO;
import com.gdcp.pas.score.vo.ScoreResultVO;
import com.gdcp.pas.score.vo.ScoreRuleDetailVO;
import com.opensymphony.xwork2.ActionContext;

/**
 * ��Ҫ�������ɿ��˺��ƽ����������Ȩ���շ���
 * 
 * @author ��ΰ��
 * @version 0407-11:59
 */
public class AverageScoreAction {
	private static Logger logger = Logger.getLogger(AverageScoreAction.class);

	// BO:
	AverageScoreBO asBo = new AverageScoreBO();
	ScoreResultBO srBo = new ScoreResultBO();
	UserBO uBo = new UserBO();
	DeptBO dBo = new DeptBO();
	ScoreRuleDetailBO scoreRuleDetailBO = new ScoreRuleDetailBO();
	ScoreResultDetailBO scoreResultDetailBO = new ScoreResultDetailBO();

	/**
	 * ���ɷ���
	 */
	public String calculateScore() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		try {
			eliminateHighLowMark();
			ArrayList<ScoreResultVO> objects = (ArrayList<ScoreResultVO>) srBo.getAllObject();
			for (ScoreResultVO object : objects) {
				calculateAverageScore(object);
				calculateWeightScore(object);
			}
			asBo.TeachingDepartmentAggregateScore();

			asBo.NotTeachingDepartmentAggregateScore();

			asBo.LeaderAggregateScore();

			asBo.otherAggregateScore();

		} catch (Exception e) {
			logger.error("���ɷ���ʧ��", e);
		}

		session.put("message", "���˽������������");
		return "calculate_sccsee";
	}

	/**
	 * ȥ��15%���˷���
	 */
	private void eliminateHighLowMark() {
		DbAccess dbAccess = new DbAccess();
		double highNum = 0.05;
		double lowNum = 0.1;
		String sql = "select distinct OBJECTID,objecttype,scorertype from TB_SCORERESULT where OBJECTTYPE <> '100120' and OBJECTTYPE <> '100121'";

		RowSet rs = null;

		try {
			rs = dbAccess.executeQuery(sql);
			while (rs != null && rs.next()) {
				String objectId = rs.getString("OBJECTID");
				String objectType = rs.getString("objecttype");
				String scorerType = rs.getString("scorertype");
				String countSql = "select count(*) from TB_SCORERESULT where OBJECTID='" + objectId
						+ "' and objecttype='" + objectType + "' and scorertype='" + scorerType + "' and status='2'";
				RowSet rs1 = dbAccess.executeQuery(countSql);
				int count = 21;
				if (rs1 != null && rs1.next()) {
					count = rs1.getInt(1);
				}

				int a1 = getRound(count * highNum);
				int a2 = getRound(count * lowNum);

				if (a2 > 0) {
					String tempSql = "select * from TB_SCORERESULT where OBJECTID='" + objectId + "' and objecttype='"
							+ objectType + "'  and scorertype='" + scorerType + "'"
							+ " and SCORERID not in (select USERID from tb_user where (USERCHARACTER='100212' or USERCHARACTER='100213' or USERCHARACTER='100214' or USERCHARACTER='100215') "
							+ " and deptid=(select DEPTID from tb_user where USERID=TB_SCORERESULT.OBJECTID))"
							+ " and status='2' order by scoreresult desc";
					rs1 = dbAccess.executeQuery(tempSql);
					int tempCount = 0;
					while (rs1 != null && rs1.next()) {
						if ((tempCount < a1 || tempCount >= count - a2)
								&& !"100319".equals(rs1.getString("scorertype"))) {
							String recordId = rs1.getString("id");
							String updateSql = "update TB_SCORERESULT set status = '-1' where id='" + recordId + "'";
							dbAccess.executeUpdate(updateSql);
						}
						tempCount++;
					}
				}
			}
		} catch (Exception e) {
			logger.error("ȥ��15%�ļ��˷���ʧ��", e);
		}
	}

	/**
	 * �������У���ƽ���ɼ���
	 * 
	 * @param object
	 *            ĳ�����˶����ScoreResultVO
	 * @param extendCodes
	 */
	private void calculateAverageScore(ScoreResultVO object) throws Exception {

		String objectId = object.getObjectId();
		int objectType = object.getObjectType();
		int scoreRuleId = object.getScoreRuleId();

		ArrayList<ExtendCode> extendCodes = (ArrayList<ExtendCode>) ExtendCodeUtil.getInstance()
				.getExtendCodeListByType("weight");
		for (ExtendCode extendCode : extendCodes) {
			String[] type = extendCode.getNumKey().split("_");

			if (type[0].equals(objectType + "")) {
				int scorerType = Integer.parseInt(type[1]);

				ArrayList<UserVO> uVos = new ArrayList<>(); // ��Ÿ������͵�userId
				switch (scorerType) { // ����������������
				case ExtendCodeConfig.SCORERTYPE_SCHOOL_LEADER:
					uVos = (ArrayList<UserVO>) uBo.getLeaderList();
					break;
				case ExtendCodeConfig.SCORERTYPE_DEPT_LEADER:
					if (objectType == ExtendCodeConfig.OBJECTTYPE_WORK_SKILLS) {
						int[] deptIds = { 100017, 100028, 100015, 100002, 100022, 100013, 100007 };
						for (int j = 0; j < deptIds.length; j++) {
							uVos.addAll(uBo.getDeptLeader(deptIds[j] + ""));
						}
						// } else if (objectType ==
						// ExtendCodeConfig.OBJECTTYPE_TEACH_LEADER) {
						// int[] deptIds = { 100017, 100020, 100028, 100022,
						// 100015, 100018 };
						// for (int j = 0; j < deptIds.length; j++) {
						// uVos.addAll(uBo.getDeptLeader(deptIds[j] + ""));
						// }
					} else if (objectType == ExtendCodeConfig.OBJECTTYPE_ADMINISTRATIVE_LEADER
							|| objectType == ExtendCodeConfig.OBJECTTYPE_ADMINISTRATIVE_DEPT
							|| objectType == ExtendCodeConfig.OBJECTTYPE_TEACH_LEADER) { // modify
																							// by
																							// liuborui
																							// 20161217
																							// ��ѧ�����쵼�����۲��ű�Ϊ���в���
						ArrayList<DeptVO> deptVos = (ArrayList<DeptVO>) dBo.queryAll();
						for (int j = 0; j < deptVos.size(); j++) {
							uVos.addAll(uBo.getDeptLeader(deptVos.get(j).getDeptId() + ""));
						}
					} else if (objectType == ExtendCodeConfig.OBJECTTYPE_INCLUDE) { // ��ͨ�����(�ǽ�ѧ�����,���ǽ�ѧר����)
						ArrayList<DeptVO> deptVos = (ArrayList<DeptVO>) dBo.queryAll();
						int objectDeptId = uBo.getUserByTeacherId(objectId).getDeptId();
						for (int j = 0; j < deptVos.size(); j++) {
							if (deptVos.get(j).getDeptId() != objectDeptId) {
								uVos.addAll(uBo.getDeptLeader(deptVos.get(j).getDeptId() + ""));
							}
						}
					} else if (objectType == ExtendCodeConfig.OBJECTTYPE_TEACH_DEPT) { // ��ѧ����
						// modify by liuborui 20161217 ��ѧ���ŵ����۲��ű�Ϊ���в���
						ArrayList<DeptVO> deptVos = (ArrayList<DeptVO>) dBo.queryAll();
						for (int j = 0; j < deptVos.size(); j++) {
							uVos.addAll(uBo.getDeptLeader(deptVos.get(j).getDeptId() + ""));
						}
					}
					break;
				case ExtendCodeConfig.SCORERTYPE_INDEPT_WORKER:
					uVos = (ArrayList<UserVO>) uBo.getWorkmateList(objectId);
					break;
				case ExtendCodeConfig.SCORERTYPE_DEPT2_LEADER:
					uVos = (ArrayList<UserVO>) uBo.getDeptLeaderByUser(objectId);
					break;
				case ExtendCodeConfig.SCORERTYPE_REPRESENTATIVE:
					uVos = (ArrayList<UserVO>) uBo.getDelegateList(objectId);
					break;
				case ExtendCodeConfig.SCORERTYPE_CLASSROOM_TEACHER:
					uVos = (ArrayList<UserVO>) uBo.getHeadMasterList(objectId);
					break;
				case ExtendCodeConfig.SCORERTYPE_GROUP_OBJECT:
					uVos = (ArrayList<UserVO>) uBo.getPositionUserList(objectId);
					break;
				case ExtendCodeConfig.SCORERTYPE_DEPT2_SELF:
					if (objectType == ExtendCodeConfig.OBJECTTYPE_OFFICE_ACADEMIC) {
						int[] deptIds = { 100017, 100020, 100002, 100022, 100015 };
						for (int j = 0; j < deptIds.length; j++) {
							uVos.add(uBo.getDeptLeaderS(deptIds[j] + ""));
						}
					} else if (objectType == ExtendCodeConfig.OBJECTTYPE_COUNSELOR) {
						int[] deptIds = { 100028, 100004, 100018 };
						for (int j = 0; j < deptIds.length; j++) {
							uVos.add(uBo.getDeptLeaderS(deptIds[j] + ""));
						}
					}
					break;
				// �˽� 10-05-10:32
				case ExtendCodeConfig.SCORERTYPE_DEPT_ALLWORKER:
					if (objectType == ExtendCodeConfig.OBJECTTYPE_OFFICE_ACADEMIC) {// ��ͨ�����(�칫��,����Ա)

						int[] deptIds = { 100017, 100020, 100002, 100022, 100015 };
						for (int j = 0; j < deptIds.length; j++) {
							uVos.addAll(uBo.getUsersByDept(deptIds[j] + ""));
						}
					} else if (objectType == ExtendCodeConfig.OBJECTTYPE_COUNSELOR) {// ��ͨ�����(����Ա)

						int[] deptIds = { 100028, 100018, 100004, 100010 };
						for (int j = 0; j < deptIds.length; j++) {
							uVos.addAll(uBo.getUsersByDept(deptIds[j] + ""));
						}
					}
					// uVos = (ArrayList<UserVO>)
					// srBo.getScorerByObjectType(objectId + "", objectType +
					// "",
					// scorerType + "");
					// break;
				}

				StringBuffer scorerIdsStrBuf = new StringBuffer("('0'");
				for (UserVO uVo : uVos) {
					scorerIdsStrBuf.append("," + StringUtil.fieldValue(uVo.getTeacherId()));
				}
				String scorerIdsStr = scorerIdsStrBuf.toString() + ")";

				double averageScore = 0;
				if (scorerType != ExtendCodeConfig.SCORERTYPE_DEPT_ALLWORKER) {
					// averageScore =
					// srBo.getAverageScoreByObjectIdAndScoreRuleIdAndScorerIds(objectId,
					// scoreRuleId,
					// scorerIdsStr);
					averageScore = srBo.getAverageScoreByObjectIdAndScoreRuleIdAndScorerIdsX(objectId, scoreRuleId,
							scorerIdsStr, scorerType);
				} else {
					Hashtable<String, DeptScoreVO> ht = new Hashtable<>();
					for (int i = 0; i < uVos.size(); i++) {
						UserVO tempUser = uVos.get(i);
						String deptId = tempUser.getDeptId() + "";

						DeptScoreVO deptScoreVO = null;
						if (ht.containsKey(deptId)) {
							deptScoreVO = ht.get(deptId);
						} else {
							deptScoreVO = new DeptScoreVO();
							deptScoreVO.setDetpId(deptId);
							ht.put(deptId, deptScoreVO);
						}
						double tempScore = srBo.getScore(objectId, objectType, String.valueOf(tempUser.getUserId()));
						if (tempUser.getUserCharacter() == ExtendCodeConfig.USERLEVEL_TEACH_CHIEF
								|| tempUser.getUserCharacter() == ExtendCodeConfig.USERLEVEL_ADMINISTRATIVE_CHIEF) { // ������ְ
							deptScoreVO.setDeptLeaderANum(deptScoreVO.getDeptLeaderANum() + 1);
							deptScoreVO.setDeptLeaderAScore(deptScoreVO.getDeptLeaderAScore() + tempScore);

						} else if (tempUser.getUserCharacter() == ExtendCodeConfig.USERLEVEL_TEACH_DEPUTY
								|| tempUser.getUserCharacter() == ExtendCodeConfig.USERLEVEL_ADMINISTRATIVE_DEPUTY) { // ���Ÿ�ְ
							deptScoreVO.setDeptLeaderBNum(deptScoreVO.getDeptLeaderBNum() + 1);
							deptScoreVO.setDeptLeaderBScore(deptScoreVO.getDeptLeaderBScore() + tempScore);
						} else if (tempUser.getUserCharacter() == ExtendCodeConfig.USERLEVEL_GENERAL_STAFF) { // ������ͨԱ��
							deptScoreVO.setDeptMemberNum(deptScoreVO.getDeptMemberNum() + 1);
							deptScoreVO.setDeptMemberScore(deptScoreVO.getDeptMemberScore() + tempScore);
						}
					}
					Enumeration<DeptScoreVO> eu = ht.elements();
					int count = 0;
					double total = 0;
					while (eu.hasMoreElements()) {
						DeptScoreVO deptScoreVO = eu.nextElement();
						total = total + deptScoreVO.getAverageScore();
						count++;
					}
					if (count != 0) {
						averageScore = total / count;
					}
				}

				int actualCommit = srBo.getCountByObjectIdAndScorerIdsAndScoreRuleId(objectId, scorerIdsStr,
						scoreRuleId, 2); // �Ѿ��ύ������
				int shouldCommit = srBo.getCountByObjectIdAndScorerIdsAndScoreRuleId(objectId, scorerIdsStr,
						scoreRuleId, 3); // ��Ҫ�ύ��������

				AverageScoreVO asVo = new AverageScoreVO();

				asVo.setObjectId(objectId);
				asVo.setObjectType(objectType);
				asVo.setSocrerType(scorerType);
				asVo.setScoreRuleId(scoreRuleId);
				asVo.setAverageScore(averageScore);
				asVo.setActualCommit(actualCommit);
				asVo.setShouldCommit(shouldCommit);

				if (asBo.isExistScore(asVo)) {
					asBo.updateScore(asVo);
				} else {
					asBo.insertScore(asVo);
				}
			}
		}
	}

	/**
	 * �������У�����Ȩ�ɼ���
	 * 
	 * @param object
	 *            ĳ�����˶����ScoreResultVO
	 * @param extendCodes
	 */
	private void calculateWeightScore(ScoreResultVO object) throws Exception {

		String objectId = object.getObjectId();
		int objectType = object.getObjectType();
		int scorerType = 0;
		int scoreRuleId = object.getScoreRuleId();
		double weigthScore = 0;
		int actualCommit = 0;
		int shouldCommit = 0;

		ArrayList<AverageScoreVO> averageScoreVos = (ArrayList<AverageScoreVO>) asBo
				.getAllAverageScoreByObjectIdAndObjectTypeAndScoreRuleID(objectId, objectType, scoreRuleId);

		int averageScorerTyoe = 0;
		double weight = 0;
		for (AverageScoreVO averageScoreVO : averageScoreVos) {
			averageScorerTyoe = averageScoreVO.getSocrerType();

			weight = Double.parseDouble(
					ExtendCodeUtil.getInstance().getValue("weight", objectType + "_" + averageScorerTyoe)) / 100;

			weigthScore += averageScoreVO.getAverageScore() * weight;
		}

		actualCommit = srBo.getCountByObjectIdAndScoreRuleId(objectId, scoreRuleId, 2);
		shouldCommit = srBo.getCountByObjectIdAndScoreRuleId(objectId, scoreRuleId, 3);

		AverageScoreVO asVo = new AverageScoreVO();

		asVo.setObjectId(objectId);
		asVo.setObjectType(objectType);
		asVo.setSocrerType(scorerType);
		asVo.setScoreRuleId(scoreRuleId);
		asVo.setAverageScore(weigthScore);
		asVo.setActualCommit(actualCommit);
		asVo.setShouldCommit(shouldCommit);

		if (asBo.isExistScore(asVo)) {
			asBo.updateScore(asVo);
		} else {
			asBo.insertScore(asVo);
		}
	}

	/**
	 * �������ճɼ���¼Excel���� ��2_1ģ��İ�ť����
	 */
	public String outputWeightScore() {

		String csvName = "���ճɼ���¼.csv";
		StringBuffer csvContent = new StringBuffer("����,����,���۶�������,�÷�\n");

		try {

			ArrayList<AverageScoreVO> wsVos = (ArrayList<AverageScoreVO>) asBo.getAllWeightScore();
			for (AverageScoreVO asVo : wsVos) {
				csvContent.append(asVo.getObjectDeptName() + ",");
				csvContent.append(asVo.getObjectName() + ",");
				csvContent.append(ExtendCodeUtil.getInstance().getValue("objectType", asVo.getObjectType() + "") + ",");
				csvContent.append(new DecimalFormat("#0.00").format(asVo.getAverageScore()));
				csvContent.append("\n");
			}

			outputExcel(csvName, csvContent);

		} catch (Exception e) {
			logger.error("�������ճɼ���¼Excelʧ��", e);
		}

		return null;
	}

	/**
	 * �������ύ��ϸ��¼��Excel���� ��2_1ģ��İ�ť����
	 */
	public String outputCommitRate() {

		String csvName = "�ύ��ϸ��¼.csv";
		StringBuffer csvContent = new StringBuffer("����,����,���۶�������,");
		csvContent.append("��������1,ʵ������,Ӧ������,");
		// csvContent.append("ƽ����,");
		csvContent.append("��������2,ʵ������,Ӧ������,");
		// csvContent.append("ƽ����,");
		csvContent.append("��������3,ʵ������,Ӧ������,");
		// csvContent.append("ƽ����,");
		csvContent.append("��������4,ʵ������,Ӧ������");
		// csvContent.append("ƽ����,");
		csvContent.append("\n");

		try {

			ArrayList<AverageScoreVO> wsVos = (ArrayList<AverageScoreVO>) asBo.getAllWeightScore();
			for (AverageScoreVO wsVo : wsVos) {

				csvContent.append(wsVo.getObjectDeptName() + ",");
				csvContent.append(wsVo.getObjectName() + ",");
				csvContent.append(ExtendCodeUtil.getInstance().getValue("objectType", wsVo.getObjectType() + "") + ",");

				ArrayList<AverageScoreVO> asVos = (ArrayList<AverageScoreVO>) asBo
						.getAllAverageScoreByObjectIdAndObjectTypeAndScoreRuleID(wsVo.getObjectId(),
								wsVo.getObjectType(), wsVo.getScoreRuleId());

				int i = 0;
				for (AverageScoreVO asVo : asVos) {
					csvContent.append(
							ExtendCodeUtil.getInstance().getValue("scorerType", asVo.getSocrerType() + "") + ",");
					csvContent.append(asVo.getActualCommit() + ",");
					csvContent.append(asVo.getShouldCommit() + ",");
					// csvContent.append(new
					// DecimalFormat("#0.00").format(asVo.getAverageScore()) +
					// ",");
					i++;
				}
				if (i < 4) {
					csvContent.append("��,");
					csvContent.append("��,");
					// csvContent.append("��,");
				}
				csvContent.append("\n");

			}

			outputExcel(csvName, csvContent);

		} catch (Exception e) {
			logger.error("�������ύ��ϸ��¼��Excelʧ��", e);
		}

		return null;
	}

	/**
	 * �����в�ɲ���ȿ��˵÷�Excel���� ��2_3ģ��İ�ť����
	 */
	public String outputMiddleScore() {

		String csvName = "�в�ɲ���ȿ��˵÷ֱ�.csv";

		StringBuffer csvContent = new StringBuffer("����,����,");
		csvContent.append("˼���������ʵ÷�,��֯�쵼�����÷�,��������÷�,����ʵ���÷�,");
		csvContent.append("ѧУ�쵼�÷�,ȫУ���򲿷֣���������쵼����,���ڲ��Ž�ְ������,�����ְ����������,");
		csvContent.append("�ܷ�,��ȿ��˵ȼ�\n");

		try {
			ArrayList<AverageScoreVO> wsVos = (ArrayList<AverageScoreVO>) asBo.getAllMiddleWeightScore();

			ArrayList<MiddleScoreVO> msVos = new ArrayList<MiddleScoreVO>();

			for (AverageScoreVO wsVo : wsVos) {
				MiddleScoreVO msVo = new MiddleScoreVO(wsVo);

				String objectId = msVo.getObjectId();
				int scoreRuleId = msVo.getScoreRuleId();

				//
				msVo.setLv1_1score("0.0");
				msVo.setLv1_2score("0.0");
				msVo.setLv1_3score("0.0");
				msVo.setLv1_4score("0.0");

				ArrayList<AverageScoreVO> asVos = (ArrayList<AverageScoreVO>) asBo
						.getAllAverageScoreByObjectIdAndObjectTypeAndScoreRuleID(wsVo.getObjectId(),
								wsVo.getObjectType(), wsVo.getScoreRuleId());
				for (AverageScoreVO asVo : asVos) {

					// Ȩ�أ�
					double weight = Double.parseDouble(ExtendCodeUtil.getInstance().getValue("weight",
							msVo.getObjectType() + "_" + asVo.getSocrerType())) / 100;

					// ����scorerIds��uVos
					ArrayList<UserVO> uVos = new ArrayList<>();

					// �� �������� ��ȡƽ����Ȩ�֣�
					double wScore = asVo.getAverageScore() * weight;
					switch (asVo.getSocrerType()) {
					case ExtendCodeConfig.SCORERTYPE_SCHOOL_LEADER:
						msVo.setScorerTypeScore1(new DecimalFormat("#0.00").format(wScore));
						uVos = (ArrayList<UserVO>) uBo.getLeaderList();
						break;
					case ExtendCodeConfig.SCORERTYPE_DEPT_LEADER:
						msVo.setScorerTypeScore2(new DecimalFormat("#0.00").format(wScore));
						if (msVo.getObjectType() == ExtendCodeConfig.OBJECTTYPE_TEACH_LEADER) {
							// int[] deptIds = { 100017, 100020, 100028, 100022,
							// 100015, 100018 };
							// for (int j = 0; j < deptIds.length; j++) {
							// uVos.addAll(uBo.getDeptLeader(deptIds[j] + ""));
							// }
							ArrayList<DeptVO> deptVos = (ArrayList<DeptVO>) dBo.queryAll();
							for (int j = 0; j < deptVos.size(); j++) {
								uVos.addAll(uBo.getDeptLeader(deptVos.get(j).getDeptId() + ""));
							}
						} else if (msVo.getObjectType() == ExtendCodeConfig.OBJECTTYPE_ADMINISTRATIVE_LEADER) {
							ArrayList<DeptVO> deptVos = (ArrayList<DeptVO>) dBo.queryAll();
							for (int j = 0; j < deptVos.size(); j++) {
								uVos.addAll(uBo.getDeptLeader(deptVos.get(j).getDeptId() + ""));
							}
						}
						break;
					case ExtendCodeConfig.SCORERTYPE_INDEPT_WORKER:
						msVo.setScorerTypeScore3(new DecimalFormat("#0.00").format(wScore));
						uVos = (ArrayList<UserVO>) uBo.getWorkmateList(objectId);
						break;
					case ExtendCodeConfig.SCORERTYPE_REPRESENTATIVE:
						msVo.setScorerTypeScore4(new DecimalFormat("#0.00").format(wScore));
						uVos = (ArrayList<UserVO>) uBo.getDelegateList(objectId);
						break;
					}

					StringBuffer scorerIdsStrBuf = new StringBuffer("('0'");
					for (UserVO uVo : uVos) {
						scorerIdsStrBuf.append("," + StringUtil.fieldValue(uVo.getTeacherId()));
					}
					String scorerIdsStr = scorerIdsStrBuf.toString() + ")";

					ArrayList<ScoreResultVO> srVos = (ArrayList<ScoreResultVO>) srBo
							.getScoreResultByObjectIdAndScoreRuleIdAndScorerIds(objectId, scoreRuleId, scorerIdsStr);
					StringBuffer scoreResultIdsStrBuf = new StringBuffer("(0");
					for (ScoreResultVO srVo : srVos) {
						scoreResultIdsStrBuf.append("," + srVo.getId());
					}
					String scoreResultIdsStr = scoreResultIdsStrBuf.toString() + ")";

					ArrayList<ScoreRuleDetailVO> srdLv1Vos = (ArrayList<ScoreRuleDetailVO>) scoreRuleDetailBO
							.getScoreRuleByRuleIdAndParentId(scoreRuleId, 0);
					for (ScoreRuleDetailVO srdLv1Vo : srdLv1Vos) {
						int srdLv1VoId = srdLv1Vo.getScoreDetailId();

						double averageDetailScore = scoreResultDetailBO
								.getAverageDetailScoreByScorerIdsStrAndSrdLv1VoId(scoreResultIdsStr, srdLv1VoId);

						switch (srdLv1Vo.getScoreDetailId()) {
						case 10010001:
							double lv1_1score = Double.parseDouble(msVo.getLv1_1score());
							lv1_1score += averageDetailScore * weight;
							msVo.setLv1_1score(String.valueOf(lv1_1score));
							break;
						case 10010002:
							double lv1_2score = Double.parseDouble(msVo.getLv1_2score());
							lv1_2score += averageDetailScore * weight;
							msVo.setLv1_2score(String.valueOf(lv1_2score));
							break;
						case 10010003:
							double lv1_3score = Double.parseDouble(msVo.getLv1_3score());
							lv1_3score += averageDetailScore * weight;
							msVo.setLv1_3score(String.valueOf(lv1_3score));
							break;
						case 10010004:
							double lv1_4score = Double.parseDouble(msVo.getLv1_4score());
							lv1_4score += averageDetailScore * weight;
							msVo.setLv1_4score(String.valueOf(lv1_4score));
							break;
						default:
							break;
						}
					}

				}
				if (msVo.getScorerTypeScore4() == null || msVo.getScorerTypeScore4().equals("")) {
					msVo.setScorerTypeScore4("��");
				}
				double db = 0;
				DecimalFormat df = new DecimalFormat("#0.00");
				db = Double.parseDouble(msVo.getLv1_1score());
				msVo.setLv1_1score(df.format(db));
				db = Double.parseDouble(msVo.getLv1_2score());
				msVo.setLv1_2score(df.format(db));
				db = Double.parseDouble(msVo.getLv1_3score());
				msVo.setLv1_3score(df.format(db));
				db = Double.parseDouble(msVo.getLv1_4score());
				msVo.setLv1_4score(df.format(db));
				db = msVo.getAverageScore();
				msVo.setAverageScore(Double.parseDouble(df.format(db)));

				msVos.add(msVo);
			}

			int i = 0;
			for (MiddleScoreVO msVo : msVos) {
				csvContent.append((i + 1) + "," + msVo.getObjectName() + ",");
				csvContent.append(msVo.getLv1_1score() + "," + msVo.getLv1_2score() + "," + msVo.getLv1_3score() + ","
						+ msVo.getLv1_4score() + ",");
				csvContent.append(msVo.getScorerTypeScore1() + "," + msVo.getScorerTypeScore2() + ","
						+ msVo.getScorerTypeScore3() + "," + msVo.getScorerTypeScore4() + ",");
				csvContent.append(msVo.getAverageScore() + ",\n");
				i++;
			}
			outputExcel(csvName, csvContent);
		} catch (Exception e) {
			logger.error("�����в�ɲ���ȿ��˵÷�Excelʧ��", e);
		}
		return null;
	}

	/**
	 * ����Excel���ļ���.csv���Ĺ��÷���
	 */
	private void outputExcel(String csvName, StringBuffer csvContent) throws IOException {
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("application/octet-stream");
		csvName = new String(csvName.getBytes(), "ISO8859_1");
		response.setHeader("Content-disposition", "attachment;filename=\"" + csvName + "\"");
		InputStream in = new ByteArrayInputStream(csvContent.toString().getBytes());
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		byte[] buffer = new byte[1024];
		int length = 0;

		while ((length = in.read(buffer)) != -1) {
			bos.write(buffer, 0, length);
		}

		if (in != null) {
			in.close();
		}
		if (bos != null) {
			bos.close();
		}
	}

	/*
	 * ȡ���� ��������
	 **/
	public int getRound(double dSource) {
		int iRound;
		// BigDecimal�Ĺ��캯������������double
		BigDecimal deSource = new BigDecimal(dSource);
		// deSource.setScale(0,BigDecimal.ROUND_HALF_UP) ����ֵ���� BigDecimal
		// intValue() ������BigDecimalת��Ϊint
		iRound = deSource.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		return iRound;
	}

}
