package com.gdcp.pas.manage.dao;

import java.util.List;

import com.gdcp.pas.manage.vo.FunctionVO;

/**
 * @author �ư���&������ 2015-03-25
 * @see �ṩ�����ݿ��й��ܱ�ĸ��ֲ���
 */
public interface FunctionDAO {
	/**
	 * @see ���ݹ���id��ѯ��������
	 * @param functionId
	 *            ����id
	 */
	public String queryFunctionById(String functionId) throws Exception;

	/**
	 * @see ��ѯ�����еĹ��ܼ�¼
	 */
	public List<FunctionVO> queryFunction() throws Exception;

	/**
	 * ����ɫ���ù���
	 */
	/**
	 * @param userFunctionList
	 *            �û�������
	 * @param baseFunctionList
	 *            ������Ϣ������
	 * @param messageFunctionList
	 *            �ʾ���Ϣ����
	 * @throws Exception
	 */
	public void setRoleFunction(int roleId, List<String> userFunctionList, List<String> baseFunctionList,
			List<String> messageFunctionList) throws Exception;

	/**
	 *
	 * @throws SQLException
	 * @throws Exception
	 * 
	 *             ��ѯ�û�����
	 */
	public List<String> queryRoleFunction(String roleId) throws Exception;

	/**
	 *
	 * @throws SQLException
	 * @throws Exception
	 * 
	 *             update�û�����
	 */
	public int updateRoleFunction(int roleId, List<String> userFunctionList, List<String> baseFunctionList,
			List<String> messageFunctionList) throws Exception;

	/**
	 * @param sqlList
	 * @throws Exception
	 * @return����ɾ��
	 */
	public int executeBatchDelete(String[] sqlList) throws Exception;

	/**
	 * @param sqlList
	 * @throws Exception
	 * @return����ɾ��
	 */
	public int deleteFunctionByRoleId(int roleId) throws Exception;

	/**
	 * @param objectId
	 *            String ����id
	 * @param objectType
	 *            String objectType ȡֵ0���� objectId���û�id�� ȡֵ1 ����objectId�ǽ�ɫid
	 * @see ��ѯ������ӵ�е�Ȩ��
	 */
	public List<String> queryObjectFunction(String objectId, String objectType) throws Exception;

}
