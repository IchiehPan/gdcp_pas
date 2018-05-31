package com.gdcp.pas.score.bo;

import java.util.HashMap;
import java.util.List;

import com.gdcp.pas.score.dao.NotEvaluateRuleDAO;
import com.gdcp.pas.score.dao.impl.NotEvaluateRuleDAOImpl;
import com.gdcp.pas.score.vo.ScoreResultsVO;
import com.gdcp.pas.score.vo.EvaluateScoreRuleVO;
import com.gdcp.pas.score.vo.SecondIndexVO;

public class NotEvaluateRuleBO {

	public List<EvaluateScoreRuleVO> getRuleList(int scoreRId) throws Exception {
		NotEvaluateRuleDAO scoreRD = new NotEvaluateRuleDAOImpl();
		List<EvaluateScoreRuleVO> soreRVList = scoreRD.getRuleList(scoreRId);
		return soreRVList;
	}

	public List<ScoreResultsVO> getResultList(int scoreRId, int scoreRuleId) throws Exception {
		NotEvaluateRuleDAO scoreRD = new NotEvaluateRuleDAOImpl();
		List<ScoreResultsVO> soreRVList = scoreRD.getResultList(scoreRId, scoreRuleId);
		return soreRVList;

	}

	public HashMap<String, String> getSunScore(int scoreRId, int scoreRuleId) throws Exception {
		HashMap<String, String> map = new HashMap<>();
		NotEvaluateRuleDAO scoreRD = new NotEvaluateRuleDAOImpl();
		map = scoreRD.getSunScore(scoreRId, scoreRuleId);
		return map;

	}

	public List<SecondIndexVO> getSecondIndex(int scoreRuleId) throws Exception {
		NotEvaluateRuleDAO scoreRD = new NotEvaluateRuleDAOImpl();
		List<SecondIndexVO> secondIndex = scoreRD.getSecondIndex(scoreRuleId);
		return secondIndex;
	}

	public void SaveScoreResult(String scores, String status) throws Exception {
		NotEvaluateRuleDAO scoreRD = new NotEvaluateRuleDAOImpl();
		scoreRD.SaveScoreResult(scores, status);
	}

}
