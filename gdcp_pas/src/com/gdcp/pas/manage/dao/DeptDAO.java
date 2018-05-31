package com.gdcp.pas.manage.dao;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.DeptVO;

/**
 * ��˵�� �ṩ�����ݿ��в��ű�ĸ��ֲ���
 * 
 * @author ����:���Ľ� && cyx
 * @version ����ʱ�䣺2015-3-22 ����12:45:05
 * @update ��ΰ�� 03-30 08:37 ���һ��getDeptById()
 * 
 */
public interface DeptDAO {

	public int getDeptByName(String deptName) throws Exception;

	/**
	 * @see ���ݲ���id��ȡһ�����Ŷ���
	 * @param deptId
	 *            ����Id
	 * @return һ������DeptVO
	 */
	public DeptVO getDeptById(int deptId) throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see ��ѯ�����е�RoleVO��¼
	 */
	public List<DeptVO> queryPage(Page page, DeptVO deptVO) throws Exception;

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ��ѯ���еĲ���
	 * @throws Exception
	 */
	public List<DeptVO> queryAll() throws Exception;

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ����һ������
	 * @throws Exception
	 */
	public int insertRec(DeptVO deptVO) throws Exception;

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ɾ��һ������
	 * @throws Exception
	 */
	public int deleteRec(int deptId) throws Exception;

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see �޸�һ������
	 * @throws Exception
	 */
	public int updateRec(DeptVO deptVO) throws Exception;

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ����ɾ������
	 * @throws Exception
	 */
	public int deleteBatchRec(String[] deleteList) throws Exception;

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see ��鲿�����Ƿ����
	 * @throws Exception
	 */
	public boolean checkName(String deptName) throws Exception;

}
