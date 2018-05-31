package com.gdcp.pas.manage.dao;

/**
 * @author cyx  2015-03-13 
 * @see 提供对数据库中用户身份表的各种操作
 */
import java.util.List;

import com.gdcp.pas.manage.vo.UserLevelVO;

public interface UserLevelDAO {

	/**
	 * @return 查询用户身份列表
	 * @throws Exception
	 */
	public List<UserLevelVO> queryAll() throws Exception;

	/**
	 * @param sqlList
	 * @throws Exception
	 * @return批量删除
	 */
	public int executeBatchDelete(String[] sqlList) throws Exception;

	/**
	 * @param them
	 * @return模糊查询的方法
	 */
	/* public List<UserlevelVO> queryByUserlevelFromThem(String them); */

	/**
	 * @param userlevelvo
	 * @throws Exception
	 * @return保存
	 */
	public int saveUserleve(UserLevelVO userlevelVO) throws Exception;

	/**
	 * @param Them
	 * @throws Exception
	 * @return检查名字是否合法
	 */
	public boolean checkName(String userLevelName) throws Exception;

	/**
	 * @param USERLEVELID
	 * @param userlevelvo
	 * @return 更新
	 * @throws Exception
	 */
	public int updata(int USERLEVELID, UserLevelVO userlevelVO) throws Exception;

	/**
	 * @param USERLEVELID
	 * @throws Exception
	 * @return删除
	 */
	public int deleteUserlevel(int userLevelId) throws Exception;

}
