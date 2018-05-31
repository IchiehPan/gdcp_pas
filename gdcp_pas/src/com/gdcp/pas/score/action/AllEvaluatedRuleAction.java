package com.gdcp.pas.score.action;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.pas.manage.vo.UserVO;
import com.gdcp.pas.score.bo.AllEvaluatedRuleBO;
import com.gdcp.pas.score.vo.ScoreResultsVO;
import com.gdcp.pas.score.vo.EvaluateScoreRuleVO;
import com.gdcp.pas.score.vo.SecondIndexVO;

public class AllEvaluatedRuleAction {
	private static Logger logger = Logger.getLogger(AllEvaluatedRuleAction.class);

	/**
	 * �õ���ǰ�û����еĹ���� ��List
	 */
	public String getRuleList() {
		List<EvaluateScoreRuleVO> soreRVList = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		UserVO userVO = (UserVO) request.getSession().getAttribute("userVO");
		int scoreRId = userVO.getUserId();
		AllEvaluatedRuleBO scoreRB = new AllEvaluatedRuleBO();
		try {
			soreRVList = scoreRB.getRuleList(scoreRId);
		} catch (Exception e) {
			logger.error("�õ���ǰ�û����еĹ���� ��Listʧ��", e);
		}
		request.setAttribute("soreRVList", soreRVList);
		return "getRuleList_success";

	}

	/**
	 * �õ���ǰ�û���ĳ�ֹ�����List�Ͷ���ָ��
	 */
	public String getScoreResultAndSecondIndex() {
		List<ScoreResultsVO> ScoreResultVList = null;
		List<SecondIndexVO> secondIndexL = null;
		HashMap<String, String> secondIndexMap = new HashMap<>();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserVO userVO = (UserVO) request.getSession().getAttribute("userVO");
		int scoreRuleId = new Integer(request.getParameter("scoreRuleId"));// �õ����ֹ���Id
		int scoreRId = userVO.getUserId(); // �õ���������Id
		AllEvaluatedRuleBO scoreRB = new AllEvaluatedRuleBO();
		try {
			ScoreResultVList = scoreRB.getResultList(scoreRId, scoreRuleId);
			secondIndexL = scoreRB.getSecondIndex(scoreRuleId);
			secondIndexMap = scoreRB.getSunScore(scoreRId, scoreRuleId);
		} catch (Exception e) {
			logger.error("�õ���ǰ�û���ĳ�ֹ�����List�Ͷ���ָ��ʧ��", e);
		}
		request.setAttribute("secondIndexMap", secondIndexMap);
		request.setAttribute("ruleName", request.getParameter("ruleName"));
		request.setAttribute("scoreRuleId", scoreRuleId);
		request.setAttribute("soreRVList", ScoreResultVList);
		request.setAttribute("secondIndexL", secondIndexL);
		return "getScoreResultAndSecondIndex_success";

	}

}
