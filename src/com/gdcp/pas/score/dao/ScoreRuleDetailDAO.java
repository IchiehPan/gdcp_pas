package com.gdcp.pas.score.dao;

import java.util.List;

import com.gdcp.pas.score.vo.ScoreResultVO;
import com.gdcp.pas.score.vo.ScoreRuleDetailVO;

/**
 * @author ��ΰ��
 * @see �����ֹ����ӱ���в����ķ����ӿ�
 * @version 0327-21:00
 */
public interface ScoreRuleDetailDAO {

	/**
	 * @see �����ֹ����ӱ��в���ָ��id���ӹ������µ�childsΪ�գ�
	 * @param scoreRuleId
	 * @param parentId
	 * @return ���ع���ʵ��������
	 */
	public List<ScoreRuleDetailVO> getScoreRuleByRuleIdAndParentId(int scoreRuleId, int parentId) throws Exception;

	/**
	 * @see �����ֹ����ӱ��в���ָ��id�ġ��ӹ��򡱼��䡰�ӹ���(ֱ�����굽��ײ�)
	 * @param scoreRuleId
	 * @param parentId
	 * @return ���ع���ʵ��������
	 */
	public List<ScoreRuleDetailVO> getScoreRuleAndChildsByResultIdAndParentId(ScoreResultVO scoreResultVo, int parentId)
			throws Exception;

}
