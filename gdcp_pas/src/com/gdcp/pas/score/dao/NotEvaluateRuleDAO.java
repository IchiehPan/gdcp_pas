package com.gdcp.pas.score.dao;

import java.util.HashMap;

import com.gdcp.pas.score.vo.ScoreResultsVO;
import com.gdcp.pas.score.vo.EvaluateScoreRuleVO;
import com.gdcp.pas.score.vo.SecondIndexVO;

public interface NotEvaluateRuleDAO {

	public java.util.List<EvaluateScoreRuleVO> getRuleList(int scoreRId) throws Exception;

	public java.util.List<ScoreResultsVO> getResultList(int scoreRId, int scoreRuleId) throws Exception;

	public java.util.List<SecondIndexVO> getSecondIndex(int scoreRuleId) throws Exception;

	public void SaveScoreResult(String scores, String status) throws Exception;

	public HashMap<String, String> getSunScore(int scoreRId, int scoreRuleId) throws Exception;

}
