package com.gdcp.pas.manage.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.sql.RowSet;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.dao.PositionDAO;
import com.gdcp.pas.manage.vo.PositionVO;

public class PositionDAOImpl implements PositionDAO {
	private DbAccess dbAccess = new DbAccess();

	public DbAccess getDbAccess() {
		return dbAccess;
	}

	public void setDbAccess(DbAccess dbAccess) {
		this.dbAccess = dbAccess;
	}

	public List<PositionVO> queryPosition() throws Exception {
		String querySql = "select * from TB_POSITION";
		RowSet rs = dbAccess.executeQuery(querySql);
		List<PositionVO> positionList = new ArrayList<>();
		while (rs != null && rs.next()) {
			PositionVO positionVO = new PositionVO();
			positionVO.setPositionId(rs.getString("POSITIONID"));
			positionVO.setPositionName(rs.getString("POSITIONNAME"));
			positionVO.setRemark(rs.getString("REMARK"));
			positionList.add(positionVO);
		}
		return positionList;
	}

}
