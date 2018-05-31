package com.gdcp.pas.manage.dao;

import java.util.List;

import com.gdcp.pas.manage.vo.FunctionVO;

/**
 * @author 黄岸鹏&陈永鑫 2015-03-25
 * @see 提供对数据库中功能表的各种操作
 */
public interface FunctionDAO {
	/**
	 * @see 根据功能id查询功能名称
	 * @param functionId
	 *            功能id
	 */
	public String queryFunctionById(String functionId) throws Exception;

	/**
	 * @see 查询出所有的功能记录
	 */
	public List<FunctionVO> queryFunction() throws Exception;

	/**
	 * 给角色设置功能
	 */
	/**
	 * @param userFunctionList
	 *            用户管理功能
	 * @param baseFunctionList
	 *            基础信息管理功能
	 * @param messageFunctionList
	 *            问卷信息管理
	 * @throws Exception
	 */
	public void setRoleFunction(int roleId, List<String> userFunctionList, List<String> baseFunctionList,
			List<String> messageFunctionList) throws Exception;

	/**
	 *
	 * @throws SQLException
	 * @throws Exception
	 * 
	 *             查询用户功能
	 */
	public List<String> queryRoleFunction(String roleId) throws Exception;

	/**
	 *
	 * @throws SQLException
	 * @throws Exception
	 * 
	 *             update用户功能
	 */
	public int updateRoleFunction(int roleId, List<String> userFunctionList, List<String> baseFunctionList,
			List<String> messageFunctionList) throws Exception;

	/**
	 * @param sqlList
	 * @throws Exception
	 * @return批量删除
	 */
	public int executeBatchDelete(String[] sqlList) throws Exception;

	/**
	 * @param sqlList
	 * @throws Exception
	 * @return单个删除
	 */
	public int deleteFunctionByRoleId(int roleId) throws Exception;

	/**
	 * @param objectId
	 *            String 对象id
	 * @param objectType
	 *            String objectType 取值0代表 objectId是用户id， 取值1 代表objectId是角色id
	 * @see 查询对象所拥有的权限
	 */
	public List<String> queryObjectFunction(String objectId, String objectType) throws Exception;

}
