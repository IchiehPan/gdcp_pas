package com.gdcp.pas.score.dao;

import com.gdcp.pas.score.vo.ScoreRuleVO;

/**
 * @author 陈伟镇
 * @see 对评分规则表进行操作的方法接口
 * @version 0327-21:00
 */
public interface ScoreRuleDAO {

	/**
	 * @see 在评分规则表中查找指定表 (指标数组为空)
	 * 
	 * @param scoreRuleById
	 *            评分规则表的id
	 * @return 一个评分规则表的对象
	 */
	public ScoreRuleVO getScoreRuleById(int scoreRuleId) throws Exception;

	/**
	 * @see 在评分规则表中查找指定表 (指标数组不为空)
	 * 
	 * @param scoreRuleById
	 *            评分规则表的id
	 * @return 一个评分规则表的对象
	 */
	public ScoreRuleVO getScoreRuleTableById(int scoreRuleId) throws Exception;

}
