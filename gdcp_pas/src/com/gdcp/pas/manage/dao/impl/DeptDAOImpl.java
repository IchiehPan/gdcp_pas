package com.gdcp.pas.manage.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.Page;
import com.gdcp.common.SqlUtil;
import com.gdcp.common.StringUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.dao.DeptDAO;
import com.gdcp.pas.manage.vo.DeptVO;

/**
 * @author ����:���Ľ�
 * @version ����ʱ�䣺2015-3-22 ����1:01:34 ��˵�� �ṩ�����ݿ��в��ű�ĸ��ֲ���
 */
public class DeptDAOImpl implements DeptDAO {

	private DbAccess dbAccess = new DbAccess();

	/**
	 * @see ���ݲ���id��ȡһ�����Ŷ���
	 * @param deptId
	 *            ����Id
	 * @return һ������DeptVO
	 */
	@Override
	public DeptVO getDeptById(int deptId) throws Exception {
		DeptVO deptVo = new DeptVO();
		String sql = "select * from TB_DEPT where DEPTID = " + deptId;
		RowSet rs = dbAccess.executeQuery(sql);
		if (rs != null && rs.next()) {
			deptVo.setDeptId(deptId);
			deptVo.setDeptName(rs.getString("DEPTNAME"));
			deptVo.setDeptType(rs.getInt("DEPTTYPE"));
			deptVo.setRemark(rs.getString("REMARK"));
		}
		return deptVo;
	}

	@Override
	public List<DeptVO> queryPage(Page page, DeptVO qDeptVO) throws Exception {
		List<DeptVO> list = new ArrayList<>();
		String querySql = "select * from tb_dept where 1=1";
		String countSql = "select count(*) from tb_dept where 1=1";
		if (qDeptVO != null && !StringUtil.isNullOrBlank(qDeptVO.getDeptName())) {
			querySql = querySql + " and deptName like'%" + qDeptVO.getDeptName() + "%'";
			countSql = countSql + " and deptName like'%" + qDeptVO.getDeptName() + "%'";
		}
		RowSet rs = dbAccess.executeQuery(countSql);
		if (rs != null && rs.next()) {
			page.setTotal(rs.getInt(1));
		}

		querySql = SqlUtil.getSQLServerPageSQL(page.getPageNo(), page.getPageSize(), "deptId", querySql);
		rs = dbAccess.executeQuery(querySql);
		while (rs != null && rs.next()) {
			DeptVO deptVO = new DeptVO();
			deptVO.setDeptId(rs.getInt("deptId"));
			deptVO.setDeptName(rs.getString("deptName"));
			deptVO.setDeptType(rs.getInt("deptType"));
			deptVO.setRemark(rs.getString("remark"));
			list.add(deptVO);
		}
		return list;
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ��ѯ���еĲ���
	 * @throws Exception
	 */
	public List<DeptVO> queryAll() throws Exception {
		List<DeptVO> list = new ArrayList<>();
		String querySql = "select * from tb_dept";
		RowSet rs = dbAccess.executeQuery(querySql);
		while (rs != null && rs.next()) {
			DeptVO deptVO = new DeptVO();
			deptVO.setDeptId(rs.getInt("deptId"));
			deptVO.setDeptName(rs.getString("deptName"));
			deptVO.setDeptType(rs.getInt("deptType"));
			deptVO.setRemark(rs.getString("remark"));
			list.add(deptVO);
		}
		return list;
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ����һ������
	 * @throws Exception
	 */
	public int insertRec(DeptVO deptVO) throws Exception {

		deptVO.setDeptId(SqlUtil.getInstance().getSeqId());
		String insertSql = "insert into tb_dept(deptId,deptName,deptType,remark) values('" + deptVO.getDeptId() + "',"
				+ StringUtil.fieldValue(deptVO.getDeptName()) + ",'" + deptVO.getDeptType() + "',"
				+ StringUtil.fieldValue(deptVO.getRemark()) + ")";
		return dbAccess.executeUpdate(insertSql);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ɾ��һ������
	 * @throws Exception
	 */
	public int deleteRec(int deptId) throws Exception {
		String deleteSql = "delete tb_dept where deptId='" + deptId + "'";
		return dbAccess.executeUpdate(deleteSql);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see �޸�һ������
	 * @throws Exception
	 */
	public int updateRec(DeptVO deptVO) throws Exception {
		String updateSql = "update tb_dept set deptName=" + StringUtil.fieldValue(deptVO.getDeptName()) + ",deptType='"
				+ deptVO.getDeptType() + "',remark=" + StringUtil.fieldValue(deptVO.getRemark()) + " where deptId="
				+ deptVO.getDeptId();
		return dbAccess.executeUpdate(updateSql);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ����ɾ������
	 * @throws Exception
	 */
	public int deleteBatchRec(String[] deleteList) throws Exception {
		List<String> list = new ArrayList<>();
		for (int i = 0; i < deleteList.length; i++) {
			String deleteBatchSql = "delete from tb_dept where deptId='" + deleteList[i] + "'";
			list.add(deleteBatchSql);
		}
		return dbAccess.executeBatchUpdate(list);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ��鲿�����Ƿ����
	 * @throws Exception
	 */
	public boolean checkName(String deptName) throws Exception {
		String sql = "select * from tb_dept";
		RowSet rs = dbAccess.executeQuery(sql);
		while (rs != null && rs.next()) {
			if (rs.getString("deptName").equals(deptName)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int getDeptByName(String deptName) throws Exception {
		int deptId = 0;
		String sql = "select * from TB_DEPT where DEPTNAME =" + StringUtil.fieldValue(deptName);
		RowSet rs = dbAccess.executeQuery(sql);
		if (rs != null && rs.next()) {
			deptId = rs.getInt("DEPTID");
		}

		return deptId;
	}

}
