package com.gdcp.pas.score.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.SqlUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.score.dao.ScoreResultDetailDAO;
import com.gdcp.pas.score.vo.ScoreResultDetailVO;

/**
 * @author ��ΰ��
 * @version 0329-06:50
 * @see �����ֽ����ϸ��ķ����ӿڵ�ʵ��
 */
public class ScoreResultDetailDAOImpl implements ScoreResultDetailDAO {
	private DbAccess dbAccess = new DbAccess();

	/**
	 * @see ��ȡĳ���ֽ����������ϸ��¼
	 * @param scoreResultId
	 *            ���ֽ����id
	 * @return �����ֽ����������ϸ��¼
	 */
	@Override
	public List<ScoreResultDetailVO> getScoreResultDetailByScoreResultId(int scoreResultId) throws Exception {
		ArrayList<ScoreResultDetailVO> srdVos = new ArrayList<>();

		String sql = "select * from TB_SCORERESULTDETAIL where SCOREID = " + scoreResultId;
		RowSet rs = dbAccess.executeQuery(sql);

		if (rs == null) {
			return null;
		}

		while (rs != null && rs.next()) {
			ScoreResultDetailVO srdVo = new ScoreResultDetailVO();
			srdVo.setScoreDetailId(rs.getInt("SCOREDETAILID"));
			srdVo.setScoreId(rs.getInt("SCOREID"));
			srdVo.setDetailId(rs.getInt("DETAILID"));
			srdVo.setScore(rs.getInt("SCORE"));
			srdVo.setRemark(rs.getString("REMARK"));

			srdVos.add(srdVo);
		}

		return srdVos;
	}

	/**
	 * @see ��ȡĳ���ֽ����ĳ���������ϸ��¼
	 * @param scoreResultId
	 *            ���ֽ����id
	 * @param scoreRuleDetailId
	 *            2�������id
	 * @return �����ֽ����������ϸ��¼
	 */
	@Override
	public ScoreResultDetailVO getScoreResultDetailByScoreResultIdAndScoreRuleDetailId(int scoreResultId,
			int scoreRuleDetailId) throws Exception {

		String sql = "select * from TB_SCORERESULTDETAIL where SCOREID = " + scoreResultId + " and DETAILID = "
				+ scoreRuleDetailId;

		RowSet rs = dbAccess.executeQuery(sql);

		ScoreResultDetailVO srdVo = new ScoreResultDetailVO();
		if (rs != null && rs.next()) {
			srdVo.setScoreDetailId(rs.getInt("SCOREDETAILID"));
			srdVo.setScoreId(rs.getInt("SCOREID"));
			srdVo.setDetailId(rs.getInt("DETAILID"));
			srdVo.setScore(rs.getInt("SCORE"));
			srdVo.setRemark(rs.getString("REMARK"));
		} else {
			return null;
		}

		return srdVo;
	}

	/**
	 * @see �����ݿ����һ�����ֽ����ϸ��¼
	 * @param srdVo
	 *            Ҫ��������ֽ����ϸ��¼
	 * @return ���ؽ����˲���������
	 */
	@Override
	public int insertScoreResultDetail(ScoreResultDetailVO srdVo) throws Exception {

		String sql = "insert into TB_SCORERESULTDETAIL(SCOREDETAILID, SCOREID, " + "DETAILID, SCORE, REMARK) "
				+ "values ( " + SqlUtil.getInstance().getSeqId() + ", " + srdVo.getScoreId() + ", "
				+ srdVo.getDetailId() + ", " + srdVo.getScore() + ", " + srdVo.getRemark() + ")";
		return dbAccess.executeUpdate(sql);

	}

	/**
	 * @see �����ݿ��޸�һ�����ֽ����ϸ��¼
	 * @param srdVo
	 *            Ҫ��������ֽ����ϸ��¼
	 * @return ���ؽ����˲���������
	 */
	@Override
	public int updateScoreResultDetail(ScoreResultDetailVO srdVo) throws Exception {

		String sql = "update TB_SCORERESULTDETAIL set" + " SCORE=" + srdVo.getScore() + " where SCOREID = "
				+ srdVo.getScoreId() + " and DETAILID = " + srdVo.getDetailId();

		return dbAccess.executeUpdate(sql);

	}

	/**
	 * �жϷ�����¼�Ƿ��Ѿ�����
	 * 
	 * @param averageScoreVo
	 * @return �Ƿ�
	 */
	public boolean isExistScore(ScoreResultDetailVO scoreResultDetailVO) throws Exception {

		String sql = "select * from TB_SCORERESULTDETAIL" + " where SCOREID = " + scoreResultDetailVO.getScoreId()
				+ " and DETAILID = " + scoreResultDetailVO.getDetailId();

		RowSet rs = dbAccess.executeQuery(sql);

		if (rs != null && rs.next()) {
			return true;
		}

		return false;
	}

	/**
	 * ��ȡ ĳЩ���۽�� �� ָ��һ��ϸ���ƽ������
	 * 
	 * @param scoreResultIdsStr
	 * @param srdLv1VoId
	 * @return
	 */
	@Override
	public double getAverageDetailScoreByScorerIdsStrAndSrdLv1VoId(String scoreResultIdsStr, int srdLv1VoId)
			throws Exception {
		String sql = "select AVG(SCORE) as averageScore from TB_SCORERESULTDETAIL" + " where SCOREID in "
				+ scoreResultIdsStr + " and DETAILID = " + srdLv1VoId;

		RowSet rs = dbAccess.executeQuery(sql);

		double averageDetailScore = 0;
		if (rs != null && rs.next()) {
			averageDetailScore += rs.getDouble("averageScore");
		}
		return averageDetailScore;
	}
}
