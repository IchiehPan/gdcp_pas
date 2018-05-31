package com.gdcp.pas.score.bo;

import java.util.List;

import com.gdcp.pas.score.dao.ScoreResultDetailDAO;
import com.gdcp.pas.score.dao.impl.ScoreResultDetailDAOImpl;
import com.gdcp.pas.score.vo.ScoreResultDetailVO;

public class ScoreResultDetailBO {
	ScoreResultDetailDAO srdd = new ScoreResultDetailDAOImpl();

	/**
	 * @see 获取某评分结果的明细记录
	 * @param scoreResultId
	 *            评分结果的id
	 * @return 该评分结果的所有明细记录
	 */
	public List<ScoreResultDetailVO> getScoreResultDetailByScoreResultId(int scoreResultId) throws Exception {
		return srdd.getScoreResultDetailByScoreResultId(scoreResultId);
	}

	/**
	 * @see 获取某评分结果中某条规则的明细记录
	 * @param scoreResultId
	 *            评分结果的id
	 * @param scoreRuleDetailId
	 *            某条规则的id
	 * @return 该评分结果某条规则的明细记录
	 */
	public ScoreResultDetailVO getScoreResultDetailByScoreResultIdAndScoreRuleDetailId(int scoreResultId,
			int scoreRuleDetailId) throws Exception {
		return srdd.getScoreResultDetailByScoreResultIdAndScoreRuleDetailId(scoreResultId, scoreRuleDetailId);
	}

	/**
	 * @see 向数据库插入一条评分结果明细记录
	 * @param srdVo
	 *            要插入的评分结果明细记录
	 * @return 返回进行了操作的行数
	 */
	public int insertScoreResultDetail(ScoreResultDetailVO srdVo) throws Exception {
		return srdd.insertScoreResultDetail(srdVo);
	}

	/**
	 * @see 向数据库修改一条评分结果明细记录
	 * @param srdVo
	 *            要插入的评分结果明细记录
	 * @return 返回进行了操作的行数
	 */
	public int updateScoreResultDetail(ScoreResultDetailVO srdVo) throws Exception {
		return srdd.updateScoreResultDetail(srdVo);
	}

	/**
	 * 判断分数记录是否已经存在
	 * 
	 * @param averageScoreVo
	 * @return 是否
	 */
	public boolean isExistScore(ScoreResultDetailVO scoreResultDetailVO) throws Exception {
		return srdd.isExistScore(scoreResultDetailVO);
	}

	/**
	 * 获取 某些评价结果 的 指定一级细则的平均分数
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
