package com.gdcp.pas.score.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.pas.score.bo.DeptScoreQuantitationBO;
import com.gdcp.pas.score.vo.DeptScoreQuantitationVO;

/**
 * 主要用于评价教学部门的定量分
 * 
 * @author 王世鹏
 * @version 1006-11:59
 */
public class DeptScoreQuantitationActoin {
	private static Logger logger = Logger.getLogger(DeptScoreQuantitationActoin.class);
	private DeptScoreQuantitationBO deptSQBO;

	/**
	 * 查询得到所有教学部门的列表
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
			logger.error("获取部门列表失败", e);
		}
		return "query_success";
	}

	/**
	 * 保存部门定量分
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
			logger.error("部门定量考核分保存失败", e);
			request.getSession().setAttribute("saveSuccess", 2);
		}
		request.getSession().setAttribute("saveSuccess", 1);
		return "saveScore_success";
	}
}
