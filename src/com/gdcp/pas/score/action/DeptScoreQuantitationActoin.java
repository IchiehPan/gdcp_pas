package com.gdcp.pas.score.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.pas.score.bo.DeptScoreQuantitationBO;
import com.gdcp.pas.score.vo.DeptScoreQuantitationVO;

/**
 * ��Ҫ�������۽�ѧ���ŵĶ�����
 * 
 * @author ������
 * @version 1006-11:59
 */
public class DeptScoreQuantitationActoin {
	private static Logger logger = Logger.getLogger(DeptScoreQuantitationActoin.class);
	private DeptScoreQuantitationBO deptSQBO;

	/**
	 * ��ѯ�õ����н�ѧ���ŵ��б�
	 */
	public String query() {

		List<DeptScoreQuantitationVO> deptVOlist;
		try {
			HttpServletRequest request = ServletActionContext.getRequest();
			deptSQBO = new DeptScoreQuantitationBO();
			deptVOlist = deptSQBO.queryList();
			request.setAttribute("deptScoreQuantitationVO", deptVOlist);
			request.setAttribute("saveSuccess", request.getSession().getAttribute("saveSuccess"));
			request.getSession().setAttribute("saveSuccess", null);
		} catch (Exception e) {
			logger.error("��ȡ�����б�ʧ��", e);
		}
		return "query_success";
	}

	/**
	 * ���沿�Ŷ�����
	 * 
	 * @throws Exception
	 */
	public String saveScore() {
		HttpServletRequest request = ServletActionContext.getRequest();
		String scores = request.getParameter("scores");
		deptSQBO = new DeptScoreQuantitationBO();
		try {
			deptSQBO.save(scores);
		} catch (Exception e) {
			logger.error("���Ŷ������˷ֱ���ʧ��", e);
			request.getSession().setAttribute("saveSuccess", 2);
		}
		request.getSession().setAttribute("saveSuccess", 1);
		return "saveScore_success";
	}
}
