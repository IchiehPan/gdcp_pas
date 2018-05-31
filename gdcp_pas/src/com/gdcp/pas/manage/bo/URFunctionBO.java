package com.gdcp.pas.manage.bo;

import java.util.List;
import com.gdcp.pas.manage.dao.URFunctionDAO;
import com.gdcp.pas.manage.dao.impl.URFunctionDAOImpl;

/**
 * @author �ư��� 2015-03-23
 * @see �ṩ�����ݿ����û���ɫ���ܶ�Ӧ��ϵ��ĸ��ֲ���
 */
public class URFunctionBO {
	private URFunctionDAO urFunctionDAO = new URFunctionDAOImpl();

	/**
	 * @param userId
	 *            �û�id
	 * @see �����û�id��ѯ�����û������еĹ���id
	 * @return ���û������еĹ���id�ַ���
	 */
	public List<String> queryURFunctionByUserId(String userId) throws Exception {
		return urFunctionDAO.queryURFunctionByUserId(userId);
	}

	/**
	 * @param urFunctionVO
	 *            �û���ɫ���ܶ�Ӧ��ϵ����
	 * @see �����ݿ����һ���û���ɫ���ܶ�Ӧ��ϵ�ļ�¼
	 */
	public void setUserFunction(String teacherId, List<String> userFunctionList, List<String> baseFunctionList,
			List<String> messageFunctionList) throws Exception {
		urFunctionDAO.setUserFunction(teacherId, userFunctionList, baseFunctionList, messageFunctionList);
	}

	/**
	 * @param userId
	 *            �û�id
	 * @see ����idɾ�����ݿ��е�1~n�����ܶ�Ӧ��ϵ�ļ�¼
	 */
	public int delURFunction(String teacherId) throws Exception {
		return urFunctionDAO.delURFunction(teacherId);
	}

}
