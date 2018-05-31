package com.gdcp.pas.manage.dao;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.vo.AddAndSubVO;

public interface AddAndSubDAO {
	/**
	 * 查询所有加减分对象
	 * 
	 * @param page
	 */
	public List<AddAndSubVO> queryPage(Page page) throws Exception;

	/**
	 * 向数据库中添加一条加减分项
	 * 
	 * @param aasVO
	 * @return
	 * @throws Exception
	 */
	public int AddOrSub(AddAndSubVO aasVO) throws Exception;

	/**
	 * 删除一条加减分项
	 * 
	 * @param objectId
	 * @return
	 * @throws Exception
	 */
	public int deleteScore(String objectId) throws Exception;

	/**
	 * 根据对象名称在加减分表中查询
	 * 
	 * @param page
	 * @return
	 * @throws Exception
	 */
	public List<AddAndSubVO> queryByObjectId(String userName) throws Exception;

	/**
	 * 根据对象名称在用户表中查询
	 * 
	 * @param userName
	 * @return
	 * @throws Exception
	 */
	public List<AddAndSubVO> queryName(String userName) throws Exception;

	/**
	 * 更新加减分对象
	 * 
	 * @param aasVO
	 * @return
	 * @throws Exception
	 */
	public int AddOrSubUpdate(AddAndSubVO aasVO) throws Exception;

	/**
	 * 点击添加后查询数据到添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<AddAndSubVO> AddAndSubTo() throws Exception;
}
