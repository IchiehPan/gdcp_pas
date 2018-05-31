package com.gdcp.pas.manage.dao;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.RoleVO;

/**
 * @author �ư��� 2015-03-19
 * @see �ṩ�����ݿ��н�ɫ��ĸ��ֲ���
 */
public interface RoleDAO {
	/**
	 * @see ��ѯ�����еĽ�ɫ��¼
	 */
	public List<RoleVO> queryRole() throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see��ѯһ����ɫ�ļ�¼
	 */
	public RoleVO getRoleById(String roleId) throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see �����ݿ����һ����ɫ�ļ�¼
	 */
	public int insertRec(RoleVO roleVO) throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see �޸����ݿ�һ����ɫ�ļ�¼
	 */
	public int updateRec(RoleVO roleVO) throws Exception;

	/**
	 * @param roleId
	 *            String ��ɫId
	 * @see ɾ�����ݿ�һ����ɫ�ļ�¼
	 */
	public int deleteRec(String roleId) throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see ��ѯ�����е�RoleVO��¼
	 */
	public List<RoleVO> query() throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see ��ѯ�����е�RoleVO��¼
	 */
	public List<RoleVO> queryPage(Page page, RoleVO roleVO) throws Exception;

	/**
	 * @param sqlList
	 * @throws Exception
	 * @return����ɾ��
	 */
	public int executeBatchDelete(String[] sqlList) throws Exception;

	/**
	 * @param sqlList
	 * @throws Exception
	 * @return����ɾ�� ��������Ƿ����
	 */
	public boolean checkRoleName(String roleName) throws Exception;

}
