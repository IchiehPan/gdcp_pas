package com.gdcp.pas.manage.dao;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.RoleVO;

/**
 * @author 黄岸鹏 2015-03-19
 * @see 提供对数据库中角色表的各种操作
 */
public interface RoleDAO {
	/**
	 * @see 查询出所有的角色记录
	 */
	public List<RoleVO> queryRole() throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see查询一个角色的记录
	 */
	public RoleVO getRoleById(String roleId) throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see 往数据库插入一个角色的记录
	 */
	public int insertRec(RoleVO roleVO) throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see 修改数据库一个角色的记录
	 */
	public int updateRec(RoleVO roleVO) throws Exception;

	/**
	 * @param roleId
	 *            String 角色Id
	 * @see 删除数据库一个角色的记录
	 */
	public int deleteRec(String roleId) throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see 查询出所有的RoleVO记录
	 */
	public List<RoleVO> query() throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see 查询出所有的RoleVO记录
	 */
	public List<RoleVO> queryPage(Page page, RoleVO roleVO) throws Exception;

	/**
	 * @param sqlList
	 * @throws Exception
	 * @return批量删除
	 */
	public int executeBatchDelete(String[] sqlList) throws Exception;

	/**
	 * @param sqlList
	 * @throws Exception
	 * @return批量删除 检查名字是否可用
	 */
	public boolean checkRoleName(String roleName) throws Exception;

}
