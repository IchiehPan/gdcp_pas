package com.gdcp.pas.manage.bo;

/**
 * @author �ſ���  2015-03-19 
 * @see �ṩ�Խ�ɫ�û������ɾ�Ĳ�Ȳ���
 */
import java.util.List;

import com.gdcp.base.BaseBO;
import com.gdcp.common.Page;
import com.gdcp.pas.manage.dao.UserRoleDAO;
import com.gdcp.pas.manage.dao.impl.UserRoleDAOImpl;
import com.gdcp.pas.manage.vo.UserRoleVO;

public class UserRoleBO extends BaseBO {
	private UserRoleDAO userRoleDAO = new UserRoleDAOImpl();

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see �����ݿ��ѯ�����û���ɫ�ļ�¼
	 */
	public List<UserRoleVO> queryPage(Page page) throws Exception {

		return userRoleDAO.queryPage(page);
	}

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see �����ݿ�ɾ��һ���û���ɫ�ļ�¼
	 */
	public int deleteRec(UserRoleVO userRole) throws Exception {
		return userRoleDAO.deleteRec(userRole);
	}

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see �����ݿ��и����û���ɫ�ļ�¼
	 */
	public int updateConfi(UserRoleVO ur) throws Exception {
		return userRoleDAO.updateConfi(ur);
	}

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see �����ݿ������һ���û���ɫ�ļ�¼
	 */
	public int insertRec(UserRoleVO ur) throws Exception {
		return userRoleDAO.insertRec(ur);
	}

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see �����ݿ��в�ѯ�����û����ֵļ�¼
	 */
	public List<UserRoleVO> queryUserName() throws Exception {
		return userRoleDAO.queryUserName();
	}

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see �����ݿ��в�ѯ���н�ɫ���ֵļ�¼
	 */
	public List<UserRoleVO> queryRoleName() throws Exception {
		return userRoleDAO.queryRoleName();
	}
}
