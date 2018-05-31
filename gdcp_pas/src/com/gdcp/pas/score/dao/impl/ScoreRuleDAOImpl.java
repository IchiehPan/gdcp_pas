package com.gdcp.pas.score.dao.impl;

import java.util.ArrayList;

import javax.sql.RowSet;

import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.score.dao.ScoreRuleDAO;
import com.gdcp.pas.score.vo.ScoreRuleVO;

/**
 * @author ��ΰ��
 * @see �����ֹ������в����ķ����ӿ�ʵ��
 * @version 0327-21:00
 */
public class ScoreRuleDAOImpl implements ScoreRuleDAO {
	private DbAccess dbAccess = new DbAccess();

	/**
	 * @see �����ֹ�����в���ָ���� (ָ������Ϊ��)
	 * 
	 * @param scoreRuleById
	 *            ���ֹ�����id
	 * @return һ�����ֹ����Ķ���
	 */
	public ScoreRuleVO getScoreRuleById(int scoreRuleId) throws Exception {

		ScoreRuleVO scoreRuleVo = new ScoreRuleVO();

		String sql = "select * from TB_SCORERULE where SCORERULEID = " + scoreRuleId;
		RowSet rs = dbAccess.executeQuery(sql);

		if (rs != null && rs.next()) {
			scoreRuleVo.setScoreRuleId(scoreRuleId);
			scoreRuleVo.setRuleYear(rs.getInt("RULEYEAR"));
			scoreRuleVo.setRuleType(rs.getInt("RULETYPE"));
			scoreRuleVo.setScore(rs.getInt("SCORE"));
			scoreRuleVo.setDetailLevel(rs.getInt("DETAILLEVEL"));
			scoreRuleVo.setRuleName(rs.getString("RULENAME"));
			scoreRuleVo.setRemark(rs.getString("REMARK"));

		} else {
			return null;
		}

		return scoreRuleVo;
	}

	/**
	 * @see �����ֹ�����в���ָ���� (ָ�����鲻Ϊ��)
	 * 
	 * @param scoreRuleById
	 *            ���ֹ�����id
	 * @return һ�����ֹ����Ķ���
	 */
	public ScoreRuleVO getScoreRuleTableById(int scoreRuleId) throws Exception {

		ScoreRuleVO scoreRuleVo = new ScoreRuleVO();

		String sql = "select * from TB_SCORERULE where SCORERULEID = " + scoreRuleId;
		RowSet rs = dbAccess.executeQuery(sql);

		if (rs != null && rs.next()) {
			scoreRuleVo.setScoreRuleId(scoreRuleId);
			scoreRuleVo.setRuleYear(rs.getInt("RULEYEAR"));
			scoreRuleVo.setRuleType(rs.getInt("RULETYPE"));
			scoreRuleVo.setScore(rs.getInt("SCORE"));
			scoreRuleVo.setDetailLevel(rs.getInt("DETAILLEVEL"));
			scoreRuleVo.setRuleName(rs.getString("RULENAME"));
			scoreRuleVo.setRemark(rs.getString("REMARK"));
		} else {
			return null;
		}

		sql = "select SCOREDETAILID from TB_SCORERULEDETAIL" + " where SCORERULEID = " + scoreRuleId
				+ " and PARENTID = " + 0;

		rs = dbAccess.executeQuery(sql);
		ArrayList<Integer> srdLV1Ids = new ArrayList<>();
		while (rs != null && rs.next()) {
			srdLV1Ids.add(rs.getInt("SCOREDETAILID"));
		}
		scoreRuleVo.setSrdLV1s(srdLV1Ids);

		return scoreRuleVo;
	}

}
