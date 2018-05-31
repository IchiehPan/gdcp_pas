package com.gdcp.pas.manage.bo;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.dao.DeptDAO;
import com.gdcp.pas.manage.dao.impl.DeptDAOImpl;
import com.gdcp.pas.manage.vo.DeptVO;

/**
 * @author ����:���Ľ�
 * @version ����ʱ�䣺2015-3-22 ����1:34:35 ��˵�� �ṩ�Բ��ű���ɾ�ĵȲ���
 */
public class DeptBO {
	DeptDAO deptDAO = new DeptDAOImpl();

	public int getDeptByName(String deptName) throws Exception {
		return deptDAO.getDeptByName(deptName);
	}

	/**
	 * @see ���ݲ���id��ȡһ�����Ŷ���
	 * @param deptId
	 *            ����Id
	 * @return һ������DeptVO
	 */
	public DeptVO getDeptById(int deptId) throws Exception {
		return deptDAO.getDeptById(deptId);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ��ѯ���еĲ���
	 * @throws Exception
	 */
	public List<DeptVO> queryAll() throws Exception {
		return deptDAO.queryAll();

	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ��ѯ���еĲ���
	 * @throws Exception
	 */
	public List<DeptVO> queryPage(Page page, DeptVO qDeptVO) throws Exception {
		return deptDAO.queryPage(page, qDeptVO);

	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ����һ������
	 * @throws Exception
	 */
	public int insertRec(DeptVO deptVO) throws Exception {
		return deptDAO.insertRec(deptVO);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ɾ��һ������
	 * @throws Exception
	 */
	public int deleteRec(int deptId) throws Exception {
		return deptDAO.deleteRec(deptId);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see �޸�һ������
	 * @throws Exception
	 */
	public int updateRec(DeptVO deptVO) throws Exception {
		return deptDAO.updateRec(deptVO);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ����ɾ������
	 * @throws Exception
	 */
	public int deleteBatchRec(String[] deleteList) throws Exception {
		return deptDAO.deleteBatchRec(deleteList);
	}

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ��鲿�����Ƿ����
	 * @throws Exception
	 */
	public boolean checkName(String deptName) throws Exception {
		return deptDAO.checkName(deptName);
	}

}
