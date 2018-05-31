package com.gdcp.pas.score.dao;

import com.gdcp.pas.score.vo.ScoreRuleVO;

/**
 * @author ��ΰ��
 * @see �����ֹ������в����ķ����ӿ�
 * @version 0327-21:00
 */
public interface ScoreRuleDAO {

	/**
	 * @see �����ֹ�����в���ָ���� (ָ������Ϊ��)
	 * 
	 * @param scoreRuleById
	 *            ���ֹ�����id
	 * @return һ�����ֹ����Ķ���
	 */
	public ScoreRuleVO getScoreRuleById(int scoreRuleId) throws Exception;

	/**
	 * @see �����ֹ�����в���ָ���� (ָ�����鲻Ϊ��)
	 * 
	 * @param scoreRuleById
	 *            ���ֹ�����id
	 * @return һ�����ֹ����Ķ���
	 */
	public ScoreRuleVO getScoreRuleTableById(int scoreRuleId) throws Exception;

}
