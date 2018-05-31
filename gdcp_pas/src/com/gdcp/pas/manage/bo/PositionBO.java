package com.gdcp.pas.manage.bo;

import java.util.List;
import com.gdcp.pas.manage.dao.PositionDAO;
import com.gdcp.pas.manage.dao.impl.PositionDAOImpl;
import com.gdcp.pas.manage.vo.PositionVO;

/**
 * @author ª∆∞∂≈Ù 2015-03-19
 */
public class PositionBO {
	private PositionDAO positionDAO = new PositionDAOImpl();

	public List<PositionVO> queryPosition() throws Exception {
		return positionDAO.queryPosition();
	}
}
