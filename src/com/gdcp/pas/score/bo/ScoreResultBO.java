package com.gdcp.pas.score.bo;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.UserVO;
import com.gdcp.pas.score.dao.ScoreResultDAO;
import com.gdcp.pas.score.dao.impl.ScoreResultDAOImpl;
import com.gdcp.pas.score.vo.ScoreResultVO;

/**
 * @author 陈伟镇
 * @version 0329=0730
 * @see 对评分结果主体表操作方法BO
 */
public class ScoreResultBO {
	ScoreResultDAO srd = new ScoreResultDAOImpl();

	/**
	 * @see 获取指定评价结果
	 * @param scoreResultId
	 *            评价结果自身的id
	 * @return 指定评价结果Vo
	 */
	public ScoreResultVO getScoreResultByScoreResultId(int scoreResultId) throws Exception {
		return srd.getScoreResultByScoreResultId(scoreResultId);
	}

	/**
	 * 获取某条指定（指定对象、主体、规则）的评价结果
	 * 
	 * @param objectId
	 * @param scorerId
	 * @param scoreReuleId
	 */
	public ScoreResultVO getScoreResultByObjectIdAndScorerId(String objectId, String scorerId, int scoreReuleId)
			throws Exception {
		return srd.getScoreResultByObjectIdAndScorerId(objectId, scorerId, scoreReuleId);
		//
	}

	/**
	 * @see 分页地 获取某用户所有应该作出的评价结果集
	 * @param ScorerId
	 *            评价主体（评价人、教工）Id
	 * @return 该评价人 所有的 应作出的 （主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByScorerIdPage(String teacherId, Page page) throws Exception {
		return srd.getScoreResultByScorerIdPage(teacherId, page);
	}

	/**
	 * @see 分页地 获取某用户 某种状态 的评价结果集
	 * @param ScorerId
	 *            评价主体（评价人、教工）Id
	 * @param status
	 *            状态：0:未评价、1：保存，未提交、2:已提交
	 * @return 一个符合要求状态的（主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByScorerIdPage(String teacherId, int status, Page page) throws Exception {
		return srd.getScoreResultByScorerIdPage(teacherId, status, page);
	}

	/**
	 * @see 获取某用户所有应该作出的评价结果集
	 * @param ScorerId
	 *            评价主体（评价人、教工）Id
	 * @return 该评价人 所有的 应作出的 （主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByScorerId(String scorerId) throws Exception {
		return srd.getScoreResultByScorerId(scorerId);
	}

	/**
	 * @see 获取某用户所有应被作出评价的评价结果集
	 * @param ScorerId
	 *            评价对象（被评人、教工）Id
	 * @return 该评价人 所有的 应作出的 （主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByObjectId(String objectId) throws Exception {
		return srd.getScoreResultByObjectId(objectId);
	}

	/**
	 * @see 获取某用户 某种状态 的评价结果集
	 * @param ScorerId
	 *            评价主体（评价人、教工）Id
	 * @param status
	 *            状态：0:未评价、1：保存，未提交、2:已提交
	 * @return 一个符合要求状态的（主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByScorerId(String scorerId, int status) throws Exception {
		return srd.getScoreResultByScorerId(scorerId, status);
	}

	/**
	 * @see 获取某用户 某种状态 应被作出评价的评价结果集
	 * @param ScorerId
	 *            评价对象（被评人、教工）Id
	 * @param status
	 *            状态：0:未评价、1：保存，未提交、2:已提交
	 * @return 一个符合要求状态的（主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByObjectId(String objectId, int status) throws Exception {
		return srd.getScoreResultByObjectId(objectId, status);
	}

	/**
	 * @see 获取某用户 某种状态 应被作出评价的评价结果集
	 * @param ScorerId
	 *            评价对象（被评人、教工）Id
	 * @param scoreRuleId
	 *            规则类型Id
	 * @param status
	 *            状态：0:未评价、1：保存，未提交、2:已提交
	 * @return 一个符合要求状态的（主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByObjectIdAndScoreRuleId(String objectId, int scoreRuleId, int status)
			throws Exception {
		return srd.getScoreResultByObjectIdAndScoreRuleId(objectId, scoreRuleId, status);
	}

	/**
	 * 获取所有考核对象的list（根据ObjectId不重复）
	 * 
	 * @return 所有考核对象的list
	 * @throws Exception
	 */
	public List<ScoreResultVO> getAllObject() throws Exception {
		return srd.getAllObject();
	}

	/**
	 * 向数据库的“评分结果主表”中插入一条“评分关系”（状态为“0”的“评分结果”）
	 * 
	 * @param scoreResultVo
	 *            状态为“0”的“评分结果”
	 * @return 运行行数
	 * @throws Exception
	 */
	public int insertScoreResult(ScoreResultVO srVo) throws Exception {
		return srd.insertScoreResult(srVo);
	}

	/**
	 * 向数据库的“评分结果主表”中插入100条“评分关系”（状态为“0”的“评分结果”）
	 * 
	 * @param scoreResultVo
	 *            状态为“0”的“评分结果”
	 * @return 运行行数
	 * @throws Exception
	 */
	public int batchInsertScoreResult(List<ScoreResultVO> scoreResultVos) throws Exception {
		return srd.batchInsertScoreResult(scoreResultVos);
	}

	/**
	 * 修改某条指定的（指定id）的评价结果
	 * 
	 * @param scoreResultVo
	 *            内含id和要修改的信息
	 * @return 受影响行数
	 */
	public int updateScoreResult(ScoreResultVO srVo) throws Exception {
		return srd.updateScoreResult(srVo);
	}

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
			int status) throws Exception {
		return srd.getCountByObjectIdAndScorerIdsAndScoreRuleId(objectId, scorerIdsStr, scoreRuleId, status);
	}

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
	public int getCountByObjectIdAndScoreRuleId(String objectId, int scoreRuleId, int status) throws Exception {
		return srd.getCountByObjectIdAndScoreRuleId(objectId, scoreRuleId, status);
	}

	/**
	 * @see 改变某个评价结果的状态
	 * @param scoreResultId
	 *            指定评价结果的Id
	 * @param status
	 *            要改变成哪种状态：0:未评价、1：保存，未提交、2:已提交
	 * @param commitDate
	 *            提交日期（YYYY年MM月DD日）
	 * @param score
	 *            总分，放在数据库中remark(备注)中
	 * @return 返回进行了操作的行数
	 */
	public int changeScoreResultStatusByID(int scoreResultId, int status, double scoreResult, String commitDate)
			throws Exception {
		return srd.changeScoreResultStatusByID(scoreResultId, status, scoreResult, commitDate);
	}

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
	public double getAverageScoreByObjectIdAndScoreRuleIdAndScorerIdsX(String objectId, int scoreRuleId,
			String scorerIdsStr, int scoreType) throws Exception {
		return srd.getAverageScoreByObjectIdAndScoreRuleIdAndScorerIdsX(objectId, scoreRuleId, scorerIdsStr, scoreType);
	}

	public double getAverageScoreByObjectIdAndScoreRuleIdAndScorerIds(String objectId, int scoreRuleId,
			String scorerIdsStr) throws Exception {
		return srd.getAverageScoreByObjectIdAndScoreRuleIdAndScorerIds(objectId, scoreRuleId, scorerIdsStr);
	}

	/**
	 * 获取某考核对象 被 某类评价主体、以 某种考核规则 评价，并处于 提交 状态的评价结果
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
			String scorerIdsStr) throws Exception {
		return srd.getScoreResultByObjectIdAndScoreRuleIdAndScorerIds(objectId, scoreRuleId, scorerIdsStr);
	}

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
	public List<UserVO> getScorerByObjectType(String objectId, String objectType, String scorerType) throws Exception {
		return srd.getScorerByObjectType(objectId, objectType, scorerType);
	}

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
	public double getScore(String objectId, int objectType, String scorerId) throws Exception {
		return srd.getScore(objectId, objectType, scorerId);
	}
}
