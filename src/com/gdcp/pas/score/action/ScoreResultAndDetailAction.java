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
 * ��Ҫ���������ɡ����۱��б� ��Ҫ�Ǵ������۽�����������޸����۽��״̬�����۽����ϸ��
 * 
 * @author ��ΰ��
 * @version 0329-23:43
 * @�������ֽ������������ϸ����Action
 */
public class ScoreResultAndDetailAction {
	private static Logger logger = Logger.getLogger(ScoreResultAndDetailAction.class);

	// BO��
	private ScoreResultBO srBo = new ScoreResultBO();
	private ScoreResultDetailBO srdBo = new ScoreResultDetailBO();

	private UserBO userBo = new UserBO();

	// �б���ʾ��Ҫ
	private int readStatus;
	private String teacherId;
	private int scoreRuleId;
	// ��ʾ������Ҫ
	private int SubmittdNum;
	private int AllResultNum;

	// �����ֹ�ϵ��Ҫ��
	private int objectType;
	private int[] scorerTypes;// ���ֵ����ͨ����ܻ�ã�Ҫ�ڷ�������ͨ��request���
	// private int scoreRuleId;�ϱ��Ѿ����ˣ�����������ָ��Ҫʹ��scoreRuleId

	// ���ֱ�����Ҫ��
	private int scoreResultId; // ��jsp�д�${session.scoreResultVo.id}��ȡ
	private int changeTo; // ��jsp�д�<int type="hidden" name="changeTo">��ȡ

	// �����ֽ���Ĳ�����
	/**
	 * @see ��ȡ���û��ĸ���״̬�����ֽ���б�
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
			logger.error("��ȡ���ֱ��б�ʧ��", e);
		}
		return "read_failed";
	}

	/**
	 * @see ��ȡ���û��������ֽ���б�
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
			logger.error("��ȡ���ֱ��б�ʧ��", e);
		}
		return "read_failed";
	}

	/**
	 * �������Լ������۵ļ�¼
	 */
	public String outputCsv() {

		if (teacherId == null || teacherId.equals("")) {
			teacherId = ((UserVO) ServletActionContext.getRequest().getSession().getAttribute("userVO")).getTeacherId();
		}

		String csvName = "���ۿ��˼�¼.csv";
		StringBuffer csvContext = new StringBuffer("���˶�����,���˶�������,���˹����,����\n");
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
			logger.error("�������Լ������۵ļ�¼ʧ��", e);
		}

		return null;
	}

	/**
	 * ��ȡ��teacherId����������б����ֽ���б�
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
			logger.error("��ȡ����3_1���б�ʧ��", e);
		}

		return "readCommitOrNot_success";
	}

	/**
	 * ����������ֹ�ϵ��״̬Ϊ0�����ֽ����¼���ķ���
	 * 
	 * @return
	 */
	public String scoreResultBatchAdd() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		if (scorerTypes/* ������������ */ == null || scorerTypes.length == 0) {
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

				// �˽�׷��
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
			logger.error("�������ֹ�ϵʧ��", e);
		}
		if (count == 0) {
			logger.info("û�в��룺" + count);
			session.put("message", "δ�ܴ��������۹�ϵ����������ΪҪ���ɵĹ�ϵ�Ѿ������ˡ�\\n�����������ʣ�����ϵϵͳά����Ա");
		} else {
			logger.info("һ�����룺" + count);
			session.put("message", "�ɹ�����" + count + "�����۹�ϵ��");
		}
		return "createScoreResult_success";
	}

	/**
	 * @param objectIds
	 *            ����id
	 * @param objectType
	 *            ��������
	 * @param userVos
	 *            �û�������Ϣ
	 * @param scorerType
	 *            ������������
	 * @param scoreRuleId
	 *            ���ֹ���id
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
					logger.error("�����������ֹ�ϵʧ��:", e);
				}
			}
		}
		return count;
	}

	/**
	 * �����桱���ύ��һ���Ѿ���д���������ֽ����
	 */
	public String saveAndcommit() {
		Map<String, Object> session = ActionContext.getContext().getSession();
		HttpServletRequest request = ServletActionContext.getRequest();

		// ��ȡ���ַ�ֵ
		String[] fenzhiStr = request.getParameterValues("fz");
		int[] fenzhi = new int[fenzhiStr.length];
		for (int i = 0; i < fenzhi.length; i++) {
			fenzhi[i] = Integer.parseInt(fenzhiStr[i]);
		}

		// ��ȡ����ϸ��
		String[] detailIdStr = request.getParameterValues("drtailId");
		int[] detailId = new int[detailIdStr.length];
		for (int i = 0; i < detailId.length; i++) {
			detailId[i] = Integer.parseInt(detailIdStr[i]);
		}

		// ��ȡ��ϸ�򣨶�����
		String[] LV2IdStr = request.getParameterValues("LV2Id");
		int[] LV2Id = null;
		if (LV2IdStr != null) {
			LV2Id = new int[LV2IdStr.length];
			for (int i = 0; i < LV2IdStr.length; i++) {
				LV2Id[i] = Integer.parseInt(LV2IdStr[i]);
			}
		}

		// ��ȡ��ϸ��һ����
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
						saveAndcommitDetail(scoreResultId, nowLV2Score, nowLV2Id);// �������ϸ���ֵ
						nowLV2Id = LV2Id[i];
						nowLV2Score = 0;
						nowLV2Score += fenzhi[i];
					}
				}

				if (nowLV1Id == LV1Id[i]) {
					nowLV1Score += fenzhi[i];
				} else {
					saveAndcommitDetail(scoreResultId, nowLV1Score, nowLV1Id);// ����һ��ϸ���ֵ
					nowLV1Id = LV1Id[i];
					nowLV1Score = 0;
					nowLV1Score += fenzhi[i];
				}

				count += saveAndcommitDetail(scoreResultId, fenzhi[i], detailId[i]);// ����ϸ���ֵ
				scoreResult += fenzhi[i];
			}
			if (LV2IdStr != null) {
				saveAndcommitDetail(scoreResultId, nowLV2Score, nowLV2Id);// �������һ������ϸ���ֵ
			}
			saveAndcommitDetail(scoreResultId, nowLV1Score, nowLV1Id);// �������һ��һ��ϸ���ֵ

			boolean isNew = true;
			if (srBo.getScoreResultByScoreResultId(scoreResultId).getStatus() != 0) {
				isNew = false;
			}

			String commitDate = "δ�ύ";
			if (changeTo == 2) {
				SimpleDateFormat format = new SimpleDateFormat("yyyy��MM��dd��");
				commitDate = format.format(System.currentTimeMillis());
			}

			int num = srBo.changeScoreResultStatusByID(scoreResultId, changeTo, scoreResult, commitDate);
			if (num > 0 && count == fenzhi.length) {
				if (changeTo == 1) {
					if (isNew) {
						session.put("message", "����ɹ���\\n������δ�����б�");
						return "new_save_success";
					}
					session.put("message", "����ɹ���\\n�������������б�");
					return "save_success";
				} else if (changeTo == 2) {
					if (isNew) {
						session.put("message", "�ύ�ɹ���\\n������δ�����б�");
						return "new_commit_success";
					}
					session.put("message", "�ύ�ɹ���\\n�������������б�");
					return "commit_success";
				}
			} else {
				return "saveAndcommit_failed";
			}
		} catch (Exception e) {
			logger.error("�������ֽ��ʧ��", e);
		}
		return "saveAndcommit_failed";
	}

	// �����ֽ����ϸ�Ĳ�����
	/**
	 * ������ϸ����� �� �޸��Դ��ڵ���ϸ�����
	 * 
	 * @param scoreResultId
	 *            ���۽��id
	 * @param score
	 *            ����ϸ�ġ��¡�����
	 * @param detailId
	 *            ����ϸid
	 * @return ��Ӱ���¼����
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
				return srdBo.updateScoreResultDetail(srdVo);// �Ѿ����ھ��޸�
			} else {
				return srdBo.insertScoreResultDetail(srdVo);// �����ھͲ���
			}
		}
		return 0;
	}

	/**
	 * @author ���˽� 1007
	 * @param scoreResultVO
	 * @see �ı����û�����ҳ���е�����ְ��ʾ
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
				logger.error("�ı����û�����ҳ���е�����ְ��ʾʧ��", e);
			}
		}
	}

	// get��set

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
