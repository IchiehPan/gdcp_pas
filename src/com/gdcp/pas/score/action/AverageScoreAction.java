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
 * 主要用于生成考核后的平均分数、加权最终分数
 * 
 * @author 陈伟镇
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
	 * 生成分数
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
			logger.error("生成分数失败", e);
		}

		session.put("message", "考核结果计数结束！");
		return "calculate_sccsee";
	}

	/**
	 * 去除15%极端分数
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
			logger.error("去除15%的极端分数失败", e);
		}
	}

	/**
	 * 计算所有：“平均成绩”
	 * 
	 * @param object
	 *            某个考核对象的ScoreResultVO
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

				ArrayList<UserVO> uVos = new ArrayList<>(); // 存放各种类型的userId
				switch (scorerType) { // 遍历评分主体类型
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
																							// 教学部门领导的评价部门变为所有部门
						ArrayList<DeptVO> deptVos = (ArrayList<DeptVO>) dBo.queryAll();
						for (int j = 0; j < deptVos.size(); j++) {
							uVos.addAll(uBo.getDeptLeader(deptVos.get(j).getDeptId() + ""));
						}
					} else if (objectType == ExtendCodeConfig.OBJECTTYPE_INCLUDE) { // 普通管理岗(非教学管理岗,含非教学专技岗)
						ArrayList<DeptVO> deptVos = (ArrayList<DeptVO>) dBo.queryAll();
						int objectDeptId = uBo.getUserByTeacherId(objectId).getDeptId();
						for (int j = 0; j < deptVos.size(); j++) {
							if (deptVos.get(j).getDeptId() != objectDeptId) {
								uVos.addAll(uBo.getDeptLeader(deptVos.get(j).getDeptId() + ""));
							}
						}
					} else if (objectType == ExtendCodeConfig.OBJECTTYPE_TEACH_DEPT) { // 教学部门
						// modify by liuborui 20161217 教学部门的评价部门变为所有部门
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
				// 宜杰 10-05-10:32
				case ExtendCodeConfig.SCORERTYPE_DEPT_ALLWORKER:
					if (objectType == ExtendCodeConfig.OBJECTTYPE_OFFICE_ACADEMIC) {// 普通管理岗(办公室,教务员)

						int[] deptIds = { 100017, 100020, 100002, 100022, 100015 };
						for (int j = 0; j < deptIds.length; j++) {
							uVos.addAll(uBo.getUsersByDept(deptIds[j] + ""));
						}
					} else if (objectType == ExtendCodeConfig.OBJECTTYPE_COUNSELOR) {// 普通管理岗(辅导员)

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
								|| tempUser.getUserCharacter() == ExtendCodeConfig.USERLEVEL_ADMINISTRATIVE_CHIEF) { // 部门正职
							deptScoreVO.setDeptLeaderANum(deptScoreVO.getDeptLeaderANum() + 1);
							deptScoreVO.setDeptLeaderAScore(deptScoreVO.getDeptLeaderAScore() + tempScore);

						} else if (tempUser.getUserCharacter() == ExtendCodeConfig.USERLEVEL_TEACH_DEPUTY
								|| tempUser.getUserCharacter() == ExtendCodeConfig.USERLEVEL_ADMINISTRATIVE_DEPUTY) { // 部门副职
							deptScoreVO.setDeptLeaderBNum(deptScoreVO.getDeptLeaderBNum() + 1);
							deptScoreVO.setDeptLeaderBScore(deptScoreVO.getDeptLeaderBScore() + tempScore);
						} else if (tempUser.getUserCharacter() == ExtendCodeConfig.USERLEVEL_GENERAL_STAFF) { // 部门普通员工
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
						scoreRuleId, 2); // 已经提交的人数
				int shouldCommit = srBo.getCountByObjectIdAndScorerIdsAndScoreRuleId(objectId, scorerIdsStr,
						scoreRuleId, 3); // 需要提交的总人数

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
	 * 计算所有：“加权成绩”
	 * 
	 * @param object
	 *            某个考核对象的ScoreResultVO
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
	 * 导出最终成绩记录Excel表方法 被2_1模块的按钮调用
	 */
	public String outputWeightScore() {

		String csvName = "最终成绩记录.csv";
		StringBuffer csvContent = new StringBuffer("部门,名称,评价对象类型,得分\n");

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
			logger.error("导出最终成绩记录Excel失败", e);
		}

		return null;
	}

	/**
	 * 导出“提交明细记录”Excel表方法 被2_1模块的按钮调用
	 */
	public String outputCommitRate() {

		String csvName = "提交明细记录.csv";
		StringBuffer csvContent = new StringBuffer("部门,名称,评价对象类型,");
		csvContent.append("评价主体1,实参评人,应参评人,");
		// csvContent.append("平均分,");
		csvContent.append("评价主体2,实参评人,应参评人,");
		// csvContent.append("平均分,");
		csvContent.append("评价主体3,实参评人,应参评人,");
		// csvContent.append("平均分,");
		csvContent.append("评价主体4,实参评人,应参评人");
		// csvContent.append("平均分,");
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
					csvContent.append("—,");
					csvContent.append("—,");
					// csvContent.append("—,");
				}
				csvContent.append("\n");

			}

			outputExcel(csvName, csvContent);

		} catch (Exception e) {
			logger.error("导出“提交明细记录”Excel失败", e);
		}

		return null;
	}

	/**
	 * 导出中层干部年度考核得分Excel表方法 被2_3模块的按钮调用
	 */
	public String outputMiddleScore() {

		String csvName = "中层干部年度考核得分表.csv";

		StringBuffer csvContent = new StringBuffer("排名,姓名,");
		csvContent.append("思想政治素质得分,组织领导能力得分,工作作风得分,工作实绩得分,");
		csvContent.append("学校领导得分,全校（或部分）内设机构领导评分,所在部门教职工评分,各类教职工代表评分,");
		csvContent.append("总分,年度考核等级\n");

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

					// 权重：
					double weight = Double.parseDouble(ExtendCodeUtil.getInstance().getValue("weight",
							msVo.getObjectType() + "_" + asVo.getSocrerType())) / 100;

					// 保存scorerIds的uVos
					ArrayList<UserVO> uVos = new ArrayList<>();

					// 按 对象类型 获取平均加权分：
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
					msVo.setScorerTypeScore4("—");
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
			logger.error("导出中层干部年度考核得分Excel失败", e);
		}
		return null;
	}

	/**
	 * 生成Excel表文件（.csv）的公用方法
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
	 * 取整， 四舍五入
	 **/
	public int getRound(double dSource) {
		int iRound;
		// BigDecimal的构造函数参数类型是double
		BigDecimal deSource = new BigDecimal(dSource);
		// deSource.setScale(0,BigDecimal.ROUND_HALF_UP) 返回值类型 BigDecimal
		// intValue() 方法将BigDecimal转化为int
		iRound = deSource.setScale(0, BigDecimal.ROUND_HALF_UP).intValue();
		return iRound;
	}

}
