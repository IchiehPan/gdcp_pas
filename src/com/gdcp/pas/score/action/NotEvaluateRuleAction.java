package com.gdcp.pas.score.action;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.pas.manage.vo.UserVO;
import com.gdcp.pas.score.bo.NotEvaluateRuleBO;
import com.gdcp.pas.score.vo.ScoreResultsVO;
import com.gdcp.pas.score.vo.EvaluateScoreRuleVO;
import com.gdcp.pas.score.vo.SecondIndexVO;

public class NotEvaluateRuleAction {
	private static Logger logger = Logger.getLogger(NotEvaluateRuleAction.class);

	/**
	 * �õ���ǰ�û����еĹ���� ��List
	 */
	public String getRuleList() {
		List<EvaluateScoreRuleVO> soreRVList = null;
		HttpServletRequest request = ServletActionContext.getRequest();
		UserVO userVO = (UserVO) request.getSession().getAttribute("userVO");
		int scoreRId = userVO.getUserId();
		NotEvaluateRuleBO scoreRB = new NotEvaluateRuleBO();
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
	 * 
	 * @throws UnsupportedEncodingException
	 */
	public String getScoreResultAndSecondIndex() throws UnsupportedEncodingException {
		List<ScoreResultsVO> ScoreResultVList = null;
		List<SecondIndexVO> secondIndexL = null;
		HashMap<String, String> secondIndexMap = new HashMap<String, String>();
		HttpServletRequest request = ServletActionContext.getRequest();
		UserVO userVO = (UserVO) request.getSession().getAttribute("userVO");
		int scoreRuleId = new Integer(request.getParameter("scoreRuleId"));// �õ����ֹ���Id
		int scoreRId = userVO.getUserId(); // �õ���������Id
		NotEvaluateRuleBO scoreRB = new NotEvaluateRuleBO();
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

	/**
	 * �������ۺ�ķ���
	 */
	public String SaveScoreResult() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String scores = request.getParameter("scores");
		String status = request.getParameter("changeTo");
		NotEvaluateRuleBO scoreRB = new NotEvaluateRuleBO();
		try {
			scoreRB.SaveScoreResult(scores, status);
		} catch (Exception e) {
			logger.error("�������ۺ�ķ���ʧ��", e);
		}
		return "SaveScoreResult_success";

	}
}
