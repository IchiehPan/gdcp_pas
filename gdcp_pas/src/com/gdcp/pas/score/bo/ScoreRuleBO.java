package com.gdcp.pas.score.bo;

import com.gdcp.pas.score.dao.impl.ScoreRuleDAOImpl;
import com.gdcp.pas.score.vo.ScoreRuleVO;

/**
 * @author ��ΰ��
 * 
 */
public class ScoreRuleBO {

	ScoreRuleDAOImpl srdi = new ScoreRuleDAOImpl();

	/**
	 * @see �����ֹ�����в���ָ����(ָ������Ϊ��)
	 * 
	 * @param scoreRuleById
	 *            ���ֹ�����id
	 * @return һ�����ֹ����Ķ���
	 */
	public ScoreRuleVO getScoreRuleById(int scoreRuleId) throws Exception {
		return srdi.getScoreRuleById(scoreRuleId);
	}

	/**
	 * @see �����ֹ�����в���ָ���� (ָ�����鲻Ϊ��)
	 * 
	 * @param scoreRuleById
	 *            ���ֹ�����id
	 * @return һ�����ֹ����Ķ���
	 */
	public ScoreRuleVO getScoreRuleTableById(int scoreRuleId) throws Exception {
		return srdi.getScoreRuleTableById(scoreRuleId);
	}

}
