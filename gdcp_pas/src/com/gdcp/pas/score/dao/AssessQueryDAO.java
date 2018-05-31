package com.gdcp.pas.score.dao;

/**
 * @author �ſ���  2015-03-29 
 * @see �ṩ���˲�ѯ����
 */
import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.score.vo.AssessQueryVO;

public interface AssessQueryDAO {
	/**
	 * @see ��ѯ���п��˶�����Ϣ
	 */
	public List<AssessQueryVO> queryPage(Page page, AssessQueryVO vo) throws Exception;

	/**
	 * @see ��ѯ���˶���÷�
	 */
	public AssessQueryVO queryScore(String objectId, int scoreRuleId) throws Exception;
}
