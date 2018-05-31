package com.gdcp.pas.manage.bo;

/**
 * @author cyx  2015-03-13 
 * @see 提供对用户身份表增删改查等操作
 */
import java.util.List;
import com.gdcp.base.BaseBO;
import com.gdcp.pas.manage.dao.UserLevelDAO;
import com.gdcp.pas.manage.dao.impl.UserLevelDAOImpl;
import com.gdcp.pas.manage.vo.UserLevelVO;

public class UserLevelBO extends BaseBO {

    UserLevelDAO userlevelDAO = new UserLevelDAOImpl();

    /**
     * 
     * @throws Exception
     * @see 查询全部
     */

    public List<UserLevelVO> queryAll() throws Exception {
        return userlevelDAO.queryAll();
    }

    /*
     * public List<UserlevelVO> queryByUSERLEVELFormId(int USERLEVELID) { //
     */
    /**
     * 
     * @throws Exception
     * @see 保存对象
     * 
     *      userlevelVO
     */

    public int save(UserLevelVO userlevelVO) throws Exception {
        return userlevelDAO.saveUserleve(userlevelVO);
    }

    /**
     * 
     * @throws Exception
     * @see 执行更新
     */

    public int updata(int userLevelId, UserLevelVO userlevelVO) throws Exception {
        return userlevelDAO.updata(userLevelId, userlevelVO);
    }

    /**
     * 
     * @throws Exception
     * @see 删除
     */

    public int delete(int userLevelId) throws Exception {
        return userlevelDAO.deleteUserlevel(userLevelId);
    }

    /**
     * 
     * @throws Exception
     * @see 检查输入
     * 
     *      临时变量 用户输入的身份名字 Them
     * 
     */

    public boolean checkName(String Them) throws Exception {
        return userlevelDAO.checkName(Them);
    }

    /**
     * 
     * @throws Exception
     * @see 批量删除
     * 
     *      sqlList sql语句List
     */
    public int executeBatchDelete(String[] sqlList) throws Exception {
        return userlevelDAO.executeBatchDelete(sqlList);
    }

}
