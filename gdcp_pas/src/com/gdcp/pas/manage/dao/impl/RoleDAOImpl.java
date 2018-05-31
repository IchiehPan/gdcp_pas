package com.gdcp.pas.manage.dao.impl;

/**
 * @author 刘伯睿  2015-03-13 
 * @see 提供对数据库中角色表的各种操作
 */
import java.util.ArrayList;
import java.util.List;

import javax.sql.RowSet;

import com.gdcp.common.Page;
import com.gdcp.common.SqlUtil;
import com.gdcp.common.StringUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.dao.RoleDAO;
import com.gdcp.pas.manage.vo.RoleVO;

public class RoleDAOImpl implements RoleDAO {

    private DbAccess dbAccess = new DbAccess();

    /**
     * @param roleVO
     *            RoleVO
     * @see 往数据库插入一个角色的记录
     */
    public RoleVO getRoleById(String roleId) throws Exception {
        RoleVO roleVO = new RoleVO();
        String querySql = "select * from tb_role where roleId =" + StringUtil.fieldValue(roleId);
        RowSet rs = dbAccess.executeQuery(querySql);
        if (rs != null && rs.next()) {
            roleVO.setRoleId(roleId);
            roleVO.setRoleName(rs.getString("roleName"));
            roleVO.setRemark(rs.getString("remark"));
        }
        return roleVO;
    }

    /**
     * @param roleVO
     *            RoleVO
     * @see 往数据库插入一个角色的记录
     */
    public int insertRec(RoleVO roleVO) throws Exception {

        roleVO.setRoleId(String.valueOf(SqlUtil.getInstance().getSeqId()));
        String insertSql = "insert into tb_role(roleId,roleName,remark) values("
                + StringUtil.fieldValue(roleVO.getRoleId()) + "," + StringUtil.fieldValue(roleVO.getRoleName()) + ","
                + StringUtil.fieldValue(roleVO.getRemark()) + ")";

        String checkSql = "select * from tb_role";
        RowSet rowSet = dbAccess.executeQuery(checkSql);
        while (rowSet != null && rowSet.next()) {
            if (rowSet.getString("ROLENAME").equals(roleVO.getRoleName())) {
                return 0;
            }
        }
        dbAccess.executeUpdate(insertSql);
        return Integer.parseInt(roleVO.getRoleId().trim());
    }

    /**
     * @param roleVO
     *            RoleVO
     * @see 往数据库修改一个角色的记录
     */
    public int updateRec(RoleVO roleVO) throws Exception {
        String updateSql = "update tb_role set roleName=" + StringUtil.fieldValue(roleVO.getRoleName()) + ",remark="
                + StringUtil.fieldValue(roleVO.getRemark()) + "  where roleId="
                + StringUtil.fieldValue(roleVO.getRoleId());

        return dbAccess.executeUpdate(updateSql);
    }

    /**
     * @param roleId
     *            String 角色Id
     * @see 往数据库删除一个角色的记录
     */
    public int deleteRec(String roleId) throws Exception {
        String updateSql = "delete tb_role where roleId=" + StringUtil.fieldValue(roleId);

        String checkSql = "select * from TB_USER where ROLEID=" + StringUtil.fieldValue(String.valueOf(roleId)) + "";

        RowSet rowSet = dbAccess.executeQuery(checkSql);
        if (rowSet.next()) {
            return 0;
        } else {
            return dbAccess.executeUpdate(updateSql);
        }

    }

    /**
     * @param roleVO
     *            RoleVO
     * @throws Exception
     * @see 查询出一页的RoleVO记录
     */
    public List<RoleVO> queryPage(Page page, RoleVO qroleVO) throws Exception {

        List<RoleVO> list = new ArrayList<>();
        String querySql = "select * from TB_ROLE where 1=1";
        String countSql = "select count(*) from TB_ROLE where 1=1";
        if (qroleVO != null && !StringUtil.isNullOrBlank(qroleVO.getRoleName())) {
            querySql = querySql + " and ROLENAME like'%" + qroleVO.getRoleName() + "%'";
            countSql = countSql + " and ROLENAME like'%" + qroleVO.getRoleName() + "%'";
        }
        RowSet rs = dbAccess.executeQuery(countSql);
        if (rs != null && rs.next()) {
            page.setTotal(rs.getInt(1));
        }

        querySql = SqlUtil.getSQLServerPageSQL(page.getPageNo(), page.getPageSize(), "ROLEID", querySql);
        rs = dbAccess.executeQuery(querySql);
        while (rs != null && rs.next()) {
            RoleVO roleVO = new RoleVO();
            roleVO.setRoleId(rs.getString("roleId"));
            roleVO.setRoleName(rs.getString("roleName"));
            roleVO.setRemark(rs.getString("REMARK"));
            list.add(roleVO);
        }
        return list;

    }

    @Override
    public int executeBatchDelete(String[] sqlList) throws Exception {
        List<String> Allsql = new ArrayList<>();
        for (int i = 0; i < sqlList.length; i++) {
            String checkSql = "select * from TB_USER where ROLEID=" + StringUtil.fieldValue(String.valueOf(sqlList[i]));
            RowSet rowSet = dbAccess.executeQuery(checkSql);
            if (rowSet.next()) {
                return 0;
            }
            String sql = "DELETE from tb_role where roleId='" + sqlList[i] + "'";
            Allsql.add(sql);
        }

        int rs = dbAccess.executeBatchUpdate(Allsql);
        return rs;
    }

    public DbAccess getDbAccess() {
        return dbAccess;
    }

    public void setDbAccess(DbAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    @Override
    public boolean checkRoleName(String roleName) throws Exception {
        String sql = "select * from tb_role";
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null && rs.next()) {
            if (rs.getString("ROLENAME").equals(roleName)) {

                return false;
            }
        }

        return true;
    }

    @Override
    public List<RoleVO> queryRole() throws Exception {

        String sql = "select * from tb_role";
        List<RoleVO> roleList = new ArrayList<>();
        // sql = SqlUtil.getInstance().getPageSql(sql,"id", page.getPageSize(),
        // page.getPageNo());
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null && rs.next()) {
            RoleVO roleVO = new RoleVO();
            roleVO.setRoleId(rs.getString("roleId"));
            roleVO.setRoleName(rs.getString("roleName"));
            roleVO.setRemark(rs.getString("REMARK"));
            roleList.add(roleVO);

        }
        // page.setTotal(SqlUtil.getInstance().getCount(sql));

        return roleList;
    }

    @Override
    public List<RoleVO> query() {
        return null;
    }

}
