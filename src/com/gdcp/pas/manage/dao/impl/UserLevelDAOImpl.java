package com.gdcp.pas.manage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.SqlUtil;
import com.gdcp.common.StringUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.dao.UserLevelDAO;
import com.gdcp.pas.manage.vo.UserLevelVO;

/**
 * @author cyx 2015-03-13
 * @see �ṩ�����ݿ����û���ݱ�ĸ��ֲ���
 */

public class UserLevelDAOImpl implements UserLevelDAO {
	DbAccess dbAccess = new DbAccess();

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gdcp.pas.manage.dao.UserlevelDAO#queryAll() ��ѯ�����û����
	 */
	@Override
	public List<UserLevelVO> queryAll() throws Exception {
		String sql = "select * from TB_USERLEVEL";

		List<UserLevelVO> UserlevelList = new ArrayList<>();

		RowSet rs = dbAccess.executeQuery(sql);
		while (rs != null && rs.next()) {
			UserLevelVO userlevelVO = new UserLevelVO();
			userlevelVO.setUserLevelId(rs.getInt("USERLEVELID"));
			userlevelVO.setUserLevelName(rs.getString("USERLEVELNAME"));
			userlevelVO.setRemark(rs.getString("REMARK"));
			UserlevelList.add(userlevelVO);
		}

		return UserlevelList;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gdcp.pas.manage.dao.UserlevelDAO#saveUserleve(com.gdcp.pas.manage.vo.
	 * UserlevelVO) ����
	 */
	@Override
	public int saveUserleve(UserLevelVO userlevelVO) throws Exception {
		int rs = 0;
		userlevelVO.setUserLevelId(SqlUtil.getInstance().getSeqId());
		String sql = "INSERT INTO TB_USERLEVEL(USERLEVELID,USERLEVELNAME,REMARK) VALUES("
				+ StringUtil.fieldValue(String.valueOf(userlevelVO.getUserLevelId())) + ","
				+ StringUtil.fieldValue(userlevelVO.getUserLevelName()) + ","
				+ StringUtil.fieldValue(userlevelVO.getRemark()) + ")";

		String checkSql = "select * from TB_USERLEVEL";
		RowSet rowSet = dbAccess.executeQuery(checkSql);
		while (rowSet != null && rowSet.next()) {
			if (rowSet.getString("USERLEVELNAME").equals(userlevelVO.getUserLevelName())) {
				return rs;
			}
		}
		rs = dbAccess.executeUpdate(sql);
		return rs;

	}

	@Override
	public int updata(int userLevelId, UserLevelVO userlevelVO) throws Exception {
		String sql = "UPDATE TB_USERLEVEL SET USERLEVELNAME=" + StringUtil.fieldValue(userlevelVO.getUserLevelName())
				+ "" + ",REMARK=" + StringUtil.fieldValue(userlevelVO.getRemark()) + "" + " WHERE USERLEVELID="
				+ StringUtil.fieldValue(String.valueOf(userLevelId)) + "";

		int rs = dbAccess.executeUpdate(sql);
		return rs;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gdcp.pas.manage.dao.UserlevelDAO#deleteUserleve(int) ɾ��
	 */
	public int deleteUserlevel(int userLevelId) throws Exception {

		// String a=new
		// String(USERLEVELNAME.getBytes("ISO-8859-1"),"utf-8").trim();
		String sql = "DELETE from TB_USERLEVEL where USERLEVELID=" + StringUtil.fieldValue(String.valueOf(userLevelId))
				+ "";

		String checkSql = "select * from TB_USER where USERCHARACTER="
				+ StringUtil.fieldValue(String.valueOf(userLevelId)) + "";

		RowSet rowSet = dbAccess.executeQuery(checkSql);
		if (rowSet.next()) {
			return 0;
		} else {
			int rs = dbAccess.executeUpdate(sql);
			return rs;
		}
	}

	/*
	 * @Override public List<UserlevelVO> queryByUserlevelFromThem(String them)
	 * { String Them=them.trim(); String sql =
	 * "select * from TB_USERLEVEL where USERLEVELNAME like '%"+Them+"%'";
	 * RowSet rs; List<UserlevelVO> list = new ArrayList<UserlevelVO>(); try {
	 * rs = dbAccess.executeQuery(sql);
	 * 
	 * while (rs.next()) { UserlevelVO userlevelvo = new UserlevelVO();
	 * userlevelvo.setUSERLEVELID(rs.getInt("USERLEVELID"));
	 * userlevelvo.setUSERLEVELNAME(rs.getString("USERLEVELNAME"));
	 * userlevelvo.setOPTIONDESC(rs.getString("REMARK")); list.add(userlevelvo);
	 * }
	 * 
	 * } catch (Exception e) { // TODO Auto-generated catch block throw new
	 * RuntimeException(e); }
	 * 
	 * return list; }
	 */

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.gdcp.pas.manage.dao.UserlevelDAO#checkName(java.lang.String)
	 * 
	 * them �û������������ʱ����
	 * 
	 * �������
	 */
	@Override
	public boolean checkName(String userLevelName) throws Exception {
		String sql = "select * from TB_USERLEVEL";
		RowSet rs = dbAccess.executeQuery(sql);
		while (rs != null && rs.next()) {
			if (rs.getString("USERLEVELNAME").equals(userLevelName)) {

				return false;
			}
		}

		return true;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.gdcp.pas.manage.dao.UserlevelDAO#executeBatchDelete(java.lang.String[
	 * ])
	 * 
	 * sqlList Sql��伯�� ����ɾ��
	 */
	@Override
	public int executeBatchDelete(String[] sqlList) throws Exception {
		List<String> Allsql = new ArrayList<String>();

		for (int i = 0; i < sqlList.length; i++) {

			String checkSql = "select * from TB_USER where USERCHARACTER="
					+ StringUtil.fieldValue(String.valueOf(sqlList[i])) + "";

			RowSet rowSet = dbAccess.executeQuery(checkSql);
			if (rowSet.next()) {
				return 0;
			}

			String sql = "DELETE from TB_USERLEVEL where USERLEVELID='" + sqlList[i] + "'";
			Allsql.add(sql);
		}

		int rs = dbAccess.executeBatchUpdate(Allsql);
		return rs;
	}

}
