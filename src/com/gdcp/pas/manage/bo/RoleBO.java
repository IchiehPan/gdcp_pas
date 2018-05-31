package com.gdcp.pas.manage.bo;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.dao.RoleDAO;
import com.gdcp.pas.manage.dao.impl.RoleDAOImpl;
import com.gdcp.pas.manage.vo.RoleVO;

/**
 * @author �ư��� 2015-03-23
 */
public class RoleBO {
	private static RoleDAO roleDAO = new RoleDAOImpl();

	public List<RoleVO> queryRole() throws Exception {
		return roleDAO.queryRole();
	}

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see �����ݿ����һ����ɫ�ļ�¼
	 */
	public RoleVO getRoleById(String roleId) throws Exception {

		return roleDAO.getRoleById(roleId);
	}

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see �����ݿ����һ����ɫ�ļ�¼
	 */
	public int insertRec(RoleVO roleVO) throws Exception {

		return roleDAO.insertRec(roleVO);
	}

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see �����ݿ��޸�һ����ɫ�ļ�¼
	 */
	public int updateRec(RoleVO roleVO) throws Exception {
		return roleDAO.updateRec(roleVO);
	}

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see �����ݿ�ɾ��һ����ɫ�ļ�¼
	 */
	public int deleteRec(String roleId) throws Exception {
		return roleDAO.deleteRec(roleId);
	}

	/**
	 * @param roleVO
	 *            RoleVO
	 * @throws Exception
	 * @see �����ݿ�ɾ�������ɫ�ļ�¼
	 */
	public int executeBatchDelete(String[] sqlList) throws Exception {
		return roleDAO.executeBatchDelete(sqlList);
	}

	/**
	 * @param sqlList
	 * @throws Exception
	 *
	 * @return����ɾ�� ��������Ƿ����
	 */
	public boolean checkRoleName(String roleName) throws Exception {
		return roleDAO.checkRoleName(roleName);
	}

	public static List<RoleVO> queryPage(Page page, RoleVO roleVO) throws Exception {

		return roleDAO.queryPage(page, roleVO);
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		RoleBO.roleDAO = roleDAO;
	}

}
