package com.gdcp.pas.score.bo;

import java.util.HashMap;
import java.util.List;

import com.gdcp.pas.score.dao.EvaluatedRuleDAO;
import com.gdcp.pas.score.dao.impl.EvaluatedRuleDAOImpl;
import com.gdcp.pas.score.vo.ScoreResultsVO;
import com.gdcp.pas.score.vo.EvaluateScoreRuleVO;
import com.gdcp.pas.score.vo.SecondIndexVO;

public class EvaluatedRuleBO {

	public List<EvaluateScoreRuleVO> getRuleList(int scoreRId) throws Exception {
		EvaluatedRuleDAO scoreRD = new EvaluatedRuleDAOImpl();
		List<EvaluateScoreRuleVO> soreRVList = scoreRD.getRuleList(scoreRId);
		return soreRVList;
	}

	public List<ScoreResultsVO> getResultList(int scoreRId, int scoreRuleId) throws Exception {
		EvaluatedRuleDAO scoreRD = new EvaluatedRuleDAOImpl();
		List<ScoreResultsVO> soreRVList = scoreRD.getResultList(scoreRId, scoreRuleId);
		return soreRVList;

	}

	public List<SecondIndexVO> getSecondIndex(int scoreRuleId) throws Exception {
		EvaluatedRuleDAO scoreRD = new EvaluatedRuleDAOImpl();
		List<SecondIndexVO> secondIndex = scoreRD.getSecondIndex(scoreRuleId);
		return secondIndex;
	}

	public HashMap<String, String> getSunScore(int scoreRId, int scoreRuleId) throws Exception {
		HashMap<String, String> map = new HashMap<>();
		EvaluatedRuleDAO scoreRD = new EvaluatedRuleDAOImpl();
		map = scoreRD.getSunScore(scoreRId, scoreRuleId);
		return map;

	}

}
