package com.gdcp.pas.score.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.score.dao.ScoreRuleDetailDAO;
import com.gdcp.pas.score.vo.ScoreResultVO;
import com.gdcp.pas.score.vo.ScoreRuleDetailVO;

/**
 * @author 陈伟镇
 * @see 对评分规则子表进行操作的接口 方法实现
 * @version 0327-21:00
 */
public class ScoreRuleDetailDAOImpl implements ScoreRuleDetailDAO {
	private DbAccess dbAccess = new DbAccess();

	/**
	 * @see 在评分规则子表中查找指定id的子规则（其下的childs为空）
	 * @param scoreRuleId
	 * @param parentId
	 * @return 返回规则实例的数组
	 */
	public List<ScoreRuleDetailVO> getScoreRuleByRuleIdAndParentId(int scoreRuleId, int parentId) throws Exception {

		ArrayList<ScoreRuleDetailVO> scoreRuleDetailVos = new ArrayList<>();

		String sql = "select * from TB_SCORERULEDETAIL" + " where SCORERULEID = " + scoreRuleId + " and PARENTID = "
				+ parentId;

		RowSet rs = dbAccess.executeQuery(sql);

		if (rs == null || rs.isAfterLast()) {
			return null;
		}

		while (rs != null && rs.next()) {
			ScoreRuleDetailVO scoreRuleDetailVo = new ScoreRuleDetailVO();
			scoreRuleDetailVo.setScoreRuleId(rs.getInt("SCORERULEID"));
			scoreRuleDetailVo.setScoreDetailId(rs.getInt("SCOREDETAILID"));
			scoreRuleDetailVo.setDetailScore(rs.getInt("DETAILSCORE"));
			scoreRuleDetailVo.setDetailLevel(rs.getInt("DETAILLEVEL"));
			scoreRuleDetailVo.setParentId(rs.getInt("PARENTID"));
			scoreRuleDetailVo.setDescRiption(rs.getString("DESCRIPTION"));
			scoreRuleDetailVo.setRemark(rs.getString("REMARK"));

			scoreRuleDetailVos.add(scoreRuleDetailVo);
		}

		return scoreRuleDetailVos;
	}

	/**
	 * @see 在评分规则子表中查找指定id的“子规则”及其“子规则”(直接引申到最底层)
	 * @param scoreRuleId
	 * @param parentId
	 * @return 返回规则实例的数组
	 */
	public List<ScoreRuleDetailVO> getScoreRuleAndChildsByResultIdAndParentId(ScoreResultVO scoreResultVo, int parentId)
			throws Exception {

		ArrayList<ScoreRuleDetailVO> scoreRuleDetailVos = new ArrayList<>();

		String sql = "";

		if (scoreResultVo.getStatus() == 0) {
			sql = "select * from TB_SCORERULEDETAIL" + " where SCORERULEID = " + scoreResultVo.getScoreRuleId()
					+ " and PARENTID = " + parentId;
		} else {
			sql = "select a.*, b.SCORE from" + " TB_SCORERULEDETAIL a, TB_SCORERESULTDETAIL b"
					+ " where a.SCORERULEID = " + scoreResultVo.getScoreRuleId() + " and b.SCOREID = "
					+ scoreResultVo.getId() + " and a.SCOREDETAILID = b.DETAILID" + " and a.PARENTID = " + parentId;
		}

		RowSet rs = dbAccess.executeQuery(sql);

		if (rs == null || rs.isAfterLast()) {
			return null;
		}

		while (rs != null && rs.next()) {
			ScoreRuleDetailVO scoreRuleDetailVo = new ScoreRuleDetailVO();
			scoreRuleDetailVo.setScoreRuleId(rs.getInt("SCORERULEID"));
			scoreRuleDetailVo.setScoreDetailId(rs.getInt("SCOREDETAILID"));
			scoreRuleDetailVo.setDetailScore(rs.getInt("DETAILSCORE"));
			scoreRuleDetailVo.setDetailLevel(rs.getInt("DETAILLEVEL"));
			scoreRuleDetailVo.setParentId(rs.getInt("PARENTID"));
			scoreRuleDetailVo.setDescRiption(rs.getString("DESCRIPTION"));
			scoreRuleDetailVo.setRemark(rs.getString("REMARK"));

			if (scoreResultVo.getStatus() != 0) {
				scoreRuleDetailVo.setScore(rs.getInt("SCORE"));
			}

			scoreRuleDetailVo
					.setChilds((ArrayList<ScoreRuleDetailVO>) getScoreRuleAndChildsByResultIdAndParentId(scoreResultVo,
							scoreRuleDetailVo.getScoreDetailId()));

			scoreRuleDetailVos.add(scoreRuleDetailVo);
		}

		return scoreRuleDetailVos;
	}

}
