package com.gdcp.pas.score.dao;

import java.util.List;

import com.gdcp.pas.score.vo.ScoreResultVO;
import com.gdcp.pas.score.vo.ScoreRuleDetailVO;

/**
 * @author 陈伟镇
 * @see 对评分规则子表进行操作的方法接口
 * @version 0327-21:00
 */
public interface ScoreRuleDetailDAO {

	/**
	 * @see 在评分规则子表中查找指定id的子规则（其下的childs为空）
	 * @param scoreRuleId
	 * @param parentId
	 * @return 返回规则实例的数组
	 */
	public List<ScoreRuleDetailVO> getScoreRuleByRuleIdAndParentId(int scoreRuleId, int parentId) throws Exception;

	/**
	 * @see 在评分规则子表中查找指定id的“子规则”及其“子规则”(直接引申到最底层)
	 * @param scoreRuleId
	 * @param parentId
	 * @return 返回规则实例的数组
	 */
	public List<ScoreRuleDetailVO> getScoreRuleAndChildsByResultIdAndParentId(ScoreResultVO scoreResultVo, int parentId)
			throws Exception;

}
