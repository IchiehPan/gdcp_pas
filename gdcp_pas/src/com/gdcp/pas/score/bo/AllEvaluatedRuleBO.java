package com.gdcp.pas.score.bo;

import java.util.HashMap;
import java.util.List;

import com.gdcp.pas.score.dao.AllEvaluatedRuleDAO;
import com.gdcp.pas.score.dao.impl.AllEvaluatedRuleDAOImpl;
import com.gdcp.pas.score.vo.ScoreResultsVO;
import com.gdcp.pas.score.vo.EvaluateScoreRuleVO;
import com.gdcp.pas.score.vo.SecondIndexVO;

public class AllEvaluatedRuleBO {

	public List<EvaluateScoreRuleVO> getRuleList(int scoreRId) throws Exception {
		AllEvaluatedRuleDAO scoreRD = new AllEvaluatedRuleDAOImpl();
		List<EvaluateScoreRuleVO> soreRVList = scoreRD.getRuleList(scoreRId);
		return soreRVList;
	}

	public List<ScoreResultsVO> getResultList(int scoreRId, int scoreRuleId) throws Exception {
		AllEvaluatedRuleDAO scoreRD = new AllEvaluatedRuleDAOImpl();
		List<ScoreResultsVO> soreRVList = scoreRD.getResultList(scoreRId, scoreRuleId);
		return soreRVList;

	}

	public List<SecondIndexVO> getSecondIndex(int scoreRuleId) throws Exception {
		AllEvaluatedRuleDAO scoreRD = new AllEvaluatedRuleDAOImpl();
		List<SecondIndexVO> secondIndex = scoreRD.getSecondIndex(scoreRuleId);
		return secondIndex;
	}

	public HashMap<String, String> getSunScore(int scoreRId, int scoreRuleId) throws Exception {
		HashMap<String, String> map = new HashMap<String, String>();
		AllEvaluatedRuleDAO scoreRD = new AllEvaluatedRuleDAOImpl();
		map = scoreRD.getSunScore(scoreRId, scoreRuleId);
		return map;

	}

}
