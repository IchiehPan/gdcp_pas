package com.gdcp.pas.manage.dao.impl;

/**
 * @author �ſ���  2015-03-19 
 * @see �ṩ�Խ�ɫ�û������ɾ�Ĳ�Ȳ���
 */
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.Page;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.dao.UserRoleDAO;
import com.gdcp.pas.manage.vo.UserRoleVO;

public class UserRoleDAOImpl implements UserRoleDAO {
    private DbAccess dbAccess = new DbAccess();

    /**
     * @param userRoleVO
     *            UserRoleVO
     * @see �����ݿ��ѯ�����û���ɫ�ļ�¼
     */
    public List<UserRoleVO> queryPage(Page page) throws Exception {
        String sql = "select * from TB_USERROLE";

        List<UserRoleVO> userRoleList = new ArrayList<>();
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null && rs.next()) {
            UserRoleVO userRoleVO = new UserRoleVO();
            String sqluser = "select * from TB_USER where USERID='" + rs.getString("USERID") + "'";
            RowSet rs1 = dbAccess.executeQuery(sqluser);
            while (rs1.next() && rs1 != null) {
                userRoleVO.setUserName(rs1.getString("USERNAME"));
            }
            String sqlrole = "select * from TB_ROLE where ROLEID='" + rs.getString("ROLEID") + "'";
            RowSet rs2 = dbAccess.executeQuery(sqlrole);
            while (rs2.next() && rs2 != null) {
                userRoleVO.setRoleName(rs2.getString("ROLENAME"));
            }
            userRoleList.add(userRoleVO);
        }
        return userRoleList;
    }

    /**
     * @param userRoleVO
     *            UserRoleVO
     * @see �����ݿ�ɾ��һ���û���ɫ�ļ�¼
     */
    public int deleteRec(UserRoleVO UserRole) throws Exception {
        String userName = new String(UserRole.getUserName().getBytes("ISO-8859-1"), "UTF-8");
        String roleName = new String(UserRole.getRoleName().getBytes("ISO-8859-1"), "UTF-8");
        String userID = null;
        String roleID = null;
        String sqluser = "select * from TB_USER  where USERNAME='" + userName + "'";
        RowSet userRs = dbAccess.executeQuery(sqluser);
        while (userRs != null && userRs.next()) {
            // �û�ID
            userID = userRs.getString("USERID");
        }
        String sqlrole = "select * from TB_ROLE  where ROLENAME='" + roleName + "'";
        RowSet roleRs = dbAccess.executeQuery(sqlrole);
        while (roleRs != null && roleRs.next()) {
            // ��ɫID
            roleID = roleRs.getString("ROLEID");
        }
        String sqlUserRole = "delete  from TB_USERROLE where USERID='" + userID + "' AND ROLEID='" + roleID + "'";
        // ɾ���û���ɫ

        return dbAccess.executeUpdate(sqlUserRole);
    }

    /**
     * @param userRoleVO
     *            UserRoleVO
     * @see �����ݿ��и����û���ɫ�ļ�¼
     */
    public int updateConfi(UserRoleVO ur) throws Exception {

        String userName = ur.getUserName();
        String roleName = ur.getRoleName();
        String oldRoleName = ur.getOldRoleName();
        String oldUserName = ur.getOldUserName();
        String roleID = null;
        String userID = null;
        String oldRoleID = null;
        String oldUserID = null;
        String sql = null;
        String sqlrole = "select * from TB_ROLE  where ROLENAME='" + roleName + "'";
        RowSet roleRs = dbAccess.executeQuery(sqlrole);
        while (roleRs != null && roleRs.next()) {
            // ��ɫID
            roleID = roleRs.getString("ROLEID");
        }
        String sqlOldRole = "select * from TB_ROLE  where ROLENAME='" + oldRoleName + "'";
        RowSet oldRoleRs = dbAccess.executeQuery(sqlOldRole);
        while (oldRoleRs != null && oldRoleRs.next()) {
            // ԭ��ɫID
            oldRoleID = oldRoleRs.getString("ROLEID");
        }
        String sqluser = "select * from TB_USER  where USERNAME='" + userName + "'";
        RowSet userRs = dbAccess.executeQuery(sqluser);
        while (userRs != null && userRs.next()) {
            // �û�ID
            userID = userRs.getString("USERID");
        }
        String sqlOldUser = "select * from TB_USER  where USERNAME='" + oldUserName + "'";
        RowSet oldUserRs = dbAccess.executeQuery(sqlOldUser);
        while (oldUserRs != null && oldUserRs.next()) {
            // ԭ�û�ID
            oldUserID = oldUserRs.getString("USERID");
        }
        if (!userName.equals(oldUserName)) {

            // �ж�Ҫ������û��ͽ�ɫID�Ƿ����
            String sqlUserRole1 = "SELECT * FROM  TB_USERROLE WHERE USERID='" + userID + "' AND ROLEID='" + oldRoleID
                    + "' ";
            RowSet rsUserRole = dbAccess.executeQuery(sqlUserRole1);
            if (rsUserRole != null && rsUserRole.next()) {
                return 0;
            }

            sql = "update TB_USERROLE set USERID='" + userID + "' where USERID='" + oldUserID + "' AND ROLEID='"
                    + roleID + "'";
        } else {
            // �ж�Ҫ������û��ͽ�ɫID�Ƿ����
            String sqlUserRole1 = "SELECT * FROM  TB_USERROLE WHERE USERID='" + oldUserID + "' AND ROLEID='" + roleID
                    + "' ";
            RowSet rsUserRole = dbAccess.executeQuery(sqlUserRole1);
            if (rsUserRole != null && rsUserRole.next()) {
                return 0;
            }

            sql = "update TB_USERROLE set ROLEID='" + roleID + "' where USERID='" + userID + "' AND ROLEID='"
                    + oldRoleID + "'";
        }

        return dbAccess.executeUpdate(sql);
    }

    /**
     * @param userRoleVO
     *            UserRoleVO
     * @see �����ݿ������һ���û���ɫ�ļ�¼
     */
    public int insertRec(UserRoleVO ur) throws Exception {

        String userID = null;
        String roleID = null;
        String sqluser = "select * from TB_USER  where USERNAME='" + ur.getUserName() + "'";
        RowSet userRs = dbAccess.executeQuery(sqluser);
        while (userRs != null && userRs.next()) {
            // �û�ID
            userID = userRs.getString("USERID");
        }
        String sqlrole = "select * from TB_ROLE  where ROLENAME='" + ur.getRoleName() + "'";
        RowSet roleRs = dbAccess.executeQuery(sqlrole);
        while (roleRs != null && roleRs.next()) {
            // ��ɫID
            roleID = roleRs.getString("ROLEID");
        }
        // �ж�Ҫ������û��ͽ�ɫID�Ƿ����
        String sqlUserRole1 = "SELECT * FROM  TB_USERROLE WHERE USERID='" + userID + "' AND ROLEID='" + roleID + "' ";
        RowSet rsUserRole = dbAccess.executeQuery(sqlUserRole1);
        if (rsUserRole != null && rsUserRole.next()) {
            return 0;
        }
        String sqlUserRole = "insert into TB_USERROLE(USERID,ROLEID) values('" + userID + "','" + roleID + "')";
        return dbAccess.executeUpdate(sqlUserRole);
    }

    /**
     * @param userRoleVO
     *            UserRoleVO
     * @see �����ݿ��в�ѯ���н�ɫ���ֵļ�¼
     */
    public List<UserRoleVO> queryRoleName() throws Exception {
        String sql = "select * from TB_ROLE";
        List<UserRoleVO> roleList = new ArrayList<>();
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null & rs.next()) {
            UserRoleVO ur = new UserRoleVO();
            ur.setRoleName(rs.getString("ROLENAME"));
            roleList.add(ur);
        }
        return roleList;
    }

    /**
     * @param userRoleVO
     *            UserRoleVO
     * @see �����ݿ��в�ѯ�����û����ֵļ�¼
     */
    public List<UserRoleVO> queryUserName() throws Exception {
        String sql = "select * from TB_USER";
        List<UserRoleVO> userList = new ArrayList<>();
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null & rs.next()) {
            UserRoleVO ur = new UserRoleVO();
            ur.setUserName(rs.getString("USERNAME"));
            userList.add(ur);
        }
        return userList;
    }

}
