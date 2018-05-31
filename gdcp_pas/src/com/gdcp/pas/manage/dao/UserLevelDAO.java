package com.gdcp.pas.manage.dao;

/**
 * @author cyx  2015-03-13 
 * @see �ṩ�����ݿ����û���ݱ�ĸ��ֲ���
 */
import java.util.List;

import com.gdcp.pas.manage.vo.UserLevelVO;

public interface UserLevelDAO {

	/**
	 * @return ��ѯ�û�����б�
	 * @throws Exception
	 */
	public List<UserLevelVO> queryAll() throws Exception;

	/**
	 * @param sqlList
	 * @throws Exception
	 * @return����ɾ��
	 */
	public int executeBatchDelete(String[] sqlList) throws Exception;

	/**
	 * @param them
	 * @returnģ����ѯ�ķ���
	 */
	/* public List<UserlevelVO> queryByUserlevelFromThem(String them); */

	/**
	 * @param userlevelvo
	 * @throws Exception
	 * @return����
	 */
	public int saveUserleve(UserLevelVO userlevelVO) throws Exception;

	/**
	 * @param Them
	 * @throws Exception
	 * @return��������Ƿ�Ϸ�
	 */
	public boolean checkName(String userLevelName) throws Exception;

	/**
	 * @param USERLEVELID
	 * @param userlevelvo
	 * @return ����
	 * @throws Exception
	 */
	public int updata(int USERLEVELID, UserLevelVO userlevelVO) throws Exception;

	/**
	 * @param USERLEVELID
	 * @throws Exception
	 * @returnɾ��
	 */
	public int deleteUserlevel(int userLevelId) throws Exception;

}
