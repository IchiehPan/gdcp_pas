package com.gdcp.pas.manage.dao;

import java.util.List;
import com.gdcp.pas.manage.vo.PositionVO;

/**
 * @author 黄岸鹏 2015-03-19
 * @see 提供对数据库中岗位表的各种操作
 */
public interface PositionDAO {
	/**
	 * @see 查询出所有的岗位记录
	 */
	public List<PositionVO> queryPosition() throws Exception;

}
