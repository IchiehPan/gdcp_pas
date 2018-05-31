package com.gdcp.pas.score.bo;

import java.util.List;

import com.gdcp.pas.score.dao.ScoreRuleDetailDAO;
import com.gdcp.pas.score.dao.impl.ScoreRuleDetailDAOImpl;
import com.gdcp.pas.score.vo.ScoreResultVO;
import com.gdcp.pas.score.vo.ScoreRuleDetailVO;

/**
 * @author 陈伟镇
 *
 */
public class ScoreRuleDetailBO {

	ScoreRuleDetailDAO srdDAO = new ScoreRuleDetailDAOImpl();

	/**
	 * @see 在评分规则子表中查找指定id的子规则（其下的childs为空）
	 * @param scoreRuleId
	 * @param parentId
	 * @return 返回规则实例的数组
	 */
	public List<ScoreRuleDetailVO> getScoreRuleByRuleIdAndParentId(int scoreRuleId, int parentId) throws Exception {
		return srdDAO.getScoreRuleByRuleIdAndParentId(scoreRuleId, parentId);
	}

	/**
	 * 获取指定 评价结果考核表 的“子规则”及其“子规则”(直接引申到最底层，包括成绩)
	 * 
	 * @param scoreResultVo
	 *            指定评价结果
	 * @param parentId
	 * @return
	 */
	public List<ScoreRuleDetailVO> getScoreRuleAndChildsByResultIdAndParentId(ScoreResultVO scoreResultVo, int parentId)
			throws Exception {
		return srdDAO.getScoreRuleAndChildsByResultIdAndParentId(scoreResultVo, parentId);
	}

}
