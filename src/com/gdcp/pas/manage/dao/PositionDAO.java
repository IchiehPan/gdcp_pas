package com.gdcp.pas.manage.dao;

import java.util.List;
import com.gdcp.pas.manage.vo.PositionVO;

/**
 * @author �ư��� 2015-03-19
 * @see �ṩ�����ݿ��и�λ��ĸ��ֲ���
 */
public interface PositionDAO {
	/**
	 * @see ��ѯ�����еĸ�λ��¼
	 */
	public List<PositionVO> queryPosition() throws Exception;

}
