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
 * @author ��ΰ��
 * @version 0329-01:18
 * @see �����ֽ������ķ����ӿڵ�ʵ��
 *
 */
public class ScoreResultDAOImpl implements ScoreResultDAO {
	private DbAccess dbAccess = new DbAccess();

	/**
	 * @see ��ȡָ�����۽��
	 * @param scoreResultId
	 *            ���۽�������id
	 * @return ָ�����۽��Vo
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

			// ������ʾ��Ҫ��
			srVo.setObjectName(rs.getString("ObjectName"));
			srVo.setObjectDeptName(rs.getString("ObjectDeptName"));
			srVo.setScorerName(rs.getString("ScorerName"));
			srVo.setScorerDeptName(rs.getString("ScorerDeptName"));
			srVo.setScoreRuleTable(rs.getString("ScoreRuleTable"));
		}

		return srVo;
	}

	/**
	 * ��ȡĳ��ָ����ָ���������塢���򣩵����۽��
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

			// ������ʾ��Ҫ��
			srVo.setObjectName(rs.getString("ObjectName"));
			srVo.setObjectDeptName(rs.getString("ObjectDeptName"));
			srVo.setScorerName(rs.getString("ScorerName"));
			srVo.setScorerDeptName(rs.getString("ScorerDeptName"));
			srVo.setScoreRuleTable(rs.getString("ScoreRuleTable"));
		}

		return srVo;
	}

	// -----------------------------�����ǽ�����ʾר��-------------------------------//
	/**
	 * @see ��ҳ�� ��ȡĳ�û�����Ӧ�����������۽����
	 * @param ScorerId
	 *            �������壨�����ˡ��̹���Id
	 * @return �������� ���е� Ӧ������ ���������۽������
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
	 * @see ��ҳ�� ��ȡĳ�û� ĳ��״̬ �����۽����
	 * @param ScorerId
	 *            �������壨�����ˡ��̹���Id
	 * @param status
	 *            ״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @return һ������Ҫ��״̬�ģ��������۽������
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

	// -----------------------------���·����б�--------------------------------//

	/**
	 * @see ��ȡĳ�û�����Ӧ�����������۽����
	 * @param ScorerId
	 *            �������壨�����ˡ��̹���Id
	 * @return �������� ���е� Ӧ������ ���������۽������
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
	 * @see ��ȡĳ�û�����Ӧ���������۵����۽����
	 * @param ObjectId
	 *            ���۶��󣨱����ˡ��̹���Id
	 * @return �������� ���е� Ӧ������ ���������۽������
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
	 * @see ��ȡĳ�û� ĳ��״̬ �����۽����
	 * @param ScorerId
	 *            �������壨�����ˡ��̹���Id
	 * @param status
	 *            ״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @return һ������Ҫ��״̬�ģ��������۽������
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
	 * @see ��ȡĳ�û� ĳ��״̬ Ӧ���������۵����۽����
	 * @param ObjectId
	 *            ���۶��󣨱����ˡ��̹���Id
	 * @param status
	 *            ״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @return һ������Ҫ��״̬�ģ��������۽������
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
	 * @see ��ȡĳ�û� ĳ��״̬ Ӧ���������۵����۽����
	 * @param ObjectI
	 *            ���۶��󣨱����ˡ��̹���Id
	 * @param scoreRuleId
	 *            ��������Id
	 * @param status
	 *            ״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ��3:����
	 * @return һ������Ҫ��״̬�ģ��������۽������
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
	 * ��ȡ���п��˶�������飨����ObjectId���ظ���
	 * 
	 * @return ���п��˶����list
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
	 * ���Ƕ�ȡ��ʱ����������������ص�Vo���û���ֵ
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

			// ������ʾ��Ҫ��
			srVo.setObjectName(rs.getString("ObjectName"));
			srVo.setObjectDeptName(rs.getString("ObjectDeptName"));
			srVo.setScorerName(rs.getString("ScorerName"));
			srVo.setScorerDeptName(rs.getString("ScorerDeptName"));
			srVo.setScoreRuleTable(rs.getString("ScoreRuleTable"));
			srVos.add(srVo);
		}

		return srVos;
	}

	// ---------------------------------�����޸����------------------------------------------//

	/**
	 * �����ݿ�ġ����ֽ�������в���һ�������ֹ�ϵ����״̬Ϊ��0���ġ����ֽ������
	 * 
	 * @param scoreResultVo
	 *            ״̬Ϊ��0���ġ����ֽ����
	 * @return ��������
	 * @throws Exception
	 */
	public int insertScoreResult(ScoreResultVO srVo) throws Exception {

		String sql = "insert into TB_SCORERESULT(id, OBJECTID, OBJECTTYPE, SCORERID, SCORERTYPE, "
				+ "SCORERULEID, STATUS, SCORERESULT, COMMITDATE, REMARK)" + " values("
				+ SqlUtil.getInstance().getSeqId() + ", " + StringUtil.fieldValue(srVo.getObjectId()) + ", "
				+ srVo.getObjectType() + ", " + StringUtil.fieldValue(srVo.getScorerId()) + ", " + srVo.getScorerType()
				+ ", " + srVo.getScoreRuleId() + ", " + srVo.getStatus() + ", " + srVo.getScoreResult() + ", "
				+ "'  ��   ��    ��', " + StringUtil.fieldValue(srVo.getRemark()) + ")";

		return dbAccess.executeUpdate(sql);
	}

	/**
	 * �����ݿ�ġ����ֽ�������в���100�������ֹ�ϵ����״̬Ϊ��0���ġ����ֽ������
	 * 
	 * @param scoreResultVo
	 *            ״̬Ϊ��0���ġ����ֽ����
	 * @return ��������
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
					+ srVo.getScoreResult() + ", " + "'  ��   ��    ��', " + StringUtil.fieldValue(srVo.getRemark()) + ")";

			sqlList.add(sql);
		}
		if (sqlList.size() > 0) {
			return dbAccess.executeBatchUpdate(sqlList);
		} else {
			return 0;
		}
	}

	/**
	 * �޸�ĳ��ָ���ģ�ָ��id�������۽��
	 * 
	 * @param scoreResultVo
	 *            �ں�id��Ҫ�޸ĵ���Ϣ
	 * @return ��Ӱ������
	 */
	public int updateScoreResult(ScoreResultVO srVo) throws Exception {
		String sql = "update TB_SCORERESULT set " + " SCORERULEID = " + srVo.getScoreRuleId() + " where id = "
				+ srVo.getId();

		return dbAccess.executeUpdate(sql);
	}

	/**
	 * @see �ı�ĳ�����۽����״̬
	 * @param scoreResultId
	 *            ָ�����۽����Id
	 * @param status
	 *            Ҫ�ı������״̬��0:δ���ۡ�1�����棬δ�ύ��2:���ύ
	 * @param scoreResult
	 *            �ܷ�
	 * @param commitDate
	 *            �ύ���ڣ�YYYY��MM��DD�գ�
	 * @return ���ؽ����˲���������
	 */
	@Override
	public int changeScoreResultStatusByID(int scoreResultId, int status, double scoreResult, String commitDate)
			throws Exception {
		String sql = "update TB_SCORERESULT set STATUS = " + status + ", SCORERESULT = " + scoreResult
				+ ", COMMITDATE = " + StringUtil.fieldValue(commitDate) + " where id = " + scoreResultId;
		return dbAccess.executeUpdate(sql);

	}

	/**
	 * ��ȡĳ�� ���˶��� �� ָ���������� �� ָ������ �����ض�״̬�ؼ�¼��
	 * 
	 * @param ObjectId
	 *            ָ�����˶���
	 * @param scorerIdsStr
	 *            �������������������������ID����ʽ����' scorerId',' scorerId',' scorerId'.....����
	 * @param scoreRuleId
	 *            ָ������
	 * @param status
	 *            ָ��״̬ ��ע�⣺3�����ȡȫ������״̬��
	 * @return ��¼��
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
	 * ��ȡĳ�� ���˶��� �� ָ������ �����ض�״̬�ؼ�¼��
	 * 
	 * @param ObjectId
	 *            ָ�����˶���
	 * @param scoreRuleId
	 *            ָ������
	 * @param status
	 *            ָ��״̬ ��ע�⣺3�����ȡȫ������״̬��
	 * @return ��¼��
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

	// --------------------------------���£��������ר��-------------------------------//
	/**
	 * ��ȡĳ���˶��� �� ĳ���������塢�� ĳ�ֿ��˹��� ���ۣ������� �ύ ״̬������ �� ƽ�����
	 * 
	 * @param teacherId
	 *            ����id
	 * @param scoreRuleId
	 *            ����id
	 * @param scorerIdsStr
	 *            �������������������������ID����ʽ����'scorerId',' scorerId',' scorerId'.....����
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
	 * ��ȡĳ���˶��� �� ĳ���������塢�� ĳ�ֿ��˹��� ���ۣ������� �ύ ״̬�����۽��
	 * 
	 * @param teacherId
	 *            ����id
	 * @param scoreRuleId
	 *            ����id
	 * @param scorerIdsStr
	 *            �������������������������ID����ʽ����' scorerId',' scorerId',' scorerId'.....����
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
	 *            String �����˶����id
	 * @param objectType
	 *            int �����˶���Ŀ�������
	 * @param scorerId
	 *            String �����������������
	 * @return tempScore ��ѯ�ķ���
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
	 *            String �����˶����id
	 * @param objectType
	 *            int �����˶���Ŀ�������
	 * @param scorerId
	 *            String �����������������
	 * @return tempScore ��ѯ�ķ���
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
