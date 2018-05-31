package com.gdcp.pas.score.dao.impl;

import java.util.ArrayList;

import javax.sql.RowSet;

import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.score.dao.ScoreRuleDAO;
import com.gdcp.pas.score.vo.ScoreRuleVO;

/**
 * @author 陈伟镇
 * @see 对评分规则表进行操作的方法接口实现
 * @version 0327-21:00
 */
public class ScoreRuleDAOImpl implements ScoreRuleDAO {
	private DbAccess dbAccess = new DbAccess();

	/**
	 * @see 在评分规则表中查找指定表 (指标数组为空)
	 * 
	 * @param scoreRuleById
	 *            评分规则表的id
	 * @return 一个评分规则表的对象
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
	 * @see 在评分规则表中查找指定表 (指标数组不为空)
	 * 
	 * @param scoreRuleById
	 *            评分规则表的id
	 * @return 一个评分规则表的对象
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
