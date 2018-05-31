package com.gdcp.pas.manage.dao.impl;

import java.util.ArrayList;
import java.util.List;
import javax.sql.RowSet;

import com.gdcp.common.SqlUtil;
import com.gdcp.common.StringUtil;
import com.gdcp.common.db.DbAccess;
import com.gdcp.pas.manage.dao.FunctionDAO;
import com.gdcp.pas.manage.vo.FunctionVO;

/**
 * @author �ư���&������ 2015-03-25
 * @see �ṩ�����ݿ��й��ܱ�ĸ��ֲ���
 */
public class FunctionDAOImpl implements FunctionDAO {
    private DbAccess dbAccess = new DbAccess();

    public DbAccess getDbAccess() {
        return dbAccess;
    }

    public void setDbAccess(DbAccess dbAccess) {
        this.dbAccess = dbAccess;
    }

    /**
     * @see ���ݹ���id��ѯ��������
     * @param functionId
     *            ����id
     */
    public String queryFunctionById(String functionId) throws Exception {
        String querySql = "select FUNCTIONNAME from TB_FUNCTION where FUNCTIONID =" + StringUtil.fieldValue(functionId);
        RowSet rs = dbAccess.executeQuery(querySql);
        String functionName = new String();
        if (rs != null && rs.next()) {
            functionName = rs.getString("FUNCTIONNAME");
        }
        return functionName;
    }

    /**
     * @see ��ѯ�����еĹ��ܼ�¼
     */
    public List<FunctionVO> queryFunction() throws Exception {
        String querySql = "select * from TB_FUNCTION";
        RowSet rs = dbAccess.executeQuery(querySql);
        List<FunctionVO> functionList = new ArrayList<>();
        while (rs != null && rs.next()) {
            FunctionVO functionVO = new FunctionVO();
            functionVO.setFunctionId(rs.getString("FUNCTIONID"));
            functionVO.setFunctionName(rs.getString("FUNCTIONNAME"));
            functionVO.setIsValid(rs.getString("ISVALID"));
            functionVO.setRemark(rs.getString("REMARK"));
            functionList.add(functionVO);
        }
        return functionList;
    }

    public void setRoleFunction(int roleId, List<String> userFunctionList,
            List<String> baseFunctionList, List<String> messageFunctionList) throws Exception {
        List<String> Allsql = new ArrayList<>();
        String[] userStringArr = null;
        if (userFunctionList.size() > 0) {
            String Them = userFunctionList.get(0);
            userStringArr = Them.split(",");
        }

        for (int i = 0; i < userStringArr.length; i++) {
            if (!userStringArr[i].equals("")) {
                String insertSql = "insert into TB_USERROLEFUNCTION(ID,OBJECTID,OBJECTTYPE,FUNCTIONID) values("
                        + StringUtil.fieldValue(String.valueOf(SqlUtil.getInstance().getSeqId())) + ","
                        + StringUtil.fieldValue(String.valueOf(roleId)) + "," + StringUtil.fieldValue("1") + ","
                        + StringUtil.fieldValue(userStringArr[i]) + ")";
                Allsql.add(insertSql);
            }
        }

        String[] baseStringArr = null;
        if (baseFunctionList.size() > 0) {
            String Them = baseFunctionList.get(0);
            baseStringArr = Them.split(",");
        }

        for (int i = 0; i < baseStringArr.length; i++) {
            if (!baseStringArr[i].equals("")) {
                String insertSql = "insert into TB_USERROLEFUNCTION(ID,OBJECTID,OBJECTTYPE,FUNCTIONID) values("
                        + StringUtil.fieldValue(String.valueOf(SqlUtil.getInstance().getSeqId())) + ","
                        + StringUtil.fieldValue(String.valueOf(roleId)) + "," + StringUtil.fieldValue("1") + ","
                        + StringUtil.fieldValue(baseStringArr[i]) + ")";
                Allsql.add(insertSql);
            }
        }

        String[] messageStringArr = null;
        if (messageFunctionList.size() > 0) {
            String Them = messageFunctionList.get(0);
            messageStringArr = Them.split(",");
        }

        for (int i = 0; i < messageStringArr.length; i++) {
            if (!messageStringArr[i].equals("")) {
                String insertSql = "insert into TB_USERROLEFUNCTION(ID,OBJECTID,OBJECTTYPE,FUNCTIONID) values("
                        + StringUtil.fieldValue(String.valueOf(SqlUtil.getInstance().getSeqId())) + ","
                        + StringUtil.fieldValue(String.valueOf(roleId)) + "," + StringUtil.fieldValue("1") + ","
                        + StringUtil.fieldValue(messageStringArr[i]) + ")";
                Allsql.add(insertSql);
            }
        }
        if (Allsql.size() >= 1) {
            dbAccess.executeBatchUpdate(Allsql);
        }

    }

    /*
     * (non-Javadoc)
     * 
     * @see ��ѯ��ɫID�µĹ���
     */
    public List<String> queryRoleFunction(String roleId) throws Exception {
        List<String> functionIdList = new ArrayList<>();

        String sql = "select * from TB_USERROLEFUNCTION where OBJECTID=" + StringUtil.fieldValue(roleId);
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null && rs.next()) {
            functionIdList.add(rs.getString("FUNCTIONID"));
        }

        return functionIdList;
    }

    /**
     * @param objectId
     *            String ����id
     * @param objectType
     *            String objectType ȡֵ0���� objectId���û�id�� ȡֵ1 ����objectId�ǽ�ɫid
     * @see ��ѯ������ӵ�е�Ȩ��
     */
    public List<String> queryObjectFunction(String objectId, String objectType) throws Exception {
        List<String> functionIdList = new ArrayList<>();
        String sql = "select * from TB_USERROLEFUNCTION where OBJECTID=" + StringUtil.fieldValue(objectId)
                + " and  OBJECTTYPE=" + objectType;
        RowSet rs = dbAccess.executeQuery(sql);
        while (rs != null && rs.next()) {
            functionIdList.add(rs.getString("FUNCTIONID"));
        }

        return functionIdList;
    }

    /*
     * (non-Javadoc)
     * 
     * @see update��ɫID�µĹ���
     */
    public int updateRoleFunction(int roleId, List<String> userFunctionList, List<String> baseFunctionList,
            List<String> messageFunctionList) throws Exception {

        List<String> Allsql = new ArrayList<>();

        String deleteSql = "DELETE from TB_USERROLEFUNCTION where OBJECTID="
                + StringUtil.fieldValue(String.valueOf(roleId));
        dbAccess.executeUpdate(deleteSql);

        String[] userStringArr = null;
        if (userFunctionList.size() > 0) {
            String Them = userFunctionList.get(0);
            userStringArr = Them.split(",");
        }

        for (int i = 0; i < userStringArr.length; i++) {
            if (!userStringArr[i].equals("")) {
                String insertSql = "insert into TB_USERROLEFUNCTION(ID,OBJECTID,OBJECTTYPE,FUNCTIONID) values("
                        + StringUtil.fieldValue(String.valueOf(SqlUtil.getInstance().getSeqId())) + ","
                        + StringUtil.fieldValue(String.valueOf(roleId)) + "," + StringUtil.fieldValue("1") + ","
                        + StringUtil.fieldValue(userStringArr[i]) + ")";
                Allsql.add(insertSql);
            }
        }

        String[] baseStringArr = null;
        if (baseFunctionList.size() > 0) {
            String Them = baseFunctionList.get(0);
            baseStringArr = Them.split(",");
        }

        for (int i = 0; i < baseStringArr.length; i++) {
            if (!baseStringArr[i].equals("")) {
                String insertSql = "insert into TB_USERROLEFUNCTION(ID,OBJECTID,OBJECTTYPE,FUNCTIONID) values("
                        + StringUtil.fieldValue(String.valueOf(SqlUtil.getInstance().getSeqId())) + ","
                        + StringUtil.fieldValue(String.valueOf(roleId)) + "," + StringUtil.fieldValue("1") + ","
                        + StringUtil.fieldValue(baseStringArr[i]) + ")";
                Allsql.add(insertSql);
            }
        }

        String[] messageStringArr = null;
        if (messageFunctionList.size() > 0) {
            String Them = messageFunctionList.get(0);
            messageStringArr = Them.split(",");
        }

        for (int i = 0; i < messageStringArr.length; i++) {
            if (!messageStringArr[i].equals("")) {
                String insertSql = "insert into TB_USERROLEFUNCTION(ID,OBJECTID,OBJECTTYPE,FUNCTIONID) values("
                        + StringUtil.fieldValue(String.valueOf(SqlUtil.getInstance().getSeqId())) + ","
                        + StringUtil.fieldValue(String.valueOf(roleId)) + "," + StringUtil.fieldValue("1") + ","
                        + StringUtil.fieldValue(messageStringArr[i]) + ")";
                Allsql.add(insertSql);
            }
        }
        int rs = 0;
        if (Allsql.size() >= 1) {
            rs = dbAccess.executeBatchUpdate(Allsql);
        }
        return rs;
    }

    /*
     * (non-Javadoc)
     * 
     * @see ɾ��roldId�µĹ���
     */
    public int deleteFunctionByRoleId(int roleId) throws Exception {
        String deleteSql = "DELETE from TB_USERROLEFUNCTION where OBJECTID="
                + StringUtil.fieldValue(String.valueOf(roleId));
        return dbAccess.executeUpdate(deleteSql);
    }

    public int executeBatchDelete(String[] sqlList) throws Exception {
        List<String> Allsql = new ArrayList<>();
        for (int i = 0; i < sqlList.length; i++) {
            String deleteSql = "DELETE from TB_USERROLEFUNCTION where OBJECTID="
                    + StringUtil.fieldValue(String.valueOf(sqlList[i]));
            Allsql.add(deleteSql);

        }
        return dbAccess.executeBatchUpdate(Allsql);
    }

}
