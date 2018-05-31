package com.gdcp.pas.manage.dao;
/**
 * @author �ſ���  2015-03-19 
 * @see �ṩ�Խ�ɫ�û������ɾ�Ĳ�Ȳ���
 */

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.UserRoleVO;

public interface UserRoleDAO {
	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see �����ݿ��ѯ�����û���ɫ�ļ�¼
	 */
	public List<UserRoleVO> queryPage(Page page) throws Exception;

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see �����ݿ�ɾ��һ���û���ɫ�ļ�¼
	 */
	public int deleteRec(UserRoleVO userRole) throws Exception;

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see �����ݿ��и����û���ɫ�ļ�¼
	 */
	public int updateConfi(UserRoleVO ur) throws Exception;

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see �����ݿ������һ���û���ɫ�ļ�¼
	 */
	public int insertRec(UserRoleVO ur) throws Exception;

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see �����ݿ��в�ѯ�����û����ֵļ�¼
	 */
	public List<UserRoleVO> queryUserName() throws Exception;

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see �����ݿ��в�ѯ���н�ɫ���ֵļ�¼
	 */
	public List<UserRoleVO> queryRoleName() throws Exception;
}
