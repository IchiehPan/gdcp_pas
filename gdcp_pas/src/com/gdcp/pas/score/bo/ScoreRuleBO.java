package com.gdcp.pas.score.bo;

import com.gdcp.pas.score.dao.impl.ScoreRuleDAOImpl;
import com.gdcp.pas.score.vo.ScoreRuleVO;

/**
 * @author 陈伟镇
 * 
 */
public class ScoreRuleBO {

	ScoreRuleDAOImpl srdi = new ScoreRuleDAOImpl();

	/**
	 * @see 在评分规则表中查找指定表(指标数组为空)
	 * 
	 * @param scoreRuleById
	 *            评分规则表的id
	 * @return 一个评分规则表的对象
	 */
	public ScoreRuleVO getScoreRuleById(int scoreRuleId) throws Exception {
		return srdi.getScoreRuleById(scoreRuleId);
	}

	/**
	 * @see 在评分规则表中查找指定表 (指标数组不为空)
	 * 
	 * @param scoreRuleById
	 *            评分规则表的id
	 * @return 一个评分规则表的对象
	 */
	public ScoreRuleVO getScoreRuleTableById(int scoreRuleId) throws Exception {
		return srdi.getScoreRuleTableById(scoreRuleId);
	}

}
