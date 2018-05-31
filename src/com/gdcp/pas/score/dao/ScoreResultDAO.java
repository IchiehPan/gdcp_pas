package com.gdcp.pas.score.dao;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.UserVO;
import com.gdcp.pas.score.vo.ScoreResultVO;

/**
 * @author 陈伟镇 0329-00:47
 * @version 0402-14:07
 * @see 对评分结果主体表操作方法接口
 */
public interface ScoreResultDAO {

	/**
	 * @see 获取指定评价结果
	 * @param scoreResultId
	 *            评价结果自身的id
	 * @return 指定评价结果Vo
	 */
	public ScoreResultVO getScoreResultByScoreResultId(int scoreResultId) throws Exception;

	/**
	 * 获取某条指定（指定对象、主体、规则）的评价结果
	 * 
	 * @param objectId
	 * @param scorerId
	 * @param scoreReuleId
	 */
	public ScoreResultVO getScoreResultByObjectIdAndScorerId(String objectId, String scorerId, int scoreReuleId)
			throws Exception;

	/**
	 * @see 分页地 获取某用户所有应该作出的评价结果集
	 * @param ScorerId
	 *            评价主体（评价人、教工）Id
	 * @return 该评价人 所有的 应作出的 （主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByScorerIdPage(String teacherId, Page page) throws Exception;

	/**
	 * @see 分页地 获取某用户 某种状态 的评价结果集
	 * @param ScorerId
	 *            评价主体（评价人、教工）Id
	 * @param status
	 *            状态：0:未评价、1：保存，未提交、2:已提交
	 * @return 一个符合要求状态的（主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByScorerIdPage(String teacherId, int status, Page page) throws Exception;

	/**
	 * @see 获取某用户所有应该作出的评价结果集
	 * @param ScorerId
	 *            评价主体（评价人、教工）Id
	 * @return 该评价人 所有的 应作出的 （主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByScorerId(String teacherId) throws Exception;

	/**
	 * @see 获取某用户所有应被作出评价的评价结果集
	 * @param ScorerId
	 *            评价对象（被评人、教工）Id
	 * @return 该评价人 所有的 应作出的 （主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByObjectId(String teacherId) throws Exception;

	/**
	 * @see 获取某用户 某种状态 的评价结果集
	 * @param ScorerId
	 *            评价主体（评价人、教工）Id
	 * @param status
	 *            状态：0:未评价、1：保存，未提交、2:已提交
	 * @return 一个符合要求状态的（主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByScorerId(String teacherId, int status) throws Exception;

	/**
	 * @see 获取某用户 某种状态 应被作出评价的评价结果集
	 * @param ScorerId
	 *            评价对象（被评人、教工）Id
	 * @param status
	 *            状态：0:未评价、1：保存，未提交、2:已提交
	 * @return 一个符合要求状态的（主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByObjectId(String teacherId, int status) throws Exception;

	/**
	 * @see 获取某对象 某种状态 应被作出评价的评价结果集
	 * @param ScorerId
	 *            评价对象（被评人、教工）Id
	 * @param scoreRuleId
	 *            规则类型Id
	 * @param status
	 *            状态：0:未评价、1：保存，未提交、2:已提交
	 * @return 一个符合要求状态的（主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByObjectIdAndScoreRuleId(String teacherId, int scoreRuleId, int status)
			throws Exception;

	/**
	 * 获取所有考核对象的数组（根据ObjectId不重复）
	 * 
	 * @return 所有考核对象的数组
	 */
	public List<ScoreResultVO> getAllObject() throws Exception;

	/**
	 * 向数据库的“评分结果主表”中插入一条“评分关系”（状态为“0”的“评分结果”）
	 * 
	 * @param scoreResultVo
	 *            状态为“0”的“评分结果”
	 * @return 受影响行数
	 */
	public int insertScoreResult(ScoreResultVO scoreResultVo) throws Exception;

	/**
	 * 向数据库的“评分结果主表”中插入100条“评分关系”（状态为“0”的“评分结果”）
	 * 
	 * @param scoreResultVo
	 *            状态为“0”的“评分结果”
	 * @return 受影响行数
	 */
	public int batchInsertScoreResult(List<ScoreResultVO> scoreResultVos) throws Exception;

	/**
	 * 修改某条指定的（指定id）的评价结果
	 * 
	 * @param scoreResultVo
	 *            内含id和要修改的信息
	 * @return 受影响行数
	 */
	public int updateScoreResult(ScoreResultVO scoreResultVo) throws Exception;

	/**
	 * @see 改变某个评价结果的状态
	 * @param scoreResultId
	 *            指定评价结果的Id
	 * @param status
	 *            要改变成哪种状态：0:未评价、1：保存，未提交、2:已提交
	 * @param scoreResult
	 *            总分
	 * @param commitDate
	 *            提交日期（YYYY年MM月DD日）
	 * @return 受影响行数
	 */
	public int changeScoreResultStatusByID(int scoreResultId, int status, double scoreResult, String commitDate)
			throws Exception;

	/**
	 * 获取某个 考核对象 被 指定主体类型 以 指定规则 处于特定状态地记录数
	 * 
	 * @param ObjectId
	 *            指定考核对象
	 * @param scorerIdsStr
	 *            该类评价主体包含的所有主体ID（格式：（' scorerId',' scorerId',' scorerId'.....））
	 * @param scoreRuleId
	 *            指定规则
	 * @param status
	 *            指定状态 （注意：3代表获取全部三种状态）
	 * @return 记录数
	 */
	public int getCountByObjectIdAndScorerIdsAndScoreRuleId(String objectId, String scorerIdsStr, int scoreRuleId,
			int status) throws Exception;

	/**
	 * 获取某个 考核对象 被 指定规则 处于特定状态地记录数
	 * 
	 * @param ObjectId
	 *            指定考核对象
	 * @param scoreRuleId
	 *            指定规则
	 * @param status
	 *            指定状态 （注意：3代表获取全部三种状态）
	 * @return 记录数
	 */
	public int getCountByObjectIdAndScoreRuleId(String objectId, int scoreRuleId, int status) throws Exception;

	// --------------------------------以下，计算分数专用-------------------------------//
	/**
	 * 获取某考核对象 被 某类评价主体、以 某种考核规则 评价，并处于 提交 状态的评价 的 平均结果
	 * 
	 * @param teacherId
	 *            对象id
	 * @param scoreRuleId
	 *            规则id
	 * @param scorerIdsStr
	 *            该类评价主体包含的所有主体ID（格式：（' scorerId',' scorerId',' scorerId'.....））
	 * @return
	 */
	public double getAverageScoreByObjectIdAndScoreRuleIdAndScorerIds(String objectId, int scoreRuleId,
			String scorerIdsStr) throws Exception;

	public double getAverageScoreByObjectIdAndScoreRuleIdAndScorerIdsX(String objectId, int scoreRuleId,
			String scorerIdsStr, int scoreType) throws Exception;

	/**
	 * 获取某考核对象 被 某类评价主体、以 某种考核规则 评价，并处于 提交 状态的所有评价
	 * 
	 * @param teacherId
	 *            对象id
	 * @param scoreRuleId
	 *            规则id
	 * @param scorerIdsStr
	 *            该类评价主体包含的所有主体ID（格式：（' scorerId',' scorerId',' scorerId'.....））
	 * @return
	 */
	public List<ScoreResultVO> getScoreResultByObjectIdAndScoreRuleIdAndScorerIds(String objectId, int scoreRuleId,
			String scorerIdsStr) throws Exception;

	/**
	 * @param objectId
	 *            String 被考核对象的id
	 * @param objectType
	 *            String 被考核对象的考核类型
	 * @param scorerType
	 *            String 评价主体的评价类型
	 * @return list 制定考核对象的考核类型的所有主体
	 * @throws Exception
	 */
	public List<UserVO> getScorerByObjectType(String objectId, String objectType, String scorerType) throws Exception;

	/**
	 * @param objectId
	 *            String 被考核对象的id
	 * @param objectType
	 *            int 被考核对象的考核类型
	 * @param scorerId
	 *            String 评价主体的评价类型
	 * @return tempScore 查询的分数
	 * @throws Exception
	 */
	public double getScore(String objectId, int objectType, String scorerId) throws Exception;

}
