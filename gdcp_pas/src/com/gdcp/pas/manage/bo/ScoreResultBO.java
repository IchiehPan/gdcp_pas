package com.gdcp.pas.manage.bo;

/**
 * @author 张俊杰  2015-03-26 
 * @see 提供评价关系管理查询，删除，增加操作
 */
import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.dao.ScoreResultDAO;
import com.gdcp.pas.manage.dao.impl.ScoreResultDAOImpl;
import com.gdcp.pas.manage.vo.ScoreResultVO;

public class ScoreResultBO {
	private ScoreResultDAO srDAO = new ScoreResultDAOImpl();

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see 从数据库查询所有评分关系
	 */
	public List<ScoreResultVO> queryPage(Page page, ScoreResultVO vo) throws Exception {
		return srDAO.queryPage(page, vo);
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see 在数据库删除一条评分关系的记录
	 */
	public int deleteRec(ScoreResultVO scoreResult) throws Exception {
		return srDAO.deleteRec(scoreResult);
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see 根据用户输入的用户名查询评分对象或评价主体
	 */
	public List<ScoreResultVO> query(ScoreResultVO scoreResult) throws Exception {
		return srDAO.query(scoreResult);
	}

	public List<ScoreResultVO> queryByStatus(int status) throws Exception {
		return srDAO.queryByStatus(status);
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see 查询所有评价规则名称
	 */
	public List<ScoreResultVO> queryRuleName() throws Exception {
		return srDAO.queryRuleName();
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see 根据评分对象部门id查询改部门下的所有用户名
	 */
	public List<ScoreResultVO> queryObjectName(int odeptId) throws Exception {
		return srDAO.queryObjectName(odeptId);
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see 根据评价主体部门id查询改部门下的所有用户名
	 */
	public List<ScoreResultVO> queryScorerName(int sdeptId) throws Exception {
		return srDAO.queryScorerName(sdeptId);
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see 查询所有考核对象类型
	 */
	public List<ScoreResultVO> queryObjectType() throws Exception {
		return srDAO.queryObjectType();
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see 查询所有评价主体类型
	 */
	public List<ScoreResultVO> queryScorerType() throws Exception {
		return srDAO.queryScorerType();
	}

	/**
	 * @param scoreResultVO
	 *            ScoreResultVO
	 * @see 在评分结果主表中添加一条评分关系记录
	 */
	public int insertRec(ScoreResultVO scoreResult) throws Exception {
		return srDAO.insertRec(scoreResult);
	}

}
