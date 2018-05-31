package com.gdcp.pas.score.bo;

/**
 * @author �ſ���  2015-03-29 
 * @see �ṩ���˲�ѯ����
 */
import java.util.ArrayList;
import java.util.List;

import com.gdcp.common.Page;
import com.gdcp.pas.manage.dao.DeptDAO;
import com.gdcp.pas.manage.dao.impl.DeptDAOImpl;
import com.gdcp.pas.manage.vo.DeptVO;
import com.gdcp.pas.score.dao.AssessQueryDAO;
import com.gdcp.pas.score.dao.impl.AssessQueryDAOImpl;
import com.gdcp.pas.score.vo.AssessQueryVO;

public class AssessQueryBO {
	private AssessQueryDAO aqDAO = new AssessQueryDAOImpl();
	private DeptDAO dpDAO = new DeptDAOImpl();

	/**
	 * @see ��ѯ���п��˶�����Ϣ
	 */
	public List<AssessQueryVO> queryPage(Page page, AssessQueryVO vo) throws Exception {
		List<AssessQueryVO> list = aqDAO.queryPage(page, vo);
		List<AssessQueryVO> list1 = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			String objectId = list.get(i).getTeacherID();
			int scoreRuleId = list.get(i).getScoreRuleId();
			list1.add(aqDAO.queryScore(objectId, scoreRuleId));
		}
		return list1;
	}

	public List<DeptVO> queryDeptName() throws Exception {
		return dpDAO.queryAll();
	}

}
