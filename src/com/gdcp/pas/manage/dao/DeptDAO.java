package com.gdcp.pas.manage.dao;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.DeptVO;

/**
 * 类说明 提供对数据库中部门表的各种操作
 * 
 * @author 作者:潘文杰 && cyx
 * @version 创建时间：2015-3-22 下午12:45:05
 * @update 陈伟镇 03-30 08:37 添加一个getDeptById()
 * 
 */
public interface DeptDAO {

	public int getDeptByName(String deptName) throws Exception;

	/**
	 * @see 根据部门id获取一个部门对象
	 * @param deptId
	 *            部门Id
	 * @return 一个部门DeptVO
	 */
	public DeptVO getDeptById(int deptId) throws Exception;

	/**
	 * @param roleVO
	 *            RoleVO
	 * @see 查询出所有的RoleVO记录
	 */
	public List<DeptVO> queryPage(Page page, DeptVO deptVO) throws Exception;

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 查询所有的部门
	 * @throws Exception
	 */
	public List<DeptVO> queryAll() throws Exception;

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 插入一个部门
	 * @throws Exception
	 */
	public int insertRec(DeptVO deptVO) throws Exception;

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 删除一个部门
	 * @throws Exception
	 */
	public int deleteRec(int deptId) throws Exception;

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 修改一个部门
	 * @throws Exception
	 */
	public int updateRec(DeptVO deptVO) throws Exception;

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 批量删除部门
	 * @throws Exception
	 */
	public int deleteBatchRec(String[] deleteList) throws Exception;

	/**
	 * @param DeptScoreQuantitationVO
	 *            deptVO
	 * @see 检查部门名是否存在
	 * @throws Exception
	 */
	public boolean checkName(String deptName) throws Exception;

}
