package com.gdcp.pas.score.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.score.dao.EvaluatedRuleDAO;
import com.gdcp.pas.score.vo.ScoreResultsVO;
import com.gdcp.pas.score.vo.EvaluateScoreRuleVO;
import com.gdcp.pas.score.vo.SecondIndexVO;

public class EvaluatedRuleDAOImpl implements EvaluatedRuleDAO {
	private DbAccess dbAccess = new DbAccess();

	/**
	 * 得到当前用户所有的规则表 的List
	 */
	@Override
	public List<EvaluateScoreRuleVO> getRuleList(int scoreRId) throws Exception {
		List<EvaluateScoreRuleVO> list = new ArrayList<>();
		String querySql = "select distinct TB_SCORERESULT.SCORERULEID, RULENAME from TB_SCORERESULT,TB_SCORERULE where TB_SCORERESULT.SCORERULEID = TB_SCORERULE.SCORERULEID and SCORERID='"
				+ scoreRId + "' and STATUS = '2'";
		RowSet rs = dbAccess.executeQuery(querySql);
		while (rs != null && rs.next()) {
			EvaluateScoreRuleVO scoreRV = new EvaluateScoreRuleVO();
			scoreRV.setScoreRuleId(rs.getInt("scoreRuleId"));
			scoreRV.setRuleName(rs.getString("ruleName"));
			// 得到当前规则的状态
			String sql = "select top 1 STATUS from  TB_SCORERESULT where SCORERULEID = '" + scoreRV.getScoreRuleId()
					+ "' and SCORERID='" + scoreRId + "' and STATUS = '2'";
			RowSet rss = dbAccess.executeQuery(sql);
			if (rss != null && rss.next()) {
				scoreRV.setStatus(rss.getInt("STATUS"));
			}
			list.add(scoreRV);
		}
		return list;
	}

	/**
	 * 得到当前用户的某种规则表的List
	 */
	@Override
	public List<ScoreResultsVO> getResultList(int scoreRId, int scoreRuleId) throws Exception {
		List<ScoreResultsVO> list = new ArrayList<>();
		String querySql = "select  id, OBJECTID,USERNAME,DEPTNAME,SCORERESULT from TB_SCORERESULT , TB_USER ,TB_DEPT where  TB_SCORERESULT.OBJECTID = TB_USER.USERID and TB_DEPT.DEPTID = TB_USER.DEPTID and  SCORERID='"
				+ scoreRId + "' and STATUS = '2' and  SCORERULEID='" + scoreRuleId + "'" + " order by TB_DEPT.DEPTID";
		RowSet rs = dbAccess.executeQuery(querySql);
		while (rs != null && rs.next()) {
			ScoreResultsVO scoreRv = new ScoreResultsVO();
			scoreRv.setResultsId(rs.getInt("id"));
			scoreRv.setObjectId(rs.getInt("OBJECTID"));
			scoreRv.setObjectDept(rs.getString("DEPTNAME"));
			scoreRv.setObjectName(rs.getString("USERNAME"));
			scoreRv.setScore(rs.getFloat("SCORERESULT"));
			list.add(scoreRv);
		}
		return list;
	}

	/**
	 * 得到当前用户的某种规则表二级指标
	 */
	@Override
	public List<SecondIndexVO> getSecondIndex(int scoreRuleId) throws Exception {
		String querySql = "select SCOREDETAILID,DESCRIPTION, DETAILSCORE  from TB_SCORERULEDETAIL where DETAILLEVEL= '1' and SCORERULEID = '"
				+ scoreRuleId + "'";
		RowSet rs = dbAccess.executeQuery(querySql);
		List<SecondIndexVO> secondIndexL = new ArrayList<>();
		while (rs != null && rs.next()) {
			SecondIndexVO secondIndexVO = new SecondIndexVO();
			secondIndexVO.setRuleDetailId(rs.getInt("SCOREDETAILID"));
			secondIndexVO.setSecondIndex(rs.getString("DESCRIPTION"));
			secondIndexVO.setDetailSV(rs.getInt("DETAILSCORE"));
			secondIndexL.add(secondIndexVO);
		}
		return secondIndexL;
	}

	/**
	 * 返回一个关于细则分的Map
	 */
	public HashMap<String, String> getSunScore(int scoreRId, int scoreRuleId) throws Exception {
		HashMap<String, String> map = new HashMap<>();
		String querySql = "select  id,ObjectId from TB_SCORERESULT where   SCORERID='" + scoreRId
				+ "' and STATUS = '2' and  SCORERULEID='" + scoreRuleId + "'";
		RowSet rs = dbAccess.executeQuery(querySql);
		while (rs != null && rs.next()) {
			// 子项得分的值
			String sql = "select SCORE,DETAILID from TB_SCORERESULTDETAIL,TB_SCORERULEDETAIL where TB_SCORERESULTDETAIL.DETAILID = TB_SCORERULEDETAIL.SCOREDETAILID and SCOREID = '"
					+ rs.getInt("id") + "'  and  TB_SCORERULEDETAIL.DETAILLEVEL =1";
			RowSet rss = dbAccess.executeQuery(sql);
			while (rss != null && rss.next()) {
				map.put(rs.getInt("ObjectId") + "_" + rss.getInt("DETAILID"), rss.getString("SCORE"));
			}
		}
		return map;

	}

}
