package com.gdcp.pas.manage.bo;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.dao.UserDAO;
import com.gdcp.pas.manage.dao.impl.UserDAOImpl;
import com.gdcp.pas.manage.vo.UserVO;

/**
 * @author �ư��� 2015-03-19
 * @see �ṩ�����ݿ����û���ĸ��ֲ���
 * @upDate ��ΰ�� 2015-03-30 08:25 getUserById()-->getUserByTeacherId() and
 *         ��ӡ�getUserByUserId()��
 * @upDate ������һ���޸��û�����ķ��� updatePassword���� cyx��
 * @upDate ��ΰ�� 2015-04-02 16:25
 *         ���getObjectIdsByObjectType()��getScorerIdsByScorerType()
 * 
 */
public class UserBO {
	private UserDAO userDAO = new UserDAOImpl();

	public int updatePassword(UserVO userVO, String uOldPassWord) throws Exception {
		return userDAO.updatePassword(userVO, uOldPassWord);
	}

	/**
	 * @param userId
	 *            �û�id
	 * @return һ��UserVO����
	 * @see ����id��ѯһ���û��ļ�¼
	 */
	public UserVO getUserByUserId(int userId) throws Exception {
		return userDAO.getUserByUserId(userId);
	}

	/**
	 * @param teacherId
	 *            �̹�id
	 * @return һ��UserVO����
	 * @see ����id��ѯһ���û��ļ�¼
	 */
	public UserVO getUserByTeacherId(String teacherId) throws Exception {
		return userDAO.getUserByTeacherId(teacherId);
	}

	/**
	 * @author ��ΰ�� ��ȡָ���������������н̹���
	 * @param objectType
	 * @return �̹��ŵ�List
	 */
	public List<String> getObjectIdsByObjectType(int objectType) throws Exception {
		return userDAO.getObjectIdsByObjectType(objectType);
	}

	/**
	 * @author ��ΰ�� ��ȡָ���������������н̹���
	 * @param scorerType
	 * @return �̹��ŵ�List
	 */
	public List<String> getScorerIdsByScorerType(int scorerType) throws Exception {
		return userDAO.getScorerIdsByScorerType(scorerType);
	}

	/**
	 * @param userVO
	 *            �û�����
	 * @see �����ݿ����һ���û��ļ�¼
	 */
	public int insertUser(UserVO userVO) throws Exception {
		return userDAO.insertUser(userVO);
	}

	/**
	 * @param userVO
	 *            �û�����
	 * @see �޸����ݿ�һ���û��ļ�¼
	 */
	public int updateUser(UserVO userVO) throws Exception {
		return userDAO.updateUser(userVO);
	}

	/**
	 * @param userId
	 *            �û�id
	 * @see ɾ�����ݿ�һ���û��ļ�¼
	 */
	public int deleteUser(String teacherId) throws Exception {
		return userDAO.deleteUser(teacherId);
	}

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see ��ѯ��һҳ���û���¼
	 */
	public List<UserVO> queryPage(Page page, UserVO userVO) throws Exception {
		return userDAO.queryPage(page, userVO);

	}

	/**
	 * @see ��ѯ��ȫ�����û���¼
	 */
	public List<UserVO> queryUsers() throws Exception {
		return userDAO.queryUsers();
	}

	/**
	 * @param userName
	 *            �û�����
	 * @see ��ѯָ���û�������Ϣ
	 */
	public List<UserVO> queryLikeName(String userName) throws Exception {
		return userDAO.queryLikeName(userName);
	}

	/**
	 * @author �ſ���
	 * @see ��ȡѧУ�쵼
	 */
	public List<UserVO> getLeaderList() throws Exception {
		return userDAO.getLeaderList();
	}

	/**
	 * @author �ſ���
	 * @see ��ȡ���������쵼��ָ����������id��
	 */
	public List<UserVO> getDeptLeader(String deptId) throws Exception {
		return userDAO.getDeptLeader(deptId);
	}

	/**
	 * @author �ſ���
	 * @see ��ȡ���ڶ��������쵼
	 */
	public List<UserVO> getDeptLeaderByUser(String userId) throws Exception {
		return userDAO.getDeptLeaderByUser(userId);
	}

	/**
	 * @author ���˽�
	 * @see ��ȡ������������Ա��
	 */
	public List<UserVO> getUsersByDept(String deptId) throws Exception {
		return userDAO.getUsersByDept(deptId);
	}

	/**
	 * @author �ſ���
	 * @see ��ȡ���ж��������쵼
	 */
	public List<UserVO> getDeptLeader() throws Exception {
		return userDAO.getDeptLeader();
	}

	/**
	 * @author �ſ���
	 * @see ��ó��������ڲ����������в��ŵ��쵼
	 */
	public List<UserVO> getOtherDeptLeader(String userId) throws Exception {
		return userDAO.getOtherDeptLeader(userId);
	}

	/**
	 * @author �ſ���
	 * @see ������ڲ��ŵ�����ְ��
	 */
	public List<UserVO> getWorkmateList(String userId) throws Exception {
		return userDAO.getWorkmateList(userId);
	}

	/**
	 * @author �ſ���
	 * @see ��ý�ְ������
	 */
	public List<UserVO> getDelegateList(String userId) throws Exception {
		return userDAO.getDelegateList(userId);
	}

	/**
	 * @author �ſ���
	 * @see ��ñ������ڲ��ŵ����а�����
	 */
	public List<UserVO> getHeadMasterList(String userId) throws Exception {
		return userDAO.getHeadMasterList(userId);
	}

	/**
	 * @author �ſ���
	 * @see ��ñ������ڵ�С��Ŀ��˶���
	 */
	public List<UserVO> getPositionUserList(String userId) throws Exception {
		return userDAO.getPositionUserList(userId);
	}

	/**
	 * @author �ſ���
	 * @see ���ָ�����ŵ���ְ�쵼
	 */
	public UserVO getDeptLeaderS(String deptId) throws Exception {
		return userDAO.getDeptLeaderS(deptId);
	}
}
