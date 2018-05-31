package com.gdcp.pas.manage.bo;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.dao.DeptDAO;
import com.gdcp.pas.manage.dao.impl.DeptDAOImpl;
import com.gdcp.pas.manage.vo.DeptVO;

/**
 * @author 作者:潘文杰
 * @version 创建时间：2015-3-22 下午1:34:35 类说明 提供对部门表增删改等操作
 */
public class DeptBO {
	DeptDAO deptDAO = new DeptDAOImpl();

	public int getDeptByName(String deptName) throws Exception {
		return deptDAO.getDeptByName(deptName);
	}

	/**
	 * @see 根据部门id获取一个部门对象
	 * @param deptId
	 *            部门Id
	 * @return 一个部门DeptVO
	 */
	public DeptVO getDeptById(int deptId) throws Exception {
		return deptDAO.getDeptById(deptId);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 查询所有的部门
	 * @throws Exception
	 */
	public List<DeptVO> queryAll() throws Exception {
		return deptDAO.queryAll();

	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 查询所有的部门
	 * @throws Exception
	 */
	public List<DeptVO> queryPage(Page page, DeptVO qDeptVO) throws Exception {
		return deptDAO.queryPage(page, qDeptVO);

	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 插入一个部门
	 * @throws Exception
	 */
	public int insertRec(DeptVO deptVO) throws Exception {
		return deptDAO.insertRec(deptVO);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 删除一个部门
	 * @throws Exception
	 */
	public int deleteRec(int deptId) throws Exception {
		return deptDAO.deleteRec(deptId);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 修改一个部门
	 * @throws Exception
	 */
	public int updateRec(DeptVO deptVO) throws Exception {
		return deptDAO.updateRec(deptVO);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 批量删除部门
	 * @throws Exception
	 */
	public int deleteBatchRec(String[] deleteList) throws Exception {
		return deptDAO.deleteBatchRec(deleteList);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 检查部门名是否存在
	 * @throws Exception
	 */
	public boolean checkName(String deptName) throws Exception {
		return deptDAO.checkName(deptName);
	}

}
