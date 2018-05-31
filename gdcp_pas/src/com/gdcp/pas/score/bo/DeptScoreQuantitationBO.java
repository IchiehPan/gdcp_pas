package com.gdcp.pas.score.bo;

import java.util.List;

import com.gdcp.pas.score.dao.DeptScoreQuantitationDAO;
import com.gdcp.pas.score.dao.impl.DeptScoreQuantitationDaoImpl;
import com.gdcp.pas.score.vo.DeptScoreQuantitationVO;

public class DeptScoreQuantitationBO {

	public List<DeptScoreQuantitationVO> queryList() throws Exception {
		DeptScoreQuantitationDAO DeptSDao = new DeptScoreQuantitationDaoImpl();
		List<DeptScoreQuantitationVO> DeptList = DeptSDao.queryAll();
		return DeptList;
	}

	public void save(String scores) throws Exception {
		DeptScoreQuantitationDAO DeptSDao = new DeptScoreQuantitationDaoImpl();
		DeptSDao.save(scores);
	}

}
