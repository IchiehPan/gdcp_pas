package com.gdcp.pas.score.dao;

import java.util.List;

import com.gdcp.pas.score.vo.ScoreResultDetailVO;

/**
 * @author 陈伟镇
 * @version 0329-01:05
 * @see 对评分结果明显表操作方法接口
 */
public interface ScoreResultDetailDAO {

	/**
	 * @see 获取某评分结果的所有明细记录
	 * @param scoreResultId
	 *            评分结果的id
	 * @return 该评分结果的所有明细记录
	 */
	public List<ScoreResultDetailVO> getScoreResultDetailByScoreResultId(int scoreResultId) throws Exception;

	/**
	 * @see 获取某评分结果的某条规则的明细记录
	 * @param scoreResultId
	 *            评分结果的id
	 * @param scoreRuleDetailId
	 *            2级规则的id
	 * @return 该评分结果的所有明细记录
	 */
	public ScoreResultDetailVO getScoreResultDetailByScoreResultIdAndScoreRuleDetailId(int scoreResultId,
			int scoreRuleDetailId) throws Exception;

	/**
	 * @see 向数据库插入一条评分结果明细记录
	 * @param srdVo
	 *            要插入的评分结果明细记录
	 * @return 返回进行了操作的行数
	 */
	public int insertScoreResultDetail(ScoreResultDetailVO srdVo) throws Exception;

	/**
	 * @see 向数据库修改一条评分结果明细记录
	 * @param srdVo
	 *            要插入的评分结果明细记录
	 * @return 返回进行了操作的行数
	 */
	public int updateScoreResultDetail(ScoreResultDetailVO srdVo) throws Exception;

	/**
	 * 判断分数记录是否已经存在
	 * 
	 * @param averageScoreVo
	 * @return 是否
	 */
	public boolean isExistScore(ScoreResultDetailVO scoreResultDetailVO) throws Exception;

	/**
	 * 获取 某些评价结果 的 指定一级细则的平均分数
	 * 
	 * @param scoreResultIdsStr
	 * @param srdLv1VoId
	 * @return
	 */
	public double getAverageDetailScoreByScorerIdsStrAndSrdLv1VoId(String scoreResultIdsStr, int srdLv1VoId)
			throws Exception;
}
