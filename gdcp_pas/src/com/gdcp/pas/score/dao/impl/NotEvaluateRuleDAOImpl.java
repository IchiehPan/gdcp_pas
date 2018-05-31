package com.gdcp.pas.score.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.SqlUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.score.dao.NotEvaluateRuleDAO;
import com.gdcp.pas.score.vo.ScoreResultsVO;
import com.gdcp.pas.score.vo.EvaluateScoreRuleVO;
import com.gdcp.pas.score.vo.SecondIndexVO;

public class NotEvaluateRuleDAOImpl implements NotEvaluateRuleDAO {
	private DbAccess dbAccess = new DbAccess();

	/**
	 * �õ���ǰ�û����еĹ���� ��List
	 */
	@Override
	public List<EvaluateScoreRuleVO> getRuleList(int scoreRId) throws Exception {
		List<EvaluateScoreRuleVO> list = new ArrayList<>();
		String querySql = "select distinct TB_SCORERESULT.SCORERULEID, RULENAME from TB_SCORERESULT,TB_SCORERULE where TB_SCORERESULT.SCORERULEID = TB_SCORERULE.SCORERULEID and SCORERID='"
				+ scoreRId + "' and STATUS <> '2'";
		RowSet rs = dbAccess.executeQuery(querySql);
		while (rs != null && rs.next()) {
			EvaluateScoreRuleVO scoreRV = new EvaluateScoreRuleVO();
			scoreRV.setScoreRuleId(rs.getInt("scoreRuleId"));
			scoreRV.setRuleName(rs.getString("ruleName"));
			// �õ���ǰ�����״̬
			String sql = "select top 1 STATUS from  TB_SCORERESULT where SCORERULEID = '" + scoreRV.getScoreRuleId()
					+ "' and SCORERID='" + scoreRId + "' and STATUS <> '2'";
			RowSet rss = dbAccess.executeQuery(sql);
			if (rss != null && rss.next()) {
				scoreRV.setStatus(rss.getInt("STATUS"));
			}
			list.add(scoreRV);
		}
		return list;
	}

	/**
	 * �õ���ǰ�û���ĳ�ֹ�����List
	 */
	@Override
	public List<ScoreResultsVO> getResultList(int scoreRId, int scoreRuleId) throws Exception {
		List<ScoreResultsVO> list = new ArrayList<>();
		// �õ���ǰ�û���ĳ�ֹ�����List
		String querySql = "select  id, OBJECTID,USERNAME,DEPTNAME,SCORERESULT from TB_SCORERESULT , TB_USER ,TB_DEPT where  TB_SCORERESULT.OBJECTID = TB_USER.USERID and TB_DEPT.DEPTID = TB_USER.DEPTID and  SCORERID='"
				+ scoreRId + "' and STATUS <> '2' and  SCORERULEID='" + scoreRuleId + "'" + " order by TB_DEPT.DEPTID";
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
	 * ����һ������ϸ��ֵ�Map
	 */
	public HashMap<String, String> getSunScore(int scoreRId, int scoreRuleId) throws Exception {
		HashMap<String, String> map = new HashMap<>();
		String querySql = "select  id,ObjectId from TB_SCORERESULT where   SCORERID='" + scoreRId
				+ "' and STATUS <> '2' and  SCORERULEID='" + scoreRuleId + "'";
		RowSet rs = dbAccess.executeQuery(querySql);
		while (rs != null && rs.next()) {
			// ����÷ֵ�ֵ
			String sql = "select SCORE,DETAILID from TB_SCORERESULTDETAIL,TB_SCORERULEDETAIL where TB_SCORERESULTDETAIL.DETAILID = TB_SCORERULEDETAIL.SCOREDETAILID and SCOREID = '"
					+ rs.getInt("id") + "'  and  TB_SCORERULEDETAIL.DETAILLEVEL =1";
			RowSet rss = dbAccess.executeQuery(sql);
			while (rss != null && rss.next()) {
				map.put(rs.getInt("ObjectId") + "_" + rss.getInt("DETAILID"), rss.getString("SCORE"));
			}
		}
		return map;

	}

	/**
	 * �õ���ǰ�û���ĳ�ֹ�������ָ��
	 */
	@Override
	public List<SecondIndexVO> getSecondIndex(int scoreRuleId) throws Exception {
		String querySql = "select SCOREDETAILID,DESCRIPTION, DETAILSCORE from TB_SCORERULEDETAIL where DETAILLEVEL= '1' and SCORERULEID = '"
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
	 * �����ֺܷ�ϸ���
	 */
	@Override
	public void SaveScoreResult(String scores, String status) throws Exception {
		// ��ȡ��ǰʱ��
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy��MM��dd��");
		String nowtime = sf.format(d);
		// �ɼ�Id���ɼ�����
		String[] scoreArray1 = scores.split("firstSign");

		for (String record1 : scoreArray1) {
			String[] scoreArray2 = record1.split("secondSign");
			String resultId = scoreArray2[0];// �ɼ�Id
			int sumScore = 0; // �ܷ�

			for (int i = 1; i < scoreArray2.length; i++) {
				String[] scoreArray3 = scoreArray2[i].split("thirdSign");
				if (scoreArray3.length == 2) {
					List<String> sqlList = new ArrayList<>();
					String sql = "delete TB_SCORERESULTDETAIL   where DETAILID ='" + scoreArray3[0] + "' and SCOREID='"
							+ resultId + "'";
					sqlList.add(sql);
					String insertSql = "insert into TB_SCORERESULTDETAIL(scoreDetailId,SCOREID,DETAILID,SCORE) values('"
							+ SqlUtil.getInstance().getSeqId() + "','" + resultId + "','" + scoreArray3[0] + "','"
							+ scoreArray3[1] + "')";
					sqlList.add(insertSql);
					dbAccess.executeBatchUpdate(sqlList);
					sumScore += new Integer(scoreArray3[1]);
				}
			}
			String sumSql = "Update TB_SCORERESULT set SCORERESULT='" + sumScore + "' , STATUS='" + status
					+ "' , COMMITDATE='" + nowtime + "'  where id='" + resultId + "'";
			dbAccess.executeUpdate(sumSql);
		}
	}

}
