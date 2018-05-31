package com.gdcp.pas.manage.bo;

import java.util.List;
import com.gdcp.pas.manage.dao.FunctionDAO;
import com.gdcp.pas.manage.dao.impl.FunctionDAOImpl;
import com.gdcp.pas.manage.vo.FunctionVO;

/**
 * @author �ư���&������ 2015-03-25
 * @see �ṩ�����ݿ��й��ܱ�ĸ��ֲ���
 */
public class FunctionBO {
	private FunctionDAO functionDAO = new FunctionDAOImpl();

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
			List<String> messageFunctionList) throws Exception {
		functionDAO.setRoleFunction(roleId, userFunctionList, baseFunctionList, messageFunctionList);

	}

	/**
	 * 
	 * @throws Exception
	 *             ��ѯroleId�µĹ���Id
	 */
	public List<String> queryRoleFunction(String roleId) throws Exception {
		return functionDAO.queryRoleFunction(roleId);
	}

	/**
	 * 
	 * @throws Exception
	 *             ɾ��roleId�µĹ���Id �����
	 */

	public int updateRoleFunction(int roleId, List<String> userFunctionList, List<String> baseFunctionList,
			List<String> messageFunctionList) throws Exception {
		return functionDAO.updateRoleFunction(roleId, userFunctionList, baseFunctionList, messageFunctionList);
	}

	/**
	 * 
	 * @throws Exception
	 *             ɾ��roleId�µĹ���Id
	 */
	public int deleteFunctionByRoleId(int roleId) throws Exception {
		return functionDAO.deleteFunctionByRoleId(roleId);

	}

	/**
	 * 
	 * @throws Exception
	 *             ����ɾ��roleId�µĹ���Id
	 */

	public int executeBatchDelete(String[] sqlList) throws Exception {
		return functionDAO.executeBatchDelete(sqlList);
	}

	/**
	 * @see ���ݹ���id��ѯ��������
	 * @param functionId
	 *            ����id
	 */
	public String queryFunctionById(String functionId) throws Exception {
		return functionDAO.queryFunctionById(functionId);
	}

	/**
	 * @see ��ѯ�����еĹ��ܼ�¼
	 */
	public List<FunctionVO> queryFunction() throws Exception {
		return functionDAO.queryFunction();
	}

	/**
	 * @param objectId
	 *            String ����id
	 * @param objectType
	 *            String objectType ȡֵ0���� objectId���û�id�� ȡֵ1 ����objectId�ǽ�ɫid
	 * @see ��ѯ������ӵ�е�Ȩ��
	 */
	public List<String> queryObjectFunction(String objectId, String objectType) throws Exception {

		return functionDAO.queryObjectFunction(objectId, objectType);
	}

}
