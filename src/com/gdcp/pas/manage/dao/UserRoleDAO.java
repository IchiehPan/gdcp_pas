package com.gdcp.pas.manage.dao;
/**
 * @author 张俊杰  2015-03-19 
 * @see 提供对角色用户表的增删改查等操作
 */

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.UserRoleVO;

public interface UserRoleDAO {
	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see 从数据库查询所有用户角色的记录
	 */
	public List<UserRoleVO> queryPage(Page page) throws Exception;

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see 在数据库删除一条用户角色的记录
	 */
	public int deleteRec(UserRoleVO userRole) throws Exception;

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see 在数据库中更新用户角色的记录
	 */
	public int updateConfi(UserRoleVO ur) throws Exception;

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see 往数据库中添加一条用户角色的记录
	 */
	public int insertRec(UserRoleVO ur) throws Exception;

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see 在数据库中查询所有用户名字的记录
	 */
	public List<UserRoleVO> queryUserName() throws Exception;

	/**
	 * @param userRoleVO
	 *            UserRoleVO
	 * @see 在数据库中查询所有角色名字的记录
	 */
	public List<UserRoleVO> queryRoleName() throws Exception;
}
