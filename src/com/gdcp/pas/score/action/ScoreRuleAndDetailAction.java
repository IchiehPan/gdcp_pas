package com.gdcp.pas.score.action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import org.apache.log4j.Logger;

import com.gdcp.common.extend.ExtendCodeUtil;
import com.gdcp.pas.manage.bo.UserBO;
import com.gdcp.pas.manage.vo.UserVO;
import com.gdcp.pas.score.bo.ScoreResultBO;
import com.gdcp.pas.score.bo.ScoreRuleBO;
import com.gdcp.pas.score.bo.ScoreRuleDetailBO;
import com.gdcp.pas.score.vo.ScoreResultVO;
import com.gdcp.pas.score.vo.ScoreRuleDetailVO;
import com.gdcp.pas.score.vo.ScoreRuleVO;
import com.opensymphony.xwork2.ActionContext;

/**
 * ��Ҫ�������ɡ����۱���δ���ġ�����ġ��ύ�ģ� �����˶�ȡ�����������ɱ��֮�⣬��Ҫ��ȡ��������������ɱ������ݣ�
 * 
 * @author ��ΰ��
 * @version 0327-21:14
 * @see �����ֹ��򣬼������ֹ����ӱ��Action
 */
public class ScoreRuleAndDetailAction {
	private static Logger logger = Logger.getLogger(ScoreRuleAndDetailAction.class);
	private ScoreRuleBO sRuletBo = new ScoreRuleBO();
	private ScoreRuleDetailBO sRuleDetailBo = new ScoreRuleDetailBO();

	private ScoreResultBO sResultBo = new ScoreResultBO();
	private UserBO userBo = new UserBO();

	// ����:scoreResultId(�ɸ������id��ȡһ�����ֽ����¼)
	private int scoreResultId;

	// ���ˣ�objectId(teacherId:�ɸ������id��ȡһϵ�����۽��)
	private String objectId;

	private int userCharacter;

	/**
	 * ���ɡ����ۿ��˱���Ψһ���
	 */
	@SuppressWarnings("unchecked")
	public String showDetailTable() {
		try {
			ScoreResultVO scoreResultVo = sResultBo.getScoreResultByScoreResultId(scoreResultId);
			setObjectName(scoreResultVo);
			int scoreRuleId = scoreResultVo.getScoreRuleId();
			if (scoreRuleId == 1001) {
				if (userCharacter == 100213 || userCharacter == 100215) {
					scoreRuleId = 1009;
					scoreResultVo.setScoreRuleId(1009);
				}
			}

			// ��ȡ������
			ScoreRuleVO scoreRuleVo = sRuletBo.getScoreRuleById(scoreRuleId);

			Map<String, Object> request = (Map<String, Object>) ActionContext.getContext().get("request");
			try {

				if (scoreRuleVo != null) {
					request.put("scoreRuleVo", scoreRuleVo);

					// ��ȡ����
					ArrayList<ScoreRuleDetailVO> srdLV1s = (ArrayList<ScoreRuleDetailVO>) sRuleDetailBo
							.getScoreRuleAndChildsByResultIdAndParentId(scoreResultVo, 0);
					request.put("scoreRuleDrtailLV1s", srdLV1s);

					// �ٰѱ����Ӧ��scoreResultVO����session
					request.put("scoreResultVo", scoreResultVo);

					String scoreTypeKet = scoreResultVo.getScorerType() + "";
					String scorerTypeName = ExtendCodeUtil.getInstance().getValue("scorerType", scoreTypeKet);
					request.put("scorerTypeName", scorerTypeName);

				} else {
					request.put("scoreRule", null);
					request.put("tableName", "�Բ���!\n�ý̹�û�б����ˣ������޷���ʾ���˽��");
					request.put("scoreRuleDrtailLV1s", null);
					request.put("scoreResultVo", null);
					request.put("heji", 0);// �ܷ�
				}

			} catch (SQLException e) {
				logger.error("SQLException", e);
			} catch (Exception e) {
				logger.error("Exception", e);
			}

			if (scoreRuleVo.getDetailLevel() == 2) {
				return "showTableLV2_success";
			} else if (scoreRuleVo.getDetailLevel() == 3) {
				return "showTableLV3_success";
			}
		} catch (Exception e) {
			logger.error("���ɡ����ۿ��˱���Ψһ���ʧ��", e);
		}

		return null;
	}

	/**
	 * Ϊһ��scoreResultVo���ҳ����Ҫ����ʾ��Ϣ����������
	 * 
	 * @param һ��ȱ����Ҫ��ʾ��Ϣ��scoreResultVo
	 */
	private void setObjectName(ScoreResultVO scoreResultVo) throws Exception {
		UserVO userVo = userBo.getUserByTeacherId(scoreResultVo.getObjectId());
		userCharacter = userVo.getUserCharacter();
		scoreResultVo.setObjectName(userVo.getUserName());
	}

	// �Զ���������get��set������
	public int getScoreResultId() {
		return scoreResultId;
	}

	public void setScoreResultId(int scoreResultId) {
		this.scoreResultId = scoreResultId;
	}

	public String getObjectId() {
		return objectId;
	}

	public void setObjectId(String objectId) {
		this.objectId = objectId;
	}

}
