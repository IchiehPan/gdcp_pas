package com.gdcp.pas.manage.bo;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.dao.AddAndSubDAO;
import com.gdcp.pas.manage.dao.impl.AddAndSubDAOImpl;
import com.gdcp.pas.manage.vo.AddAndSubVO;

public class AddAndSubBO {
	AddAndSubDAO aas = new AddAndSubDAOImpl();

	/**
	 * @param page
	 *            ��ѯ���мӼ��ֶ���
	 */
	public List<AddAndSubVO> queryPage(Page page) throws Exception {
		return aas.queryPage(page);
	}

	/**
	 * �����ݿ������һ���Ӽ�����
	 * 
	 * @param aasVO
	 * @return
	 * @throws Exception
	 */
	public int AddOrSub(AddAndSubVO aasVO) throws Exception {
		return aas.AddOrSub(aasVO);
	}

	/**
	 * ɾ��һ���Ӽ�����
	 * 
	 * @param objectId
	 * @return
	 * @throws Exception
	 */
	public int deleteScore(String objectId) throws Exception {
		return aas.deleteScore(objectId);
	}

	/**
	 * ���ݶ��������ڼӼ��ֱ��в�ѯ
	 * 
	 * @param objectId
	 * @return
	 * @throws Exception
	 */
	public List<AddAndSubVO> queryByObjectId(String userName) throws Exception {
		return aas.queryByObjectId(userName);
	}

	/**
	 * ���ݶ����������û����в�ѯ
	 * 
	 * @param objectId
	 * @return
	 * @throws Exception
	 */
	public List<AddAndSubVO> queryName(String userName) throws Exception {
		return aas.queryName(userName);
	}

	/**
	 * ���¼Ӽ��ֶ���
	 * 
	 * @param aasVO
	 * @return
	 * @throws Exception
	 */
	public int AddOrSubUpdate(AddAndSubVO aasVO) throws Exception {
		return aas.AddOrSubUpdate(aasVO);
	}

	/**
	 * �����Ӻ��ѯ���ݵ����ҳ��
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<AddAndSubVO> AddAndSubTO() throws Exception {
		return aas.AddAndSubTo();
	}
}
