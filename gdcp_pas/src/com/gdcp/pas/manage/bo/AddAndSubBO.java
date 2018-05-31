package com.gdcp.pas.manage.bo;

import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.dao.AddAndSubDAO;
import com.gdcp.pas.manage.dao.impl.AddAndSubDAOImpl;
import com.gdcp.pas.manage.vo.AddAndSubVO;

public class AddAndSubBO {
	AddAndSubDAO aas = new AddAndSubDAOImpl();

	/**
	 * @param page
	 *            查询所有加减分对象
	 */
	public List<AddAndSubVO> queryPage(Page page) throws Exception {
		return aas.queryPage(page);
	}

	/**
	 * 向数据库中添加一条加减分项
	 * 
	 * @param aasVO
	 * @return
	 * @throws Exception
	 */
	public int AddOrSub(AddAndSubVO aasVO) throws Exception {
		return aas.AddOrSub(aasVO);
	}

	/**
	 * 删除一条加减分项
	 * 
	 * @param objectId
	 * @return
	 * @throws Exception
	 */
	public int deleteScore(String objectId) throws Exception {
		return aas.deleteScore(objectId);
	}

	/**
	 * 根据对象名称在加减分表中查询
	 * 
	 * @param objectId
	 * @return
	 * @throws Exception
	 */
	public List<AddAndSubVO> queryByObjectId(String userName) throws Exception {
		return aas.queryByObjectId(userName);
	}

	/**
	 * 根据对象名称在用户表中查询
	 * 
	 * @param objectId
	 * @return
	 * @throws Exception
	 */
	public List<AddAndSubVO> queryName(String userName) throws Exception {
		return aas.queryName(userName);
	}

	/**
	 * 更新加减分对象
	 * 
	 * @param aasVO
	 * @return
	 * @throws Exception
	 */
	public int AddOrSubUpdate(AddAndSubVO aasVO) throws Exception {
		return aas.AddOrSubUpdate(aasVO);
	}

	/**
	 * 点击添加后查询数据到添加页面
	 * 
	 * @return
	 * @throws Exception
	 */
	public List<AddAndSubVO> AddAndSubTO() throws Exception {
		return aas.AddAndSubTo();
	}
}
