package com.gdcp.pas.score.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.score.dao.DeptScoreQuantitationDAO;
import com.gdcp.pas.score.vo.DeptScoreQuantitationVO;

public class DeptScoreQuantitationDaoImpl implements DeptScoreQuantitationDAO {
	private DbAccess dbAccess = new DbAccess();

	@Override
	/**
	 * @param DeptScoreQuantitationVO
	 *            DeptSQVO
	 * @see 查询所有的部门
	 * @throws Exception
	 */
	public List<DeptScoreQuantitationVO> queryAll() throws Exception {
		List<DeptScoreQuantitationVO> list = new ArrayList<>();
		String querySql = "select tb_dept.DEPTID, tb_dept.DEPTNAME,tb_dept.DEPTTYPE,tb_dept.REMARK,tb_deptquantitationscore.SCORE from tb_dept left join tb_deptquantitationscore on tb_dept.deptid=tb_deptquantitationscore.deptid where deptType='1'";
		RowSet rs = dbAccess.executeQuery(querySql);
		while (rs != null && rs.next()) {
			DeptScoreQuantitationVO DeptSQVO = new DeptScoreQuantitationVO();
			DeptSQVO.setDeptId(rs.getInt("deptId"));
			DeptSQVO.setDeptName(rs.getString("deptName"));
			DeptSQVO.setDeptType(rs.getInt("deptType"));
			DeptSQVO.setRemark(rs.getString("remark"));
			DeptSQVO.setQuantitationScore(rs.getFloat("score"));
			list.add(DeptSQVO);
		}
		return list;
	}

	public void save(String scores) throws Exception {
		// 获取当前时间
		Date d = new Date();
		SimpleDateFormat sf = new SimpleDateFormat("yyyy年MM月dd日");
		String nowtime = sf.format(d);

		String sleSql = "select DEPTID from tb_deptquantitationscore ";
		RowSet rs = dbAccess.executeQuery(sleSql);
		if (!rs.next()) {
			String querySql = "select tb_dept.DEPTID from tb_dept left join tb_deptquantitationscore on tb_dept.deptid=tb_deptquantitationscore.deptid where deptType='1'";
			RowSet rss = dbAccess.executeQuery(querySql);
			while (rss != null && rss.next()) {
				String sql = "insert tb_deptquantitationscore(DEPTID,YEAR,STATUS) values('" + rss.getInt("DEPTID")
						+ "','" + nowtime + "','0')";
				dbAccess.executeUpdate(sql);
			}
		}
		String[] scoresArray = null;
		scoresArray = scores.split("a");
		for (String record : scoresArray) {
			String[] scoreArray = record.split("b");
			String sql = "Update tb_deptquantitationscore set score='" + scoreArray[1] + "',STATUS ='2'  where deptid='"
					+ scoreArray[0] + "'";
			dbAccess.executeUpdate(sql);
		}

	}

}
