package com.gdcp.pas.score.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.SqlUtil;
import com.gdcp.common.StringUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.score.config.ExtendCodeConfig;
import com.gdcp.pas.score.dao.AverageScoreDAO;
import com.gdcp.pas.score.vo.AverageScoreVO;

/**
 * @author 陈伟镇
 * 
 */
public class AverageScoreDAOImpl implements AverageScoreDAO {
	private DbAccess dbAccess = new DbAccess();

	/**
	 * 获取所有被考核对象的所有最终成绩 按部门排序
	 */
	public List<AverageScoreVO> getAllWeightScore() throws Exception {

		String sql = "select a.*, b.USERNAME as OBJECTNAME, c.DEPTNAME as OBJECTDEPTNAME"
				+ " from TB_AVERAGESCORE a, TB_USER b, TB_DEPT c"
				+ " where a.OBJECTID = b.TEACHERID and b.DEPTID = c.DEPTID" + " and a.SCORERTYPE = 0"
				+ " order by c.DEPTID";

		RowSet rs = dbAccess.executeQuery(sql);

		ArrayList<AverageScoreVO> asVos = new ArrayList<>();
		while (rs != null && rs.next()) {
			AverageScoreVO asVo = new AverageScoreVO();
			setBasicMessage(asVo, rs);
			asVo.setObjectName(rs.getString("OBJECTNAME"));
			asVo.setObjectDeptName(rs.getString("OBJECTDEPTNAME"));
			asVos.add(asVo);
		}

		return asVos;
	}

	/**
	 * 获取某个考核对象的最总成绩
	 * 
	 * @param objertId
	 * @return 一个AverageScoreVO（）
	 * @throws Exception
	 */
	@Override
	public AverageScoreVO getWeightScoreByObjectId(String objertId) throws Exception {

		String sql = "select * from TB_AVERAGESCORE" + " where OBJECTID = " + StringUtil.fieldValue(objertId)
				+ " and SCORERTYPE = 0";
		RowSet rs = dbAccess.executeQuery(sql);

		AverageScoreVO asVo = new AverageScoreVO();
		if (rs != null && rs.next()) {
			setBasicMessage(asVo, rs);
		} else {
			return null;
		}

		return asVo;
	}

	/**
	 * 获取某个被某个考核表考核的 所有对象的 最总成绩的 ArrayList
	 * 
	 * @param scoerBuleId
	 *            指定考核表
	 * @return ArrayList<AverageScoreVO>
	 */
	@Override
	public List<AverageScoreVO> getWeightScoreByScoreBuleId(int scoerBuleId) throws Exception {

		String sql = "select * from TB_AVERAGESCORE" + " where SCORERULEID = " + scoerBuleId + " and SCORERTYPE = 0";
		RowSet rs = dbAccess.executeQuery(sql);

		ArrayList<AverageScoreVO> asVos = new ArrayList<>();
		while (rs != null && rs.next()) {
			AverageScoreVO asVo = new AverageScoreVO();
			setBasicMessage(asVo, rs);
			asVos.add(asVo);
		}

		return asVos;
	}

	/**
	 * 获取某个被某个考核表考核的 某个部门的 所有对象的 最终成绩的 ArrayList
	 * 
	 * @param scoerBuleId
	 *            指定考核表
	 * @param deptId
	 *            指定部门
	 * @return
	 */
	@Override
	public List<AverageScoreVO> getWeightScoreByScoreBuleIdAndDeptId(int scoerBuleId, int deptId) throws Exception {

		String sql = "select a.* from TB_AVERAGESCORE a, TB_USER b" + " where a.SCORERULEID = " + scoerBuleId
				+ " and a.OBJECTID = b.TEACHERID" + " and b.DEPTID = " + deptId + " and a.SCORERTYPE = 0";
		RowSet rs = dbAccess.executeQuery(sql);

		ArrayList<AverageScoreVO> asVos = new ArrayList<>();
		while (rs != null && rs.next()) {
			AverageScoreVO asVo = new AverageScoreVO();
			setBasicMessage(asVo, rs);
			asVos.add(asVo);
		}

		return asVos;
	}

	/**
	 * 获取 某个考核对象 作为 某种考核对象类型 而被 某种规则 时，被哪些主体类型评价
	 * 
	 * @param objectId
	 *            指定考核对象
	 * @param objectType
	 *            指定考核对象类型
	 * @param scoreRuleId
	 *            指定规则
	 * @return
	 */
	@Override
	public List<AverageScoreVO> getAllAverageScoreByObjectIdAndObjectTypeAndScoreRuleID(String objectId, int objectType,
			int scoreRuleId) throws Exception {

		String sql = "select * from TB_AVERAGESCORE" + " where OBJECTID = " + StringUtil.fieldValue(objectId)
				+ " and OBJECTTYPE = " + objectType + " and SCORERTYPE <> " + 0 + " and SCORERULEID = " + scoreRuleId;
		RowSet rs = dbAccess.executeQuery(sql);

		ArrayList<AverageScoreVO> asVos = new ArrayList<>();
		while (rs != null && rs.next()) {
			AverageScoreVO asVo = new AverageScoreVO();
			setBasicMessage(asVo, rs);
			asVos.add(asVo);
		}

		return asVos;

	}

	/**
	 * 获取所有部门领导的考核结果(包括平均和加权后成绩)
	 * 
	 * @return
	 */
	public List<AverageScoreVO> getAllMiddleWeightScore() throws Exception {
		String sql = "select b.USERNAME as objectName, a.*" + " from TB_AVERAGESCORE a, TB_USER b"
				+ " where b.USERCHARACTER in (100212,100213,100214,100215)" + " and a.OBJECTID = b.TEACHERID"
				+ " and a.SCORERTYPE = 0" + " order by a.AVERAGESCORE desc";
		RowSet rs = dbAccess.executeQuery(sql);

		ArrayList<AverageScoreVO> asVos = new ArrayList<>();
		while (rs != null && rs.next()) {
			AverageScoreVO asVo = new AverageScoreVO();
			setBasicMessage(asVo, rs);
			asVo.setObjectName(rs.getString("objectName"));
			asVos.add(asVo);
		}
		return asVos;
	}

	/**
	 * 读取方法调用，用于设置返回值（VO）的基本变量
	 * 
	 * @param asVo
	 * @param rs
	 * @throws SQLException
	 */
	private void setBasicMessage(AverageScoreVO asVo, RowSet rs) throws SQLException {
		asVo.setAverageId(rs.getInt("AVERAGEID"));
		asVo.setObjectId(rs.getString("OBJECTID"));
		asVo.setObjectType(rs.getInt("OBJECTTYPE"));
		asVo.setSocrerType(rs.getInt("SCORERTYPE"));
		asVo.setScoreRuleId(rs.getInt("SCORERULEID"));
		asVo.setAverageScore(rs.getFloat("AVERAGESCORE"));
		asVo.setActualCommit(rs.getInt("ACTUALCOMMIT"));
		asVo.setShouldCommit(rs.getInt("SHOULDCOMMIT"));
		asVo.setRemark(rs.getString("REMARK"));
	}

	// /**
	// * 判断某个对象作为某种对象类型的评分是否已经结束
	// * @param objectId 指定对象
	// * @param objectType 指定对象类型
	// * @return 是否
	// */
	// @Override
	// public boolean isFinishByObjectIdAndObjectType(String objectId, int
	// objectType) throws Exception {
	//
	// ArrayList<ExtendCode> ecs = (ArrayList<ExtendCode>)
	// ExtendCodeUtil.getInstance().getExtendCodeListByType("weight");
	//
	// int scorerTyoeCount = 0;
	// for(int i=0; i<ecs.size(); i++){
	// if(ecs.get(i).getNumKey().startsWith(objectType+"")){
	// scorerTyoeCount++;
	// }
	// }
	//
	// String sql = "select count(*) as sum from TB_AVERAGESCORE"
	// + " where OBJECTID = " + StringUtil.fieldValue(objectId)
	// + " and PBJECTTYPE = " + objectType;
	//
	// RowSet rs = dbAccess.executeQuery(sql);
	//
	// if(rs!=null && rs.next()){
	// if(scorerTyoeCount <= rs.getInt("sum")){
	// return true;
	// }
	// }
	//
	// return false;
	// }

	/**
	 * 判断分数记录是否已经存在
	 * 
	 * @param averageScoreVo
	 * @return 是否
	 */
	@Override
	public boolean isExistScore(AverageScoreVO averageScoreVo) throws Exception {

		String sql = "select * from TB_AVERAGESCORE" + " where OBJECTID = "
				+ StringUtil.fieldValue(averageScoreVo.getObjectId()) + " and OBJECTTYPE = "
				+ averageScoreVo.getObjectType() + " and SCORERTYPE = " + averageScoreVo.getSocrerType()
				+ " and SCORERULEID = " + averageScoreVo.getScoreRuleId();
		RowSet rs = dbAccess.executeQuery(sql);

		if (rs != null && rs.next()) {
			return true;
		}
		return false;
	}

	/**
	 * 插入一条成绩
	 * 
	 * @param averageScoreVo
	 * @return
	 */
	@Override
	public int insertScore(AverageScoreVO averageScoreVo) throws Exception {

		String sql = "insert into TB_AVERAGESCORE(AVERAGEID, OBJECTID, OBJECTTYPE, SCORERTYPE, SCORERULEID, AVERAGESCORE, ACTUALCOMMIT, SHOULDCOMMIT)"
				+ "values(" + SqlUtil.getInstance().getSeqId() + ", "
				+ StringUtil.fieldValue(averageScoreVo.getObjectId()) + ", " + averageScoreVo.getObjectType() + ", "
				+ averageScoreVo.getSocrerType() + ", " + averageScoreVo.getScoreRuleId() + ", "
				+ averageScoreVo.getAverageScore() + ", " + averageScoreVo.getActualCommit() + ", "
				+ averageScoreVo.getShouldCommit() + ")";
		return dbAccess.executeUpdate(sql);

	}

	/**
	 * 修改一条成绩
	 * 
	 * @param averageScoreVo
	 * @return
	 */
	@Override
	public int updateScore(AverageScoreVO averageScoreVo) throws Exception {
		String sql = "update TB_AVERAGESCORE" + " set AVERAGESCORE = " + averageScoreVo.getAverageScore() + ","
				+ " ACTUALCOMMIT = " + averageScoreVo.getActualCommit() + "," + " SHOULDCOMMIT = "
				+ averageScoreVo.getShouldCommit() + " where OBJECTID = "
				+ StringUtil.fieldValue(averageScoreVo.getObjectId()) + " and OBJECTTYPE = "
				+ averageScoreVo.getObjectType() + " and SCORERTYPE = " + averageScoreVo.getSocrerType()
				+ " and SCORERULEID = " + averageScoreVo.getScoreRuleId();
		return dbAccess.executeUpdate(sql);
	}

	/**
	 * 计算教学部门总分
	 */
	@Override
	public void TeachingDepartmentAggregateScore() throws Exception {
		// sql: 得到教学部门定性分和定量分 从‘TB_AVERAGESCORE’和‘TB_DEPTQUANTITATIONSCORE’表中
		String sql = "select  TB_AVERAGESCORE.AVERAGEID,  TB_AVERAGESCORE.OBJECTID,TB_AVERAGESCORE.AVERAGESCORE, TB_DEPTQUANTITATIONSCORE.SCORE "
				+ " from TB_AVERAGESCORE "
				+ " join TB_DEPTQUANTITATIONSCORE on TB_AVERAGESCORE.OBJECTID = TB_DEPTQUANTITATIONSCORE.DEPTID  "
				+ " join TB_DEPT on TB_AVERAGESCORE.OBJECTID = TB_DEPT.DEPTID  "
				+ " where   SCORERTYPE='0' and TB_DEPT.DEPTTYPE = '1'";
		RowSet rs = dbAccess.executeQuery(sql);
		while (rs.next()) {
			int addScore = 0, deduction = 0, encourage = 0;
			// 查询加分
			String addScoreSql = "select MAX(SCORE) from TB_ADDANDSUB  where OBJECTID='" + rs.getInt("OBJECTID")
					+ "' and STATUS='0'";
			RowSet rs1 = dbAccess.executeQuery(addScoreSql);
			if (rs1.next()) {
				addScore = rs1.getInt(1);
			}
			// 查询减分
			String deductionScoreSql = "select SUM (SCORE) from TB_ADDANDSUB  where OBJECTID='" + rs.getInt("OBJECTID")
					+ "' and STATUS='1'";
			RowSet rs2 = dbAccess.executeQuery(deductionScoreSql);
			if (rs2.next()) {
				deduction = rs2.getInt(1);
			}
			// 查询鼓励分
			String encourageScoreSql = "select SUM(SCORE) from TB_ADDANDSUB  where OBJECTID='" + rs.getInt("OBJECTID")
					+ "' and STATUS='2'";
			RowSet rs3 = dbAccess.executeQuery(encourageScoreSql);
			if (rs3.next()) {
				encourage = rs3.getInt(1);
			}
			if (encourage > 20) {
				encourage = 20;
			}
			// 计算总分
			float TeachingDepartmentAggregateScore = (float) (rs.getFloat("AVERAGESCORE") + rs.getFloat("SCORE")
					+ addScore - deduction + encourage); // 该教学部门的总分，定性分+定量分+加减分=总分
			String inputSql = "update TB_AVERAGESCORE set AVERAGESCORE='" + TeachingDepartmentAggregateScore
					+ "' where AVERAGEID = '" + rs.getInt("AVERAGEID") + "'";
			dbAccess.executeUpdate(inputSql);
		}

	}

	/**
	 * 计算非教学部门总分
	 */
	@Override
	public void NotTeachingDepartmentAggregateScore() throws Exception {
		// sql: 得到非教学部门定性分 从‘TB_AVERAGESCORE’表中
		String sql = "select  TB_AVERAGESCORE.AVERAGEID, TB_AVERAGESCORE.OBJECTID,TB_AVERAGESCORE.AVERAGESCORE "
				+ " from TB_AVERAGESCORE " + " join TB_DEPT on TB_AVERAGESCORE.OBJECTID = TB_DEPT.DEPTID  "
				+ " where   SCORERTYPE='0' and TB_DEPT.DEPTTYPE = '2'";
		RowSet rs = dbAccess.executeQuery(sql);
		while (rs.next()) {
			int addScore = 0, deduction = 0, encourage = 0;
			// 查询加分
			String addScoreSql = "select MAX(SCORE) from TB_ADDANDSUB  where OBJECTID='" + rs.getInt("OBJECTID")
					+ "' and STATUS='0'";
			RowSet rs1 = dbAccess.executeQuery(addScoreSql);
			if (rs1.next()) {
				addScore = rs1.getInt(1);
			}
			// 查询减分
			String deductionScoreSql = "select SUM(SCORE) from TB_ADDANDSUB  where OBJECTID='" + rs.getInt("OBJECTID")
					+ "' and STATUS='1'";
			RowSet rs2 = dbAccess.executeQuery(deductionScoreSql);
			if (rs2.next()) {
				deduction = rs2.getInt(1);
			}
			// 查询鼓励分
			String encourageScoreSql = "select SUM(SCORE) from TB_ADDANDSUB  where OBJECTID='" + rs.getInt("OBJECTID")
					+ "' and STATUS='2'";
			RowSet rs3 = dbAccess.executeQuery(encourageScoreSql);
			if (rs3.next()) {
				encourage = rs3.getInt(1);
			}
			if (encourage > 10) {
				encourage = 10;
			}
			// 计算总分
			float TeachingDepartmentAggregateScore = rs.getFloat("AVERAGESCORE") + addScore - deduction + encourage; // 该非教学部门的总分，定性分+加减分=总分
			if (TeachingDepartmentAggregateScore > 100) {
				TeachingDepartmentAggregateScore = 100;
			}
			String inputSql = "update TB_AVERAGESCORE set AVERAGESCORE='" + TeachingDepartmentAggregateScore
					+ "' where AVERAGEID = '" + rs.getInt("AVERAGEID") + "'";
			dbAccess.executeUpdate(inputSql);
		}

	}

	/**
	 * 计算内设机构正职领导的个人总分
	 */
	@Override
	public void PrincipalAggregateScore() throws Exception {
		/*--先在 TB_AVERAGESCORE中所有内设机构领导的个人分数
		  --再在user表中找到所有正职领导
		  --最后联合查询到所有内设机构正职领导的个人分数*/
		String sql1 = "select TB_AVERAGESCORE.AVERAGEID,TB_AVERAGESCORE.OBJECTID,TB_AVERAGESCORE.AVERAGESCORE ,TB_USER.DEPTID "
				+ "from  TB_AVERAGESCORE,TB_USER  " + "where (OBJECTTYPE = '100114' or OBJECTTYPE ='100115') "
				+ "and SCORERTYPE ='0'  " + "and (USERCHARACTER = '100212' or USERCHARACTER = '100214')  "
				+ "and TB_AVERAGESCORE.OBJECTID = TB_USER.USERID";

		RowSet rs1 = dbAccess.executeQuery(sql1);
		while (rs1.next()) {
			String sql2 = "select TB_AVERAGESCORE.AVERAGESCORE  from  TB_AVERAGESCORE where OBJECTID = '"
					+ rs1.getInt("DEPTID") + "'  and SCORERTYPE ='0'";
			RowSet rs2 = dbAccess.executeQuery(sql2);
			if (rs2.next()) {
				float AggregateScore = (float) (rs1.getFloat("AVERAGESCORE") * 0.7
						+ rs2.getFloat("AVERAGESCORE") * 0.3);
				String inputSql = "update TB_AVERAGESCORE set AVERAGESCORE='" + AggregateScore + "' where AVERAGEID = '"
						+ rs1.getInt("AVERAGEID") + "'";
				dbAccess.executeUpdate(inputSql);
			}
		}
	}

	/**
	 * 计算内设机构副职领导的个人总分
	 */
	@Override
	public void NOtPrincipalAggregateScore() throws Exception {
		/*-- 先在 TB_AVERAGESCORE中所有内设机构领导的个人分数
		  --再在user表中找到所有副职领导
		  --最后联合查询到所有内设机构副职领导的个人分数*/
		String sql1 = "select TB_AVERAGESCORE.AVERAGEID,TB_AVERAGESCORE.OBJECTID,TB_AVERAGESCORE.AVERAGESCORE ,TB_USER.DEPTID "
				+ "from  TB_AVERAGESCORE,TB_USER  " + "where (OBJECTTYPE = '100114' or OBJECTTYPE ='100115') "
				+ "and SCORERTYPE ='0'  " + "and (USERCHARACTER = '100213' or USERCHARACTER = '100215') "
				+ "and TB_AVERAGESCORE.OBJECTID = TB_USER.USERID";

		RowSet rs1 = dbAccess.executeQuery(sql1);
		while (rs1.next()) {
			String sql2 = "select TB_AVERAGESCORE.AVERAGESCORE  from  TB_AVERAGESCORE where OBJECTID = '"
					+ rs1.getInt("DEPTID") + "'  and SCORERTYPE ='0'";
			RowSet rs2 = dbAccess.executeQuery(sql2);
			if (rs2.next()) {
				float AggregateScore = (float) (rs1.getFloat("AVERAGESCORE") * 0.8
						+ rs2.getFloat("AVERAGESCORE") * 0.2);
				String inputSql = "update TB_AVERAGESCORE set AVERAGESCORE='" + AggregateScore + "' where AVERAGEID = '"
						+ rs1.getInt("AVERAGEID") + "'";
				dbAccess.executeUpdate(inputSql);
			}
		}
	}

	/*
	 * 计算部门其他人员的个人总分
	 */
	@Override
	public void otherAggregateScore() throws Exception {
		String sql1 = "select TB_AVERAGESCORE.AVERAGEID,TB_AVERAGESCORE.OBJECTID,TB_AVERAGESCORE.AVERAGESCORE ,TB_USER.DEPTID "

				+ "from  TB_AVERAGESCORE,TB_USER  " + "where  " + " SCORERTYPE ='0'  " + "and USERCHARACTER = '"

				+ ExtendCodeConfig.USERLEVEL_GENERAL_STAFF + "' " + "and TB_AVERAGESCORE.OBJECTID = TB_USER.USERID";

		RowSet rs1 = dbAccess.executeQuery(sql1);
		while (rs1.next()) {
			String sql2 = "select TB_AVERAGESCORE.AVERAGESCORE  from  TB_AVERAGESCORE where OBJECTID = '"
					+ rs1.getInt("DEPTID") + "'  and SCORERTYPE ='0'";
			RowSet rs2 = dbAccess.executeQuery(sql2);
			if (rs2.next()) {
				float AggregateScore = (float) (rs1.getFloat("AVERAGESCORE") * 0.9
						+ rs2.getFloat("AVERAGESCORE") * 0.1);
				String inputSql = "update TB_AVERAGESCORE set AVERAGESCORE='" + AggregateScore + "' where AVERAGEID = '"
						+ rs1.getInt("AVERAGEID") + "'";
				dbAccess.executeUpdate(inputSql);
			}
		}
	}

}
