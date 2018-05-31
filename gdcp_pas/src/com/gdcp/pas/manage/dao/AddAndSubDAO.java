package com.gdcp.pas.manage.dao;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.AddAndSubVO;

public interface AddAndSubDAO {
	/**
	 * ��ѯ���мӼ��ֶ���
	 * 
	 * @param page
	 */
	public List<AddAndSubVO> queryPage(Page page) throws Exception;

	/**
	 * �����ݿ������һ���Ӽ�����
	 * 
	 * @param aasVO
	 * @return
	 * @throws Exception
	 */
	public int AddOrSub(AddAndSubVO aasVO) throws Exception;

	/**
	 * ɾ��һ���Ӽ�����
	 * 
	 * @param objectId
	 * @return
	 * @throws Exception
	 */
	public int deleteScore(String objectId) throws Exception;

	/**
	 * ���ݶ��������ڼӼ��ֱ��в�ѯ
	 * 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<AddAndSubVO> queryByObjectId(String userName) throws Exception;

	/**
	 * ���ݶ����������û����в�ѯ
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<AddAndSubVO> queryName(String userName) throws Exception;

	/**
	 * ���¼Ӽ��ֶ���
	 * 
	 * @param aasVO
	 * @return
	 * @throws Exception
	 */
	public int AddOrSubUpdate(AddAndSubVO aasVO) throws Exception;

	/**
	 * �����Ӻ��ѯ���ݵ����ҳ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<AddAndSubVO> AddAndSubTo() throws Exception;
}
