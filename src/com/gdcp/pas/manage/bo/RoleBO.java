package com.gdcp.pas.manage.bo;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.dao.RoleDAO;
import com.gdcp.pas.manage.dao.impl.RoleDAOImpl;
import com.gdcp.pas.manage.vo.RoleVO;

/**
 * @author 黄岸鹏 2015-03-23
 */
public class RoleBO {
	private static RoleDAO roleDAO = new RoleDAOImpl();

	public List<RoleVO> queryRole() throws Exception {
		return roleDAO.queryRole();
	}

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see 往数据库插入一个角色的记录
	 */
	public RoleVO getRoleById(String roleId) throws Exception {

		return roleDAO.getRoleById(roleId);
	}

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see 往数据库插入一个角色的记录
	 */
	public int insertRec(RoleVO roleVO) throws Exception {

		return roleDAO.insertRec(roleVO);
	}

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see 往数据库修改一个角色的记录
	 */
	public int updateRec(RoleVO roleVO) throws Exception {
		return roleDAO.updateRec(roleVO);
	}

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see 往数据库删除一个角色的记录
	 */
	public int deleteRec(String roleId) throws Exception {
		return roleDAO.deleteRec(roleId);
	}

	/**
	 * @param roleVO
	 *            RoleVO
	 * @throws Exception
	 * @see 往数据库删除多个角色的记录
	 */
	public int executeBatchDelete(String[] sqlList) throws Exception {
		return roleDAO.executeBatchDelete(sqlList);
	}

	/**
	 * @param sqlList
	 * @throws Exception
	 *
	 * @return批量删除 检查名字是否可用
	 */
	public boolean checkRoleName(String roleName) throws Exception {
		return roleDAO.checkRoleName(roleName);
	}

	public static List<RoleVO> queryPage(Page page, RoleVO roleVO) throws Exception {

		return roleDAO.queryPage(page, roleVO);
	}

	public RoleDAO getRoleDAO() {
		return roleDAO;
	}

	public void setRoleDAO(RoleDAO roleDAO) {
		RoleBO.roleDAO = roleDAO;
	}

}
