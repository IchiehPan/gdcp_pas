package com.gdcp.pas.manage.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;

import com.gdcp.pas.manage.bo.DeptBO;

import com.gdcp.pas.manage.vo.DeptVO;
import com.gdcp.pas.score.bo.AverageScoreBO;
import com.gdcp.pas.score.vo.AverageScoreVO;

/**
 * @author Administrator cyx 2015-3-30
 * 
 */
public class ShowResultAction {
	private String scoreLeve;// redio名字
	private String DeptList;// 部门名字
	private HttpServletRequest request;

	private static Logger logger = Logger.getLogger(ShowResultAction.class);
	private DeptBO deptBO = new DeptBO();

	public String queryResule() {
		try {

			List<DeptVO> deptVOlist = deptBO.queryAll();
			HttpServletRequest request = ServletActionContext.getRequest();
			request.setAttribute("deptVO", deptVOlist);
		} catch (Exception e) {
			logger.error("queryResule", e);
			return "query_failed";
		}

		return "query_success";
	}

	public void Look() {

		int deptId;
		request = ServletActionContext.getRequest();
		String dataXml = null;
		HttpServletResponse response = ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		try {
			String score = URLDecoder.decode(scoreLeve, "UTF-8");
			String Dept = URLDecoder.decode(DeptList, "UTF-8");
			if (Dept.equals("所有部门")) {
				deptId = -1;
			} else {
				deptId = deptBO.getDeptByName(Dept);
			}
			AverageScoreBO averageScoreBO = new AverageScoreBO();
			;
			List<AverageScoreVO> list;

			int one = 0;// <60
			int two = 0;// 60~70
			int three = 0;// 70~80
			int four = 0;// 80~90
			int five = 0;// 90~100

			if (deptId == -1) {

				list = averageScoreBO.getWeightScoreByScoreBuleId(Integer.parseInt(score));
				for (int i = 0; i < list.size(); i++) {

					AverageScoreVO averageScoreVO = list.get(i);
					double averageScore = averageScoreVO.getAverageScore();

					if (averageScore < 60) {
						one++;
					} else if (averageScore < 70) {
						two++;
					} else if (averageScore < 80) {
						three++;
					} else if (averageScore < 90) {
						four++;
					} else if (averageScore <= 100) {
						five++;
					}
				}

			} else {

				list = averageScoreBO.getWeightScoreByScoreBuleIdAndDeptId(Integer.parseInt(score), deptId);

				for (int i = 0; i < list.size(); i++) {

					AverageScoreVO averageScoreVO = list.get(i);
					double averageScore = averageScoreVO.getAverageScore();
					if (averageScore < 60) {
						one++;
					} else if (averageScore < 70) {
						two++;
					} else if (averageScore < 80) {
						three++;
					} else if (averageScore < 90) {
						four++;
					} else if (averageScore <= 100) {
						five++;
					}
				}

			}

			switch (Integer.parseInt(score)) {
			case 1001:
				dataXml = " <chart caption=\"" + " 内设机构领导绩效考核统计"
						+ "\"  showLegend=\"1\" legendPosition=\"RIGHT\" subcaption=\"" + "For the year 2015"
						+ "\" xAxisName=\"" + "scoreScope" + "\" yAxisName=\"" + "number of people" + "\" >"
						+ " <set value=\"" + one + "\" label=\"" + "0~60" + "\"  /> " + " <set value=\"" + two
						+ "\" label=\"" + "60~70" + "\"  />" + " <set value=\"" + three + "\" label=\"" + "70~80"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "<set value=\"" + four + "\" label=\"" + "80~90"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "<set value=\"" + five + "\" label=\"" + "90~100"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "</chart> ";

				break;
			case 1002:

				dataXml = " <chart caption=\"" + " 二级教学部门综合办主任、教务员岗绩效考核统计"
						+ "\"  showLegend=\"1\"  legendPosition=\"RIGHT\" subcaption=\"" + "For the year 2015"
						+ "\" xAxisName=\"" + "scoreScope" + "\" yAxisName=\"" + "number of people" + "\" >"
						+ " <set value=\"" + one + "\" label=\"" + "0~60" + "\"  /> " + " <set value=\"" + two
						+ "\" label=\"" + "60~70" + "\"  />" + " <set value=\"" + three + "\" label=\"" + "70~80"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "<set value=\"" + four + "\" label=\"" + "80~90"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "<set value=\"" + five + "\" label=\"" + "90~100"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "</chart> ";

				break;

			case 1003:
				dataXml = " <chart caption=\"" + " 辅导员岗绩效考核统计"
						+ "\"  showLegend=\"1\" legendPosition=\"RIGHT\" subcaption=\"" + "For the year 2015"
						+ "\" xAxisName=\"" + "scoreScope" + "\" yAxisName=\"" + "number of people" + "\" >"
						+ " <set value=\"" + one + "\" label=\"" + "0~60" + "\"  /> " + " <set value=\"" + two
						+ "\" label=\"" + "60~70" + "\"  />" + " <set value=\"" + three + "\" label=\"" + "70~80"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "<set value=\"" + four + "\" label=\"" + "80~90"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "<set value=\"" + five + "\" label=\"" + "90~100"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "</chart> ";

				break;

			case 1004:
				dataXml = " <chart caption=\"" + " 非教学管理岗绩效考核统计"
						+ "\"  showLegend=\"1\" legendPosition=\"RIGHT\" subcaption=\"" + "For the year 2015"
						+ "\" xAxisName=\"" + "scoreScope" + "\" yAxisName=\"" + "number of people" + "\" >"
						+ " <set value=\"" + one + "\" label=\"" + "0~60" + "\"  /> " + " <set value=\"" + two
						+ "\" label=\"" + "60~70" + "\"  />" + " <set value=\"" + three + "\" label=\"" + "70~80"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "<set value=\"" + four + "\" label=\"" + "80~90"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "<set value=\"" + five + "\" label=\"" + "90~100"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "</chart> ";

				break;

			case 1005:
				dataXml = " <chart caption=\"" + " 工勤技能岗绩效考核统计"
						+ "\"  showLegend=\"1\" legendPosition=\"RIGHT\" subcaption=\"" + "For the year 2015"
						+ "\" xAxisName=\"" + "scoreScope" + "\" yAxisName=\"" + "number of people" + "\" >"
						+ " <set value=\"" + one + "\" label=\"" + "0~60" + "\"  /> " + " <set value=\"" + two
						+ "\" label=\"" + "60~70" + "\"  />" + " <set value=\"" + three + "\" label=\"" + "70~80"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "<set value=\"" + four + "\" label=\"" + "80~90"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "<set value=\"" + five + "\" label=\"" + "90~100"
						+ "\"  isSliced=\"" + "1" + "\" /> " + "</chart> ";

				break;

			default:
				break;
			}

			request.setAttribute("dataXml", dataXml);
			out = response.getWriter();
			out.print(dataXml);
			out.flush();
			out.close();
		} catch (IOException e) {
			logger.error("IO异常", e);
		} catch (Exception e) {
			logger.error("", e);
		}
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public String getScoreLeve() {
		return scoreLeve;
	}

	public void setScoreLeve(String scoreLeve) {
		this.scoreLeve = scoreLeve;
	}

	public String getDeptList() {
		return DeptList;
	}

	public void setDeptList(String deptList) {
		DeptList = deptList;
	}

	public static Logger getLogger() {
		return logger;
	}

	public static void setLogger(Logger logger) {
		ShowResultAction.logger = logger;
	}

	public DeptBO getDeptBO() {
		return deptBO;
	}

	public void setDeptBO(DeptBO deptBO) {
		this.deptBO = deptBO;
	}

}
