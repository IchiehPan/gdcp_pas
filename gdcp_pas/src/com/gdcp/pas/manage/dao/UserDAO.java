package com.gdcp.pas.manage.dao;

import java.util.List;
import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.UserVO;

/**
 * @author �ư��� 2015-03-19
 * @see �ṩ�����ݿ����û���ĸ��ֲ���
 * @upDate ��ΰ�� 2015-03-30 08:25 getUserById()-->getUserByTeacherId() and
 *         ��ӡ�getUserByUserId()��
 * @upDate ��ΰ�� 2015-04-02 16:25
 *         ���getObjectIdsByObjectType()��getScorerIdsByScorerType()
 */
public interface UserDAO {

	/**
	 * @param userVO
	 * @return
	 * @throws Exception
	 * 
	 *             �����û�����
	 */
	public int updatePassword(UserVO userVO, String uOldPassWord) throws Exception;

	/**
	 * @param userId
	 *            �û�id
	 * @return һ��UserVO����
	 * @see ����userId��ѯһ���û��ļ�¼
	 */
	public UserVO getUserByUserId(int userId) throws Exception;

	/**
	 * @param teacherId
	 *            �̹�id
	 * @return һ��UserVO����
	 * @see ����teacherId��ѯһ���û��ļ�¼
	 */
	public UserVO getUserByTeacherId(String teacherId) throws Exception;

	/**
	 * ��ȡָ���������������н̹���
	 * 
	 * @param objectType
	 * @return �̹��ŵ�List
	 */
	public List<String> getObjectIdsByObjectType(int objectType) throws Exception;

	/**
	 * ��ȡָ���������������н̹���
	 * 
	 * @param scorerType
	 * @return �̹��ŵ�List
	 */
	public List<String> getScorerIdsByScorerType(int scorerType) throws Exception;

	/**
	 * @param userVO
	 *            �û�����
	 * @see �����ݿ����һ���û��ļ�¼
	 */
	public int insertUser(UserVO userVO) throws Exception;

	/**
	 * @param userVO
	 *            �û�����
	 * @see �޸����ݿ�һ���û��ļ�¼
	 */
	public int updateUser(UserVO userVO) throws Exception;

	/**
	 * @param userId
	 *            �û�id
	 * @see ɾ�����ݿ�һ���û��ļ�¼
	 */
	public int deleteUser(String teacherId) throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see ��ѯ��һҳ���û���¼
	 */
	public List<UserVO> queryPage(Page page, UserVO userVO) throws Exception;

	/**
	 * @see ��ѯ��ȫ�����û���¼
	 */
	public List<UserVO> queryUsers() throws Exception;

	/**
	 * @param userName
	 *            �û�����
	 * @see ��ѯָ���û�������Ϣ
	 */
	public List<UserVO> queryLikeName(String userName) throws Exception;

	/**
	 * @author �ſ���
	 * @see ��ȡѧУ�쵼
	 */
	public List<UserVO> getLeaderList() throws Exception;

	/**
	 * @author �ſ���
	 * @see ��ȡ���������쵼��ָ����������id��
	 */
	public List<UserVO> getDeptLeader(String deptId) throws Exception;

	/**
	 * @author �ſ���
	 * @see ��ȡ���ڶ��������쵼
	 */
	public List<UserVO> getDeptLeaderByUser(String userId) throws Exception;

	/**
	 * @author �ſ���
	 * @see ��ȡ���ж��������쵼
	 */
	public List<UserVO> getDeptLeader() throws Exception;

	/**
	 * @author �ſ���
	 * @see ��ȡ���ж��������쵼
	 */
	public List<UserVO> getDeptLeader(List<String> deptIdList) throws Exception;

	/**
	 * @author �ſ���
	 * @see ��ó��������ڲ����������в��ŵ��쵼
	 */
	public List<UserVO> getOtherDeptLeader(String userId) throws Exception;

	/**
	 * @author �ſ���
	 * @see ������ڲ��ŵ�����ְ��
	 */
	public List<UserVO> getWorkmateList(String userId) throws Exception;

	/**
	 * @author �ſ���
	 * @see ��ý�ְ������
	 */
	public List<UserVO> getDelegateList(String userId) throws Exception;

	/**
	 * @author �ſ���
	 * @see ��ñ������ڲ��ŵ����а�����
	 */
	public List<UserVO> getHeadMasterList(String userId) throws Exception;

	/**
	 * @author �ſ���
	 * @see ��ñ������ڵ�С��Ŀ��˶���
	 */
	public List<UserVO> getPositionUserList(String userId) throws Exception;

	/**
	 * @author �ſ���
	 * @see ���ָ�����ŵ���ְ�쵼
	 */
	public UserVO getDeptLeaderS(String deptId) throws Exception;

	/**
	 * @author ���˽�
	 * @see ���ָ�����ŵ�����Ա��
	 */
	public List<UserVO> getUsersByDept(String deptId) throws Exception;

}