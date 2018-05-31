package com.gdcp.pas.score.bo;

import java.util.List;

import com.gdcp.pas.score.dao.ScoreResultDetailDAO;
import com.gdcp.pas.score.dao.impl.ScoreResultDetailDAOImpl;
import com.gdcp.pas.score.vo.ScoreResultDetailVO;

public class ScoreResultDetailBO {
	ScoreResultDetailDAO srdd = new ScoreResultDetailDAOImpl();

	/**
	 * @see ��ȡĳ���ֽ������ϸ��¼
	 * @param scoreResultId
	 *            ���ֽ����id
	 * @return �����ֽ����������ϸ��¼
	 */
	public List<ScoreResultDetailVO> getScoreResultDetailByScoreResultId(int scoreResultId) throws Exception {
		return srdd.getScoreResultDetailByScoreResultId(scoreResultId);
	}

	/**
	 * @see ��ȡĳ���ֽ����ĳ���������ϸ��¼
	 * @param scoreResultId
	 *            ���ֽ����id
	 * @param scoreRuleDetailId
	 *            ĳ�������id
	 * @return �����ֽ��ĳ���������ϸ��¼
	 */
	public ScoreResultDetailVO getScoreResultDetailByScoreResultIdAndScoreRuleDetailId(int scoreResultId,
			int scoreRuleDetailId) throws Exception {
		return srdd.getScoreResultDetailByScoreResultIdAndScoreRuleDetailId(scoreResultId, scoreRuleDetailId);
	}

	/**
	 * @see �����ݿ����һ�����ֽ����ϸ��¼
	 * @param srdVo
	 *            Ҫ��������ֽ����ϸ��¼
	 * @return ���ؽ����˲���������
	 */
	public int insertScoreResultDetail(ScoreResultDetailVO srdVo) throws Exception {
		return srdd.insertScoreResultDetail(srdVo);
	}

	/**
	 * @see �����ݿ��޸�һ�����ֽ����ϸ��¼
	 * @param srdVo
	 *            Ҫ��������ֽ����ϸ��¼
	 * @return ���ؽ����˲���������
	 */
	public int updateScoreResultDetail(ScoreResultDetailVO srdVo) throws Exception {
		return srdd.updateScoreResultDetail(srdVo);
	}

	/**
	 * �жϷ�����¼�Ƿ��Ѿ�����
	 * 
	 * @param averageScoreVo
	 * @return �Ƿ�
	 */
	public boolean isExistScore(ScoreResultDetailVO scoreResultDetailVO) throws Exception {
		return srdd.isExistScore(scoreResultDetailVO);
	}

	/**
	 * ��ȡ ĳЩ���۽�� �� ָ��һ��ϸ���ƽ������
	 * 
	 * @param scoreResultIdsStr
	 * @param srdLv1VoId
	 * @return
	 */
	public double getAverageDetailScoreByScorerIdsStrAndSrdLv1VoId(String scoreResultIdsStr, int srdLv1VoId)
			throws Exception {
		return srdd.getAverageDetailScoreByScorerIdsStrAndSrdLv1VoId(scoreResultIdsStr, srdLv1VoId);
	}
}
