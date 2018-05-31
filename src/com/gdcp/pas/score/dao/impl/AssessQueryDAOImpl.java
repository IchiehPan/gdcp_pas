package com.gdcp.pas.score.dao.impl;

/**
 * @author 张俊杰  2015-03-29 
 * @see 提供考核查询操作
 */
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.Page;
import com.gdcp.common.SqlUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.score.dao.AssessQueryDAO;
import com.gdcp.pas.score.vo.AssessQueryVO;

public class AssessQueryDAOImpl implements AssessQueryDAO {
	private DbAccess dbAccess = new DbAccess();

	public DbAccess getDbAccess() {
		return dbAccess;
	}

	public void setDbAccess(DbAccess dbAccess) {
		this.dbAccess = dbAccess;
	}

	/**
	 * @see 查询所用考核对象信息 关联表有： TB_USER中获取教职工ID和用户名
	 */
	public List<AssessQueryVO> queryPage(Page page, AssessQueryVO vo) throws Exception {
		String countSql = " select count(*) from ( select distinct * from  (select a.OBJECTID,a.SCORERULEID,b.DEPTNAME,c.USERNAME,d.RULEYEAR,d.RULENAME";
		String querySql = "  select distinct * from  (select a.OBJECTID,a.SCORERULEID,b.DEPTNAME,c.USERNAME,d.RULEYEAR,d.RULENAME";
		querySql = querySql + " from TB_SCORERESULT a,TB_DEPT b,TB_USER c,TB_SCORERULE d ";
		querySql = querySql + " where c.DEPTID=b.DEPTID and c.USERID=a.OBJECTID and a.SCORERULEID=d.SCORERULEID";

		countSql = countSql + " from TB_SCORERESULT a,TB_DEPT b,TB_USER c,TB_SCORERULE d ";
		countSql = countSql + " where c.DEPTID=b.DEPTID and c.USERID=a.OBJECTID and a.SCORERULEID=d.SCORERULEID";
		// 部门ID查询
		if (vo.getDeptId() != null && !vo.getDeptId().equals("-1") && vo.getObjectTypeId().equals("0")) {
			querySql = querySql + " and b.DEPTID=" + vo.getDeptId() + "";
			countSql = countSql + " and b.DEPTID=" + vo.getDeptId() + "";
		}
		// 对象类型ID查询
		if (vo.getObjectTypeId() != null && !vo.getObjectTypeId().equals("0") && vo.getDeptId().equals("-1")) {
			querySql = querySql + " and a.OBJECTTYPE='" + vo.getObjectTypeId() + "'";
			countSql = countSql + " and a.OBJECTTYPE='" + vo.getObjectTypeId() + "'";
		}
		// 对象类型ID和部门ID查询
		if (vo.getDeptId() != null && vo.getObjectTypeId() != null && !vo.getObjectTypeId().equals("0")
				&& !vo.getDeptId().equals("-1")) {
			querySql = querySql + " and a.OBJECTTYPE='" + vo.getObjectTypeId() + "' and b.DEPTID=" + vo.getDeptId()
					+ "";
			countSql = countSql + " and a.OBJECTTYPE='" + vo.getObjectTypeId() + "' and b.DEPTID=" + vo.getDeptId()
					+ "";
		}
		querySql = querySql + ") t";
		countSql = countSql + ") t) t1";
		RowSet rs = dbAccess.executeQuery(countSql);
		if (rs != null && rs.next()) {
			page.setTotal(rs.getInt(1));
		}

		List<AssessQueryVO> list = new ArrayList<>();
		querySql = SqlUtil.getSQLServerPageSQL(page.getPageNo(), page.getPageSize(), "objectId", querySql);
		rs = dbAccess.executeQuery(querySql);
		while (rs != null && rs.next()) {
			AssessQueryVO aqVO = new AssessQueryVO();
			aqVO.setTeacherID(rs.getString("OBJECTID"));
			aqVO.setUserName(rs.getString("USERNAME"));
			aqVO.setRuleYear(rs.getInt("RULEYEAR"));
			aqVO.setRuleName(rs.getString("RULENAME"));
			aqVO.setScoreRuleId(rs.getInt("SCORERULEID"));
			aqVO.setDeptName(rs.getString("DEPTNAME"));

			// cwz:
			getCommitRate(aqVO);
			aqVO.setScore(0);

			list.add(aqVO);
		}
		return list;
	}

	/**
	 * 获取提交进度（比率）
	 * 
	 * @throws Exception
	 */
	private void getCommitRate(AssessQueryVO aqVO) throws Exception {
		String sql = "select distinct a.OBJECTID, b.num as NotSubmittdNum, c.num as AllResultNum"
				+ " from TB_SCORERESULT a,"
				+ " (select OBJECTID,COUNT(OBJECTID) as num from TB_SCORERESULT where STATUS in(0,1) and OBJECTID="
				+ aqVO.getTeacherID() + " group by OBJECTID) b,"
				+ " (select OBJECTID,COUNT(OBJECTID) as num from TB_SCORERESULT where OBJECTID= " + aqVO.getTeacherID()
				+ " group by OBJECTID) c" + " where a.OBJECTID = b.OBJECTID and a.OBJECTID=c.OBJECTID and a.OBJECTID= "
				+ aqVO.getTeacherID();

		RowSet rs = dbAccess.executeQuery(sql);

		if (rs != null && rs.next()) {
			aqVO.setSubmittdNum(rs.getInt("AllResultNum") - rs.getInt("NotSubmittdNum"));
			aqVO.setAllResultNum(rs.getInt("AllResultNum"));
		} else {
			sql = " select OBJECTID,COUNT(OBJECTID) as AllResultNum " + "from TB_SCORERESULT where OBJECTID="
					+ aqVO.getTeacherID() + " group by OBJECTID";
			rs = dbAccess.executeQuery(sql);
			if (rs != null && rs.next()) {
				aqVO.setSubmittdNum(rs.getInt("AllResultNum"));
				aqVO.setAllResultNum(rs.getInt("AllResultNum"));
			}
		}
	}

	/**
	 * @see 左关联TB_AVERAGESCORE,查询考核对象得分
	 */
	@Override
	public AssessQueryVO queryScore(String objectId, int scoreRuleId) throws Exception {
		String sql = "select distinct * from  (select a.OBJECTID,a.SCORERULEID,b.DEPTNAME,c.USERNAME,d.RULEYEAR,d.RULENAME";
		sql = sql + " from TB_SCORERESULT a,TB_DEPT b,TB_USER c,TB_SCORERULE d";
		sql = sql + " where c.DEPTID=b.DEPTID and c.USERID=a.OBJECTID and a.SCORERULEID=d.SCORERULEID and c.USERID="
				+ objectId + "  and a.SCORERULEID=" + scoreRuleId + ") t";
		sql = sql + " left join TB_AVERAGESCORE e on e.SCORERTYPE=0 and e.OBJECTID = " + objectId;
		RowSet rs = dbAccess.executeQuery(sql);
		while (rs != null && rs.next()) {
			AssessQueryVO aqVO = new AssessQueryVO();
			aqVO.setTeacherID(rs.getString("OBJECTID"));
			aqVO.setUserName(rs.getString("USERNAME"));
			aqVO.setRuleYear(rs.getInt("RULEYEAR"));
			aqVO.setRuleName(rs.getString("RULENAME"));
			aqVO.setScoreRuleId(rs.getInt("SCORERULEID"));
			aqVO.setDeptName(rs.getString("DEPTNAME"));
			// cwz:
			getCommitRate(aqVO);
			// 得分
			String score = String.valueOf(rs.getFloat("AVERAGESCORE"));
			if (score == null) {
				score = 0 + ""; // 如果成绩为NULL 则将成绩赋值为0
				double score1 = Double.parseDouble(score);
				aqVO.setScore(score1);
			} else {
				aqVO.setScore(rs.getFloat("AVERAGESCORE"));
			}
			return aqVO;
		}
		return null;
	}

}
