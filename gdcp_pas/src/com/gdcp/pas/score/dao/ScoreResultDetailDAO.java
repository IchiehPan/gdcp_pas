package com.gdcp.pas.score.dao;

import java.util.List;

import com.gdcp.pas.score.vo.ScoreResultDetailVO;

/**
 * @author ��ΰ��
 * @version 0329-01:05
 * @see �����ֽ�����Ա���������ӿ�
 */
public interface ScoreResultDetailDAO {

	/**
	 * @see ��ȡĳ���ֽ����������ϸ��¼
	 * @param scoreResultId
	 *            ���ֽ����id
	 * @return �����ֽ����������ϸ��¼
	 */
	public List<ScoreResultDetailVO> getScoreResultDetailByScoreResultId(int scoreResultId) throws Exception;

	/**
	 * @see ��ȡĳ���ֽ����ĳ���������ϸ��¼
	 * @param scoreResultId
	 *            ���ֽ����id
	 * @param scoreRuleDetailId
	 *            2�������id
	 * @return �����ֽ����������ϸ��¼
	 */
	public ScoreResultDetailVO getScoreResultDetailByScoreResultIdAndScoreRuleDetailId(int scoreResultId,
			int scoreRuleDetailId) throws Exception;

	/**
	 * @see �����ݿ����һ�����ֽ����ϸ��¼
	 * @param srdVo
	 *            Ҫ��������ֽ����ϸ��¼
	 * @return ���ؽ����˲���������
	 */
	public int insertScoreResultDetail(ScoreResultDetailVO srdVo) throws Exception;

	/**
	 * @see �����ݿ��޸�һ�����ֽ����ϸ��¼
	 * @param srdVo
	 *            Ҫ��������ֽ����ϸ��¼
	 * @return ���ؽ����˲���������
	 */
	public int updateScoreResultDetail(ScoreResultDetailVO srdVo) throws Exception;

	/**
	 * �жϷ�����¼�Ƿ��Ѿ�����
	 * 
	 * @param averageScoreVo
	 * @return �Ƿ�
	 */
	public boolean isExistScore(ScoreResultDetailVO scoreResultDetailVO) throws Exception;

	/**
	 * ��ȡ ĳЩ���۽�� �� ָ��һ��ϸ���ƽ������
	 * 
	 * @param scoreResultIdsStr
	 * @param srdLv1VoId
	 * @return
	 */
	public double getAverageDetailScoreByScorerIdsStrAndSrdLv1VoId(String scoreResultIdsStr, int srdLv1VoId)
			throws Exception;
}
