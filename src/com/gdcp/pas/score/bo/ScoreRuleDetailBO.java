package com.gdcp.pas.score.bo;

import java.util.List;

import com.gdcp.pas.score.dao.ScoreRuleDetailDAO;
import com.gdcp.pas.score.dao.impl.ScoreRuleDetailDAOImpl;
import com.gdcp.pas.score.vo.ScoreResultVO;
import com.gdcp.pas.score.vo.ScoreRuleDetailVO;

/**
 * @author ��ΰ��
 *
 */
public class ScoreRuleDetailBO {

	ScoreRuleDetailDAO srdDAO = new ScoreRuleDetailDAOImpl();

	/**
	 * @see �����ֹ����ӱ��в���ָ��id���ӹ������µ�childsΪ�գ�
	 * @param scoreRuleId
	 * @param parentId
	 * @return ���ع���ʵ��������
	 */
	public List<ScoreRuleDetailVO> getScoreRuleByRuleIdAndParentId(int scoreRuleId, int parentId) throws Exception {
		return srdDAO.getScoreRuleByRuleIdAndParentId(scoreRuleId, parentId);
	}

	/**
	 * ��ȡָ�� ���۽�����˱� �ġ��ӹ��򡱼��䡰�ӹ���(ֱ�����굽��ײ㣬�����ɼ�)
	 * 
	 * @param scoreResultVo
	 *            ָ�����۽��
	 * @param parentId
	 * @return
	 */
	public List<ScoreRuleDetailVO> getScoreRuleAndChildsByResultIdAndParentId(ScoreResultVO scoreResultVo, int parentId)
			throws Exception {
		return srdDAO.getScoreRuleAndChildsByResultIdAndParentId(scoreResultVo, parentId);
	}

}
