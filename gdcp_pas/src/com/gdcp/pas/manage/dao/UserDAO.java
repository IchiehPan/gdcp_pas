package com.gdcp.pas.manage.dao;

import java.util.List;
import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.UserVO;

/**
 * @author 黄岸鹏 2015-03-19
 * @see 提供对数据库中用户表的各种操作
 * @upDate 陈伟镇 2015-03-30 08:25 getUserById()-->getUserByTeacherId() and
 *         添加“getUserByUserId()”
 * @upDate 陈伟镇 2015-04-02 16:25
 *         添加getObjectIdsByObjectType()和getScorerIdsByScorerType()
 */
public interface UserDAO {

	/**
	 * @param userVO
	 * @return
	 * @throws Exception
	 * 
	 *             更新用户密码
	 */
	public int updatePassword(UserVO userVO, String uOldPassWord) throws Exception;

	/**
	 * @param userId
	 *            用户id
	 * @return 一个UserVO对象
	 * @see 根据userId查询一个用户的记录
	 */
	public UserVO getUserByUserId(int userId) throws Exception;

	/**
	 * @param teacherId
	 *            教工id
	 * @return 一个UserVO对象
	 * @see 根据teacherId查询一个用户的记录
	 */
	public UserVO getUserByTeacherId(String teacherId) throws Exception;

	/**
	 * 获取指定对象类型下所有教工号
	 * 
	 * @param objectType
	 * @return 教工号的List
	 */
	public List<String> getObjectIdsByObjectType(int objectType) throws Exception;

	/**
	 * 获取指定主体类型下所有教工号
	 * 
	 * @param scorerType
	 * @return 教工号的List
	 */
	public List<String> getScorerIdsByScorerType(int scorerType) throws Exception;

	/**
	 * @param userVO
	 *            用户对象
	 * @see 往数据库插入一个用户的记录
	 */
	public int insertUser(UserVO userVO) throws Exception;

	/**
	 * @param userVO
	 *            用户对象
	 * @see 修改数据库一个用户的记录
	 */
	public int updateUser(UserVO userVO) throws Exception;

	/**
	 * @param userId
	 *            用户id
	 * @see 删除数据库一个用户的记录
	 */
	public int deleteUser(String teacherId) throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see 查询出一页的用户记录
	 */
	public List<UserVO> queryPage(Page page, UserVO userVO) throws Exception;

	/**
	 * @see 查询出全部的用户记录
	 */
	public List<UserVO> queryUsers() throws Exception;

	/**
	 * @param userName
	 *            用户姓名
	 * @see 查询指定用户名的信息
	 */
	public List<UserVO> queryLikeName(String userName) throws Exception;

	/**
	 * @author 张俊杰
	 * @see 获取学校领导
	 */
	public List<UserVO> getLeaderList() throws Exception;

	/**
	 * @author 张俊杰
	 * @see 获取二级部门领导（指定二级部门id）
	 */
	public List<UserVO> getDeptLeader(String deptId) throws Exception;

	/**
	 * @author 张俊杰
	 * @see 获取所在二级部门领导
	 */
	public List<UserVO> getDeptLeaderByUser(String userId) throws Exception;

	/**
	 * @author 张俊杰
	 * @see 获取所有二级部门领导
	 */
	public List<UserVO> getDeptLeader() throws Exception;

	/**
	 * @author 张俊杰
	 * @see 获取所有二级部门领导
	 */
	public List<UserVO> getDeptLeader(List<String> deptIdList) throws Exception;

	/**
	 * @author 张俊杰
	 * @see 获得除本人所在部门以外所有部门的领导
	 */
	public List<UserVO> getOtherDeptLeader(String userId) throws Exception;

	/**
	 * @author 张俊杰
	 * @see 获得所在部门的所有职工
	 */
	public List<UserVO> getWorkmateList(String userId) throws Exception;

	/**
	 * @author 张俊杰
	 * @see 获得教职工代表
	 */
	public List<UserVO> getDelegateList(String userId) throws Exception;

	/**
	 * @author 张俊杰
	 * @see 获得本人所在部门的所有班主任
	 */
	public List<UserVO> getHeadMasterList(String userId) throws Exception;

	/**
	 * @author 张俊杰
	 * @see 获得本人所在的小组的考核对象
	 */
	public List<UserVO> getPositionUserList(String userId) throws Exception;

	/**
	 * @author 张俊杰
	 * @see 获得指定部门的正职领导
	 */
	public UserVO getDeptLeaderS(String deptId) throws Exception;

	/**
	 * @author 潘宜杰
	 * @see 获得指定部门的所有员工
	 */
	public List<UserVO> getUsersByDept(String deptId) throws Exception;

}