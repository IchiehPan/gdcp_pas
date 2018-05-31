package com.gdcp.pas.manage.bo;

import java.util.List;
import com.gdcp.pas.manage.dao.FunctionDAO;
import com.gdcp.pas.manage.dao.impl.FunctionDAOImpl;
import com.gdcp.pas.manage.vo.FunctionVO;

/**
 * @author 黄岸鹏&陈永鑫 2015-03-25
 * @see 提供对数据库中功能表的各种操作
 */
public class FunctionBO {
	private FunctionDAO functionDAO = new FunctionDAOImpl();

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
			List<String> messageFunctionList) throws Exception {
		functionDAO.setRoleFunction(roleId, userFunctionList, baseFunctionList, messageFunctionList);

	}

	/**
	 * 
	 * @throws Exception
	 *             查询roleId下的功能Id
	 */
	public List<String> queryRoleFunction(String roleId) throws Exception {
		return functionDAO.queryRoleFunction(roleId);
	}

	/**
	 * 
	 * @throws Exception
	 *             删除roleId下的功能Id 再添加
	 */

	public int updateRoleFunction(int roleId, List<String> userFunctionList, List<String> baseFunctionList,
			List<String> messageFunctionList) throws Exception {
		return functionDAO.updateRoleFunction(roleId, userFunctionList, baseFunctionList, messageFunctionList);
	}

	/**
	 * 
	 * @throws Exception
	 *             删除roleId下的功能Id
	 */
	public int deleteFunctionByRoleId(int roleId) throws Exception {
		return functionDAO.deleteFunctionByRoleId(roleId);

	}

	/**
	 * 
	 * @throws Exception
	 *             批量删除roleId下的功能Id
	 */

	public int executeBatchDelete(String[] sqlList) throws Exception {
		return functionDAO.executeBatchDelete(sqlList);
	}

	/**
	 * @see 根据功能id查询功能名称
	 * @param functionId
	 *            功能id
	 */
	public String queryFunctionById(String functionId) throws Exception {
		return functionDAO.queryFunctionById(functionId);
	}

	/**
	 * @see 查询出所有的功能记录
	 */
	public List<FunctionVO> queryFunction() throws Exception {
		return functionDAO.queryFunction();
	}

	/**
	 * @param objectId
	 *            String 对象id
	 * @param objectType
	 *            String objectType 取值0代表 objectId是用户id， 取值1 代表objectId是角色id
	 * @see 查询对象所拥有的权限
	 */
	public List<String> queryObjectFunction(String objectId, String objectType) throws Exception {

		return functionDAO.queryObjectFunction(objectId, objectType);
	}

}
