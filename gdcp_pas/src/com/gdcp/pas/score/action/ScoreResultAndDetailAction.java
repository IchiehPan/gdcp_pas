package com.gdcp.pas.score.action;

import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.bo.UserBO;
import com.gdcp.pas.manage.vo.UserVO;
import com.gdcp.pas.score.bo.ScoreResultBO;
import com.gdcp.pas.score.bo.ScoreResultDetailBO;
import com.gdcp.pas.score.bo.ScoreRuleBO;
import com.gdcp.pas.score.config.ExtendCodeConfig;
import com.gdcp.pas.score.vo.ScoreResultDetailVO;
import com.gdcp.pas.score.vo.ScoreResultVO;
import com.gdcp.pas.score.vo.ScoreRuleVO;
import com.opensymphony.xwork2.ActionContext;

/**
 * 主要是用于生成“评价表列表” 次要是处理“评价结果”（例如修改评价结果状态、评价结果明细）
 * 
 * @author 陈伟镇
 * @version 0329-23:43
 * @处理评分结果（及评分明细）的Action
 */
public class ScoreResultAndDetailAction {
	private static Logger logger = Logger.getLogger(ScoreResultAndDetailAction.class);

	// BO：
	private ScoreResultBO srBo = new ScoreResultBO();
	private ScoreResultDetailBO srdBo = new ScoreResultDetailBO();

	private UserBO userBo = new UserBO();

	// 列表显示需要
	private int readStatus;
	private String teacherId;
	private int scoreRuleId;
	// 显示进度需要
	private int SubmittdNum;
	private int AllResultNum;

	// 生评分关系需要：
	private int objectType;
	private int[] scorerTypes;// 这个值不能通过框架获得，要在方法体内通过request获得
	// private int scoreRuleId;上边已经有了，这行留下来指明要使用scoreRuleId

	// 评分表保存需要：
	private int scoreResultId; // 在jsp中从${session.scoreResultVo.id}获取
	private int changeTo; // 在jsp中从<int type="hidden" name="changeTo">获取

	// 对评分结果的操作：
	/**
	 * @see 获取该用户的各种状态的评分结果列表
	 */
	public String read() {

		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();
		Page page = new Page(request);
		if (teacherId == null || teacherId.equals("")) {
			teacherId = ((UserVO) ServletActionContext.getRequest().getSession().getAttribute("userVO")).getTeacherId();
		}
		try {
			if (readStatus == 0) {
				ArrayList<ScoreResultVO> beginSrVos = (ArrayList<ScoreResultVO>) srBo
						.getScoreResultByScorerIdPage(teacherId, 0, page);

				changeScoreRuleTable(beginSrVos);

				if (beginSrVos != null && beginSrVos.size() > 0) {
					request.setAttribute("beginScoreResultVOs", beginSrVos);
				} else {
					request.setAttribute("beginScoreResultVOs", null);
				}
				request.setAttribute("page", page);
				if (session.get("message") != null) {
					request.setAttribute("message", session.get("message"));
					session.put("message", null);
				}
				return "readBegin_success";
			} else {
				ArrayList<ScoreResultVO> saveAndCommitScoreResultVOs = (ArrayList<ScoreResultVO>) srBo
						.getScoreResultByScorerIdPage(teacherId, 3, page);
				changeScoreRuleTable(saveAndCommitScoreResultVOs);

				if (saveAndCommitScoreResultVOs != null && saveAndCommitScoreResultVOs.size() > 0) {
					request.setAttribute("saveAndCommitScoreResultVOs", saveAndCommitScoreResultVOs);
				} else {
					request.setAttribute("saveAndCommitScoreResultVOs", null);
				}
				request.setAttribute("page", page);
				if (session.get("message") != null) {
					request.setAttribute("message", session.get("message"));
					session.put("message", null);
				}
				return "readSaveAndCommit_success";
			}
		} catch (Exception e) {
			logger.error("获取评分表列表失败", e);
		}
		return "read_failed";
	}

	/**
	 * @see 获取该用户所有评分结果列表
	 */
	public String readAll() {
		HttpServletRequest request = ServletActionContext.getRequest();
		Page page = new Page(request);
		if (teacherId == null || teacherId.equals("")) {
			teacherId = ((UserVO) ServletActionContext.getRequest().getSession().getAttribute("userVO")).getTeacherId();
		}
		try {
			ArrayList<ScoreResultVO> srVos = (ArrayList<ScoreResultVO>) srBo.getScoreResultByScorerIdPage(teacherId,
					page);

			if (srVos != null && srVos.size() > 0) {
				request.setAttribute("ScoreResultVOs", srVos);
			} else {
				request.setAttribute("ScoreResultVOs", null);
			}
			request.setAttribute("page", page);
			return "readAll_success";
		} catch (Exception e) {
			logger.error("获取评分表列表失败", e);
		}
		return "read_failed";
	}

	/**
	 * 导出“自己”评价的记录
	 */
	public String outputCsv() {

		if (teacherId == null || teacherId.equals("")) {
			teacherId = ((UserVO) ServletActionContext.getRequest().getSession().getAttribute("userVO")).getTeacherId();
		}

		String csvName = "评价考核记录.csv";
		StringBuffer csvContext = new StringBuffer("考核对象部门,考核对象名称,考核规则表,分数\n");
		try {
			ArrayList<ScoreResultVO> srVos = (ArrayList<ScoreResultVO>) srBo.getScoreResultByScorerId(teacherId, 2);
			for (ScoreResultVO srVo : srVos) {
				csvContext.append(srVo.getObjectDeptName() + ",");
				csvContext.append(srVo.getObjectName() + ",");
				csvContext.append(srVo.getScoreRuleTable() + ",");
				csvContext.append(srVo.getScoreResult() + ",");
				csvContext.append("\n");
			}

			HttpServletResponse response = ServletActionContext.getResponse();
			response.setCharacterEncoding("UTF-8");
			// response.setCharacterEncoding("GBK");

			response.setContentType("application/octet-stream");

			csvName = new String(csvName.getBytes(), "ISO8859_1");
			response.setHeader("Content-disposition", "attachment;filename=\"" + csvName + "\"");

			InputStream in = new ByteArrayInputStream(csvContext.toString().getBytes());

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

		} catch (Exception e) {
			logger.error("导出“自己”评价的记录失败", e);
		}

		return null;
	}

	/**
	 * 获取该teacherId代表对象所有被评分结果列表
	 */
	@SuppressWarnings("unchecked")
	public String readCommitOrNot() {
		Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");

		try {

			ArrayList<ScoreResultVO> commitOrNotScoreResultVOs = (ArrayList<ScoreResultVO>) srBo
					.getScoreResultByObjectIdAndScoreRuleId(teacherId, scoreRuleId, 3);

			if (commitOrNotScoreResultVOs != null && commitOrNotScoreResultVOs.size() > 0) {
				request.put("commitOrNotScoreResultVOs", commitOrNotScoreResultVOs);
			} else {
				request.put("commitOrNotScoreResultVOs", null);
			}

			String objectName = commitOrNotScoreResultVOs.get(0).getObjectName();
			String objectDeptName = commitOrNotScoreResultVOs.get(0).getObjectDeptName();
			request.put("SubmittdNum", SubmittdNum);
			request.put("AllResultNum", AllResultNum);
			request.put("objectName", objectName);
			request.put("objectDeptName", objectDeptName);

		} catch (Exception e) {
			logger.error("获取考核3_1表列表失败", e);
		}

		return "readCommitOrNot_success";
	}

	/**
	 * 批量添加评分关系（状态为0的评分结果记录）的方法
	 * 
	 * @return
	 */
	public String scoreResultBatchAdd() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (scorerTypes/* 评价主体类型 */ == null || scorerTypes.length == 0) {
			String[] scorerTypesStr = ServletActionContext.getRequest().getParameterValues("scorerType");
			scorerTypes = new int[scorerTypesStr.length];
			for (int i = 0; i < scorerTypesStr.length; i++) {
				scorerTypes[i] = Integer.parseInt(scorerTypesStr[i]);
			}
		}

		int count = 0;
		try {
			ArrayList<String> objectIds = (ArrayList<String>) userBo.getObjectIdsByObjectType(objectType);

			ArrayList<UserVO> UserVos = new ArrayList<>();

			String[] deptIdsStr = null;
			for (int i = 0; i < scorerTypes.length; i++) {
				int scorerType = scorerTypes[i];
				switch (scorerType) {
				case ExtendCodeConfig.SCORERTYPE_SCHOOL_LEADER:
					UserVos.clear();
					UserVos = (ArrayList<UserVO>) userBo.getLeaderList();
					count += insertOrUpdateScoreResult(objectIds, objectType, UserVos, scorerType, scoreRuleId);
					break;
				case ExtendCodeConfig.SCORERTYPE_DEPT_LEADER:
					UserVos.clear();
					deptIdsStr = ServletActionContext.getRequest().getParameterValues("dept100312");
					for (int j = 0; j < deptIdsStr.length; j++) {
						UserVos.addAll(userBo.getDeptLeader(deptIdsStr[j]));
					}
					count += insertOrUpdateScoreResult(objectIds, objectType, UserVos, scorerType, scoreRuleId);
					break;
				case ExtendCodeConfig.SCORERTYPE_INDEPT_WORKER:
					for (int j = 0; j < objectIds.size(); j++) {
						UserVos.clear();
						UserVos = (ArrayList<UserVO>) userBo.getWorkmateList(objectIds.get(j));
						ArrayList<String> thisObjectId = new ArrayList<>();
						thisObjectId.clear();
						thisObjectId.add(objectIds.get(j));
						count += insertOrUpdateScoreResult(thisObjectId, objectType, UserVos, scorerType, scoreRuleId);
					}
					break;
				case ExtendCodeConfig.SCORERTYPE_DEPT2_LEADER:
					for (int j = 0; j < objectIds.size(); j++) {
						UserVos.clear();
						UserVos = (ArrayList<UserVO>) userBo.getDeptLeaderByUser(objectIds.get(j));
						ArrayList<String> thisObjectId = new ArrayList<>();
						thisObjectId.add(objectIds.get(j));
						count += insertOrUpdateScoreResult(thisObjectId, objectType, UserVos, scorerType, scoreRuleId);
					}
					break;
				case ExtendCodeConfig.SCORERTYPE_REPRESENTATIVE:
					UserVos.clear();
					for (int j = 0; j < 1; j++) {
						UserVos.addAll((ArrayList<UserVO>) userBo.getDelegateList(objectIds.get(j)));
					}
					count += insertOrUpdateScoreResult(objectIds, objectType, UserVos, scorerType, scoreRuleId);
					break;
				case ExtendCodeConfig.SCORERTYPE_CLASSROOM_TEACHER:
					for (int j = 0; j < objectIds.size(); j++) {
						UserVos.clear();
						UserVos = (ArrayList<UserVO>) userBo.getHeadMasterList(objectIds.get(j));
						ArrayList<String> thisObjectId = new ArrayList<>();
						thisObjectId.add(objectIds.get(j));
						count += insertOrUpdateScoreResult(thisObjectId, objectType, UserVos, scorerType, scoreRuleId);
					}
					break;
				case ExtendCodeConfig.SCORERTYPE_GROUP_OBJECT:
					for (int j = 0; j < objectIds.size(); j++) {
						UserVos.clear();
						UserVos = (ArrayList<UserVO>) userBo.getPositionUserList(objectIds.get(j));
						ArrayList<String> thisObjectId = new ArrayList<>();
						thisObjectId.add(objectIds.get(j));
						count += insertOrUpdateScoreResult(thisObjectId, objectType, UserVos, scorerType, scoreRuleId);
					}
					break;
				case ExtendCodeConfig.SCORERTYPE_DEPT2_SELF:
					UserVos.clear();
					deptIdsStr = ServletActionContext.getRequest().getParameterValues("dept100318");
					for (int j = 0; j < deptIdsStr.length; j++) {
						UserVos.add(userBo.getDeptLeaderS(deptIdsStr[j]));
					}
					count += insertOrUpdateScoreResult(objectIds, objectType, UserVos, scorerType, scoreRuleId);
					break;

				// 宜杰追加
				case ExtendCodeConfig.SCORERTYPE_DEPT_ALLWORKER:
					UserVos.clear();
					deptIdsStr = ServletActionContext.getRequest().getParameterValues("dept100319");
					for (int j = 0; j < deptIdsStr.length; j++) {
						UserVos.addAll(userBo.getUsersByDept(deptIdsStr[j]));

					}
					count += insertOrUpdateScoreResult(objectIds, objectType, UserVos, scorerType, scoreRuleId);
					break;

				default:
					break;
				}

			}
		} catch (Exception e) {
			logger.error("创建评分关系失败", e);
		}
		if (count == 0) {
			logger.info("没有插入：" + count);
			session.put("message", "未能创建新评价关系，可能是因为要生成的关系已经存在了。\\n如有其他疑问，请联系系统维护人员");
		} else {
			logger.info("一共插入：" + count);
			session.put("message", "成功创建" + count + "条评价关系！");
		}
		return "createScoreResult_success";
	}

	/**
	 * @param objectIds
	 *            部门id
	 * @param objectType
	 *            部门类型
	 * @param userVos
	 *            用户所有信息
	 * @param scorerType
	 *            评价主体类型
	 * @param scoreRuleId
	 *            评分规则id
	 * @return
	 */
	private int insertOrUpdateScoreResult(ArrayList<String> objectIds, int objectType, ArrayList<UserVO> userVos,
			int scorerType, int scoreRuleId) {
		int count = 0;
		ArrayList<ScoreResultVO> batch = new ArrayList<>();
		for (int j = 0; j < objectIds.size(); j++) {
			String objectId = objectIds.get(j);
			for (int k = 0; k < userVos.size(); k++) {
				String scorerId = userVos.get(k).getTeacherId();

				ScoreResultVO srVo = new ScoreResultVO();
				srVo.setObjectId(objectId);
				srVo.setObjectType(objectType);
				srVo.setScorerId(scorerId);
				srVo.setScorerType(scorerType);
				srVo.setScoreRuleId(scoreRuleId);
				srVo.setStatus(0);
				srVo.setScoreResult(0);

				try {
					ScoreResultVO isExist = srBo.getScoreResultByObjectIdAndScorerId(objectId, scorerId, scoreRuleId);
					if (isExist.getId() == 0 && !objectId.equals(scorerId)) {
						batch.add(srVo);
					} else {
						if (!objectId.equals(scorerId)) {
						}
						// srVo.setId(isExist.getId());
						// srBo.updateScoreResult(srVo);
					}
					if (batch.size() == 100) {
						count += srBo.batchInsertScoreResult(batch);
						batch.clear();
					}
					if (k == userVos.size() - 1) {
						count += srBo.batchInsertScoreResult(batch);
						batch.clear();
					}
				} catch (Exception e) {
					logger.error("批量生成评分关系失败:", e);
				}
			}
		}
		return count;
	}

	/**
	 * “保存”或“提交”一个已经填写结束的评分结果表
	 */
	public String saveAndcommit() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();

		// 获取评分分值
		String[] fenzhiStr = request.getParameterValues("fz");
		int[] fenzhi = new int[fenzhiStr.length];
		for (int i = 0; i < fenzhi.length; i++) {
			fenzhi[i] = Integer.parseInt(fenzhiStr[i]);
		}

		// 获取评分细则
		String[] detailIdStr = request.getParameterValues("drtailId");
		int[] detailId = new int[detailIdStr.length];
		for (int i = 0; i < detailId.length; i++) {
			detailId[i] = Integer.parseInt(detailIdStr[i]);
		}

		// 获取父细则（二级）
		String[] LV2IdStr = request.getParameterValues("LV2Id");
		int[] LV2Id = null;
		if (LV2IdStr != null) {
			LV2Id = new int[LV2IdStr.length];
			for (int i = 0; i < LV2IdStr.length; i++) {
				LV2Id[i] = Integer.parseInt(LV2IdStr[i]);
			}
		}

		// 获取父细则（一级）
		String[] LV1IdStr = request.getParameterValues("LV1Id");
		int[] LV1Id = new int[LV1IdStr.length];
		for (int i = 0; i < LV1IdStr.length; i++) {
			LV1Id[i] = Integer.parseInt(LV1IdStr[i]);
		}

		try {
			int count = 0;
			double scoreResult = 0;
			int nowLV1Id = LV1Id[0];
			int nowLV1Score = 0;
			int nowLV2Id = 0;
			int nowLV2Score = 0;
			if (LV2IdStr != null) {
				nowLV2Id = LV2Id[0];
			}
			for (int i = 0; i < fenzhi.length; i++) {

				if (LV2IdStr != null) {
					if (nowLV2Id == LV2Id[i]) {
						nowLV2Score += fenzhi[i];
					} else {
						saveAndcommitDetail(scoreResultId, nowLV2Score, nowLV2Id);// 保存二级细则分值
						nowLV2Id = LV2Id[i];
						nowLV2Score = 0;
						nowLV2Score += fenzhi[i];
					}
				}

				if (nowLV1Id == LV1Id[i]) {
					nowLV1Score += fenzhi[i];
				} else {
					saveAndcommitDetail(scoreResultId, nowLV1Score, nowLV1Id);// 保存一级细则分值
					nowLV1Id = LV1Id[i];
					nowLV1Score = 0;
					nowLV1Score += fenzhi[i];
				}

				count += saveAndcommitDetail(scoreResultId, fenzhi[i], detailId[i]);// 保存细则分值
				scoreResult += fenzhi[i];
			}
			if (LV2IdStr != null) {
				saveAndcommitDetail(scoreResultId, nowLV2Score, nowLV2Id);// 保存最后一个二级细则分值
			}
			saveAndcommitDetail(scoreResultId, nowLV1Score, nowLV1Id);// 保存最后一个一级细则分值

			boolean isNew = true;
			if (srBo.getScoreResultByScoreResultId(scoreResultId).getStatus() != 0) {
				isNew = false;
			}

			String commitDate = "未提交";
			if (changeTo == 2) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
				commitDate = format.format(System.currentTimeMillis());
			}

			int num = srBo.changeScoreResultStatusByID(scoreResultId, changeTo, scoreResult, commitDate);
			if (num > 0 && count == fenzhi.length) {
				if (changeTo == 1) {
					if (isNew) {
						session.put("message", "保存成功！\\n将返回未评价列表");
						return "new_save_success";
					}
					session.put("message", "保存成功！\\n将返回已评价列表");
					return "save_success";
				} else if (changeTo == 2) {
					if (isNew) {
						session.put("message", "提交成功！\\n将返回未评价列表");
						return "new_commit_success";
					}
					session.put("message", "提交成功！\\n将返回已评价列表");
					return "commit_success";
				}
			} else {
				return "saveAndcommit_failed";
			}
		} catch (Exception e) {
			logger.error("保存评分结果失败", e);
		}
		return "saveAndcommit_failed";
	}

	// 对评分结果明细的操作：
	/**
	 * 插入明细项分数 或 修改以存在的明细项分数
	 * 
	 * @param scoreResultId
	 *            评价结果id
	 * @param score
	 *            本明细的“新”分数
	 * @param detailId
	 *            本明细id
	 * @return 受影响记录条数
	 */
	private int saveAndcommitDetail(int scoreResultId, int score, int detailId) throws Exception {
		ScoreResultVO srVo = srBo.getScoreResultByScoreResultId(scoreResultId);

		ScoreResultDetailVO srdVo = new ScoreResultDetailVO();

		srdVo.setScoreId(scoreResultId);
		srdVo.setDetailId(detailId);
		srdVo.setScore(score);

		if (srVo.getStatus() == 0) {
			return srdBo.insertScoreResultDetail(srdVo);
		} else if (srVo.getStatus() == 1) {
			if (srdBo.isExistScore(srdVo)) {
				return srdBo.updateScoreResultDetail(srdVo);// 已经存在就修改
			} else {
				return srdBo.insertScoreResultDetail(srdVo);// 不存在就插入
			}
		}
		return 0;
	}

	/**
	 * @author 潘宜杰 1007
	 * @param scoreResultVO
	 * @see 改变在用户评价页面中的正副职显示
	 */
	public void changeScoreRuleTable(ArrayList<ScoreResultVO> scoreResultVO) {
		for (int i = 0; i < scoreResultVO.size(); i++) {
			ScoreResultVO srVO = scoreResultVO.get(i);
			try {
				UserVO uVO = userBo.getUserByTeacherId(srVO.getObjectId());
				if (uVO.getUserCharacter() == 100213 || uVO.getUserCharacter() == 100215) {
					ScoreRuleBO sRuleBo = new ScoreRuleBO();
					ScoreRuleVO sRuleVo = sRuleBo.getScoreRuleById(1009);
					srVO.setScoreRuleTable(sRuleVo.getRuleName());
				}
			} catch (Exception e) {
				logger.error("改变在用户评价页面中的正副职显示失败", e);
			}
		}
	}

	// get和set

	public int getReadStatus() {
		return readStatus;
	}

	public int getSubmittdNum() {
		return SubmittdNum;
	}

	public void setSubmittdNum(int submittdNum) {
		SubmittdNum = submittdNum;
	}

	public int getAllResultNum() {
		return AllResultNum;
	}

	public void setAllResultNum(int allResultNum) {
		AllResultNum = allResultNum;
	}

	public void setReadStatus(int readStatus) {
		this.readStatus = readStatus;
	}

	public String getTeacherId() {
		return teacherId;
	}

	public void setTeacherId(String teacherId) {
		this.teacherId = teacherId;
	}

	public int getScoreRuleId() {
		return scoreRuleId;
	}

	public void setScoreRuleId(int scoreRuleId) {
		this.scoreRuleId = scoreRuleId;
	}

	public int getObjectType() {
		return objectType;
	}

	public void setObjectType(int objectType) {
		this.objectType = objectType;
	}

	public int getScoreResultId() {
		return scoreResultId;
	}

	public void setScoreResultId(int scoreResultId) {
		this.scoreResultId = scoreResultId;
	}

	public int getChangeTo() {
		return changeTo;
	}

	public void setChangeTo(int changeTo) {
		this.changeTo = changeTo;
	}

}
