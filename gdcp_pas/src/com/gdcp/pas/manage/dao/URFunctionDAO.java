package com.gdcp.pas.manage.dao;

import java.util.List;

/**
 * @author �ư��� 2015-03-23
 * @see �ṩ�����ݿ����û���ɫ���ܶ�Ӧ��ϵ��ĸ��ֲ���
 */
public interface URFunctionDAO {
	/**
	 * @param teacherId
	 *            �̹���
	 * @see �����û�id��ѯ�����û������еĹ���id
	 * @return ���û������еĹ���id�ַ���
	 */
	public List<String> queryURFunctionByUserId(String teacherId) throws Exception;

	/**
	 * @param userId
	 *            �û�id
	 * @see ����idɾ�����ݿ��е�1~n�����ܶ�Ӧ��ϵ�ļ�¼
	 */
	public int delURFunction(String teacherId) throws Exception;

	/**
	 * @param urFunctionVO
	 *            �û���ɫ���ܶ�Ӧ��ϵ����
	 * @see �����ݿ����һ���û���ɫ���ܶ�Ӧ��ϵ�ļ�¼
	 */
	public void setUserFunction(String teacherId, List<String> userFunctionList, List<String> baseFunctionList,
			List<String> messageFunctionList) throws Exception;
}
