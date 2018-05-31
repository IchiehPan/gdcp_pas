package com.gdcp.pas.manage.dao.impl;

/**
 * @author 张俊杰
 * 15/4/23
 */
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.Page;
import com.gdcp.common.SqlUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.dao.AddAndSubDAO;
import com.gdcp.pas.manage.vo.AddAndSubVO;

public class AddAndSubDAOImpl implements AddAndSubDAO {
	private DbAccess dbAccess = new DbAccess();

	public DbAccess getDbAccess() {
		return dbAccess;
	}

	public void setDbAccess(DbAccess dbAccess) {
		this.dbAccess = dbAccess;
	}

	@Override
	public List<AddAndSubVO> queryPage(Page page) throws Exception {
		String countSql = "select count(*)";
		String sql = "select a.id, a.OBJECTID,a.STATUS,a.SCORE,a.DESCRIBE,b.USERNAME as USERName from TB_ADDANDSUB a , TB_USER b where a.OBJECTID=b.TEACHERID";
		List<AddAndSubVO> list = new ArrayList<>();
		countSql = countSql + "from TB_ADDANDSUB a , TB_USER b where a.OBJECTID=b.TEACHERID";
		RowSet rs = dbAccess.executeQuery(countSql);
		if (rs != null && rs.next()) {
			page.setTotal(rs.getInt(1));
		}
		sql = SqlUtil.getSQLServerPageSQL(page.getPageNo(), page.getPageSize(), "objectId", sql);
		rs = dbAccess.executeQuery(sql);
		while (rs != null && rs.next()) {
			AddAndSubVO aasVO = new AddAndSubVO();
			aasVO.setId(rs.getInt("id"));
			aasVO.setUserName(rs.getString("USERNAME"));
			aasVO.setDescribe(rs.getString("DESCRIBE"));
			aasVO.setObjectId(rs.getString("OBJECTID"));
			aasVO.setScore(rs.getInt("SCORE"));
			aasVO.setStatus(rs.getInt("STATUS"));
			list.add(aasVO);
		}
		return list;
	}

	/**
	 * 向数据库中添加一条加减分项
	 */
	@Override
	public int AddOrSub(AddAndSubVO aasVO) throws Exception {
		String sql = "select * from TB_ADDANDSUB";
		RowSet rs = dbAccess.executeQuery(sql);
		int id = 0;
		while (rs.next() && rs != null) {
			id += 1;
		}
		String objectId = null;
		sql = "select * from TB_USER where USERNAME='" + aasVO.getUserName() + "'";
		rs = dbAccess.executeQuery(sql);
		while (rs != null && rs.next()) {
			objectId = rs.getString("TEACHERID");
		}
		if (objectId == null) {
			return -1;
		}
		sql = "insert into TB_ADDANDSUB (ID,OBJECTID,STATUS,SCORE,DESCRIBE) values(" + id + ",'" + objectId + "',"
				+ aasVO.getStatus() + "," + aasVO.getScore() + ",'" + aasVO.getDescribe() + "') ";
		return dbAccess.executeUpdate(sql);
	}

	/**
	 * 删除一条加减分项
	 */
	@Override
	public int deleteScore(String objectId) throws Exception {
		String sql = "delete from TB_ADDANDSUB where id='" + objectId + "'";
		return dbAccess.executeUpdate(sql);
	}

	/**
	 * 根据对象名称查询
	 */
	@Override
	public List<AddAndSubVO> queryByObjectId(String userName) throws Exception {
		String sql = null;
		if (userName.contains("@")) {
			String[] objectId = userName.split("@");
			sql = "select a.id, a.OBJECTID,a.STATUS,a.SCORE,a.DESCRIBE,b.USERNAME as USERNAME from TB_ADDANDSUB a , TB_USER b where a.OBJECTID=b.TEACHERID and '"
					+ objectId[0] + "'=a.id";
		} else {
			sql = "select a.id, a.OBJECTID,a.STATUS,a.SCORE,a.DESCRIBE,b.USERNAME as USERNAME from TB_ADDANDSUB a , TB_USER b where a.OBJECTID=b.TEACHERID and b.USERNAME like '%"
					+ userName + "%'";
		}
		List<AddAndSubVO> list = new ArrayList<>();
		RowSet rs = dbAccess.executeQuery(sql);
		while (rs != null && rs.next()) {
			AddAndSubVO aasVO = new AddAndSubVO();
			aasVO.setId(rs.getInt("id"));
			aasVO.setUserName(rs.getString("USERNAME"));
			aasVO.setDescribe(rs.getString("DESCRIBE"));
			aasVO.setObjectId(rs.getString("OBJECTID"));
			aasVO.setScore(rs.getInt("SCORE"));
			aasVO.setStatus(rs.getInt("STATUS"));
			list.add(aasVO);
		}
		return list;
	}

	/**
	 * 更新加减分对象
	 */
	@Override
	public int AddOrSubUpdate(AddAndSubVO aasVO) throws Exception {
		String objectId = null;
		String sql = "select * from TB_USER where USERNAME='" + aasVO.getUserName() + "'";
		RowSet rs = dbAccess.executeQuery(sql);
		while (rs != null && rs.next()) {
			objectId = rs.getString("TEACHERID");
		}
		sql = "update TB_ADDANDSUB set SCORE=" + aasVO.getScore() + " , STATUS=" + aasVO.getStatus() + " , DESCRIBE='"
				+ aasVO.getDescribe() + "', OBJECTID='" + objectId + "' where ID='" + aasVO.getId() + "' and STATUS="
				+ aasVO.getOldstatus() + "";
		return dbAccess.executeUpdate(sql);
	}

	/**
	 * 点击添加后查询数据到添加页面
	 */
	@Override
	public List<AddAndSubVO> AddAndSubTo() throws Exception {
		String sql = "select a.USERID , a.USERNAME from TB_USER a";
		List<AddAndSubVO> list = new ArrayList<>();
		RowSet rs = dbAccess.executeQuery(sql);
		while (rs != null && rs.next()) {
			AddAndSubVO aasVO = new AddAndSubVO();
			aasVO.setObjectId(rs.getString("USERID"));
			aasVO.setUserName(rs.getString("USERNAME"));
			list.add(aasVO);
		}
		return list;
	}

	/**
	 * 根据对象名称在用户表中查询
	 */
	@Override
	public List<AddAndSubVO> queryName(String userName) throws Exception {
		String sql = "select a.USERNAME from TB_USER a where a.userName like '%" + userName + "%'";
		List<AddAndSubVO> list = new ArrayList<>();
		RowSet rs = dbAccess.executeQuery(sql);
		while (rs != null && rs.next()) {
			AddAndSubVO aasVO = new AddAndSubVO();
			aasVO.setUserName(rs.getString("USERNAME"));
			list.add(aasVO);
		}
		return list;
	}

}
