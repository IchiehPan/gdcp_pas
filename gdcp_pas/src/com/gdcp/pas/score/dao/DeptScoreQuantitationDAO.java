package com.gdcp.pas.score.dao;

import java.util.List;

import com.gdcp.pas.score.vo.DeptScoreQuantitationVO;

public interface DeptScoreQuantitationDAO {

	public List<DeptScoreQuantitationVO> queryAll() throws Exception;

	public void save(String scores) throws Exception;
}
