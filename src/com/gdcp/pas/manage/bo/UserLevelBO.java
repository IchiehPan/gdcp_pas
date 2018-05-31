package com.gdcp.pas.manage.bo;

/**
 * @author cyx  2015-03-13 
 * @see �ṩ���û���ݱ���ɾ�Ĳ�Ȳ���
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
     * @see ��ѯȫ��
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
     * @see �������
     * 
     *      userlevelVO
     */

    public int save(UserLevelVO userlevelVO) throws Exception {
        return userlevelDAO.saveUserleve(userlevelVO);
    }

    /**
     * 
     * @throws Exception
     * @see ִ�и���
     */

    public int updata(int userLevelId, UserLevelVO userlevelVO) throws Exception {
        return userlevelDAO.updata(userLevelId, userlevelVO);
    }

    /**
     * 
     * @throws Exception
     * @see ɾ��
     */

    public int delete(int userLevelId) throws Exception {
        return userlevelDAO.deleteUserlevel(userLevelId);
    }

    /**
     * 
     * @throws Exception
     * @see �������
     * 
     *      ��ʱ���� �û������������� Them
     * 
     */

    public boolean checkName(String Them) throws Exception {
        return userlevelDAO.checkName(Them);
    }

    /**
     * 
     * @throws Exception
     * @see ����ɾ��
     * 
     *      sqlList sql���List
     */
    public int executeBatchDelete(String[] sqlList) throws Exception {
        return userlevelDAO.executeBatchDelete(sqlList);
    }

}
