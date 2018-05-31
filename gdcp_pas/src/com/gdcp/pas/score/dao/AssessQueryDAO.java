package com.gdcp.pas.score.dao;

/**
 * @author 张俊杰  2015-03-29 
 * @see 提供考核查询操作
 */
import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.score.vo.AssessQueryVO;

public interface AssessQueryDAO {
	/**
	 * @see 查询所有考核对象信息
	 */
	public List<AssessQueryVO> queryPage(Page page, AssessQueryVO vo) throws Exception;

	/**
	 * @see 查询考核对象得分
	 */
	public AssessQueryVO queryScore(String objectId, int scoreRuleId) throws Exception;
}
