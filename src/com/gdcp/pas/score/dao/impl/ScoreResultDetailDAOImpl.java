package com.gdcp.pas.score.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.SqlUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.score.dao.ScoreResultDetailDAO;
import com.gdcp.pas.score.vo.ScoreResultDetailVO;

/**
 * @author 陈伟镇
 * @version 0329-06:50
 * @see 对评分结果明细表的方法接口的实现
 */
public class ScoreResultDetailDAOImpl implements ScoreResultDetailDAO {
	private DbAccess dbAccess = new DbAccess();

	/**
	 * @see 获取某评分结果的所有明细记录
	 * @param scoreResultId
	 *            评分结果的id
	 * @return 该评分结果的所有明细记录
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
	 * @see 获取某评分结果的某条规则的明细记录
	 * @param scoreResultId
	 *            评分结果的id
	 * @param scoreRuleDetailId
	 *            2级规则的id
	 * @return 该评分结果的所有明细记录
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
	 * @see 向数据库插入一条评分结果明细记录
	 * @param srdVo
	 *            要插入的评分结果明细记录
	 * @return 返回进行了操作的行数
	 */
	@Override
	public int insertScoreResultDetail(ScoreResultDetailVO srdVo) throws Exception {

		String sql = "insert into TB_SCORERESULTDETAIL(SCOREDETAILID, SCOREID, " + "DETAILID, SCORE, REMARK) "
				+ "values ( " + SqlUtil.getInstance().getSeqId() + ", " + srdVo.getScoreId() + ", "
				+ srdVo.getDetailId() + ", " + srdVo.getScore() + ", " + srdVo.getRemark() + ")";
		return dbAccess.executeUpdate(sql);

	}

	/**
	 * @see 向数据库修改一条评分结果明细记录
	 * @param srdVo
	 *            要插入的评分结果明细记录
	 * @return 返回进行了操作的行数
	 */
	@Override
	public int updateScoreResultDetail(ScoreResultDetailVO srdVo) throws Exception {

		String sql = "update TB_SCORERESULTDETAIL set" + " SCORE=" + srdVo.getScore() + " where SCOREID = "
				+ srdVo.getScoreId() + " and DETAILID = " + srdVo.getDetailId();

		return dbAccess.executeUpdate(sql);

	}

	/**
	 * 判断分数记录是否已经存在
	 * 
	 * @param averageScoreVo
	 * @return 是否
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
	 * 获取 某些评价结果 的 指定一级细则的平均分数
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
