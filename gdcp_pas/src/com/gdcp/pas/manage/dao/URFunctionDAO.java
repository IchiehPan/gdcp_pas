package com.gdcp.pas.manage.dao;

import java.util.List;

/**
 * @author 黄岸鹏 2015-03-23
 * @see 提供对数据库中用户角色功能对应关系表的各种操作
 */
public interface URFunctionDAO {
	/**
	 * @param teacherId
	 *            教工号
	 * @see 根据用户id查询出该用户所具有的功能id
	 * @return 该用户所具有的功能id字符串
	 */
	public List<String> queryURFunctionByUserId(String teacherId) throws Exception;

	/**
	 * @param userId
	 *            用户id
	 * @see 根据id删除数据库中的1~n个功能对应关系的记录
	 */
	public int delURFunction(String teacherId) throws Exception;

	/**
	 * @param urFunctionVO
	 *            用户角色功能对应关系对象
	 * @see 往数据库插入一个用户角色功能对应关系的记录
	 */
	public void setUserFunction(String teacherId, List<String> userFunctionList, List<String> baseFunctionList,
			List<String> messageFunctionList) throws Exception;
}
