package com.gdcp.pas.score.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.score.dao.ScoreRuleDetailDAO;
import com.gdcp.pas.score.vo.ScoreResultVO;
import com.gdcp.pas.score.vo.ScoreRuleDetailVO;

/**
 * @author ��ΰ��
 * @see �����ֹ����ӱ���в����Ľӿ� ����ʵ��
 * @version 0327-21:00
 */
public class ScoreRuleDetailDAOImpl implements ScoreRuleDetailDAO {
	private DbAccess dbAccess = new DbAccess();

	/**
	 * @see �����ֹ����ӱ��в���ָ��id���ӹ������µ�childsΪ�գ�
	 * @param scoreRuleId
	 * @param parentId
	 * @return ���ع���ʵ��������
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
	 * @see �����ֹ����ӱ��в���ָ��id�ġ��ӹ��򡱼��䡰�ӹ���(ֱ�����굽��ײ�)
	 * @param scoreRuleId
	 * @param parentId
	 * @return ���ع���ʵ��������
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
