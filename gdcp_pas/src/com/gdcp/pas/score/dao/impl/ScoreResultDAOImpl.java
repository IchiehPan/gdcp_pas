package com.gdcp.pas.score.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.gdcp.common.Page;

import javax.sql.RowSet;

import com.gdcp.common.SqlUtil;
import com.gdcp.common.StringUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.vo.UserVO;
import com.gdcp.pas.score.dao.ScoreResultDAO;
import com.gdcp.pas.score.vo.ScoreResultVO;

/**
 * @author 陈伟镇
 * @version 0329-01:18
 * @see 对评分结果主表的方法接口的实现
 *
 */
public class ScoreResultDAOImpl implements ScoreResultDAO {
	private DbAccess dbAccess = new DbAccess();

	/**
	 * @see 获取指定评价结果
	 * @param scoreResultId
	 *            评价结果自身的id
	 * @return 指定评价结果Vo
	 */
	public ScoreResultVO getScoreResultByScoreResultId(int scoreResultId) throws Exception {
		String sql = "select a.* , b.USERNAME as ObjectName, c.DEPTNAME as ObjectDeptName, d.USERNAME as ScorerName, e.DEPTNAME as ScorerDeptName, f.RULENAME as ScoreRuleTable"
				+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f "
				+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID"
				+ " and a.id = " + scoreResultId;

		RowSet rs = dbAccess.executeQuery(sql);

		ScoreResultVO srVo = new ScoreResultVO();

		if (rs != null && rs.next()) {
			// bean:
			srVo.setId(rs.getInt("id"));
			srVo.setObjectId(rs.getString("OBJECTID"));
			srVo.setObjectType(rs.getInt("OBJECTTYPE"));
			srVo.setScorerId(rs.getString("SCORERID"));
			srVo.setScorerType(rs.getInt("SCORERTYPE"));
			srVo.setScoreRuleId(rs.getInt("SCORERULEID"));
			srVo.setStatus(rs.getInt("STATUS"));
			srVo.setScoreResult(rs.getDouble("SCORERESULT"));
			srVo.setCommitDate(rs.getString("COMMITDATE"));
			srVo.setRemark(rs.getString("REMARK"));

			// 界面显示需要：
			srVo.setObjectName(rs.getString("ObjectName"));
			srVo.setObjectDeptName(rs.getString("ObjectDeptName"));
			srVo.setScorerName(rs.getString("ScorerName"));
			srVo.setScorerDeptName(rs.getString("ScorerDeptName"));
			srVo.setScoreRuleTable(rs.getString("ScoreRuleTable"));
		}

		return srVo;
	}

	/**
	 * 获取某条指定（指定对象、主体、规则）的评价结果
	 * 
	 * @param objectId
	 * @param scorerId
	 * @param scoreReuleId
	 */
	public ScoreResultVO getScoreResultByObjectIdAndScorerId(String objectId, String scorerId, int scoreReuleId)
			throws Exception {
		String sql = "select a.* , b.USERNAME as ObjectName, c.DEPTNAME as ObjectDeptName, d.USERNAME as ScorerName, e.DEPTNAME as ScorerDeptName, f.RULENAME as ScoreRuleTable"
				+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f "
				+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID"
				+ " and a.OBJECTID = " + StringUtil.fieldValue(objectId) + " and a.SCORERID = "
				+ StringUtil.fieldValue(scorerId) + " and a.SCORERULEID = " + scoreReuleId;

		RowSet rs = dbAccess.executeQuery(sql);

		ScoreResultVO srVo = new ScoreResultVO();

		if (rs != null && rs.next()) {
			// bean:
			srVo.setId(rs.getInt("id"));
			srVo.setObjectId(rs.getString("OBJECTID"));
			srVo.setObjectType(rs.getInt("OBJECTTYPE"));
			srVo.setScorerId(rs.getString("SCORERID"));
			srVo.setScorerType(rs.getInt("SCORERTYPE"));
			srVo.setScoreRuleId(rs.getInt("SCORERULEID"));
			srVo.setStatus(rs.getInt("STATUS"));
			srVo.setScoreResult(rs.getDouble("SCORERESULT"));
			srVo.setCommitDate(rs.getString("COMMITDATE"));
			srVo.setRemark(rs.getString("REMARK"));

			// 界面显示需要：
			srVo.setObjectName(rs.getString("ObjectName"));
			srVo.setObjectDeptName(rs.getString("ObjectDeptName"));
			srVo.setScorerName(rs.getString("ScorerName"));
			srVo.setScorerDeptName(rs.getString("ScorerDeptName"));
			srVo.setScoreRuleTable(rs.getString("ScoreRuleTable"));
		}

		return srVo;
	}

	// -----------------------------以下是界面显示专用-------------------------------//
	/**
	 * @see 分页地 获取某用户所有应该作出的评价结果集
	 * @param ScorerId
	 *            评价主体（评价人、教工）Id
	 * @return 该评价人 所有的 应作出的 （主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByScorerIdPage(String teacherId, Page page) throws Exception {
		String countSql = " select count(*) ";
		ArrayList<ScoreResultVO> srVos = new ArrayList<>();
		String sql = "select a.* , b.USERNAME as ObjectName, c.DEPTNAME as ObjectDeptName, d.USERNAME as ScorerName, e.DEPTNAME as ScorerDeptName, f.RULENAME as ScoreRuleTable"
				+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f "
				+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID"
				+ " and a.SCORERID = " + StringUtil.fieldValue(teacherId);
		// + " order by STATUS desc , c.DEPTID";
		countSql = countSql + " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f ";
		countSql = countSql
				+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID";
		countSql = countSql + " and a.SCORERID = " + StringUtil.fieldValue(teacherId);
		RowSet rs = dbAccess.executeQuery(countSql);
		if (rs != null && rs.next()) {
			page.setTotal(rs.getInt(1));
		}

		sql = SqlUtil.getSQLServerPageSQL(page.getPageNo(), page.getPageSize(), "Id", sql);
		rs = dbAccess.executeQuery(sql);
		return setBasicMessage(srVos, rs);
	}

	/**
	 * @see 分页地 获取某用户 某种状态 的评价结果集
	 * @param ScorerId
	 *            评价主体（评价人、教工）Id
	 * @param status
	 *            状态：0:未评价、1：保存，未提交、2:已提交
	 * @return 一个符合要求状态的（主）评价结果数组
	 */
	@Override
	public List<ScoreResultVO> getScoreResultByScorerIdPage(String teacherId, int status, Page page) throws Exception {
		String countSql = " select count(*) ";
		String sql = "";
		if (status != 3) {
			sql = "select a.* , b.USERNAME as ObjectName, c.DEPTNAME as ObjectDeptName, d.USERNAME as ScorerName, e.DEPTNAME as ScorerDeptName, f.RULENAME as ScoreRuleTable"
					+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f "
					+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID"
					+ " and a.SCORERID = " + StringUtil.fieldValue(teacherId) + " and a.STATUS = " + status;
			// + " order by c.DEPTID";
			countSql = countSql
					+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f ";
			countSql = countSql
					+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID";
			countSql = countSql + " and a.SCORERID = " + StringUtil.fieldValue(teacherId) + " and a.STATUS = " + status;

		} else if (status == 3) {
			sql = "select a.* , b.USERNAME as ObjectName, c.DEPTNAME as ObjectDeptName, d.USERNAME as ScorerName, e.DEPTNAME as ScorerDeptName, f.RULENAME as ScoreRuleTable"
					+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f "
					+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID"
					+ " and a.SCORERID = " + StringUtil.fieldValue(teacherId) + " and a.STATUS in(1,2)";
			// + " order by a.STATUS,c.DEPTID";

			countSql = countSql
					+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f ";
			countSql = countSql
					+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID";
			countSql = countSql + " and a.SCORERID = " + StringUtil.fieldValue(teacherId) + " and a.STATUS in(1,2)";
		}
		RowSet rs = dbAccess.executeQuery(countSql);

		if (rs != null && rs.next()) {
			page.setTotal(rs.getInt(1));
		}

		sql = SqlUtil.getSQLServerPageSQL(page.getPageNo(), page.getPageSize(), "Id", sql);
		ArrayList<ScoreResultVO> srVos = new ArrayList<>();
		rs = dbAccess.executeQuery(sql);

		return setBasicMessage(srVos, rs);
	}

	// -----------------------------以下返回列表--------------------------------//

	/**
	 * @see 获取某用户所有应该作出的评价结果集
	 * @param ScorerId
	 *            评价主体（评价人、教工）Id
	 * @return 该评价人 所有的 应作出的 （主）评价结果数组
	 */
	@Override
	public List<ScoreResultVO> getScoreResultByScorerId(String teacherId) throws Exception {

		ArrayList<ScoreResultVO> srVos = new ArrayList<>();
		String sql = "select a.* , b.USERNAME as ObjectName, c.DEPTNAME as ObjectDeptName, d.USERNAME as ScorerName, e.DEPTNAME as ScorerDeptName, f.RULENAME as ScoreRuleTable"
				+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f "
				+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID"
				+ " and a.SCORERID = " + StringUtil.fieldValue(teacherId) + " order by a.STATUS desc, c.DEPTID";

		RowSet rs = dbAccess.executeQuery(sql);

		return setBasicMessage(srVos, rs);
	}

	/**
	 * @see 获取某用户所有应被作出评价的评价结果集
	 * @param ObjectId
	 *            评价对象（被评人、教工）Id
	 * @return 该评价人 所有的 应作出的 （主）评价结果数组
	 */
	@Override
	public List<ScoreResultVO> getScoreResultByObjectId(String teacherId) throws Exception {
		ArrayList<ScoreResultVO> srVos = new ArrayList<>();

		String sql = "select a.* , b.USERNAME as ObjectName, c.DEPTNAME as ObjectDeptName, d.USERNAME as ScorerName, e.DEPTNAME as ScorerDeptName, f.RULENAME as ScoreRuleTable"
				+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f "
				+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID"
				+ " and a.OBJECTID = " + StringUtil.fieldValue(teacherId) + " order by a.STATUS, e.DEPTID";

		RowSet rs = dbAccess.executeQuery(sql);

		return setBasicMessage(srVos, rs);
	}

	/**
	 * @see 获取某用户 某种状态 的评价结果集
	 * @param ScorerId
	 *            评价主体（评价人、教工）Id
	 * @param status
	 *            状态：0:未评价、1：保存，未提交、2:已提交
	 * @return 一个符合要求状态的（主）评价结果数组
	 */
	@Override
	public List<ScoreResultVO> getScoreResultByScorerId(String teacherId, int status) throws Exception {
		ArrayList<ScoreResultVO> srVos = new ArrayList<>();

		String sql = "select a.* , b.USERNAME as ObjectName, c.DEPTNAME as ObjectDeptName, d.USERNAME as ScorerName, e.DEPTNAME as ScorerDeptName, f.RULENAME as ScoreRuleTable"
				+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f "
				+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID"
				+ " and a.SCORERID = " + StringUtil.fieldValue(teacherId) + " and a.STATUS = " + status
				+ " order by c.DEPTID";

		RowSet rs = dbAccess.executeQuery(sql);

		return setBasicMessage(srVos, rs);
	}

	/**
	 * @see 获取某用户 某种状态 应被作出评价的评价结果集
	 * @param ObjectId
	 *            评价对象（被评人、教工）Id
	 * @param status
	 *            状态：0:未评价、1：保存，未提交、2:已提交
	 * @return 一个符合要求状态的（主）评价结果数组
	 */
	@Override
	public List<ScoreResultVO> getScoreResultByObjectId(String teacherId, int status) throws Exception {
		ArrayList<ScoreResultVO> srVos = new ArrayList<>();

		String sql = "select a.* , b.USERNAME as ObjectName, c.DEPTNAME as ObjectDeptName, d.USERNAME as ScorerName, e.DEPTNAME as ScorerDeptName, f.RULENAME as ScoreRuleTable"
				+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f "
				+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID"
				+ " and a.OBJECTID = " + StringUtil.fieldValue(teacherId) + " and a.STATUS = " + status
				+ " order by e.DEPTID";

		RowSet rs = dbAccess.executeQuery(sql);

		return setBasicMessage(srVos, rs);
	}

	/**
	 * @see 获取某用户 某种状态 应被作出评价的评价结果集
	 * @param ObjectI
	 *            评价对象（被评人、教工）Id
	 * @param scoreRuleId
	 *            规则类型Id
	 * @param status
	 *            状态：0:未评价、1：保存，未提交、2:已提交、3:所有
	 * @return 一个符合要求状态的（主）评价结果数组
	 */
	public List<ScoreResultVO> getScoreResultByObjectIdAndScoreRuleId(String teacherId, int scoreRuleId, int status)
			throws Exception {
		ArrayList<ScoreResultVO> srVos = new ArrayList<>();
		String sql = "";
		if (status != 3) {
			sql = "select a.* , b.USERNAME as ObjectName, c.DEPTNAME as ObjectDeptName, d.USERNAME as ScorerName, e.DEPTNAME as ScorerDeptName, f.RULENAME as ScoreRuleTable"
					+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c  , TB_USER d , TB_DEPT e, TB_SCORERULE f "
					+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID"
					+ " and a.OBJECTID = " + StringUtil.fieldValue(teacherId) + " and a.STATUS = " + status
					+ " and a.SCORERULEID = " + scoreRuleId + " order by e.DEPTID";
		} else {
			sql = "select a.* , b.USERNAME as ObjectName, c.DEPTNAME as ObjectDeptName, d.USERNAME as ScorerName, e.DEPTNAME as ScorerDeptName, f.RULENAME as ScoreRuleTable"
					+ " from TB_SCORERESULT a, TB_USER b, TB_DEPT c , TB_USER d , TB_DEPT e, TB_SCORERULE f "
					+ " where a.OBJECTID=b.USERID and b.DEPTID=c.DEPTID and a.SCORERID=d.USERID  and d.DEPTID=e.DEPTID and a.SCORERULEID=f.SCORERULEID"
					+ " and a.OBJECTID = " + StringUtil.fieldValue(teacherId) + " and a.SCORERULEID = " + scoreRuleId
					+ " order by a.STATUS, a.SCORERTYPE, e.DEPTID, d.USERNAME";
		}
		RowSet rs = dbAccess.executeQuery(sql);

		return setBasicMessage(srVos, rs);

	}

	/**
	 * 获取所有考核对象的数组（根据ObjectId不重复）
	 * 
	 * @return 所有考核对象的list
	 * @throws Exception
	 */
	@Override
	public List<ScoreResultVO> getAllObject() throws Exception {
		ArrayList<ScoreResultVO> srVos = new ArrayList<>();

		String sql = "select distinct OBJECTID, OBJECTTYPE, SCORERULEID from TB_SCORERESULT";

		RowSet rs = dbAccess.executeQuery(sql);
		while (rs != null && rs.next()) {
			ScoreResultVO srVo = new ScoreResultVO();
			srVo.setObjectId(rs.getString("OBJECTID"));
			srVo.setObjectType(rs.getInt("OBJECTTYPE"));
			srVo.setScoreRuleId(rs.getInt("SCORERULEID"));

			srVos.add(srVo);
		}

		return srVos;
	}

	/**
	 * 凡是读取的时候都用这个方法给返回的Vo设置基本值
	 */
	private List<ScoreResultVO> setBasicMessage(ArrayList<ScoreResultVO> srVos, RowSet rs) throws SQLException {

		while (rs != null && rs.next()) {
			ScoreResultVO srVo = new ScoreResultVO();
			// bean:
			srVo.setId(rs.getInt("id"));
			srVo.setObjectId(rs.getString("OBJECTID"));
			srVo.setObjectType(rs.getInt("OBJECTTYPE"));
			srVo.setScorerId(rs.getString("SCORERID"));
			srVo.setScorerType(rs.getInt("SCORERTYPE"));
			srVo.setScoreRuleId(rs.getInt("SCORERULEID"));
			srVo.setStatus(rs.getInt("STATUS"));
			srVo.setScoreResult(rs.getDouble("SCORERESULT"));
			srVo.setCommitDate(rs.getString("COMMITDATE"));
			srVo.setRemark(rs.getString("REMARK"));

			// 界面显示需要：
			srVo.setObjectName(rs.getString("ObjectName"));
			srVo.setObjectDeptName(rs.getString("ObjectDeptName"));
			srVo.setScorerName(rs.getString("ScorerName"));
			srVo.setScorerDeptName(rs.getString("ScorerDeptName"));
			srVo.setScoreRuleTable(rs.getString("ScoreRuleTable"));
			srVos.add(srVo);
		}

		return srVos;
	}

	// ---------------------------------以下修改相关------------------------------------------//

	/**
	 * 向数据库的“评分结果主表”中插入一条“评分关系”（状态为“0”的“评分结果”）
	 * 
	 * @param scoreResultVo
	 *            状态为“0”的“评分结果”
	 * @return 运行行数
	 * @throws Exception
	 */
	public int insertScoreResult(ScoreResultVO srVo) throws Exception {

		String sql = "insert into TB_SCORERESULT(id, OBJECTID, OBJECTTYPE, SCORERID, SCORERTYPE, "
				+ "SCORERULEID, STATUS, SCORERESULT, COMMITDATE, REMARK)" + " values("
				+ SqlUtil.getInstance().getSeqId() + ", " + StringUtil.fieldValue(srVo.getObjectId()) + ", "
				+ srVo.getObjectType() + ", " + StringUtil.fieldValue(srVo.getScorerId()) + ", " + srVo.getScorerType()
				+ ", " + srVo.getScoreRuleId() + ", " + srVo.getStatus() + ", " + srVo.getScoreResult() + ", "
				+ "'  年   月    日', " + StringUtil.fieldValue(srVo.getRemark()) + ")";

		return dbAccess.executeUpdate(sql);
	}

	/**
	 * 向数据库的“评分结果主表”中插入100条“评分关系”（状态为“0”的“评分结果”）
	 * 
	 * @param scoreResultVo
	 *            状态为“0”的“评分结果”
	 * @return 运行行数
	 * @throws Exception
	 */
	public int batchInsertScoreResult(List<ScoreResultVO> scoreResultVos) throws Exception {
		ArrayList<String> sqlList = new ArrayList<>();

		for (int i = 0; i < scoreResultVos.size(); i++) {
			ScoreResultVO srVo = scoreResultVos.get(i);

			String sql = "insert into TB_SCORERESULT(id, OBJECTID, OBJECTTYPE, SCORERID, SCORERTYPE, "
					+ "SCORERULEID, STATUS, SCORERESULT, COMMITDATE, REMARK)" + " values("
					+ SqlUtil.getInstance().getSeqId() + ", " + StringUtil.fieldValue(srVo.getObjectId()) + ", "
					+ srVo.getObjectType() + ", " + StringUtil.fieldValue(srVo.getScorerId()) + ", "
					+ srVo.getScorerType() + ", " + srVo.getScoreRuleId() + ", " + srVo.getStatus() + ", "
					+ srVo.getScoreResult() + ", " + "'  年   月    日', " + StringUtil.fieldValue(srVo.getRemark()) + ")";

			sqlList.add(sql);
		}
		if (sqlList.size() > 0) {
			return dbAccess.executeBatchUpdate(sqlList);
		} else {
			return 0;
		}
	}

	/**
	 * 修改某条指定的（指定id）的评价结果
	 * 
	 * @param scoreResultVo
	 *            内含id和要修改的信息
	 * @return 受影响行数
	 */
	public int updateScoreResult(ScoreResultVO srVo) throws Exception {
		String sql = "update TB_SCORERESULT set " + " SCORERULEID = " + srVo.getScoreRuleId() + " where id = "
				+ srVo.getId();

		return dbAccess.executeUpdate(sql);
	}

	/**
	 * @see 改变某个评价结果的状态
	 * @param scoreResultId
	 *            指定评价结果的Id
	 * @param status
	 *            要改变成哪种状态：0:未评价、1：保存，未提交、2:已提交
	 * @param scoreResult
	 *            总分
	 * @param commitDate
	 *            提交日期（YYYY年MM月DD日）
	 * @return 返回进行了操作的行数
	 */
	@Override
	public int changeScoreResultStatusByID(int scoreResultId, int status, double scoreResult, String commitDate)
			throws Exception {
		String sql = "update TB_SCORERESULT set STATUS = " + status + ", SCORERESULT = " + scoreResult
				+ ", COMMITDATE = " + StringUtil.fieldValue(commitDate) + " where id = " + scoreResultId;
		return dbAccess.executeUpdate(sql);

	}

	/**
	 * 获取某个 考核对象 被 指定主体类型 以 指定规则 处于特定状态地记录数
	 * 
	 * @param ObjectId
	 *            指定考核对象
	 * @param scorerIdsStr
	 *            该类评价主体包含的所有主体ID（格式：（' scorerId',' scorerId',' scorerId'.....））
	 * @param scoreRuleId
	 *            指定规则
	 * @param status
	 *            指定状态 （注意：3代表获取全部三种状态）
	 * @return 记录数
	 */
	@Override
	public int getCountByObjectIdAndScorerIdsAndScoreRuleId(String objectId, String scorerIdsStr, int scoreRuleId,
			int status) throws Exception {
		String sql = "";
		if (status != 3) {
			sql = "select OBJECTID,COUNT(OBJECTID) as num from TB_SCORERESULT" + " where OBJECTID = "
					+ StringUtil.fieldValue(objectId) + " and SCORERULEID = " + scoreRuleId + " and SCORERID in "
					+ scorerIdsStr + " and STATUS = " + status + " GROUP BY OBJECTID";
		} else {
			sql = "select OBJECTID,COUNT(OBJECTID) as num from TB_SCORERESULT" + " where OBJECTID= "
					+ StringUtil.fieldValue(objectId) + " and SCORERID in " + scorerIdsStr + " and SCORERULEID = "
					+ scoreRuleId + " GROUP BY OBJECTID";
		}

		RowSet rs = dbAccess.executeQuery(sql);
		if (rs != null && rs.next()) {
			return rs.getInt("num");
		}
		return 0;
	}

	/**
	 * 获取某个 考核对象 被 指定规则 处于特定状态地记录数
	 * 
	 * @param ObjectId
	 *            指定考核对象
	 * @param scoreRuleId
	 *            指定规则
	 * @param status
	 *            指定状态 （注意：3代表获取全部三种状态）
	 * @return 记录数
	 */
	public int getCountByObjectIdAndScoreRuleId(String objectId, int scoreRuleId, int status) throws Exception {
		String sql = "";
		if (status != 3) {
			sql = "select OBJECTID,COUNT(OBJECTID) as num from TB_SCORERESULT" + " where OBJECTID = "
					+ StringUtil.fieldValue(objectId) + " and SCORERULEID = " + scoreRuleId + " and STATUS = " + status
					+ " GROUP BY OBJECTID";
		} else {
			sql = "select OBJECTID,COUNT(OBJECTID) as num from TB_SCORERESULT" + " where OBJECTID= "
					+ StringUtil.fieldValue(objectId) + " and SCORERULEID = " + scoreRuleId + " GROUP BY OBJECTID";
		}

		RowSet rs = dbAccess.executeQuery(sql);
		if (rs != null && rs.next()) {
			return rs.getInt("num");
		}
		return 0;
	}

	// --------------------------------以下，计算分数专用-------------------------------//
	/**
	 * 获取某考核对象 被 某类评价主体、以 某种考核规则 评价，并处于 提交 状态的评价 的 平均结果
	 * 
	 * @param teacherId
	 *            对象id
	 * @param scoreRuleId
	 *            规则id
	 * @param scorerIdsStr
	 *            该类评价主体包含的所有主体ID（格式：（'scorerId',' scorerId',' scorerId'.....））
	 * @return
	 */
	@Override
	public double getAverageScoreByObjectIdAndScoreRuleIdAndScorerIds(String objectId, int scoreRuleId,
			String scorerIdsStr) throws Exception {
		String sql = "select  AVG(SCORERESULT) as averageScore from TB_SCORERESULT" + " where OBJECTID = " + objectId
				+ " and SCORERULEID = " + scoreRuleId + " and STATUS = " + 2 + " and SCORERID in " + scorerIdsStr;

		RowSet rs = dbAccess.executeQuery(sql);
		double averageScore = 0;
		if (rs != null && rs.next()) {
			averageScore = rs.getDouble("averageScore");
		}

		return averageScore;
	}

	@Override
	public double getAverageScoreByObjectIdAndScoreRuleIdAndScorerIdsX(String objectId, int scoreRuleId,
			String scorerIdsStr, int scoreType) throws Exception {
		String sql = "select  AVG(SCORERESULT) as averageScore from TB_SCORERESULT" + " where OBJECTID = " + objectId
				+ " and SCORERULEID = " + scoreRuleId + " and STATUS = " + 2 + " and SCORERID in " + scorerIdsStr;

		RowSet rs = dbAccess.executeQuery(sql);
		double averageScore = 0;
		if (rs != null && rs.next()) {
			averageScore = rs.getDouble("averageScore");
		}

		return averageScore;
	}

	/**
	 * 获取某考核对象 被 某类评价主体、以 某种考核规则 评价，并处于 提交 状态的评价结果
	 * 
	 * @param teacherId
	 *            对象id
	 * @param scoreRuleId
	 *            规则id
	 * @param scorerIdsStr
	 *            该类评价主体包含的所有主体ID（格式：（' scorerId',' scorerId',' scorerId'.....））
	 * @return
	 */
	@Override
	public List<ScoreResultVO> getScoreResultByObjectIdAndScoreRuleIdAndScorerIds(String objectId, int scoreRuleId,
			String scorerIdsStr) throws Exception {

		ArrayList<ScoreResultVO> srVos = new ArrayList<>();
		String sql = "select * from TB_SCORERESULT" + " where OBJECTID = " + objectId + " and SCORERULEID = "
				+ scoreRuleId + " and STATUS = " + 2 + " and SCORERID in " + scorerIdsStr;

		RowSet rs = dbAccess.executeQuery(sql);

		while (rs != null && rs.next()) {
			ScoreResultVO srVo = new ScoreResultVO();
			// bean:
			srVo.setId(rs.getInt("id"));
			srVo.setObjectId(rs.getString("OBJECTID"));
			srVo.setObjectType(rs.getInt("OBJECTTYPE"));
			srVo.setScorerId(rs.getString("SCORERID"));
			srVo.setScorerType(rs.getInt("SCORERTYPE"));
			srVo.setScoreRuleId(rs.getInt("SCORERULEID"));
			srVo.setStatus(rs.getInt("STATUS"));
			srVo.setScoreResult(rs.getDouble("SCORERESULT"));
			srVo.setCommitDate(rs.getString("COMMITDATE"));
			srVo.setRemark(rs.getString("REMARK"));

			srVos.add(srVo);
		}

		return srVos;
	}

	/**
	 * @param objectId
	 *            String 被考核对象的id
	 * @param objectType
	 *            int 被考核对象的考核类型
	 * @param scorerId
	 *            String 评价主体的评价类型
	 * @return tempScore 查询的分数
	 * @throws Exception
	 */
	@Override
	public List<UserVO> getScorerByObjectType(String objectId, String objectType, String scorerType) throws Exception {
		String sqlUser = "select * from TB_USER where USERID in (select SCORERID from TB_SCORERESULT where OBJECTID = '"
				+ objectId + "' and OBJECTTYPE = '" + objectType + "' and SCORERTYPE = '" + scorerType
				+ "' and status='2' )";
		RowSet rs = dbAccess.executeQuery(sqlUser);
		List<UserVO> list = new ArrayList<UserVO>();
		while (rs != null && rs.next()) {
			UserVO userVO = new UserVO();
			userVO.setUserId(rs.getInt("USERID"));
			userVO.setTeacherId(rs.getString("TEACHERID"));
			userVO.setUserName(rs.getString("USERNAME"));
			userVO.setPassword(rs.getString("PASSWORD"));
			userVO.setUserCharacter(rs.getInt("USERCHARACTER"));
			userVO.setRoleId(rs.getInt("ROLEID"));
			userVO.setDeptId(rs.getInt("DEPTID"));
			userVO.setIsProfessional(rs.getInt("ISPROFESSIONAL"));
			userVO.setProdeptId1(rs.getInt("PRODEPTID1"));
			userVO.setProdeptId2(rs.getInt("PRODEPTID2"));
			userVO.setEvalPosition(rs.getInt("EVALPOSITION"));
			userVO.setSex(rs.getString("SEX"));
			userVO.setBirthday(rs.getString("BIRTHDAY"));
			userVO.setTechnicaltitle(rs.getString("TECHNICALTITLE"));
			userVO.setJob(rs.getString("JOB"));
			userVO.setDegree(rs.getString("DEGREE"));
			userVO.setPresentPosition(rs.getString("PRESENTPOSITION"));
			userVO.setPositionKind(rs.getString("POSITIONKIND"));
			userVO.setUserLevel(rs.getString("USERLEVEL"));
			userVO.setRemark(rs.getString("REMARK"));
			list.add(userVO);
		}
		return list;
	}

	/**
	 * @param objectId
	 *            String 被考核对象的id
	 * @param objectType
	 *            int 被考核对象的考核类型
	 * @param scorerId
	 *            String 评价主体的评价类型
	 * @return tempScore 查询的分数
	 * @throws Exception
	 */
	public double getScore(String objectId, int objectType, String scorerId) throws Exception {
		String sql = "select scoreresult from TB_SCORERESULT where OBJECTID='" + objectId + "' and OBJECTTYPE='"
				+ objectType + "' and SCORERID='" + scorerId + "'";
		RowSet rs = dbAccess.executeQuery(sql);
		double tempScore = 0;
		if (rs != null && rs.next()) {
			tempScore = rs.getDouble("scoreresult");
		}
		return tempScore;
	}
}
